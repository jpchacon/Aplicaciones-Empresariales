package co.edu.usbcali.banco.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.banco.modelo.Cliente;

@Repository
@Scope("singleton")
public class ClienteDAO implements IClienteDAO{
	
	@PersistenceContext
	private EntityManager entityManager; 

	@Override
	public void grabar(Cliente cliente) {
		entityManager.persist(cliente);
	}

	@Override
	public void modificar(Cliente cliente) {
		entityManager.merge(cliente);
	}

	@Override
	public void borrar(Cliente cliente) {
		entityManager.remove(cliente);
	}

	@Override
	public Cliente consultarPorId(BigDecimal clieId) {
		return entityManager.find(Cliente.class, clieId);
	}

	@Override
	public List<Cliente> consultarTodos() {
		String jpql="SELECT cli FROM Cliente cli";
		return entityManager.createQuery(jpql).getResultList();
	}

}
