package model;

public class Casa extends Estabelecimento {

	public Casa() {
		// ATRIBUTOS GERAIS
		this.nome.set("Casa");
		this.custo.set(800); // CUSTO DE CONSTRUIR
		this.receita.set(80); // IPTU
		this.felicidade.set(5); // FELICIDADE
		this.numero_moradores.set(4); // NUMERO DE MORADORES DA CASA
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
