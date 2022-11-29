from administrador import Utils
from gestorAplicacion.economia import Abonable
from gestorAplicacion.economia import Ingreso
from gestorAplicacion.economia import Salida
from datetime import date, time, datetime

class Prestamo(Abonable):

    def __init__(self, usuario, valorInicial, tiempo, fechaInicio, fechaFinal, divisa):
        self._usuario = usuario
        self._valorInicial = valorInicial
        self._valorPagado = 0
        self._divisa = divisa
        self._tiempo = tiempo
        self._fechaInicio = fechaInicio
        self._ultimaFechaPago = fechaInicio
        self._fechaPago = fechaFinal
        self._cumplida = False
        self._interesesPendientes = 0
        self._ultimaFechaPago = None
        self._TEA = 0
        self._baseTEAAlto = 34.49
        self._baseTEABajo = 30.69


    def getUsuario(self):
        return self._usuario

    def setUsuario(self, usuario):
        self._usuario = usuario

    def getFechaFinal(self):
        return self._fechaFinal

    def setFechaFinal(self, fechaFinal):
        self._fechaFinal = fechaFinal

    def getUltimaFechaPago(self):
        return self._ultimaFechaPago

    def setUltimaFechaPago(self, ultimaFechaPago):
        self._ultimaFechaPago = ultimaFechaPago

    def getTEA(self):
        return self._TEA

    def getValorInicial(self):
        return self._valorInicial

    def setValorInicial(self, valorInicial):
        self._valorInicial = valorInicial

    def getValorPagado(self):
        return self._valorPagado

    def setValorPagado(self, valorPagado):
        self._valorPagado = valorPagado

    def getTiempo(self):
        return self._tiempo

    def setTiempo(self, tiempo):
        self._tiempo = tiempo

    def getFechaInicio(self):
        return self._fechaInicio

    def getFechaPago(self):
        return self._fechaFinal

    def setFechaPago(self, fechaPago):
        self._fechaFinal = fechaPago

    def getDivisa(self):
        return self._divisa

    def setDivisa(self, divisa):
        self._divisa = divisa

    def isCumplida(self):
        return self._cumplida

    def getInteresesPendientes(self):
        return self._interesesPendientes

    def setInteresesPendientes(self, interesesPendientes):
        self._interesesPendientes = interesesPendientes

    def setCumplida(self, cumplida):
        self._cumplida = cumplida

    def calcularCuotas(self):
        n = Utils.mesesEntreFechas(self.ultimaFechaPago, self.fechaFinal)
        capitalPendiente = self._interesesPendientes + self._valorInicial - self._valorPagado
        r = (1 + self.TEA / 100) ** (1 / 12.0) - 1
        cuotaMensual = (r * capitalPendiente) / (1 - (1 + r) ** (-n))
        return cuotaMensual

    def abonar(self, monto, origen):
        arreglo = [0 for _ in range(3)]
        if self._cumplida == False:
            capitalPendiente = self.valorInicial - self.valorPagado
            diasDeIntereses = Utils.diasEntreFechas(self.ultimaFechaPago, date.today())
            intereses = self._interesesPendientes + ((self._interesesPendientes + capitalPendiente) * ((1 + (self.TEA / 100)) ** (diasDeIntereses / 365.0) - 1))
            pendiente = intereses + capitalPendiente
            now = date.today()
            monto2 = origen.getDivisa().ConvertToDivisa(monto, self.divisa)
            salida = Salida(monto2[0],monto, now, origen, None, origen.getDivisa(), self.divisa)
            if self.usuario.nuevaSalida(salida):
                nuevosIntereses = max(0, intereses - monto2[0])
                nuevoCapital = min(capitalPendiente, pendiente - monto2[0])
                arreglo[0] = intereses - nuevosIntereses
                arreglo[1] = max(0, monto2[0] - intereses + nuevosIntereses)
                arreglo[2] = monto2[1]
                self.interesesPendientes = nuevosIntereses
                self.valorPagado = self.valorInicial - nuevoCapital
                self.ultimaFechaPago = date.today()
                return arreglo
            return None
        return None

    def terminar(self, cuenta):
        self._cumplida = True
        if self._valorPagado > self._valorInicial:
            ingreso = Ingreso(self.divisa.ConvertToDivisa(self._valorPagado - self._valorInicial, cuenta.getDivisa())[0],(self._valorPagado-self._valorInicial), date.today(), True, None, None, cuenta, self.divisa, cuenta.getDivisa())
            self._usuario.nuevoIngreso(ingreso)
            return ingreso
        return None
