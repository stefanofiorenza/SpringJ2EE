package corso.ejb.demo.servizi.basic.io.impl;

import javax.ejb.Stateless;

import corso.ejb.demo.servizi.basic.io.HelloServiceRemote;

@Stateless
public class HelloServiceImpl implements HelloServiceRemote {

	
	public String hello(String nome) {
		// TODO Auto-generated method stub
		return "ciao "+nome;
	}

}
