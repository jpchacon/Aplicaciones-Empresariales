package co.edu.usbcali.banco.vista;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import co.edu.usbcali.banco.modelo.TipoUsuario;

@ViewScoped
@ManagedBean
public class TipoUsuarioVista {
	
	private InputText txtTipoUsuarioId;
	private InputText txtNombre;
	private SelectOneMenu somActivo;
	
	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnBorrar;
	private CommandButton btnLimpiar;
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	private List<TipoUsuario> losTipoUsuario;
	
	public void txtTipoTransaccionIdListener() {
		
		Long id = null;
		
		try {
			id = new Long(txtTipoUsuarioId.getValue().toString());
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La identificación debe ser numérica", "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		
		TipoUsuario tipoUsuario = delegadoDeNegocio.consultarTipoUsuarioPorId(id);
		
		if(tipoUsuario == null) {
			txtNombre.resetValue();
			somActivo.resetValue();
			
			btnBorrar.setDisabled(true);
			btnModificar.setDisabled(true);
			btnCrear.setDisabled(false);
		}else {
			txtNombre.setValue(tipoUsuario.getNombre());
			somActivo.setValue(tipoUsuario.getActivo());
			
			btnBorrar.setDisabled(false);
			btnModificar.setDisabled(false);
			btnCrear.setDisabled(true);
			
		}
		
	}
	public String crearAction() {
		try {
			TipoUsuario tipoUsuario = new TipoUsuario();
			tipoUsuario.setActivo(somActivo.getValue().toString().charAt(0));
			tipoUsuario.setTiusId(new Long(txtTipoUsuarioId.getValue().toString()));
			tipoUsuario.setNombre(txtNombre.getValue().toString());
			
			delegadoDeNegocio.grabarTipoUsuario(tipoUsuario);
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"El tipo de usuario se creo con exito",""));
			
			btnCrear.setDisabled(true);
			losTipoUsuario = null;
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		return "";
	}
	public String modificarAction() {
		try {
			TipoUsuario tipoUsuario = new TipoUsuario();
			tipoUsuario.setActivo(somActivo.getValue().toString().charAt(0));
			tipoUsuario.setTiusId(new Long(txtTipoUsuarioId.getValue().toString()));
			tipoUsuario.setNombre(txtNombre.getValue().toString());
			
			delegadoDeNegocio.modificarTipoUsuario(tipoUsuario);
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"El tipo de usuario se modifico con exito",""));
			
			losTipoUsuario = null;
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		return "";
	}
	public String borrarAction() {
		try {
			TipoUsuario tipoUsuario = new TipoUsuario();
			tipoUsuario.setActivo(somActivo.getValue().toString().charAt(0));
			tipoUsuario.setTiusId(new Long(txtTipoUsuarioId.getValue().toString()));
			tipoUsuario.setNombre(txtNombre.getValue().toString());
			
			delegadoDeNegocio.borrarTipoUsuario(tipoUsuario);
			
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"El tipo de usuario se borro con exito",""));
			
			losTipoUsuario = null;
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		return "";
	}
	public String limpiarAction() {
		txtTipoUsuarioId.resetValue();
		txtNombre.resetValue();
		somActivo.resetValue();
		
		btnBorrar.setDisabled(true);
		btnModificar.setDisabled(true);
		btnCrear.setDisabled(true);
		return "";
	}
	
	public List<TipoUsuario> getLosTipoUsuario() {
		if(losTipoUsuario == null) {
			losTipoUsuario = delegadoDeNegocio.consultarTipoUsuarioTodos();
		}
		return losTipoUsuario;
	}
	public void setLosTipoUsuario(List<TipoUsuario> losTipoUsuario) {
		this.losTipoUsuario = losTipoUsuario;
	}
	public InputText getTxtTipoUsuarioId() {
		return txtTipoUsuarioId;
	}
	public void setTxtTipoUsuarioId(InputText txtTipoUsuarioId) {
		this.txtTipoUsuarioId = txtTipoUsuarioId;
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
	public CommandButton getBtnBorrar() {
		return btnBorrar;
	}
	public void setBtnBorrar(CommandButton btnBorrar) {
		this.btnBorrar = btnBorrar;
	}
	public CommandButton getBtnLimpiar() {
		return btnLimpiar;
	}
	public void setBtnLimpiar(CommandButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}
	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}
	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}

}
