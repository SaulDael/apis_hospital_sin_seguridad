package med.voll.api.INFRA.Excepciones;

public class ValidacionDeIntegridad extends RuntimeException {

    public ValidacionDeIntegridad(String s){
        super(s);
    }
}
