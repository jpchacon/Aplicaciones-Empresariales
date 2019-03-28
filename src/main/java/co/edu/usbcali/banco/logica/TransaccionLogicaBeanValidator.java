package co.edu.usbcali.banco.logica;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import co.edu.usbcali.banco.dao.ITransaccionDAO;
import co.edu.usbcali.banco.modelo.Transaccion;

@Service
@Scope("singleton")
public class TransaccionLogicaBeanValidator implements ITransaccionLogica {
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private ITransaccionDAO transaccionDAO;

	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void grabar(Transaccion transaccion) throws Exception {
		if(transaccion==null) {
			throw new Exception("El tipo documento no puede ser nulo");
		}
		
		validarTransaccion(transaccion);
		
		Transaccion transaccionValida = transaccionDAO.consultarPorId(transaccion.getTranId());
		
		if(transaccionValida != null) {
			throw new Exception("La transaccion ya existe");
		}
		transaccionDAO.grabar(transaccion);
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void modificar(Transaccion transaccion) throws Exception {
		if(transaccion==null) {
			throw new Exception("La transaccion no puede ser nulo");
		}
		
		validarTransaccion(transaccion);
		transaccionDAO.modificar(transaccion);
		
	}
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void borrar(Transaccion transaccion) throws Exception {
		if(transaccion==null) {
			throw new Exception("El tipo documento no puede ser nulo");
		}
		validarTransaccion(transaccion);
		
		Transaccion entity=transaccionDAO.consultarPorId(transaccion.getTranId());
		
		transaccionDAO.borrar(entity);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Transaccion consultarPorId(long tranId) {
		return transaccionDAO.consultarPorId(tranId);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Transaccion> consultarTodos() {
		
		return transaccionDAO.consultarTodos();
	}
	
	
	public void validarTransaccion(Transaccion  transaccion) throws Exception {
	    try {
	        Set<ConstraintViolation<Transaccion>> constraintViolations = validator.validate(transaccion);

	        if (constraintViolations.size() > 0) {
	            StringBuilder strMessage = new StringBuilder();

	            for (ConstraintViolation<Transaccion> constraintViolation : constraintViolations) {
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

	@Override
	public List<Transaccion> consultarPorCuentaDeTransaccion(String cuendId) {
		return transaccionDAO.consultarPorCuentaDeTransaccion(cuendId);
	}

	@Override
	public List<Transaccion> consultarPorCuentaYTipoTransaccion(String cuendId, Long titrId) {
		return transaccionDAO.consultarPorCuentaYTipoTransaccion(cuendId, titrId);
	}


	
}
