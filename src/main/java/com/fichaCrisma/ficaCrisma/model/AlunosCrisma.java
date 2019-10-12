package com.fichaCrisma.ficaCrisma.model;

import java.util.Date;

public class AlunosCrisma {
	
	private String nome;
	private Date dataNascimento;
	private String idade;
	private String pai;
	private String mae;
	private String estadoCivil;
	private Endereco endereco;
	private DadosReligiosos dadosReligiosos;
	private Catequese catequese;
	
	
	public AlunosCrisma(String nome, Date dataNascimento, String idade, String pai, String mae, String estadoCivil,
			Endereco endereco, DadosReligiosos dadosReligiosos, Catequese catequese) {
		super();
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.idade = idade;
		this.pai = pai;
		this.mae = mae;
		this.estadoCivil = estadoCivil;
		this.endereco = endereco;
		this.dadosReligiosos = dadosReligiosos;
		this.catequese = catequese;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getIdade() {
		return idade;
	}
	public void setIdade(String idade) {
		this.idade = idade;
	}
	public String getPai() {
		return pai;
	}
	public void setPai(String pai) {
		this.pai = pai;
	}
	public String getMae() {
		return mae;
	}
	public void setMae(String mae) {
		this.mae = mae;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public DadosReligiosos getDadosReligiosos() {
		return dadosReligiosos;
	}
	public void setDadosReligiosos(DadosReligiosos dadosReligiosos) {
		this.dadosReligiosos = dadosReligiosos;
	}
	public Catequese getCatequese() {
		return catequese;
	}
	public void setCatequese(Catequese catequese) {
		this.catequese = catequese;
	}
	
	@Override
	public String toString() {
		return "[Aluno: " + this.nome + "; Idade: " + this.idade + "; Endereco: " + this.endereco.getNome() + "; Catequista: " + this.catequese.getCatequista() + "]";
	}
	
}
