package co.edu.usbcali.banco.modelo;
// Generated 30/01/2018 10:10:31 AM by Hibernate Tools 5.2.3.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * TipoTransaccion generated by hbm2java
 */
@Entity
@Table(name = "tipo_transaccion", schema = "public")
public class TipoTransaccion implements java.io.Serializable {
	
	@NotNull(message="El id no puede ser nulo")
	@Positive
	private long titrId;
	
	@NotNull
	@Size(max=20)
	@NotEmpty
	private String nombre;
	
	private char activo;
	private Set<Transaccion> transaccions = new HashSet<Transaccion>(0);

	public TipoTransaccion() {
	}

	public TipoTransaccion(long titrId, String nombre, char activo) {
		this.titrId = titrId;
		this.nombre = nombre;
		this.activo = activo;
	}

	public TipoTransaccion(long titrId, String nombre, char activo, Set<Transaccion> transaccions) {
		this.titrId = titrId;
		this.nombre = nombre;
		this.activo = activo;
		this.transaccions = transaccions;
	}

	@Id

	@Column(name = "titr_id", unique = true, nullable = false)
	public long getTitrId() {
		return this.titrId;
	}

	public void setTitrId(long titrId) {
		this.titrId = titrId;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "activo", nullable = false, length = 1)
	public char getActivo() {
		return this.activo;
	}

	public void setActivo(char activo) {
		this.activo = activo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoTransaccion")
	public Set<Transaccion> getTransaccions() {
		return this.transaccions;
	}

	public void setTransaccions(Set<Transaccion> transaccions) {
		this.transaccions = transaccions;
	}

}
