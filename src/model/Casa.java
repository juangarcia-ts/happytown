package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Casa extends Estabelecimento {
    protected IntegerProperty numero_moradores = new SimpleIntegerProperty();
    
    Casa(){
    	//ATRIBUTOS GERAIS
        this.custo.set(100) ;
        this.receita.set(0);
        this.estresse.set(0);
        this.greve.set(false);
        this.terreno.set(1); 
        this.numero_moradores.set(4);
    }
    
}
