package com.Jader.os2.resouces;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Jader.os2.DTO.OS2DTO;
import com.Jader.os2.domain.Service.OS2Service;
@CrossOrigin("*")
@RestController
@RequestMapping(value="/OS2")
public class Os2Resource {
	
	@Autowired
	private OS2Service service;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<OS2DTO>findById(@PathVariable Integer id){
		OS2DTO obj = new OS2DTO( service.findById(id));
		return ResponseEntity.ok().body(obj);
	}
	public ResponseEntity<List<OS2DTO>>findAll(){
		List<OS2DTO> list = service.findAll().stream().map(obj -> new OS2DTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}
	@PostMapping
	public ResponseEntity<OS2DTO> create(@Valid @RequestBody OS2DTO obj){
		obj = new OS2DTO (service.create(obj));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@PostMapping
	public ResponseEntity<OS2DTO>update(@RequestBody OS2DTO obj){
		obj = new OS2DTO(service.update(obj));
		return ResponseEntity.ok().body(obj);
	}
	
}

