from tkinter import *
import pathlib
import os
import tkinter
from ventanas.fieldFrame import FieldFrame

from excepciones.errorAplicacion import ErrorAplicacion
from excepciones.excepcionExistente import ExcepcionExistente
from excepciones.excepcionLista import ExcepcionLista
from excepciones.excepcionLongitud import ExcepcionLongitud
from excepciones.excepcionNumerica import ExcepcionNumerica
from excepciones.excepcionVacio import ExcepcionVacio

from ventanas.popUp import PopUp


class VentanaUsuario(Tk):

    framesEnPantalla=[]

    def __init__(self):
        super().__init__()

        # Parámetros de la ventana de usuario

        self.title('Sistema Gestor de Dinero')
        self.option_add("*tearOff",  False)
        self.geometry("1400x720")
        self.resizable(False,False)

        # Barra de menú
        self._barraMenu = Menu(self)
        archivo = Menu(self._barraMenu)
        archivo.add_command(label="Aplicacion", command=lambda: infoApp())
        archivo.add_command(label="Salir y guardar", command=lambda: cerrarGuardar())
        self._barraMenu.add_cascade(label="Archivo", menu=archivo)
        self.config(menu= self._barraMenu)

        procesosYConsultas = Menu(self._barraMenu)

        verSaldosDispo = Menu(self._barraMenu)
        verSaldosDispo.add_command(label="Ver bolsillos", command=lambda: cambiarVista(frameVerBolsillos))
        verSaldosDispo.add_command(label="Ver colchones", command=lambda: cambiarVista(frameVerColchones))
        verSaldosDispo.add_command(label="Ver metas", command=lambda: cambiarVista(frameVerMetas))
        verSaldosDispo.add_command(label="Ver dinero total", command=lambda: cambiarVista(frameVerDineroTotal))
        procesosYConsultas.add_cascade(label="Ver saldos disponibles en la cuenta", menu=verSaldosDispo)

        ingresarDinero = Menu(self._barraMenu)
        ingresarDinero.add_command(label="Bolsillos", command=lambda: cambiarVista(frameIngresarBolsillos))
        ingresarDinero.add_command(label="Colchones", command=lambda: cambiarVista(frameIngresarColchones))
        procesosYConsultas.add_cascade(label="Ingresar dinero a su cuenta", menu=ingresarDinero)

        moverDinero = Menu(self._barraMenu)
        moverDinero.add_command(label="Bolsillos", command=lambda: cambiarVista(frameMoverBolsillos))
        moverDinero.add_command(label="Colchones", command=lambda: cambiarVista(frameMoverColchones))
        procesosYConsultas.add_cascade(label="Mover dinero en su cuenta", menu=moverDinero)

        enviarYSacarDinero = Menu(self._barraMenu)
        enviarYSacarDinero.add_command(label="Retiro", command=lambda: cambiarVista(frameRetiro))
        enviarYSacarDinero.add_command(label="Envio", command=lambda: cambiarVista(frameEnvio))
        procesosYConsultas.add_cascade(label="Enviar y sacar dinero de su cuenta", menu=enviarYSacarDinero)

        procesosYConsultas.add_separator()

        procesosYConsultas.add_command(label="Agregar bolsillo a su cuenta", command=lambda: cambiarVista(frameAgregarBolsillo))
        
        procesosYConsultas.add_command(label="Agregar colchon a su cuenta", command=lambda: cambiarVista(frameAgregarColchon))
        
        procesosYConsultas.add_command(label="Agregar meta a su cuenta", command=lambda: cambiarVista(frameAgregarMeta))

        procesosYConsultas.add_separator()

        modificarColBolMet = Menu(self._barraMenu)
        modificarColBolMet.add_command(label="Bolsillo", command=lambda: cambiarVista(frameModificarBolsillo))
        modificarColBolMet.add_command(label="Colchon", command=lambda: cambiarVista(frameModificarColchon))
        modificarColBolMet.add_command(label="Meta", command=lambda: cambiarVista(frameModificarMeta))
        procesosYConsultas.add_cascade(label="Modificar Colchon/Bolsillo/Meta", menu=modificarColBolMet)

        solicitarPrestamo = Menu(self._barraMenu)
        solicitarPrestamo.add_command(label="Prestamo Fugaz", command=lambda: cambiarVista(frameSolicitarFugaz))
        solicitarPrestamo.add_command(label="Prestamo a largo plazo", command=lambda: cambiarVista(frameSolicitarLargo))
        procesosYConsultas.add_cascade(label="SolicitarPrestamo", menu=solicitarPrestamo)

        abonarPrestamoMeta = Menu(self._barraMenu)
        abonarPrestamoMeta.add_command(label="Prestamos", command=lambda: cambiarVista(frameAbonarPrestamo))
        abonarPrestamoMeta.add_command(label="Metas", command=lambda: cambiarVista(frameAbonarMetas))
        procesosYConsultas.add_cascade(label="Abonar a un prestamo o meta", menu=abonarPrestamoMeta)

        self._barraMenu.add_cascade(label="Procesos y consultas", menu= procesosYConsultas)

        ayuda = Menu(self._barraMenu)
        ayuda.add_command(label="Acerca de", command = lambda: infoDevs())
        self._barraMenu.add_cascade(label="Ayuda", menu = ayuda)

        self.config(menu = self._barraMenu)

        # Funciones utiles en la manipulacion de Frames
        
        # Cambiar vista del frame
        def cambiarVista(frameUtilizado):
            for frame in VentanaUsuario.framesEnPantalla:
                frame.pack_forget()
            frameUtilizado.pack(fill=BOTH,expand=True, pady = (10,10))

        # Mostrar un output
        def mostrarOutput(string, text):
            text.delete("1.0", "end")
            text.insert(INSERT, string)
            text.pack(fill=X, expand=True, padx=(10,10))

        # Verificar input vacio

        def verificarVacio(fieldFrame):
            for criterio in fieldFrame._criterios:
                if fieldFrame.getValue(criterio) == "":
                    raise ExcepcionVacio(criterio)

        # Verificar longitud de input

        def verificarLongitud(texto, requerido, nombreCampo):
            if len(texto) < requerido:
                raise ExcepcionLongitud([nombreCampo, requerido])

        # Verificar input numerico
        def verificarNumero(valor):
            if not valor.isnumeric():
                raise ExcepcionNumerica(valor)

        # Ayuda -> Acerca de
        def infoDevs():
            ventanaDevs = Tk()
            ventanaDevs.geometry("640x360")
            ventanaDevs.resizable(False,False)
            ventanaDevs.title("Sistemas Gestor de Dinero - Acerca de")

            textoInfo = f"Desarrolladores:\n" \
                        f"• Nombre Integrantes\n" \

            devs = Label(ventanaDevs, text = textoInfo, justify = "left", font=("Verdana", 12))
            devs.pack(fill=tkinter.Y, expand=True)

        #Pantalla de inicio

        framePantallaInicio = Frame(self)
        nombrePantallaInicio = Label(framePantallaInicio, text="Bienvenide", font=("Verdana", 16), fg="#245efd")
        outputPantallaInicio = Text(framePantallaInicio, height=100, font=("Verdana",10))

        nombrePantallaInicio.pack
        outputPantallaInicio.pack(fill=X, expand=True, padx=(10,10))

        VentanaUsuario.framesEnPantalla.append(framePantallaInicio)
        cambiarVista(framePantallaInicio)


        def botonVerBolsillos():

            try:
                verificarVacio(FFVerBolsillos)
                nombre = FFVerBolsillos.getValue("Nombre del bolsillo")
                disponible = FFVerBolsillos.getValue("Disponible")
                divisa = FFVerBolsillos.getValue("Divisa")
   
                verificarNumero(disponible)
                verificarLongitud(nombre, 3, "Nombre del bolsillo")
                verificarLongitud(divisa, 5, "Divisa")

            except ErrorAplicacion as e:
                PopUp(str(e))

        #Hacer get para obtener el nombre del usuario como sus bolsillo, en casilla que no sean aditebles
        frameVerBolsillos = Frame(self)
        nombreVerBolsillos = Label(frameVerBolsillos, text="Saldo en Bolsillos", font=("Verdana", 16), fg = "#245efd")
        descVerBolsillos = Label(frameVerBolsillos, text="Su saldo disponible en su bolsillo es de:", font=("Verdana", 12))
        FFVerBolsillos = FieldFrame(frameVerBolsillos, None, ["Nombre del bolsillo", "Disponible", "Divisa"], None, None, None)
        FFVerBolsillos.crearBotones(botonVerBolsillos)

        outputVerBolsillos = Text(frameVerBolsillos, height=100, font=("Verdana", 10))
        VentanaUsuario.framesEnPantalla.append(outputVerBolsillos)

        nombreVerBolsillos.pack()
        descVerBolsillos.pack()
        FFVerBolsillos.pack()
