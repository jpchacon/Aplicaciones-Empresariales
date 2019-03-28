package co.edu.usbcali.banco.vista;



import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.faces.bean.ManagedProperty;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.util.FacesUtils;
@ViewScoped
@ManagedBean(name = "loginClienteVista")
public class LoginClienteVista {
	
	 private String cuenId;
	 private String iduser;
	 private String password;
	    
	 @ManagedProperty(value = "#{authenticationManager}")
	 private AuthenticationManager authenticationManager = null;
	 

	public String getCuenId() {
		return cuenId;
	}

	public void setCuenId(String cuenId) {
		this.cuenId = cuenId;
	}

	public String getIduser() {
		return iduser;
	}

	public void setIduser(String iduser) {
		this.iduser = iduser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}


	public String login() {
        try {
            Authentication request = new UsernamePasswordAuthenticationToken(this.getCuenId(), this.getPassword());
            Authentication result = authenticationManager.authenticate(request);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(result);

            ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
            
            
            Cuenta cuenta = (Cuenta) FacesUtils.getfromSession("cuenta");
            String idTem = new String(cuenta.getCliente().getClieId().toString());
            
            
            
            if(! (idTem.equals(this.getIduser())) ) {
            	
            	FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_ERROR, "La cuenta no pertenece a este cliente", ""));
            	return "";
            }
            
            
            if(cuenta.getActiva() == 'N') {
            	FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_ERROR, "La cuenta debe estar activa", ""));
            	return "";
            }
            

        } catch (AuthenticationException e) {
          FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o clave no validos", ""));

            return "/loginCliente.xhtml";
        }

        return "/xhtml/principal.xhtml";
    } 

}
