package com.calculadoraapp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.calculadoraapp.models.Calculadora2;
import com.calculadoraapp.repository.Calculadora2Repository;

@Controller
public class CalculadoraController {
//Injeção de dependÊncia: Cria um nova instância automaticamente
	
	@Autowired
	private Calculadora2Repository cr2;
	
	

	


    
	
	
	
	@PostMapping("/calculoEnergia2")
	public String calcularEficienciaEnergetica(@RequestParam("equipamento") List<String> equipamentos,
			@RequestParam("potencia") List<Double> potencias, @RequestParam("horas") List<String> horas,
			@RequestParam("diasMes") List<Integer> diasMes, Model model) {

		List<Calculadora2> calculadoras = new ArrayList<>();

		double potenciaTotal = 0;
		double consumoTotal = 0;
		double consumoMensalD = 0;
		for (int i = 0; i < equipamentos.size(); i++) {
			String equipamento = equipamentos.get(i);
			double potencia = potencias.get(i);
			String hora = horas.get(i);
			int diaMes = diasMes.get(i);
			
			
			potenciaTotal += potencias.get(i);
			

			double consumoMensal = (potencia * 0.001) * calcularHoras(hora) * diaMes * 0.89;
			
			consumoTotal += consumoMensal;

			Calculadora2 calculadora = new Calculadora2();
			calculadora.setEquipamento(equipamento);
			calculadora.setPotencia(potencia);
			calculadora.setHoras(hora);
			calculadora.setDiasMes(diaMes);
			calculadora.setConsumoMensal(consumoMensal);

			calculadoras.add(calculadora);
			cr2.save(calculadora);
		}
		String potenciaTotalFormatted = String.format("%.2f", potenciaTotal);
        String consumoTotalFormatted = String.format("%.2f", consumoTotal);
 
		model.addAttribute("potenciaTotal", potenciaTotalFormatted);
	    model.addAttribute("consumoTotal", consumoTotalFormatted);
		model.addAttribute("calculadoras", calculadoras);
		return "calculadora/resultado3";
    }

	
	
	//metodo Get para retornar o formulario
			@RequestMapping(value = "/quemSomos", method = RequestMethod.GET)
			public String form3() {
				return "quemSomos";
			}
	
	
	//metodo Get para retornar o formulario
		@RequestMapping(value = "/CalcEficEnergetica", method = RequestMethod.GET)
		public String form2() {
			return "calculadora/formCalcEficEnergetica";
		}
	
	
	
	//CONTROLLER DO PROJETO START EM CIMA
	
	


	private double calcularHoras(String horas) {

		String[] partes = horas.split(":");
		int horasInt = Integer.parseInt(partes[0]);
		int minutosInt = Integer.parseInt(partes[1]);
		double horasDecimal = horasInt + (minutosInt / 60.0);
		return horasDecimal;
	}

	
}