package gestorAplicacion.usuario;

import java.time.LocalDate;
import java.util.List;

import gestorAplicacion.administrador.DataBank;
import gestorAplicacion.administrador.Utils;
import gestorAplicacion.economia.Banco;
import gestorAplicacion.economia.Divisa;

public class Probar {

	/*
	 * private static String bytesToHex(byte[] hash) { StringBuilder hexString = new
	 * StringBuilder(2 * hash.length); for (int i = 0; i < hash.length; i++) {
	 * String hex = Integer.toHexString(0xff & hash[i]); if(hex.length() == 1) {
	 * hexString.append('0'); } hexString.append(hex); } return
	 * hexString.toString(); }
	 */
	public static void main(String[] args) {
		/*
		 * MessageDigest digest = null; try { digest =
		 * MessageDigest.getInstance("SHA3-256"); }catch (Exception e) {
		 *
		 * } final byte[] hashbytes = digest.digest(
		 * "hola1".getBytes(StandardCharsets.UTF_8)); String sha3Hex =
		 * bytesToHex(hashbytes); System.out.println(sha3Hex);
		 */
		// System.out.println(conn.guardar(new Usuario("Manuela",
		// "123manu2@unal.edu.co", "enero", "hoy", "vinilostopia")));
		/*List<Usuario> aux = DataBank.getUsuarios();
		for(Usuario usu : aux) {
			System.out.println(usu.getNombre());
		}
		double[] aux2 = Divisa.USD.ConvertToDivisa(2,Divisa.COP);
		System.out.println(aux2[0]+ ", "+ aux2[1]);*/
		
		
		
		
		
		/*LocalDate fecha = LocalDate.now().minusYears(1).minusDays(15);
		long a = Utils.diasEntreFechas(fecha,LocalDate.now());
		System.out.print(a);*/
	}

}
