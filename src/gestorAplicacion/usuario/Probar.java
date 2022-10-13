package gestorAplicacion.usuario;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import baseDatos.Conexion;

public class Probar {

	/*
	 * private static String bytesToHex(byte[] hash) { StringBuilder hexString = new
	 * StringBuilder(2 * hash.length); for (int i = 0; i < hash.length; i++) {
	 * String hex = Integer.toHexString(0xff & hash[i]); if(hex.length() == 1) {
	 * hexString.append('0'); } hexString.append(hex); } return
	 * hexString.toString(); }
	 */
	public static void main(String[] args) {
		Conexion conn = new Conexion();
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
	}

}
