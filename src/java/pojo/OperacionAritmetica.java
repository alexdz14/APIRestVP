package pojo;

/**
 *
 * @author stivm
 */
public class OperacionAritmetica {
    
    private String operacion;
    private String operandos;
    private float resultado;

    public OperacionAritmetica() {
    }

    public OperacionAritmetica(String operacion, String operandos, float resultado) {
        this.operacion = operacion;
        this.operandos = operandos;
        this.resultado = resultado;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getOperandos() {
        return operandos;
    }

    public void setOperandos(String operandos) {
        this.operandos = operandos;
    }

    public float getResultado() {
        return resultado;
    }

    public void setResultado(float resultado) {
        this.resultado = resultado;
    }
    
}
