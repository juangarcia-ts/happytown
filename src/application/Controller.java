package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

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
	//Geral
	static Cidade cidade;	// Após a cidade ser criada, ela é acessível em todo o código	
	
	//Menu (Status do jogo ou do jogador presentes em todas as janelas)
	@FXML private Text imposto;
	@FXML private Text nome_cidade;		
	@FXML private Text satisfacao;	
	@FXML private Text populacao;	
	@FXML private Text dinheiro;
	@FXML private Text meses;
	
	//Construção 	
	@FXML private Button btn_confirmacao; //Botão para confirmar compra
	@FXML private Button btn_casa; //Comprar casa
	@FXML private Button btn_hospital; //Comprar hospital
	@FXML private Button btn_banco; //Comprar banco
	@FXML private Button btn_praca; //Comprar praça
	@FXML private Text custo_construcao;
	@FXML private Text receita_construcao;
	@FXML private Text felicidade_construcao;
	@FXML private Text populacao_construcao;
	static private Estabelecimento ultima_opcao; //Última opção marcada na aba de construção
	
	//Terrenos (Cada botão é um terreno)
	@FXML private Button terreno1; 
	@FXML private Button terreno2;
	@FXML private Button terreno3;
	@FXML private Button terreno4;
	@FXML private Button terreno5;
	@FXML private Button terreno6;
	@FXML private Button terreno7;
	@FXML private Button terreno8;
	@FXML private Button terreno9;
	@FXML private Button terreno10;
	@FXML private Button terreno11;
	@FXML private Button terreno12;
	static Button[] terrenos = new Button[12]; // Array contendo todos os terrenos
	
	// Array de String contendo a situação de cada terreno e o que deve aparecer dentro do botão.
	// "Terreno Vazio" permite compra de estabelecimentos;
	// "Desabilitado" só é liberado após o usuário comprar o terreno.
	static String[] estabelecimentos = new String[] {"Terreno Vazio", "Terreno Vazio", "Terreno Vazio", "Terreno Vazio", 
													 "Terreno Vazio", "Terreno Vazio", "Desabilitado", "Desabilitado", 
													 "Desabilitado", "Desabilitado", "Desabilitado", "Desabilitado"};
	
	/*
	 * É necessário implementar a interface Iniatilizable e reescrever o método initialize.
	 * Ele ocorre assim que o programa é executado.
	 * Sua função é a criação de todos os botões para que não sejam nulos e a atribuição de 
	 * cada um deles a seus respectivos terrenos.
	 */
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
    	terrenos[6] = terreno7;
    	terrenos[7] = terreno8;
    	terrenos[8] = terreno9;
    	terrenos[9] = terreno10;
    	terrenos[10] = terreno11;
    	terrenos[11] = terreno12;
		
	}
	
	/*
	 * Primeiro evento que ocorre quando o usuário inicia o jogo. É criado uma caixa de dialógo onde o usuário
	 * insere o nome da cidade. Após isso, é criado um objeto estático do tipo Cidade 
	 */
	public void iniciarJogo() throws Exception{ 		
    	TextInputDialog dialogo = new TextInputDialog();   // Criação da caixa de diálogo 	
    	dialogo.setTitle("Olá!");
        dialogo.setHeaderText("Bem-vindo a HappyTown");
        dialogo.setContentText("Insira o nome de sua cidade e divirta-se!");
        ImageView imagem = new ImageView(new Image(getClass().getResource("../resources/icone_secretario.png").toExternalForm()));
    	dialogo.setGraphic(imagem); // Imagem do Secretário no dialógo
        
        Optional<String> resultado = dialogo.showAndWait(); // Mostrar diálogo e receber o resultado
        
        //Caso o resultado esteja vazio ou tenha mais de 15 caracteres, continuar perguntando
        while (resultado.get().equals("") ) { 
        	dialogo.setContentText("O nome não pode estar vazio");
        	resultado = dialogo.showAndWait();                 
        }
       
        while (resultado.get().length() > 15){
        	dialogo.setContentText("O nome não pode conter mais de 15 caracteres");
        	resultado = dialogo.showAndWait();  
        }
        
        //Transformar a primeira letra em maiúscula
        String nome_cidade = resultado.get().substring(0, 1).toUpperCase()
        					 + (resultado.get().substring(1, resultado.get().length()));
        
       
        cidade = new Cidade( nome_cidade );  //Criação do objeto cidade       
       
        Main.iniciarCidade(cidade);  //Chamada do método Main de iniciarJogo (criação da janela em si)        
        
        Evento.boasVindas(cidade); //Primeiro evento do jogo
    }
	
	/*
	 * Função para realizar duas tarefas durante o decorrer do jogo.
	 * A primeira é a de arrecadar impostos e receitas e acontece a
	 * cada 45 segundos (primeira arrecadação em 30s).
	 * A segunda é a de ocorrer eventos aleatórios a cada 20s
	 * (primeiro evento em 15s).
	 */
	public void contarTempo(){
    	Timer contador = new Timer(); //Contador
    	
        TimerTask arrecadacao = new TimerTask(){ //Tarefa de arrecadação
            @Override
            public void run(){
            	//Platform.runLater é o único modo de manipular tempo no JavaFx
                Platform.runLater(new Runnable() {
                @Override
                public void run() {
                	try { 
                		cidade.addMes(1);  //Adiciona 1 mês ao tempo do jogo
						Evento.recolherLucros(cidade);
					} catch (Exception e) {						
						e.printStackTrace();
					}
                	
                }});
            }
        };
        
        TimerTask eventos = new TimerTask(){ //Tarefa de gerar eventos
            @Override
            public void run(){
            	//Platform.runLater é o único modo de manipular tempo no JavaFx
                Platform.runLater(new Runnable() {
                @Override
                public void run() { 
                	try {
                		 Evento.eventoAleatorio(cidade);					
					} catch (Exception e) {						
						e.printStackTrace();
					}
                }});
            }
        };
        
       contador.scheduleAtFixedRate(arrecadacao, 30000, 45000);
       contador.scheduleAtFixedRate(eventos, 15000, 20000);
    }
    
	/*
	 * Abre os créditos do jogo
	 */
    public void abrirCreditos(){
    	Main.mudarPagina("creditos.fxml");      	
    }
    
    /*
	 * Retorna ao main do jogo
	 */
    public void voltarMain(){
    	Main.mudarPagina("main.fxml");    	    
    }
    
    /*
	 * Retorna ao menu do jogo
	 */
    public void voltarMenu(){
    	Main.mudarPagina("menu.fxml");    		
    }
    
    /*
     * Fechar jogo
     */
    public void fecharJogo(){    	
    	Platform.exit();
    }  
  
    /*
     * A cada mudança de páginas, os status do jogo e a situação dos terrenos são atualizados.
     * Os textos são setados a partir dos dados do objeto cidade.
     * É checado também se ocorrerá Game Over (Dinheiro, Felicidade ou População = 0)
     */
    public void carregarStatus() throws Exception {
    	nome_cidade.setText(cidade.getNome());  
    	dinheiro.setText("F$ " + cidade.getDinheiro());
    	populacao.setText(cidade.getPopulacao().toString());    	
    	satisfacao.setText(cidade.getFelicidade() + "%");
    	imposto.setText("F$ " + cidade.getImposto() + "/mês");
    	meses.setText("Mês " + cidade.getMes());  
    	
    	for (int i = 0; i < terrenos.length; i++){
    	    if (!estabelecimentos[i].equals("Terreno Vazio")){
    	    	if(estabelecimentos[i].equals("Desabilitado")){
        			terrenos[i].setDisable(true);
        		}
    			terrenos[i].setText(estabelecimentos[i]);
    		}
    	}
    	
    	//Se qualquer um desses status chegar a 0 ou menos, chamar método gameOver()
    	if (cidade.getDinheiro() <= 0 || cidade.getFelicidade() <= 0 || cidade.getPopulacao() <= 0){
    		Evento.gameOver();    		
    	}
    	
    }
    
    
    /*
     * Função que recebe duas strings (título do evento e texto do alerta) e retorna um alerta
     * com essas informações 
     */
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
    	
    	Optional<ButtonType> confirmacao = alerta_evento.showAndWait(); //Mostrar alerta e receber confirmação
    	
    	//Após confirmar o alerta, há a checagem de eventos
    	if (confirmacao.get() == ButtonType.OK) {  
    		
    	 	if (titulo_evento.equals("GAME OVER")){    	 		
    	 		fecharJogo();// Se o evento for de Game Over, fechar o jogo
    	 	}else if(titulo_evento.equals("Que pena!") || titulo_evento.equals("Opsss!")){
    	 		// Se o título for igual a "Que pena" ou "Ops", é um evento de erro.
    	 		// Portanto, deve-se manter o jogador na mesma janela.
    	 	}else{      	 		
    	 		voltarMain(); // Em todos os outros casos, retorna para o main do jogo.
    	 	}
    	 	
    	} 	  
    }  
    
    /*
     * Checa a situação do terreno. Caso o botão do terreno esteja com o texto "Terreno Vazio"
     * e não esteja desabilitado é redirecionado o usuário à parte de construção.
     * Caso não esteja vazio, é emitido o alerta perguntando ao usuário se gostaria de demolir 
     * o atual estabelecimento.
     */
    public void checarTerreno(ActionEvent evento){
    	Button botao_apertado = (Button)evento.getSource();
    	
    	if (botao_apertado.getText().equals("Terreno Vazio") && !botao_apertado.isDisabled()){ 
    		Main.mudarPagina("construcao.fxml");     		 		
    	}else{
    		Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
    		ImageView imagem = new ImageView(new Image(getClass().getResource("../resources/icone_secretario.png").toExternalForm()));
    		alerta.getDialogPane().setMaxWidth(400);
    		alerta.getDialogPane().setMinWidth(400);
    	    alerta.setGraphic(imagem); 
    		alerta.setTitle("Demolir?");
    		alerta.setHeaderText("Deseja demolir o estabelecimento?");
    		alerta.setContentText("Você não receberá nada por isso");	    
    		    
    		Optional<ButtonType> resultado = alerta.showAndWait();
    		    	    
    		if (resultado.get() == ButtonType.OK){    	
    			// Caso queira demolir, pegamos o número do terreno e mandamos para o método demolirEstabelecimento().
    			// Ex: Se for o terreno1, pegamos o número 1 e enviamos o parâmetro 0 (Terreno 1 - Posição 0 do array).
    			String numero_terreno = Character.toString(botao_apertado.getId().charAt(botao_apertado.getId().length() - 1));
    			demolirEstabelecimento(Integer.parseInt(numero_terreno) - 1);
    			voltarMain();  	
    		}else{
    			voltarMain(); //Independente da confirmação, retornar à Main
    		}   		
    		
    	}
    }
    
    /*
     * Função que mostra o que cada Estabelecimento proporciona.
     * Por exemplo, caso o usuário clique no btn_casa, é criado um novo
     * objeto da classe Casa e seus staus são mostrados na janela através
     * do método mostrarDadosConstrução que manda esse objeto como param.
     * Além disso, esse objeto torna-se a última opção escolhida pelo
     * usuário caso ele confirme a compra (método checarCompra()).
     */
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
    
    /*
     * Função que recebe um objeto da Classe Estabelecimento através da função 
     * opcoesConstrucao() e mostra os textos na janela do usuário.
     */
    private void mostrarDadosConstrução(Estabelecimento estabelecimento){
    	custo_construcao.setText("- F$ " + estabelecimento.getCusto() );
    	receita_construcao.setText("+ F$ " + estabelecimento.getReceita() +"/mês" );
        felicidade_construcao.setText("+ " + estabelecimento.getFelicidade() + "%");
    	populacao_construcao.setText("+ " + estabelecimento.getNumMoradores());
    }
    
    /*
     * Após apertar o btn_confirmar, a função vê a última opção inserida e
     * chama os métodos de construção do estabelecimento do objeto cidade.
     * Caso esses métodos retornem true, chama o Evento de compra realizada
     * com sucesso e aloca o terreno com o objeto comprado (através do método
     * alocarTerreno())
     */
    public void checarCompra() throws Exception{ 
    	
    	if (ultima_opcao.getNome().equals("Casa")){    		
    			if (cidade.construirCasa() == true){    				
    				Evento.compraSucesso(); 
    				alocarTerreno(ultima_opcao.getNome());
    			}else{
    				Evento.compraFalha();    				
    			}
    	}else if (ultima_opcao.getNome().equals("Hospital")){
    			if (cidade.construirHospital() == true){
    				Evento.compraSucesso(); 
    				alocarTerreno(ultima_opcao.getNome());
    			}else{
    				Evento.compraFalha();    				
    			}
    	}else if (ultima_opcao.getNome().equals("Banco")){
    			if (cidade.construirBanco() == true){
    				Evento.compraSucesso();  
    				alocarTerreno(ultima_opcao.getNome());
    			}else{
    				Evento.compraFalha();    				
    			}
    	}else if (ultima_opcao.getNome().equals("Praça")){
    			if (cidade.construirPraca() == true){
    				Evento.compraSucesso();
    				alocarTerreno(ultima_opcao.getNome());
    			}else{
    				Evento.compraFalha();    				
    			}
    	}else if (ultima_opcao.equals("")){
    			Evento.compraOpcaoVazia();    				
    	}
    	
    	ultima_opcao = null;    	
    }
    
    /*
     * Método que transforma o terreno na opção comprada. Ele procura 
     * o primeiro terreno que estiver vazio e transforma-o. Além disso,
     * adiciona o objeto criado na lista_estabelecimentos da cidade.
     */         
    private void alocarTerreno(String opcao) throws Exception{ 
    	for (int i = 0; i < terrenos.length; i++){     		
    		if (estabelecimentos[i].equals("Terreno Vazio")){
    			estabelecimentos[i] = opcao;
    			cidade.lista_estabelecimento.add(ultima_opcao); //Adiciona objeto ao array
    			carregarStatus(); //Atualizar terrenos
    			break;
    		}
    	}    	
    }
    
    /*
     * Remove o objeto dos dois array ("estabelecimentos" - parte gráfica e
     * "lista_estabelecimento" - objeto cidade) através da posição.
     */
    private void demolirEstabelecimento(int posicao){
    	cidade.lista_estabelecimento.remove(posicao);
    	estabelecimentos[posicao] = "Terreno Vazio";    	
    }
    
    /*
     * Libera mais um terreno ao jogador. Transforma "Desabilitado" em
     * "Terreno Vazio" caso o usuário tenha mais de 1000 moedas. 
     */
    
    public void comprarTerreno() throws Exception{
	    Alert dialogoCompra = new Alert(Alert.AlertType.CONFIRMATION);
	    ImageView imagem = new ImageView(new Image(getClass().getResource("../resources/icone_secretario.png").toExternalForm()));
	    dialogoCompra.getDialogPane().setMaxWidth(400);
	    dialogoCompra.getDialogPane().setMinWidth(400);
    	dialogoCompra.setGraphic(imagem); 
	    dialogoCompra.setTitle("Comprar terreno");
	    dialogoCompra.setHeaderText("Gostaria de comprar um terreno?");
	    dialogoCompra.setContentText("É necessário F$ 1000.00");	    
	    
	    Optional<ButtonType> resultado = dialogoCompra.showAndWait();
	    	    
	    if (resultado.get() == ButtonType.OK){
	    	// Checagem do dinheiro do usuário
	    	if (cidade.getDinheiro() - 1000 > 0){
	    		cidade.setDinheiro(cidade.getDinheiro() - 1000);
		    	for (int i = 5; i < terrenos.length; i++){
		    		if (estabelecimentos[i].equals("Desabilitado")){		    			
		    			estabelecimentos[i] = "Terreno Vazio";
		    			break;
		    		}
		    	}
		    	voltarMain();	    	
	    	}else{
	    		//Alerta caso o usuário não tenha dinheiro suficiente
	    		Alert error = new Alert(Alert.AlertType.ERROR);
	    		error.getDialogPane().setMaxWidth(400);
	    		error.getDialogPane().setMinWidth(400);
	    	    error.setGraphic(imagem); 
	    		error.setTitle("Comprar terreno");
	    		error.setHeaderText("Você não tem dinheiro suficiente!");
	    		error.showAndWait();
	    		
	    	}	    	
	    }
	    	    
    }
    
    /*
     * Permite ao usuário alterar o imposto do jogo para o valor desejado
     */
    public void alterarImposto(){
    	TextInputDialog dialogo = new TextInputDialog();    	
    	dialogo.setTitle("Imposto");
        dialogo.setHeaderText("Altere o imposto para o valor desejado");
        dialogo.setContentText("Lembre-se:\nQuanto maior o imposto, menor a felicidade!");
        ImageView imagem = new ImageView(new Image(getClass().getResource("../resources/icone_secretario.png").toExternalForm()));
	    dialogo.getDialogPane().setMaxWidth(400);
	    dialogo.getDialogPane().setMinWidth(400);
    	dialogo.setGraphic(imagem); 
        
        Optional<String> resultado = dialogo.showAndWait();
        
        double novo_imposto = Double.parseDouble(resultado.get());
        
        if (resultado.isPresent()){
	        cidade.alterarImposto(novo_imposto);
	        voltarMain(); 
        }
        
    }
            
}	
    