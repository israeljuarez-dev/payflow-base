package com.nttdata.payflow.model;

/**
 * Empleado con sueldo fijo mensual.
 */
public class EmpleadoPlanilla extends Empleado implements Bonificable {

    private static final double PORCENTAJE_BONO = 0.10;
    private static final double ASIGNACION_FAMILIAR = 100.00;

    private final double sueldoBase;
    private final boolean tieneHijos;
    private final int evaluacion;

    public EmpleadoPlanilla(String id, String nombre, Area area,
                            double sueldoBase, boolean tieneHijos, int evaluacion) {
        super(id, nombre, area);
        if (!(sueldoBase > 0)) {
            throw new IllegalArgumentException("El sueldo base debe ser positivo");
        }
        if (evaluacion < 1 || evaluacion > 5) {
            throw new IllegalArgumentException("La evaluación debe estar entre 1 y 5");
        }
        this.sueldoBase = sueldoBase;
        this.tieneHijos = tieneHijos;
        this.evaluacion = evaluacion;
    }

    @Override
    public double calcularBono() {
        return evaluacion >= 4 ? sueldoBase * PORCENTAJE_BONO : 0;
    }

    @Override
    public double calcularPagoMensual() {
        return sueldoBase + (tieneHijos ? ASIGNACION_FAMILIAR : 0) + calcularBono();
    }

    @Override
    public String getTipo() {
        return "Planilla";
    }

    public double getSueldoBase() {
        return sueldoBase;
    }
}
