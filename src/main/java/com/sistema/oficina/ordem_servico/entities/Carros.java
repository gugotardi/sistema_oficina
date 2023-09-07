package com.sistema.oficina.ordem_servico.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_carros")
public class Carros implements Serializable {
	private static final long  serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idCarro;
	private String placaCarro;
	private String nomeCarro;
	private String anoCarro;
	private String modeloCarro;
	
	@ManyToOne
	@JoinColumn(name="idMarca")
	private Marcas marcas;

	public Carros() {}

	public Carros(Long idCarro, String placaCarro, String nomeCarro, String anoCarro, String modeloCarro,
			Marcas marcas) {
		this.idCarro = idCarro;
		this.placaCarro = placaCarro;
		this.nomeCarro = nomeCarro;
		this.anoCarro = anoCarro;
		this.modeloCarro = modeloCarro;
		this.marcas = marcas;
	}

	public Long getIdCarro() {
		return idCarro;
	}

	public void setIdCarro(Long idCarro) {
		this.idCarro = idCarro;
	}

	public String getPlacaCarro() {
		return placaCarro;
	}

	public void setPlacaCarro(String placaCarro) {
		this.placaCarro = placaCarro;
	}

	public String getNomeCarro() {
		return nomeCarro;
	}

	public void setNomeCarro(String nomeCarro) {
		this.nomeCarro = nomeCarro;
	}

	public String getAnoCarro() {
		return anoCarro;
	}

	public void setAnoCarro(String anoCarro) {
		this.anoCarro = anoCarro;
	}

	public String getModeloCarro() {
		return modeloCarro;
	}

	public void setModeloCarro(String modeloCarro) {
		this.modeloCarro = modeloCarro;
	}

	public Marcas getMarcas() {
		return marcas;
	}

	public void setMarcas(Marcas marcas) {
		this.marcas = marcas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCarro);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carros other = (Carros) obj;
		return Objects.equals(idCarro, other.idCarro);
	}
	
	
	
	
	

}
