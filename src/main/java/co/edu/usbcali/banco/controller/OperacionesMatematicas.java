package co.edu.usbcali.banco.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.banco.dto.ResultadoDTO;

@RestController
@RequestMapping("/operaciones")
public class OperacionesMatematicas {
	
	@GetMapping(value="/sumar/{numeroUno}/{numeroDos}")
	public ResultadoDTO sumar(@PathVariable("numeroUno") Integer n1, @PathVariable("numeroDos") Integer n2) {
		ResultadoDTO resultadoDTO = new ResultadoDTO();
		resultadoDTO.setValor(n1+n2);
		return resultadoDTO;
	}
	
	@GetMapping(value="/restar/{numeroUno}/{numeroDos}")
	public ResultadoDTO restar(@PathVariable("numeroUno") Integer n1, @PathVariable("numeroDos") Integer n2) {
		ResultadoDTO resultadoDTO = new ResultadoDTO();
		resultadoDTO.setValor(n1-n2);
		return resultadoDTO;
	}

}
