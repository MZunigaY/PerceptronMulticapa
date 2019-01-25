package com.demo.controller;

import java.text.ParseException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entrenamiento.Entrenamiento;
import com.demo.model.InputModel;
import com.demo.model.UserCredential;
import com.demo.service.InputService;
import com.demo.service.LoginService;
import com.demo.service.ResultadoService;

@Controller
@RequestMapping
public class HomeController {
	
	private static final Log LOG = LogFactory.getLog(HomeController.class);
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	InputService inputService;
	
	@Autowired
	ResultadoService resultadoService;
	
	@Autowired
	Entrenamiento entrenamiento;
	
	@GetMapping({ "", "/", "/login" })
	public String login(Model model, 
			@RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {
		LOG.info("METHOD: login() -- PARAMS: error=" + error + ", logout=" + logout);
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		LOG.info("Returning to login view");
		return "login";
	}
	
	@GetMapping("/reclamos")
	public ModelAndView contacts() throws ParseException {
		ModelAndView mav = new ModelAndView("reclamos");
		mav.addObject("pruebas", inputService.obtenerListaPrueba());
		// FALTA MANDAR EL PORCENTAJE DE ENTRENAMIENTO
		return mav;
	}
	
	@PostMapping("/entrenamiento")
	@ResponseBody
	public String entrenamiento() throws Exception {
		String response = String.valueOf(entrenamiento.realizarEntrenamiento());
		System.out.println("################ RESPOONSE = "+response);
		return response;
	}
	
	@PostMapping("/logincheck")
	public String loginCheck(@ModelAttribute(name="userCredentials") UserCredential userCredential) {
		LOG.info("METHOD: loginCheck() -- PARAMS: " + userCredential.toString());
		boolean logeado = loginService.authentication(userCredential);
		if (logeado == true)
			return "redirect:/reclamos";
		else
			return "redirect:/index?error";
	}
	
	@GetMapping("/form")
	public String redirectInputForm(@RequestParam(name="id", required=false) int id, Model model) throws ParseException {
		InputModel inputModel = new InputModel();
		if (id != 0) {
			inputModel = inputService.findInputByIdModel(id);
		}
		model.addAttribute("inputModel", inputModel);
		model.addAttribute("resultadosEsperados", resultadoService.obtenerResultadosEsperados());
		return "input_form";
	}
	
	@PostMapping("/addinput")
	public ModelAndView agregarInput(@ModelAttribute(name="inputModel") InputModel inputModel, RedirectAttributes redirAtr) throws Exception {
		ModelAndView mav = new ModelAndView();
		LOG.info("METHOD: agregarInput() -- PARAMS: "+inputModel.toString());
		mav.setViewName("redirect:/reclamos");
		if(inputService.agregarInputPrueba(inputModel) != null) {
			redirAtr.addFlashAttribute("result",1);
		} else{
			redirAtr.addFlashAttribute("result",2);
		}
		return mav;
	}
	
	@GetMapping("/cancel")
	public String cancel() {
		return "redirect:/reclamos";
	}
	
}
