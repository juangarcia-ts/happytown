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
import model.FabricaEstabelecimento;
import model.Cidade;
import model.Estabelecimento;
import model.Evento;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class Controller implements Initializable {
	// Geral
	static Cidade cidade; // Ap�s a cidade ser criada, ela � acess�vel em todo o c�digo
	private FabricaEstabelecimento fabrica = FabricaEstabelecimento.pegaInstancia();

	// Menu (Status do jogo ou do jogador presentes em todas as janelas)
	@FXML
	private Text imposto;
	@FXML
	private Text nome_cidade;
	@FXML
	private Text satisfacao;
	@FXML
	private Text populacao;
	@FXML
	private Text dinheiro;
	@FXML
	private Text meses;

	// Constru��o
	@FXML
	private Button btn_confirmacao; // Bot�o para confirmar compra
	@FXML
	private Button btn_casa; // Comprar casa
	@FXML
	private Button btn_hospital; // Comprar hospital
	@FXML
	private Button btn_banco; // Comprar banco
	@FXML
	private Button btn_praca; // Comprar pra�a
	@FXML
	private Text custo_construcao;
	@FXML
	private Text receita_construcao;
	@FXML
	private Text felicidade_construcao;
	@FXML
	private Text populacao_construcao;
	static private Estabelecimento ultima_opcao; // �ltima op��o marcada na aba de constru��o

	// Terrenos (Cada bot�o � um terreno)
	@FXML
	private Button terreno1;
	@FXML
	private Button terreno2;
	@FXML
	private Button terreno3;
	@FXML
	private Button terreno4;
	@FXML
	private Button terreno5;
	@FXML
	private Button terreno6;
	@FXML
	private Button terreno7;
	@FXML
	private Button terreno8;
	@FXML
	private Button terreno9;
	@FXML
	private Button terreno10;
	@FXML
	private Button terreno11;
	@FXML
	private Button terreno12;
	static Button[] terrenos = new Button[12]; // Array contendo todos os terrenos

	// Array de String contendo a situa��o de cada terreno e o que deve aparecer
	// dentro do bot�o.
	// "Terreno Vazio" permite compra de estabelecimentos;
	// "Desabilitado" s� � liberado ap�s o usu�rio comprar o terreno.
	static String[] estabelecimentos = new String[] { "Terreno Vazio", "Terreno Vazio", "Terreno Vazio",
			"Terreno Vazio", "Terreno Vazio", "Terreno Vazio", "Desabilitado", "Desabilitado", "Desabilitado",
			"Desabilitado", "Desabilitado", "Desabilitado" };

	/*
	 * � necess�rio implementar a interface Iniatilizable e reescrever o m�todo
	 * initialize. Ele ocorre assim que o programa � executado. Sua fun��o � a
	 * cria��o de todos os bot�es para que n�o sejam nulos e a atribui��o de cada um
	 * deles a seus respectivos terrenos.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		for (int i = 0; i < terrenos.length; i++) {
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
	 * Primeiro evento que ocorre quando o usu�rio inicia o jogo. � criado uma caixa
	 * de dial�go onde o usu�rio insere o nome da c idade. Ap�s isso, � criado um
	 * objeto est�tico do tipo Cidade
	 */
	public void iniciarJogo() throws Exception {
		TextInputDialog dialogo = new TextInputDialog(); // Cria��o da caixa de di�logo
		dialogo.setTitle("Ol�!");
		dialogo.setHeaderText("Bem-vindo a HappyTown");
		dialogo.setContentText("Insira o nome de sua cidade e divirta-se!");
		ImageView imagem = new ImageView(
				new Image(Controller.class.getResource("/resources/icone_secretario.png").toExternalForm()));
		dialogo.setGraphic(imagem); // Imagem do Secret�rio no dial�go

		Optional<String> resultado = dialogo.showAndWait(); // Mostrar di�logo e receber o resultado

		// Caso o resultado esteja vazio ou tenha mais de 15 caracteres, continuar perguntando
		while (resultado.get().equals("")) {
			dialogo.setContentText("O nome n�o pode estar vazio");
			resultado = dialogo.showAndWait();
		}

		while (resultado.get().length() > 15) {
			dialogo.setContentText("O nome n�o pode conter mais de 15 caracteres");
			resultado = dialogo.showAndWait();
		}

		// Transformar a primeira letra em mai�scula
		String nome_cidade = resultado.get().substring(0, 1).toUpperCase()
				+ (resultado.get().substring(1, resultado.get().length()));

		cidade = new Cidade(nome_cidade); // Cria��o do objeto cidade

		Main.iniciarCidade(cidade); // Chamada do m�todo Main de iniciarJogo (cria��o da janela em si)

		Evento.boasVindas(cidade); // Primeiro evento do jogo
	}

	/*
	 * Fun��o para realizar duas tarefas durante o decorrer do jogo. A primeira � a
	 * de arrecadar impostos e receitas e acontece a cada 45 segundos (primeira
	 * arrecada��o em 30s). A segunda � a de ocorrer eventos aleat�rios a cada 20s
	 * (primeiro evento em 15s).
	 */
	public void contarTempo() {
		Timer contador = new Timer(); // Contador

		TimerTask arrecadacao = new TimerTask() { // Tarefa de arrecada��o
			@Override
			public void run() {
				// Platform.runLater � o �nico modo de manipular tempo no JavaFx
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							cidade.addMes(1); // Adiciona 1 m�s ao tempo do jogo
							Evento.recolherLucros(cidade);
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				});
			}
		};

		TimerTask eventos = new TimerTask() { // Tarefa de gerar eventos
			@Override
			public void run() {
				// Platform.runLater � o �nico modo de manipular tempo no JavaFx
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							Evento.eventoAleatorio(cidade);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		};

		contador.scheduleAtFixedRate(arrecadacao, 30000, 45000);
		contador.scheduleAtFixedRate(eventos, 15000, 20000);
	}

	/*
	 * Abre os cr�ditos do jogo
	 */
	public void abrirCreditos() {
		Main.mudarPagina("creditos.fxml");
	}

	/*
	 * Retorna ao main do jogo
	 */
	public void voltarMain() {
		Main.mudarPagina("main.fxml");
	}

	/*
	 * Retorna ao menu do jogo
	 */
	public void voltarMenu() {
		Main.mudarPagina("menu.fxml");
	}

	/*
	 * Fechar jogo
	 */
	public void fecharJogo() {
		Platform.exit();
	}

	/*
	 * A cada mudan�a de p�ginas, os status do jogo e a situa��o dos terrenos s�o
	 * atualizados. Os textos s�o setados a partir dos dados do objeto cidade. �
	 * checado tamb�m se ocorrer� Game Over (Dinheiro, Felicidade ou Popula��o = 0)
	 */
	public void carregarStatus() throws Exception {
		nome_cidade.setText(cidade.getNome());
		dinheiro.setText("F$ " + cidade.getDinheiro());
		populacao.setText(cidade.getPopulacao().toString());
		satisfacao.setText(cidade.getFelicidade() + "%");
		imposto.setText("F$ " + cidade.getImposto() + "/m�s");
		meses.setText("M�s " + cidade.getMes());

		for (int i = 0; i < terrenos.length; i++) {
			if (!estabelecimentos[i].equals("Terreno Vazio")) {
				if (estabelecimentos[i].equals("Desabilitado")) {
					terrenos[i].setDisable(true);
				}
				terrenos[i].setText(estabelecimentos[i]);
			}
		}

		// Se qualquer um desses status chegar a 0 ou menos, chamar m�todo gameOver()
		if (cidade.getDinheiro() <= 0 || cidade.getFelicidade() <= 0 || cidade.getPopulacao() <= 0) {
			Evento.gameOver();
		}

	}

	/*
	 * Fun��o que recebe duas strings (t�tulo do evento e texto do alerta) e retorna
	 * um alerta com essas informa��es
	 */
	public void alertaEvento(String titulo, String texto) throws Exception {
		String titulo_evento = titulo;
		String texto_evento = texto;

		Alert alerta_evento = new Alert(Alert.AlertType.INFORMATION, "INFO", ButtonType.OK);

		ImageView imagem = new ImageView(
				new Image(Controller.class.getResource("/resources/icone_secretario.png").toString()));

		alerta_evento.getDialogPane().setMaxWidth(400);
		alerta_evento.getDialogPane().setMinWidth(400);
		alerta_evento.setGraphic(imagem);
		alerta_evento.setHeaderText(titulo_evento);
		alerta_evento.setTitle(titulo_evento);
		alerta_evento.setContentText(texto_evento);

		Optional<ButtonType> confirmacao = alerta_evento.showAndWait(); // Mostrar alerta e receber confirma��o

		// Ap�s confirmar o alerta, h� a checagem de eventos
		if (confirmacao.get() == ButtonType.OK) {

			if (titulo_evento.equals("GAME OVER")) {
				fecharJogo();// Se o evento for de Game Over, fechar o jogo
			} else if (titulo_evento.equals("Que pena!") || titulo_evento.equals("Opsss!")) {
				// Se o t�tulo for igual a "Que pena" ou "Ops", � um evento de erro.
				// Portanto, deve-se manter o jogador na mesma janela.
			} else {
				voltarMain(); // Em todos os outros casos, retorna para o main do jogo.
			}

		}
	}

	/*
	 * Checa a situa��o do terreno. Caso o bot�o do terreno esteja com o texto
	 * "Terreno Vazio" e n�o esteja desabilitado � redirecionado o usu�rio � parte
	 * de constru��o. Caso n�o esteja vazio, � emitido o alerta perguntando ao
	 * usu�rio se gostaria de demolir o atual estabelecimento.
	 */
	public void checarTerreno(ActionEvent evento) {
		Button botao_apertado = (Button) evento.getSource();

		if (botao_apertado.getText().equals("Terreno Vazio") && !botao_apertado.isDisabled()) {
			Main.mudarPagina("construcao.fxml");
		} else {
			Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
			ImageView imagem = new ImageView(
					new Image(Controller.class.getResource("/resources/icone_secretario.png").toString()));
			alerta.getDialogPane().setMaxWidth(400);
			alerta.getDialogPane().setMinWidth(400);
			alerta.setGraphic(imagem);
			alerta.setTitle("Demolir?");
			alerta.setHeaderText("Deseja demolir o estabelecimento?");
			alerta.setContentText("Voc� n�o receber� nada por isso");

			Optional<ButtonType> resultado = alerta.showAndWait();

			if (resultado.get() == ButtonType.OK) {
				// Caso queira demolir, pegamos o n�mero do terreno e mandamos para o m�todo
				// demolirEstabelecimento().
				// Ex: Se for o terreno1, pegamos o n�mero 1 e enviamos o par�metro 0 (Terreno 1
				// - Posi��o 0 do array).
				String numero_terreno = Character
						.toString(botao_apertado.getId().charAt(botao_apertado.getId().length() - 1));
				demolirEstabelecimento(Integer.parseInt(numero_terreno) - 1);
				voltarMain();
			} else {
				voltarMain(); // Independente da confirma��o, retornar � Main
			}

		}
	}

	/*
	 * Fun��o que mostra o que cada Estabelecimento proporciona. Por exemplo, caso o
	 * usu�rio clique no btn_casa, � criado um novo objeto da classe Casa e seus
	 * staus s�o mostrados na janela atrav�s do m�todo mostrarDadosConstru��o que
	 * manda esse objeto como param. Al�m disso, esse objeto torna-se a �ltima op��o
	 * escolhida pelo usu�rio caso ele confirme a compra (m�todo checarCompra()).
	 */
	public void opcoesConstrucao(ActionEvent evento) throws Exception {
		ultima_opcao = null;
		Button botao_apertado = ((Button) evento.getSource());
		if (botao_apertado == btn_casa) {
			Estabelecimento nova_casa = fabrica.gerarCasa();
			mostrarDadosConstru��o(nova_casa);
			ultima_opcao = nova_casa;
		} else if (botao_apertado == btn_hospital) {
			Estabelecimento novo_hospital = fabrica.gerarHospital();
			mostrarDadosConstru��o(novo_hospital);
			ultima_opcao = novo_hospital;
		} else if (botao_apertado == btn_banco) {
			Estabelecimento novo_banco = fabrica.gerarBanco();
			mostrarDadosConstru��o(novo_banco);
			ultima_opcao = novo_banco;
		} else if (botao_apertado == btn_praca) {
			Estabelecimento nova_praca = fabrica.gerarPraca();
			mostrarDadosConstru��o(nova_praca);
			ultima_opcao = nova_praca;
		}
	}

	/*
	 * Fun��o que recebe um objeto da Classe Estabelecimento atrav�s da fun��o
	 * opcoesConstrucao() e mostra os textos na janela do usu�rio.
	 */
	private void mostrarDadosConstru��o(Estabelecimento estabelecimento) {
		custo_construcao.setText("- F$ " + estabelecimento.getCusto());
		receita_construcao.setText("+ F$ " + estabelecimento.getReceita() + "/m�s");
		felicidade_construcao.setText("+ " + estabelecimento.getFelicidade() + "%");
		populacao_construcao.setText("+ " + estabelecimento.getNumMoradores());
	}

	/*
	 * Ap�s apertar o btn_confirmar, a fun��o v� a �ltima op��o inserida e chama os
	 * m�todos de constru��o do estabelecimento do objeto cidade. Caso esses m�todos
	 * retornem true, chama o Evento de compra realizada com sucesso e aloca o
	 * terreno com o objeto comprado (atrav�s do m�todo alocarTerreno())
	 */
	public void checarCompra() throws Exception {

		try {
			if (ultima_opcao.getNome().equals("Casa")) {
				cidade.construirCasa();
			} else if (ultima_opcao.getNome().equals("Hospital")) {
				cidade.construirHospital();
			} else if (ultima_opcao.getNome().equals("Banco")) {
				cidade.construirBanco();
			} else if (ultima_opcao.getNome().equals("Pra�a")) {
				cidade.construirPraca();
			} else if (ultima_opcao.equals("")) {
				Evento.compraOpcaoVazia();
			}
			
			Evento.compraSucesso();
			alocarTerreno(ultima_opcao.getNome());
		} catch (Exception e) {
			Evento.compraFalha();
		}		

		ultima_opcao = null;
	}

	/*
	 * M�todo que transforma o terreno na op��o comprada. Ele procura o primeiro
	 * terreno que estiver vazio e transforma-o. Al�m disso, adiciona o objeto
	 * criado na lista_estabelecimentos da cidade.
	 */
	private void alocarTerreno(String opcao) throws Exception {
		for (int i = 0; i < terrenos.length; i++) {
			if (estabelecimentos[i].equals("Terreno Vazio")) {
				estabelecimentos[i] = opcao;
				cidade.getEstabelecimentos().add(ultima_opcao); // Adiciona objeto ao array
				carregarStatus(); // Atualizar terrenos
				break;
			}
		}
	}

	/*
	 * Remove o objeto dos dois array ("estabelecimentos" - parte gr�fica e
	 * "lista_estabelecimento" - objeto cidade) atrav�s da posi��o.
	 */
	private void demolirEstabelecimento(int posicao) {
		cidade.removerEstabelecimento(posicao);
		estabelecimentos[posicao] = "Terreno Vazio";
	}

	/*
	 * Libera mais um terreno ao jogador. Transforma "Desabilitado" em
	 * "Terreno Vazio" caso o usu�rio tenha mais de 1000 moedas.
	 */

	public void comprarTerreno() throws Exception {
		Alert dialogoCompra = new Alert(Alert.AlertType.CONFIRMATION);
		ImageView imagem = new ImageView(
				new Image(Controller.class.getResource("/resources/icone_secretario.png").toString()));
		dialogoCompra.getDialogPane().setMaxWidth(400);
		dialogoCompra.getDialogPane().setMinWidth(400);
		dialogoCompra.setGraphic(imagem);
		dialogoCompra.setTitle("Comprar terreno");
		dialogoCompra.setHeaderText("Gostaria de comprar um terreno?");
		dialogoCompra.setContentText("� necess�rio F$ 1000.00");

		Optional<ButtonType> resultado = dialogoCompra.showAndWait();

		if (resultado.get() == ButtonType.OK) {
			// Checagem do dinheiro do usu�rio
			if (cidade.getDinheiro() - 1000 > 0) {
				cidade.setDinheiro(cidade.getDinheiro() - 1000);
				for (int i = 5; i < terrenos.length; i++) {
					if (estabelecimentos[i].equals("Desabilitado")) {
						estabelecimentos[i] = "Terreno Vazio";
						break;
					}
				}
				voltarMain();
			} else {
				// Alerta caso o usu�rio n�o tenha dinheiro suficiente
				Alert error = new Alert(Alert.AlertType.ERROR);
				error.getDialogPane().setMaxWidth(400);
				error.getDialogPane().setMinWidth(400);
				error.setGraphic(imagem);
				error.setTitle("Comprar terreno");
				error.setHeaderText("Voc� n�o tem dinheiro suficiente!");
				error.showAndWait();
			}
		}
	}

	/*
	 * Permite ao usu�rio alterar o imposto do jogo para o valor desejado
	 */
	public void alterarImposto() {
		TextInputDialog dialogo = new TextInputDialog();
		dialogo.setTitle("Imposto");
		dialogo.setHeaderText("Altere o imposto para o valor desejado");
		dialogo.setContentText("Lembre-se:\nQuanto maior o imposto, menor a felicidade!");
		ImageView imagem = new ImageView(
				new Image(Controller.class.getResource("/resources/icone_secretario.png").toString()));
		dialogo.getDialogPane().setMaxWidth(400);
		dialogo.getDialogPane().setMinWidth(400);
		dialogo.setGraphic(imagem);

		Optional<String> resultado = dialogo.showAndWait();

		double novo_imposto = Double.parseDouble(resultado.get());

		if (resultado.isPresent()) {
			cidade.alterarImposto(novo_imposto);
			voltarMain();
		}
	}

}
