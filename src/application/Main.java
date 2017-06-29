package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Cidade;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	static Stage stage;	
	
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
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
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
	
	public static void iniciarJogo(Cidade cidade){		
		try{
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("main.fxml"));
			Parent root = loader.load();			
			Controller controller = (Controller)loader.getController();	
			controller.carregarStatus();			
			stage.setScene(new Scene(root, 500, 500));				
			stage.setTitle("HappyTown - Simulador de Cidade");
			stage.setResizable(false);
			stage.show();			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);		
		}
	
	}
