package co.edu.usbcali.banco.logica;

import java.util.List;

import co.edu.usbcali.banco.modelo.Usuario;

public interface IUsuarioLogica {
	
	public void grabar(Usuario usuario) throws Exception;
	
	public void modificar(Usuario usuario) throws Exception;
	
	public void borrar(Usuario usuario)  throws Exception;
	
	public Usuario consultarPorId(String usuUsuario);
	public List<Usuario> consultarTodos();

}
