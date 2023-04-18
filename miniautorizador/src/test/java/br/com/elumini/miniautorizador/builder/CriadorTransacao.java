package br.com.elumini.miniautorizador.builder;

import br.com.elumini.miniautorizador.dto.TransacaoRequestDTO;

public class CriadorTransacao {
    private Double valor;
    private String numeroCartao;
    private String senhaCartao;


    public CriadorTransacao comValor(Double valor) {
        this.valor = valor;
        return this;
    }

    public CriadorTransacao comNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
        return this;
    }

    public CriadorTransacao comSenhaCartao(String senhaCartao) {
        this.senhaCartao = senhaCartao;
        return this;
    }

    public TransacaoRequestDTO builder(){
        TransacaoRequestDTO transacao = new TransacaoRequestDTO();
        transacao.setNumeroCartao(this.numeroCartao);
        transacao.setSenhaCartao(this.senhaCartao);
        transacao.setValor(this.valor);
        return transacao;
    }
}
