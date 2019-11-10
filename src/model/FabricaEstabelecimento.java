package model;

public class FabricaEstabelecimento {

	private static FabricaEstabelecimento instancia;

	public static FabricaEstabelecimento pegaInstancia() {
		if (instancia == null) {
			instancia = new FabricaEstabelecimento();
		}

		return (instancia);
	}

	public Estabelecimento gerarCasa() {
		return new Casa();
	}

	public Estabelecimento gerarHospital() {
		return new Hospital();
	}

	public Estabelecimento gerarPraca() {
		return new Praca();
	}

	public Estabelecimento gerarBanco() {
		return new Banco();
	}
}
