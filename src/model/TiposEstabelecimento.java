package model;

public enum TiposEstabelecimento {
	BANCO("Banco"), CASA("Casa"), HOSPITAL("Hospital"), PRACA("Praça");	
	
	public String nome;
	
	TiposEstabelecimento(String nome){
		this.nome = nome;
	}
}
