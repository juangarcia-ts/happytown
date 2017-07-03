package model;

public class Praca extends Estabelecimento {
	
	public Praca(){
    	//ATRIBUTOS GERAIS
		this.nome.set("Praça");
        this.custo.set(1500) ;
        this.receita.set(150);
        this.numero_moradores.set(5);
        this.felicidade.set(20);
        //this.estresse.set(0);
        //this.greve.set(false);
        //this.terreno.set(1); ,    
	}	
    
	@Override
	public String getNome() {
		return this.nome.get();
	}
	
	@Override
	public double getCusto() {
		return this.custo.get();
	}

	@Override
	public double getReceita() {
		return this.receita.get();
	}

	@Override
	public int getNumMoradores() {		
		return this.numero_moradores.get();
	}

	@Override
	public int getFelicidade() {		
		return this.felicidade.get();
	}

}
