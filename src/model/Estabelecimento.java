package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Estabelecimento {
    protected DoubleProperty custo = new SimpleDoubleProperty(); //CUSTO PARA CONSTRUIR
    protected DoubleProperty receita = new SimpleDoubleProperty(); // LUCRO QUE RETORNA
    protected DoubleProperty estresse = new SimpleDoubleProperty(); // SATISFAÇAO (OU NAO)DOS ESTABELECIMENTOS
    protected BooleanProperty greve = new SimpleBooleanProperty(); // TRUE PARA "EM GREVE" E FALSE PARA "TRABALHANDO"
    protected IntegerProperty terreno = new SimpleIntegerProperty();
   
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