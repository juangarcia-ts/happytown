package application;

import java.util.Optional;

import application.Main;
import javafx.application.Platform;
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

public class Controller{
	
	static Cidade cidade;
	
	@FXML private Text nome_cidade;	
	
	@FXML private Text satisfacao;	
	
	@FXML private Text populacao;	
	
	@FXML private Text dinheiro;
	
	public void iniciarJogo(ActionEvent evento){      	
    	TextInputDialog dialogo = new TextInputDialog();    	
    	dialogo.setTitle("");
        dialogo.setHeaderText("Bem-vindo a HappyTown");
        dialogo.setContentText("Insira o seu nome e divirta-se!");
        
        Optional<String> resultado = dialogo.showAndWait();
        
        while (resultado.get().equals("") ) { 
        	dialogo.setContentText("O nome não pode estar vazio");
        	resultado = dialogo.showAndWait();                 
        }
        
        while (resultado.get().length() > 10){
        	dialogo.setContentText("O nome não pode conter mais de 10 caracteres");
        	resultado = dialogo.showAndWait();  
        }
        
        cidade = new Cidade("Cidade de " + resultado.get());
               
        Main.iniciarJogo(cidade);
    }
    
    public void abrirCreditos(ActionEvent evento){
    	Main.mudarPagina("creditos.fxml");      	
    }
    
    public void voltarMain(ActionEvent evento) throws Exception{
    	Main.mudarPagina("main.fxml");
    	Evento.Praga(cidade);
    }
    
    public void voltarMenu(ActionEvent evento){
    	Main.mudarPagina("menu.fxml");    		
    }
  
    public void carregarStatus() { 
    	Double satisfacao_cidade = cidade.getFelicidade()*100;
    	
    	nome_cidade.setText(cidade.getNome());  
    	dinheiro.setText("F$ " + cidade.getDinheiro());
    	populacao.setText(cidade.getPopulacao().toString());    	
    	satisfacao.setText(satisfacao_cidade.toString() + "%");
    	
    }
    
    public void alertaEvento(String titulo, String texto) throws Exception{
    	
    	
    	String titulo_evento = titulo;
    	String texto_evento = texto;
    	
    	Alert alert = new Alert(Alert.AlertType.INFORMATION, "INFO", ButtonType.OK);      	
    	Image image = new Image(getClass().getResource("../resources/icone_secretario.png").toExternalForm());
    	ImageView imageView = new ImageView(image);
    	alert.getDialogPane().setMaxWidth(350);
    	alert.getDialogPane().setMinWidth(350);
    	alert.setGraphic(imageView);
    	alert.setHeaderText(null);
    	alert.setTitle(titulo_evento);    	
    	alert.setContentText(texto_evento);	
    	
    	 Optional<ButtonType> result = alert.showAndWait();
    	 	if (result.get() == ButtonType.OK) {
    	 		carregarStatus();
    	 		Main.mudarPagina("main.fxml");
    	 	}
    	
    }
    
    public void fecharJogo(){    	
    	Platform.exit();
    }    
    
    public void checarTerreno(ActionEvent evento){
    	Button botao_apertado = ((Button)evento.getSource());
    	
    	if (botao_apertado.getText().equals("Terreno Vazio") && !botao_apertado.isDisabled()){
    		Main.mudarPagina("construcao.fxml"); 
    	}
    }
     
}	
    