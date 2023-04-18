package br.com.elumini.miniautorizador.dto;

import br.com.elumini.miniautorizador.model.Cartao;

public class CartaoResponseDTO extends CartaoBaseDTO {

    public CartaoResponseDTO(String numero, String senha) {
        super(numero, senha);
    }

    public CartaoResponseDTO(Cartao cartao) {
        super(String.valueOf(cartao.getNumero()), cartao.getSenha());
    }

    public CartaoResponseDTO(CartaoResquestDTO cartaoRequest) {
        super(cartaoRequest.getNumeroCartao(), cartaoRequest.getSenha());
    }

}
