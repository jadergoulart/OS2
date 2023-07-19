package com.Jader.os2.domain.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.tomcat.jni.OS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Jader.Exceptions.ObjectNotFoundException;
import com.Jader.os2.DTO.OS2DTO;
import com.Jader.os2.domain.Cliente;
import com.Jader.os2.domain.OS2;
import com.Jader.os2.domain.Tecnico;
import com.Jader.os2.domain.enuns.Prioridade;
import com.Jader.os2.domain.enuns.Status;
import com.Jader.os2.domain.repository.OS2Repository;

@Service
public class OS2Service {

	@Autowired
	private OS2Repository repository;
	
	@Autowired
	private TecnicoService tecnicoService;


	@Autowired
	private ClienteService clienteService;

	public OS2 findById(Integer id) {
		Optional<OS2> obj = repository.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("objeto nao encontrado! id:" + id + "Tipo: " + OS.class.getName()));

	}
	public List<OS2> findAll(){
		return repository.findAll();
	}
	public OS2 create(@Valid OS2DTO obj) {
		return fromDTO(obj);
		
	}
	public OS2 update(OS2DTO obj) {
		findById(obj.getId());
		return fromDTO(obj);
	}
	private OS2 fromDTO(OS2DTO obj) {
		OS2 newObj = new OS2();
		newObj.setId(obj.getId());
		newObj.setObservacoes(obj.getObservacoes());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		newObj.setStatus(Status.toEnum(obj.getStatus()));
		
		Tecnico tec = tecnicoService.findByid(obj.getTecnico());
		Cliente cli = clienteService.findByid(obj.getCliente());
	
			newObj.setTecnico(tec);
			newObj.setCliente(cli);
			
			if(newObj.getStatus().getCod().equals(2)) {
				newObj.setDataFechamento(LocalDateTime.now());
			}
			
			return repository.save(newObj);
			
	}

}


