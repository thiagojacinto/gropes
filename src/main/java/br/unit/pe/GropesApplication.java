package br.unit.pe;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableScheduling
public class GropesApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		/*TecnologiaUsuario tec = new TecnologiaUsuario();
		Tecnologia tecnologia = new Tecnologia();
		tecnologia.setId(1L);
		//tec.setTecnologia(tecnologia);
		Usuario u = new Usuario();
		u.setId(1L);
		//tec.setUsuario(u);
		List<TecnologiaUsuario> tecs = new ArrayList<TecnologiaUsuario>();
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2020);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date dateRepresentation = cal.getTime();
		tec.setConheceDesde(dateRepresentation);
		tec.setEstudaDesde(dateRepresentation);
		
		tecs.add(tec);
		
		cal.set(Calendar.YEAR,2019);
		dateRepresentation = cal.getTime();
		tec = new TecnologiaUsuario();
		//tec.setTecnologia(tecnologia);
		tec.setConheceDesde(dateRepresentation);
		
		tecs.add(tec);
		
		cal.set(Calendar.YEAR,2020);
		dateRepresentation = cal.getTime();
		tec = new TecnologiaUsuario();
		//tec.setTecnologia(tecnologia);
		tec.setConheceDesde(dateRepresentation);
		
		
		EmpresaUsuario emp = new EmpresaUsuario();
		emp.setId(1L);
		emp.setIdUsuario(1L);
		
		List<EmpresaUsuario> listaEmps = new ArrayList<EmpresaUsuario>();
		listaEmps.add(emp);
		
		EmpresaUsuarioItem ite = new EmpresaUsuarioItem();
		ite.setEmpUsuId(1L);
		ite.setDataFim(dateRepresentation);
		ite.setTecnologiaId(1L);
		
		List<EmpresaUsuarioItem> listaEmpsItem = new ArrayList<EmpresaUsuarioItem>();
		listaEmpsItem.add(ite);
		
		Calculos.inovatividade(tecs,tec);
		Calculos.relevancia(10,listaEmps,listaEmpsItem,tecs);*/
		SpringApplication.run(GropesApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				.allowedOrigins("http://localhost:4200")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");;
			}
		};
	}
}
