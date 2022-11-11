import pickle

class Deserializador:
    def __init__(self):
        self.args = None

    def deserializar(self):
        picklefile = open("src/tmp/usuarios.pkl", "rb")
        args = pickle.load(picklefile)
        picklefile.close()
        self.args = args