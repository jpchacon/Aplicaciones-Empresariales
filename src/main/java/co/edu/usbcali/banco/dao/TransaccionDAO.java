package co.edu.usbcali.banco.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.banco.modelo.Transaccion;

@Repository
@Scope("singleton")
public class TransaccionDAO implements ITransaccionDAO {

	@PersistenceContext
	private EntityManager entityManager;

	
	@Override
	public void grabar(Transaccion transaccion) {
		entityManager.persist(transaccion);
		
	}

	@Override
	public void modificar(Transaccion transaccion) {
		entityManager.merge(transaccion);
	}

	@Override
	public void borrar(Transaccion transaccion) {
		entityManager.remove(transaccion);
	}

	
	@Override
	public List<Transaccion> consultarTodos() {
		String jpql="SELECT tr FROM Transaccion tr";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public Transaccion consultarPorId(long tranId) {
		return entityManager.find(Transaccion.class, tranId);
	}

	@Override
	public List<Transaccion> consultarPorCuentaDeTransaccion(String cuendId) {
		String jpql="FROM Transaccion WHERE cuenta.cuenId = " + cuendId;
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Transaccion> consultarPorCuentaYTipoTransaccion(String cuendId, Long titrId) {
		String jpql="FROM Transaccion tr WHERE tr.cuenta.cuenId = '" + cuendId + "' AND tr.tipoTransaccion.titrId = " + titrId;
		return entityManager.createQuery(jpql).getResultList();
	}

	


}
