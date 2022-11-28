from tkinter import *
import pathlib
import os
from ventanaUsuario import VentanaUsuario
import ventanaUsuario

class VentanaInicio(Tk):
    def __init__(self):
        super().__init__()

        # Parámetros de la ventana de inicio
        self.title('Sistema Gestor de Dinero')
        self.option_add("*tearOff",  False)
        self.geometry("1400x720")
        self.resizable(False,False)

        # Barra de menú
        self._barraMenu = Menu(self)
        inicio = Menu(self._barraMenu)
        inicio.add_command(label = "Descripcion", command = lambda: self.desplegarDescripcion())
        inicio.add_command(label = "Salir", command = lambda: self.destroy())
        self._barraMenu.add_cascade(label = "Inicio", menu = inicio)
        self.config(menu = self._barraMenu)

        archivo = Menu(self._barraMenu)
        archivo.add_command(label="Aplicacion", command=lambda: infoApp())
        archivo.add_command(label="Salir y guardar", command=lambda: cerrarGuardar())
        self._barraMenu.add_cascade(label="Archivo", menu=archivo)
        

        self._p1 = P1(self)
        self._p2 = P2(self) 

        self._p1.grid(row = 0, column = 0, padx=(10,10))
        self._p2.grid(row = 0, column = 1, padx=(10,10))

    # Función desplegar descripción de la aplicaciíon 
    def desplegarDescripcion(self):
        self._p1._descripcion.pack(pady=(10,0))
        self.geometry("1420x840")

class P1(Frame):
    def __init__(self, ventana):
        super().__init__(ventana)
        self._ventana = ventana

        # Definicion de Frames
        self._p3 = Frame(self)
        self._p4_1 = Frame(self)
        self._p4_2 = Frame(self)

        textoSaludo = f"Bienvenid@ al Sistema de Gestor de Dinero\n"

        self._saludo = Label(self._p3, text = textoSaludo, font = ("Arial Rounded MT Bold", 16), fg = "#131D60", bg= "#AEDEF5")
        self._saludo.pack()

        textoDescripcion = f"El Sistema de Gestor de Dinero es una aplicación que permite al usuario realizar multiples\n" \
                           f"..." \
                           f"..." 
                           
        self._descripcion = Label(self._p3, text = textoDescripcion, width = 100, justify = "left", font=("Verdana", 8))

        # Imagenes 5 para ventana de inicio
        self._imagenActual = 0 
        self._imagenes = []

        for i in range(5):
            archivo = os.path.join(pathlib.Path(__file__).parent.parent.parent.absolute(), f"practica-g2-e6\\Python\multimedia\Imagen{i+1}.png")
            imagen = PhotoImage(file = archivo)
            self._imagenes.append(imagen)

         # Cambiar Imagen
        self._imagen = Label(self._p4_1, image = self._imagenes[0], height = 480, width = 640)
        self._imagen.bind('<Enter>', self.cambiarImagen) 
        self._imagen.pack()

        # Boton de acceso a la aplicacion abajo en P4
        self._boton = Button(self._p4_2, text = "Acceder a la aplicacion", font = ("Arial Rounded MT Bold", 16), fg = "white", bg = "#245efd", command = lambda: self.accederApp())
        self._boton.pack()

        # Colocar todos los elementos en pantalla
        self._p3.grid(row = 0, column = 0, pady=(10,10))
        self._p4_1.grid(row = 1, column = 0, pady=(10,10))
        self._p4_2.grid(row = 2, column = 0, pady=(10,10))

    # Funcion Cambiar de imagen de P4 al pasar el mouse por encima
    def cambiarImagen(self, args):
        if self._imagenActual == 4:
            self._imagenActual = 0
        else:
            self._imagenActual += 1

        self._imagen.configure(image = self._imagenes[self._imagenActual])
        self._imagen.image = self._imagenes[self._imagenActual]

    # Acceder a la aplicacion al darle click al boton de P4
    def accederApp(self):
        self._ventana.destroy()
        ventanaUsuario = VentanaUsuario()
        

# Frame P2 - Hoja de vida de los integrantes
class P2(Frame):
    _posicion_imagen = [(0, 0), (0, 1), (1, 0), (1, 1)]    

    def __init__(self, window):
        super().__init__(window)

        self._p5 = Frame(self)
        self._p6 = Frame(self) 

        self._text = None
        self._next_hv = 0
        self._photos = [None, None, None, None]
        self._labels = [] 

        self.cargarHVTexto(0)

        for i in range(0, 4):
            label = Label(self._p6, width = 320, height = 240)
            (r, c) = P2._posicion_imagen[i]
            label.grid(row=r, column=c)
            self._labels.append(label)
            self.cargarHVImagen(0, i)
    
        self._p5.grid()
        self._p6.grid()

    # Función para mostrar la siguiente hoja de vida
    def proximo(self, _):
        if self._next_hv < 3:
            self._next_hv = self._next_hv + 1
        else:
            self._next_hv = 0

        self._photos = [None, None, None, None]
        self.cargarHVTexto(self._next_hv)
        for i in range(0, 4):
            self.cargarHVImagen(self._next_hv, i)

    # Carga imagen en formato png
    def cargarHVImagen(self, hv_num, numero):
        path = os.path.join(pathlib.Path(__file__).parent.parent.parent.absolute(),'practica-g2-e6\\Python\multimedia\IMG{0}{1}.png'.format(hv_num, numero))
        photo = PhotoImage(file = path)
        self._labels[numero].configure(image = photo)
        self._labels[numero].image = photo 

     # Carga texto en formato txt
    def cargarHVTexto(self, numero):
        self._text = Text(self._p5, height = 10, font = ("Arial Rounded MT Bold",10), width = 80)
        self._text.grid(row = 1, column = 0)
        self._text.bind('<Button-1>', self.proximo)

        path = os.path.join(pathlib.Path(__file__).parent.parent.parent.absolute(),'practica-g2-e6\\Python\multimedia\TXT{0}4.txt'.format(numero))

        with open(path, "r+") as hv_text:
            self._text.insert(INSERT, hv_text.read())