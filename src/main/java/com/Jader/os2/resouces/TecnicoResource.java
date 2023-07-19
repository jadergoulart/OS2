package com.Jader.os2.resouces;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Jader.os2.DTO.tecnicoDTO;
import com.Jader.os2.domain.Tecnico;
import com.Jader.os2.domain.Service.TecnicoService;
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {
//localhost:8080/tecnicos/1

	@Autowired
	private TecnicoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<tecnicoDTO> findById(@PathVariable Integer id) {
		Tecnico obj = service.findByid(id);
		tecnicoDTO objDTO = new tecnicoDTO(obj);
		return ResponseEntity.ok().body(objDTO);

	}

	@GetMapping
	public ResponseEntity<List<tecnicoDTO>> findALL() {
		List<tecnicoDTO> listDTO = service.findALL().stream().map(obj -> new tecnicoDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	@PostMapping
	public ResponseEntity<tecnicoDTO> create(@Valid @RequestBody tecnicoDTO objDTO){
		Tecnico newObj = service.create(objDTO);
	URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(newObj.getId()).toUri();
	return ResponseEntity.created(uri).build();
	}
	@PutMapping(value = "/{id}")
	public ResponseEntity<tecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody tecnicoDTO objDTO){
		tecnicoDTO newObj = new tecnicoDTO(service.update(id, objDTO));
		return ResponseEntity.ok().body(newObj);
	}
	/*
	 * delete Tecnico
	 */
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}