from tkinter import *
from ventanaInicio import VentanaInicio
import ventanaInicio
from gestorAplicacion.usuario.Usuario import Usuario
from datetime import date, time, datetime
from baseDatos.serializador import Serializador
from baseDatos.deserializador import Deserializador

#Se deserializa el usuario administrador
deserializar = Deserializador()
deserializar.deserializar()
usuario = deserializar.getArgs()
print(len(usuario.getColchones()))
for i in range (0,len(usuario.getColchones())):
    print(usuario.getColchones()[i].getNombre())

# Ventana de inicio
ventana =  VentanaInicio(usuario)

# Ejecuta la ventana
ventana.mainloop()

#Creación del usuario administrador
#usuario = Usuario("1","Administrador","administrador@unal.edu.co",date.today(),"12345")
#serializar = Serializador(usuario)
#serializar.serializar()






