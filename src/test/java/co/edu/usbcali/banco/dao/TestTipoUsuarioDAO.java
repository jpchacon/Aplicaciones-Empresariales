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
import co.edu.usbcali.banco.modelo.TipoUsuario;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContext.xml")
@Rollback(false)
class TestTipoUsuarioDAO {

	private final static Logger log = LoggerFactory.getLogger(TestTipoUsuarioDAO.class);
	
	@Autowired
	private ITipoUsuarioDAO tipoUsuarioDAO;
	
	static long tiusId = 4;
	
	
	@Test
	@DisplayName("Crear Tipo Usuario")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void aTest() {
		assertNotNull(tipoUsuarioDAO,"El tipoUsuarioDAO esta nulo");
		TipoUsuario tipoUsuario = tipoUsuarioDAO.consultarPorId(tiusId);
		assertNull(tipoUsuario,"El tipoUsuario ya existe");
		
		tipoUsuario=new TipoUsuario();
		tipoUsuario.setTiusId(tiusId);
		tipoUsuario.setNombre("CONTADOR");
		tipoUsuario.setActivo('S');
		
		tipoUsuarioDAO.grabar(tipoUsuario);
	}
	
	@Test
	@DisplayName("Modificar Tipo Usuario")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void cTest() {
		assertNotNull(tipoUsuarioDAO,"El tipoUsuarioDAO esta nulo");
		TipoUsuario tipoUsuario = tipoUsuarioDAO.consultarPorId(tiusId);
		assertNotNull(tipoUsuario,"El tipoUsuario no existe");
		
		tipoUsuario.setNombre("SOPORTE");
		tipoUsuario.setActivo('S');
		
		tipoUsuarioDAO.modificar(tipoUsuario);
	}
	
	@Test
	@DisplayName("Borrar Tipo Usuario")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void dTest() {
		assertNotNull(tipoUsuarioDAO,"El tipoUsuarioDAO esta nulo");
		TipoUsuario tipoUsuario = tipoUsuarioDAO.consultarPorId(tiusId);
		assertNotNull(tipoUsuario,"El tipoUsuario no existe");
		
		tipoUsuarioDAO.borrar(tipoUsuario);
	}
	
	
	@Test
	@DisplayName("Consultar Tipo Usuario")
	@Transactional(readOnly=true)
	public void bTest() {
		assertNotNull(tipoUsuarioDAO);
		TipoUsuario tipoUsuario = tipoUsuarioDAO.consultarPorId(tiusId);
		assertNotNull(tipoUsuario);
		log.info("id: " + tipoUsuario.getTiusId());
		log.info("Nombre: " + tipoUsuario.getNombre());
		log.info("Estado: " + tipoUsuario.getActivo());
	}
	
	
	@Test
	@DisplayName("Consultar Todos Los Tipo Usuario")
	@Transactional(readOnly=true)
	public void eTest() {
		assertNotNull(tipoUsuarioDAO);
		List<TipoUsuario> losTipoUsuario=tipoUsuarioDAO.consultarTodos();
		losTipoUsuario.forEach(tipoUsuario->{
			log.info("id: " + tipoUsuario.getTiusId());
			log.info("Nombre: " + tipoUsuario.getNombre());
			log.info("Estado: " + tipoUsuario.getActivo());
		});
		
	}

}
