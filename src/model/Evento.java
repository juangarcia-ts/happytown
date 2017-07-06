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
    
    public static void Praga (Cidade cidade) throws Exception{ //SUA CIDADE FOI ATINGIDA POR UMA PRAGA E METADE DA POPULACAO MORREU    	
    	String titulo = "Uma praga ocorreu!"; // REDUZ POPULACAO E FELICIDADE
    	String texto ="Infelizmente metade de sua população foi dizimada.\n"
    				 + "\nNinguém realmente poderia esperar por isso\n"
    				 + "\n-50% de população\n-10% de felicidade";
    	
		Controller controller = Main.carregarController();
			
		cidade.setPopulacao(cidade.getPopulacao()/2);
		cidade.setFelicidade(cidade.getFelicidade() - 10);
			
		controller.alertaEvento(titulo, texto);
		        
    }    
    
    public static void Furacao (Cidade cidade) throws Exception{ //SUA CIDADE FOI ATINGIDA POR UM FURACAO E AS CASAS FORAM DESTRUIDAS
    	String titulo = "Ocorreu um furacão!"; // REDUZ DINHEIRO E FELICIDADE
    	String texto ="Várias propriedades foram atingidas \n"
    				 + "\nSerá necessário reconstruí-las\n"
    				 + "\n-750 de dinheiro\n-20% de felicidade";
    	
		Controller controller = Main.carregarController();
			
		cidade.setDinheiro(cidade.getDinheiro() - 750);
		cidade.setFelicidade(cidade.getFelicidade() - 20);
			
		controller.alertaEvento(titulo, texto);
    	
    }   
    
    public static void Guerra (Cidade cidade) throws Exception{ // SUA CIDADE ENTROU EM GUERRA E PERDE METADE DA POPULACAO
    	String titulo ="Sua cidade entrou em guerra!!" ; // REDUZ POPULACAO
    	String texto ="Aliste a população!!\n"
    				 + "\n-50% de população\n";
    	
		Controller controller = Main.carregarController();
			
		cidade.setPopulacao((int)(cidade.getPopulacao() - (0.5*cidade.getPopulacao())));
			
		controller.alertaEvento(titulo, texto);	
    }
    
    public static void Poluicao (Cidade cidade) throws Exception{ // MULTA POR EMISSAO DE GASES POLUENTES (REDUZ DINHEIRO)
    	String titulo = "Sua cidade está poluindo muito!!";
    	String texto = "Voce recebeu uma multa por desrespeitar o acordo de emissao de gases\n"
    				 + "\n-1000 de dinheiro\n";
    	
		Controller controller = Main.carregarController();
			
		cidade.setDinheiro(cidade.getDinheiro() - 1000);
			
		controller.alertaEvento(titulo, texto);	
    }
    
    public static void Corrupcao (Cidade cidade) throws Exception{ // ESCANDALO DE CORRUPCAO (REDUZ FELICIDADE)
    	String titulo = "Escândalo de Corrupção!!";
    	String texto = "Notícias sobre corrupcão chegaram aos jornais!!\n"
    				 + "\nA populacao está indignada!!"
    				 + "\n-40% de felicidade\n";
    	
		Controller controller = Main.carregarController();
			
		cidade.setFelicidade(cidade.getFelicidade() - 40);
			
		controller.alertaEvento(titulo, texto);	
    }
    
    public static void Emigracao (Cidade cidade) throws Exception{ // CRISES DE EMIGRACAO (REDUZ POPULACAO E DINHEIRO)
    	String titulo = "A populacao esta indo embora!!";
    	String texto = "Um surto de emigracao está ocorrendo!!\n"
    				 + "\nParece que a cidade vizinha se mostrou muito mais próspera"
    				 + "\n-%40 de populacao\n-1000 de dinheiro";
    	
		Controller controller = Main.carregarController();
			
		cidade.setPopulacao((int)(cidade.getPopulacao() - (0.4*cidade.getPopulacao())));
		cidade.setDinheiro(cidade.getDinheiro() - 1000);
		controller.alertaEvento(titulo, texto);	
    }
    

    
    // EVENTOS LEGAIS  
    
    public static void Turismo (Cidade cidade) throws Exception{ // CIDADE SE TORNA PONTO TURISTICO (AUMENTA  DINHEIRO)
    	String titulo = "Sua cidade virou ponto turístico!!";
    	String texto = "\nParece que sua cidade está ficando famosa\n"
    				 + "\nMuitos visitantes estão gastando dinheiro!\n"
    				 + "\n+1200 de dinheiro\n";
    	
		Controller controller = Main.carregarController();
			
		cidade.setDinheiro(cidade.getDinheiro() + 1200);
			
		controller.alertaEvento(titulo, texto);	
    }
    
    public static void Festival (Cidade cidade) throws Exception{ // FESTIVAL DE MUSICA CHEGANDO (AUMENTA FELICIDADE E DINHEIRO)
    	String titulo = "Um grande festival esta para acontecer!!";
    	String texto = "\nO Rock in Happy esta chegando!!\n"
    				 +"\nSua populacao está muito animada!!\n"
    				 + "\n+30% de felicidade\n+700 de dinheiro";
    	
		Controller controller = Main.carregarController();
			
		cidade.setFelicidade(cidade.getFelicidade() + 30);
		cidade.setDinheiro(cidade.getDinheiro() + 700);
			
		controller.alertaEvento(titulo, texto);	
    }
    
    // COMPRAR ESTABELECIMENTO
    
    public static void compraSucesso() throws Exception{ // EVENTO RETORNADO AO COMPRAR O ESTABELCIMENTO
    	String titulo = "Compra realizada com sucesso!";
    	String texto = "O seu estabelecimento foi comprado com sucesso!"    				 
    				 + "\nAproveite";
    	
		Controller controller = Main.carregarController();
	
		controller.alertaEvento(titulo, texto);	
    }
    
    public static void compraFalha() throws Exception{ // EVENTO RETORNADO NA FALHA DA COMPRA (FALTA DE DINHEIRO)
    	String titulo = "Que pena!";
    	String texto = "Infelizmente você não tem dinheiro suficiente!"    				 
    				 + "\nQue tal esperar até a data do pagamento dos impostos?";
    	
		Controller controller = Main.carregarController();
	
		controller.alertaEvento(titulo, texto);	
    }
    
    public static void compraOpcaoVazia() throws Exception{ // NAO SELECAO DE QUAL ESTABELECIMENTO COMPRAR
    	String titulo = "Opsss!";
    	String texto = "É necessário inserir uma opção!"    				 
    				 + "\nEscolha um estabelecimento ou cancele a operação!";
    	
		Controller controller = Main.carregarController();
	
		controller.alertaEvento(titulo, texto);	
    }
        
    // RECEITA
    
    public static void recolherLucros(Cidade cidade) throws Exception{ // RECOLHE O LUCRO DE CADA ESTABELECIMENTO E O IMPOSTO DE CADA CIDADAO
    	double receita = cidade.gerarReceita();
    	
    	String titulo = "Oba! Chegou o melhor dia do mês!";
    	String texto = "Oba! Chegou o melhor dia do mês!\n"
    				 + "\nHora de arrecadar os impostos e a receita de seus estabelecimentos\n"
    				 + "\nImpostos: F$" + cidade.getPopulacao()*cidade.getImposto() +"\n"
    				 + "\nReceita: F$ " + receita ;
    	
    	Controller controller = Main.carregarController();
    	
    	cidade.arrecadarImposto();  
    	
    	controller.alertaEvento(titulo, texto);
    	
    }
    
    // BOAS-VINDAS
    
    public static void boasVindas(Cidade cidade) throws Exception{ // EVENTO DE BOAS VINDAS QUE INCITA UMA ACAO
    	String titulo = "Bem vindo a " + cidade.getNome() + "!!\n";
    	String texto =  "A cidade está ansiosa para o seu governo!!\n"
            + "\nQue tal começar construindo uma casa?\n"
            + "\nClique em qualquer terreno e divirta-se!!! ";
        
        Controller controller = Main.carregarController();
        controller.alertaEvento(titulo, texto);
        
    }
    
    
    // GAME OVER
    
    public static void gameOver() throws Exception{ // SE QUALQUER STATS CHEGAR A 0 = GAME OVER
    	String titulo = "GAME OVER";
    	String texto = "Um de seus status chegou a zero!\n"
    				 + "\nInfelizmente seu governo não deu certo e "
    				 + "o povo clama por outro representante!\n"
    				 + "\nOBRIGADO POR JOGAR!";
    	
		Controller controller = Main.carregarController();
	
		controller.alertaEvento(titulo, texto);	
    }
}
