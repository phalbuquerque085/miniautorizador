package br.com.elumini.miniautorizador.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;



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