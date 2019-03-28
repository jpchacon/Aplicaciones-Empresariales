package co.edu.usbcali.banco.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.modelo.CuentaRegistrada;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContext.xml")
@Rollback(false)
class TestCuentaRegistradaDAO {
	
	private final static Logger log = LoggerFactory.getLogger(TestCuentaRegistradaDAO.class);
	
	@Autowired
	private ICuentaRegistradaDAO cuentaRegistradaDAO;
	
	@Autowired
	private ICuentaDAO cuentaDao;
	
	@Autowired
	private IClienteDAO clienteDAO;
	
	static long cureId = 1;
	static String cuenId = new String("0000-6776-1365-3228");
	static BigDecimal clieId = new BigDecimal(33);
	
	
	@Test
	@DisplayName("Crear Cuenta Registrada")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void aTest() {
		
		assertNotNull(cuentaRegistradaDAO,"La cuentaRegistradaDAO esta nulo");
		CuentaRegistrada cuentaRegistrada = cuentaRegistradaDAO.consultarPorId(cureId);
		assertNull(cuentaRegistrada,"La cuentaRegistrada ya existe");
		
		cuentaRegistrada = new CuentaRegistrada();
		cuentaRegistrada.setCureId(cureId);
		
		Cliente cliente =  clienteDAO.consultarPorId(clieId);
		assertNotNull(cliente,"El cliente esta nulo");
		
		cuentaRegistrada.setCliente(cliente);
		
		Cuenta cuenta = cuentaDao.consultarPorId(cuenId);
		assertNotNull(cuenta, "La cuenta esta nula");
		
		cuentaRegistrada.setCuenta(cuenta);
		
		cuentaRegistradaDAO.grabar(cuentaRegistrada);
	}
	
	@Test
	@DisplayName("Modificar Cuenta Registrada")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void cTest() {
		assertNotNull(cuentaRegistradaDAO,"cuentaRegistradaDAO esta nulo");
		CuentaRegistrada cuentaRegistrada = cuentaRegistradaDAO.consultarPorId(cureId);
		assertNotNull(cuentaRegistrada,"La cuentaRegistrada no existe");
		
		Cliente cliente =  clienteDAO.consultarPorId(new BigDecimal(55));
		assertNotNull(cliente,"El cliente esta nulo");
		
		cuentaRegistrada.setCliente(cliente);
		
		Cuenta cuenta = cuentaDao.consultarPorId("0034-6767-4782-7505");
		assertNotNull(cuenta, "La cuenta esta nula");
		
		cuentaRegistradaDAO.modificar(cuentaRegistrada);
	}
	
	@Test
	@DisplayName("Borrar Cuenta Registrada")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void dTest() {
		assertNotNull(cuentaRegistradaDAO,"La cuentaRegistradaDAO esta nulo");
		CuentaRegistrada cuentaRegistrada = cuentaRegistradaDAO.consultarPorId(cureId);
		assertNotNull(cuentaRegistrada,"La cuenta no existe");
		
		cuentaRegistradaDAO.borrar(cuentaRegistrada);
	}

	@Test
	@DisplayName("Consultar Cuenta Registrada por ID")
	@Transactional(readOnly=true)
	public void bTest() {
		assertNotNull(cuentaRegistradaDAO);
		CuentaRegistrada cuentaRegistrada = cuentaRegistradaDAO.consultarPorId(cureId);
		assertNotNull(cuentaRegistrada);
		
		log.info("Nombre del cliente: " + cuentaRegistrada.getCliente().getNombre());
		log.info("Cuenta Registrada: " + cuentaRegistrada.getCuenta().getCuenId());
	}
	
	@Test
	@DisplayName("Consultar Todos Las Cuentas Registradas")
	@Transactional(readOnly=true)
	public void eTest() {
		assertNotNull(cuentaRegistradaDAO);
		List<CuentaRegistrada> lasCuentasRegistradas = cuentaRegistradaDAO.consultarTodos();
		lasCuentasRegistradas.forEach(cuentaR->{
			log.info("Nombre del cliente: " + cuentaR.getCliente().getNombre());
			log.info("Cuenta Registrada: " + cuentaR.getCuenta().getCuenId());
		});
		
	}

}
