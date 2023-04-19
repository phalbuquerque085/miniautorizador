package br.com.elumini.miniautorizador.builder;

import br.com.elumini.miniautorizador.dto.CartaoResquestDTO;
import br.com.elumini.miniautorizador.model.Cartao;

import java.time.LocalDate;

public class CriadorCartao {
    private Long id;
    private String numero;
    private String senha;
    private Double saldo;
    private LocalDate dataCriacao;
    private LocalDate dataValidade;

    public CriadorCartao comId(Long id) {
        this.id = id;
        return this;
    }

    public CriadorCartao comNumero(String numero) {
        this.numero = numero;
        return this;
    }

    public CriadorCartao comSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public CriadorCartao comSaldo(Double saldo) {
        this.saldo = saldo;
        return this;
    }

    public CriadorCartao comDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
        return this;
    }

    public CriadorCartao comDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
        return this;
    }

    public Cartao builder(){
        Cartao cartao = new Cartao();
        cartao.setId(this.id);
        cartao.setNumero(this.numero);
        cartao.setSenha(this.senha);
        cartao.setSaldo(this.saldo);
        cartao.setDataCriacao(this.dataCriacao);
        cartao.setDataValidade(this.dataValidade);
        return cartao;
    }
}
