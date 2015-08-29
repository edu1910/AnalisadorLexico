package br.com.ceducarneiro.analisadorlexico;

public class Analisador {

    private String code = "23+4";

    private int idx = 0;
    private char ch;

    public void readCh() {
        ch = code.length() > idx ? code.charAt(idx++) : 0;
    }

    public boolean readCh(char ch) {
        readCh();
        return (this.ch == ch);
    }

    public Token nextToken() throws LexException {
        Token token = null;
        int estado = 1;

        String lexema = "";

        while (token == null && estado > 0) {
            switch (estado) {
                case 1 :
                    readCh();
                    switch (ch) {
                        case '+' : token = new Token(TipoToken.OP_SOMA); break;
                        case '-' : token = new Token(TipoToken.OP_SUBTRACAO); break;
                        case '*' : token = new Token(TipoToken.OP_MULTIPLICACAO); break;
                        case '/' : token = new Token(TipoToken.OP_DIVISAO); break;
                        case ':' :
                            if (readCh('=')) {
                                token = new Token(TipoToken.ATRIBUICAO);
                            } else {
                                throw new LexException(ch);
                            }
                            break;
                        case '.' : estado = 3; break;
                        default:
                            if (ch >= '0' && ch <= '9') {
                                estado = 2;
                            } else if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
                                estado = 11;
                            } else if (ch == 0) {
                                estado = 0;
                            } else if (Character.isWhitespace(ch)) {
                                continue;
                            } else {
                                throw new LexException(ch);
                            }
                    }
                    break;
                case 2:
                    lexema += ch;
                    readCh();
                    if (ch >= '0' && ch <= '9') {
                        continue;
                    } else if (ch == '.') {
                        estado = 3;
                    } else {
                        if (ch != 0) idx--;
                        token = new Token(TipoToken.NUM_REAL, lexema);
                    }
                    break;
                case 3:
                    lexema += ch;
                    readCh();
                    if (ch >= '0' && ch <= '9') {
                        estado = 4;
                    } else {
                        throw new LexException(ch);
                    }
                    break;
                case 4:
                    lexema += ch;
                    readCh();
                    if (ch >= '0' && ch <= '9') {
                        continue;
                    } else {
                        if (ch != 0) idx--;
                        token = new Token(TipoToken.NUM_REAL, lexema);
                    }
                    break;
                case 11:
                    lexema += ch;
                    readCh();
                    if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
                        continue;
                    } else {
                        if (ch != 0) idx--;
                        token = new Token(TipoToken.IDENTIFICADOR, lexema);
                    }
                    break;
            }
        }

        return token;
    }

}
