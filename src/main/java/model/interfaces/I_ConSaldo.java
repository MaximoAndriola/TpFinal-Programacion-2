package model.interfaces;

import model.exepciones.ValorInvalidoException;

public interface I_ConSaldo {
    void agregarSaldo (String monto) throws ValorInvalidoException;
    double getSaldo ();
    void validarMonto(String monto)throws ValorInvalidoException;
}
