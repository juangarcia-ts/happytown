package model;

public class Casa extends Estabelecimento {
	
    public Casa(){
    	//ATRIBUTOS GERAIS
    	this.nome.set("Casa");
        this.custo.set(100) ;
        this.receita.set(50);
        this.felicidade.set(5);
        //this.estresse.set(0);
        //this.greve.set(false);
        //this.terreno.set(1); 
        this.numero_moradores.set(4);
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
