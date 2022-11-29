from tkinter import *
from ventanaInicio import VentanaInicio
import ventanaInicio
from gestorAplicacion.usuario.Usuario import Usuario
from datetime import date, time, datetime
from baseDatos.serializador import Serializador
from baseDatos.deserializador import Deserializador


# Ventana de inicio
ventana =  VentanaInicio()

# Ejecuta la ventana
ventana.mainloop()

#Creación del usuario administrador
#usuario = Usuario("1","Administrador","administrador@unal.edu.co",date.today(),"12345")
#serializar = Serializador(usuario)
#serializar.serializar()


deserializar = Deserializador()
deserializar.deserializar()
usuario = deserializar.getArgs()



