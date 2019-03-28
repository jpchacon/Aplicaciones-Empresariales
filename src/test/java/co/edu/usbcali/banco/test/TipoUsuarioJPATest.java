package co.edu.usbcali.banco.test;

import static org.junit.jupiter.api.Assertions.*;

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

import co.edu.usbcali.banco.modelo.TipoUsuario;

class TipoUsuarioJPATest {
	
	private final static Logger log = LoggerFactory.getLogger(TipoUsuarioJPATest.class);
	
	static EntityManagerFactory entityManagerFactory;
	static EntityManager entityManager;
	long tiusId = new Long(4);
	
	
	@Test
	@DisplayName("CrearTipoUsuario")
	void aTest() {
		assertNotNull(entityManager,"El entityManager esta nulo");
		TipoUsuario tipoUsuario = entityManager.find(TipoUsuario.class, tiusId);
		assertNull(tipoUsuario,"EL usuario ya existe");
		
		tipoUsuario = new TipoUsuario();
		tipoUsuario.setTiusId(tiusId);
		tipoUsuario.setNombre("Soporte tecnico");
		tipoUsuario.setActivo('S');
		
		entityManager.getTransaction().begin();
		entityManager.persist(tipoUsuario);
		entityManager.getTransaction().commit();
	}
	
	@Test
	@DisplayName("ConsultarTipoUsuario")
	void bTest() {
		assertNotNull(entityManager,"El entityManager esta nulo");
		TipoUsuario tipoUsuario = entityManager.find(TipoUsuario.class, tiusId);
		assertNotNull(tipoUsuario);
		log.info(tipoUsuario.getNombre());
		
	}
	
	
	@Test
	@DisplayName("ModificarTipoUsuario")
	void cTest() {
		assertNotNull(entityManager,"El entityManager esta nulo");
		TipoUsuario tipoUsuario = entityManager.find(TipoUsuario.class, tiusId);
		assertNotNull(tipoUsuario,"EL usuario ya existe");
		
		tipoUsuario.setNombre("Guarda espalda");
		tipoUsuario.setActivo('S');
		
		entityManager.getTransaction().begin();
		entityManager.merge(tipoUsuario);
		entityManager.getTransaction().commit();
	}
	
	@Test
	@DisplayName("BorrarTipoUsuario")
	void dTest() {
		assertNotNull(entityManager,"El entityManager esta nulo");
		TipoUsuario tipoUsuario = entityManager.find(TipoUsuario.class, tiusId);
		assertNotNull(tipoUsuario,"EL usuario ya existe");
		
		entityManager.getTransaction().begin();
		entityManager.remove(tipoUsuario);
		entityManager.getTransaction().commit();
	}
	
	
	
	
	
	@BeforeAll
	public static void inicializarTodo() {
		entityManagerFactory = Persistence.createEntityManagerFactory("banco-persitencia");
		entityManager = entityManagerFactory.createEntityManager();
	}

	
	@AfterAll
	public static void cerrarTodo() {
		entityManager.close();
		entityManagerFactory.close();
	}

}
