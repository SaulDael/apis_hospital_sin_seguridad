package med.voll.api.Domain.Consultas;

import med.voll.api.Domain.Consultas.Validaciones.ValidadorDeConsultas;
import med.voll.api.Domain.Consultas.Validaciones.ValidadrorCancelamientoConsulta;
import med.voll.api.Domain.Medicos.Medico;
import med.voll.api.Domain.Medicos.MedicoRepository;
import med.voll.api.Domain.Pacientes.PacienteRepository;
import med.voll.api.INFRA.Excepciones.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("all")
public class AgendaDeConsultaService {
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    List<ValidadorDeConsultas> validadores;

    private List<ValidadrorCancelamientoConsulta> validadrorCancelamientoConsultas;

    public DatosDetallesConsulta agendar(DatosAgendarConsulta datos) {

        if (!pacienteRepository.findById(datos.idPaciente()).isPresent()) {
            throw new ValidacionDeIntegridad("Este ID para el Paciente no fue encontrado");
        }

        if (datos.idMedico() != null && !medicoRepository.existsById(datos.idMedico())) {
            throw new ValidacionDeIntegridad("Este ID para el Medico no fue encontrado");
        }

        validadores.forEach(v->v.validar(datos));

        var paciente = pacienteRepository.findById(datos.idPaciente()).get();

        var medico = seleccionarMedico(datos);

        if (medico == null) {
            throw new ValidacionDeIntegridad("No existen medicos disponibles para este horario y especialidad");
        }


        var consulta = new Consulta(medico, paciente, datos.fecha());

        consultaRepository.save(consulta);

        return new DatosDetallesConsulta(consulta);
    }

    private Medico seleccionarMedico(DatosAgendarConsulta datos) {
        if(datos.idMedico()!=null){
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if(datos.especialidad()==null){
            throw new ValidacionDeIntegridad("Debe seleccionarse una especialidad para el medico");
        }
        return medicoRepository.seleccionarMedicoConEspecialidadEnFecha(datos.especialidad(), datos.fecha());
    }

    public  void cancelar(DatosCancelarConsulta datos){
        if(!consultaRepository.existsById(datos.idConsulta())){
            throw new ValidacionDeIntegridad("ID de consulta no existe");
        }

        validadrorCancelamientoConsultas.forEach(v -> v.valida(datos));

        var consulta = consultaRepository.getReferenceById(datos.idConsulta());
        consulta.cancelar(datos.motivoCancelamiento());
    }

    public Page<DatosDetallesConsulta> consultar(Pageable paginacion){
        return consultaRepository.findAll(paginacion).map(DatosDetallesConsulta::new);
    }
}
