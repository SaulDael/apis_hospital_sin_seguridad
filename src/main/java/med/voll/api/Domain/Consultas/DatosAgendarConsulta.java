package med.voll.api.Domain.Consultas;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.Domain.Medicos.Especialidad;

import java.time.LocalDateTime;

public record DatosAgendarConsulta(Long id, @NotNull Long idPaciente, Long idMedico,
                                   @NotNull @Future LocalDateTime fecha,Especialidad especialidad) {
}
