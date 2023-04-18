package br.com.elumini.miniautorizador.model;

import br.com.elumini.miniautorizador.dto.CartaoResquestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Cartao", schema="miniautorizador")
public class Cartao {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;
//    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "cartao_sequence")
//    @SequenceGenerator(name="cartao_sequence", sequenceName="miniautorizador.cartao_seq", schema="miniautorizador", allocationSize = 1)
//    @GeneratedValue

    @Column(nullable = false, unique = true, updatable = false, length = 16)
    @NotNull
    @NotEmpty
    @Size(min = 16, max = 16, message = "Numero de cartao nao possui 16 digitos.")
    private String numero;

    private String senha;

    private Double saldo;

    private LocalDate dataCriacao;

    private LocalDate dataValidade;

    public Cartao() {}

    public Cartao(CartaoResquestDTO cartao) {
        this.numero = cartao.getNumeroCartao();
        this.senha = cartao.getSenha();
        this.saldo = 500.00;
        this.dataCriacao = LocalDate.now();
        this.dataValidade = LocalDate.now().plusYears(3).plusMonths(10);
    }



}