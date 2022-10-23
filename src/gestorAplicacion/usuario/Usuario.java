package gestorAplicacion.usuario;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import gestorAplicacion.economia.*;


public class Usuario implements Serializable {

    private static final long serialVersionUID = -64431385135968757L;

    private String cedula;
    private String nombre;
    private String email;
    private LocalDate fechaIngreso;
    private String clave;
    private List<Bolsillo> bolsillos = new ArrayList<>();
    private List<Colchon> colchones = new ArrayList<>();
    private List<Ingreso> ingresos = new ArrayList<>();
    private List<Salida> salidas = new ArrayList<>();
    private List<Prestamo> prestamos = new ArrayList<>();

    private List<Meta> metas = new ArrayList<>();


    public Usuario(String cedula, String nombre, String email, LocalDate fechaIngreso, String clave) {
        setCedula(cedula);
        setNombre(nombre);
        setEmail(email);
        setFechaIngreso(fechaIngreso);
        setClave(clave);
        bolsillos.add(new Bolsillo(this, Divisa.COP, "DEFAULT"));
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public List<Bolsillo> getBolsillos() {
        return bolsillos;
    }

    public void setBolsillos(List<Bolsillo> bolsillos) {
        this.bolsillos = bolsillos;
    }

    public List<Colchon> getColchones() {
        return colchones;
    }

    public void setColchones(List<Colchon> colchones) {
        this.colchones = colchones;
    }

    public List<Ingreso> getIngresos() {
        return ingresos;
    }

    public void setIngresos(List<Ingreso> ingresos) {
        this.ingresos = ingresos;
    }

    public List<Salida> getSalidas() {
        return salidas;
    }

    public void setSalidas(List<Salida> salidas) {
        this.salidas = salidas;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public List<Meta> getMetas() {
        return metas;
    }

    public void setMetas(List<Meta> metas) {
        this.metas = metas;
    }

    public void nuevoIngreso(Ingreso ingreso) {
        ingreso.getCuentaDestino().depositar(ingreso.getValor());
        ingresos.add(ingreso);
    }
    public boolean nuevaSalida(Salida salida) {
        boolean retirado = salida.getCuentaOrigen().retirar(salida.getValor());
        if(retirado){
            salidas.add(salida);
        }
        return retirado;
    }

    public void nuevoBolsillo(Bolsillo bolsillo) {
        bolsillos.add(bolsillo);
    }

    public void nuevoColchon(Colchon colchon) {
        colchones.add(colchon);
    }

    public void nuevaMeta(Meta meta) {
        metas.add(meta);
    }

    public void nuevoPrestamo(PrestamoLargoPlazo prestamo, Bolsillo bolsillo) {
        prestamos.add(prestamo);
        bolsillo.depositar(prestamo.getDivisa().ConvertToDivisa(prestamo.getValorInicial(), bolsillo.getDivisa())[0]);
    }
    public void nuevoPrestamo(PrestamoFugaz prestamo, Bolsillo bolsillo) {
        prestamos.add(prestamo);
        bolsillo.depositar(prestamo.getDivisa().ConvertToDivisa(prestamo.getValorInicial(), bolsillo.getDivisa())[0]);
    }

    public double[] getDineroTotal() {
        double[] total = new double[Divisa.values().length];
        List<Contable> contables = new ArrayList<>();
        contables.addAll(getBolsillos());
        contables.addAll(getColchones());
        contables.addAll(getMetas());
        for (Contable i : contables) {
            for (int j = 0; j < Divisa.values().length; j++) {
                if (i.getDivisa().equals(Divisa.values()[j])) {
                    total[j] += i.getSaldo();
                    break;/**
     *
     */
                }
            }
        }
        return total;
    }

    //Se listan los bolsillos del usuario
    public boolean listarBolsillos() {
        System.out.println("---------------------------------------------------------");
        if (!this.getBolsillos().isEmpty()) {
            int j = 1;
            for (Bolsillo i : this.getBolsillos()) {
                System.out.println(j + ". " + i.getNombre() + "\t\tDisponible: " + i.getSaldo() + "\t\tDivisa: " + i.getDivisa());
                j++;
            }
            System.out.println("---------------------------------------------------------");
            return true;
        } else {
            System.out.println("EL USUARIO NO POSEE BOLSILLOS...\n");
            System.out.println("---------------------------------------------------------");
            return false;
        }
    }

    //Se listan los colchones del usuario que se seleccionó en el login()
    public boolean listarColchones() {
        System.out.println("---------------------------------------------------------");
        if (!this.getColchones().isEmpty()) {
            int j = 1;
            for (Colchon i : this.getColchones()) {
                System.out.println(j + ". " + i.getNombre() + "\t\tDisponible: " + i.getSaldo() + "\t\tFecha de retiro: " + i.getFechaRetiro() + "		Divisa: " + i.getDivisa());
                j++;
                System.out.println("--------------------------------------------------------------------------");
            }
            return true;
        } else {
            System.out.println("EL USUARIO NO POSEE COLCHONES...\n");
            System.out.println("--------------------------------------------------------------------------");
            return false;
        }
    }

    public boolean listarMetas() {
        System.out.println("---------------------------------------------------------");
        if (!this.getMetas().isEmpty()) {
            int j = 1;
            for (Meta i : this.getMetas()) {
                System.out.println(j + ". " + i.getNombre() + "\t\tcumplido: "+(i.isCumplida()?"Si":"No")+"\t\tDisponible: " + i.getSaldo() + "\t\tDivisa: " + i.getDivisa() + " cantidad objetivo: " + i.getObjetivo());
                j++;
                System.out.println("--------------------------------------------------------------------------");
            }
            return true;
        } else {
            System.out.println("EL USUARIO NO POSEE METAS...\n");
            System.out.println("--------------------------------------------------------------------------");
            return false;
        }
    }

}
