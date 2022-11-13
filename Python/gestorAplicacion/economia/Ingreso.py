from gestorAplicacion.usuario import Cuenta

class Ingreso(Movimiento):

    def _initialize_instance_fields(self):
        #instance fields found by Java to Python Converter:
        self._cuentaOrigen = None
        self._cuentaDestino = None
        self._divisaOrigen = 0
        self._divisaDestino = 0

#JAVA TO PYTHON CONVERTER TODO TASK: There is no Python equivalent to multiple constructors:
#ORIGINAL LINE: public Ingreso(double valorDestino, double valorOrigen, java.time.LocalDate fechaCreacion,gestorAplicacion.usuario.Cuenta cuentaOrigen, gestorAplicacion.usuario.Cuenta cuentaDestino, Divisa divisaOrigen, Divisa divisaDestino)
    def __init__(self, valorDestino, valorOrigen, fechaCreacion, cuentaOrigen, cuentaDestino, divisaOrigen, divisaDestino):
        self(valorDestino, valorOrigen, fechaCreacion, True,None, cuentaOrigen, cuentaDestino,divisaOrigen,divisaDestino)

#JAVA TO PYTHON CONVERTER TODO TASK: There is no Python equivalent to multiple constructors:
#ORIGINAL LINE: public Ingreso(double valorDestino, java.time.LocalDate fechaCreacion, Banco bancoOrigen, gestorAplicacion.usuario.Cuenta cuentaDestino, Divisa divisa)
    def __init__(self, valorDestino, fechaCreacion, bancoOrigen, cuentaDestino, divisa):
        self(valorDestino,valorDestino,fechaCreacion,False, bancoOrigen, None,cuentaDestino,divisa,divisa)

#JAVA TO PYTHON CONVERTER TODO TASK: There is no Python equivalent to multiple constructors:
#ORIGINAL LINE: public Ingreso(double valorDestino, double valorOrigen, java.time.LocalDate fechaCreacion, boolean interno, Banco banco, gestorAplicacion.usuario.Cuenta cuentaOrigen, gestorAplicacion.usuario.Cuenta cuentaDestino, Divisa divisaOrigen, Divisa divisaDestino)
    def __init__(self, valorDestino, valorOrigen, fechaCreacion, interno, banco, cuentaOrigen, cuentaDestino, divisaOrigen, divisaDestino):
        self._initialize_instance_fields()

        super().__init__(valorDestino, valorOrigen, fechaCreacion, interno, banco)
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

