import pickle

class Serializador:
    def __init__(self, *args):
        self.args = args

    def serializar(self):
        picklefile = open("src/tmp/usuarios.pkl", "wb")
        for arg in self.args:
            pickle.dump(arg, picklefile)
        picklefile.close()
