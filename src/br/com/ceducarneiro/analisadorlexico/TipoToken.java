package br.com.ceducarneiro.analisadorlexico;

public enum TipoToken {

    NUM_REAL("NUM_REAL"),
    OP_SOMA("OP_SOMA"),
    OP_SUBTRACAO("OP_SUBTRACAO"),
    OP_DIVISAO("OP_DIVISAO"),
    OP_MULTIPLICACAO("OP_MULTIPLICACAO"),
    ATRIBUICAO("ATRIBUICAO"),
    IDENTIFICADOR("IDENTIFICADOR");

    String tipoStr;

    TipoToken(String tipoStr) {
        this.tipoStr = tipoStr;
    }

    @Override
    public String toString() {
        return tipoStr;
    }
}
