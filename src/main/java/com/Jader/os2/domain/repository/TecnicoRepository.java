package com.Jader.os2.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Jader.os2.domain.Tecnico;
public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>{
	@Query("SELECT obj From Tecnico obj WHERE obj.cpf=:cpf")
	Tecnico findByCPF(@Param("cpf") String cpf);

}
