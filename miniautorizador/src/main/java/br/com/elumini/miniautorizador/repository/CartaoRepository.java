package br.com.elumini.miniautorizador.repository;

import br.com.elumini.miniautorizador.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartaoRepository extends JpaRepository<Cartao, String> {
    @Query("SELECT c.saldo FROM Cartao c WHERE c.numero= :numero")
    double obterSaldoCartao(String numero);

    Optional<Cartao> findByNumero(String numero);


}
