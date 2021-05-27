package br.unit.pe.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Scheduled(cron = "0 0/5 * * * ?")
	public void cronJobSch() throws Exception {
		System.out.println("Cronjob inicio");
		jdbcTemplate.update("call calcula_score()");
	}
	
}
