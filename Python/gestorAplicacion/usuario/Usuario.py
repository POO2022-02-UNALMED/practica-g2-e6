from gestorAplicacion.usuario.Bolsillo import Bolsillo

class Usuario:

    def __init__(self, cedula, nombre, email, fechaIngreso, clave):
        #Datos necesarios para la identificación del usuario
        self._cedula = cedula
        self._nombre = nombre
        self._email = email
        self._fechaIngreso = fechaIngreso
        self._clave = clave
        #Atributos necesarios para la interacción del usuario con el sistema
        self._bolsillos = []
        self._colchones = []
        self._ingresos = []
        self._salidas = []
        self._prestamos = []
        self._metas = []
        self._bolsillos.append(Bolsillo(self, "COP", "DEFAULT"))

    def getCedula(self):
        return self._cedula

    def setCedula(self, cedula):
        self._cedula = cedula

    def getNombre(self):
        return self._nombre

    def setNombre(self, nombre):
        self._nombre = nombre

    def getEmail(self):
        return self._email

    def setEmail(self, email):
        self._email = email

    def getFechaIngreso(self):
        return self._fechaIngreso

    def setFechaIngreso(self, fechaIngreso):
        self._fechaIngreso = fechaIngreso

    def getClave(self):
        return self._clave

    def setClave(self, clave):
        self._clave = clave

    def getBolsillos(self):
        return self._bolsillos

    def setBolsillos(self, bolsillos):
        self._bolsillos = bolsillos

    def getColchones(self):
        return self._colchones

    def setColchones(self, colchones):
        self._colchones = colchones

    def getIngresos(self):
        return self._ingresos

    def setIngresos(self, ingresos):
        self._ingresos = ingresos

    def getSalidas(self):
        return self._salidas

    def setSalidas(self, salidas):
        self._salidas = salidas

    def getPrestamos(self):
        return self._prestamos

    def setPrestamos(self, prestamos):
        self._prestamos = prestamos

    def getMetas(self):
        return self._metas

    def setMetas(self, metas):
        self._metas = metas

    #Se realiza un deposito en la cuenta destino del usuario y se genera un ingreso en el historial
    def nuevoIngreso(self, ingreso):
        ingreso.getCuentaDestino().depositar(ingreso.getValorDestino())
        self._ingresos.append(ingreso)

    #Se realiza un retiro validando su consistencia origen del usuario y se genera una salida en el historial
    def nuevaSalida(self, salida):
        retirado = salida.getCuentaOrigen().retirar(salida.getValorOrigen())
        if retirado:
            self._salidas.append(salida)
        return retirado

    def nuevoBolsillo(self, bolsillo):
        self._bolsillos.append(bolsillo)

    def nuevoColchon(self, colchon):
        self._colchones.append(colchon)

    def nuevaMeta(self, meta):
        self._metas.append(meta)

    def nuevoPrestamo(self, prestamo, bolsillo):
        self._prestamos.append(prestamo)
        bolsillo.depositar(prestamo.getDivisa().ConvertToDivisa(prestamo.getValorInicial(), bolsillo.getDivisa())[0])

    def nuevoPrestamo(self, prestamo, bolsillo):
        self._prestamos.append(prestamo)
        bolsillo.depositar(prestamo.getDivisa().ConvertToDivisa(prestamo.getValorInicial(), bolsillo.getDivisa())[0])


