class Movimiento:

    def __init__(self, valorDestino, valorOrigen, fechaCreacion, interno, bancoOrigen):
        self._valorDestino = valorDestino
        self._valorOrigen = valorOrigen
        self._fechaCreacion = fechaCreacion
        self._interno = interno
        self._banco = bancoOrigen

    def getValorDestino(self):
        return self._valorDestino

    def setValorDestino(self, valorDestino):
        self._valorDestino = valorDestino

    def getValorOrigen(self):
        return self._valorOrigen

    def setValorOrigen(self, valorOrigen):
        self._valorOrigen = valorOrigen

    def getFechaCreacion(self):
        return self._fechaCreacion

    def setFechaCreacion(self, fechaCreacion):
        self._fechaCreacion = fechaCreacion

    def isInterno(self):
        return self._interno

    def setInterno(self, interno):
        self._interno = interno

    def getBanco(self):
        return self._bancoOrigen
        
    def setBanco(self, bancoOrigen):
        self._bancoOrigen = bancoOrigen

