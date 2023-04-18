package br.com.elumini.miniautorizador.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TransacaoRequestDTO{
    @NotNull
    private Double valor;

    @NotNull
    @NotEmpty
    private String numeroCartao;

    @NotNull
    @NotEmpty
    private String senhaCartao;

    public TransacaoRequestDTO(String numero, String senha, Double valor) {
        this.valor = valor;
        this.senhaCartao = senha;
        this.numeroCartao = numero;
    }

}
