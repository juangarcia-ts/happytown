package application;

import java.util.Optional;
import application.Main;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.Cidade;
import model.Evento;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

public class Controller{
	
	static Cidade cidade;
	
	@FXML private Text nome_cidade;	
	
	@FXML private Text satisfacao;	
	
	@FXML private Text populacao;	
	
	@FXML private Text dinheiro;	

	@FXML private ComboBox<String> opcoes_construcao;
	
	@FXML private Text custo_construcao;
	@FXML private Text receita_construcao;
	@FXML private Text felicidade_construcao;
	@FXML private Text populacao_construcao;
	
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
    	
    	if (cidade.getDinheiro() <= 0 || cidade.getFelicidade() <= 0 || cidade.getPopulacao() <= 0){
    		Evento.GameOver();    		
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
    	 	if (!titulo_evento.equals("GAME OVER")){
    	 		Main.mudarPagina("main.fxml");
    	 	}else{    	 		
    	 		fecharJogo();  	 		
    	 	}
    	}    	  
    	
    }

    public void fecharJogo(){    	
    	Platform.exit();
    }    
    
    public void checarTerreno(ActionEvent evento){
    	Button botao_apertado = ((Button)evento.getSource());
    	
    	if (botao_apertado.getText().equals("Terreno Vazio") && !botao_apertado.isDisabled()){ 
    		Main.mudarPagina("construcao.fxml"); 
    		construcaoTerreno();    		
    	}
    }
    
    public void construcaoTerreno(){
    	opcoes_construcao = new ComboBox<>();
    	
    	ObservableList<String> estabelecimentos = FXCollections.observableArrayList();
    	
    	estabelecimentos.addAll("Casa",
				    			"Banco",
				    			"Praça",
				    			"Hospital");
    	
    	opcoes_construcao.setItems(estabelecimentos);
        	
    }
     
}	
    