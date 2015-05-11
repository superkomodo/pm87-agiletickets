package br.com.caelum.agiletickets.models;

public enum TipoDeEspetaculo {
	
	CINEMA(0.05, 0.10, 0, 0), 
	SHOW(0.05, 0.10, 0, 0), 
	TEATRO(0.0, 0.0, 0, 0.0), 
	BALLET(0.50, 0.20, 60, 0.10), 
	ORQUESTRA(0.50, 0.20, 60, 0.10);
	
	private double limiteDisponibilidade = 0;
	private double reajusteDisponibilidade = 0;
	private int limiteDuracao = 0;
	private double reajusteDuracao = 0;
	
	private TipoDeEspetaculo(
			double limiteDisponibilidade,
			double reajusteDisponibilidade,
			int limiteDuracao,
			double reajusteDuracao) {
		this.limiteDisponibilidade = limiteDisponibilidade;
		this.reajusteDisponibilidade = reajusteDisponibilidade;
		this.limiteDuracao = limiteDuracao;
		this.reajusteDuracao = reajusteDuracao;
	}
	
	public double getLimiteDisponibilidade() {
		return limiteDisponibilidade;
	}

	public double getReajusteDisponibilidade() {
		return reajusteDisponibilidade;
	}

	public int getLimiteDuracao() {
		return limiteDuracao;
	}

	public double getReajusteDuracao() {
		return reajusteDuracao;
	}
}


