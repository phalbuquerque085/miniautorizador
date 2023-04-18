package br.com.elumini.miniautorizador.controller;

import br.com.elumini.miniautorizador.dto.CartaoResquestDTO;
import br.com.elumini.miniautorizador.service.CartaoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    @PostMapping("/cartoes")
    public ResponseEntity<Object> criar(@RequestBody @Valid CartaoResquestDTO cartaoDTO, HttpServletRequest request) {
        request.setAttribute("cartaoDTO", cartaoDTO);
        return new ResponseEntity<>(cartaoService.criarCartao(cartaoDTO), HttpStatus.CREATED);
    }

    @GetMapping("/cartoes/{numeroCartao}")
    public ResponseEntity<Object> obterSaldo(@PathVariable(required = true) String numero, HttpServletRequest request){
            return new ResponseEntity<>(cartaoService.obterSaldo(numero), HttpStatus.OK);
    }
}