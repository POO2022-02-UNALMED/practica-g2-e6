class Cuenta:

    numeroCuenta = 0

    def __init__(self, usuario, divisa, nombre):
        self._numeroCuenta = Cuenta.numeroCuenta
        self._usuario = usuario
        self._saldo = 0
        self._divisa = divisa
        self._nombre = nombre
        Cuenta.numeroCuenta += 1

    def getNombre(self):
        return self._nombre

    def setNombre(self, nombre):
        self._nombre = nombre
        
    def getNumeroCuenta(self):
        return self._numeroCuenta

    def setNumeroCuenta(self, numeroCuenta):
        self._numeroCuenta = numeroCuenta

    def getUsuario(self):
        return self._usuario

    def setUsuario(self, usuario):
        self._usuario = usuario

    def depositar(self, cantidad):
        self._saldo = self._saldo + cantidad

    def retirar(self, cantidad):
        if cantidad<=self._saldo:
            self._saldo = self._saldo - cantidad
            return True
        else:
            return False

    def getSaldo(self):
        return self._saldo

    def setSaldo(self, saldo):
        self._saldo = saldo

    def getDivisa(self):
        return self._divisa

    def setDivisa(self, divisa):
        self._divisa = divisa

