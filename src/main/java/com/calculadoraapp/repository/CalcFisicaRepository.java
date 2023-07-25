package com.calculadoraapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.calculadoraapp.models.CalcFisica;

public interface CalcFisicaRepository extends CrudRepository<CalcFisica, String> {
	
	//buscar pelo códgio
	CalcFisica findByIdFisica(long idFisica);

}
