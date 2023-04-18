package br.com.elumini.miniautorizador.service;


import br.com.elumini.miniautorizador.dto.CartaoResponseDTO;
import br.com.elumini.miniautorizador.dto.CartaoResquestDTO;
import br.com.elumini.miniautorizador.exception.ExceptionCartaoNaoExistente;
import br.com.elumini.miniautorizador.model.Cartao;
import br.com.elumini.miniautorizador.repository.CartaoRepository;
import jakarta.validation.constraints.Null;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    public CartaoResponseDTO criarCartao(CartaoResquestDTO cartaoRequest) {
        return new CartaoResponseDTO(cartaoRepository.save(new Cartao(cartaoRequest)));
    }

    public Double obterSaldoCartao(String numero) {
        try {
            return cartaoRepository.obterSaldoCartao(numero);
        }catch (AopInvocationException e){
            throw new ExceptionCartaoNaoExistente();
        }
    }

}
