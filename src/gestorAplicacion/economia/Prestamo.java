package gestorAplicacion.economia;

import gestorAplicacion.administrador.Utils;
import gestorAplicacion.usuario.Bolsillo;
import gestorAplicacion.usuario.Cuenta;
import gestorAplicacion.usuario.Usuario;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Prestamo implements Serializable, Abonable {

    /**
     *
     */
    private static final long serialVersionUID = -5514442359859569278L;

    private Usuario usuario;
    protected double valorInicial;
    protected double valorPagado;
    protected int tiempo;            //meses diferidos
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private Divisa divisa;
    private boolean cumplida;
    private double interesesPendientes;
    private LocalDate ultimaFechaPago;
    protected double TEA;

    public Prestamo(Usuario usuario, double valorInicial, int tiempo, LocalDate fechaInicio, LocalDate fechaFinal, Divisa divisa) {
        setUsuario(usuario);
        setValorInicial(valorInicial);
        valorPagado = 0;
        setDivisa(divisa);
        setTiempo(tiempo);
        setFechaInicio(fechaInicio);
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

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
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

    public void calcularCuotas() {
        long n = Utils.mesesEntreFechas(this.ultimaFechaPago, this.fechaFinal);
        double capitalPendiente = this.interesesPendientes + this.valorInicial - this.valorPagado;
        double r = Math.pow(1 + this.TEA / 100, 1 / 12.0) - 1;
        double cuotaMensual = (r * capitalPendiente) / (1 - Math.pow(1 + r, -n));
        System.out.println("Se recomienda una cuota mensual de " + cuotaMensual + " " + this.divisa);
        System.out.println("Para poder dar cumplimiento a la fecha del cobro");
    }

    public boolean abonar(double monto, Cuenta origen) {
        if (!cumplida) {
            double capitalPendiente = this.valorInicial - this.valorPagado;
            long diasDeIntereses = Utils.diasEntreFechas(this.ultimaFechaPago, LocalDate.now());
            double intereses = interesesPendientes + ((this.interesesPendientes + capitalPendiente) * (Math.pow(1 + (this.TEA / 100), diasDeIntereses / 365.0) - 1));
            double pendiente = intereses + capitalPendiente;
            LocalDate now = LocalDate.now();
            Salida salida = new Salida(monto, now, origen, null, origen.getDivisa(), this.divisa);
            if (this.usuario.nuevaSalida(salida)) {
                double[] monto2 = origen.getDivisa().ConvertToDivisa(monto, this.divisa);
                double nuevosIntereses = Math.max(0, intereses - monto2[0]);
                double nuevoCapital = Math.min(capitalPendiente, pendiente - monto2[0]);
                System.out.println("Intereses pagados de: " + (intereses - nuevosIntereses) + " " + this.divisa);
                System.out.println("Abono a Capital de: " + Math.max(0, monto2[0] - intereses + nuevosIntereses) + " " + this.divisa);
                System.out.println("TRM usada de: " + monto2[1]);
                this.interesesPendientes = nuevosIntereses;
                this.valorPagado = this.valorInicial - nuevoCapital;
                this.ultimaFechaPago = LocalDate.now();
                if (this.valorPagado < this.valorInicial) {
                    System.out.println("Te queda por pagar: " + nuevoCapital);
                    calcularCuotas();
                } else {
                    System.err.println("FELICIDADES PAGASTE TU PRESTAMO");
                    this.cumplida = true;
                    if (valorPagado > valorInicial) {
                        System.out.println("Quedaste con un saldo a favor de: " + (valorPagado - valorInicial));
                        System.out.println("Este se enviara a tu Bolsillo default");
                        Bolsillo bolsillo = this.usuario.getBolsillos().get(0);
                        Ingreso ingreso = new Ingreso(this.divisa.ConvertToDivisa(valorPagado - valorInicial, bolsillo.getDivisa())[0], LocalDate.now(), true, null, null, bolsillo, this.divisa, bolsillo.getDivisa());
                        usuario.nuevoIngreso(ingreso);
                    }
                }
                return true;
            }
            return false;
        }
        System.err.println("El prestamo ya esta pago, no es posible generar el abono");
        return false;

    }
}
