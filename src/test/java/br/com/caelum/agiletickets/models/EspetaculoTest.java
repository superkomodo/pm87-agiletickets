package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}

	@Test
	public void cria1SessaoDiariaSeComecaETerminaHoje() {
		
		
		LocalDate inicio= LocalDate.now();
		LocalTime horario = LocalTime.now();
		
		
		
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, inicio, horario, Periodicidade.DIARIA);
		
		Assert.assertEquals(1, sessoes.size());
		
		Assert.assertEquals(inicio.toDateTime(inicio.toDateTime(horario)), sessoes.get(0).getInicio());
	}
	
	@Test
	public void cria3SessoesDiariasSeComecaHojeETerminaDepoisDeAmanha(){
		
		LocalDate inicio= LocalDate.now();
		LocalTime horario = LocalTime.now();
		LocalDate fim = inicio.plusDays(2);
		
		
		
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio,fim , horario, Periodicidade.DIARIA);
		Assert.assertEquals(3, sessoes.size());
		Assert.assertEquals(inicio.toDateTime(horario), sessoes.get(0).getInicio());
		Assert.assertEquals(inicio.toDateTime(horario).plusDays(1), sessoes.get(1).getInicio());
		Assert.assertEquals(inicio.toDateTime(horario).plusDays(2), sessoes.get(2).getInicio());
		
	}
	
	@Test
	public void cria1SessaoSemanalComecaHojeETerminaAmanha(){
		
		LocalDate inicio= LocalDate.now();
		LocalTime horario = LocalTime.now();
		LocalDate fim = inicio.plusDays(1);
		
		
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, Periodicidade.SEMANAL);
		Assert.assertEquals(1, sessoes.size());
		Assert.assertEquals(inicio.toDateTime(horario), sessoes.get(0).getInicio());
		
	}
	
	@Test
	public void cria2SessoesSemanaisEm15Dias(){

		LocalDate inicio= LocalDate.now();
		LocalTime horario = LocalTime.now();
		LocalDate fim = inicio.plusDays(14);
		
		
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, Periodicidade.SEMANAL);
		Assert.assertEquals(2, sessoes.size());
		Assert.assertEquals(inicio.toDateTime(horario), sessoes.get(0).getInicio());
		Assert.assertEquals(inicio.toDateTime(horario).plusDays(7), sessoes.get(1).getInicio());
		
		
	}

	
	
}
