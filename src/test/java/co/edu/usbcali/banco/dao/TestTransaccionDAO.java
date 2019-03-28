package co.edu.usbcali.banco.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Date;
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
import co.edu.usbcali.banco.modelo.TipoTransaccion;
import co.edu.usbcali.banco.modelo.TipoUsuario;
import co.edu.usbcali.banco.modelo.Transaccion;
import co.edu.usbcali.banco.modelo.Usuario;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContext.xml")
@Rollback(false)
class TestTransaccionDAO {
	
	
	private final static Logger log = LoggerFactory.getLogger(TestTransaccionDAO.class);
	
	@Autowired
	private ITransaccionDAO transaccionDAO;
	
	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	@Autowired
	private ICuentaDAO cuentaDao;
	
	@Autowired
	private ITipoTransaccionDAO tipoTransaccionDAO;
	
	static long tranId = 3001;
	static String cuenId = new String("4640-0341-9387-5781");
	static long titrId = 1;
	static String usuUsuario = "aadamoco";
	
	@Test
	@DisplayName("Crear Transaccion")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void aTest() {
		assertNotNull(transaccionDAO,"El transaccionDAO esta nulo");
		Transaccion transaccion = transaccionDAO.consultarPorId(tranId);
		assertNull(transaccion,"La transaccionDAO ya existe");
		
		transaccion=new Transaccion();
		transaccion.setTranId(tranId);
		
		Cuenta cuenta = cuentaDao.consultarPorId(cuenId);
		assertNotNull(cuenta);
		transaccion.setCuenta(cuenta);
		
		TipoTransaccion tipoTransaccion = tipoTransaccionDAO.consultarPorId(titrId);
		assertNotNull(tipoTransaccion);
		transaccion.setTipoTransaccion(tipoTransaccion);
		
		Usuario usuario = usuarioDAO.consultarPorId(usuUsuario);
		assertNotNull(usuario);
		transaccion.setUsuario(usuario);
		
		transaccion.setValor(new BigDecimal(199999));
		Date fecha = new Date();
		transaccion.setFecha(fecha);
		
		
		transaccionDAO.grabar(transaccion);
	}
	
	@Test
	@DisplayName("Modificar Transaccion")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void cTest() {
		assertNotNull(transaccionDAO,"La transaccionDAO esta nulo");
		Transaccion transaccion = transaccionDAO.consultarPorId(tranId);
		assertNotNull(transaccion,"La transaccion no existe");
		
		Cuenta cuenta = cuentaDao.consultarPorId("0034-6767-4782-7505");
		assertNotNull(cuenta);
		transaccion.setCuenta(cuenta);
		
		TipoTransaccion tipoTransaccion = tipoTransaccionDAO.consultarPorId(3);
		assertNotNull(tipoTransaccion);
		transaccion.setTipoTransaccion(tipoTransaccion);
		
		Usuario usuario = usuarioDAO.consultarPorId("aanlaynu");
		assertNotNull(usuario);
		transaccion.setUsuario(usuario);
		
		transaccion.setValor(new BigDecimal(20258));
		Date fecha = new Date();
		transaccion.setFecha(fecha);
		
		transaccionDAO.modificar(transaccion);
	}
	
	@Test
	@DisplayName("Borrar Transaccion")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void dTest() {
		assertNotNull(transaccionDAO,"La transaccionDAO esta nulo");
		Transaccion transaccion = transaccionDAO.consultarPorId(tranId);
		assertNotNull(transaccion,"La transaccion no existe");
		
		transaccionDAO.borrar(transaccion);
	}
	
	@Test
	@DisplayName("Consultar Transaccion")
	@Transactional(readOnly=true)
	public void bTest() {
		assertNotNull(transaccionDAO);
		Transaccion transaccion = transaccionDAO.consultarPorId(tranId);
		assertNotNull(transaccion);
		log.info("Cuenta: " +transaccion.getCuenta().getCuenId());
		log.info("Tipo Transaccion: " +transaccion.getTipoTransaccion().getNombre());
		log.info("Valor: " +transaccion.getValor());
		log.info("Nombre usuario: " +transaccion.getUsuario().getNombre());
	}
	
	@Test
	@DisplayName("Consultar Todos Las Transaccion")
	@Transactional(readOnly=true)
	public void eTest() {
		assertNotNull(transaccionDAO);
		List<Transaccion> lasTransacciones = transaccionDAO.consultarTodos();
		lasTransacciones.forEach(transacciones->{
			log.info("Cuenta: " +transacciones.getCuenta().getCuenId());
			log.info("Tipo Transaccion: " +transacciones.getTipoTransaccion().getNombre());
			log.info("Valor: " +transacciones.getValor());
			log.info("Nombre usuario: " +transacciones.getUsuario().getNombre());
		});
		
	}
	
	@Test
	@DisplayName("Consultar por cuenta y tipo transaccion")
	@Transactional(readOnly=true)
	public void fTest() {
		assertNotNull(transaccionDAO);
		List<Transaccion> lasTransacciones = transaccionDAO.consultarPorCuentaYTipoTransaccion("0000-6776-1365-3228", 1L);
		lasTransacciones.forEach(transacciones->{
			log.info("Cuenta: " +transacciones.getCuenta().getCuenId());
			log.info("Tipo Transaccion: " +transacciones.getTipoTransaccion().getNombre());
			log.info("Valor: " +transacciones.getValor());
			log.info("Nombre usuario: " +transacciones.getUsuario().getNombre());
		});
		
	}

}
