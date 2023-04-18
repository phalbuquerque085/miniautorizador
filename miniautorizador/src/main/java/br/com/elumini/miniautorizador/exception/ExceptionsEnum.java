package br.com.elumini.miniautorizador.exception;

public enum ExceptionsEnum {

    ERRO_SERVIDOR(){
        @Override
        public String getErro() {
            return "Ocorreu um erro interno no servicor.";
        }
    },
    SALDO_INSUFICIENTE{
        @Override
        public String getErro() {
            return "SALDO_INSUFICIENTE";
        }
    },
    SENHA_INVALIDA {
        @Override
        public String getErro() {
            return "SENHA_INVALIDA";
        }
    },
    CARTAO_INEXISTENTE {
        @Override
        public String getErro() {
            return "";
        }
    };

    public abstract String getErro();
}