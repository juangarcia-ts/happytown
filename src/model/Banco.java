package model;

public class Banco extends Estabelecimento {
    public Banco(){
        //ATRIBUTOS GERAIS
    	this.nome.set("Banco");
        this.custo.set(3000); // CUSTO DE CONSTRUIR
        this.receita.set(300); //RECEITA DO BANCO
        this.felicidade.set(20); // FELICIDADE
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
