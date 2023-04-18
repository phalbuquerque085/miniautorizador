package br.com.elumini.miniautorizador.exception;

import br.com.elumini.miniautorizador.dto.CartaoResponseDTO;
import br.com.elumini.miniautorizador.dto.CartaoResquestDTO;
import br.com.elumini.miniautorizador.model.Cartao;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> NotFoundException(ChangeSetPersister.NotFoundException e){
        return new ResponseEntity<>(new String(ExceptionsEnum.ERRO_SERVIDOR.getErro()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity<CartaoResponseDTO> dataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {
        return new ResponseEntity<>(new CartaoResponseDTO((CartaoResquestDTO) request.getAttribute("cartaoDTO")), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = {ExceptionCartaoNaoExistente.class})
    public ResponseEntity<Object> exceptionCartaoNaoExistente(ExceptionCartaoNaoExistente e) {
        return new ResponseEntity<>(new String(ExceptionsEnum.CARTAO_INEXISTENTE.getErro()), HttpStatus.NOT_FOUND);
    }

}