package br.com.elumini.miniautorizador.controller;

import br.com.elumini.miniautorizador.dto.CartaoResquestDTO;
import br.com.elumini.miniautorizador.service.CartaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequestMapping
public class CartaoController {
    @Autowired
    private CartaoService cartaoService;

    @PostMapping("/cartoes")
    @Operation(summary = "Criar um cartão, informando número e senha.", responses = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = CartaoResquestDTO.class))),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity", content = @Content(schema = @Schema(implementation = CartaoResquestDTO.class)))})
    public ResponseEntity<Object> criar(@RequestBody @Valid CartaoResquestDTO cartaoDTO, HttpServletRequest request) {
        request.setAttribute("cartaoDTO", cartaoDTO);
        return new ResponseEntity<>(cartaoService.criarCartao(cartaoDTO), HttpStatus.CREATED);
    }

    @GetMapping("/cartoes/{numero}")
    @Operation(summary = "Obter saldo do cartão", responses = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = Double.class))),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    public ResponseEntity<Object> obterSaldo(@PathVariable(required = true) String numero, HttpServletRequest request) {
        return new ResponseEntity<>(cartaoService.obterSaldo(numero), HttpStatus.OK);
    }
}