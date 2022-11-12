﻿from gestorAplicacion.economia import Divisa

class Colchon(Cuenta):

    def __init__(self, usuario, divisa, nombre, fechaRetiro):
        
        self._fechaRetiro = None

        super().__init__(usuario,divisa, nombre)
        self.setUsuario(usuario)
        self.setFechaRetiro(fechaRetiro)

    def retirar(self, monto):
        if self._fechaRetiro.isBefore(java.time.LocalDate.now()):
            return super().retirar(monto)
        return False


    def getFechaRetiro(self):
        return self._fechaRetiro

    def setFechaRetiro(self, fechaRetiro):
        self._fechaRetiro = fechaRetiro
