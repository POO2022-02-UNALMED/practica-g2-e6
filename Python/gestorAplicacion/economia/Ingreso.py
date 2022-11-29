from gestorAplicacion.usuario import Movimiento

class Ingreso(Movimiento):

    def __init__(self, valorDestino, valorOrigen, fechaCreacion, cuentaOrigen, cuentaDestino, divisaOrigen, divisaDestino, banco):
        super().__init__(valorDestino, valorOrigen, fechaCreacion, True, banco)
        self._cuentaOrigen = cuentaOrigen
        self._cuentaDestino = cuentaDestino
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

