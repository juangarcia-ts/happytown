package model;

import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.    property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cidade {
	
    public StringProperty nome = new SimpleStringProperty();
    public DoubleProperty dinheiro = new SimpleDoubleProperty();
    public IntegerProperty populacao = new SimpleIntegerProperty();
    public IntegerProperty felicidade = new SimpleIntegerProperty();
    public DoubleProperty imposto = new SimpleDoubleProperty();
    public IntegerProperty terrenosLivres = new SimpleIntegerProperty();
    public IntegerProperty terrenosOcupados = new SimpleIntegerProperty();
    public IntegerProperty mes = new SimpleIntegerProperty();
    
    public ArrayList <Estabelecimento> lista_estabelecimento = new ArrayList<>();    
    
    public Cidade(String nome){
    	this.nome.set(nome);
    	this.dinheiro.set(2000);
    	this.populacao.set(20);
    	this.felicidade.set(60);
    	this.imposto.set(20);
        this.terrenosLivres.set(10);
        this.terrenosOcupados.set(5); // 1 CASA A CADA 4 PESSOAS
        this.mes.set(1);
    }
        
    public final String getNome(){
    	return nome.get();
    }
    
    public final Double getDinheiro(){
    	return dinheiro.get();
    }
    
    public final Double getImposto(){
    	return imposto.get();
    }
    
    public final Integer getPopulacao(){
    	return populacao.get();
    }
    
    public final Integer getFelicidade(){
    	return felicidade.get();
    }
    public final Integer getMes(){
    	return mes.get();
    }
    
    public final void addMes(int num_meses){
    	mes.set(mes.get() + num_meses);
    }
    
    public final void setDinheiro(double dinheiro){
    	this.dinheiro.set(dinheiro);
    }
    
    public final void setPopulacao(int populacao){
    	this.populacao.set(populacao);
    }
    
    public final void setFelicidade(int felicidade){
    	if (this.felicidade.get() + felicidade <= 100){
    		this.felicidade.set(this.felicidade.get() + felicidade);
    	}else{
    		this.felicidade.set(100);
    	}
    		
    }
       
    // AUMENTAR E REDUZIR IMPOSTO
    
    public void alterarImposto(double valor){
    	int proporcao = (int)(valor / 50);
    	
    	if (proporcao < 1){
    		proporcao = 1;
    	}
    	
    	int felicidade = 3 * proporcao;
    	
    	if (valor > this.getImposto()){    		
    		this.setFelicidade(-felicidade);
    	}else if (valor < this.getImposto()){
    		System.out.println("ue2");
    		this.setFelicidade(+felicidade);
    	}
    	
        this.imposto.set(valor);        
        
    }
      
    //ARRECADAR IMPOSTO
    
    public void arrecadarImposto(){
        this.dinheiro.set(this.dinheiro.get() + populacao.get()*imposto.get());
    }
    
    //GERAR RECEITA
    
    public double gerarReceita(){
    	double receita = 0;
    	
    	for (Estabelecimento estabelecimento: this.lista_estabelecimento)
    		receita += estabelecimento.getReceita();
    	
    	this.dinheiro.set(this.dinheiro.get() + receita);
    	
    	return receita;
    	
    }
    
    // METODOS DE CONSTRUÇÃO
    
    //BANCO
    
    public boolean construirBanco (){
    	Banco novo_banco = new Banco(); 
    	 
        if (dinheiro.get() > novo_banco.getCusto()){
            this.lista_estabelecimento.add(novo_banco);            
            this.dinheiro.set(dinheiro.get() - novo_banco.getCusto());
            this.populacao.set(populacao.get() + novo_banco.getNumMoradores());
            this.setFelicidade(novo_banco.getFelicidade());
            return true;
        }else{
            return false;
        }
    } 

    //CASA
    
    public boolean construirCasa (){
    	Casa nova_casa = new Casa(); 
    	 
        if (dinheiro.get() > nova_casa.getCusto()){
            this.lista_estabelecimento.add(nova_casa);            
            this.dinheiro.set(dinheiro.get() - nova_casa.getCusto());
            this.populacao.set(populacao.get() + nova_casa.getNumMoradores());
            this.setFelicidade(nova_casa.getFelicidade());
            return true;
        }else{
            return false;
        }
    }
     
    //HOSPITAL
     
    public boolean construirHospital (){
     	 Hospital novo_hospital = new Hospital(); 
     	 
         if (dinheiro.get() > novo_hospital.getCusto()){
             this.lista_estabelecimento.add(novo_hospital);            
             this.dinheiro.set(dinheiro.get() - novo_hospital.getCusto());
             this.populacao.set(populacao.get() + novo_hospital.getNumMoradores());
             this.setFelicidade(novo_hospital.getFelicidade());
             return true;
         }else{
             return false;
         }
    }
     
    //PRACA
      
    public boolean construirPraca (){
     	 Praca nova_praca = new Praca(); 
     	 
         if (dinheiro.get() > nova_praca.getCusto()){
             this.lista_estabelecimento.add(nova_praca);            
             this.dinheiro.set(dinheiro.get() - nova_praca.getCusto());
             this.populacao.set(populacao.get() + nova_praca.getNumMoradores());
             this.setFelicidade(nova_praca.getFelicidade());
             return true;
         }else{
             return false;
         }
    }
    
}