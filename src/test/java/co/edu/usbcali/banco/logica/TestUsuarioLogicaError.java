package co.edu.usbcali.banco.logica;

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
class TestUsuarioLogicaError {
	
	private final static Logger log = LoggerFactory.getLogger(TestUsuarioLogicaError.class);

	@Autowired
	private IUsuarioLogica usuarioLogica;
	
	@Autowired
	private ITipoUsuarioLogica tipoUsuarioLogica;
	
	
	static String usuUsuario = "Petro";
	static long tiusId = 1;
	
	
	@Test
	@DisplayName("Crear Usuario")
	public void aTest() throws Exception {
		assertNotNull(usuarioLogica,"El usuarioLogica esta nulo");
		Usuario usuario = usuarioLogica.consultarPorId(usuUsuario);
		assertNull(usuario,"El usuario ya existe");
		
		usuario=new Usuario();
		usuario.setUsuUsuario(usuUsuario);
		
		TipoUsuario tipoUsuario = tipoUsuarioLogica.consultarPorId(tiusId);
		assertNotNull(tipoUsuario);
		usuario.setTipoUsuario(tipoUsuario);
		
		usuario.setClave("123456789");
		usuario.setIdentificacion(new BigDecimal(123));
		usuario.setNombre("Pepito perez");
		usuario.setActivo('S');
		
		usuarioLogica.grabar(usuario);
	}
	
	@Test
	@DisplayName("Modificar Usuario")
	public void cTest() throws Exception {
		assertNotNull(usuarioLogica,"El usuarioDAO esta nulo");
		Usuario usuario = usuarioLogica.consultarPorId(usuUsuario);
		assertNotNull(usuario,"El usuario no existe");
		
		TipoUsuario tipoUsuario = tipoUsuarioLogica.consultarPorId(2);
		assertNotNull(tipoUsuario);
		usuario.setTipoUsuario(tipoUsuario);
		
		usuario.setClave("Hola_worl!");
		usuario.setIdentificacion(new BigDecimal(65465465));
		usuario.setNombre("Manuelito");
		usuario.setActivo('S');
		
		usuarioLogica.modificar(usuario);
	}
	
	@Test
	@DisplayName("Borrar Usuario")
	public void dTest() throws Exception {
		assertNotNull(usuarioLogica,"El usuarioDAO esta nulo");
		Usuario usuario = usuarioLogica.consultarPorId(usuUsuario);
		assertNotNull(usuario,"El usuario no existe");
		
		usuarioLogica.borrar(usuario);
	}
	
	@Test
	@DisplayName("Consultar Usuario")
	public void bTest() {
		assertNotNull(usuarioLogica);
		Usuario usuario = usuarioLogica.consultarPorId(usuUsuario);
		assertNotNull(usuario);
		log.info("Nombre: " + usuario.getNombre());
		log.info("Identificacion: " + usuario.getIdentificacion());
		
		TipoUsuario tipoUsuario = tipoUsuarioLogica.consultarPorId(usuario.getTipoUsuario().getTiusId());
		log.info("Tipo de usuario: " + tipoUsuario.getNombre());
		
	}
	
	@Test
	@DisplayName("Consultar Todos Los Usuario")
	public void eTest() {
		assertNotNull(usuarioLogica);
		List<Usuario> losUsuarios=usuarioLogica.consultarTodos();
		
		losUsuarios.forEach(usuarios->{
			log.info("Nombre: " + usuarios.getNombre());
			TipoUsuario tipoUsuario = tipoUsuarioLogica.consultarPorId(usuarios.getTipoUsuario().getTiusId());
			log.info("Tipo de usuario: " + tipoUsuario.getNombre());
			
			log.info("Identificacion: " + usuarios.getIdentificacion());
		});
		
		
		
	}

}
