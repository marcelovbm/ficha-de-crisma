package com.fichaCrisma.ficaCrisma.model;

public class Catequese {

	private String etapa;
	private String catequista;
	private String comunidade;
	private String cidade;
	
	public Catequese(String etapa, String catequista, String comunidade, String cidade) {
		super();
		this.etapa = etapa;
		this.catequista = catequista;
		this.comunidade = comunidade;
		this.cidade = cidade;
	}
	public String getEtapa() {
		return etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	public String getCatequista() {
		return catequista;
	}
	public void setCatequista(String catequista) {
		this.catequista = catequista;
	}
	public String getComunidade() {
		return comunidade;
	}
	public void setComunidade(String comunidade) {
		this.comunidade = comunidade;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

}
