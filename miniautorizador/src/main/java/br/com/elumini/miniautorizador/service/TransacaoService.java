package br.com.elumini.miniautorizador.service;

import br.com.elumini.miniautorizador.dto.TransacaoRequestDTO;
import br.com.elumini.miniautorizador.exception.ExceptionCartaoNaoExistente_422;
import br.com.elumini.miniautorizador.exception.ExceptionSaldoInsuficiente;
import br.com.elumini.miniautorizador.exception.ExceptionSenhaCartaoInvalida;
import br.com.elumini.miniautorizador.model.Cartao;
import br.com.elumini.miniautorizador.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TransacaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Transactional
    public Cartao realizarTransacao(TransacaoRequestDTO transacao) {
        Cartao cartao = cartaoRepository.findByNumeroLock(transacao.getNumeroCartao());
        validaTransacao(transacao);
        cartao.setSaldo(cartao.getSaldo() - transacao.getValor());
        cartaoRepository.save(cartao);
        return cartao;
    }

    private void validaTransacao(TransacaoRequestDTO transacao) {
        cartaoRepository.findByNumero(transacao.getNumeroCartao()).map(c -> c.getId()).orElseThrow(ExceptionCartaoNaoExistente_422::new);
        cartaoRepository.findByNumeroAndSenha(transacao.getNumeroCartao(), transacao.getSenhaCartao()).map(c -> c).orElseThrow(ExceptionSenhaCartaoInvalida::new);
        cartaoRepository.obterLimiteCartao(transacao.getValor(), transacao.getNumeroCartao()).ifPresent(c-> {throw new ExceptionSaldoInsuficiente(); });
    }
}
