package com.calculadoraapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.calculadoraapp.models.Calculadora;

public interface CalculadoraRepository extends CrudRepository<Calculadora, String>{ 

	//buscar pelo códgio
	Calculadora findByCodigo(long codigo);
	
	 
}
