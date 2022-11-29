import pickle

class Deserializador:
    def __init__(self):
        self.args = None

    def deserializar(self):
        picklefile = open("C:\\Users\\danii\\Documents\\GitHub\\practica-g2-e6\\Python\\baseDatos\\src\\tmp\\usuarios.pkl", "rb")
        args = pickle.load(picklefile)
        picklefile.close()
        self.args = args

    def getArgs(self):
        return self.args