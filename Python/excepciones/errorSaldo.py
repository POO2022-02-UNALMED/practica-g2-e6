from excepciones.errorAplicacion import errorAplicacion

class ErrorNoSaldo(errorAplicacion):

    def __init__(self, saldo=None, limiteSaldo=False):
        if limiteSaldo:
            super().__init__(f"Tu operación ha sido rechazado ya que no cuentas con saldo suficiente o tu producto de origen no permite mover el valor indicado.")
        else:
            super().__init__(f"Proceso cancelado por saldo insuficiente. \n\n El saldo de tu cuenta es {saldo}.")