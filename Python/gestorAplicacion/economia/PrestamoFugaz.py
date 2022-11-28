from gestorAplicacion.usuario import Usuario

class PrestamoFugaz(Prestamo):

    _SERIALVERSIONUID = -3388280595710146580

    def __init__(self, usuario, valorInicial, fechaInicio, divisa):
        super().__init__(usuario,valorInicial, 6, fechaInicio, fechaInicio.plusMonths(6), divisa)
        self.setTEA(valorInicial)

    def setTEA(self, monto):
        if monto < Divisa.COP.ConvertToDivisa(1000000, self.getDivisa())[0]:
            self.TEA = self.baseTEAAlto
        else:
            self.TEA = self.baseTEABajo

    def setTEA(self, monto, garantia):
        self.setTEA(monto)

    def getTEA(self):
        return self.TEA
