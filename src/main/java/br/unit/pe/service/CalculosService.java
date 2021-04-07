package br.unit.pe.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unit.pe.entity.TecnologiaUsuario;
import br.unit.pe.util.ConverterData;

@Service
public class CalculosService {
	@Autowired
	private static TecnologiaUsuarioService tecUsuService;
	@Autowired
	//private static EmpresaUsuarioService empUsuService;
	
	public static void inovatividade() {
		String dataHojeStr = ConverterData.paraString("yyyy-MM-dd", new Date());
		Date menorDataConheceDesde = tecUsuService.minConheceDesde();
		String menorDataConheceDesdeStr = ConverterData.paraString("yyyy-MM-dd", menorDataConheceDesde);
		Long mesesDiffMenorData = ChronoUnit.MONTHS.between(YearMonth.from(LocalDate.parse(menorDataConheceDesdeStr)),
				YearMonth.from(LocalDate.parse(dataHojeStr)));
		
		List<TecnologiaUsuario> tecs = tecUsuService.listar();
		
		if(tecs!=null) {
			for (TecnologiaUsuario tec : tecs) {
				String dataUsuConheceDesdeStr = ConverterData.paraString("yyyy-MM-dd", tec.getConheceDesde());
				Long mesesDiff = ChronoUnit.MONTHS.between(YearMonth.from(LocalDate.parse(dataUsuConheceDesdeStr)),
						YearMonth.from(LocalDate.parse(dataHojeStr)));
				Double inovatividade = (double) ((double) mesesDiff / (double) mesesDiffMenorData);
				System.out.printf("%.3f", inovatividade);
				System.out.println();
			}
		}
	}
	public static void relevancia() {
		/***
		 * funcao incompleta/
		 * 
		/*Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 2);
		// data de dois anos atrás da new Date
		Date date = cal.getTime();
		
		List<EmpresaUsuario> eus = empUsuService.listar();
		
		if(eus!=null) {
			for (EmpresaUsuario empresaUsuario : eus) {
				
			}
		}*/
	}
}
