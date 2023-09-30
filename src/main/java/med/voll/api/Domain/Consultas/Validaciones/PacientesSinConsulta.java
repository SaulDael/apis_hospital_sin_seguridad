package med.voll.api.Domain.Consultas.Validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.Domain.Consultas.ConsultaRepository;
import med.voll.api.Domain.Consultas.DatosAgendarConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacientesSinConsulta implements ValidadorDeConsultas{

    @Autowired
    private ConsultaRepository consultaRepository;
    public void validar(DatosAgendarConsulta datos){
        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario = datos.fecha().withHour(18);

        var pacienteConConsulta= consultaRepository.existsByPacienteIdAndDataBetween(datos.idPaciente(), primerHorario, ultimoHorario);

        if (pacienteConConsulta) {
            throw new ValidationException("El paciente ya tiene una consulta para ese dia");
        }
    }
}
