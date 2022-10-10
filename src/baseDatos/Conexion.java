package baseDatos;

import java.io.*;
import gestorAplicacion.usuario.Usuario;

public class Conexion{
	
	public Conexion() {
		leerUsuario();
	}
	
	public boolean guardar(Usuario usuario){
		boolean exito;
		if(BuscarUsuario(usuario.getEmail())==false) {
			File file=new File("bin\\baseDatos\\temp\\Usuarios.txt");
			if(!file.exists()){
				try{
					file.createNewFile();
					FileWriter writer = new FileWriter(file);
					writer.write(usuario.getNombre()+"|"+usuario.getEmail()+"|"+usuario.getFechaNacimiento()+"|"+usuario.getFechaIngreso()+"|"+usuario.getClave()+"\n");
					writer.close();
					Usuario.usuarios.add(usuario);
					exito=true;
            	 	}catch(IOException a){
            	 		System.out.print(a);
            	 		exito=false;
            	 	}
             	}else{
             		try{
             			FileWriter writer = new FileWriter(file,true);
    					writer.write(usuario.getNombre()+"|"+usuario.getEmail()+"|"+usuario.getFechaNacimiento()+"|"+usuario.getFechaIngreso()+"|"+usuario.getClave()+"\n");
    					writer.close();
    					Usuario.usuarios.add(usuario);
             			exito=true;
                	 	}catch(IOException a){
                	 		System.out.print(a);
                	 		exito=false;
                	 	}             
             		}
			}else{
				exito=false;
			}
		return exito;
	}
	
	public boolean leerUsuario(){
		try {
			BufferedReader bf = new BufferedReader(new FileReader("bin\\baseDatos\\temp\\Usuarios.txt"));
			String bfRead;
			while((bfRead = bf.readLine()) != null) {
				String[] parts = bfRead.split("\\|");
				Usuario.usuarios.add(new Usuario(parts[0], parts[1], parts[2], parts[3], parts[4]));
			}
			bf.close();
			return true;
		}catch(Exception e) {
			return false;
		}
	}
		
	
	private boolean BuscarUsuario(String email){
		for(Usuario i:Usuario.usuarios){
			if(i.getEmail().equals(email)){
				return true;
				}
         }
         return false;
     }
}
