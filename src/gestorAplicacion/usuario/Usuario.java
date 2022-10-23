package gestorAplicacion.usuario;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import gestorAplicacion.economia.Divisa;
import gestorAplicacion.economia.Ingreso;
import gestorAplicacion.economia.Prestamo;
import gestorAplicacion.economia.PrestamoFugaz;
import gestorAplicacion.economia.PrestamoLargoPlazo;
import gestorAplicacion.economia.Salida;


public class Usuario implements Serializable{
	
	private static final long serialVersionUID = -64431385135968757L;
	
	private String cedula;
	private String nombre;
	private String email;
	private LocalDate fechaIngreso;
	private String clave;
	private List<Bolsillo> bolsillos = new ArrayList<Bolsillo>();
	private List<Colchon> colchones = new ArrayList<Colchon>();
	private List<Ingreso> ingresos = new ArrayList<Ingreso>();
	private List<Salida> salidas = new ArrayList<Salida>();
	private List<Prestamo> prestamos = new ArrayList<Prestamo>();
	private PerfilCredito perfilCredito;

	
	public Usuario(String cedula, String nombre, String email, LocalDate fechaIngreso, String clave){
		setCedula(cedula);
		setNombre(nombre);
		setEmail(email);
		setFechaIngreso(fechaIngreso);
		setClave(clave);
		bolsillos.add(new Bolsillo(this, Divisa.COP,"DEFAULT"));
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

	public void nuevoIngreso(Ingreso ingreso) {
		ingresos.add(ingreso);
		ingreso.getCuentaDestino().depositar(ingreso.getValor());
	}
	
	public void nuevoBolsillo(Bolsillo bolsillo) {
		bolsillos.add(bolsillo);
	}
	
	public void nuevoColchon(Colchon colchon) {
		colchones.add(colchon);
	}
	
	public void nuevoPrestamo(PrestamoFugaz prestamo) {
		prestamos.add(prestamo);
	}
	
	public void nuevoPrestamo(PrestamoLargoPlazo prestamo) {
		prestamos.add(prestamo);
	}

	public int solicitarCredito(float monto, int plazo, Cuenta numeroCuenta) {
		int salida=0;
		if(this.getPerfilCredito()==null) {
			PerfilCredito perfil = new PerfilCredito(this,this.getIngresos(),comportamientoPago.randomNivel());
			this.setPerfilCredito(perfil);
		}

		if(this.getPerfilCredito().getcomportamientoPago().getNivel()==3) {

			salida=10;

		}else {

			float posibleCuota = Credito.simularCredito(monto, plazo);

			if(posibleCuota>this.getPerfilCredito().getcapacidadDeuda()) {

				salida=5;

			}else {

				Credito credito = new Credito(this, monto, posibleCuota);
				numeroCuenta.setSaldo(numeroCuenta.getSaldo() + monto);
				salida=1;

			}
		}
		return salida;
	}
}
