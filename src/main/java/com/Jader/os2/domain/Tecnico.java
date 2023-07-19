package com.Jader.os2.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity

public class Tecnico extends Pessoa implements Serializable {
	private static final long serialVersionUID=1l;
	@JsonIgnore
	@OneToMany(mappedBy = "Tecnico")

	private List<OS2> list = new ArrayList<>();
	
	public Tecnico() {
		super();

	}

	public Tecnico(Integer id, String nome, String cpf, String telefone) {
		super(id, nome, cpf, telefone);

	}

	public List<OS2> getList() {
		return list;
	}

	public void setList(List<OS2> list) {
		this.list = list;
	}

}
