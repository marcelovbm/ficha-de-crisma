package com.fichaCrisma.ficaCrisma.model;

import java.util.Date;

public class AlunosCrisma {

	private String nome;
	private Date dataNascimento;
	private String idade;
	private String sexo;
	private String pai;
	private String mae;
	private String estadoCivil;
	private Endereco endereco;
	private DadosReligiosos dadosReligiosos;
	private Catequese catequese;


	public AlunosCrisma(String nome, Date dataNascimento, String idade, String sexo, String pai, String mae, String estadoCivil,
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
		this.sexo = sexo;
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
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	@Override
	public String toString() {
		return "[Aluno: " + this.nome + "; Idade: " + this.idade + "; Endereco: " + this.endereco.getNome() + "; Catequista: " + this.catequese.getCatequista() + "]";
	}

}
