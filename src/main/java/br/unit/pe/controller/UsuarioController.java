package br.unit.pe.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.unit.pe.exception.UserNotFoundException;
import br.unit.pe.model.Empresa;
import br.unit.pe.model.EmpresaUsuario;
import br.unit.pe.model.EmpresaUsuarioDTO;
import br.unit.pe.model.EmpresaUsuarioItem;
import br.unit.pe.model.InfoRegistro;
import br.unit.pe.model.Tecnologia;
import br.unit.pe.model.TecnologiaDTO;
import br.unit.pe.model.TecnologiaUsuario;
import br.unit.pe.model.TecnologiaUsuarioDTO;
import br.unit.pe.model.Usuario;
import br.unit.pe.model.UsuarioDTO;
import br.unit.pe.service.RegistroService;
import br.unit.pe.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;

@RestController
public class UsuarioController {
	@Autowired
	UsuarioService service;
	@Autowired
	RegistroService registroService;
	@Autowired
	private ModelMapper modelMapper;
	//@Autowired
	//private ObjectMapper objectMapper;
	
	@GetMapping("/usuarios")
	@ApiOperation(value = "Retorna todos os usuários do Sistema e seus dados pessoais.")
	Collection<Usuario> listar() {
		return service.listar();
	}

	//@GetMapping("/usuarios/findByNome/{nome}")
	Usuario procurar(@PathVariable("nome") String userName) {
		return service.findByNome(userName).orElseThrow(() -> new UserNotFoundException(userName));
	}

	//@GetMapping("/usuarios/findById/{id}")
	Usuario procurar(@PathVariable("id") Long id) {
		return service.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}

	/*@DeleteMapping("/usuarios/{id}")
	ResponseEntity<Void> excluir(@PathVariable("id") long userId) {
		Usuario usuario = procurar(userId);
		service.excluir(usuario);
		return ResponseEntity.noContent().build();
	}*/

	boolean existe(long id) {
		return service.existe(id);
	}

	/*@PutMapping("/usuarios/{id}")
	ResponseEntity<Void> atualizar(@RequestBody Usuario usuario, @PathVariable("id") long userId) {
		if (existe(userId)) {
			usuario.setId(userId);
			service.salvar(usuario);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}*/

	/*@GetMapping("/usuarios/login/{email}/{senha}")
	ResponseEntity<Void> login(@PathVariable("email") String email, @PathVariable("senha") String senha) {
		if (service.login(email, senha)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}*/

	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping(value = "/usuarios", consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation(value = "Registra um usuário no Sistema.")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "", examples =@Example(value = 
            	    @ExampleProperty(
          	    	      mediaType = MediaType.APPLICATION_JSON_VALUE,value = "{\"cause\": null,\r\n"
          	    	      		+ "  \"stackTrace\":[{...}],  \"message\": \"O usuário Luedjisw já existe\",\r\n"
          	    	      		+ "  \"suppressed\": [],\r\n"
          	    	      		+ "  \"localizedMessage\": \"O usuário Luedjisw já existe\"}")))            
			,@ApiResponse(code = 201, message = "Registrado com sucesso",examples = @Example(value = 
			            	    @ExampleProperty(
			            	    	      mediaType = MediaType.APPLICATION_JSON_VALUE,
			            	    	      value = "{\r\n"
			            	    	      		+ "  \"Registro\": {\r\n"
			            	    	      		+ "    \"Usuario\": {\r\n"
			            	    	      		+ "      \"id\": 46,\r\n"
			            	    	      		+ "      \"nome\": \"Luedjisw\",\r\n"
			            	    	      		+ "      \"nascimento\": 487382400000,\r\n"
			            	    	      		+ "      \"score\": null,\r\n"
			            	    	      		+ "      \"email\": \"luss@test.com\",\r\n"
			            	    	      		+ "      \"senha\": \"umasenhasegura10S\",\r\n"
			            	    	      		+ "      \"rua\": \"Rua\",\r\n"
			            	    	      		+ "      \"numero\": \"92\",\r\n"
			            	    	      		+ "      \"complemento\": \"Complemento Bloco C\",\r\n"
			            	    	      		+ "      \"cep\": \"20000000\"\r\n"
			            	    	      		+ "    },\r\n"
			            	    	      		+ "    \"Empresa\": [\r\n"
			            	    	      		+ "      {\r\n"
			            	    	      		+ "        \"id\": 54,\r\n"
			            	    	      		+ "        \"descricao\": \"Uh Tech\"\r\n"
			            	    	      		+ "      },\r\n"
			            	    	      		+ "      {\r\n"
			            	    	      		+ "        \"id\": 39,\r\n"
			            	    	      		+ "        \"descricao\": \"Autonomo\"\r\n"
			            	    	      		+ "      }\r\n"
			            	    	      		+ "    ],\r\n"
			            	    	      		+ "    \"Tecnologias\": [\r\n"
			            	    	      		+ "      {\r\n"
			            	    	      		+ "        \"tecnologia\": \"Java\",\r\n"
			            	    	      		+ "        \"frequenciaDeUso\": 4,\r\n"
			            	    	      		+ "        \"utilizaAtual\": false,\r\n"
			            	    	      		+ "        \"dataIni\": 1458432000000,\r\n"
			            	    	      		+ "        \"dataFim\": 1536710400000\r\n"
			            	    	      		+ "      },\r\n"
			            	    	      		+ "      {\r\n"
			            	    	      		+ "        \"tecnologia\": \"ANT\",\r\n"
			            	    	      		+ "        \"frequenciaDeUso\": 4,\r\n"
			            	    	      		+ "        \"utilizaAtual\": false,\r\n"
			            	    	      		+ "        \"dataIni\": 1466208000000,\r\n"
			            	    	      		+ "        \"dataFim\": 1535587200000\r\n"
			            	    	      		+ "      },\r\n"
			            	    	      		+ "      {\r\n"
			            	    	      		+ "        \"tecnologia\": \"Firebird\",\r\n"
			            	    	      		+ "        \"frequenciaDeUso\": 5,\r\n"
			            	    	      		+ "        \"utilizaAtual\": false,\r\n"
			            	    	      		+ "        \"dataIni\": 1507766400000,\r\n"
			            	    	      		+ "        \"dataFim\": 1608422400000\r\n"
			            	    	      		+ "      }\r\n"
			            	    	      		+ "    ],\r\n"
			            	    	      		+ "    \"Empresa Usuário\": null,\r\n"
			            	    	      		+ "    \"Empresa Usuário Item\": [\r\n"
			            	    	      		+ "      {\r\n"
			            	    	      		+ "        \"empUsu\": {\r\n"
			            	    	      		+ "          \"id\": 73,\r\n"
			            	    	      		+ "          \"empresa\": {\r\n"
			            	    	      		+ "            \"id\": 54,\r\n"
			            	    	      		+ "            \"descricao\": \"Uh Tech\"\r\n"
			            	    	      		+ "          },\r\n"
			            	    	      		+ "          \"usuario\": {\r\n"
			            	    	      		+ "            \"id\": 46,\r\n"
			            	    	      		+ "            \"nome\": \"Luedjisw\",\r\n"
			            	    	      		+ "            \"nascimento\": 487382400000,\r\n"
			            	    	      		+ "            \"score\": null,\r\n"
			            	    	      		+ "            \"email\": \"luss@test.com\",\r\n"
			            	    	      		+ "            \"senha\": \"umasenhasegura10S\",\r\n"
			            	    	      		+ "            \"rua\": \"Rua\",\r\n"
			            	    	      		+ "            \"numero\": \"92\",\r\n"
			            	    	      		+ "            \"complemento\": \"Complemento Bloco C\",\r\n"
			            	    	      		+ "            \"cep\": \"20000000\"\r\n"
			            	    	      		+ "          },\r\n"
			            	    	      		+ "          \"dataIni\": 1444608000000,\r\n"
			            	    	      		+ "          \"dataFim\": 1554163200000,\r\n"
			            	    	      		+ "          \"diversidade\": 3,\r\n"
			            	    	      		+ "          \"complexidade\": 4,\r\n"
			            	    	      		+ "          \"trabalhoAtual\": false,\r\n"
			            	    	      		+ "          \"descricao\": \"Já começou comprando errado\"\r\n"
			            	    	      		+ "        },\r\n"
			            	    	      		+ "        \"tecnologia\": {\r\n"
			            	    	      		+ "          \"id\": 6,\r\n"
			            	    	      		+ "          \"descricao\": \"Java\",\r\n"
			            	    	      		+ "          \"relevancia\": 0.782608695652174\r\n"
			            	    	      		+ "        },\r\n"
			            	    	      		+ "        \"dataIni\": 1458432000000,\r\n"
			            	    	      		+ "        \"dataFim\": 1536710400000,\r\n"
			            	    	      		+ "        \"frequencia\": 4,\r\n"
			            	    	      		+ "        \"ca\": null,\r\n"
			            	    	      		+ "        \"det\": null,\r\n"
			            	    	      		+ "        \"exp\": null,\r\n"
			            	    	      		+ "        \"utilizaAtual\": \"N\"\r\n"
			            	    	      		+ "      },\r\n"
			            	    	      		+ "      {\r\n"
			            	    	      		+ "        \"empUsu\": {\r\n"
			            	    	      		+ "          \"id\": 73,\r\n"
			            	    	      		+ "          \"empresa\": {\r\n"
			            	    	      		+ "            \"id\": 54,\r\n"
			            	    	      		+ "            \"descricao\": \"Uh Tech\"\r\n"
			            	    	      		+ "          },\r\n"
			            	    	      		+ "          \"usuario\": {\r\n"
			            	    	      		+ "            \"id\": 46,\r\n"
			            	    	      		+ "            \"nome\": \"Luedjisw\",\r\n"
			            	    	      		+ "            \"nascimento\": 487382400000,\r\n"
			            	    	      		+ "            \"score\": null,\r\n"
			            	    	      		+ "            \"email\": \"luss@test.com\",\r\n"
			            	    	      		+ "            \"senha\": \"umasenhasegura10S\",\r\n"
			            	    	      		+ "            \"rua\": \"Rua\",\r\n"
			            	    	      		+ "            \"numero\": \"92\",\r\n"
			            	    	      		+ "            \"complemento\": \"Complemento Bloco C\",\r\n"
			            	    	      		+ "            \"cep\": \"20000000\"\r\n"
			            	    	      		+ "          },\r\n"
			            	    	      		+ "          \"dataIni\": 1444608000000,\r\n"
			            	    	      		+ "          \"dataFim\": 1554163200000,\r\n"
			            	    	      		+ "          \"diversidade\": 3,\r\n"
			            	    	      		+ "          \"complexidade\": 4,\r\n"
			            	    	      		+ "          \"trabalhoAtual\": false,\r\n"
			            	    	      		+ "          \"descricao\": \"Já começou comprando errado\"\r\n"
			            	    	      		+ "        },\r\n"
			            	    	      		+ "        \"tecnologia\": {\r\n"
			            	    	      		+ "          \"id\": 54,\r\n"
			            	    	      		+ "          \"descricao\": \"ANT\",\r\n"
			            	    	      		+ "          \"relevancia\": 0.0\r\n"
			            	    	      		+ "        },\r\n"
			            	    	      		+ "        \"dataIni\": 1466208000000,\r\n"
			            	    	      		+ "        \"dataFim\": 1535587200000,\r\n"
			            	    	      		+ "        \"frequencia\": 4,\r\n"
			            	    	      		+ "        \"ca\": null,\r\n"
			            	    	      		+ "        \"det\": null,\r\n"
			            	    	      		+ "        \"exp\": null,\r\n"
			            	    	      		+ "        \"utilizaAtual\": \"N\"\r\n"
			            	    	      		+ "      },\r\n"
			            	    	      		+ "      {\r\n"
			            	    	      		+ "        \"empUsu\": {\r\n"
			            	    	      		+ "          \"id\": 74,\r\n"
			            	    	      		+ "          \"empresa\": {\r\n"
			            	    	      		+ "            \"id\": 39,\r\n"
			            	    	      		+ "            \"descricao\": \"Autonomo\"\r\n"
			            	    	      		+ "          },\r\n"
			            	    	      		+ "          \"usuario\": {\r\n"
			            	    	      		+ "            \"id\": 46,\r\n"
			            	    	      		+ "            \"nome\": \"Luedjisw\",\r\n"
			            	    	      		+ "            \"nascimento\": 487382400000,\r\n"
			            	    	      		+ "            \"score\": null,\r\n"
			            	    	      		+ "            \"email\": \"luss@test.com\",\r\n"
			            	    	      		+ "            \"senha\": \"umasenhasegura10S\",\r\n"
			            	    	      		+ "            \"rua\": \"Rua\",\r\n"
			            	    	      		+ "            \"numero\": \"92\",\r\n"
			            	    	      		+ "            \"complemento\": \"Complemento Bloco C\",\r\n"
			            	    	      		+ "            \"cep\": \"20000000\"\r\n"
			            	    	      		+ "          },\r\n"
			            	    	      		+ "          \"dataIni\": 1507593600000,\r\n"
			            	    	      		+ "          \"dataFim\": 1608422400000,\r\n"
			            	    	      		+ "          \"diversidade\": 1,\r\n"
			            	    	      		+ "          \"complexidade\": 5,\r\n"
			            	    	      		+ "          \"trabalhoAtual\": false,\r\n"
			            	    	      		+ "          \"descricao\": \"A máquina do futuro\"\r\n"
			            	    	      		+ "        },\r\n"
			            	    	      		+ "        \"tecnologia\": {\r\n"
			            	    	      		+ "          \"id\": 55,\r\n"
			            	    	      		+ "          \"descricao\": \"Firebird\",\r\n"
			            	    	      		+ "          \"relevancia\": 0.043478260869565216\r\n"
			            	    	      		+ "        },\r\n"
			            	    	      		+ "        \"dataIni\": 1507766400000,\r\n"
			            	    	      		+ "        \"dataFim\": 1608422400000,\r\n"
			            	    	      		+ "        \"frequencia\": 5,\r\n"
			            	    	      		+ "        \"ca\": null,\r\n"
			            	    	      		+ "        \"det\": null,\r\n"
			            	    	      		+ "        \"exp\": null,\r\n"
			            	    	      		+ "        \"utilizaAtual\": \"N\"\r\n"
			            	    	      		+ "      }\r\n"
			            	    	      		+ "    ],\r\n"
			            	    	      		+ "    \"Tecnologia Usuário\": [\r\n"
			            	    	      		+ "      {\r\n"
			            	    	      		+ "        \"usuario\": {\r\n"
			            	    	      		+ "          \"id\": 46,\r\n"
			            	    	      		+ "          \"nome\": \"Luedjisw\",\r\n"
			            	    	      		+ "          \"nascimento\": 487382400000,\r\n"
			            	    	      		+ "          \"score\": null,\r\n"
			            	    	      		+ "          \"email\": \"luss@test.com\",\r\n"
			            	    	      		+ "          \"senha\": \"umasenhasegura10S\",\r\n"
			            	    	      		+ "          \"rua\": \"Rua\",\r\n"
			            	    	      		+ "          \"numero\": \"92\",\r\n"
			            	    	      		+ "          \"complemento\": \"Complemento Bloco C\",\r\n"
			            	    	      		+ "          \"cep\": \"20000000\"\r\n"
			            	    	      		+ "        },\r\n"
			            	    	      		+ "        \"tecnologia\": {\r\n"
			            	    	      		+ "          \"id\": 58,\r\n"
			            	    	      		+ "          \"descricao\": \"Symphony\",\r\n"
			            	    	      		+ "          \"relevancia\": null\r\n"
			            	    	      		+ "        },\r\n"
			            	    	      		+ "        \"inovatividade\": null,\r\n"
			            	    	      		+ "        \"conheceDesde\": 1594771200000,\r\n"
			            	    	      		+ "        \"estudaDesde\": 1594771200000,\r\n"
			            	    	      		+ "        \"estudouAte\": 1613520000000,\r\n"
			            	    	      		+ "        \"aplicacaoPratica\": 4\r\n"
			            	    	      		+ "      },\r\n"
			            	    	      		+ "      {\r\n"
			            	    	      		+ "        \"usuario\": {\r\n"
			            	    	      		+ "          \"id\": 46,\r\n"
			            	    	      		+ "          \"nome\": \"Luedjisw\",\r\n"
			            	    	      		+ "          \"nascimento\": 487382400000,\r\n"
			            	    	      		+ "          \"score\": null,\r\n"
			            	    	      		+ "          \"email\": \"luss@test.com\",\r\n"
			            	    	      		+ "          \"senha\": \"umasenhasegura10S\",\r\n"
			            	    	      		+ "          \"rua\": \"Rua\",\r\n"
			            	    	      		+ "          \"numero\": \"92\",\r\n"
			            	    	      		+ "          \"complemento\": \"Complemento Bloco C\",\r\n"
			            	    	      		+ "          \"cep\": \"20000000\"\r\n"
			            	    	      		+ "        },\r\n"
			            	    	      		+ "        \"tecnologia\": {\r\n"
			            	    	      		+ "          \"id\": 59,\r\n"
			            	    	      		+ "          \"descricao\": \"Websphere\",\r\n"
			            	    	      		+ "          \"relevancia\": null\r\n"
			            	    	      		+ "        },\r\n"
			            	    	      		+ "        \"inovatividade\": null,\r\n"
			            	    	      		+ "        \"conheceDesde\": 1585180800000,\r\n"
			            	    	      		+ "        \"estudaDesde\": 1585180800000,\r\n"
			            	    	      		+ "        \"estudouAte\": 1616630400000,\r\n"
			            	    	      		+ "        \"aplicacaoPratica\": 2\r\n"
			            	    	      		+ "      }\r\n"
			            	    	      		+ "    ]\r\n"
			            	    	      		+ "  }\r\n"
			            	    	      		+ "}"
			            	    	    )
			            	    	  ) ),
	})
	ResponseEntity<?> registrar(@ApiParam(value="objeto json",example="{\r\n"
			+ "  \"basico\": {\r\n"
			+ "    \"nome\": \"Luedjisw\",\r\n"
			+ "    \"email\": \"luss@test.com\",\r\n"
			+ "    \"nascimento\": \"1985-06-12T03:00:00.000Z\",\r\n"
			+ "    \"senha\": \"umasenhasegura10S\",\r\n"
			+ "    \"endereco\": {\r\n"
			+ "      \"rua\": \"Rua\",\r\n"
			+ "      \"numero\": \"92\",\r\n"
			+ "      \"complemento\": \"Complemento Bloco C\",\r\n"
			+ "      \"cep\": \"20000000\"\r\n"
			+ "    }\r\n"
			+ "  },\r\n"
			+ "  \"profissionais\": [\r\n"
			+ "    {\r\n"
			+ "      \"autonomo\": false,\r\n"
			+ "      \"empresa\": \"Uh Tech\",\r\n"
			+ "      \"dataIni\": \"2015-10-12T03:00:00.000Z\",\r\n"
			+ "      \"dataFim\": \"2019-04-02T03:00:00.000Z\",\r\n"
			+ "      \"trabalhoAtual\": false,\r\n"
			+ "      \"descricao\": \"Já começou comprando errado\",\r\n"
			+ "      \"dificuldade\": 4,\r\n"
			+ "      \"diversidade\": 3,\r\n"
			+ "      \"tecnologias\": [\r\n"
			+ "        {\r\n"
			+ "          \"tecnologia\": \"Java\",\r\n"
			+ "          \"frequenciaDeUso\": 4,\r\n"
			+ "          \"dataIni\": \"2016-03-20T03:00:00.000Z\",\r\n"
			+ "          \"dataFim\": \"2018-09-12T03:00:00.000Z\",\r\n"
			+ "          \"utilizaAtual\": false\r\n"
			+ "        },\r\n"
			+ "        {\r\n"
			+ "          \"tecnologia\": \"ANT\",\r\n"
			+ "          \"frequenciaDeUso\": 4,\r\n"
			+ "          \"dataIni\": \"2016-06-18T03:00:00.000Z\",\r\n"
			+ "          \"dataFim\": \"2018-08-30T03:00:00.000Z\",\r\n"
			+ "          \"utilizaAtual\": false\r\n"
			+ "        }\r\n"
			+ "      ]\r\n"
			+ "    },\r\n"
			+ "    {\r\n"
			+ "      \"autonomo\": true,\r\n"
			+ "      \"dataIni\": \"2017-10-10T03:00:00.000Z\",\r\n"
			+ "      \"dataFim\": \"2020-12-20T03:00:00.000Z\",\r\n"
			+ "      \"trabalhoAtual\": false,\r\n"
			+ "      \"descricao\": \"A máquina do futuro\",\r\n"
			+ "      \"dificuldade\": 5,\r\n"
			+ "      \"diversidade\": 1,\r\n"
			+ "      \"tecnologias\": [\r\n"
			+ "        {\r\n"
			+ "          \"tecnologia\": \"Firebird\",\r\n"
			+ "          \"frequenciaDeUso\": 5,\r\n"
			+ "          \"dataIni\": \"2017-10-12T03:00:00.000Z\",\r\n"
			+ "          \"dataFim\": \"2020-12-20T03:00:00.000Z\",\r\n"
			+ "          \"utilizaAtual\": false\r\n"
			+ "        }\r\n"
			+ "      ],\r\n"
			+ "      \"empresa\": \"Autonomo\"\r\n"
			+ "    }\r\n"
			+ "  ],\r\n"
			+ "  \"pessoais\": [\r\n"
			+ "    {\r\n"
			+ "      \"tecnologia\": \"Symphony\",\r\n"
			+ "      \"aplicacaoPratica\": 4,\r\n"
			+ "      \"dataIni\": \"2020-07-15T03:00:00.000Z\",\r\n"
			+ "      \"dataFim\": \"2021-02-17T03:00:00.000Z\",\r\n"
			+ "      \"maisDe24Meses\": false\r\n"
			+ "    },\r\n"
			+ "    {\r\n"
			+ "      \"tecnologia\": \"Websphere\",\r\n"
			+ "      \"aplicacaoPratica\": 2,\r\n"
			+ "      \"dataIni\": \"2020-03-26T03:00:00.000Z\",\r\n"
			+ "      \"dataFim\": \"2021-03-25T03:00:00.000Z\",\r\n"
			+ "      \"maisDe24Meses\": false\r\n"
			+ "    }\r\n"
			+ "  ],\r\n"
			+ "}")@RequestBody Map<String, Object> payload) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		try {
			UsuarioDTO userRequest = modelMapper.map(payload.get("basico"), UsuarioDTO.class);
			//System.out.println(userRequest);
			Usuario u = modelMapper.map(userRequest, Usuario.class);
			//System.out.println(u);
			u.setRua(userRequest.getEndereco().getRua());
			u.setCep(userRequest.getEndereco().getCep());
			u.setComplemento(userRequest.getEndereco().getComplemento());
			u.setNumero(userRequest.getEndereco().getNumero());
			u.setNascimento(userRequest.getNascimento());
			
			Map<String,Object> objetos = new HashMap<String,Object>();
			objetos.put("usuario",u);
			
			//Usuario usuarioBd = registroService.salvar(u);
			//System.out.println("Usuário salvo no bd: "+ usuarioBd);
			
			ArrayList<Object> a = (ArrayList) payload.get("profissionais");
			List<Empresa> empresasList = new ArrayList<Empresa>();
			List<Empresa> empresasList2 = new ArrayList<Empresa>();
			List<TecnologiaDTO> tecnologiasList = new ArrayList<TecnologiaDTO>();
			List<Tecnologia> tecnologiasList2 = new ArrayList<Tecnologia>();
			List<EmpresaUsuario> empresaUsuarioList = new ArrayList<EmpresaUsuario>();
			List<EmpresaUsuarioItem> empresaUsuarioItensList = new ArrayList<EmpresaUsuarioItem>();
			List<TecnologiaUsuario> tecnologiasUsuario = new ArrayList<TecnologiaUsuario>();
			
			for (Object object : a) {
				EmpresaUsuarioDTO euRequest = modelMapper.map(object, EmpresaUsuarioDTO.class);
				//System.out.println(euRequest);
				
				String descricaoEmpresa = euRequest.getEmpresa().getEmpresa();
				Empresa e = registroService.findEmpresaByDescricao(descricaoEmpresa);
				
				if(e==null) {
					e = new Empresa();
					e.setDescricao(descricaoEmpresa);
					e.setAutonomo(euRequest.isAutonomo()? 'S': 'N');
					empresasList2.add(e);
					//Empresa empresaBd = registroService.salvar(e);
					//e = empresaBd;
					//System.out.println("Empresa salva no bd: "+ e);
				}
				empresasList.add(e);
				//System.out.println(e);
				
				//int dificuldade = 0;
				//int totalTecnologias = 0;
				
				List<TecnologiaDTO>tecnologias = euRequest.getTecnologias();
				
				if(!tecnologias.isEmpty()) {
					tecnologiasList.addAll(tecnologias);
					//totalTecnologias = tecnologias.size();
					for (TecnologiaDTO tecDTO : tecnologias) {
						//dificuldade += tecDTO.getDificuldade();
						
						String descricaoTecnologia = tecDTO.getTecnologia();
						Tecnologia t = registroService.findTecnologiaByDescricao(descricaoTecnologia);
						//System.out.println("DEBUG:"+descricaoTecnologia);
						//System.out.println("DEBUG:" +t);
						if(t == null){
							t = new Tecnologia();
							t.setDescricao(tecDTO.getTecnologia());
							tecnologiasList2.add(t);
							//Tecnologia tecnologiaBd = registroService.salvar(t);
							//System.out.println("Tecnologia no bd:" +tecnologiaBd);
						}
					}
				}
				/*if (dificuldade > 0) {
					dificuldade /= totalTecnologias;
				}*/
				
				EmpresaUsuario eu = new EmpresaUsuario();
				eu.setDataFim(euRequest.getDataFim());
				eu.setDataIni(euRequest.getDataIni());
				eu.setUsuario(u);
				//eu.setUsuario(usuarioBd);
				eu.setTrabalhoAtual(euRequest.isTrabalhoAtual());
				eu.setEmpresa(e);
				eu.setComplexidade(euRequest.getDificuldade());
				//eu.setComplexidade(dificuldade);
				
				eu.setDiversidade(euRequest.getDiversidade());
				if(euRequest.getDescricao()!=null)eu.setDescricao(euRequest.getDescricao());
				//System.out.println(eu);
				//EmpresaUsuario empresaUsuarioBd = registroService.salvar(eu);
				//empresaUsuarioList.add(empresaUsuarioBd);
				//System.out.println("Empresa Usuário salva no bd: "+ empresaUsuarioBd);
				empresaUsuarioList.add(eu);
				
				if(!tecnologias.isEmpty()) {
					for (TecnologiaDTO tecDTO : tecnologias) {
						//EmpresaUsuarioItemDTO euiRequest = modelMapper.map(object, EmpresaUsuarioItemDTO.class);
						//System.out.println(euiRequest);
						EmpresaUsuarioItem eui = new EmpresaUsuarioItem();
						if(tecDTO.getDataFim()!=null)eui.setDataFim(tecDTO.getDataFim());
						if(tecDTO.getDataIni()!=null)eui.setDataIni(tecDTO.getDataIni());
						//eui.setEmpUsu(empresaUsuarioBd);
						eui.setEmpUsu(eu);
						
						String descricaoTecnologia = tecDTO.getTecnologia();
						Tecnologia t = registroService.findTecnologiaByDescricao(descricaoTecnologia);
						if(t==null) {
							t = new Tecnologia();
							t.setDescricao(descricaoTecnologia);
							
						}
						eui.setTecnologia(t);
						eui.setFrequencia(tecDTO.getFrequenciaDeUso());
						eui.setUtilizaAtual(tecDTO.getUtilizaAtual()?'S':'N');
	
						//empresaUsuarioItemBd = registroService.salvar(eui);
						//empresaUsuarioItensBd.add(empresaUsuarioItemBd);
						//System.out.println("Empresa Usuário Item salvo no bd: "+ empresaUsuarioItemBd);
						empresaUsuarioItensList.add(eui);
					}
				}	
			}
			objetos.put("empresas",empresasList2);
			objetos.put("empresas usuario", empresaUsuarioList);
			objetos.put("empresa usuario itens",empresaUsuarioItensList);
			objetos.put("tecnologias", tecnologiasList2);
			
			ArrayList b = (ArrayList) payload.get("pessoais");
			for (Object object : b) {
				TecnologiaDTO t = modelMapper.map(object, TecnologiaDTO.class);
				
				String descricaoTecnologia = t.getTecnologia();
				Tecnologia tec = registroService.findTecnologiaByDescricao(descricaoTecnologia);
				if(tec == null){
					tec = new Tecnologia();
					tec.setDescricao(t.getTecnologia());
					tecnologiasList2.add(tec);
					//Tecnologia tecnologiaBd = registroService.salvar(tec);
					//System.out.println("Tecnologia no bd:" +tecnologiaBd);
				}
				TecnologiaUsuarioDTO tu = modelMapper.map(object, TecnologiaUsuarioDTO.class);
	
				TecnologiaUsuario tecnologiaUsuario = new TecnologiaUsuario();
				tecnologiaUsuario.setConheceDesde(tu.getDataIni());
				tecnologiaUsuario.setTecnologia(tec);
				tecnologiaUsuario.setEstudaDesde(tu.getDataIni());
				tecnologiaUsuario.setEstudouAte(tu.getDataFim());
				//tecnologiaUsuario.setUsuario(usuarioBd);
				tecnologiaUsuario.setUsuario(u);
				//tecnologiaUsuario.setMaisde24Meses(tu.isMaisDe24Meses()?'S':'N');
				tecnologiaUsuario.setAplicacaoPratica(tu.getAplicacaoPratica());
				//TecnologiaUsuario tecnologiaUsuarioBd = registroService.salvar(tecnologiaUsuario);
				//tecnologiasUsuario.add(tecnologiaUsuarioBd);
				//System.out.println(tecnologiasUsuario);
				tecnologiasUsuario.add(tecnologiaUsuario);
			}
			objetos.put("tecnologias usuario", tecnologiasUsuario);
			
			
			Map<String,Object> objetos2 = registroService.salvarRegistro(objetos);
			
			Usuario uBd = (Usuario) objetos2.getOrDefault("usuario", null);
			List<EmpresaUsuario> empresaUsuarioListBd = (ArrayList<EmpresaUsuario>) objetos2.getOrDefault("empresa usuario", null);
			List<EmpresaUsuarioItem> empresaUsuarioItensBd = (ArrayList<EmpresaUsuarioItem>) objetos2.getOrDefault("empresa usuario itens", null);
			List<TecnologiaUsuario> tecnologiasUsuarioBd = (ArrayList<TecnologiaUsuario>) objetos2.getOrDefault("tecnologias usuario", null);
			
			//criando resposta
			//JsonNode userNode = mapper.convertValue(usuarioBd, JsonNode.class);
			JsonNode userNode = mapper.convertValue(uBd, JsonNode.class);		
			JsonNode empresaNode = mapper.convertValue(empresasList, JsonNode.class);
			JsonNode tecnologiasNode  = mapper.convertValue(tecnologiasList, JsonNode.class);
			JsonNode empresaUsuarioNode = mapper.convertValue(empresaUsuarioListBd, JsonNode.class);
			JsonNode empresaUsuarioItensNode = mapper.convertValue(empresaUsuarioItensBd, JsonNode.class);
			JsonNode tecnologiasUsuarioNode = mapper.convertValue(tecnologiasUsuarioBd, JsonNode.class);
			
			ObjectNode registro = mapper.createObjectNode();
			
			registro.set("Usuario",userNode);
			registro.set("Empresa", empresaNode);
			registro.set("Tecnologias", tecnologiasNode);
			registro.set("Empresa Usuário", empresaUsuarioNode);
			registro.set("Empresa Usuário Item", empresaUsuarioItensNode);
			registro.set("Tecnologia Usuário", tecnologiasUsuarioNode);
			
			
			ObjectNode response = mapper.createObjectNode();
			response.set("Registro", registro);
			
			return ResponseEntity.created(null).body(response);
		}catch(Exception e) {
			System.err.println(e.getMessage());
			JsonNode response = mapper.convertValue(e, JsonNode.class);
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping("/usuarios/{id}")
	@ApiOperation(value = "Retorna todas as informações de um usuário do Sistema.")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK",examples =@Example(value = 
            	    @ExampleProperty(
            	    	      mediaType = MediaType.APPLICATION_JSON_VALUE,value = "{\r\n"
            	    	      		+ "  \"empresaUsuarioItens\": [\r\n"
            	    	      		+ "    {\r\n"
            	    	      		+ "      \"empUsu\": {\r\n"
            	    	      		+ "        \"id\": 3,\r\n"
            	    	      		+ "        \"empresa\": {\r\n"
            	    	      		+ "          \"id\": 1,\r\n"
            	    	      		+ "          \"descricao\": \"Kovacek, Lemke and Bayer\",\r\n"
            	    	      		+ "          \"hibernateLazyInitializer\": {}\r\n"
            	    	      		+ "        },\r\n"
            	    	      		+ "        \"usuario\": {\r\n"
            	    	      		+ "          \"id\": 6,\r\n"
            	    	      		+ "          \"nome\": \"daldese2322\",\r\n"
            	    	      		+ "          \"nascimento\": \"1990-10-31T00:00:00.000+00:00\",\r\n"
            	    	      		+ "          \"score\": 542.9856238461539,\r\n"
            	    	      		+ "          \"email\": \"awbcss2sdss222@example.com\",\r\n"
            	    	      		+ "          \"senha\": \"Ceb2yxUP9z1Crmz\",\r\n"
            	    	      		+ "          \"rua\": \"Sheldon Inlet\",\r\n"
            	    	      		+ "          \"numero\": \"254\",\r\n"
            	    	      		+ "          \"complemento\": \"Complem. Apto. 324\",\r\n"
            	    	      		+ "          \"cep\": \"53000000\"\r\n"
            	    	      		+ "        },\r\n"
            	    	      		+ "        \"dataIni\": \"2020-05-19T00:00:00.000+00:00\",\r\n"
            	    	      		+ "        \"dataFim\": \"2021-05-25T00:00:00.000+00:00\",\r\n"
            	    	      		+ "        \"diversidade\": 4,\r\n"
            	    	      		+ "        \"complexidade\": 4,\r\n"
            	    	      		+ "        \"trabalhoAtual\": false,\r\n"
            	    	      		+ "        \"descricao\": \"Velit voluptas velit quo praesentium.\"\r\n"
            	    	      		+ "      },\r\n"
            	    	      		+ "      \"tecnologia\": {\r\n"
            	    	      		+ "        \"id\": 3,\r\n"
            	    	      		+ "        \"descricao\": \"Mongo\",\r\n"
            	    	      		+ "        \"relevancia\": 0.3076923076923077\r\n"
            	    	      		+ "      },\r\n"
            	    	      		+ "      \"dataIni\": \"2016-11-20T00:00:00.000+00:00\",\r\n"
            	    	      		+ "      \"dataFim\": \"2021-05-25T00:00:00.000+00:00\",\r\n"
            	    	      		+ "      \"frequencia\": 3,\r\n"
            	    	      		+ "      \"ca\": 198.0,\r\n"
            	    	      		+ "      \"det\": 0.55242,\r\n"
            	    	      		+ "      \"exp\": 54,\r\n"
            	    	      		+ "      \"utilizaAtual\": \"N\"\r\n"
            	    	      		+ "    },\r\n"
            	    	      		+ "    {\r\n"
            	    	      		+ "      \"empUsu\": {\r\n"
            	    	      		+ "        \"id\": 3,\r\n"
            	    	      		+ "        \"empresa\": {\r\n"
            	    	      		+ "          \"id\": 1,\r\n"
            	    	      		+ "          \"descricao\": \"Kovacek, Lemke and Bayer\",\r\n"
            	    	      		+ "          \"hibernateLazyInitializer\": {}\r\n"
            	    	      		+ "        },\r\n"
            	    	      		+ "        \"usuario\": {\r\n"
            	    	      		+ "          \"id\": 6,\r\n"
            	    	      		+ "          \"nome\": \"daldese2322\",\r\n"
            	    	      		+ "          \"nascimento\": \"1990-10-31T00:00:00.000+00:00\",\r\n"
            	    	      		+ "          \"score\": 542.9856238461539,\r\n"
            	    	      		+ "          \"email\": \"awbcss2sdss222@example.com\",\r\n"
            	    	      		+ "          \"senha\": \"Ceb2yxUP9z1Crmz\",\r\n"
            	    	      		+ "          \"rua\": \"Sheldon Inlet\",\r\n"
            	    	      		+ "          \"numero\": \"254\",\r\n"
            	    	      		+ "          \"complemento\": \"Complem. Apto. 324\",\r\n"
            	    	      		+ "          \"cep\": \"53000000\"\r\n"
            	    	      		+ "        },\r\n"
            	    	      		+ "        \"dataIni\": \"2020-05-19T00:00:00.000+00:00\",\r\n"
            	    	      		+ "        \"dataFim\": \"2021-05-25T00:00:00.000+00:00\",\r\n"
            	    	      		+ "        \"diversidade\": 4,\r\n"
            	    	      		+ "        \"complexidade\": 4,\r\n"
            	    	      		+ "        \"trabalhoAtual\": false,\r\n"
            	    	      		+ "        \"descricao\": \"Velit voluptas velit quo praesentium.\"\r\n"
            	    	      		+ "      },\r\n"
            	    	      		+ "      \"tecnologia\": {\r\n"
            	    	      		+ "        \"id\": 1,\r\n"
            	    	      		+ "        \"descricao\": \"JavaScript\",\r\n"
            	    	      		+ "        \"relevancia\": 0.6923076923076923\r\n"
            	    	      		+ "      },\r\n"
            	    	      		+ "      \"dataIni\": \"2016-08-31T00:00:00.000+00:00\",\r\n"
            	    	      		+ "      \"dataFim\": \"2021-05-25T00:00:00.000+00:00\",\r\n"
            	    	      		+ "      \"frequencia\": 3,\r\n"
            	    	      		+ "      \"ca\": 205.33333333333331,\r\n"
            	    	      		+ "      \"det\": 0.55242,\r\n"
            	    	      		+ "      \"exp\": 56,\r\n"
            	    	      		+ "      \"utilizaAtual\": \"N\"\r\n"
            	    	      		+ "    },\r\n"
            	    	      		+ "    {\r\n"
            	    	      		+ "      \"empUsu\": {\r\n"
            	    	      		+ "        \"id\": 3,\r\n"
            	    	      		+ "        \"empresa\": {\r\n"
            	    	      		+ "          \"id\": 1,\r\n"
            	    	      		+ "          \"descricao\": \"Kovacek, Lemke and Bayer\",\r\n"
            	    	      		+ "          \"hibernateLazyInitializer\": {}\r\n"
            	    	      		+ "        },\r\n"
            	    	      		+ "        \"usuario\": {\r\n"
            	    	      		+ "          \"id\": 6,\r\n"
            	    	      		+ "          \"nome\": \"daldese2322\",\r\n"
            	    	      		+ "          \"nascimento\": \"1990-10-31T00:00:00.000+00:00\",\r\n"
            	    	      		+ "          \"score\": 542.9856238461539,\r\n"
            	    	      		+ "          \"email\": \"awbcss2sdss222@example.com\",\r\n"
            	    	      		+ "          \"senha\": \"Ceb2yxUP9z1Crmz\",\r\n"
            	    	      		+ "          \"rua\": \"Sheldon Inlet\",\r\n"
            	    	      		+ "          \"numero\": \"254\",\r\n"
            	    	      		+ "          \"complemento\": \"Complem. Apto. 324\",\r\n"
            	    	      		+ "          \"cep\": \"53000000\"\r\n"
            	    	      		+ "        },\r\n"
            	    	      		+ "        \"dataIni\": \"2020-05-19T00:00:00.000+00:00\",\r\n"
            	    	      		+ "        \"dataFim\": \"2021-05-25T00:00:00.000+00:00\",\r\n"
            	    	      		+ "        \"diversidade\": 4,\r\n"
            	    	      		+ "        \"complexidade\": 4,\r\n"
            	    	      		+ "        \"trabalhoAtual\": false,\r\n"
            	    	      		+ "        \"descricao\": \"Velit voluptas velit quo praesentium.\"\r\n"
            	    	      		+ "      },\r\n"
            	    	      		+ "      \"tecnologia\": {\r\n"
            	    	      		+ "        \"id\": 2,\r\n"
            	    	      		+ "        \"descricao\": \"NodeJS\",\r\n"
            	    	      		+ "        \"relevancia\": 0.34615384615384615\r\n"
            	    	      		+ "      },\r\n"
            	    	      		+ "      \"dataIni\": \"2016-10-20T00:00:00.000+00:00\",\r\n"
            	    	      		+ "      \"dataFim\": \"2021-05-25T00:00:00.000+00:00\",\r\n"
            	    	      		+ "      \"frequencia\": 5,\r\n"
            	    	      		+ "      \"ca\": 238.33333333333331,\r\n"
            	    	      		+ "      \"det\": 0.6803,\r\n"
            	    	      		+ "      \"exp\": 55,\r\n"
            	    	      		+ "      \"utilizaAtual\": \"N\"\r\n"
            	    	      		+ "    },\r\n"
            	    	      		+ "    {\r\n"
            	    	      		+ "      \"empUsu\": {\r\n"
            	    	      		+ "        \"id\": 4,\r\n"
            	    	      		+ "        \"empresa\": {\r\n"
            	    	      		+ "          \"id\": 2,\r\n"
            	    	      		+ "          \"descricao\": \"Cormier Inc\",\r\n"
            	    	      		+ "          \"hibernateLazyInitializer\": {}\r\n"
            	    	      		+ "        },\r\n"
            	    	      		+ "        \"usuario\": {\r\n"
            	    	      		+ "          \"id\": 6,\r\n"
            	    	      		+ "          \"nome\": \"daldese2322\",\r\n"
            	    	      		+ "          \"nascimento\": \"1990-10-31T00:00:00.000+00:00\",\r\n"
            	    	      		+ "          \"score\": 542.9856238461539,\r\n"
            	    	      		+ "          \"email\": \"awbcss2sdss222@example.com\",\r\n"
            	    	      		+ "          \"senha\": \"Ceb2yxUP9z1Crmz\",\r\n"
            	    	      		+ "          \"rua\": \"Sheldon Inlet\",\r\n"
            	    	      		+ "          \"numero\": \"254\",\r\n"
            	    	      		+ "          \"complemento\": \"Complem. Apto. 324\",\r\n"
            	    	      		+ "          \"cep\": \"53000000\"\r\n"
            	    	      		+ "        },\r\n"
            	    	      		+ "        \"dataIni\": \"2014-02-03T00:00:00.000+00:00\",\r\n"
            	    	      		+ "        \"dataFim\": \"2016-08-31T00:00:00.000+00:00\",\r\n"
            	    	      		+ "        \"diversidade\": 4,\r\n"
            	    	      		+ "        \"complexidade\": 5,\r\n"
            	    	      		+ "        \"trabalhoAtual\": false,\r\n"
            	    	      		+ "        \"descricao\": \"Deserunt eum quia labore autem dolore tenetur.\"\r\n"
            	    	      		+ "      },\r\n"
            	    	      		+ "      \"tecnologia\": {\r\n"
            	    	      		+ "        \"id\": 4,\r\n"
            	    	      		+ "        \"descricao\": \"AngularJS\",\r\n"
            	    	      		+ "        \"relevancia\": 0.3076923076923077\r\n"
            	    	      		+ "      },\r\n"
            	    	      		+ "      \"dataIni\": \"2014-05-02T00:00:00.000+00:00\",\r\n"
            	    	      		+ "      \"dataFim\": \"2021-05-25T00:00:00.000+00:00\",\r\n"
            	    	      		+ "      \"frequencia\": 4,\r\n"
            	    	      		+ "      \"ca\": 364.0,\r\n"
            	    	      		+ "      \"det\": 0.6803,\r\n"
            	    	      		+ "      \"exp\": 84,\r\n"
            	    	      		+ "      \"utilizaAtual\": \"S\"\r\n"
            	    	      		+ "    },\r\n"
            	    	      		+ "    {\r\n"
            	    	      		+ "      \"empUsu\": {\r\n"
            	    	      		+ "        \"id\": 4,\r\n"
            	    	      		+ "        \"empresa\": {\r\n"
            	    	      		+ "          \"id\": 2,\r\n"
            	    	      		+ "          \"descricao\": \"Cormier Inc\",\r\n"
            	    	      		+ "          \"hibernateLazyInitializer\": {}\r\n"
            	    	      		+ "        },\r\n"
            	    	      		+ "        \"usuario\": {\r\n"
            	    	      		+ "          \"id\": 6,\r\n"
            	    	      		+ "          \"nome\": \"daldese2322\",\r\n"
            	    	      		+ "          \"nascimento\": \"1990-10-31T00:00:00.000+00:00\",\r\n"
            	    	      		+ "          \"score\": 542.9856238461539,\r\n"
            	    	      		+ "          \"email\": \"awbcss2sdss222@example.com\",\r\n"
            	    	      		+ "          \"senha\": \"Ceb2yxUP9z1Crmz\",\r\n"
            	    	      		+ "          \"rua\": \"Sheldon Inlet\",\r\n"
            	    	      		+ "          \"numero\": \"254\",\r\n"
            	    	      		+ "          \"complemento\": \"Complem. Apto. 324\",\r\n"
            	    	      		+ "          \"cep\": \"53000000\"\r\n"
            	    	      		+ "        },\r\n"
            	    	      		+ "        \"dataIni\": \"2014-02-03T00:00:00.000+00:00\",\r\n"
            	    	      		+ "        \"dataFim\": \"2016-08-31T00:00:00.000+00:00\",\r\n"
            	    	      		+ "        \"diversidade\": 4,\r\n"
            	    	      		+ "        \"complexidade\": 5,\r\n"
            	    	      		+ "        \"trabalhoAtual\": false,\r\n"
            	    	      		+ "        \"descricao\": \"Deserunt eum quia labore autem dolore tenetur.\"\r\n"
            	    	      		+ "      },\r\n"
            	    	      		+ "      \"tecnologia\": {\r\n"
            	    	      		+ "        \"id\": 5,\r\n"
            	    	      		+ "        \"descricao\": \"SQL\",\r\n"
            	    	      		+ "        \"relevancia\": 0.3076923076923077\r\n"
            	    	      		+ "      },\r\n"
            	    	      		+ "      \"dataIni\": \"2014-03-12T00:00:00.000+00:00\",\r\n"
            	    	      		+ "      \"dataFim\": \"2021-05-25T00:00:00.000+00:00\",\r\n"
            	    	      		+ "      \"frequencia\": 2,\r\n"
            	    	      		+ "      \"ca\": 315.3333333333333,\r\n"
            	    	      		+ "      \"det\": 0.55242,\r\n"
            	    	      		+ "      \"exp\": 86,\r\n"
            	    	      		+ "      \"utilizaAtual\": \"N\"\r\n"
            	    	      		+ "    }\r\n"
            	    	      		+ "  ],\r\n"
            	    	      		+ "  \"tecnologiasUsuario\": [\r\n"
            	    	      		+ "    {\r\n"
            	    	      		+ "      \"usuario\": {\r\n"
            	    	      		+ "        \"id\": 6,\r\n"
            	    	      		+ "        \"nome\": \"daldese2322\",\r\n"
            	    	      		+ "        \"nascimento\": \"1990-10-31T00:00:00.000+00:00\",\r\n"
            	    	      		+ "        \"score\": 542.9856238461539,\r\n"
            	    	      		+ "        \"email\": \"awbcss2sdss222@example.com\",\r\n"
            	    	      		+ "        \"senha\": \"Ceb2yxUP9z1Crmz\",\r\n"
            	    	      		+ "        \"rua\": \"Sheldon Inlet\",\r\n"
            	    	      		+ "        \"numero\": \"254\",\r\n"
            	    	      		+ "        \"complemento\": \"Complem. Apto. 324\",\r\n"
            	    	      		+ "        \"cep\": \"53000000\"\r\n"
            	    	      		+ "      },\r\n"
            	    	      		+ "      \"tecnologia\": {\r\n"
            	    	      		+ "        \"id\": 6,\r\n"
            	    	      		+ "        \"descricao\": \"Java\",\r\n"
            	    	      		+ "        \"relevancia\": 0.6923076923076923\r\n"
            	    	      		+ "      },\r\n"
            	    	      		+ "      \"inovatividade\": 1.0,\r\n"
            	    	      		+ "      \"conheceDesde\": \"2016-08-31T00:00:00.000+00:00\",\r\n"
            	    	      		+ "      \"estudaDesde\": \"2016-08-31T00:00:00.000+00:00\",\r\n"
            	    	      		+ "      \"estudouAte\": \"2021-05-25T00:00:00.000+00:00\",\r\n"
            	    	      		+ "      \"aplicacaoPratica\": 4\r\n"
            	    	      		+ "    },\r\n"
            	    	      		+ "    {\r\n"
            	    	      		+ "      \"usuario\": {\r\n"
            	    	      		+ "        \"id\": 6,\r\n"
            	    	      		+ "        \"nome\": \"daldese2322\",\r\n"
            	    	      		+ "        \"nascimento\": \"1990-10-31T00:00:00.000+00:00\",\r\n"
            	    	      		+ "        \"score\": 542.9856238461539,\r\n"
            	    	      		+ "        \"email\": \"awbcss2sdss222@example.com\",\r\n"
            	    	      		+ "        \"senha\": \"Ceb2yxUP9z1Crmz\",\r\n"
            	    	      		+ "        \"rua\": \"Sheldon Inlet\",\r\n"
            	    	      		+ "        \"numero\": \"254\",\r\n"
            	    	      		+ "        \"complemento\": \"Complem. Apto. 324\",\r\n"
            	    	      		+ "        \"cep\": \"53000000\"\r\n"
            	    	      		+ "      },\r\n"
            	    	      		+ "      \"tecnologia\": {\r\n"
            	    	      		+ "        \"id\": 7,\r\n"
            	    	      		+ "        \"descricao\": \"SQL Server\",\r\n"
            	    	      		+ "        \"relevancia\": 0.3076923076923077\r\n"
            	    	      		+ "      },\r\n"
            	    	      		+ "      \"inovatividade\": 1.0,\r\n"
            	    	      		+ "      \"conheceDesde\": \"2016-11-20T00:00:00.000+00:00\",\r\n"
            	    	      		+ "      \"estudaDesde\": \"2016-11-20T00:00:00.000+00:00\",\r\n"
            	    	      		+ "      \"estudouAte\": \"2021-05-25T00:00:00.000+00:00\",\r\n"
            	    	      		+ "      \"aplicacaoPratica\": 3\r\n"
            	    	      		+ "    }\r\n"
            	    	      		+ "  ]\r\n"
            	    	      		+ "}"))            
  			 ),
	})
	ResponseEntity<?> getInfoRegistro(@PathVariable("id") Long userId) {
		List<TecnologiaUsuario> tecnologiasUsuario = registroService.listTecUsuByIdUsuario(userId);
		//List<EmpresaUsuario> empresasUsuario = registroService.listEmpUsuByIdUsuario(userId);
		List<EmpresaUsuarioItem> empresaUsuarioItens = registroService.listEmpUsuItemByIdUsuario(userId);
		
		InfoRegistro infoRegistro = new InfoRegistro();
		infoRegistro.setEmpresaUsuarioItens(empresaUsuarioItens);
		infoRegistro.setTecnologiasUsuario(tecnologiasUsuario);
		
		return ResponseEntity.ok().body(infoRegistro);
	}
}
