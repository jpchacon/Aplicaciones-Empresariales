package co.edu.usbcali.banco.logica;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.banco.dao.ITipoUsuarioDAO;
import co.edu.usbcali.banco.dao.IUsuarioDAO;
import co.edu.usbcali.banco.modelo.TipoUsuario;
import co.edu.usbcali.banco.modelo.Usuario;

@Service
@Scope("singleton")
public class UsuarioLogicaBeanValidator implements IUsuarioLogica{
	
	private Logger log = LoggerFactory.getLogger(UsuarioLogicaBeanValidator.class);
	
	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	@Autowired
	private ITipoUsuarioDAO tipoUsuarioDAO;
	
	@Autowired
	private Validator validator;

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void grabar(Usuario usuario) throws Exception {
		log.info("Inicio el grabado del usuario");
		
		if(usuario == null) {
			throw new Exception("El usuario no puede ser nulo");
		}
		
		validarUsuario(usuario);
		
		if(consultarPorId(usuario.getUsuUsuario()) != null) {
			throw new Exception("El usuario ya existe");
		}
		
		usuarioDAO.grabar(usuario);
		log.info("Finalizo el grabado del usuario");
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void modificar(Usuario usuario) throws Exception {
		log.info("Inicio el modificado del usuario");
		
		if(usuario == null) {
			throw new Exception("El usuario no puede ser nulo");
		}
		
		validarUsuario(usuario);
		
		usuarioDAO.modificar(usuario);
		log.info("Finalizo el modificado del usuario");
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void borrar(Usuario usuario) throws Exception {
		log.info("Inicio el borrado del usuario");
		
		if(usuario == null) {
			throw new Exception("El usuario no puede ser nulo");
		}
		
		validarUsuario(usuario);
		
		Usuario entity = usuarioDAO.consultarPorId(usuario.getUsuUsuario());
		
		usuarioDAO.borrar(entity);
		log.info("Finalizao el borrado del tipo de usuario");
		
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario consultarPorId(String usuUsuario) {
		return usuarioDAO.consultarPorId(usuUsuario);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Usuario> consultarTodos() {
		return usuarioDAO.consultarTodos();
	}
	
	public void validarUsuario(Usuario usuario) throws Exception {
	    try {
	        Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);

	        if (constraintViolations.size() > 0) {
	            StringBuilder strMessage = new StringBuilder();

	            for (ConstraintViolation<Usuario> constraintViolation : constraintViolations) {
	                strMessage.append(constraintViolation.getPropertyPath().toString());
	                strMessage.append(" - ");
	                strMessage.append(constraintViolation.getMessage());
	                strMessage.append(". \n");
	            }

	            throw new Exception(strMessage.toString());
	        }
	    } catch (Exception e) {
	        throw e;
	    }
	}

}
