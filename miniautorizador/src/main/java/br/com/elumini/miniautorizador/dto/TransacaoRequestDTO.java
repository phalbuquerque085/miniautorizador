package br.com.elumini.miniautorizador.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

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
