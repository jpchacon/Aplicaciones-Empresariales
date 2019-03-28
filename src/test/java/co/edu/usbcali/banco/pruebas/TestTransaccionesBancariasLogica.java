package co.edu.usbcali.banco.pruebas;

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

import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;

import co.edu.usbcali.banco.logica.ICuentaLogica;
import co.edu.usbcali.banco.logica.ITransaccionesBancariasLogica;
import co.edu.usbcali.banco.logica.IUsuarioLogica;
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
	
	
	/**
	 * 	static String usuUsuario = "aaizikovitz9q";
	 *	static String cuenId = "4640-0341-9387-5781";
	 *	static BigDecimal valor = new BigDecimal(330000);
	 */
	
	
	@Disabled
	@Test
	@DisplayName("Caso número 1")
	void aTest() throws Exception {
		String usuario = "banco";
		String cuenOrigen = "0000-6776-1365-3228";
		String cuenDestino = "0031-0825-4207-7451";
		BigDecimal valor = new BigDecimal(100000);
		
		
		Cuenta cuentaOrigenTest = cuentaLogica.consultarPorId(cuenOrigen);
		assertNotNull(cuentaOrigenTest, "La cuenta origen no existe");
		
		
		Cuenta cuentaDestinoTest = cuentaLogica.consultarPorId(cuenDestino);
		assertNotNull(cuentaDestinoTest, "La cuenta destino no existe");
		
		
		log.info("Saldo actual cuenta origen: " + cuentaOrigenTest.getSaldo());
		log.info("Saldo actual cuenta destino: " + cuentaDestinoTest.getSaldo());
		
		
		transaccionesBancariasLogica.transferencia(cuentaOrigenTest.getCuenId(), cuentaDestinoTest.getCuenId(), valor, usuario);
		
		Cuenta cuentaOrigenTestOut = cuentaLogica.consultarPorId(cuenOrigen);
		assertNotNull(cuentaOrigenTestOut, "La cuenta origen no existe");
		
		
		Cuenta cuentaDestinoTestOut = cuentaLogica.consultarPorId(cuenDestino);
		assertNotNull(cuentaDestinoTestOut, "La cuenta destino no existe");
		
		log.info("Saldo despues de retiro cuenta origen: " + cuentaOrigenTestOut.getSaldo());
		log.info("Saldo despues de retiro cuenta origen: " + cuentaDestinoTestOut.getSaldo());
		
	}
	
	@Disabled
	@Test
	@DisplayName("Caso número 2")
	void bTest() throws Exception {
		String usuario = "banco";
		String cuenOrigen = "bbbb-aaaa-bbbb-aaaa-bbbb";
		String cuenDestino = "0031-0825-4207-7451";
		BigDecimal valor = new BigDecimal(10000);
		
		
		Cuenta cuentaOrigenTest = cuentaLogica.consultarPorId(cuenOrigen);
		assertNotNull(cuentaOrigenTest, "La cuenta origen no existe");
		
		
		Cuenta cuentaDestinoTest = cuentaLogica.consultarPorId(cuenDestino);
		assertNotNull(cuentaDestinoTest, "La cuenta destino no existe");
		
		
		log.info("Saldo actual cuenta origen: " + cuentaOrigenTest.getSaldo());
		log.info("Saldo actual cuenta destino: " + cuentaDestinoTest.getSaldo());
		
		
		transaccionesBancariasLogica.transferencia(cuentaOrigenTest.getCuenId(), cuentaDestinoTest.getCuenId(), valor, usuario);
		
		Cuenta cuentaOrigenTestOut = cuentaLogica.consultarPorId(cuenOrigen);
		assertNotNull(cuentaOrigenTestOut, "La cuenta origen no existe");
		
		
		Cuenta cuentaDestinoTestOut = cuentaLogica.consultarPorId(cuenDestino);
		assertNotNull(cuentaDestinoTestOut, "La cuenta destino no existe");
		
		log.info("Saldo despues de retiro cuenta origen: " + cuentaOrigenTestOut.getSaldo());
		log.info("Saldo despues de retiro cuenta origen: " + cuentaDestinoTestOut.getSaldo());
		
	}
	
	@Disabled
	@Test
	@DisplayName("Caso número 3")
	void cTest() throws Exception {
		String usuario = "banco";
		String cuenOrigen = "0031-0825-4207-7451";
		String cuenDestino = "bbbb-aaaa-bbbb-aaaa-bbbb";
		BigDecimal valor = new BigDecimal(5000);
		
		
		Cuenta cuentaOrigenTest = cuentaLogica.consultarPorId(cuenOrigen);
		assertNotNull(cuentaOrigenTest, "La cuenta origen no existe");
		
		
		Cuenta cuentaDestinoTest = cuentaLogica.consultarPorId(cuenDestino);
		assertNotNull(cuentaDestinoTest, "La cuenta destino no existe");
		
		
		log.info("Saldo actual cuenta origen: " + cuentaOrigenTest.getSaldo());
		log.info("Saldo actual cuenta destino: " + cuentaDestinoTest.getSaldo());
		
		
		transaccionesBancariasLogica.transferencia(cuentaOrigenTest.getCuenId(), cuentaDestinoTest.getCuenId(), valor, usuario);
		
		Cuenta cuentaOrigenTestOut = cuentaLogica.consultarPorId(cuenOrigen);
		assertNotNull(cuentaOrigenTestOut, "La cuenta origen no existe");
		
		
		Cuenta cuentaDestinoTestOut = cuentaLogica.consultarPorId(cuenDestino);
		assertNotNull(cuentaDestinoTestOut, "La cuenta destino no existe");
		
		log.info("Saldo despues de retiro cuenta origen: " + cuentaOrigenTestOut.getSaldo());
		log.info("Saldo despues de retiro cuenta origen: " + cuentaDestinoTestOut.getSaldo());
		
	}
	
	@Disabled
	@Test
	@DisplayName("Caso número 4")
	void dTest() throws Exception {
		String usuario = "banco";
		String cuenOrigen = "0000-6776-1365-3228";
		String cuenDestino = "0031-0825-4207-7451";
		BigDecimal valor = new BigDecimal(1000000);
		
		
		Cuenta cuentaOrigenTest = cuentaLogica.consultarPorId(cuenOrigen);
		assertNotNull(cuentaOrigenTest, "La cuenta origen no existe");
		
		
		Cuenta cuentaDestinoTest = cuentaLogica.consultarPorId(cuenDestino);
		assertNotNull(cuentaDestinoTest, "La cuenta destino no existe");
		
		
		log.info("Saldo actual cuenta origen: " + cuentaOrigenTest.getSaldo());
		log.info("Saldo actual cuenta destino: " + cuentaDestinoTest.getSaldo());
		
		
		transaccionesBancariasLogica.transferencia(cuentaOrigenTest.getCuenId(), cuentaDestinoTest.getCuenId(), valor, usuario);
		
		Cuenta cuentaOrigenTestOut = cuentaLogica.consultarPorId(cuenOrigen);
		assertNotNull(cuentaOrigenTestOut, "La cuenta origen no existe");
		
		
		Cuenta cuentaDestinoTestOut = cuentaLogica.consultarPorId(cuenDestino);
		assertNotNull(cuentaDestinoTestOut, "La cuenta destino no existe");
		
		log.info("Saldo despues de retiro cuenta origen: " + cuentaOrigenTestOut.getSaldo());
		log.info("Saldo despues de retiro cuenta origen: " + cuentaDestinoTestOut.getSaldo());
		
	}
	
	@Disabled
	@Test
	@DisplayName("Caso número 5")
	void eTest() throws Exception {
		String usuario = "banco";
		String cuenOrigen = "0000-6776-1365-3228";
		String cuenDestino = "0031-0825-4207-7451";
		BigDecimal valor = new BigDecimal(-100000);
		
		
		Cuenta cuentaOrigenTest = cuentaLogica.consultarPorId(cuenOrigen);
		assertNotNull(cuentaOrigenTest, "La cuenta origen no existe");
		
		
		Cuenta cuentaDestinoTest = cuentaLogica.consultarPorId(cuenDestino);
		assertNotNull(cuentaDestinoTest, "La cuenta destino no existe");
		
		
		log.info("Saldo actual cuenta origen: " + cuentaOrigenTest.getSaldo());
		log.info("Saldo actual cuenta destino: " + cuentaDestinoTest.getSaldo());
		
		
		transaccionesBancariasLogica.transferencia(cuentaOrigenTest.getCuenId(), cuentaDestinoTest.getCuenId(), valor, usuario);
		
		Cuenta cuentaOrigenTestOut = cuentaLogica.consultarPorId(cuenOrigen);
		assertNotNull(cuentaOrigenTestOut, "La cuenta origen no existe");
		
		
		Cuenta cuentaDestinoTestOut = cuentaLogica.consultarPorId(cuenDestino);
		assertNotNull(cuentaDestinoTestOut, "La cuenta destino no existe");
		
		log.info("Saldo despues de retiro cuenta origen: " + cuentaOrigenTestOut.getSaldo());
		log.info("Saldo despues de retiro cuenta origen: " + cuentaDestinoTestOut.getSaldo());
		
	}
	
	@Disabled
	@Test
	@DisplayName("Caso número 6")
	void fTest() throws Exception {
		String usuario = "banco";
		String cuenOrigen = "bbbb-aaaa-bbbb-aaaa-bbbb";
		String cuenDestino = "aaaa-bbbb-aaaa-bbbb";
		BigDecimal valor = new BigDecimal(100000);
		
		
		Cuenta cuentaOrigenTest = cuentaLogica.consultarPorId(cuenOrigen);
		assertNotNull(cuentaOrigenTest, "La cuenta origen no existe");
		
		
		Cuenta cuentaDestinoTest = cuentaLogica.consultarPorId(cuenDestino);
		assertNotNull(cuentaDestinoTest, "La cuenta destino no existe");
		
		
		log.info("Saldo actual cuenta origen: " + cuentaOrigenTest.getSaldo());
		log.info("Saldo actual cuenta destino: " + cuentaDestinoTest.getSaldo());
		
		
		transaccionesBancariasLogica.transferencia(cuentaOrigenTest.getCuenId(), cuentaDestinoTest.getCuenId(), valor, usuario);
		
		Cuenta cuentaOrigenTestOut = cuentaLogica.consultarPorId(cuenOrigen);
		assertNotNull(cuentaOrigenTestOut, "La cuenta origen no existe");
		
		
		Cuenta cuentaDestinoTestOut = cuentaLogica.consultarPorId(cuenDestino);
		assertNotNull(cuentaDestinoTestOut, "La cuenta destino no existe");
		
		log.info("Saldo despues de retiro cuenta origen: " + cuentaOrigenTestOut.getSaldo());
		log.info("Saldo despues de retiro cuenta origen: " + cuentaDestinoTestOut.getSaldo());
		
	}
	
	@Disabled
	@Test
	@DisplayName("Caso número 7")
	void gTest() throws Exception {
		String usuario = "banco";
		String cuenOrigen = "0000-6776-1365-3228";
		String cuenDestino = "0031-0825-4207-7451";
		BigDecimal valor = new BigDecimal(0);
		
		
		Cuenta cuentaOrigenTest = cuentaLogica.consultarPorId(cuenOrigen);
		assertNotNull(cuentaOrigenTest, "La cuenta origen no existe");
		
		
		Cuenta cuentaDestinoTest = cuentaLogica.consultarPorId(cuenDestino);
		assertNotNull(cuentaDestinoTest, "La cuenta destino no existe");
		
		
		log.info("Saldo actual cuenta origen: " + cuentaOrigenTest.getSaldo());
		log.info("Saldo actual cuenta destino: " + cuentaDestinoTest.getSaldo());
		
		
		transaccionesBancariasLogica.transferencia(cuentaOrigenTest.getCuenId(), cuentaDestinoTest.getCuenId(), valor, usuario);
		
		Cuenta cuentaOrigenTestOut = cuentaLogica.consultarPorId(cuenOrigen);
		assertNotNull(cuentaOrigenTestOut, "La cuenta origen no existe");
		
		
		Cuenta cuentaDestinoTestOut = cuentaLogica.consultarPorId(cuenDestino);
		assertNotNull(cuentaDestinoTestOut, "La cuenta destino no existe");
		
		log.info("Saldo despues de retiro cuenta origen: " + cuentaOrigenTestOut.getSaldo());
		log.info("Saldo despues de retiro cuenta origen: " + cuentaDestinoTestOut.getSaldo());
		
	}
	
	
	@Test
	@DisplayName("Caso número 9")
	void iTest() throws Exception {
		String usuario = "banco";
		String cuenOrigen = "9990-7823-1906-7309";
		String cuenDestino = "9999-8370-6668-7190";
		BigDecimal valor = new BigDecimal(100000);
		
		
		Cuenta cuentaOrigenTest = cuentaLogica.consultarPorId(cuenOrigen);
		assertNotNull(cuentaOrigenTest, "La cuenta origen no existe");
		
		
		Cuenta cuentaDestinoTest = cuentaLogica.consultarPorId(cuenDestino);
		assertNotNull(cuentaDestinoTest, "La cuenta destino no existe");
		
		
		log.info("Saldo actual cuenta origen: " + cuentaOrigenTest.getSaldo());
		log.info("Saldo actual cuenta destino: " + cuentaDestinoTest.getSaldo());
		
		
		transaccionesBancariasLogica.transferencia(cuentaOrigenTest.getCuenId(), cuentaDestinoTest.getCuenId(), valor, usuario);
		
		Cuenta cuentaOrigenTestOut = cuentaLogica.consultarPorId(cuenOrigen);
		assertNotNull(cuentaOrigenTestOut, "La cuenta origen no existe");
		
		
		Cuenta cuentaDestinoTestOut = cuentaLogica.consultarPorId(cuenDestino);
		assertNotNull(cuentaDestinoTestOut, "La cuenta destino no existe");
		
		log.info("Saldo despues de retiro cuenta origen: " + cuentaOrigenTestOut.getSaldo());
		log.info("Saldo despues de retiro cuenta origen: " + cuentaDestinoTestOut.getSaldo());
		
	}
	
	@Disabled
	@Test
	@DisplayName("Caso número 10")
	void jTest() throws Exception {
		String usuario = "banco";
		String cuenOrigen = "9990-7823-1906-7309";
		String cuenDestino = "9999-8370-6668-7190";
		BigDecimal valor = new BigDecimal(100000);
		
		
		Cuenta cuentaOrigenTest = cuentaLogica.consultarPorId(cuenOrigen);
		assertNotNull(cuentaOrigenTest, "La cuenta origen no existe");
		
		
		Cuenta cuentaDestinoTest = cuentaLogica.consultarPorId(cuenDestino);
		assertNotNull(cuentaDestinoTest, "La cuenta destino no existe");
		
		
		log.info("Saldo actual cuenta origen: " + cuentaOrigenTest.getSaldo());
		log.info("Saldo actual cuenta destino: " + cuentaDestinoTest.getSaldo());
		
		
		transaccionesBancariasLogica.transferencia(cuentaOrigenTest.getCuenId(), cuentaDestinoTest.getCuenId(), valor, usuario);
		
		Cuenta cuentaOrigenTestOut = cuentaLogica.consultarPorId(cuenOrigen);
		assertNotNull(cuentaOrigenTestOut, "La cuenta origen no existe");
		
		
		Cuenta cuentaDestinoTestOut = cuentaLogica.consultarPorId(cuenDestino);
		assertNotNull(cuentaDestinoTestOut, "La cuenta destino no existe");
		
		log.info("Saldo despues de retiro cuenta origen: " + cuentaOrigenTestOut.getSaldo());
		log.info("Saldo despues de retiro cuenta origen: " + cuentaDestinoTestOut.getSaldo());
		
	}
	
	

}
