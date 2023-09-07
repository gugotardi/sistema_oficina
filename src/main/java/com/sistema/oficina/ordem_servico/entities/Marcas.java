package com.sistema.oficina.ordem_servico.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_marcas")
public class Marcas implements Serializable {
	private static final long  serialVersionUID = 1L; 
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idMarca;
	private String nomeMarca;
	
	
	public Marcas() {}

	public Marcas(Long idMarca, String nomeMarca) {
	
		this.idMarca = idMarca;
		this.nomeMarca = nomeMarca;
	}

	public Long getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(Long idMarca) {
		this.idMarca = idMarca;
	}

	public String getNomeMarca() {
		return nomeMarca;
	}

	public void setNomeMarca(String nomeMarca) {
		this.nomeMarca = nomeMarca;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(idMarca);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Marcas other = (Marcas) obj;
		return Objects.equals(idMarca, other.idMarca);
	}
	
	
	
	
	

}
