package model;

import application.Controller;
import application.Main;


public class Evento {
	
	//ALEATORIEDADE
    public static void eventoAleatorio(int n){
        
    }
    
    //DESASTRES
    
    public static void Praga (Cidade cidade) throws Exception{ //SUA CIDADE FOI ATINGIDA POR UMA PRAGA E METADE DA POPULAÃ‡AO MORREU
    	
    	String titulo = "Uma praga ocorreu!";
    	String texto = "Infelizmente metade de sua população foi dizimada.\n"
    				 + "\nNinguém realmente poderia esperar por isso\n"
    				 + "\n-50% de população\n-10% de felicidade";
    	
		Controller controller = Main.carregarController();
			
		cidade.populacao.set(cidade.populacao.get()/2);
		cidade.felicidade.set(cidade.felicidade.get() - 0.10);
			
		controller.alertaEvento(titulo, texto);
		        
    }
    
    public static void GameOver() throws Exception{
    	String titulo = "GAME OVER";
    	String texto = "Um de seus status chegou a zero!\n"
    				 + "\nInfelizmente seu governo não deu certo e "
    				 + "o povo clama por outro representante!\n"
    				 + "\nGAME OVER";
    				 
    				 
    	
		Controller controller = Main.carregarController();
	
		controller.alertaEvento(titulo, texto);	
    }
}
