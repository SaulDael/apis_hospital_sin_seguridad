package med.voll.api.Domain.Pacientes;

public record DatosListadoPaciente(Long id, String nombre, String email, String CURP) {

    public DatosListadoPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getCURP());
    }
}
