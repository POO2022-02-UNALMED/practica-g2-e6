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

deserializar = Deserializador()
deserializar.deserializar()
usuarios = deserializar.getArgs()



#usuario1 = Usuario("12","Andres","andres@unal.edu.co",date.today(),"123")
#usuario2 = Usuario("34","Santiago","santiago@unal.edu.co",date.today(),"321")

#usuarios = [usuario1, usuario2]

#serializar = Serializador(usuarios)
#serializar.serializar()