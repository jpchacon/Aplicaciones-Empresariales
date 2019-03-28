package co.edu.usbcali.banco.dao;

import java.util.List;

import co.edu.usbcali.banco.modelo.Transaccion;
import co.edu.usbcali.banco.modelo.Usuario;

public interface IUsuarioDAO {
	
	public void grabar(Usuario usuario);
	
	public void modificar(Usuario usuario);
	
	public void borrar(Usuario usuario);
	
	public Usuario consultarPorId(String usuUsuario);
	public List<Usuario> consultarTodos();
}
