package med.voll.api.Domain.Pacientes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.Domain.Direccion.Direccion;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String CURP;

    private boolean activo;
    @Embedded
    private Direccion direccion;

    public Paciente(DatosRegistroPaciente datosRegistroPaciente){
        this.activo = true;
        this.nombre = datosRegistroPaciente.nombre();
        this.email = datosRegistroPaciente.email();
        this.telefono = datosRegistroPaciente.telefono();
        this.CURP = datosRegistroPaciente.CURP();
        this.direccion = new Direccion(datosRegistroPaciente.direccion());

    }

    public  void  desactivarPaciente(){
        this.activo = false;
    }

    public void actualizarInformacion(DatosActualizarPaciente datosActualizarPaciente) {
        if (datosActualizarPaciente.nombre() != null) {
            this.nombre = datosActualizarPaciente.nombre();
        }
        if (datosActualizarPaciente.email() != null) {
            this.email = datosActualizarPaciente.email();
        }
        if (datosActualizarPaciente.direccion() != null) {
            this.direccion.actualizarDatos(datosActualizarPaciente.direccion());
        }
    }
}
