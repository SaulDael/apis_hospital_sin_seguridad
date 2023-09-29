package med.voll.api.Domain.Medicos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.Domain.Direccion.Direccion;

public record DatosRegistroMedico(

        @NotBlank
        String nombre,
        @NotBlank
                @Email
        String email,
        @NotBlank
        String telefono,
        @NotBlank
                @Pattern(regexp = "\\d{4,6}")
        String documento,
        @NotNull
        Especialidad especialidad,
        @NotNull
                @Valid
        Direccion direccion
) {
}
