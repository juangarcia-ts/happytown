package model;

import java.util.Random;

import application.Controller;
import application.Main;


public class Evento {
	
	//ALEATORIEDADE
    public static void eventoAleatorio(Cidade cidade) throws Exception{    	
    	Random random = new Random();
    	int aleatorio = random.nextInt(7)+1;
    	    	
    	if (aleatorio == 1)
	    	Evento.Furacao(cidade);
    	else if (aleatorio == 2)
	    	Evento.Praga(cidade);
    	else if (aleatorio == 3)
    		Evento.Guerra(cidade);  
    	else if (aleatorio == 4)
    		Evento.Poluicao(cidade);
    	else if (aleatorio == 5)
    		Evento.Corrupcao(cidade);
    	else if (aleatorio == 6)
    		Evento.Turismo(cidade);
    	else if (aleatorio == 7)
    		Evento.Festival(cidade);
    	else if (aleatorio == 8)
    		Evento.Emigracao(cidade);
    	
    }
    
    //DESASTRES
    
    public static void Praga (Cidade cidade) throws Exception{ //SUA CIDADE FOI ATINGIDA POR UMA PRAGA E METADE DA POPULAÇAO MORREU    	
    	String titulo = "Uma praga ocorreu!";
    	String texto ="Infelizmente metade de sua popula��o foi dizimada.\n"
    				 + "\nNingu�m realmente poderia esperar por isso\n"
    				 + "\n-50% de popula��o\n-10% de felicidade";
    	
		Controller controller = Main.carregarController();
			
		cidade.populacao.set(cidade.populacao.get()/2);
		cidade.felicidade.set(cidade.felicidade.get() - 10);
			
		controller.alertaEvento(titulo, texto);
		        
    }    
    
    public static void Furacao (Cidade cidade) throws Exception{
    	String titulo = "Ocorreu um furac�o!";
    	String texto ="V�rias propriedades foram atingidas \n"
    				 + "\nSer� necess�rio reconstru�-las\n"
    				 + "\n-400 de dinheiro\n-20% de felicidade";
    	
		Controller controller = Main.carregarController();
			
		cidade.dinheiro.set(cidade.dinheiro.get() - 400);
		cidade.felicidade.set(cidade.felicidade.get() - 20);
			
		controller.alertaEvento(titulo, texto);
    	
    }   
    
    public static void Guerra (Cidade cidade) throws Exception{
    	String titulo ="Sua cidade entrou em guerra!!" ;
    	String texto ="Aliste a popula��o!!\n"
    				 + "\n-50% de popula��o\n";
    	
		Controller controller = Main.carregarController();
			
		cidade.populacao.set((int)(cidade.populacao.get() - (0.50*cidade.populacao.get())));
			
		controller.alertaEvento(titulo, texto);	
    }
    
    public static void Poluicao (Cidade cidade) throws Exception{
    	String titulo = "Sua cidade est� poluindo muito!!";
    	String texto = "Voce recebeu uma multa por desrespeitar o acordo de emissao de gases\n"
    				 + "\n-500 de dinheiro\n";
    	
		Controller controller = Main.carregarController();
			
		cidade.dinheiro.set(cidade.dinheiro.get() - 500);
			
		controller.alertaEvento(titulo, texto);	
    }
    
    public static void Corrupcao (Cidade cidade) throws Exception{
    	String titulo = "Esc�ndalo de Corrup��o!!";
    	String texto = "Not�cias sobre corrupc�o chegaram aos jornais!!\n"
    				 + "\nA populacao est� indignada!!"
    				 + "\n-40% de felicidade\n";
    	
		Controller controller = Main.carregarController();
			
		cidade.felicidade.set(cidade.felicidade.get() - 40);
			
		controller.alertaEvento(titulo, texto);	
    }
    
    public static void Emigracao (Cidade cidade) throws Exception{
    	String titulo = "A populacao esta indo embora!!";
    	String texto = "Um surto de emigracao est� ocorrendo!!\n"
    				 + "\nParece que a cidade vizinha se mostrou muito mais pr�spera"
    				 + "\n-%40 de populacao\n-1000 de dinheiro";
    	
		Controller controller = Main.carregarController();
			
		cidade.populacao.set((int)(cidade.populacao.get() - (0.4*cidade.populacao.get())));
		cidade.dinheiro.set(cidade.dinheiro.get() - 1000);
		controller.alertaEvento(titulo, texto);	
    }
    

    
    // EVENTOS LEGAIS  
    
    public static void Turismo (Cidade cidade) throws Exception{
    	String titulo = "Sua cidade virou ponto tur�stico!!";
    	String texto = "\nParece que sua cidade est� ficando famosa\n"
    				 + "\nMuitos visitantes est�o gastando dinheiro!\n"
    				 + "\n+1200 de dinheiro\n";
    	
		Controller controller = Main.carregarController();
			
		cidade.dinheiro.set(cidade.dinheiro.get() + 1200);
			
		controller.alertaEvento(titulo, texto);	
    }
    
    public static void Festival (Cidade cidade) throws Exception{
    	String titulo = "Um grande festival esta para acontecer!!";
    	String texto = "\nO Rock in Happy esta chegando!!\n"
    				 +"\nSua populacao est� muito animada!!\n"
    				 + "\n+30% de felicidade\n+700 de dinheiro";
    	
		Controller controller = Main.carregarController();
			
		cidade.felicidade.set(cidade.felicidade.get() + 30);
		cidade.dinheiro.set(cidade.dinheiro.get() + 700);
			
		controller.alertaEvento(titulo, texto);	
    }
    
    // COMPRAR ESTABELECIMENTO
    
    public static void compraSucesso() throws Exception{
    	String titulo = "Sucesso!";
    	String texto = "O seu estabelecimento foi comprado com sucesso!"    				 
    				 + "\nAproveite";
    	
		Controller controller = Main.carregarController();
	
		controller.alertaEvento(titulo, texto);	
    }
    
    public static void compraFalha() throws Exception{
    	String titulo = "Que pena!";
    	String texto = "Infelizmente voc� n�o tem dinheiro suficiente!"    				 
    				 + "\nConstrua outro estabelecimento ou junte um pouco mais!";
    	
		Controller controller = Main.carregarController();
	
		controller.alertaEvento(titulo, texto);	
    }
    
    public static void compraOpcaoVazia() throws Exception{
    	String titulo = "Opsss!";
    	String texto = "� necess�rio inserir uma op��o!"    				 
    				 + "\nEscolha um estabelecimento ou cancele a opera��o!";
    	
		Controller controller = Main.carregarController();
	
		controller.alertaEvento(titulo, texto);	
    }
        
    // RECEITA
    
    public static void recolherLucros(Cidade cidade) throws Exception{
    	double receita = cidade.gerarReceita();
    	
    	String titulo = "Oba! Chegou o melhor dia do m�s!";
    	String texto = "Oba! Chegou o melhor dia do m�s!\n"
    				 + "\nHora de arrecadar os impostos e a receita de seus estabelecimentos\n"
    				 + "\nImpostos: F$" + cidade.getPopulacao()*cidade.getImposto() +"\n"
    				 + "\nReceita: F$ " + receita ;
    	
    	Controller controller = Main.carregarController();
    	
    	cidade.arrecadarImposto();  
    	
    	controller.alertaEvento(titulo, texto);
    	
    }
    
    // BOAS-VINDAS
    
    public static void boasVindas(Cidade cidade) throws Exception{
    	String titulo = "Bem vindo a " + cidade.getNome() + "!!\n";
    	String texto =  "A cidade est� ansiosa para o seu governo!!\n"
            + "\nQue tal come�ar construindo uma casa?\n"
            + "\nClique em qualquer terreno e divirta-se!!! ";
        
        Controller controller = Main.carregarController();
        controller.alertaEvento(titulo, texto);
        
    }
    
    
    // GAME OVER
    
    public static void gameOver() throws Exception{
    	String titulo = "GAME OVER";
    	String texto = "Um de seus status chegou a zero!\n"
    				 + "\nInfelizmente seu governo n�o deu certo e "
    				 + "o povo clama por outro representante!\n"
    				 + "\nOBRIGADO POR JOGAR!";
    	
		Controller controller = Main.carregarController();
	
		controller.alertaEvento(titulo, texto);	
    }
}
