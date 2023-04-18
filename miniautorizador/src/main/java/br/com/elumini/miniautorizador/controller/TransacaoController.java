package br.com.elumini.miniautorizador.controller;

import br.com.elumini.miniautorizador.dto.TransacaoRequestDTO;
import br.com.elumini.miniautorizador.service.TransacaoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping("/transacoes")
    public ResponseEntity<Object> criar(@RequestBody @Valid TransacaoRequestDTO transacaoRequestDTO) {
        transacaoService.realizarTransacao(transacaoRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.CREATED);
    }

}
