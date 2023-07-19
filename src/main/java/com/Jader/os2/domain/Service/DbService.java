package com.Jader.os2.domain.Service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Jader.os2.domain.Cliente;
import com.Jader.os2.domain.OS2;
import com.Jader.os2.domain.Tecnico;
import com.Jader.os2.domain.enuns.Prioridade;
import com.Jader.os2.domain.enuns.Status;
import com.Jader.os2.domain.repository.ClienteRepository;
import com.Jader.os2.domain.repository.OS2Repository;
import com.Jader.os2.domain.repository.TecnicoRepository;

@Service
public class DbService {

	@Autowired
	private TecnicoRepository tecnoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OS2Repository os2Repository;

	public void instanciaDB() {

		Tecnico t1 = new Tecnico(null, "Jader Goulart", "644.350.680-44", "53-999999999");
		Cliente c1 = new Cliente(null, "Betina Soares", "769.659.410-90", "53-999999988");
		OS2 os1 = new OS2(null, null, Prioridade.BAIXA, "Teste Create OD", Status.ANDAMENTO, t1, c1);
		t1.getList().add(os1);
		c1.getList().add(os1);
		tecnoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		os2Repository.saveAll(Arrays.asList(os1));

	}
}
