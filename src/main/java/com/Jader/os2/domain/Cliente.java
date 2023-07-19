package com.Jader.os2.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Configuration;


@Configuration
@Entity
public class Cliente extends Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	@OneToMany(mappedBy = "Cliente")
	private List<OS2> list = new ArrayList<>();

	public Cliente() {
		super();

	}

	public Cliente(Integer id, String nome, String cpf, String telefone) {
		super(id, nome, cpf, telefone);

	}

	public List<OS2> getList() {
		return list;
	}

	public void setList(List<OS2> list) {
		this.list = list;
	}

}