package com.calculadoraapp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.calculadoraapp.models.CalcFisica;
import com.calculadoraapp.models.Calculadora;
import com.calculadoraapp.models.Calculadora2;
import com.calculadoraapp.models.Tarifa;
import com.calculadoraapp.repository.CalcFisicaRepository;
import com.calculadoraapp.repository.Calculadora2Repository;
import com.calculadoraapp.repository.CalculadoraRepository;
import com.calculadoraapp.repository.TarifaRepository;

@Controller
public class CalculadoraController {
//Injeção de dependÊncia: Cria um nova instância automaticamente
	@Autowired
	private CalculadoraRepository cr;
	@Autowired
	private TarifaRepository tr;
	@Autowired
	private Calculadora2Repository cr2;
	@Autowired
	private CalcFisicaRepository cr3;

	

	@PostMapping("/calculoAtividade")
    public String calcularAtividadeFisica(@RequestParam("genero") String genero,
                                              @RequestParam("peso") double peso,
                                              @RequestParam("horas") String horas,
                                              @RequestParam("altura") double altura,
                                              @RequestParam("atividadeFisica") String atividadeFisica,
                                              Model model, CalcFisica calcfisica) {
       
		double imc = peso /(altura * altura);

        double gastoCalorico = calcularHoras(horas) * imc;;
        
        String gastoTotalFormatted = String.format("%.3f", gastoCalorico);
        
        model.addAttribute("atividadeFisica", atividadeFisica);
        model.addAttribute("genero", genero);
        model.addAttribute("horas", horas);
        model.addAttribute("peso", peso);
        model.addAttribute("altura", altura);
        model.addAttribute("gastoCalorico", gastoTotalFormatted);
        model.addAttribute("imc", imc);

        cr3.save(calcfisica);
       
        return "calculadora/ResultCalcGastoEnergPAtivPraticada";
    }


    
	
	
	
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
	@RequestMapping(value = "/ResultCalcGastoEnergPAtivPraticada", method = RequestMethod.GET)
	public String result2() {
		return "calculadora/ResultCalcGastoEnergPAtivPraticada";
	}
	
	//metodo Get para retornar o formulario
			@RequestMapping(value = "/ResultCalcEficEnergetica", method = RequestMethod.GET)
			public String result1() {
				return "calculadora/ResultCalcEficEnergetica";
			}
	
	
	//metodo Get para retornar o formulario
		@RequestMapping(value = "/CalcEficEnergetica2", method = RequestMethod.GET)
		public String form5() {
			return "calculadora/formCalcEficEnergetica2";
		}
	
	//metodo Get para retornar o formulario
	@RequestMapping(value = "/CalcGastoEnergPAtivPraticada", method = RequestMethod.GET)
	public String form4() {
		return "calculadora/formCalcGastoEnergPAtivPraticada";
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
		
	//CONTROLLER DA BASE DO PROJETO EMBAIXO
	
//metodo Get para retornar o formulario
	@RequestMapping(value = "/calculoEnergia", method = RequestMethod.GET)
	public String form() {
		return "calculadora/formCalEnergia";
	}
	

//metodo POST para enviar para o banco
	@RequestMapping(value = "/calculoEnergia", method = RequestMethod.POST)
	public String form(@RequestParam("eletronico") String eletronico, @RequestParam("potencia") double potencia,
			@RequestParam("diasMes") int dias, @RequestParam("horas") String horas, @RequestParam("taxa") double taxa,
			Model model) {

		double consumoTotal = (potencia / 1000) * dias * calcularHoras(horas) * taxa;

		model.addAttribute("eletronico", eletronico);
		model.addAttribute("consumoTotal", consumoTotal);

		return "calculadora/resultado";
	}

	private double calcularHoras(String horas) {

		String[] partes = horas.split(":");
		int horasInt = Integer.parseInt(partes[0]);
		int minutosInt = Integer.parseInt(partes[1]);
		double horasDecimal = horasInt + (minutosInt / 60.0);
		return horasDecimal;
	}

	@RequestMapping("/calculadoras")
	public ModelAndView listaCalculadora() {
		ModelAndView mv = new ModelAndView("index");
		//busca no DB
		Iterable<Calculadora> calculadoras = cr.findAll();
		//mostrar na view, utilizando a mesma palavra da div ${"calculadoras"}
		mv.addObject("calculadoras", calculadoras);
		return mv;
	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ModelAndView detalhesCalculadora(@PathVariable("codigo") long codigo) {
		Calculadora calculadora = cr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("calculadora/detalhesCalculadora");
		mv.addObject("calculadora", calculadora);
//É apemas um objeto
		Tarifa tarifas = tr.findByCalculadora(calculadora);
		mv.addObject("tarifas", tarifas);
		return mv;
	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.POST)
	public String detalhesCalculadoraPost(@PathVariable("codigo") long codigo, Tarifa tarifa) {
		Calculadora calculadora = cr.findByCodigo(codigo);
		tarifa.setCalculadora(calculadora);
		tr.save(tarifa);
		return "redirect:/{codigo}";
	}
}