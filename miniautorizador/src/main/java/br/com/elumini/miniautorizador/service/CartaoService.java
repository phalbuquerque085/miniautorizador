package br.com.elumini.miniautorizador.service;


import br.com.elumini.miniautorizador.dto.CartaoResponseDTO;
import br.com.elumini.miniautorizador.dto.CartaoResquestDTO;
import br.com.elumini.miniautorizador.exception.ExceptionCartaoNaoExistente_404;
import br.com.elumini.miniautorizador.model.Cartao;
import br.com.elumini.miniautorizador.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    public CartaoResponseDTO criarCartao(CartaoResquestDTO cartaoRequest) {
        return new CartaoResponseDTO(cartaoRepository.save(new Cartao(cartaoRequest)));
    }

    public Double obterSaldo(String numero) {
        return cartaoRepository.findByNumero(numero).map(c -> c.getSaldo()).orElseThrow(ExceptionCartaoNaoExistente_404::new);
    }

}
