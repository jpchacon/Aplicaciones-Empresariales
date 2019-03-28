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
import co.edu.usbcali.banco.modelo.TipoDocumento;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContext.xml")
@Rollback(false)
class TestCuentaDAO {

	private final static Logger log = LoggerFactory.getLogger(TestCuentaDAO.class);
	
	@Autowired
	private ICuentaDAO cuentaDao;
	
	@Autowired
	private IClienteDAO clienteDAO;
	
	static String cuenId = new String("1095-0706-1934-1991");
	static BigDecimal clieId = new BigDecimal(33);
	
	@Test
	@DisplayName("Crear Cuenta")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void aTest() {
		
		assertNotNull(cuentaDao,"El cuentaDao esta nulo");
		Cuenta cuenta=cuentaDao.consultarPorId(cuenId);
		assertNull(cuenta,"El cliente ya existe");
		
		cuenta=new Cuenta();
		cuenta.setCuenId(cuenId);
		Cliente cliente =  clienteDAO.consultarPorId(clieId);
		assertNotNull(cliente,"El cliente esta nulo");
		cuenta.setCliente(cliente);
		cuenta.setSaldo(new BigDecimal(1235485));
		cuenta.setClave("Pepe_mujica");
		cuenta.setActiva('S');
		
		cuentaDao.grabar(cuenta);
	}
	
	@Test
	@DisplayName("Modificar Cuenta")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void cTest() {
		assertNotNull(cuentaDao,"El cuentaDao esta nulo");
		Cuenta cuenta=cuentaDao.consultarPorId(cuenId);
		assertNotNull(cuenta,"La cuenta no existe");
		
		Cliente cliente =  clienteDAO.consultarPorId(clieId);
		assertNotNull(cliente,"El cliente esta nulo");
		cuenta.setCliente(cliente);
		cuenta.setSaldo(new BigDecimal(99854205));
		cuenta.setClave("NoEstaMal");
		cuenta.setActiva('S');
		
		cuentaDao.modificar(cuenta);
	}
	
	@Test
	@DisplayName("Borrar Cuenta")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void dTest() {
		assertNotNull(cuentaDao,"El clienteDAO esta nulo");
		Cuenta cuenta=cuentaDao.consultarPorId(cuenId);
		assertNotNull(cuenta,"La cuenta no existe");
		
		cuentaDao.borrar(cuenta);
	}
	
	@Test
	@DisplayName("Consultar Cuenta por ID")
	@Transactional(readOnly=true)
	public void bTest() {
		assertNotNull(cuentaDao);
		Cuenta cuenta=cuentaDao.consultarPorId(cuenId);
		assertNotNull(cuenta);
		log.info("Dueño de la ceunta: " + cuenta.getCliente().getNombre());
		log.info("Número de cuenta: " + cuenta.getCuenId());
	}
	
	@Test
	@DisplayName("Consultar Todos Las Cuentas")
	@Transactional(readOnly=true)
	public void eTest() {
		assertNotNull(cuentaDao);
		List<Cuenta> lasCuentas = cuentaDao.consultarTodos();
		lasCuentas.forEach(cuenta->{
			log.info("Dueño de la ceunta: " + cuenta.getCliente().getNombre());
			log.info("Número de cuenta: " + cuenta.getCuenId());
		});
		
	}

}
