package model;

import java.util.Random;

import application.Controller;
import application.Main;


public class Evento {
	
	//ALEATORIEDADE
    public static void eventoAleatorio(Cidade cidade) throws Exception{    	
    	Random random = new Random();
    	int aleatorio = random.nextInt(3)+1;
    	    	
    	if (aleatorio == 1)
	    	Evento.Furacao(cidade);
    	else if (aleatorio == 2)
	    	Evento.Praga(cidade);
    	else if (aleatorio == 3)
    		Evento.Guerra(cidade);    	
    	
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
    
    public static void Furacao (Cidade cidade) throws Exception{
    	String titulo = "Ocorreu um furacão!";
    	String texto = "Várias propriedades foram atingidas \n"
    				 + "\nÉ necessário reconstruí-las\n"
    				 + "\n-30% de dinheiro\n-20% de felicidade";
    	
		Controller controller = Main.carregarController();
			
		cidade.dinheiro.set(cidade.dinheiro.get() - (0.3*cidade.dinheiro.get()));
		cidade.felicidade.set(cidade.felicidade.get() - 0.20);
			
		controller.alertaEvento(titulo, texto);
    	
    }   
    
    public static void Guerra (Cidade cidade) throws Exception{
    	String titulo = "Sua cidade entrou em guerra!!";
    	String texto = "Aliste a população!!\n"
    				 + "\n-50% de população\n";
    	
		Controller controller = Main.carregarController();
			
		cidade.populacao.set((int)(cidade.populacao.get() - (0.20*cidade.populacao.get())));
			
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
