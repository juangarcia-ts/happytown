package model;

import java.util.Observable;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Estabelecimento extends Observable {
	protected StringProperty nome = new SimpleStringProperty();// NOME
    protected DoubleProperty custo = new SimpleDoubleProperty(); //CUSTO PARA CONSTRUIR
    protected DoubleProperty receita = new SimpleDoubleProperty(); // LUCRO QUE RETORNA
    protected IntegerProperty numero_moradores = new SimpleIntegerProperty(); // MORADORES QUE SAO ATRAIDOS PELO ESTABELECIMENTO
    protected IntegerProperty felicidade = new SimpleIntegerProperty(); // FELICIDADE
    
    public abstract String getNome();
    
	public abstract double getCusto();
	
	public abstract double getReceita();
	
	public abstract int getNumMoradores();
	
	public abstract int getFelicidade();   
	
	public void construir(Cidade cidade) throws Exception {
		this.addObserver(cidade);
		
		if (cidade.getDinheiro() > custo.get()) {
			setChanged();
		    notifyObservers(this);
			return;
		} 
		
		throw new Exception("Sem dinheiro");	
	}
}