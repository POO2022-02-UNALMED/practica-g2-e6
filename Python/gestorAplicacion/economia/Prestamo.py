from administrador import Utils
from gestorAplicacion.usuario import Cuenta
from gestorAplicacion.usuario import Usuario


class Prestamo(Abonable):

    def __init__(self, usuario, valorInicial, tiempo, fechaInicio, fechaFinal, divisa):
        #instance fields found by Java to Python Converter:
        self._usuario = None
        self.valorInicial = 0
        self.valorPagado = 0
        self.tiempo = 0
        self._fechaInicio = None
        self._fechaFinal = None
        self._divisa = 0
        self._cumplida = False
        self._interesesPendientes = 0
        self._ultimaFechaPago = None
        self.TEA = 0
        self.baseTEAAlto = 34.49
        self.baseTEABajo = 30.69

        self.setUsuario(usuario)
        self.setValorInicial(valorInicial)
        self.valorPagado = 0
        setDivisa(divisa)
        setTiempo(tiempo)
        self._fechaInicio = fechaInicio
        self.setUltimaFechaPago(fechaInicio)
        setFechaPago(fechaFinal)
        setCumplida(False)
        setInteresesPendientes(0)

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
        return self.TEA

    def setTEA(self, monto):
        pass

    def setTEA(self, monto, garantia):
        pass

    def getValorInicial(self):
        return self.valorInicial

    def setValorInicial(self, valorInicial):
        self.valorInicial = valorInicial

    def getValorPagado(self):
        return self.valorPagado

    def setValorPagado(self, valorPagado):
        self.valorPagado = valorPagado

    def getTiempo(self):
        return tiempo

    def setTiempo(self, tiempo):
        self.tiempo = tiempo

    def getFechaInicio(self):
        return fechaInicio

    def getFechaPago(self):
        return fechaFinal

    def setFechaPago(self, fechaPago):
        self.fechaFinal = fechaPago

    def getDivisa(self):
        return divisa

    def setDivisa(self, divisa):
        self.divisa = divisa

    def isCumplida(self):
        return cumplida

    def getInteresesPendientes(self):
        return interesesPendientes

    def setInteresesPendientes(self, interesesPendientes):
        self.interesesPendientes = interesesPendientes

    def setCumplida(self, cumplida):
        self.cumplida = cumplida

    def calcularCuotas(self):
        n = Utils.mesesEntreFechas(self.ultimaFechaPago, self.fechaFinal)
        capitalPendiente = self.interesesPendientes + self.valorInicial - self.valorPagado
    #JAVA TO PYTHON CONVERTER TODO TASK: Java to Python Converter cannot determine whether both operands of this division are integer types - if they are then you should change 'lhs / rhs' to 'math.trunc(lhs / float(rhs))':
        r = (1 + self.TEA / 100) ** (1 / 12.0) - 1
        cuotaMensual = (r * capitalPendiente) / (1 - (1 + r) ** (-n))
        return cuotaMensual

    def abonar(self, monto, origen):
        arreglo = [0 for _ in range(3)]
        if not cumplida:
            capitalPendiente = self.valorInicial - self.valorPagado
            diasDeIntereses = Utils.diasEntreFechas(self.ultimaFechaPago, LocalDate.now())
    #JAVA TO PYTHON CONVERTER TODO TASK: Java to Python Converter cannot determine whether both operands of this division are integer types - if they are then you should change 'lhs / rhs' to 'math.trunc(lhs / float(rhs))':
            intereses = interesesPendientes + ((self.interesesPendientes + capitalPendiente) * ((1 + (self.TEA / 100)) ** (diasDeIntereses / 365.0) - 1))
            pendiente = intereses + capitalPendiente
            now = LocalDate.now()
            monto2 = origen.getDivisa().ConvertToDivisa(monto, self.divisa)
            salida = Salida(monto2[0],monto, now, origen, None, origen.getDivisa(), self.divisa)
            if self.usuario.nuevaSalida(salida):
                nuevosIntereses = Math.max(0, intereses - monto2[0])
                nuevoCapital = Math.min(capitalPendiente, pendiente - monto2[0])
                arreglo[0] = intereses - nuevosIntereses
                arreglo[1] = Math.max(0, monto2[0] - intereses + nuevosIntereses)
                arreglo[2] = monto2[1]
                self.interesesPendientes = nuevosIntereses
                self.valorPagado = self.valorInicial - nuevoCapital
                self.ultimaFechaPago = LocalDate.now()
                return arreglo
            return None
        return None

    def terminar(self, cuenta):
        self.cumplida = True
        if valorPagado > valorInicial:
            ingreso = Ingreso(self.divisa.ConvertToDivisa(valorPagado - valorInicial, cuenta.getDivisa())[0],(valorPagado-valorInicial), LocalDate.now(), True, None, None, cuenta, self.divisa, cuenta.getDivisa())
            usuario.nuevoIngreso(ingreso)
            return ingreso
        return None
    }
