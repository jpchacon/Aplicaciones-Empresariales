package co.edu.usbcali.banco.vista;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import co.edu.usbcali.banco.modelo.TipoUsuario;
import co.edu.usbcali.banco.modelo.Usuario;

@ViewScoped
@ManagedBean
public class UsuarioVista {
	
	private InputText txtUsuUsuario;
	private InputText txtClave;
	private InputText txtIdentificacion;
	private InputText txtNombre;
	private SelectOneMenu somActivo;
	
	private SelectOneMenu somTipoUsuario;
	
	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnLimpiar;
	
	private List<SelectItem> losTipoUsuarioSelectItem;
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	private List<Usuario> losUsuarios;
	
	public void txtUsuarioIdListener(){
		
		String id = null;
		
		try {
			id = new String(txtUsuUsuario.getValue().toString());
			
		} catch (Exception e) {

			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La identificación debe ser una cadena", "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		
		Usuario usuario = delegadoDeNegocio.consultarUsuarioPorId(id);
		
		if(usuario == null) {
			txtClave.resetValue();
			txtIdentificacion.resetValue();
			txtNombre.resetValue();
			somActivo.resetValue();
			somTipoUsuario.resetValue();
			
			txtUsuUsuario.setDisabled(false);
			
			btnModificar.setDisabled(true);
			btnCrear.setDisabled(false);
			
		}else {
			
			txtClave.setValue(usuario.getClave());
			txtIdentificacion.setValue(usuario.getIdentificacion());
			txtNombre.setValue(usuario.getNombre());
			somActivo.setValue(usuario.getActivo());
			
			txtUsuUsuario.setDisabled(true);
			
			TipoUsuario tipoUsuario = delegadoDeNegocio.consultarTipoUsuarioPorId(usuario.getTipoUsuario().getTiusId());
			somTipoUsuario.setValue(tipoUsuario.getTiusId());
			
			btnModificar.setDisabled(false);
			btnCrear.setDisabled(true);
			
		}
	}
	public String crearAction() {
		try {
			Usuario usuario = new Usuario();
			String passwd = generarPassword();
			usuario.setActivo(somActivo.getValue().toString().charAt(0));
			usuario.setUsuUsuario(txtUsuUsuario.getValue().toString());
			usuario.setClave(passwd);
			usuario.setIdentificacion(new BigDecimal(txtIdentificacion.getValue().toString()));
			usuario.setNombre(txtNombre.getValue().toString());
			
			TipoUsuario tipoUsuario = delegadoDeNegocio.consultarTipoUsuarioPorId(Long.parseLong(somTipoUsuario.getValue().toString()));
			usuario.setTipoUsuario(tipoUsuario);
			
			delegadoDeNegocio.grabarUsuario(usuario);
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"El usuario se creo con exito",""));
			
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		return "";
	}
	public String modificarAction() {
		try {
			Usuario usuario = new Usuario();
			usuario.setActivo(somActivo.getValue().toString().charAt(0));
			usuario.setUsuUsuario(txtUsuUsuario.getValue().toString());
			usuario.setClave(txtClave.getValue().toString());
			usuario.setIdentificacion(new BigDecimal(txtIdentificacion.getValue().toString()));
			usuario.setNombre(txtNombre.getValue().toString());
			
			TipoUsuario tipoUsuario = delegadoDeNegocio.consultarTipoUsuarioPorId(Long.parseLong(somTipoUsuario.getValue().toString()));
			usuario.setTipoUsuario(tipoUsuario);
			
			delegadoDeNegocio.modificarUsuario(usuario);
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"El usuario se modifico con exito",""));
			
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		return "";
	}
	public String borrarAction() {
		try {
			Usuario usuario = new Usuario();
			usuario.setActivo(somActivo.getValue().toString().charAt(0));
			usuario.setUsuUsuario(txtUsuUsuario.getValue().toString());
			usuario.setClave(txtClave.getValue().toString());
			usuario.setIdentificacion(new BigDecimal(txtIdentificacion.getValue().toString()));
			usuario.setNombre(txtNombre.getValue().toString());
			
			TipoUsuario tipoUsuario = delegadoDeNegocio.consultarTipoUsuarioPorId(Long.parseLong(somTipoUsuario.getValue().toString()));
			usuario.setTipoUsuario(tipoUsuario);
			
			delegadoDeNegocio.borrarUsuario(usuario);
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"El usuario se borro con exito",""));
			
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		return "";
	}
	public String limpiarAction() {
		txtUsuUsuario.resetValue();
		txtClave.resetValue();
		txtIdentificacion.resetValue();
		txtNombre.resetValue();
		somActivo.resetValue();
		somTipoUsuario.resetValue();
		txtUsuUsuario.setDisabled(false);
		
		btnModificar.setDisabled(true);
		btnCrear.setDisabled(true);
		return "";
	}
	
	public List<Usuario> getLosUsuarios() {
		if(losUsuarios == null) {
			losUsuarios = delegadoDeNegocio.consultarUsuarioTodos();
		}
		return losUsuarios;
	}
	public void setLosUsuarios(List<Usuario> losUsuarios) {
		this.losUsuarios = losUsuarios;
	}
	public InputText getTxtUsuUsuario() {
		return txtUsuUsuario;
	}
	public void setTxtUsuUsuario(InputText txtUsuUsuario) {
		this.txtUsuUsuario = txtUsuUsuario;
	}
	public InputText getTxtClave() {
		return txtClave;
	}
	public void setTxtClave(InputText txtClave) {
		this.txtClave = txtClave;
	}
	public InputText getTxtIdentificacion() {
		return txtIdentificacion;
	}
	public void setTxtIdentificacion(InputText txtIdentificacion) {
		this.txtIdentificacion = txtIdentificacion;
	}
	public InputText getTxtNombre() {
		return txtNombre;
	}
	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}
	
	public SelectOneMenu getSomActivo() {
		return somActivo;
	}
	public void setSomActivo(SelectOneMenu somActivo) {
		this.somActivo = somActivo;
	}
	public SelectOneMenu getSomTipoUsuario() {
		return somTipoUsuario;
	}
	public void setSomTipoUsuario(SelectOneMenu somTipoUsuario) {
		this.somTipoUsuario = somTipoUsuario;
	}
	public CommandButton getBtnCrear() {
		return btnCrear;
	}
	public void setBtnCrear(CommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}
	public CommandButton getBtnModificar() {
		return btnModificar;
	}
	public void setBtnModificar(CommandButton btnModificar) {
		this.btnModificar = btnModificar;
	}
	public CommandButton getBtnLimpiar() {
		return btnLimpiar;
	}
	public void setBtnLimpiar(CommandButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}
	public List<SelectItem> getLosTipoUsuarioSelectItem() {
		if(losTipoUsuarioSelectItem == null) {
			List<TipoUsuario> losTipoUsuario = delegadoDeNegocio.consultarTipoUsuarioTodos();
			losTipoUsuarioSelectItem = new ArrayList<>();
			for (TipoUsuario tipoUsuario : losTipoUsuario) {
				losTipoUsuarioSelectItem.add(new SelectItem(tipoUsuario.getTiusId(), tipoUsuario.getNombre()));
			}
		}
		return losTipoUsuarioSelectItem;
	}
	public void setLosTipoUsuarioSelectItem(List<SelectItem> losTipoUsuarioSelectItem) {
		this.losTipoUsuarioSelectItem = losTipoUsuarioSelectItem;
	}
	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}
	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}
	
	private String generarPassword() throws NoSuchAlgorithmException {

		String[] symbols = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
		int length = 10;
		Random random = SecureRandom.getInstanceStrong(); // as of JDK 8, this should return the strongest algorithm available to the JVM
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
		    int indexRandom = random.nextInt( symbols.length );
		    sb.append( symbols[indexRandom] );
		}
		
		String password = sb.toString();
		
	
	return password;
	
}
	

}
