from gestorAplicacion.economia import Movimiento

class Salida(Movimiento):

    def __init__(self, valorDestino, valorOrigen, fechaCreacion, cuentaOrigen, cuantaDestino, divisaOrigen, divisaDestino):
        super().__init__(valorDestino,valorOrigen, fechaCreacion, True, None)
        self._cuentaOrigen = cuentaOrigen
        self._cuentaDestino = cuantaDestino
        self._divisaOrigen = divisaOrigen
        self._divisaDestino = divisaDestino

    def getCuentaOrigen(self):
        return self._cuentaOrigen

    def setCuentaOrigen(self, cuentaOrigen):
        self._cuentaOrigen = cuentaOrigen

    def getCuentaDestino(self):
        return self._cuentaDestino

    def setCuentaDestino(self, cuentaDestino):
        self._cuentaDestino = cuentaDestino

    def getDivisaOrigen(self):
        return self._divisaOrigen

    def setDivisaOrigen(self, divisaOrigen):
        self._divisaOrigen = divisaOrigen

    def getDivisaDestino(self):
        return self._divisaDestino

    def setDivisaDestino(self, divisaDestino):
        self._divisaDestino = divisaDestino

    def getBancoDestino(self):
        return self._bancoDestino
        
    def setBancoDestino(self, bancoDestino):
        self._bancoDestino = bancoDestino


