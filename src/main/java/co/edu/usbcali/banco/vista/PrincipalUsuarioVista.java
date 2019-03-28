package co.edu.usbcali.banco.vista;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.edu.usbcali.banco.modelo.TipoUsuario;
import co.edu.usbcali.banco.modelo.Usuario;
import co.edu.usbcali.banco.util.FacesUtils;

@ViewScoped
@ManagedBean
public class PrincipalUsuarioVista {
	private String nombreUsuarioLogin;
	private String nombreTipoUsuarioLogin;
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;

	public String getNombreUsuarioLogin() {
		Usuario usuario = (Usuario) FacesUtils.getfromSession("usuario");
		nombreUsuarioLogin = usuario.getNombre();
		return nombreUsuarioLogin;
	}

	public void setNombreUsuarioLogin(String nombreUsuarioLogin) {
		this.nombreUsuarioLogin = nombreUsuarioLogin;
	}

	public String getNombreTipoUsuarioLogin() {
		Usuario usuario = (Usuario) FacesUtils.getfromSession("usuario");
		TipoUsuario tipoUsuario = delegadoDeNegocio.consultarTipoUsuarioPorId(usuario.getTipoUsuario().getTiusId());
		nombreTipoUsuarioLogin = tipoUsuario.getNombre();
		return nombreTipoUsuarioLogin;
	}

	public void setNombreTipoUsuarioLogin(String nombreTipoUsuarioLogin) {
		this.nombreTipoUsuarioLogin = nombreTipoUsuarioLogin;
	}

	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}

	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}
	
	
}
