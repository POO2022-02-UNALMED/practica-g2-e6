package baseDatos;

import java.io.*;
import gestorAplicacion.usuario.Usuario;

public class Conexion {
	
	public boolean guardar(Usuario usuario) {
		boolean exito;
		if(BuscarUsuario(usuario.getEmail())==false) {
			File file=new File("\\temp\\Usuarios.txt");
			if(!file.exists()){
				try{
					file.createNewFile();
					ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(file));
					obj.writeObject(usuario);
					exito=true;
            	 	}catch(IOException a){
            	 		exito=false;
            	 	}
             	}else{
             		try{
             			ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(file));
             			obj.writeObject(usuario);
             			exito=true;
                	 	}catch(IOException a){
                	 		exito=false;
                	 	}             
             		}
			}else{
				exito=false;
			}
		return exito;
	}
		
	
	public boolean BuscarUsuario(String email){
		for(Usuario i:Usuario.getUsuarios() ){
             if(i.getEmail().equals(email)){
                 return true;
             }
         }
         return false;
     }
}
