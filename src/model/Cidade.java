package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cidade implements Observer {

	private StringProperty nome = new SimpleStringProperty();
	private DoubleProperty dinheiro = new SimpleDoubleProperty();
	private IntegerProperty populacao = new SimpleIntegerProperty();
	private IntegerProperty felicidade = new SimpleIntegerProperty();
	private DoubleProperty imposto = new SimpleDoubleProperty();
	private IntegerProperty mes = new SimpleIntegerProperty();

	private ArrayList<Estabelecimento> lista_estabelecimento = new ArrayList<>();

	public Cidade(String nome) {
		this.nome.set(nome);
		this.dinheiro.set(2000);
		this.populacao.set(20);
		this.felicidade.set(60);
		this.imposto.set(20);
		this.mes.set(1);
	}

	public final String getNome() {
		return nome.get();
	}

	public final Double getDinheiro() {
		return dinheiro.get();
	}

	public final Double getImposto() {
		return imposto.get();
	}

	public final Integer getPopulacao() {
		return populacao.get();
	}

	public final Integer getFelicidade() {
		return felicidade.get();
	}

	public final Integer getMes() {
		return mes.get();
	}

	public final void addMes(int num_meses) {
		mes.set(mes.get() + num_meses);
	}

	public final void setDinheiro(double dinheiro) {
		this.dinheiro.set(dinheiro);
	}

	public final void setPopulacao(int populacao) {
		this.populacao.set(populacao);
	}

	public final void setFelicidade(int felicidade) {
		if (this.felicidade.get() + felicidade <= 100) {
			this.felicidade.set(this.felicidade.get() + felicidade);
		} else {
			this.felicidade.set(100);
		}

	}

	public final ArrayList<Estabelecimento> getEstabelecimentos() {
		return this.lista_estabelecimento;
	}

	// AUMENTAR E REDUZIR IMPOSTO

	public void alterarImposto(double valor) {
		int proporcao = (int) (valor / 50);

		if (proporcao < 1) {
			proporcao = 1;
		}

		int felicidade = 10 * proporcao;

		if (valor > this.getImposto()) {
			this.setFelicidade(-felicidade);
		} else if (valor < this.getImposto()) {
			this.setFelicidade(+felicidade);
		}

		this.imposto.set(valor);

	}

	// ARRECADAR IMPOSTO

	public void arrecadarImposto() {
		this.dinheiro.set(this.dinheiro.get() + populacao.get() * imposto.get());
	}

	// GERAR RECEITA

	public double gerarReceita() {
		double receita = 0;

		for (Estabelecimento estabelecimento : this.lista_estabelecimento)
			receita += estabelecimento.getReceita();

		this.dinheiro.set(this.dinheiro.get() + receita);

		return receita;
	}

	// METODOS DE CONSTRUÇÃO

	// BANCO

	public void construirBanco() throws Exception {
		Banco novo_banco = new Banco();
		novo_banco.construir(this);
	}

	// CASA

	public void construirCasa() throws Exception {
		Casa nova_casa = new Casa();
		nova_casa.construir(this);
	}

	// HOSPITAL

	public void construirHospital() throws Exception {
		Hospital novo_hospital = new Hospital();
		novo_hospital.construir(this);
	}

	// PRACA

	public void construirPraca() throws Exception {
		Praca nova_praca = new Praca();
		nova_praca.construir(this);
	}

	// METODOS DE REMOÇÃO

	public void removerEstabelecimento(int posicao) {
		Estabelecimento estabelecimento = this.lista_estabelecimento.get(posicao);

		this.felicidade.set(this.felicidade.get() - estabelecimento.getFelicidade());
		this.populacao.set(this.populacao.get() - estabelecimento.getNumMoradores());

		this.lista_estabelecimento.remove(posicao);
	}

	@Override
	public void update(Observable novaConstrucao, Object arg1) {		
		if (novaConstrucao instanceof Estabelecimento) {
			Estabelecimento estabelecimento = (Estabelecimento) novaConstrucao;
			
			this.lista_estabelecimento.add(estabelecimento); // ADICIONA O HOSPITAL AO ARRAY
			this.dinheiro.set(dinheiro.get() - estabelecimento.getCusto()); // O PRECO DE CONSTRUCAO E DIMINUIDO DO DINHEIRO DA CIDADE
			this.populacao.set(populacao.get() + estabelecimento.getNumMoradores()); // AUMENTA A POPULACAO
			this.setFelicidade(estabelecimento.getFelicidade());// AUMENTA A FELICIDADE DA CIDADE
        }		
	}
}