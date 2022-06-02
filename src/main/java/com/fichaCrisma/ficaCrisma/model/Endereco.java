package com.fichaCrisma.ficaCrisma.model;

public class Endereco {

	private String nome;
	private String numero;
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
	public Endereco(String nome, String numero, String bairro, String telefone1, String telefone2) {
		super();
		this.nome = nome;
		this.numero = numero;
		this.bairro = bairro;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
}
