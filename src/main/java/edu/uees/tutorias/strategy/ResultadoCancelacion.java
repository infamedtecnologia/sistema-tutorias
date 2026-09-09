package edu.uees.tutorias.strategy;

public class ResultadoCancelacion {
    private final boolean exitosa;
    private final double porcentajePenalizacion;
    private final String mensaje;

    public ResultadoCancelacion(boolean exitosa, double porcentajePenalizacion, String mensaje) {
        this.exitosa = exitosa;
        this.porcentajePenalizacion = porcentajePenalizacion;
        this.mensaje = mensaje;
    }

    public boolean isExitosa() { return exitosa; }
    public double getPorcentajePenalizacion() { return porcentajePenalizacion; }
    public String getMensaje() { return mensaje; }
}
