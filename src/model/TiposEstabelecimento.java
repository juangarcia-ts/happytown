package model;

public enum TiposEstabelecimento {
	BANCO("Banco"), CASA("Casa"), HOSPITAL("Hospital"), PRACA("Pra�a");	
	
	public String nome;
	
	TiposEstabelecimento(String nome){
		this.nome = nome;
	}
}
