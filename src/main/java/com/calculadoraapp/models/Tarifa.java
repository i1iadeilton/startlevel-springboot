package com.calculadoraapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Tarifa {
 
	@Id
	private String uf;
	private Double taxa;
	
	@OneToOne
	private Calculadora calculadora;
	
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public double getTaxa() {
		return taxa;
	}
	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}
	public Calculadora getCalculadora() {
		return calculadora;
	}
	public void setCalculadora(Calculadora calculadora) {
		this.calculadora = calculadora;
	}
	
	
}
