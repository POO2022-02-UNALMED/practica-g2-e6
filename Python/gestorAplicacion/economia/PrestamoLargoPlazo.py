from gestorAplicacion.economia import Prestamo
from datetime import date, time, datetime

class PrestamoLargoPlazo(Prestamo):

    def __init__(self, usuario, valorIncial, tiempo, fechaInicio, divisa, referencia, garantia):
        super().__init__(usuario, valorIncial, tiempo, fechaInicio, fechaInicio+datetime.timedelta(weeks=4*tiempo), divisa)
        self._referencia = referencia
        self._garantia = garantia
        self._TEA = valorIncial

    def getReferencia(self):
        return self._referencia

    def setReferencia(self, referencia):
        self._referencia = referencia

    def getTEA(self):
        return self._TEA

    def getGarantia(self):
        return self._garantia

    def setGarantia(self, garantia):
        self._garantia = garantia

