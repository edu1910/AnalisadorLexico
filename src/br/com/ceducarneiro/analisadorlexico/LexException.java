package br.com.ceducarneiro.analisadorlexico;

public class LexException extends Exception {

    private char badCh;

    public LexException(char ch) {
        badCh = ch;
    }

    @Override
    public String toString() {
        return String.format("Caractere inesperado: \"%s\"", badCh != 0 ? badCh : "EOF");
    }

}
