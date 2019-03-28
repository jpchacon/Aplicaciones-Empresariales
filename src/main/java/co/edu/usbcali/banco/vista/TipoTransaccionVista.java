package co.edu.usbcali.banco.vista;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

@ViewScoped
@ManagedBean
public class TipoTransaccionVista {
	
	private InputText txtTipoTransaccionId;
	private InputText txtNombre;
	private InputText txtActivo;
	
	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnBorrar;
	private CommandButton btnLimpiar;
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	
	public InputText getTxtTipoTransaccionId() {
		return txtTipoTransaccionId;
	}
	public void setTxtTipoTransaccionId(InputText txtTipoTransaccionId) {
		this.txtTipoTransaccionId = txtTipoTransaccionId;
	}
	public InputText getTxtNombre() {
		return txtNombre;
	}
	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}
	public InputText getTxtActivo() {
		return txtActivo;
	}
	public void setTxtActivo(InputText txtActivo) {
		this.txtActivo = txtActivo;
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
	public String crearAction() {
		return "";
	}
	public String modificarAction() {
		return "";
	}
	public String borrarAction() {
		return "";
	}
	public String limpiarAction() {
		return "";
	}

}
