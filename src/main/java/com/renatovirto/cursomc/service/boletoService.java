package com.renatovirto.cursomc.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.renatovirto.cursomc.domain.PagamentoComBoleto;

@Service
public class boletoService {

	public static void preencherPagamentoComBoleto(PagamentoComBoleto pag, Date instante) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(instante);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		pag.setDataVencimento(calendar.getTime());
	}

}
