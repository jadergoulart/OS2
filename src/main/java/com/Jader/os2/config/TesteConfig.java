package com.Jader.os2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.Jader.os2.domain.Service.DbService;

@Configuration
@Profile("dev")
public class TesteConfig {
	@Autowired
	private DbService dbservice;


	public void instanciaDB() {
		this.dbservice.instanciaDB();

	}
}
