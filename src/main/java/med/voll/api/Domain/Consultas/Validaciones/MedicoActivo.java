package med.voll.api.Domain.Consultas.Validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.Domain.Consultas.DatosAgendarConsulta;
import med.voll.api.Domain.Medicos.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoActivo implements ValidadorDeConsultas{

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DatosAgendarConsulta datos){

        if(datos.idMedico()==null){
            return;
        }
        var medicoActivo = medicoRepository.finActivoById(datos.idMedico());

        if(medicoActivo == null){
            throw new ValidationException("No puede permitir programar citas con médicos inactivos en el sistema");
        }
    }
}
