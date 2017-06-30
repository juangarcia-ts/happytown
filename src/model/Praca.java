package model;

public class Praca extends Estabelecimento {
	
	Praca(){
    	//ATRIBUTOS GERAIS
        this.custo.set(100) ;
        this.receita.set(0);
        this.estresse.set(0);
        this.greve.set(false);
        this.terreno.set(1); 
	}

}
