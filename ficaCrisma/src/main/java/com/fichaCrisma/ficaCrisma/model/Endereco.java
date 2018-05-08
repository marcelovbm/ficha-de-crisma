package com.fichaCrisma.ficaCrisma.model;

public class Endereco {

	private String logradouro;
	private String nome;
	private int numero;
	private String bairro;
	private String telefone1;
	private String telefone2;
	
	public String getTelefone1() {
		return telefone1;
	}
	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}
	public String getTelefone2() {
		return telefone2;
	}
	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}
	public Endereco(String logradouro, String nome, int numero, String bairro, String telefone1, String telefone2) {
		super();
		this.logradouro = logradouro;
		this.nome = nome;
		this.numero = numero;
		this.bairro = bairro;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
}
