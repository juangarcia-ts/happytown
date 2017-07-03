package model;

//import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
//import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Estabelecimento {
	protected StringProperty nome = new SimpleStringProperty();// NOME
    protected DoubleProperty custo = new SimpleDoubleProperty(); //CUSTO PARA CONSTRUIR
    protected DoubleProperty receita = new SimpleDoubleProperty(); // LUCRO QUE RETORNA
    //protected DoubleProperty estresse = new SimpleDoubleProperty(); // SATISFAÇAO (OU NAO)DOS ESTABELECIMENTOS
    //protected BooleanProperty greve = new SimpleBooleanProperty(); // TRUE PARA "EM GREVE" E FALSE PARA "TRABALHANDO"
    //protected IntegerProperty terreno = new SimpleIntegerProperty();
    protected IntegerProperty numero_moradores = new SimpleIntegerProperty();
    protected IntegerProperty felicidade = new SimpleIntegerProperty();
    
    public abstract String getNome();
    
	public abstract double getCusto();
	
	public abstract double getReceita();
	
	public abstract int getNumMoradores();
	
	public abstract int getFelicidade();
    
   /*     
    protected void entrarGreve (Estabelecimento estabelecimento){
        if (estabelecimento.greve.get() == false){
            if (estabelecimento.estresse.get() >= 0.85 ){
                estabelecimento.receita = 0;
                estabelecimento.greve = true;
            }
        }
    }
    
    protected void sairGreve (Estabelecimento estabelecimento){
        if (estabelecimento.greve == true){    
            if (estabelecimento.estresse <= 0.85 ){
                estabelecimento.receita = 0;
                estabelecimento.greve = false;
            }
        }
    }
    */

}