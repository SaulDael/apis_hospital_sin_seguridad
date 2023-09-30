package med.voll.api.Domain.Consultas.Validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.Domain.Consultas.DatosAgendarConsulta;
import med.voll.api.Domain.Pacientes.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteActivo implements ValidadorDeConsultas{

    @Autowired
    private PacienteRepository pacienteRepository;
    public void validar(DatosAgendarConsulta datos){

        if(datos.idPaciente()==null){
            return;
        }
        var pacienteActivo = pacienteRepository.finActivoById(datos.idPaciente());

        if(pacienteActivo == null){
            throw new ValidationException("No se puede permitir agendar citas con pacientes inactivos en el sistema");
        }
    }
}
