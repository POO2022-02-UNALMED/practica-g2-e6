import pickle

class Serializador:
    def __init__(self, usuario):
        self._usuario = usuario

    def serializar(self):
        picklefile = open("C:\\Users\\danii\\Documents\\GitHub\\practica-g2-e6\\Python\\baseDatos\\src\\tmp\\usuario.pkl", "wb")
        pickle.dump(self._usuario, picklefile)
        picklefile.close()
