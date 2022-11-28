from gestorAplicacion.usuario import Usuario

class PrestamoLargoPlazo(Prestamo):

    def _initialize_instance_fields(self):
        #instance fields found by Java to Python Converter:
        self._referencia = []
        self._garantia = 0


    _SERIALVERSIONUID = 1



#JAVA TO PYTHON CONVERTER TODO TASK: There is no Python equivalent to multiple constructors:
#ORIGINAL LINE: public PrestamoLargoPlazo(gestorAplicacion.usuario.Usuario usuario, double valorIncial, int tiempo, java.time.LocalDate fechaInicio, Divisa divisa, String[] referencia, Garantia garantia)
    def __init__(self, usuario, valorIncial, tiempo, fechaInicio, divisa, referencia, garantia):
        self._initialize_instance_fields()

        super().__init__(usuario, valorIncial, tiempo, fechaInicio, fechaInicio.plusMonths(tiempo), divisa)
        self.setReferencia(referencia)
        self.setGarantia(garantia)
        self.setTEA(valorIncial, garantia)

#JAVA TO PYTHON CONVERTER TODO TASK: There is no Python equivalent to multiple constructors:
#ORIGINAL LINE: public PrestamoLargoPlazo(gestorAplicacion.usuario.Usuario usuario, double valorIncial, int tiempo, java.time.LocalDate fechaInicio, Divisa divisa, String[] referencia)
    def __init__(self, usuario, valorIncial, tiempo, fechaInicio, divisa, referencia):
        self._initialize_instance_fields()

        super().__init__(usuario, valorIncial, tiempo, fechaInicio, fechaInicio.plusMonths(tiempo), divisa)
        self.setReferencia(referencia)
        self.setGarantia(None)
        self.setTEA(valorIncial)

    def getReferencia(self):
        return self._referencia

    def setReferencia(self, referencia):
        self._referencia = referencia

    def setTEA(self, monto):
        self.setTEA(monto, None)

    def setTEA(self, monto, garantia):
        acumulado = 0
        if monto < Divisa.COP.ConvertToDivisa(5000000, self.getDivisa())[0]:
            acumulado += self.baseTEAAlto - 30
        else:
            acumulado += self.baseTEABajo - 30
        if garantia is not None:
            if garantia == gestorAplicacion.economia.Garantia.VIVIENDA:
                acumulado += 6
            elif garantia == gestorAplicacion.economia.Garantia.LOTE:
                acumulado += 9
            elif garantia == gestorAplicacion.economia.Garantia.CARRO:
                acumulado += 16
            elif garantia == gestorAplicacion.economia.Garantia.MOTO:
                acumulado += 22
        else:
            acumulado += 30

        self.TEA = acumulado


    def getTEA(self):
        return self.TEA

    def getGarantia(self):
        return self._garantia

    def setGarantia(self, garantia):
        self._garantia = garantia

