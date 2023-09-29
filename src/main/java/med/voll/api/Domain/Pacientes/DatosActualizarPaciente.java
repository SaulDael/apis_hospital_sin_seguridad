package med.voll.api.Domain.Pacientes;

import jakarta.validation.constraints.NotNull;
import med.voll.api.Domain.Direccion.DatosDireccion;

public record DatosActualizarPaciente(
        @NotNull Long id,
        String nombre,
        String email ,
        DatosDireccion direccion
) {
}
