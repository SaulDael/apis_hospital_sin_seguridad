package med.voll.api.Domain.Consultas;

import med.voll.api.Domain.Medicos.Medico;
import med.voll.api.Domain.Medicos.MedicoRepository;
import med.voll.api.Domain.Pacientes.PacienteRepository;
import med.voll.api.INFRA.Excepciones.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultaService {
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private ConsultaRepository consultaRepository;

    public void agendar(DatosAgendarConsulta datos){

        if (pacienteRepository.findById(datos.idPaciente()).isPresent()){
            throw new ValidacionDeIntegridad("Este ID para el Paciente no fue encontrado");
        }

        if(datos.idMedico()!= null && medicoRepository.existsById(datos.idMedico())){
            throw new ValidacionDeIntegridad("Este ID para el Medico no fue encontrado");
        }

        var paciente = pacienteRepository.findById(datos.idPaciente()).get();

        var medico = seleccionarMedico(datos);

        var consulta = new Consulta(null, medico, paciente, datos.fecha());

        consultaRepository.save(consulta);
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
}