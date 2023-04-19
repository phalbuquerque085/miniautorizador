package br.com.elumini.miniautorizador.service;

import br.com.elumini.miniautorizador.builder.CriadorCartao;
import br.com.elumini.miniautorizador.builder.CriadorTransacao;
import br.com.elumini.miniautorizador.dto.TransacaoRequestDTO;
import br.com.elumini.miniautorizador.exception.ExceptionCartaoNaoExistente_422;
import br.com.elumini.miniautorizador.exception.ExceptionSaldoInsuficiente;
import br.com.elumini.miniautorizador.exception.ExceptionSenhaCartaoInvalida;
import br.com.elumini.miniautorizador.model.Cartao;
import br.com.elumini.miniautorizador.repository.CartaoRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransacaoServiceTest {
    private static final String NUMERO_CARTAO = "6549873025634501";
    private static final String SENHA_CARTAO = "1234";
    private static final Double SALDO = 500.0;

    @InjectMocks
    private TransacaoService transacaoService;
    @Mock
    private CartaoRepository cartaoRepository;
    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deveRealizarTransacao() {
        TransacaoRequestDTO transacao = criaTransacaoBase();
        Cartao cartao = criaCartaoBase();
        when(cartaoRepository.findByNumeroLock(NUMERO_CARTAO)).thenReturn(cartao);
        when(cartaoRepository.findByNumero(NUMERO_CARTAO)).thenReturn(Optional.of(cartao));
        when(cartaoRepository.findByNumeroAndSenha(NUMERO_CARTAO, SENHA_CARTAO)).thenReturn(Optional.of(cartao));
        when(cartaoRepository.obterLimiteCartao(transacao.getValor(), NUMERO_CARTAO )).thenReturn(Optional.empty());

        Cartao cartaoComNovoSaldo = transacaoService.realizarTransacao(transacao);
        Double novoSaldo = SALDO - transacao.getValor();
        assertEquals(cartaoComNovoSaldo.getSaldo(), novoSaldo);
    }

    @Test
    public void deveRetornarExceptionExceptionCartaoNaoExistente_422CasoNaoExistaCartao() {
        TransacaoRequestDTO transacao = criaTransacaoBase();
        Cartao cartao = criaCartaoBase();
        when(cartaoRepository.findByNumero(NUMERO_CARTAO)).thenThrow(ExceptionCartaoNaoExistente_422.class);
        assertThrows(ExceptionCartaoNaoExistente_422.class, () -> transacaoService.realizarTransacao(transacao));
    }

    @Test
    public void deveRetornarExceptionSenhaCartaoInvalida() {
        TransacaoRequestDTO transacao = criaTransacaoBase();
        Cartao cartao = criaCartaoBase();
        when(cartaoRepository.findByNumeroLock(NUMERO_CARTAO)).thenReturn(cartao);
        when(cartaoRepository.findByNumero(NUMERO_CARTAO)).thenReturn(Optional.of(cartao));
        when(cartaoRepository.findByNumeroAndSenha(NUMERO_CARTAO, SENHA_CARTAO)).thenThrow(ExceptionSenhaCartaoInvalida.class);
        assertThrows(ExceptionSenhaCartaoInvalida.class, () -> transacaoService.realizarTransacao(transacao));
    }

    @Test
    public void deveRetornarExceptionSaldoInsuficiente() {
        TransacaoRequestDTO transacao = criaTransacaoBase();
        Cartao cartao = criaCartaoBase();
        when(cartaoRepository.findByNumeroLock(NUMERO_CARTAO)).thenReturn(cartao);
        when(cartaoRepository.findByNumero(NUMERO_CARTAO)).thenReturn(Optional.of(cartao));
        when(cartaoRepository.findByNumeroAndSenha(NUMERO_CARTAO, SENHA_CARTAO)).thenReturn(Optional.of(cartao));
        when(cartaoRepository.obterLimiteCartao(transacao.getValor(), NUMERO_CARTAO )).thenReturn(Optional.of(1l));
        assertThrows(ExceptionSaldoInsuficiente.class, () -> transacaoService.realizarTransacao(transacao));
    }

    private static TransacaoRequestDTO criaTransacaoBase() {
        TransacaoRequestDTO transacao = new CriadorTransacao().
                comValor(10.0).
                comNumeroCartao(NUMERO_CARTAO).
                comSenhaCartao(SENHA_CARTAO).builder();
        return transacao;
    }

    private static Cartao criaCartaoBase() {
        Cartao cartao = new CriadorCartao().
                comId(1l).comNumero(NUMERO_CARTAO).
                comSenha(SENHA_CARTAO).comSaldo(SALDO).
                comDataCriacao(LocalDate.now()).
                comDataValidade(LocalDate.now().plusYears(5)).builder();
        return cartao;
    }
}
