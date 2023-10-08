package med.voll.api.Domain.Consultas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.NonNull;

public record DatosCancelarConsulta(
        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamiento motivoCancelamiento) {
}
