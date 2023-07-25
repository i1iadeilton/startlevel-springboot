package com.calculadoraapp.models;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Calculadora implements Serializable {
 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long codigo;

	@OneToOne
	private Tarifa tarifa;
	
	private String eletronico;
	private Double potencia;
	private Integer dias;
	private String horas;
	private Double taxa;
	
		
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public String getEletronico() {
		return eletronico;
	}
	public void setEletronico(String eletronico) {
		this.eletronico = eletronico;
	}
	public Double getPotencia() {
		return potencia;
	}
	public void setPotencia(Double potencia) {
		this.potencia = potencia;
	}
	public Integer getDias() {
		return dias;
	}
	public void setDias(Integer qtdDias) {
		this.dias = qtdDias;
	}
	public String getHoras() {
		return horas;
	}
	public void setHoras(String horario) {
		this.horas = horario;
	}
	public Double getTaxa() {
		return taxa;
	}
	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}
}
