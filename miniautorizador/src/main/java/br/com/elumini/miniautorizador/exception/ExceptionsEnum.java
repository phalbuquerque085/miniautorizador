package br.com.elumini.miniautorizador.exception;

public enum ExceptionsEnum {

    ERRO_SERVIDOR() {
        @Override
        public String getErro() {
            return "Ocorreu um erro interno no servicor.";
        }
    },
    SALDO_INSUFICIENTE {
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
    CARTAO_INEXISTENTE_404 {
        @Override
        public String getErro() {
            return "";
        }
    }, CARTAO_INEXISTENTE_422 {
        @Override
        public String getErro() {
            return "CARTAO_INEXISTENTE";
        }
    };

    public abstract String getErro();
}