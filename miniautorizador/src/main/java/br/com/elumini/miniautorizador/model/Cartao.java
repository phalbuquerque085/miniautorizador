package br.com.elumini.miniautorizador.model;

import br.com.elumini.miniautorizador.dto.CartaoResquestDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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