import pickle

class Deserializador:
    def __init__(self):
        self._usuario = None

    def deserializar(self):
        picklefile = open("C:\\Users\\danii\\Documents\\GitHub\\practica-g2-e6\\Python\\baseDatos\\src\\tmp\\usuario.pkl", "rb")
        usuario = pickle.load(picklefile)
        picklefile.close()
        self._usuario = usuario

    def getArgs(self):
        return self._usuario