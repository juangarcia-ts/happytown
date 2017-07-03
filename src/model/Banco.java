package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Banco extends Estabelecimento {
    protected DoubleProperty juros = new SimpleDoubleProperty();;
    protected DoubleProperty divida = new SimpleDoubleProperty();
    protected BooleanProperty quite = new SimpleBooleanProperty(true); //SE O ULTIMO EMPRESTIMO NAO TIVER SIDO PAGO, NAO SERA POSSIVEL PEDIR OUTRO
  
    public Banco(){
        //ATRIBUTOS GERAIS
    	this.nome.set("Banco");
        this.custo.set(3000);
        this.receita.set(300);
        this.felicidade.set(20);
        //this.estresse.set(0.5);
        //this.greve.set(false);
        //this.terreno.set(3); 
        
        //ATRIBUTOS PROPRIOS
    	this.juros.set(0.1);
    	this.divida.set(0);
    	this.quite.set(true);
        
    }
    
    @Override
	public String getNome() {
		return this.nome.get();
	}
    
    public void pedirEmprestimo (Cidade cidade, Banco banco, double valor){
        if (cidade.lista_estabelecimento.contains(banco)){   
            if (this.quite.get() == true){
                cidade.dinheiro.set(valor + cidade.getDinheiro());
                this.quite.set(false);
                this.divida.set(valor);
            }  
            else{
                System.out.println("VocÊ não pagou o ultimo emprestimo");
            }
        }
        else{
                System.out.println("Esse estabelecimento não existe");
            }
    }
    
    public void pagarEmprestimo (Cidade cidade, Banco banco){
        if (cidade.lista_estabelecimento.contains(banco)){
            if (this.quite.get() == false){
                cidade.dinheiro.set(cidade.dinheiro.get() - (((divida.get())*juros.get())+ divida.get()));
                this.divida.set(0);
                this.quite.set(true);
            }
        }
        else{
                System.out.println("Esse estabelecimento não existe");
            }
    }
    
    public void verDivida (Cidade cidade){
        System.out.println("A divida da cidade é de: R$ " + this.divida.get());
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
