from gestorAplicacion.economia import Abonable, Contable, Salida, Ingreso
from datetime import date, time, datetime

class Meta(Abonable, Contable):

    def __init__(self, usuario, nombre, fechaInicio, objetivo, divisa):
        
        self._nombre = nombre
        self._cumplida = False
        self._fechaCumplimiento = None
        self._fechaInicio = fechaInicio
        self._objetivo = objetivo
        self._divisa = divisa
        self._usuario = usuario
        self._saldo = 0

    def getNombre(self):
        return self._nombre

    def setNombre(self, nombre):
        self._nombre = nombre

    def isCumplida(self):
        return self._cumplida

    def setCumplida(self, cumplida):
        self._cumplida = cumplida


    def getObjetivo(self):
        return self._objetivo

    def setObjetivo(self, objetivo):
        bol = [False for _ in range(2)]
        if not self._cumplida:
            self._objetivo = objetivo
            bol[1] = self.metaCumplida()
            bol[0] = True
        else:
            bol[0] = False
            bol[1] = False
        return bol

    def metaCumplida(self):
        return self._saldo>= self._objetivo


    def getDivisa(self):
        return self._divisa

    def setDivisa(self, divisa):
        self._divisa = divisa

    def getSaldo(self):
        return self._saldo

    def setSaldo(self, saldo):
        self._saldo = saldo

    def getUsuario(self):
        return self._usuario

    def setUsuario(self, usuario):
        self._usuario = usuario

    def getFechaCumplimiento(self):
        return self._fechaCumplimiento

    def setFechaCumplimiento(self, fechaCumplimiento):
        self._fechaCumplimiento = fechaCumplimiento

    def getFechaInicio(self):
        return self._fechaInicio

    def setFechaInicio(self, fechaInicio):
        self._fechaInicio = fechaInicio

    def abonar(self, monto, origen):
        if not self._cumplida:
            monto2 = origen.getDivisa().ConvertToDivisa(monto,self._divisa)
            salida = Salida(monto2[0],monto, date.today(), origen, None, origen.getDivisa(), self._divisa)
            retirado = self._usuario.nuevaSalida(salida)
            if not retirado:
                return None
            self._saldo += monto2[0]
            return salida
        return None

    def terminar(self, cuenta):
        nuevoSaldo = self.getDivisa().ConvertToDivisa(self._saldo, cuenta.getDivisa())
        self._saldo = 0
        self._cumplida = True
        self._fechaCumplimiento = date.today()
        ingreso = Ingreso(nuevoSaldo[0], self._saldo, date.today(), True, None, None, cuenta, self._divisa, cuenta.getDivisa())
        self._usuario.nuevoIngreso(ingreso)
        return ingreso
