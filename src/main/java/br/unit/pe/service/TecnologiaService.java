package br.unit.pe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unit.pe.repository.TecnologiaRepository;

@Service
public class TecnologiaService {

	@Autowired
	TecnologiaRepository repository;
	
	
}
