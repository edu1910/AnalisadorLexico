package br.com.ceducarneiro.analisadorlexico;

public class Main {

    public static void main(String[] args) throws LexException {
        Analisador lex = new Analisador();

        Token tok = lex.nextToken();
        while (tok != null) {
            System.out.print(tok);
            tok = lex.nextToken();
        }
    }

}
