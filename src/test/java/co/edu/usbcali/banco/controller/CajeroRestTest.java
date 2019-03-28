package co.edu.usbcali.banco.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import co.edu.usbcali.banco.dto.CajeroDTO;

public class CajeroRestTest {
	
	private final static Logger log = LoggerFactory.getLogger(CajeroRestTest.class);
	
	
	
	@Test
	@DisplayName("Realizar Retiro")
	void testCajeroRetiro() {
		String url = "http://localhost:8080/banco-web/controller/cajero/retirar";
		RestTemplate restTemplate = new RestTemplate();

/**
		CajeroDTO cajeroDTO = new CajeroDTO();
		cajeroDTO.setCuenId("0051-9076-0761-5793");
		cajeroDTO.setClave("6QMy0350r");
		cajeroDTO.setValor(new BigDecimal(1000000));
		
		restTemplate.postForLocation(url, cajeroDTO);
		*/
	}

}
