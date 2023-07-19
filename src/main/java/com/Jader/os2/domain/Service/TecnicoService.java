package com.Jader.os2.domain.Service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Jader.Exceptions.DataIntegratyviolationException;
import com.Jader.Exceptions.ObjectNotFoundException;
import com.Jader.os2.DTO.tecnicoDTO;
import com.Jader.os2.domain.Pessoa;
import com.Jader.os2.domain.Tecnico;
import com.Jader.os2.domain.repository.PessoaRepository;
import com.Jader.os2.domain.repository.TecnicoRepository;

@Service
public class TecnicoService {
	@Autowired
	private TecnicoRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	public Tecnico findByid(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"objeto nao encontrado! id: " + id + ",Tipo: " + Tecnico.class.getName()));

	}

	public List<Tecnico> findALL() {
		return repository.findAll();

	}

	public Tecnico create(tecnicoDTO objDTO) {
		if (findByCPF(objDTO) != null) {
			throw new DataIntegratyviolationException("Cpf ja cadastrado na base de dados");
		}
		return repository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}

	public Tecnico update(Integer id, @Valid tecnicoDTO objDTO) {
		Tecnico oldObj = findByid(id);

		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyviolationException("Cpf ja cadastrado na base de dados");
		}
		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		Tecnico obj = findByid(id);
		if(obj.getList().size() > 0) {
			throw new DataIntegratyviolationException("Tecnico possui Ordens de Serviço, não pode ser deletado");
		}
		
		repository.deleteById(id);
	}

	private Pessoa findByCPF(tecnicoDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
		if (obj != null) {
			return obj;

		}
		return null;

	}
}
