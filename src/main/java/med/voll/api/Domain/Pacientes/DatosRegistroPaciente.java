package med.voll.api.Domain.Pacientes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.Domain.Direccion.DatosDireccion;

public record DatosRegistroPaciente(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefono,
        @NotBlank
        @Pattern(regexp = "^[A-Z]{4}[0-9]{6}[H,M][A-Z]{6}[0-9]{1}$")
        String CURP,
        @NotNull
        @Valid
        DatosDireccion direccion
) {
}
