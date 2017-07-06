package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Cidade;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	static Stage stage;	// Janela principal da aplicação
	
	/*
	 * Método que inicia as janelas do jogo com determinadas 
	 * propriedades, além de carregar o CSS e o ícone do jogo.
	 * A primeira página é o menu.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			AnchorPane root = FXMLLoader.load(Main.class.getResource("menu.fxml"));				
			Scene scene = new Scene(root, 500, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setTitle("HappyTown - Simulador de Cidade");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.getIcons().add(new Image(Main.class.getResource("/resources/icone_happytown.png").toString()));	
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Recebe uma string com o nome da view e redirecionada o usuário a página
	 * escolhida. Caso não seja o menu nem os créditos, atualiza o status do jogo.
	 */
	public static void mudarPagina(String pagina){	
				
		try{
			FXMLLoader loader = new FXMLLoader(Main.class.getResource(pagina));
			Parent root = loader.load();
			
			if (!pagina.equals("creditos.fxml") && !pagina.equals("menu.fxml")){
				Controller controller = (Controller)loader.getController();	
				controller.carregarStatus();					
			}
			
			stage.setScene(new Scene(root, 500, 500));				
			stage.setTitle("HappyTown - Simulador de Cidade");
			stage.setResizable(false);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Método que inicia o jogo caso o usuário clique em "Jogar" no menu.
	 * Bem parecida com o método start, porém além de carregar outra página
	 * atualiza os dados com o nome da cidade inserida anteriormente e
	 * começa a contar o tempo do jogo.
	 */
	public static void iniciarCidade(Cidade cidade){		
		try{
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("main.fxml"));
			Parent root = loader.load();			
			Controller controller = (Controller)loader.getController();
			controller.carregarStatus();				
			stage.setScene(new Scene(root, 500, 500));				
			stage.setTitle("HappyTown - Simulador de Cidade");
			stage.setResizable(false);
			stage.show();	
			controller.contarTempo();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Carrega o controller da aplicação para que a classe Evento possa chamar
	 * a função alertaEventos()
	 */
	public static Controller carregarController() throws IOException{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("main.fxml"));
		@SuppressWarnings("unused") // É utilizada no método que a chamou
		Parent root = loader.load();
		Controller controller = (Controller)loader.getController();	
			
		return controller;
	}
	
	public static void main(String[] args) {
		launch(args);		
		}
	
	}
