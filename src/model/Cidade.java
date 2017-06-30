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
    public DoubleProperty felicidade = new SimpleDoubleProperty();
    public DoubleProperty imposto = new SimpleDoubleProperty();
    public IntegerProperty terrenosLivres = new SimpleIntegerProperty();
    public IntegerProperty terrenosOcupados = new SimpleIntegerProperty();
    public IntegerProperty mes = new SimpleIntegerProperty();
    
    ArrayList <Estabelecimento> lista_estabelecimento = new ArrayList<>();    
    
    public Cidade(String nome){
    	this.nome.set(nome);
    	this.dinheiro.set(2000);
    	this.populacao.set(20);
    	this.felicidade.set(0.60);
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
    
    public final Integer getPopulacao(){
    	return populacao.get();
    }
    
    public final Double getFelicidade(){
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
    
    public final void setFelicidade(double felicidade){
    	this.felicidade.set(felicidade);
    }
       
    // AUMENTAR E REDUZIR IMPOSTO
    
    public void aumentarImposto(double valor){
        this.imposto.set(imposto.get() + valor);
    }
    
    public void reduzirImposto (double valor){
        this.imposto.set(imposto.get() - valor); 
    }
    
    
    //COMPRA E VENDER TERRENO
    
    public void comprarTerreno(int qtd){  
        if (this.dinheiro.get() > 500 * qtd){
            this.terrenosLivres.set(terrenosLivres.get() + qtd);
            this.dinheiro.set(dinheiro.get()-500 * qtd);
        }
        else{
            System.out.println("Você não tem dinheiro suficiente");
        }   
    }
    
    public void venderTerreno (int qtd){
        if (qtd < this.terrenosLivres.get()){
            this.terrenosLivres.set(terrenosLivres.get() - qtd);
            this.dinheiro.set(dinheiro.get()+(qtd * 200));
        }
        else{
            System.out.println ("Você não tem essa quantidade de terrenos disponiveis");
        }
    }
    
    //ARRECADAR IMPOSTO
    
    public void arrecadarImposto (){
        this.dinheiro.set(populacao.get()*imposto.get());
    }
    
    
    // METODOS DE CONTRUÇÃO E DEMOLIÇÃO
    
  //BANCO
    public void construirBanco (Banco banco){
        if (this.dinheiro.get() > banco.custo.get()){
            this.lista_estabelecimento.add(banco);
            this.terrenosLivres.set(terrenosLivres.get() - banco.terreno.get());
            this.dinheiro.set(dinheiro.get() - banco.custo.get());
            this.felicidade.set(this.felicidade.get() + 0.2);
            this.terrenosOcupados.set(terrenosOcupados.get() + banco.terreno.get());
        }
        else{
            System.out.println("Você não tem dinheiro suficiente");
        }
    }
    
    public void demolirBanco (Banco banco){
        if (dinheiro.get() > (banco.terreno.get() * 200)){
            this.lista_estabelecimento.remove(banco);
            this.terrenosLivres.set(terrenosLivres.get() + banco.terreno.get());
            this.dinheiro.set(dinheiro.get() - banco.terreno.get() * 200);
            this.terrenosOcupados.set(terrenosOcupados.get() - banco.terreno.get());
            this.felicidade.set(felicidade.get() - 0.2);
        }
        else{
             System.out.println("Voc� n�o tem dinheiro suficiente");
        }
    }

    //CASA
    
     public void construirCasa (Casa casa){
        if (dinheiro.get() > casa.custo.get()){
            this.lista_estabelecimento.add(casa);
            this.terrenosLivres.set(terrenosLivres.get() - casa.terreno.get());
            this.terrenosOcupados.set (terrenosOcupados.get() + casa.terreno.get());
            this.dinheiro.set(dinheiro.get() - casa.custo.get());
            this.populacao.set(populacao.get() + casa.numero_moradores.get());
            this.felicidade.set(felicidade.get() + 0.05);
        }
        else{
            System.out.println("Você não tem dinheiro suficiente");
        }
    }
     
     public void demolirCasa (Casa casa){
        if (dinheiro.get() > (casa.terreno.get() * 200)){
            this.lista_estabelecimento.remove(casa);
            this.terrenosLivres.set(terrenosLivres.get() + casa.terreno.get());
            this.terrenosOcupados.set(terrenosOcupados.get() - casa.terreno.get());
            this.dinheiro.set(dinheiro.get() - casa.terreno.get() * 200); //CUSTO DE DEMOLIR
            this.populacao.set(populacao.get() - casa.numero_moradores.get());
            this.felicidade.set(felicidade.get() - 0.2);
        }
        else{
            System.out.println("Você não tem dinheiro suficiente");
        }
    }
     
 //HOSPITAL
     
     public void construirHospital (Hospital hospital){
         if (dinheiro.get() > hospital.custo.get()){
             this.lista_estabelecimento.add(hospital);
             this.terrenosLivres.set(terrenosLivres.get() - hospital.terreno.get());
             this.terrenosOcupados.set (terrenosOcupados.get() + hospital.terreno.get());
             this.dinheiro.set(dinheiro.get() - hospital.custo.get());
             this.felicidade.set(felicidade.get() + 0.4);
         }
         else{
             System.out.println("Você não tem dinheiro suficiente");
         }
     }
      
      public void demolirHospital (Hospital hospital){
         if (dinheiro.get() > (hospital.terreno.get() * 200)){
             this.lista_estabelecimento.remove(hospital);
             this.terrenosLivres.set(terrenosLivres.get() + hospital.terreno.get());
             this.terrenosOcupados.set(terrenosOcupados.get() - hospital.terreno.get());
             this.dinheiro.set(dinheiro.get() - hospital.terreno.get() * 200); //CUSTO DE DEMOLIR
             this.felicidade.set(felicidade.get() - 0.5);
         }
         else{
             System.out.println("Você não tem dinheiro suficiente");
         }
     }
      
      //PRACA
      
      public void construirPraca (Praca praca){
          if (dinheiro.get() > praca.custo.get()){
              this.lista_estabelecimento.add(praca);
              this.terrenosLivres.set(terrenosLivres.get() - praca.terreno.get());
              this.terrenosOcupados.set (terrenosOcupados.get() + praca.terreno.get());
              this.dinheiro.set(dinheiro.get() - praca.custo.get());
              this.felicidade.set(felicidade.get() + 0.2);
          }
          else{
              System.out.println("Você não tem dinheiro suficiente");
          }
      }
       
       public void demolirPraca (Praca praca){
          if (dinheiro.get() > (praca.terreno.get() * 200)){
              this.lista_estabelecimento.remove(praca);
              this.terrenosLivres.set(terrenosLivres.get() + praca.terreno.get());
              this.terrenosOcupados.set(terrenosOcupados.get() - praca.terreno.get());
              this.dinheiro.set(dinheiro.get() - praca.terreno.get() * 200); //CUSTO DE DEMOLIR
              this.felicidade.set(felicidade.get() - 0.1);
          }
          else{
              System.out.println("Você não tem dinheiro suficiente");
          }
      }
    
}