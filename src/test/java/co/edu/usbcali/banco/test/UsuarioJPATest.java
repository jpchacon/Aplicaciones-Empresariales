package co.edu.usbcali.banco.test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.TipoUsuario;
import co.edu.usbcali.banco.modelo.Usuario;

class UsuarioJPATest {
	
	private final static Logger log = LoggerFactory.getLogger(UsuarioJPATest.class);
	
	static EntityManagerFactory entityManagerFactory;
	static EntityManager entityManager;
	static String usuUsuario = new String("alverjas");
	
	
	@Test
	@DisplayName("CrearUsuario")
	public void aTest() {
		assertNotNull(entityManager,"El entityManager esta nulo");
		Usuario usuario=entityManager.find(Usuario.class,usuUsuario);
		assertNull(usuario,"El usuario ya existe");
		
		usuario=new Usuario();
		usuario.setUsuUsuario(usuUsuario);
		
		TipoUsuario tipoUsuario = entityManager.find(TipoUsuario.class,3L);
		assertNotNull(tipoUsuario,"El tipoDeUsuario esta nulo o no existe");
		usuario.setTipoUsuario(tipoUsuario);
		
		usuario.setClave("1234");
		usuario.setIdentificacion(new BigDecimal(1123413));
		usuario.setNombre("Alverjas Jose");
		usuario.setActivo('S');
		
		entityManager.getTransaction().begin();
			entityManager.persist(usuario);
		entityManager.getTransaction().commit();
	}
	
	@Test
	@DisplayName("ConsultarUsuario")
	public void bTest() {
		assertNotNull(entityManager, "El entityManager esta nulo");
		Usuario usuario = entityManager.find(Usuario.class, usuUsuario);
		assertNotNull(usuario);
		
		log.info("Usuario: " + usuario.getUsuUsuario());
		log.info("Clave: " + usuario.getClave());
		log.info("Identificación: " + usuario.getIdentificacion());
		log.info("Nombre: " + usuario.getNombre());
		log.info("Estado: " + usuario.getActivo());
		
	}
	
	
	@Test
	@DisplayName("ModificarUsuario")
	public void cTest() {
		assertNotNull(entityManager,"El entityManager esta nulo");
		Usuario usuario=entityManager.find(Usuario.class,usuUsuario);
		assertNotNull(usuario,"El usuario no existe");
		
		TipoUsuario tipoUsuario = entityManager.find(TipoUsuario.class,1L);
		assertNotNull(tipoUsuario,"El tipoDeUsuario esta nulo o no existe");
		usuario.setTipoUsuario(tipoUsuario);
		
		usuario.setClave("4853");
		usuario.setIdentificacion(new BigDecimal(1123413));
		usuario.setNombre("Alverjas Jose");
		usuario.setActivo('S');
		
		entityManager.getTransaction().begin();
			entityManager.merge(usuario);
		entityManager.getTransaction().commit();
	}
	
	@Test
	@DisplayName("BorrarUsuario")
	public void dTest() {
		assertNotNull(entityManager,"El entityManager esta nulo");
		Usuario usuario=entityManager.find(Usuario.class,usuUsuario);
		assertNotNull(usuario,"El usuario no existe");
		
		entityManager.getTransaction().begin();
			entityManager.remove(usuario);
		entityManager.getTransaction().commit();
	}
	
	
	@BeforeAll
	public static void inicializarTodo() {
		entityManagerFactory=Persistence.createEntityManagerFactory("banco-persitencia");
		entityManager=entityManagerFactory.createEntityManager();
	}	
	@AfterAll
	public static void cerrarTodo() {
		entityManager.close();
		entityManagerFactory.close();
	}
}
