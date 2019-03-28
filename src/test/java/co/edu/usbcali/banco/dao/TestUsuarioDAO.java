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

import co.edu.usbcali.banco.modelo.TipoUsuario;
import co.edu.usbcali.banco.modelo.Usuario;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContext.xml")
@Rollback(false)
class TestUsuarioDAO {
	
	private final static Logger log = LoggerFactory.getLogger(TestUsuarioDAO.class);

	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	
	@Autowired
	private ITipoUsuarioDAO tipoUsuarioDAO;
	
	
	static String usuUsuario = "Petro";
	static long tiusId = 1;
	
	
	@Test
	@DisplayName("Crear Usuario")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void aTest() {
		assertNotNull(usuarioDAO,"El usuarioDAO esta nulo");
		Usuario usuario = usuarioDAO.consultarPorId(usuUsuario);
		assertNull(usuario,"El usuario ya existe");
		
		usuario=new Usuario();
		usuario.setUsuUsuario(usuUsuario);
		
		TipoUsuario tipoUsuario = tipoUsuarioDAO.consultarPorId(tiusId);
		assertNotNull(tipoUsuario);
		usuario.setTipoUsuario(tipoUsuario);
		
		usuario.setClave("Hola_mundo!");
		usuario.setIdentificacion(new BigDecimal(11421155));
		usuario.setNombre("Pepito perez");
		usuario.setActivo('S');
		
		usuarioDAO.grabar(usuario);
	}
	
	@Test
	@DisplayName("Modificar Usuario")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void cTest() {
		assertNotNull(usuarioDAO,"El usuarioDAO esta nulo");
		Usuario usuario = usuarioDAO.consultarPorId(usuUsuario);
		assertNotNull(usuario,"El usuario no existe");
		
		TipoUsuario tipoUsuario = tipoUsuarioDAO.consultarPorId(2);
		assertNotNull(tipoUsuario);
		usuario.setTipoUsuario(tipoUsuario);
		
		usuario.setClave("Hola_worl!");
		usuario.setIdentificacion(new BigDecimal(65465465));
		usuario.setNombre("Manuelito");
		usuario.setActivo('S');
		
		usuarioDAO.modificar(usuario);
	}
	
	@Test
	@DisplayName("Borrar Usuario")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void dTest() {
		assertNotNull(usuarioDAO,"El usuarioDAO esta nulo");
		Usuario usuario = usuarioDAO.consultarPorId(usuUsuario);
		assertNotNull(usuario,"El usuario no existe");
		
		usuarioDAO.borrar(usuario);
	}
	
	@Test
	@DisplayName("Consultar Usuario")
	@Transactional(readOnly=true)
	public void bTest() {
		assertNotNull(usuarioDAO);
		Usuario usuario = usuarioDAO.consultarPorId(usuUsuario);
		assertNotNull(usuario);
		log.info("Nombre: " + usuario.getNombre());
		log.info("Tipo de usuario: " + usuario.getTipoUsuario().getNombre());
		log.info("Identificacion: " + usuario.getIdentificacion());
	}
	
	@Test
	@DisplayName("Consultar Todos Los Usuario")
	@Transactional(readOnly=true)
	public void eTest() {
		assertNotNull(usuarioDAO);
		List<Usuario> losUsuarios=usuarioDAO.consultarTodos();
		losUsuarios.forEach(usuarios->{
			log.info("Nombre: " + usuarios.getNombre());
			log.info("Tipo de usuario: " + usuarios.getTipoUsuario().getNombre());
			log.info("Identificacion: " + usuarios.getIdentificacion());
		});
		
	}

}
