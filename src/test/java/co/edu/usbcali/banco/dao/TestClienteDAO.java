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
import co.edu.usbcali.banco.modelo.TipoDocumento;



@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContext.xml")
@Rollback(false)
class TestClienteDAO {
	
	private final static Logger log = LoggerFactory.getLogger(TestClienteDAO.class);
	
	@Autowired
	private IClienteDAO clienteDAO;
	
	@Autowired
	private ITipoDocumentoDAO tipoDocumentoDAO;
	
	static BigDecimal clieId=new BigDecimal(142020);

	@Test
	@DisplayName("CrearCliente")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void aTest() {
		assertNotNull(clienteDAO,"El clienteDAO esta nulo");
		Cliente cliente=clienteDAO.consultarPorId(clieId);
		assertNull(cliente,"El cliente ya existe");
		
		cliente=new Cliente();
		cliente.setActivo('S');
		cliente.setClieId(clieId);
		cliente.setDireccion("Avenida Siempre Viva 123");
		cliente.setEmail("hsimpson@gmail.com");
		cliente.setNombre("Homer J Simpson");
		cliente.setTelefono("555-5555-555");
		TipoDocumento tipoDocumento=tipoDocumentoDAO.consultarPorId(3L);
		assertNotNull(tipoDocumento,"El tipo de documento es Nulo o no Existe");
		cliente.setTipoDocumento(tipoDocumento);
		
		clienteDAO.grabar(cliente);
	}
	
	@Test
	@DisplayName("ModificarCliente")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void cTest() {
		assertNotNull(clienteDAO,"El clienteDAO esta nulo");
		Cliente cliente=clienteDAO.consultarPorId(clieId);
		assertNotNull(cliente,"El cliente no existe");
		
		cliente.setActivo('S');
		cliente.setDireccion("Avenida Siempre Viva 123");
		cliente.setEmail("hsimpson@gmail.com");
		cliente.setNombre("Homer J Simpson M");
		cliente.setTelefono("555-5555-555");
		TipoDocumento tipoDocumento=tipoDocumentoDAO.consultarPorId(1L);
		assertNotNull(tipoDocumento,"El tipo de documento es Nulo o no Existe");
		cliente.setTipoDocumento(tipoDocumento);
		
		clienteDAO.modificar(cliente);
	}
	
	@Test
	@DisplayName("BorrarCliente")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void dTest() {
		assertNotNull(clienteDAO,"El clienteDAO esta nulo");
		Cliente cliente=clienteDAO.consultarPorId(clieId);
		assertNotNull(cliente,"El cliente no existe");
		
		clienteDAO.borrar(cliente);
	}
	
	
	@Test
	@DisplayName("ConsultarCliente")
	@Transactional(readOnly=true)
	public void bTest() {
		assertNotNull(clienteDAO);
		Cliente cliente=clienteDAO.consultarPorId(clieId);
		assertNotNull(cliente);
		log.info(cliente.getNombre());
		log.info(cliente.getEmail());
	}
	
	@Test
	@DisplayName("Consultar Todos Los Cliente")
	@Transactional(readOnly=true)
	public void eTest() {
		assertNotNull(clienteDAO);
		List<Cliente> losCliente=clienteDAO.consultarTodos();
		losCliente.forEach(cliente->{
			log.info(cliente.getNombre());
			log.info(cliente.getEmail());
		});
		
	}
		

}
