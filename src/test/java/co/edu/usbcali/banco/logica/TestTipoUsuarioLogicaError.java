package co.edu.usbcali.banco.logica;

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
class TestTipoUsuarioLogicaError {

	private final static Logger log = LoggerFactory.getLogger(TestTipoUsuarioLogicaError.class);
	
	@Autowired
	private ITipoUsuarioLogica tipoUsuarioLogica;
	
	
	static long tiusId = 4;
	
	
	@Test
	@DisplayName("Crear Tipo Usuario")
	public void aTest() throws Exception {
		assertNotNull(tipoUsuarioLogica,"El tipoUsuarioLogica esta nulo");
		TipoUsuario tipoUsuario = tipoUsuarioLogica.consultarPorId(tiusId);
		assertNull(tipoUsuario,"El tipoUsuario ya existe");
		
		tipoUsuario=new TipoUsuario();
		tipoUsuario.setTiusId(tiusId);
		tipoUsuario.setNombre("C");
		tipoUsuario.setActivo('S');
		
		tipoUsuarioLogica.grabar(tipoUsuario);
	}
	
	@Test
	@DisplayName("Modificar Tipo Usuario")
	public void cTest() throws Exception {
		assertNotNull(tipoUsuarioLogica,"El tipoUsuarioLogica esta nulo");
		TipoUsuario tipoUsuario = tipoUsuarioLogica.consultarPorId(tiusId);
		assertNotNull(tipoUsuario,"El tipoUsuario no existe");
		
		tipoUsuario.setNombre("SOPORTE");
		tipoUsuario.setActivo('S');
		
		tipoUsuarioLogica.modificar(tipoUsuario);
	}
	
	@Test
	@DisplayName("Borrar Tipo Usuario")
	public void dTest() throws Exception {
		assertNotNull(tipoUsuarioLogica,"El tipoUsuarioLogica esta nulo");
		TipoUsuario tipoUsuario = tipoUsuarioLogica.consultarPorId(tiusId);
		assertNotNull(tipoUsuario,"El tipoUsuario no existe");
		
		tipoUsuarioLogica.borrar(tipoUsuario);
	}
	
	
	@Test
	@DisplayName("Consultar Tipo Usuario")
	public void bTest() {
		assertNotNull(tipoUsuarioLogica);
		TipoUsuario tipoUsuario = tipoUsuarioLogica.consultarPorId(tiusId);
		assertNotNull(tipoUsuario);
		log.info("id: " + tipoUsuario.getTiusId());
		log.info("Nombre: " + tipoUsuario.getNombre());
		log.info("Estado: " + tipoUsuario.getActivo());
	}
	
	
	@Test
	@DisplayName("Consultar Todos Los Tipo Usuario")
	public void eTest() {
		assertNotNull(tipoUsuarioLogica);
		List<TipoUsuario> losTipoUsuario=tipoUsuarioLogica.consultarTodos();
		losTipoUsuario.forEach(tipoUsuario->{
			log.info("id: " + tipoUsuario.getTiusId());
			log.info("Nombre: " + tipoUsuario.getNombre());
			log.info("Estado: " + tipoUsuario.getActivo());
		});
		
	}

}
