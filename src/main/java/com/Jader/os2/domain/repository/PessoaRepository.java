package com.Jader.os2.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Jader.os2.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

	@Query("SELECT obj From Pessoa obj WHERE obj.cpf=:cpf")
	Pessoa findByCPF(@Param("cpf") String cpf);

}

