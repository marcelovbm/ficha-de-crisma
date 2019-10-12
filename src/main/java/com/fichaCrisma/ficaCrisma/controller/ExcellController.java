package com.fichaCrisma.ficaCrisma.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExcellController {

	@RequestMapping("/teste")
	public String index() {
		return "Olá Marcelo";
	}
		
}
