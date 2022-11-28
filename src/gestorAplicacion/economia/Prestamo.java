package gestorAplicacion.economia;

import administrador.Utils;
import gestorAplicacion.usuario.Cuenta;
import gestorAplicacion.usuario.Usuario;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Prestamo implements Serializable, Abonable<double[]> {

    /**
     *
     */
    private static final long serialVersionUID = -5514442359859569278L;

    private Usuario usuario;
    protected double valorInicial;
    protected double valorPagado;
    protected int tiempo;            //meses diferidos
    private final LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private Divisa divisa;
    private boolean cumplida;
    private double interesesPendientes;
    private LocalDate ultimaFechaPago;
    protected double TEA;
    protected final double baseTEAAlto = 34.49;  //bases para calcular los intereses dependiendo de las clases hijas
    protected final double baseTEABajo = 30.69;

    public Prestamo(Usuario usuario, double valorInicial, int tiempo, LocalDate fechaInicio, LocalDate fechaFinal, Divisa divisa) {
        setUsuario(usuario);
        setValorInicial(valorInicial);
        valorPagado = 0;
        setDivisa(divisa);
        setTiempo(tiempo);
        this.fechaInicio = fechaInicio;
        setUltimaFechaPago(fechaInicio);
        setFechaPago(fechaFinal);
        setCumplida(false);
        setInteresesPendientes(0);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public LocalDate getUltimaFechaPago() {
        return ultimaFechaPago;
    }

    public void setUltimaFechaPago(LocalDate ultimaFechaPago) {
        this.ultimaFechaPago = ultimaFechaPago;
    }

    public double getTEA() {
        return TEA;
    }

    protected abstract void setTEA(double monto);

    protected abstract void setTEA(double monto, Garantia garantia);

    public double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(double valorInicial) {
        this.valorInicial = valorInicial;
    }

    public double getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(double valorPagado) {
        this.valorPagado = valorPagado;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaPago() {
        return fechaFinal;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaFinal = fechaPago;
    }

    public Divisa getDivisa() {
        return divisa;
    }

    public void setDivisa(Divisa divisa) {
        this.divisa = divisa;
    }

    public boolean isCumplida() {
        return cumplida;
    }

    public double getInteresesPendientes() {
        return interesesPendientes;
    }

    public void setInteresesPendientes(double interesesPendientes) {
        this.interesesPendientes = interesesPendientes;
    }

    public void setCumplida(boolean cumplida) {
        this.cumplida = cumplida;
    }

    public double calcularCuotas() {
        long n = Utils.mesesEntreFechas(this.ultimaFechaPago, this.fechaFinal);
        double capitalPendiente = this.interesesPendientes + this.valorInicial - this.valorPagado;
        double r = Math.pow(1 + this.TEA / 100, 1 / 12.0) - 1;
        double cuotaMensual = (r * capitalPendiente) / (1 - Math.pow(1 + r, -n));
        return cuotaMensual;
    }

    public double[] abonar(double monto, Cuenta origen) {
    	double[] arreglo = new double[3];
        if (!cumplida) {
            double capitalPendiente = this.valorInicial - this.valorPagado;
            long diasDeIntereses = Utils.diasEntreFechas(this.ultimaFechaPago, LocalDate.now());
            double intereses = interesesPendientes + ((this.interesesPendientes + capitalPendiente) * (Math.pow(1 + (this.TEA / 100), diasDeIntereses / 365.0) - 1));
            double pendiente = intereses + capitalPendiente;
            LocalDate now = LocalDate.now();
            double[] monto2 = origen.getDivisa().ConvertToDivisa(monto, this.divisa);
            Salida salida = new Salida(monto2[0],monto, now, origen, null, origen.getDivisa(), this.divisa);
            if (this.usuario.nuevaSalida(salida)) {
                double nuevosIntereses = Math.max(0, intereses - monto2[0]);
                double nuevoCapital = Math.min(capitalPendiente, pendiente - monto2[0]);
                arreglo[0] = intereses - nuevosIntereses;
                arreglo[1] = Math.max(0, monto2[0] - intereses + nuevosIntereses);
                arreglo[2] = monto2[1];
                this.interesesPendientes = nuevosIntereses;
                this.valorPagado = this.valorInicial - nuevoCapital;
                this.ultimaFechaPago = LocalDate.now();
                return arreglo;                
            }
            return null;
        }
        return null;
    }

    public Movimiento terminar(Cuenta cuenta) {
    	 this.cumplida = true;
         if (valorPagado > valorInicial) {
             Ingreso ingreso = new Ingreso(this.divisa.ConvertToDivisa(valorPagado - valorInicial, cuenta.getDivisa())[0],(valorPagado-valorInicial), LocalDate.now(), true, null, null, cuenta, this.divisa, cuenta.getDivisa());
             usuario.nuevoIngreso(ingreso);
             return ingreso;
         }
         return null;
    }
}
