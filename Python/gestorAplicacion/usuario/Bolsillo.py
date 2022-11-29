from gestorAplicacion.usuario.Cuenta import Cuenta


class Bolsillo(Cuenta):

    def __init__(self, usuario, divisa, nombre):
        super().__init__(usuario,divisa, nombre)

