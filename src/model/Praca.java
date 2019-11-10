package model;

public class Praca extends Estabelecimento {

	public Praca() {
		// ATRIBUTOS GERAIS
		this.nome.set("Praça");
		this.custo.set(1500); // CUSTO DE CONSTRUIR
		this.receita.set(150); // RECEITA DA PRACA
		this.numero_moradores.set(20); // NUMERO DE MORADORES QUE A PRACA ATRAI
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
