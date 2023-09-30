package med.voll.api.Domain.Consultas.Validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.Domain.Consultas.ConsultaRepository;
import med.voll.api.Domain.Consultas.DatosAgendarConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoConConsulta implements ValidadorDeConsultas{

    @Autowired
    private ConsultaRepository consultaRepository;
    public void validar(DatosAgendarConsulta datos){
        if (datos.idMedico() == null)
            return;

            var medicoConConsulta = consultaRepository.existsByMedicoIdAndData(datos.idMedico(), datos.fecha());

            if (medicoConConsulta){
             throw new ValidationException("Este medico ya tiene una consulta en ese horario");
         }


    }
}
