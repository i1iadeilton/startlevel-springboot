package com.calculadoraapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.calculadoraapp.models.Calculadora;
import com.calculadoraapp.models.Tarifa;

public interface TarifaRepository extends CrudRepository<Tarifa, String> {
	Tarifa findByCalculadora(Calculadora calculadora);
}
 