package model;

import application.Controller;
import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class Evento {
    public static void eventoAleatorio(int n){
        
    }
    
    //DESASTRES
    
    public static void Praga (Cidade cidade) throws Exception{ //SUA CIDADE FOI ATINGIDA POR UMA PRAGA E METADE DA POPULAÃ‡AO MORREU
    	
    	String titulo = "Uma praga ocorreu!";
    	String texto = "Infelizmente metade de sua população foi dizimada.\n"
    				 + "\nNinguém realmente poderia esperar por isso\n"
    				 + "\n-50% de população\n-10% de felicidade";
    	
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("main.fxml"));
		@SuppressWarnings("unused")
		Parent root = loader.load();
		Controller controller = (Controller)loader.getController();	
			
		cidade.populacao.set(cidade.populacao.get()/2);
		cidade.felicidade.set(cidade.felicidade.get() - 0.1);
			
		controller.alertaEvento(titulo, texto);
		        
    }
    
    public static void Furacao (Cidade cidade){
        
    }
}
