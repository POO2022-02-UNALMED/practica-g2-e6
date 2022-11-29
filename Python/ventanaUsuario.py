from tkinter import *
import pathlib
import os
import tkinter
from ventanas.fieldFrame import FieldFrame
from tkinter import ttk
from gestorAplicacion.usuario.Usuario import Bolsillo
from excepciones.errorAplicacion import ErrorAplicacion
from excepciones.excepcionExistente import ExcepcionExistente
from excepciones.excepcionLongitud import ExcepcionLongitud
from excepciones.excepcionNumerica import ExcepcionNumerica
from excepciones.excepcionVacio import ExcepcionVacio
from baseDatos.serializador import Serializador
from ventanas.popUp import PopUp

from gestorAplicacion.usuario.Bolsillo import Bolsillo
from gestorAplicacion.usuario.Colchon import Colchon

class VentanaUsuario(Tk):

    framesEnPantalla=[]

    def __init__(self, usuario):
        super().__init__()
        self._usuario = usuario
        # Parámetros de la ventana de usuario

        self.title('Sistema Gestor de Dinero')
        self.option_add("*tearOff",  False)
        self.geometry("1366x768")
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
                        f"• Juan Daniel Villa Mejia\n" \
                        f"• Juan Camilo Montoya Mejia\n" \
                        f"• Carlos Sebastián Zamora Rosero\n" \
                        f"• Juan David Cortes Amador\n" \
                        f"• Andrés Felipe Parra Naranjo\n" \

            devs = Label(ventanaDevs, text = textoInfo, justify = "left", font=("Verdana", 12))
            devs.pack(fill=tkinter.Y, expand=True)

        #serializar el usuario
        def cerrarGuardar():
            serializar = Serializador(self._usuario)
            serializar.serializar()
            self.destroy()

        #Pantalla de inicio

        framePantallaInicio = Frame(self)
        nombrePantallaInicio = Label(framePantallaInicio, text="Bienvenide", font=("Verdana", 16), fg="#245efd")
        outputPantallaInicio = Text(framePantallaInicio, height=100, font=("Verdana",10))

        nombrePantallaInicio.pack
        outputPantallaInicio.pack(fill=X, expand=True, padx=(10,10))

        VentanaUsuario.framesEnPantalla.append(framePantallaInicio)
        cambiarVista(framePantallaInicio)

        #Boton para ver el saldo disponible en Bolsillos
        def botonVerBolsillos():

            try:
                verificarVacio(FFVerBolsillos)
                nombre = FFVerBolsillos.getValue("Nombre del bolsillo")
                disponible = FFVerBolsillos.getValue("Disponible")
                divisa = FFVerBolsillos.getValue("Divisa")
   
                verificarNumero(disponible)
                verificarLongitud(nombre, 3, "Nombre del bolsillo")
                verificarLongitud(divisa, 3, "Divisa")

            except ErrorAplicacion as e:
                PopUp(str(e))

        #Hacer get para obtener el nombre del usuario como sus bolsillo, casillas que no sean editables, solo para observar
        frameVerBolsillos = Frame(self)
        nombreVerBolsillos = Label(frameVerBolsillos, text="Saldo en Bolsillos", font=("Arial Rounded MT Bold", 18), fg = "#245efd")
        descVerBolsillos = Label(frameVerBolsillos, text="Su saldo disponible en su bolsillo es de:", font=("Arial Rounded MT Bold", 14))
        FFVerBolsillos = FieldFrame(frameVerBolsillos, None, ["Nombre del bolsillo", "Disponible", "Divisa"], None, None, None)
        FFVerBolsillos.crearBotones(botonVerBolsillos)

        outputVerBolsillos = Text(frameVerBolsillos, height=100, font=("Arial Rounded MT Bold", 10))
        VentanaUsuario.framesEnPantalla.append(outputVerBolsillos)

        nombreVerBolsillos.pack()
        descVerBolsillos.pack()
        FFVerBolsillos.pack()

        VentanaUsuario.framesEnPantalla.append(frameVerBolsillos)

        #Boton para ver el saldo disponible en Colchones
        def botonVerColchones():

            try:
                verificarVacio(FFVerColchones)
                nombre = FFVerColchones.getValue("Nombre del colchon")
                disponible = FFVerColchones.getValue("Disponible")
                #fechaRetiro = FFVerColchones.getValue("Fecha de retiro")
                divisa = FFVerColchones.getValue("Divisa")
   
                verificarNumero(disponible)
                verificarLongitud(nombre, 3, "Nombre del bolsillo")
                verificarLongitud(divisa, 3, "Divisa")

            except ErrorAplicacion as e:
                PopUp(str(e))

        #Hacer get para obtener el nombre del usuario como sus colchones, casillas que no sean editables, solo para observar
        frameVerColchones = Frame(self)
        nombreVerColchones = Label(frameVerColchones, text="Saldo en Colchones", font=("Arial Rounded MT Bold", 18), fg = "#245efd")
        descVerColchones = Label(frameVerColchones, text="Su saldo disponible en su colchon es de:", font=("Arial Rounded MT Bold", 14))
        FFVerColchones = FieldFrame(frameVerColchones, None, ["Nombre del colchon", "Disponible","Fecha de retiro", "Divisa"], None, None, None)
        FFVerColchones.crearBotones(botonVerColchones)

        outputVerColchones = Text(frameVerColchones, height=100, font=("Arial Rounded MT Bold", 10))
        VentanaUsuario.framesEnPantalla.append(outputVerColchones)

        nombreVerColchones.pack()
        descVerColchones.pack()
        FFVerColchones.pack()

        VentanaUsuario.framesEnPantalla.append(frameVerColchones)

        #Boton para ver el saldo disponible en Metas
        def botonVerMetas():

            try:
                verificarVacio(FFVerMetas)
                nombre = FFVerMetas.getValue("Nombre de la meta")
                disponible = FFVerMetas.getValue("Disponible")
                divisa = FFVerMetas.getValue("Divisa")
   
                verificarNumero(disponible)
                verificarLongitud(nombre, 3, "Nombre de la Meta")
                verificarLongitud(divisa, 3, "Divisa")

            except ErrorAplicacion as e:
                PopUp(str(e))

        frameVerMetas = Frame(self)
        nombreVerMetas = Label(frameVerMetas, text="Saldo en Metas", font=("Arial Rounded MT Bold", 18), fg = "#245efd")
        descVerMetas = Label(frameVerMetas, text="Su saldo disponible en su meta es de:", font=("Arial Rounded MT Bold", 14))
        FFVerMetas = FieldFrame(frameVerMetas, None, ["Nombre de la meta", "Cumplido","Disponible", "Divisa", "Cantidad objetivo"], None, None, None)
        FFVerMetas.crearBotones(botonVerMetas)

        outputVerMetas = Text(frameVerMetas, height=100, font=("Arial Rounded MT Bold", 10))
        VentanaUsuario.framesEnPantalla.append(outputVerMetas)

        nombreVerMetas.pack()
        descVerMetas.pack()
        FFVerMetas.pack()

        VentanaUsuario.framesEnPantalla.append(frameVerMetas)

        #Boton para ver el saldo disponible en el Dinero Total
        def botonVerDineroTotal():

            try:
                verificarVacio(FFVerDineroTotal)
                disponible = FFVerDineroTotal.getValue("Disponible")
                divisa = FFVerDineroTotal.getValue("Divisa")
   
                verificarNumero(disponible)
                verificarLongitud(divisa, 3, "Divisa")

            except ErrorAplicacion as e:
                PopUp(str(e))

        frameVerDineroTotal = Frame(self)
        nombreVerDineroTotal = Label(frameVerDineroTotal, text="Saldo Dinero Total", font=("Arial Rounded MT Bold", 18), fg = "#245efd")
        descVerDineroTotal = Label(frameVerDineroTotal, text="Su dinero total es de:", font=("Arial Rounded MT Bold", 14))
        FFVerDineroTotal = FieldFrame(frameVerDineroTotal, None, ["Divisa", "Disponible"], None, None)
        FFVerDineroTotal.crearBotones(botonVerDineroTotal)

        outputVerDineroTotal = Text(frameVerDineroTotal, height=100, font=("Arial Rounded MT Bold", 10))
        VentanaUsuario.framesEnPantalla.append(outputVerDineroTotal)

        nombreVerDineroTotal.pack()
        descVerDineroTotal.pack()
        FFVerDineroTotal.pack()

        VentanaUsuario.framesEnPantalla.append(frameVerDineroTotal)

        #Boton para Ingresar dinero a bolsillos
        def botonIngresarBolsillos():

            try:
                verificarVacio(FFIngresarBolsillos)
                escogerBolsillo = FFIngresarBolsillos.getValue("Escoger Bolsillo")
                escogerBanco = FFIngresarBolsillos.getValue("Escoger banco")
                cantidad = FFIngresarBolsillos.getValue("Ingresar cantidad")
   
                verificarLongitud(escogerBanco, 3, "Escoger banco")
                verificarLongitud(escogerBolsillo, 3, "Escoger Bolsillo")
                verificarNumero(cantidad)

            except ErrorAplicacion as e:
                PopUp(str(e))

        frameIngresarBolsillos = Frame(self)
        nombreIngresarBolsillos = Label(frameIngresarBolsillos, text="Ingresar dinero a un bolsillo", font=("Arial Rounded MT Bold", 18), fg = "#245efd")
        descIngresarBolsillos = Label(frameIngresarBolsillos, text="Seleccionar el bolsillo al que desea ingresar el dinero", font=("Arial Rounded MT Bold", 14))
        FFIngresarBolsillos = FieldFrame(frameIngresarBolsillos, None, ["Escoger Bolsillo", "Escoger banco", "Ingresar cantidad"], None, None)
        FFIngresarBolsillos.crearBotones(botonIngresarBolsillos)

        outputIngresarBolsillos = Text(frameIngresarBolsillos, height=100, font=("Arial Rounded MT Bold", 10))
        VentanaUsuario.framesEnPantalla.append(outputIngresarBolsillos)

        nombreIngresarBolsillos.pack()
        descIngresarBolsillos.pack()
        FFIngresarBolsillos.pack()

        VentanaUsuario.framesEnPantalla.append(frameIngresarBolsillos)

        #Boton para Ingresar dinero a sus Colchones
        def botonIngresarColchones():

            try:
                verificarVacio(FFIngresarColchones)
                escogerColchon = FFIngresarColchones.getValue("Escoger colchon")
                escogerBanco = FFIngresarColchones.getValue("Escoger banco")
                cantidad = FFIngresarColchones.getValue("Ingresar cantidad")
   
                verificarLongitud(escogerBanco, 3, "Escoger banco")
                verificarLongitud(escogerColchon, 3, "Escoger Bolsillo")
                verificarNumero(cantidad)

            except ErrorAplicacion as e:
                PopUp(str(e))

        frameIngresarColchones = Frame(self)
        nombreIngresarColchones = Label(frameIngresarColchones, text="Ingresar dinero a un colchon", font=("Arial Rounded MT Bold", 18), fg = "#245efd")
        descIngresarColchones = Label(frameIngresarColchones, text="Seleccionar el colchon al que desea ingresar el dinero", font=("Arial Rounded MT Bold", 14))
        FFIngresarColchones = FieldFrame(frameIngresarColchones, None, ["Escoger colchon", "Escoger banco", "Ingresar cantidad"], None, None)
        FFIngresarColchones.crearBotones(botonIngresarColchones)

        outputIngresarColchones = Text(frameIngresarColchones, height=100, font=("Arial Rounded MT Bold", 10))
        VentanaUsuario.framesEnPantalla.append(outputIngresarColchones)

        nombreIngresarColchones.pack()
        descIngresarColchones.pack()
        FFIngresarColchones.pack()

        VentanaUsuario.framesEnPantalla.append(frameIngresarColchones)

        #Boton para Mover dinero a Bolsillos
        def botonMoverBolsillos():

            try:
                verificarVacio(FFMoverBolsillos)
                cantidad = FFMoverBolsillos.getValue("Cantidad a transferir")
   
                verificarNumero(cantidad)

            except ErrorAplicacion as e:
                PopUp(str(e))

        frameMoverBolsillos = Frame(self)
        nombreMoverBolsillos = Label(frameMoverBolsillos, text="Ingresar dinero a un colchon", font=("Arial Rounded MT Bold", 18), fg = "#245efd")
        descMoverBolsillos = Label(frameMoverBolsillos, text="Seleccionar el colchon al que desea ingresar el dinero", font=("Arial Rounded MT Bold", 14))
        FFMoverBolsillos = FieldFrame(frameMoverBolsillos, None, ["Elegir a que bolsillo desea mover su dinero", "De donde sale el dinero", "Escoger uno de los disponibles", "Cantidad a transferir"], None, None)
        FFMoverBolsillos.crearBotones(botonMoverBolsillos)

        outputMoverBolsillos = Text(frameMoverBolsillos, height=100, font=("Arial Rounded MT Bold", 10))
        VentanaUsuario.framesEnPantalla.append(outputMoverBolsillos)

        nombreMoverBolsillos.pack()
        descMoverBolsillos.pack()
        FFMoverBolsillos.pack()

        VentanaUsuario.framesEnPantalla.append(frameMoverBolsillos)

        #Boton para Mover dinero a Colchones
        def botonMoverColchones():

            try:
                verificarVacio(FFMoverColchones)
                cantidad = FFMoverColchones.getValue("Cantidad a transferir")
   
                verificarNumero(cantidad)

            except ErrorAplicacion as e:
                PopUp(str(e))

        frameMoverColchones = Frame(self)
        nombreMoverColchones = Label(frameMoverColchones, text="Ingresar dinero a un colchon", font=("Arial Rounded MT Bold", 18), fg = "#245efd")
        descMoverColchones = Label(frameMoverColchones, text="Seleccionar el colchon al que desea ingresar el dinero", font=("Arial Rounded MT Bold", 14))
        FFMoverColchones = FieldFrame(frameMoverColchones, None, ["Elegir a que colchon desea mover su dinero", "De donde sale el dinero", "Escoger uno de los disponibles", "Cantidad a transferir"], None, None)
        FFMoverColchones.crearBotones(botonMoverColchones)

        outputMoverColchones = Text(frameMoverColchones, height=100, font=("Arial Rounded MT Bold", 10))
        VentanaUsuario.framesEnPantalla.append(outputMoverColchones)

        nombreMoverColchones.pack()
        descMoverColchones.pack()
        FFMoverColchones.pack()

        VentanaUsuario.framesEnPantalla.append(frameMoverColchones)

        #Boton para Mover dinero a Colchones
        def botonRetiro():

            try:
                verificarVacio(FFRetiro)
                cantidad = FFRetiro.getValue("Cantidad a transferir")
   
                verificarNumero(cantidad)

            except ErrorAplicacion as e:
                PopUp(str(e))

        frameRetiro = Frame(self)
        nombreRetiro = Label(frameRetiro, text="Retirar dinero", font=("Arial Rounded MT Bold", 18), fg = "#245efd")
        descRetiro = Label(frameRetiro, text="Ingrese los siguientes datos para proceder al retiro de su dinero: ", font=("Arial Rounded MT Bold", 14))
        FFRetiro = FieldFrame(frameRetiro, None, ["De donde sale el dinero", "Escoger uno de los disponibles", "Cantidad a transferir", "Seleccionar banco"], None, None)
        FFRetiro.crearBotones(botonRetiro)

        outputRetiro = Text(frameRetiro, height=100, font=("Arial Rounded MT Bold", 10))
        VentanaUsuario.framesEnPantalla.append(outputRetiro)

        nombreRetiro.pack()
        descRetiro.pack()
        FFRetiro.pack()

        VentanaUsuario.framesEnPantalla.append(frameRetiro)

        #Boton para Mover dinero a Colchones
        def botonEnvio():

            try:
                verificarVacio(FFEnvio)
                cantidad = FFEnvio.getValue("Cantidad a transferir")
   
                verificarNumero(cantidad)

            except ErrorAplicacion as e:
                PopUp(str(e))

        frameEnvio = Frame(self)
        nombreEnvio = Label(frameEnvio, text="Enviar dinero", font=("Arial Rounded MT Bold", 18), fg = "#245efd")
        descEnvio = Label(frameEnvio, text="Para enviar dinero desde su cuenta ingrese los siguientes datos: ", font=("Arial Rounded MT Bold", 14))
        FFEnvio = FieldFrame(frameEnvio, None, ["Seleccionar a quien le desea enviar su dinero","De donde sale el dinero", "Escoger uno de los disponibles", "Cantidad a transferir"], None, None)
        FFEnvio.crearBotones(botonEnvio)

        outputEnvio = Text(frameEnvio, height=100, font=("Arial Rounded MT Bold", 10))
        VentanaUsuario.framesEnPantalla.append(outputEnvio)

        nombreEnvio.pack()
        descEnvio.pack()
        FFEnvio.pack()

        VentanaUsuario.framesEnPantalla.append(frameEnvio)

        #Boton Agregar Bolsillo
        def botonAgregarBolsillo():

            try:

                divisa = FFAgregarBolsillo.getValue("Elegir Divisa")
                nombreBolsillo = FFAgregarBolsillo.getValue("Nombre del nuevo Bolsillo")
            
                bolsillo= Bolsillo(self._usuario, divisa, nombreBolsillo)

                self._usuario.nuevoBolsillo(bolsillo)

                
                resultadoAgregarBolsillo = "Bolsillo agregado con exito"
                mostrarOutput(resultadoAgregarBolsillo, outputAgregarBolsillo)
                

            except ErrorAplicacion as e:
                PopUp(str(e))

        frameAgregarBolsillo = Frame(self)
        nombreAgregarBolsillo = Label(frameAgregarBolsillo, text="Agregar Bolsillo", font=("Arial Rounded MT Bold", 18), fg = "#245efd")
        descAgregarBolsillo = Label(frameAgregarBolsillo, text="Rellene los siguientes datos para agregar un bolsillo a su cuenta: ", font=("Arial Rounded MT Bold", 14))
        FFAgregarBolsillo = FieldFrame(frameAgregarBolsillo, None, ["Elegir Divisa","Nombre del nuevo Bolsillo"], None, None)

        FFAgregarBolsillo.crearBotones(botonAgregarBolsillo)

        outputAgregarBolsillo = Text(frameAgregarBolsillo, height=100, font=("Arial Rounded MT Bold", 10))
        VentanaUsuario.framesEnPantalla.append(outputAgregarBolsillo)

        nombreAgregarBolsillo.pack()
        descAgregarBolsillo.pack()
        FFAgregarBolsillo.pack()

        VentanaUsuario.framesEnPantalla.append(frameAgregarBolsillo)

        #Boton Agregar Colchon
        def botonAgregarColchon():

            try:
                divisa = FFAgregarColchon.getValue("Elegir Divisa")
                nombreBolsillo = FFAgregarColchon.getValue("Nombre del nuevo Colchon")
                fechaLiberar = FFAgregarColchon.getValue("Fecha de liberacion del colchon")

                colchon = Colchon(self._usuario, divisa, nombreBolsillo, fechaLiberar)

                self._usuario.nuevoColchon(colchon)

                
                resultadoAgregarColchon = "Colchon agregado con exito"
                mostrarOutput(resultadoAgregarColchon, outputAgregarColchon)

                



            except ErrorAplicacion as e:
                PopUp(str(e))

        frameAgregarColchon = Frame(self)
        nombreAgregarColchon = Label(frameAgregarColchon, text="Agregar Colchon", font=("Arial Rounded MT Bold", 18), fg = "#245efd")
        descAgregarColchon = Label(frameAgregarColchon, text="Rellene los siguientes datos para agregar un colchon a su cuenta: ", font=("Arial Rounded MT Bold", 14))
        FFAgregarColchon = FieldFrame(frameAgregarColchon, None, ["Elegir Divisa","Nombre del nuevo Colchon", "Fecha de liberacion del colchon"], None, None)
        FFAgregarColchon.crearBotones(botonAgregarColchon)

        outputAgregarColchon = Text(frameAgregarColchon, height=100, font=("Arial Rounded MT Bold", 10))
        VentanaUsuario.framesEnPantalla.append(outputAgregarColchon)

        nombreAgregarColchon.pack()
        descAgregarColchon.pack()
        FFAgregarColchon.pack()

        VentanaUsuario.framesEnPantalla.append(frameAgregarColchon)

        #Boton Agregar Meta
        def botonAgregarMeta():

            try:
                verificarVacio(FFAgregarMeta)

            except ErrorAplicacion as e:
                PopUp(str(e))

        frameAgregarMeta = Frame(self)
        nombreAgregarMeta = Label(frameAgregarMeta, text="Agregar Meta", font=("Arial Rounded MT Bold", 18), fg = "#245efd")
        descAgregarMeta = Label(frameAgregarMeta, text="Rellene los siguientes datos para agregar una nueva meta a su cuenta: ", font=("Arial Rounded MT Bold", 14))
        FFAgregarMeta = FieldFrame(frameAgregarMeta, None, ["Elegir Divisa","Nombre de la nueva Meta", "Valor Objetivo de la meta"], None, None)
        FFAgregarMeta.crearBotones(botonAgregarMeta)

        outputAgregarMeta = Text(frameAgregarMeta, height=100, font=("Arial Rounded MT Bold", 10))
        VentanaUsuario.framesEnPantalla.append(outputAgregarMeta)

        nombreAgregarMeta.pack()
        descAgregarMeta.pack()
        FFAgregarMeta.pack()

        VentanaUsuario.framesEnPantalla.append(frameAgregarMeta)

        #Boton para Modificar Bolsillo
        def botonModificarBolsillo():

            try:
                verificarVacio(FFModificarBolsillo)

            except ErrorAplicacion as e:
                PopUp(str(e))

        frameModificarBolsillo = Frame(self)
        nombreModificarBolsillo = Label(frameModificarBolsillo, text="Modificar Bolsillo", font=("Arial Rounded MT Bold", 18), fg = "#245efd")
        descModificarBolsillo = Label(frameModificarBolsillo, text="Rellene los siguientes datos para modificar un bolsillo en su cuenta: ", font=("Arial Rounded MT Bold", 14))
        FFModificarBolsillo = FieldFrame(frameModificarBolsillo, None, ["Seleccionar Bolsillo a modificar","¿Qué desea modificar?", "Nueva edición"], None, None)
        FFModificarBolsillo.crearBotones(botonModificarBolsillo)

        outputModificarBolsillo = Text(frameModificarBolsillo, height=100, font=("Arial Rounded MT Bold", 10))
        VentanaUsuario.framesEnPantalla.append(outputModificarBolsillo)

        nombreModificarBolsillo.pack()
        descModificarBolsillo.pack()
        FFModificarBolsillo.pack()

        VentanaUsuario.framesEnPantalla.append(frameModificarBolsillo)

        #Boton para Modificar Colchon
        def botonModificarColchon():

            try:
                verificarVacio(FFModificarColchon)

            except ErrorAplicacion as e:
                PopUp(str(e))

        frameModificarColchon = Frame(self)
        nombreModificarColchon = Label(frameModificarColchon, text="Modificar Colchon", font=("Arial Rounded MT Bold", 18), fg = "#245efd")
        descModificarColchon = Label(frameModificarColchon, text="Rellene los siguientes datos para modificar un colchon en su cuenta: ", font=("Arial Rounded MT Bold", 14))
        FFModificarColchon = FieldFrame(frameModificarColchon, None, ["Seleccionar Colchon a modificar","¿Qué desea modificar?", "Nueva edición"], None, None) #Condicional para las opciones de eleccion
        FFModificarColchon.crearBotones(botonModificarColchon)

        outputModificarColchon = Text(frameModificarColchon, height=100, font=("Arial Rounded MT Bold", 10))
        VentanaUsuario.framesEnPantalla.append(outputModificarColchon)

        nombreModificarColchon.pack()
        descModificarColchon.pack()
        FFModificarColchon.pack()

        VentanaUsuario.framesEnPantalla.append(frameModificarColchon)

        #Boton para Modificar Meta
        def botonModificarMeta():

            try:
                verificarVacio(FFModificarMeta)

            except ErrorAplicacion as e:
                PopUp(str(e))

        frameModificarMeta = Frame(self)
        nombreModificarMeta = Label(frameModificarMeta, text="Modificar Meta", font=("Arial Rounded MT Bold", 18), fg = "#245efd")
        descModificarMeta = Label(frameModificarMeta, text="Rellene los siguientes datos para modificar un colchon en su cuenta: ", font=("Arial Rounded MT Bold", 14))
        FFModificarMeta = FieldFrame(frameModificarMeta, None, ["Seleccionar Meta a modificar","¿Qué desea modificar?", "Nueva edición"], None, None) 
        FFModificarMeta.crearBotones(botonModificarMeta)

        outputModificarMeta = Text(frameModificarMeta, height=100, font=("Arial Rounded MT Bold", 10))
        VentanaUsuario.framesEnPantalla.append(outputModificarMeta)

        nombreModificarMeta.pack()
        descModificarMeta.pack()
        FFModificarMeta.pack()

        VentanaUsuario.framesEnPantalla.append(frameModificarMeta)

        #Boton solicitar un Prestamo Fugaz
        def botonSolicitarFugaz():

            try:
                verificarVacio(FFSolicitarFugaz)

            except ErrorAplicacion as e:
                PopUp(str(e))

        frameSolicitarFugaz = Frame(self)
        nombreSolicitarFugaz = Label(frameSolicitarFugaz, text="Solicitar un Prestamo Fugaz", font=("Arial Rounded MT Bold", 18), fg = "#245efd")
        descSolicitarFugaz = Label(frameSolicitarFugaz, text="Rellene los siguientes datos para solicitar un prestamo fugaz: ", font=("Arial Rounded MT Bold", 14))
        FFSolicitarFugaz = FieldFrame(frameSolicitarFugaz, None, ["¿Que cantidad desea prestar?","Seleccione un Bolsillo disponible al que se le enviara el dinero"], None, None) 
        FFSolicitarFugaz.crearBotones(botonSolicitarFugaz)

        outputSolicitarFugaz = Text(frameSolicitarFugaz, height=100, font=("Arial Rounded MT Bold", 10))
        VentanaUsuario.framesEnPantalla.append(outputSolicitarFugaz)

        nombreSolicitarFugaz.pack()
        descSolicitarFugaz.pack()
        FFSolicitarFugaz.pack()

        VentanaUsuario.framesEnPantalla.append(frameSolicitarFugaz)

        #Boton solicitar un Prestamo Largo
        def botonSolicitarLargo():

            try:
                verificarVacio(FFSolicitarLargo)

            except ErrorAplicacion as e:
                PopUp(str(e))

        frameSolicitarLargo = Frame(self)
        nombreSolicitarLargo = Label(frameSolicitarLargo, text="Solicitar un Prestamo a Largo Plazo", font=("Arial Rounded MT Bold", 18), fg = "#245efd")
        descSolicitarLargo = Label(frameSolicitarLargo, text="Rellene los siguientes datos para solicitar un prestamo a largo plazo: ", font=("Arial Rounded MT Bold", 14))
        FFSolicitarLargo = FieldFrame(frameSolicitarLargo, None, ["¿Cuantos hijos tiene?","Digite su ingreso mensual","¿Cuanto dinero desea solicitar para realizar el prestamo?","¿Cuantos años tiene usted?","¿A cuantos años desea solicitar el prestamo?","Escriba el nombre de una referencia","Escriba el numero telefonico de la referencia","¿Desea dar alguna garantia para reducir la tasa de interes?","Escoja el elemento que dejara como garantia","Escoja el bolsillo al que se le envia el dinero"], None, None) 
        FFSolicitarLargo.crearBotones(botonSolicitarLargo)

        outputSolicitarLargo = Text(frameSolicitarLargo, height=100, font=("Arial Rounded MT Bold", 10))
        VentanaUsuario.framesEnPantalla.append(outputSolicitarLargo)

        nombreSolicitarLargo.pack()
        descSolicitarLargo.pack()
        FFSolicitarLargo.pack()

        VentanaUsuario.framesEnPantalla.append(frameSolicitarLargo)

        #Boton abonar a un prestamo
        def botonAbonarPrestamo():

            try:
                verificarVacio(FFAbonarPrestamo)

            except ErrorAplicacion as e:
                PopUp(str(e))

        frameAbonarPrestamo = Frame(self)
        nombreAbonarPrestamo = Label(frameAbonarPrestamo, text="Abonar a un Prestamo", font=("Arial Rounded MT Bold", 18), fg = "#245efd")
        descAbonarPrestamo = Label(frameAbonarPrestamo, text="Rellene los siguientes datos para abonar a un prestamo: ", font=("Arial Rounded MT Bold", 14))
        FFAbonarPrestamo = FieldFrame(frameAbonarPrestamo, None, ["Seleccione el bolsillo desde el que va a abonar","Ingrese la cantidad que va a abonar"], None, None) 
        FFAbonarPrestamo.crearBotones(botonAbonarPrestamo)

        outputAbonarPrestamo = Text(frameAbonarPrestamo, height=100, font=("Arial Rounded MT Bold", 10))
        VentanaUsuario.framesEnPantalla.append(outputAbonarPrestamo)

        nombreAbonarPrestamo.pack()
        descAbonarPrestamo.pack()
        FFAbonarPrestamo.pack()

        VentanaUsuario.framesEnPantalla.append(frameAbonarPrestamo)

        #Boton abonar a una meta
        def botonAbonarMetas():

            try:
                verificarVacio(FFAbonarMetas)

            except ErrorAplicacion as e:
                PopUp(str(e))

        frameAbonarMetas = Frame(self)
        nombreAbonarMetas = Label(frameAbonarMetas, text="Abonar a una Meta", font=("Arial Rounded MT Bold", 18), fg = "#245efd")
        descAbonarMetas = Label(frameAbonarMetas, text="Rellene los siguientes datos para abonar a una meta: ", font=("Arial Rounded MT Bold", 14))
        FFAbonarMetas = FieldFrame(frameAbonarMetas, None, ["Seleccione una meta","Seleccione el bolsillo desde el que va a abonar", "Ingrese la cantidad que va a abonar"], None, None) 
        FFAbonarMetas.crearBotones(botonAbonarMetas)

        outputAbonarMetas = Text(frameAbonarMetas, height=100, font=("Arial Rounded MT Bold", 10))
        VentanaUsuario.framesEnPantalla.append(outputAbonarMetas)

        nombreAbonarMetas.pack()
        descAbonarMetas.pack()
        FFAbonarMetas.pack()

        VentanaUsuario.framesEnPantalla.append(frameAbonarMetas)

        