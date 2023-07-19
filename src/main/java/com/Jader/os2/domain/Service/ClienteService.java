package com.Jader.os2.domain.Service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Jader.Exceptions.DataIntegratyviolationException;
import com.Jader.Exceptions.ObjectNotFoundException;
import com.Jader.os2.DTO.ClienteDTO;
import com.Jader.os2.domain.Cliente;
import com.Jader.os2.domain.Pessoa;
import com.Jader.os2.domain.repository.ClienteRepository;
import com.Jader.os2.domain.repository.PessoaRepository;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	public Cliente findByid(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"objeto nao encontrado! id: " + id + ",Tipo: " + Cliente.class.getName()));

	}

	public List<Cliente> findALL() {
		return repository.findAll();

	}

	public Cliente create(ClienteDTO objDTO) {
		if (findByCPF(objDTO) != null) {
			throw new DataIntegratyviolationException("Cpf ja cadastrado na base de dados");
		}
		return repository.save(new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}

	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		Cliente oldObj = findByid(id);

		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyviolationException("Cpf ja cadastrado na base de dados");
		}
		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		Cliente obj = findByid(id);
		if(obj.getList().size() > 0) {
			throw new DataIntegratyviolationException("Cliente possui Ordens de Serviço, não pode ser deletado");
		}
		
		repository.deleteById(id);
	}

	private Pessoa findByCPF(ClienteDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
		if (obj != null) {
			return obj;

		}
		return null;

	}
}
