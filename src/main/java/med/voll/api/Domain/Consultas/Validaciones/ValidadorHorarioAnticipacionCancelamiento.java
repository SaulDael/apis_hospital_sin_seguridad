package med.voll.api.Domain.Consultas.Validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.Domain.Consultas.ConsultaRepository;
import med.voll.api.Domain.Consultas.DatosCancelarConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAnticipacionCancelamiento")
public class ValidadorHorarioAnticipacionCancelamiento implements ValidadrorCancelamientoConsulta{

    @Autowired
    private ConsultaRepository consultaRepository;
    @Override
    public void valida(DatosCancelarConsulta datos) {
        var consulta = consultaRepository.getReferenceById(datos.idConsulta());
        var ahora = LocalDateTime.now();
        var diferenciaHoras = Duration.between(ahora, consulta.getData()).toHours();

        if (diferenciaHoras < 24) {
            throw new ValidationException("¡La consulta solamenet puede ser cancelada con una anticipacion de 24 horas!");
        }

    }
}
