package br.com.ByteLivros.dominio;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class Grafico extends EntidadeDominio{

	Map<String, Map<String, BigDecimal>> dados = new TreeMap<>();
	private String dataInicio;
	private String dataFim;
	
	
	public Grafico(String dataInicio, String dataFim) {
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}
	
	public Grafico() {
	}

	public Map<String, Map<String, BigDecimal>> getDados() {
		return dados;
	}

	public void setDados(Map<String, Map<String, BigDecimal>> dados) {
		this.dados = dados;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}
	
	
	
}
