package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.Banco;
import model.Casa;
import model.Cidade;
import model.Estabelecimento;
import model.Evento;
import model.Hospital;
import model.Praca;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class Controller implements Initializable{
	//Menu
	@FXML private Text nome_cidade;		
	@FXML private Text satisfacao;	
	@FXML private Text populacao;	
	@FXML private Text dinheiro;
	
	//Construção
	static private Estabelecimento ultima_opcao;
	@FXML private Button btn_confirmacao;
	@FXML private Button btn_casa;
	@FXML private Button btn_hospital;
	@FXML private Button btn_banco;
	@FXML private Button btn_praca;
	@FXML private Text custo_construcao;
	@FXML private Text receita_construcao;
	@FXML private Text felicidade_construcao;
	@FXML private Text populacao_construcao;
	
	//Terrenos		
	@FXML private Button terreno1;
	@FXML private Button terreno2;
	@FXML private Button terreno3;
	@FXML private Button terreno4;
	@FXML private Button terreno5;
	@FXML private Button terreno6;
	static Button[] terrenos = new Button[6];
	static String[] estabelecimentos = new String[] {"Terreno Vazio", "Terreno Vazio", "Terreno Vazio",
													 "Terreno Vazio", "Terreno Vazio", "Terreno Vazio"	};
	
	//Geral
	static Cidade cidade;		
	
	public void iniciarJogo(ActionEvent evento){ 		
    	TextInputDialog dialogo = new TextInputDialog();    	
    	dialogo.setTitle("");
        dialogo.setHeaderText("Bem-vindo a HappyTown");
        dialogo.setContentText("Insira o nome de sua cidade e divirta-se!");
        
        Optional<String> resultado = dialogo.showAndWait();
        
        while (resultado.get().equals("") ) { 
        	dialogo.setContentText("O nome não pode estar vazio");
        	resultado = dialogo.showAndWait();                 
        }
        
        while (resultado.get().length() > 10){
        	dialogo.setContentText("O nome não pode conter mais de 10 caracteres");
        	resultado = dialogo.showAndWait();  
        }
        
        String nome_cidade = resultado.get().substring(0, 1).toUpperCase()
        					 + (resultado.get().substring(1, resultado.get().length()));
        
        cidade = new Cidade( nome_cidade );
       
        Main.iniciarJogo(cidade);
    }
    
    public void abrirCreditos(){
    	Main.mudarPagina("creditos.fxml");      	
    }
    
    public void voltarMain(ActionEvent evento) throws Exception{
    	Main.mudarPagina("main.fxml");
    	Evento.eventoAleatorio(cidade);    
    }
    
    public void voltarMenu(ActionEvent evento){
    	Main.mudarPagina("menu.fxml");    		
    }
  
    public void carregarStatus() throws Exception {
    	nome_cidade.setText(cidade.getNome());  
    	dinheiro.setText("F$ " + cidade.getDinheiro());
    	populacao.setText(cidade.getPopulacao().toString());    	
    	satisfacao.setText(cidade.getFelicidade() + "%");
    	//mes.setText("Mês " + cidade.getMes());
    	
    	for (int i = 0; i < terrenos.length; i++){
    		if (!estabelecimentos[i].equals("Terreno Vazio")){
    			terrenos[i].setText(estabelecimentos[i]);
    		}
    	}
    	
    	if (cidade.getDinheiro() <= 0 || cidade.getFelicidade() <= 0 || cidade.getPopulacao() <= 0){
    		Evento.gameOver();    		
    	}
    	
    }
    
    public void alertaEvento(String titulo, String texto) throws Exception{
    	String titulo_evento = titulo;
    	String texto_evento = texto;
    	
    	Alert alerta_evento = new Alert(Alert.AlertType.INFORMATION, "INFO", ButtonType.OK); 
    	
    	ImageView imagem = new ImageView(new Image(getClass().getResource("../resources/icone_secretario.png").toExternalForm()));
    	
    	alerta_evento.getDialogPane().setMaxWidth(400);
    	alerta_evento.getDialogPane().setMinWidth(400);
    	alerta_evento.setGraphic(imagem);    
    	alerta_evento.setHeaderText(titulo_evento);
    	alerta_evento.setTitle(titulo_evento);    	
    	alerta_evento.setContentText(texto_evento);	
    	
    	Optional<ButtonType> confirmacao = alerta_evento.showAndWait();
    	if (confirmacao.get() == ButtonType.OK) {    	 		
    	 	if (titulo_evento.equals("GAME OVER")){
    	 		fecharJogo();  	 		
    	 	}else if(titulo_evento.equals("Que pena!") || 
    	 			 titulo_evento.equals("Opsss!")){
    	 		// Continua na página
    	 	}else{  
    	 		Main.mudarPagina("main.fxml");
    	 	}
    	} 	  
    }

    public void fecharJogo(){    	
    	Platform.exit();
    }    
    
    public void checarTerreno(ActionEvent evento){
    	Button botao_apertado = (Button)evento.getSource();
    	
    	if (botao_apertado.getText().equals("Terreno Vazio") && !botao_apertado.isDisabled()){ 
    		Main.mudarPagina("construcao.fxml");     		 		
    	}else{
    		
    	}
    }
          
    public void opcoesConstrucao(ActionEvent evento) throws Exception{
    	ultima_opcao = null;
    	Button botao_apertado = ((Button)evento.getSource());
    	    	
    	if (botao_apertado == btn_casa){
    		Estabelecimento nova_casa = new Casa();
    		mostrarDadosConstrução(nova_casa);   
    		ultima_opcao = nova_casa;
    	}else if(botao_apertado == btn_hospital){
    		Estabelecimento novo_hospital = new Hospital();
    		mostrarDadosConstrução(novo_hospital); 
    		ultima_opcao = novo_hospital;
    	}else if(botao_apertado == btn_banco){
    		Estabelecimento novo_banco = new Banco();
    		mostrarDadosConstrução(novo_banco); 
    		ultima_opcao = novo_banco;
    	}else if(botao_apertado == btn_praca){
    		Estabelecimento nova_praca = new Praca();
    		mostrarDadosConstrução(nova_praca); 
    		ultima_opcao = nova_praca;
    	}    	
    }
    
    private void mostrarDadosConstrução(Estabelecimento estabelecimento){
    	custo_construcao.setText("- F$ " + estabelecimento.getCusto() );
    	receita_construcao.setText("+ F$ " + estabelecimento.getReceita() +"/mês" );
        felicidade_construcao.setText("+ " + estabelecimento.getFelicidade() + "%");
    	populacao_construcao.setText("+ " + estabelecimento.getNumMoradores());
    }
    
    public void checarCompra() throws Exception{ 
    	
    	if (ultima_opcao.getNome().equals("Casa")){    		
    			if (cidade.construirCasa() == true){    				
    				Evento.compraSucesso(); 
    				venderTerreno(ultima_opcao.getNome());
    			}else{
    				Evento.compraFalha();    				
    			}
    	}else if (ultima_opcao.getNome().equals("Hospital")){
    			if (cidade.construirHospital() == true){
    				Evento.compraSucesso(); 
    				venderTerreno(ultima_opcao.getNome());
    			}else{
    				Evento.compraFalha();    				
    			}
    	}else if (ultima_opcao.getNome().equals("Banco")){
    			if (cidade.construirBanco() == true){
    				Evento.compraSucesso();  
    				venderTerreno(ultima_opcao.getNome());
    			}else{
    				Evento.compraFalha();    				
    			}
    	}else if (ultima_opcao.getNome().equals("Praça")){
    			if (cidade.construirPraca() == true){
    				Evento.compraSucesso();
    				venderTerreno(ultima_opcao.getNome());
    			}else{
    				Evento.compraFalha();    				
    			}
    	}else if (ultima_opcao.equals("")){
    			Evento.compraOpcaoVazia();    				
    	}
    	
    	ultima_opcao = null;    	
    }
    
    private void venderTerreno(String opcao) throws Exception{ 
    	for (int i = 0; i < terrenos.length; i++){     		
    		if (estabelecimentos[i].equals("Terreno Vazio")){
    			estabelecimentos[i] = opcao;
    			cidade.lista_estabelecimento.add(ultima_opcao);
    			carregarStatus();
    			break;
    		}
    	}
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 
        for (int i = 0; i < terrenos.length; i++){    		
    		terrenos[i] = new Button();
    	}    
    	    	
    	terrenos[0] = terreno1;
    	terrenos[1] = terreno2;
    	terrenos[2] = terreno3;
    	terrenos[3] = terreno4;
    	terrenos[4] = terreno5;
    	terrenos[5] = terreno6;
      
		
	}
        
}	
    