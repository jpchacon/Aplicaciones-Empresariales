package co.edu.usbcali.banco.logica;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.modelo.Usuario;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContext.xml")
@Rollback(false)
class TestTransaccionesBancariasLogica {
	
	private final static Logger log = LoggerFactory.getLogger(TestTransaccionesBancariasLogica.class);
	
	@Autowired
	private ITransaccionesBancariasLogica transaccionesBancariasLogica;
	
	@Autowired
	private IUsuarioLogica usuarioLogica;
	
	@Autowired
	private ICuentaLogica cuentaLogica;
	
	
	
	static String usuUsuario = "aaizikovitz9q";
	static String cuenId = "4640-0341-9387-5781";
	static BigDecimal valor = new BigDecimal(330000);
	
	@Disabled
	@Test
	@DisplayName("Retirar exitoso")
	void aTest() throws Exception {
		
		assertNotNull(usuarioLogica, "El usuarioLogica esta vacio");
		Usuario usuario = usuarioLogica.consultarPorId(usuUsuario);
		assertNotNull(usuario, "El usuario no existe");
		assertNotNull(cuentaLogica, "La cuentaLogica esta vacio");
		Cuenta cuenta = cuentaLogica.consultarPorId(cuenId);
		assertNotNull(cuenta, "La cuenta no existe");
		
		
		log.info("Saldo actual cuenta: " + cuenta.getSaldo());
		
		assertNotNull(transaccionesBancariasLogica," La transaccionesBancariasLogica esta vacia");
		
		transaccionesBancariasLogica.retirar(cuenId, valor, usuUsuario);
		
		log.info("Saldo despues de retiro cuenta: " + cuenta.getSaldo());
		
	}
	
	@Disabled
	@Test
	@DisplayName("Retirar error usuario")
	void bTest() throws Exception {
		
		assertNotNull(usuarioLogica, "El usuarioLogica esta vacio");
		Usuario usuario = usuarioLogica.consultarPorId("petrosky");
		assertNotNull(usuario, "El usuario no existe");
		assertNotNull(cuentaLogica, "La cuentaLogica esta vacio");
		Cuenta cuenta = cuentaLogica.consultarPorId(cuenId);
		assertNotNull(cuenta, "La cuenta no existe");
		
		
		log.info("Saldo actual cuenta: " + cuenta.getSaldo());
		
		assertNotNull(transaccionesBancariasLogica," La transaccionesBancariasLogica esta vacia");
		
		transaccionesBancariasLogica.retirar(cuenId, valor, usuUsuario);
		
		log.info("Saldo despues de retiro cuenta: " + cuenta.getSaldo());
		
	}
	
	@Disabled
	@Test
	@DisplayName("Retirar error cuenta")
	void cTest() throws Exception {
		
		assertNotNull(usuarioLogica, "El usuarioLogica esta vacio");
		Usuario usuario = usuarioLogica.consultarPorId(usuUsuario);
		assertNotNull(usuario, "El usuario no existe");
		assertNotNull(cuentaLogica, "La cuentaLogica esta vacio");
		Cuenta cuenta = cuentaLogica.consultarPorId("1111-2222-3333-4444");
		assertNotNull(cuenta, "La cuenta no existe");
		
		
		log.info("Saldo actual cuenta: " + cuenta.getSaldo());
		
		assertNotNull(transaccionesBancariasLogica," La transaccionesBancariasLogica esta vacia");
		
		transaccionesBancariasLogica.retirar(cuenId, valor, usuUsuario);
		
		log.info("Saldo despues de retiro cuenta: " + cuenta.getSaldo());
		
	}
	
	
	@Test
	@DisplayName("Retirar error valor > 0")
	void eTest() throws Exception {
		BigDecimal valor2 = new BigDecimal(999999999);
		assertNotNull(usuarioLogica, "El usuarioLogica esta vacio");
		Usuario usuario = usuarioLogica.consultarPorId(usuUsuario);
		assertNotNull(usuario, "El usuario no existe");
		assertNotNull(cuentaLogica, "La cuentaLogica esta vacio");
		Cuenta cuenta = cuentaLogica.consultarPorId(cuenId);
		assertNotNull(cuenta, "La cuenta no existe");
		
		
		log.info("Saldo actual cuenta: " + cuenta.getSaldo());
		
		assertNotNull(transaccionesBancariasLogica," La transaccionesBancariasLogica esta vacia");
		
		transaccionesBancariasLogica.retirar(cuenId, valor2, usuUsuario);
		
		log.info("Saldo despues de retiro cuenta: " + cuenta.getSaldo());
		
	}
	
	@Disabled
	@Test
	@DisplayName("Retirar error valor = 0")
	void dTest() throws Exception {
		BigDecimal valor2 = new BigDecimal(0);
		assertNotNull(usuarioLogica, "El usuarioLogica esta vacio");
		Usuario usuario = usuarioLogica.consultarPorId(usuUsuario);
		assertNotNull(usuario, "El usuario no existe");
		assertNotNull(cuentaLogica, "La cuentaLogica esta vacio");
		Cuenta cuenta = cuentaLogica.consultarPorId(cuenId);
		assertNotNull(cuenta, "La cuenta no existe");
		
		
		log.info("Saldo actual cuenta: " + cuenta.getSaldo());
		
		assertNotNull(transaccionesBancariasLogica," La transaccionesBancariasLogica esta vacia");
		
		transaccionesBancariasLogica.retirar(cuenId, valor2, usuUsuario);
		
		log.info("Saldo despues de retiro cuenta: " + cuenta.getSaldo());
		
	}
	
	@Disabled
	@Test
	@DisplayName("Consignar")
	void Test() throws Exception {
		assertNotNull(usuarioLogica, "El usuarioLogica esta vacio");
		Usuario usuario = usuarioLogica.consultarPorId(usuUsuario);
		assertNotNull(usuario, "El usuario no existe");
		assertNotNull(cuentaLogica, "La cuentaLogica esta vacio");
		Cuenta cuenta = cuentaLogica.consultarPorId(cuenId);
		assertNotNull(cuenta, "La cuenta no existe");
		
		
		log.info("Saldo actual cuenta: " + cuenta.getSaldo());
		
		assertNotNull(transaccionesBancariasLogica," La transaccionesBancariasLogica esta vacia");
		
		transaccionesBancariasLogica.consignar(cuenId, valor, usuUsuario);
		
		log.info("Saldo despues de retiro cuenta: " + cuenta.getSaldo());
	}

}
