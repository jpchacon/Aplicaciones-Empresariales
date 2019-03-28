package co.edu.usbcali.banco.vista;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.modelo.CuentaRegistrada;
import co.edu.usbcali.banco.util.FacesUtils;

@ViewScoped
@ManagedBean
public class TransferenciaEntreCuentasVista {
	
	
	
	private InputText valor;
	
	private SelectOneMenu somCuentasRegistradas;
	
	private CommandButton btnTransferir;
	private CommandButton btnCancelar;
	
	private List<SelectItem> lasCuentasRegistradasSelectItem;
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	public String transferirAction() {
			
			try {
				Cuenta cuenta = (Cuenta) FacesUtils.getfromSession("cuenta");
				String usuario = "banco";
				String idPosCuenta = somCuentasRegistradas.getValue().toString();
				
				
				CuentaRegistrada cuentaDestino = delegadoDeNegocio.consultarCuentaRegistradaPorId(Long.parseLong(idPosCuenta));
				BigDecimal valorTransaccion = new BigDecimal(valor.getValue().toString());
				
				
				delegadoDeNegocio.transferencia(cuenta.getCuenId(), cuentaDestino.getCuenta().getCuenId(), valorTransaccion, usuario);
				
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"La transferencia fue exitosa",""));
				
				somCuentasRegistradas.resetValue();
				valor.resetValue();
				
			} catch (Exception e) {
				FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
				FacesContext.getCurrentInstance().addMessage("", mensaje);
			}
			return "";
		}

	public String cancelarAction() {
		somCuentasRegistradas.resetValue();
		valor.resetValue();
		return "";
	}

	public InputText getValor() {
		return valor;
	}

	public void setValor(InputText valor) {
		this.valor = valor;
	}

	public SelectOneMenu getSomCuentasRegistradas() {
		return somCuentasRegistradas;
	}

	public void setSomCuentasRegistradas(SelectOneMenu somCuentasRegistradas) {
		this.somCuentasRegistradas = somCuentasRegistradas;
	}

	public CommandButton getBtnTransferir() {
		return btnTransferir;
	}

	public void setBtnTransferir(CommandButton btnTransferir) {
		this.btnTransferir = btnTransferir;
	}

	public CommandButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(CommandButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public List<SelectItem> getLasCuentasRegistradasSelectItem() {
		if(lasCuentasRegistradasSelectItem == null) {
			Cuenta cuenta = (Cuenta) FacesUtils.getfromSession("cuenta");
			List<CuentaRegistrada> lasCuentaR = delegadoDeNegocio.consultarTodasLasCuentasRegistradasPorId(cuenta.getCliente().getClieId());
			lasCuentasRegistradasSelectItem = new ArrayList<>();
			for (CuentaRegistrada cuentaRegistrada : lasCuentaR) {
				lasCuentasRegistradasSelectItem.add(new SelectItem(cuentaRegistrada.getCureId(), cuentaRegistrada.getCuenta().getCuenId()));
			}
		}
		return lasCuentasRegistradasSelectItem;
	}

	public void setLasCuentasRegistradasSelectItem(List<SelectItem> lasCuentasRegistradasSelectItem) {
		this.lasCuentasRegistradasSelectItem = lasCuentasRegistradasSelectItem;
	}

	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}

	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}
	
	

}
