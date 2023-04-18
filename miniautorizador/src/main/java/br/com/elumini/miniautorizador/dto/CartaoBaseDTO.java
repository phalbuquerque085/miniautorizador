package br.com.elumini.miniautorizador.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class CartaoBaseDTO {
    @NotNull
    @NotEmpty
    private String numeroCartao;

    @NotNull
    @NotEmpty
    private String senha;

    public CartaoBaseDTO(String numero, String senha) {
        this.numeroCartao = numero;
        this.senha = senha;
    }

    public CartaoBaseDTO() {}
}