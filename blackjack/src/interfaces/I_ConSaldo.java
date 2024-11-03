package interfaces;

import exepciones.ValorInvalidoException;

public interface I_ConSaldo {
    void cargarSaldo (double monto) throws ValorInvalidoException;
    void verSaldo ();
}
