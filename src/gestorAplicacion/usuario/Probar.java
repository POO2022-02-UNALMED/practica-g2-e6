package gestorAplicacion.usuario;

import baseDatos.Serializador;
import gestorAplicacion.administrador.DataBank;

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
		
		DataBank databank = new DataBank();
		databank.nuevoUsuario("Juan", "juan@unal.edu.co", "hace dias", "hoy", "clave");
		databank.nuevoUsuario("Andres", "andres@unal.edu.co", "hace ddias", "hoy y ", "clave2");
		
		for(Usuario usu : databank.getUsuarios()) {
			System.out.println(usu.getNombre());
		}
		Serializador.serializar(databank);
	}

}
