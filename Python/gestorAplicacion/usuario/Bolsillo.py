from gestorAplicacion.economia import Divisa


class Bolsillo(Cuenta):

    def __init__(self, usuario, divisa, nombre):
        super().__init__(usuario,divisa, nombre)

