package com.Jader.os2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.Jader.os2.domain.Service.DbService;

@Configuration
@Profile("Teste")
public class DevConfig {
	@Autowired
	private DbService dbservice;
@Value("${spring.jpa.hibernate.ddl-auto}")

	private String ddl;
	
	public boolean instanciaDB() {
		if(ddl.equals("create"));
		this.dbservice.instanciaDB();
	
	return false;
}

}