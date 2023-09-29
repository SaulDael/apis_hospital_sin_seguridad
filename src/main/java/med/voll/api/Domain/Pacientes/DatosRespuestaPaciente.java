package med.voll.api.Domain.Pacientes;

import med.voll.api.Domain.Direccion.DatosDireccion;

public record DatosRespuestaPaciente(
        Long id,
        String nombre,
        String email,
        String telefono,
        String CURP,
        DatosDireccion direccion
) {
}
