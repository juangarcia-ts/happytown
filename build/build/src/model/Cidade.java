package model;

import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.    property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cidade {
	
    private StringProperty nome = new SimpleStringProperty();
    private DoubleProperty dinheiro = new SimpleDoubleProperty();
    private IntegerProperty populacao = new SimpleIntegerProperty();
    private IntegerProperty felicidade = new SimpleIntegerProperty();
    private DoubleProperty imposto = new SimpleDoubleProperty();
    private IntegerProperty mes = new SimpleIntegerProperty();
    
    private ArrayList <Estabelecimento> lista_estabelecimento = new ArrayList<>();    
    
    public Cidade(String nome){
    	this.nome.set(nome);
    	this.dinheiro.set(2000);
    	this.populacao.set(20);
    	this.felicidade.set(60);
    	this.imposto.set(20);
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
    
    public final ArrayList<Estabelecimento> getEstabelecimentos(){
    	return this.lista_estabelecimento;
    }
       
    // AUMENTAR E REDUZIR IMPOSTO
    
    public void alterarImposto(double valor){
    	int proporcao = (int)(valor / 50);
    	
    	if (proporcao < 1){
    		proporcao = 1;
    	}
    	
    	int felicidade = 10 * proporcao;
    	
    	if (valor > this.getImposto()){    		
    		this.setFelicidade(-felicidade);
    	}else if (valor < this.getImposto()){    		
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
    	 
        if (dinheiro.get() >= novo_banco.getCusto()){ // VERIFICA SE POSSUI DINHEIRO PRA CONSTRUIR
            this.lista_estabelecimento.add(novo_banco);  //ADICIONA O BANCO AO ARRAY         
            this.dinheiro.set(dinheiro.get() - novo_banco.getCusto()); //O PRECO DE CONSTRUCAO E DIMINUIDO DO DINHEIRO DA CIDADE
            this.setFelicidade(novo_banco.getFelicidade()); //AUMENTA A FELICIDADE DA CIDADE
            return true;
        }else{
            return false;
        }
    } 

    //CASA
    
    public boolean construirCasa (){
    	Casa nova_casa = new Casa(); 
    	 
        if (dinheiro.get() >= nova_casa.getCusto()){ // VERIFICA SE POSSUI DINHEIRO PRA CONSTRUIR
            this.lista_estabelecimento.add(nova_casa);  // ADICIONA A CASA AO ARRAY           
            this.dinheiro.set(dinheiro.get() - nova_casa.getCusto());//O PRECO DE CONSTRUCAO E DIMINUIDO DO DINHEIRO DA CIDADE
            this.populacao.set(populacao.get() + nova_casa.getNumMoradores()); // AUMENTA A POPULACAO POR MEIO DOS MORADORES DA CASA
            this.setFelicidade(nova_casa.getFelicidade()); // AUMENTA A FELICIDADE DA CIDADE
            return true;
        }else{
            return false;
        }
    }
     
    //HOSPITAL
     
    public boolean construirHospital (){
     	 Hospital novo_hospital = new Hospital(); 
     	 
         if (dinheiro.get() >= novo_hospital.getCusto()){ // VERIFICA SE POSSUI DINHEIRO PRA CONSTRUIR
             this.lista_estabelecimento.add(novo_hospital);         // ADICIONA O HOSPITAL AO ARRAY   
             this.dinheiro.set(dinheiro.get() - novo_hospital.getCusto()); //O PRECO DE CONSTRUCAO E DIMINUIDO DO DINHEIRO DA CIDADE
             this.populacao.set(populacao.get() + novo_hospital.getNumMoradores()); // AUMENTA A POPULACAO
             this.setFelicidade(novo_hospital.getFelicidade());// AUMENTA A FELICIDADE DA CIDADE
             return true;
         }else{
             return false;
         }
    }
     
    //PRACA
      
    public boolean construirPraca (){
     	 Praca nova_praca = new Praca(); 
     	 
         if (dinheiro.get() >= nova_praca.getCusto()){ // VERIFICA SE POSSUI DINHEIRO PRA CONSTRUIR  
             this.lista_estabelecimento.add(nova_praca);     // ADICIONA A PRACA AO ARRAY  
             this.dinheiro.set(dinheiro.get() - nova_praca.getCusto()); //O PRECO DE CONSTRUCAO E DIMINUIDO DO DINHEIRO DA CIDADE
             this.populacao.set(populacao.get() + nova_praca.getNumMoradores());// AUMENTA A POPULACAO
             this.setFelicidade(nova_praca.getFelicidade());// AUMENTA A FELICIDADE DA CIDADE
             return true;
         }else{
             return false;
         }
    }
    
    // METODOS DE REMOÇÃO
    
    public void removerEstabelecimento(int posicao){
    	Estabelecimento estabelecimento = this.lista_estabelecimento.get(posicao);
    	
    	this.felicidade.set(this.felicidade.get() - estabelecimento.getFelicidade());
    	this.populacao.set(this.populacao.get() - estabelecimento.getNumMoradores());
    	
    	this.lista_estabelecimento.remove(posicao);
    }
    
    
    
}