package com.calculadoraapp.models;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Calculadora2 implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long codigo;
	
	private String equipamento;
	private Double potencia;
	private Integer diasMes;
	private String horas;
	private Double consumoMensal;
	
	
	public Double getConsumoMensal() {
		return consumoMensal;
	}
	public void setConsumoMensal(Double consumoMensal) {
		this.consumoMensal = consumoMensal;
	}
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public String getEquipamento() {
		return equipamento;
	}
	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
	}
	public Double getPotencia() {
		return potencia;
	}
	public void setPotencia(Double potencia) {
		this.potencia = potencia;
	}
	public Integer getDiasMes() {
		return diasMes;
	}
	public void setDiasMes(Integer diasMes) {
		this.diasMes = diasMes;
	}
	public String getHoras() {
		return horas;
	}
	public void setHoras(String horas) {
		this.horas = horas;
	}
	
}
