package co.edu.usbcali.banco.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import co.edu.usbcali.banco.dto.ClienteDTO;

class ClienteRestTest {
	
	private final static Logger log = LoggerFactory.getLogger(ClienteRestTest.class);
	
	BigDecimal clieId = new BigDecimal("101010");

	@Test
	@DisplayName("Consultar cliente")
	void testConsultarCliente() {
		String url = "http://localhost:8080/banco-web/controller/cliente/101010";
		RestTemplate restTemplate = new RestTemplate();
		ClienteDTO clienteDTO = restTemplate.getForObject(url, ClienteDTO.class);
		
		assertNotNull(clienteDTO);
		
		log.info("id: " + clienteDTO.getClieId());
		log.info("Telefono: " + clienteDTO.getTelefono());
		log.info("Dirección: " + clienteDTO.getDireccion());
		log.info("Nombre: " + clienteDTO.getNombre());
	}
	
	@Test
	@DisplayName("Crear cliente")
	void testCrearCliente() {
		String url = "http://localhost:8080/banco-web/controller/cliente/crear";
		RestTemplate restTemplate = new RestTemplate();
		
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setActivo('S');
		clienteDTO.setClieId(clieId);
		clienteDTO.setDireccion("Avenida siempre viva");
		clienteDTO.setEmail("j-pablo-cp@hotmail.com");
		clienteDTO.setIdTipoDocumento(1L);
		clienteDTO.setNombre("Juan Pablo");
		clienteDTO.setNombreTipoDocumento("CEDULA");
		clienteDTO.setTelefono("1376349896");
		
		restTemplate.postForLocation(url, clienteDTO);
	}
	
	@Test
	@DisplayName("Modificar cliente")
	void testModificarCliente() {
		String url = "http://localhost:8080/banco-web/controller/cliente/101010";
		RestTemplate restTemplate = new RestTemplate();
		ClienteDTO clienteDTO = restTemplate.getForObject(url, ClienteDTO.class);
		
		assertNotNull(clienteDTO);
		
		clienteDTO.setNombre("Pablito Chacón");
		
		String urlModificar = "http://localhost:8080/banco-web/controller/cliente/modificar";
		restTemplate.put(urlModificar, clienteDTO);
	}
	
	@Test
	@DisplayName("Borrar cliente")
	void testBorrarCliente() {
		String url = "http://localhost:8080/banco-web/controller/cliente/borrar/"+clieId;
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(url);
		
	}

}
