package com.calculadoraapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.calculadoraapp.models.Calculadora2;

public interface Calculadora2Repository extends CrudRepository<Calculadora2, String>{

	//buscar pelo códgio
	Calculadora2 findByCodigo(long codigo);
}
