package br.unit.pe.util;

import java.util.List;
import java.util.Objects;

import br.unit.pe.model.EmpresaUsuario;
import br.unit.pe.model.EmpresaUsuarioItem;
import br.unit.pe.model.TecnologiaUsuario;

public class Calculos {

	public static void relevancia(int totalUsers, List<EmpresaUsuario> empList, List<EmpresaUsuarioItem> iteList,
			List<TecnologiaUsuario> tecUsuList) {
		/*Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 2);
		// data de dois anos atrás da new Date
		Date date = cal.getTime();

		// não aceita objetos iguais
		Set<Registro> registros = new HashSet<Registro>();
		if (empList != null) {
			for (EmpresaUsuario emp : empList) {
				for (EmpresaUsuarioItem ite : iteList) {
					if (emp.getId() == ite.getEmpUsuId()) {
						if (ite.getDataFim() != null) {
							if (ite.getDataFim().after(date) || ite.getDataFim().equals(date)) {
								registros.add(new Registro(ite.getTecnologiaId(), emp.getIdUsuario()));
							}
						}

						else if (emp.getDataFim() != null)
							if (emp.getDataFim().after(date) || emp.getDataFim().equals(date)) {
								registros.add(new Registro(ite.getTecnologiaId(), emp.getIdUsuario()));
							}
					} else {
						if (new Date().after(date) || new Date().equals(date)) {
							registros.add(new Registro(ite.getTecnologiaId(), emp.getIdUsuario()));
						}
					}
				}
			}
		}
		if (tecUsuList != null) {
			for (TecnologiaUsuario tecusu : tecUsuList) {
				if (tecusu.getEstudaDesde() != null) {
					/*registros.add(new Registro(tecusu.getTecnologia().getId()
							, tecusu.getUsuario().getId()));*//*
				}
			}
		}
		if (registros != null) {
			 Map<Long, Integer> mapIdTecnologia = registros.stream().
					 collect(Collectors.groupingBy(Registro::getIdTecnologia
					 ,Collectors.collectingAndThen(
							 Collectors.mapping(Registro::getIdTecnologia
									 ,Collectors.toSet()), 
							 Set::size)
					 ));
			 /*Map<Long, Integer> mapIdUsuario = registros.stream().
					 collect(Collectors.groupingBy(Registro::getIdUsuario
					 ,Collectors.collectingAndThen(
							 Collectors.mapping(Registro::getIdUsuario
									 ,Collectors.toSet()), 
							 Set::size)
					 ));*/
			/*for(Entry<Long, Integer> entry:mapIdTecnologia.entrySet()) {
				 //System.out.println(entry.getKey()+ " : "+entry.getValue());
			     Double relevancia = (double) ((double) entry.getValue() / (double) totalUsers);
				 System.out.printf("%.3f", relevancia);
				 System.out.println();			        
			 }
			 /*for(Entry<Long, Integer> entry:mapIdUsuario.entrySet()) {
			   		System.out.println(entry.getKey()+ " : "+entry.getValue());
			 }*/
		//}
	}

	public static class Registro {
		private Long idTecnologia;
		private Long idUsuario;

		public Registro(Long idTecnologia, Long idUsuario) {
			this.idTecnologia = idTecnologia;
			this.idUsuario = idUsuario;
		}

		public Long getIdTecnologia() {
			return idTecnologia;
		}

		public void setIdTecnologia(Long idTecnologia) {
			this.idTecnologia = idTecnologia;
		}

		public Long getIdUsuario() {
			return idUsuario;
		}

		public void setIdUsuario(Long idUsuario) {
			this.idUsuario = idUsuario;
		}

		@Override
		public int hashCode() {
			return Objects.hash(idTecnologia, idUsuario);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Registro other = (Registro) obj;
			return Objects.equals(idTecnologia, other.idTecnologia) && Objects.equals(idUsuario, other.idUsuario);
		}

	}
}
