package br.com.elumini.miniautorizador.service;

import br.com.elumini.miniautorizador.builder.CriadorCartao;
import br.com.elumini.miniautorizador.dto.CartaoResponseDTO;
import br.com.elumini.miniautorizador.exception.ExceptionCartaoNaoExistente_404;
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
public class CartaoServiceTest {
    private static final String NUMERO_CARTAO = "6549873025634501";
    private static final String SENHA_CARTAO = "1234";
    private static final Double SALDO = 500.0;

    @InjectMocks
    private CartaoService cartaoService;
    @Mock
    private CartaoRepository cartaoRepository;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deveCriarCartaoComNumeroESenhaOk() {
        Cartao cartao = criaCartaoBase();
        when(cartaoRepository.save(cartao)).thenReturn(cartao);

        CartaoResponseDTO cartaoResquestDTO = cartaoService.criarCartao(cartao);

        assertEquals(cartaoResquestDTO.getNumeroCartao(), NUMERO_CARTAO);
        assertEquals(cartaoResquestDTO.getSenha(), SENHA_CARTAO);
    }

    @Test
    public void deveRetornarExceptionCartaoNaoExistente_404CasoNaoExistaCartaoNaVerificacaoDeSaldo() {
        when(cartaoRepository.findByNumero(NUMERO_CARTAO)).thenThrow(ExceptionCartaoNaoExistente_404.class);
        assertThrows(ExceptionCartaoNaoExistente_404.class, () -> cartaoService.obterSaldo(NUMERO_CARTAO));
    }

    @Test
    public void deveRetornarSaldoCartao() {
        Cartao cartao = new CriadorCartao().comNumero(NUMERO_CARTAO).comSaldo(500.0).builder();
        when(cartaoRepository.findByNumero(NUMERO_CARTAO)).thenReturn(Optional.of(cartao));
        Double saldoBD = cartaoService.obterSaldo(cartao.getNumero());
        assertEquals(saldoBD, cartao.getSaldo());
    }

    private static Cartao criaCartaoBase() {
        Cartao cartao = new CriadorCartao().
                comId(1l).comNumero(NUMERO_CARTAO).
                comSenha(SENHA_CARTAO).comSaldo(500.0).
                comDataCriacao(LocalDate.now()).
                comDataValidade(LocalDate.now().plusYears(5)).builder();
        return cartao;
    }
}
