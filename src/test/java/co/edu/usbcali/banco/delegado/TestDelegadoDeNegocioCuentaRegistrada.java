package co.edu.usbcali.banco.delegado;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.usbcali.banco.logica.IClienteLogica;
import co.edu.usbcali.banco.logica.ICuentaLogica;
import co.edu.usbcali.banco.logica.ICuentaRegistradaLogica;
import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.modelo.CuentaRegistrada;
import co.edu.usbcali.banco.vista.IDelegadoDeNegocio;

class TestDelegadoDeNegocioCuentaRegistrada {
	
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	@Autowired
	private ICuentaRegistradaLogica cuentaRegistradaLogica;
	
	@Autowired
	private ICuentaLogica cuentaLogica;
	
	@Autowired
	private IClienteLogica clienteLogica;
	
	private final static Logger log = LoggerFactory.getLogger(TestDelegadoDeNegocioCuentaRegistrada.class);
	
	

	@Test
	@DisplayName("Registrar Cuentas")
	void aTest() throws Exception {
		assertNotNull(cuentaLogica);
		assertNotNull(clienteLogica);
		assertNotNull(cuentaRegistradaLogica);
		CuentaRegistrada cuentaRegistrada = new CuentaRegistrada();
		
		
		Cliente cliente = clienteLogica.consultarPorId(new BigDecimal(572));
				
		assertNotNull(cliente);
		
		cuentaRegistrada.setCliente(cliente);
		
		Cuenta Cuenta = cuentaLogica.consultarPorId("0026-2816-9564-1013");
		assertNotNull(Cuenta);
		
		//cuentaRegistrada.setCureId(1L);
		
		cuentaRegistrada.setCuenta(Cuenta);
		
		cuentaRegistradaLogica.grabar(cuentaRegistrada);
		
	}
	
	@Test
	@DisplayName("Cuentas Registradas ")
	void bTest() {
		assertNotNull(cuentaRegistradaLogica);
		List<CuentaRegistrada> listaCuentasRegistradas = delegadoDeNegocio.consultarCuentaRegistradaTodos();
		for (CuentaRegistrada cuentaRegistrada : listaCuentasRegistradas) {
			log.info("id: " + cuentaRegistrada.getCureId());
			log.info("cliente: " + cuentaRegistrada.getCliente().getNombre());
			log.info("id cliente: " + cuentaRegistrada.getCliente().getClieId());
			log.info("id cuenta: " + cuentaRegistrada.getCuenta().getCuenId());
		}
		
	}

}
