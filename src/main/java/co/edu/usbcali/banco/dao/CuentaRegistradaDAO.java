package co.edu.usbcali.banco.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.banco.modelo.CuentaRegistrada;


@Repository
@Scope("singleton")
public class CuentaRegistradaDAO implements ICuentaRegistradaDAO{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void grabar(CuentaRegistrada cuentaRegistrada) {
		entityManager.persist(cuentaRegistrada);
	}

	@Override
	public void modificar(CuentaRegistrada cuentaRegistrada) {
		entityManager.merge(cuentaRegistrada);
	}

	@Override
	public void borrar(CuentaRegistrada cuentaRegistrada) {
		entityManager.remove(cuentaRegistrada);
	}

	@Override
	public CuentaRegistrada consultarPorId(long cureId) {
		return entityManager.find(CuentaRegistrada.class, cureId);
	}

	@Override
	public List<CuentaRegistrada> consultarTodos() {
		String jpql = "FROM CuentaRegistrada";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<CuentaRegistrada> consultarTodasLasCuentasPorId(BigDecimal id) {
		String jpql = "FROM CuentaRegistrada WHERE cliente.clieId = " + id;
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<CuentaRegistrada> consultarCuentaRegistradaPorCliente(BigDecimal clieId) {
		String jpql = "FROM CuentaRegistrada cr WHERE cr.cliente.clieId = " + clieId ;
		return entityManager.createQuery(jpql).getResultList();
	}
}
