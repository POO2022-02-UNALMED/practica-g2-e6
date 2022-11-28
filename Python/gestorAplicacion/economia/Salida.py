from gestorAplicacion.usuario import Cuenta

class Salida(Movimiento):

    def _initialize_instance_fields(self):
        #instance fields found by Java to Python Converter:
        self._cuentaOrigen = None
        self._cuentaDestino = None
        self._divisaOrigen = 0
        self._divisaDestino = 0
        self._bancoDestino = 0

#JAVA TO PYTHON CONVERTER TODO TASK: There is no Python equivalent to multiple constructors:
#ORIGINAL LINE: public Salida(double valorDestino, double valorOrigen, java.time.LocalDate fechaCreacion, gestorAplicacion.usuario.Cuenta cuentaOrigen, gestorAplicacion.usuario.Cuenta cuantaDestino, Divisa divisaOrigen, Divisa divisaDestino)
    def __init__(self, valorDestino, valorOrigen, fechaCreacion, cuentaOrigen, cuantaDestino, divisaOrigen, divisaDestino):
        self._initialize_instance_fields()

        super().__init__(valorDestino,valorOrigen, fechaCreacion, True, None)
        self.setCuentaOrigen(cuentaOrigen)
        self.setCuentaDestino(cuantaDestino)
        self.setDivisaOrigen(divisaOrigen)
        self.setDivisaDestino(divisaDestino)

#JAVA TO PYTHON CONVERTER TODO TASK: There is no Python equivalent to multiple constructors:
#ORIGINAL LINE: public Salida(double valorDestino, java.time.LocalDate fechaCreacion, gestorAplicacion.usuario.Cuenta cuentaOrigen, Banco bancoDestino, Divisa divisaOrigen)
    def __init__(self, valorDestino, fechaCreacion, cuentaOrigen, bancoDestino, divisaOrigen):
        self._initialize_instance_fields()

        super().__init__(valorDestino,valorDestino, fechaCreacion, False, bancoDestino)
        self.setBancoDestino(bancoDestino)
        self.setCuentaOrigen(cuentaOrigen)
        self.setDivisaOrigen(divisaOrigen)

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


