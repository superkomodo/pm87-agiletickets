package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	private static boolean temPoucosIngressos(double taxaDisponibilidade, double limiteDisponibilidade) {
		if (limiteDisponibilidade == 0.0) 
			return false;
		return taxaDisponibilidade <= limiteDisponibilidade;
	}
	
	private static boolean espetaculoLongo(double duracao, int limiteDuracao) {
		if (limiteDuracao == 0)
			return false;
		return duracao > limiteDuracao;
	}
	
	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		
//		if (TipoDeEspetaculo.TEATRO.equals(sessao.getEspetaculo().getTipo()))
//			preco = sessao.getPreco();
//		else {
//			if (temPoucosIngressos(sessao.getTaxaDisponibilidade(), sessao.getEspetaculo().getTipo().getLimiteDisponibilidade())) { 
//				preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(sessao.getEspetaculo().getTipo().getReajusteDisponibilidade())));
//			} 
//			else {
//				preco = sessao.getPreco();
//			}
//			
//			if (espetaculoLongo(sessao.getDuracaoEmMinutos(), sessao.getEspetaculo().getTipo().getLimiteDuracao())) {
//			    preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(sessao.getEspetaculo().getTipo().getReajusteDuracao())));
//			}
//		}
		
		switch (sessao.getEspetaculo().getTipo()) {
			case CINEMA:
			case SHOW:
				//quando estiver acabando os ingressos... 
				if (temPoucosIngressos(sessao.getTaxaDisponibilidade(), 0.05)) { 
					preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
				} else {
					preco = sessao.getPreco();
				}
				break;
	
			case BALLET:
				if (temPoucosIngressos(sessao.getTaxaDisponibilidade(), 0.50)) { 
					preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
				} else {
					preco = sessao.getPreco();
				}
				
				if(sessao.getDuracaoEmMinutos() > 60){
					preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
				}
				break;
			
			case ORQUESTRA:
				if (temPoucosIngressos(sessao.getTaxaDisponibilidade(), 0.50)) { 
					preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
				} else {
					preco = sessao.getPreco();
				}

				if(sessao.getDuracaoEmMinutos() > 60){
					preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
				}
				break;
				
			default:
				//nao aplica aumento para teatro (quem vai é pobretão)
				preco = sessao.getPreco();
				break;
		}
		
		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

}