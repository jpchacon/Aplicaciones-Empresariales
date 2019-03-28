package co.edu.usbcali.banco.dao;

import static org.junit.jupiter.api.Assertions.*;

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
import co.edu.usbcali.banco.modelo.TipoDocumento;
import co.edu.usbcali.banco.modelo.TipoTransaccion;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContext.xml")
@Rollback(false)
class TestTipoTransaccionDAO {
	
	private final static Logger log = LoggerFactory.getLogger(TestTipoTransaccionDAO.class);

	@Autowired
	private ITipoTransaccionDAO tipoTransaccionDAO;
	
	static long titrId = 4;
	
	
	@Test
	@DisplayName("Crear Tipo Transaccion")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void aTest() {
		assertNotNull(tipoTransaccionDAO,"El tipoTransaccionDAO esta nulo");
		TipoTransaccion tipoTransaccion = tipoTransaccionDAO.consultarPorId(titrId);
		assertNull(tipoTransaccion,"El tipoTransaccion ya existe");
		
		tipoTransaccion=new TipoTransaccion();
		tipoTransaccion.setTitrId(titrId);
		tipoTransaccion.setNombre("Cambio a Bitcoin");
		tipoTransaccion.setActivo('S');
		
		tipoTransaccionDAO.grabar(tipoTransaccion);
	}
	
	@Test
	@DisplayName("Modificar Tipo Transaccion")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void cTest() {
		assertNotNull(tipoTransaccionDAO,"El tipoTransaccionDAO esta nulo");
		TipoTransaccion tipoTransaccion = tipoTransaccionDAO.consultarPorId(titrId);
		assertNotNull(tipoTransaccion,"El tipoTransaccion no existe");
		
		tipoTransaccion.setNombre("Cambio a Peercoin");
		tipoTransaccion.setActivo('S');
		
		tipoTransaccionDAO.modificar(tipoTransaccion);
	}
	
	@Test
	@DisplayName("Borrar Tipo Transaccion")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void dTest() {
		assertNotNull(tipoTransaccionDAO,"El clienteDAO esta nulo");
		TipoTransaccion tipoTransaccion = tipoTransaccionDAO.consultarPorId(titrId);
		assertNotNull(tipoTransaccion,"El cliente no existe");
		
		tipoTransaccionDAO.borrar(tipoTransaccion);
	}
	
	@Test
	@DisplayName("Consultar Tipo Transaccion")
	@Transactional(readOnly=true)
	public void bTest() {
		assertNotNull(tipoTransaccionDAO);
		TipoTransaccion tipoTransaccion = tipoTransaccionDAO.consultarPorId(titrId);
		assertNotNull(tipoTransaccion);
		log.info("Id: " + tipoTransaccion.getTitrId());
		log.info("Nombre: " + tipoTransaccion.getNombre());
		log.info("Estado: " + tipoTransaccion.getActivo());
	}
	
	@Test
	@DisplayName("Consultar Todos Los Tipo Transaccion")
	@Transactional(readOnly=true)
	public void eTest() {
		assertNotNull(tipoTransaccionDAO);
		List<TipoTransaccion> losTipoTransaccion=tipoTransaccionDAO.consultarTodos();
		losTipoTransaccion.forEach(tipoTra->{
			log.info("Id: " + tipoTra.getTitrId());
			log.info("Nombre: " + tipoTra.getNombre());
			log.info("Estado: " + tipoTra.getActivo());
		});
		
	}

}
