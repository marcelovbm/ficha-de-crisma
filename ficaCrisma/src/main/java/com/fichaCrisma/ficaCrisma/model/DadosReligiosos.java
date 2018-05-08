package com.fichaCrisma.ficaCrisma.model;

import java.util.Date;

public class DadosReligiosos {

	private Date batismo;
	private String paroquiaDoBatismo;
	private String padrinho;
	private String madrinha;
	private String celebrante;
	private boolean primeiraEucaristia;
	private boolean preCrisma;
	
	
	public DadosReligiosos(Date batismo, String paroquiaDoBatismo, String padrinho, String madrinha, String celebrante,
			boolean primeiraEucaristia, boolean preCrisma) {
		super();
		this.batismo = batismo;
		this.paroquiaDoBatismo = paroquiaDoBatismo;
		this.padrinho = padrinho;
		this.madrinha = madrinha;
		this.celebrante = celebrante;
		this.primeiraEucaristia = primeiraEucaristia;
		this.preCrisma = preCrisma;
	}

	public Date getBatismo() {
		return batismo;
	}
	public void setBatismo(Date batismo) {
		this.batismo = batismo;
	}
	public String getParoquiaDoBatismo() {
		return paroquiaDoBatismo;
	}
	public void setParoquiaDoBatismo(String paroquiaDoBatismo) {
		this.paroquiaDoBatismo = paroquiaDoBatismo;
	}
	public String getPadrinho() {
		return padrinho;
	}
	public void setPadrinho(String padrinho) {
		this.padrinho = padrinho;
	}
	public String getMadrinha() {
		return madrinha;
	}
	public void setMadrinha(String madrinha) {
		this.madrinha = madrinha;
	}
	public String getCelebrante() {
		return celebrante;
	}
	public void setCelebrante(String celebrante) {
		this.celebrante = celebrante;
	}
	public boolean isPrimeiraEucaristia() {
		return primeiraEucaristia;
	}
	public void setPrimeiraEucaristia(boolean primeiraEucaristia) {
		this.primeiraEucaristia = primeiraEucaristia;
	}
	public boolean isPreCrisma() {
		return preCrisma;
	}
	public void setPreCrisma(boolean preCrisma) {
		this.preCrisma = preCrisma;
	}
	
}
