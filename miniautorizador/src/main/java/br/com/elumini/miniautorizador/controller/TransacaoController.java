package br.com.elumini.miniautorizador.controller;

import br.com.elumini.miniautorizador.dto.TransacaoRequestDTO;
import br.com.elumini.miniautorizador.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping
public class TransacaoController {
    @Autowired
    private TransacaoService transacaoService;
    @PostMapping("/transacoes")
    @Operation(summary = "Executar transação com o cartão, informando número, senha e valor da transação.", responses = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = TransacaoRequestDTO.class))),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity", content = @Content(schema = @Schema(implementation = TransacaoRequestDTO.class)))})
    public ResponseEntity<Object> criar(@RequestBody @Valid TransacaoRequestDTO transacaoRequestDTO) {
        transacaoService.realizarTransacao(transacaoRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.CREATED);
    }
}
