package model;

public class Hospital extends Estabelecimento{
	
	public Hospital(){
    	//ATRIBUTOS GERAIS
		this.nome.set("Hospital");
        this.custo.set(4000) ; // CUSTO DE CONTRUIR
        this.receita.set(400); // RECEITA DO HOSPITAL
        this.numero_moradores.set(30); // NUMERO DE MORADORES QUE O HOSPITAL ATRAI PARA A CIDADE
        this.felicidade.set(20);// FELICIDADE     
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
