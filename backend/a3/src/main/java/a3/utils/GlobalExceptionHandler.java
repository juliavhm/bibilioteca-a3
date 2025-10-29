package a3.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import a3.dto.MensagemErro;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<MensagemErro> handleValidationErrors(MethodArgumentNotValidException ex) {
		List<String> erros = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());

		return ResponseEntity.badRequest().body(new MensagemErro("Validação falhou: " + erros));
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<MensagemErro> handleConstraintViolation(ConstraintViolationException ex) {
		List<String> erros = ex.getConstraintViolations().stream().map(v -> v.getMessage())
				.collect(Collectors.toList());

		return ResponseEntity.badRequest().body(new MensagemErro("Violação de restrição:" + erros));
	}

	@ExceptionHandler(RecursoNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<MensagemErro> handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensagemErro(ex.getMessage()));
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<MensagemErro> handleRecursoIlegal(IllegalArgumentException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemErro(ex.getMessage()));

	}

}
