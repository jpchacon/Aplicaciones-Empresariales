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

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.TipoDocumento;



@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContext.xml")
@Rollback(false)
class TestClienteLogica {
	
	private final static Logger log = LoggerFactory.getLogger(TestClienteLogica.class);
	
	@Autowired
	private IClienteLogica clienteLogica;
	
	@Autowired
	private ITipoDocumentoLogica tipoDocumentoLogica;
	
	static BigDecimal clieId=new BigDecimal(142020);

	@Test
	@DisplayName("CrearCliente")
	public void aTest() throws Exception {
		assertNotNull(clienteLogica,"El clienteLogica esta nulo");
		Cliente cliente=clienteLogica.consultarPorId(clieId);
		assertNull(cliente,"El cliente ya existe");
		
		cliente=new Cliente();
		cliente.setActivo('S');
		cliente.setClieId(clieId);
		cliente.setDireccion("Avenida Siempre Viva 123");
		cliente.setEmail("hsimpson@gmail.com");
		cliente.setNombre("Homer J Simpson");
		cliente.setTelefono("555-5555-555");
		TipoDocumento tipoDocumento=tipoDocumentoLogica.consultarPorId(3L);
		assertNotNull(tipoDocumento,"El tipo de documento es Nulo o no Existe");
		cliente.setTipoDocumento(tipoDocumento);
		
		clienteLogica.grabar(cliente);
	}
	
	@Test
	@DisplayName("ModificarCliente")
	public void cTest() throws Exception {
		assertNotNull(clienteLogica,"El clienteLogica esta nulo");
		Cliente cliente=clienteLogica.consultarPorId(clieId);
		assertNotNull(cliente,"El cliente no existe");
		
		cliente.setActivo('S');
		cliente.setDireccion("Avenida Siempre Viva 123");
		cliente.setEmail("hsimpson@gmail.com");
		cliente.setNombre("Homer J Simpson M");
		cliente.setTelefono("555-5555-555");
		TipoDocumento tipoDocumento=tipoDocumentoLogica.consultarPorId(1L);
		assertNotNull(tipoDocumento,"El tipo de documento es Nulo o no Existe");
		cliente.setTipoDocumento(tipoDocumento);
		
		clienteLogica.modificar(cliente);
	}
	
	@Test
	@DisplayName("BorrarCliente")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void dTest() throws Exception {
		assertNotNull(clienteLogica,"El clienteDAO esta nulo");
		Cliente cliente=clienteLogica.consultarPorId(clieId);
		assertNotNull(cliente,"El cliente no existe");
		
		clienteLogica.borrar(cliente);
	}
	
	
	@Test
	@DisplayName("ConsultarCliente")
	@Transactional(readOnly=true)
	public void bTest() {
		assertNotNull(clienteLogica);
		Cliente cliente=clienteLogica.consultarPorId(clieId);
		assertNotNull(cliente);
		log.info(cliente.getNombre());
		log.info(cliente.getEmail());
	}
	
	@Test
	@DisplayName("Consultar Todos Los Cliente")
	@Transactional(readOnly=true)
	public void eTest() {
		assertNotNull(clienteLogica);
		List<Cliente> losCliente=clienteLogica.consultarTodos();
		losCliente.forEach(cliente->{
			log.info(cliente.getNombre());
			log.info(cliente.getEmail());
		});
		
	}
		

}
