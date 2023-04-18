package br.com.elumini.miniautorizador.repository;

import br.com.elumini.miniautorizador.model.Cartao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartaoRepository extends CrudRepository<Cartao, String> {
    @Query("SELECT c.saldo FROM Cartao c WHERE c.numero= :numero")
    double obterSaldoCartao(String numero);

    Optional<Cartao> findByNumero(String numero);

    Optional<Cartao> findByNumeroAndSenha(String numero, String senha);

    @Query(nativeQuery = true, value = "SELECT IF(c.saldo > :valor, null, c.id) FROM miniautorizador.cartao c WHERE c.numero= :numero")
    Optional<Long> obterLimiteCartao(Double valor, String numero);

    @Query(nativeQuery = true, value = "SELECT * FROM miniautorizador.cartao c WHERE c.numero= :numero FOR UPDATE")
    Cartao findByNumeroLock(String numero);


}
