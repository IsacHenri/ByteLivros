package br.com.ByteLivros.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import br.com.ByteLivros.dominio.Endereco;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Usuario;



public class CalcularFrete implements IStrategy {

	private static Map<String, BigDecimal> tarifaEstado;
	private static Map<String, BigDecimal> tarifaCubagem;
	
	public CalcularFrete() {
		
		tarifaEstado = new HashMap<>();
		tarifaCubagem = new HashMap<>();
		
		tarifaEstado.put("SP", new BigDecimal(31.00));
		tarifaEstado.put("RJ", new BigDecimal(33.00));
		tarifaEstado.put("MG", new BigDecimal(35.00));
		tarifaEstado.put("PR", new BigDecimal(37.00));
		tarifaEstado.put("SC", new BigDecimal(39.00));
		tarifaEstado.put("RS", new BigDecimal(41.00));
		tarifaEstado.put("MS", new BigDecimal(43.00));
		tarifaEstado.put("MT", new BigDecimal(45.00));
		tarifaEstado.put("GO", new BigDecimal(47.00));
		tarifaEstado.put("TO", new BigDecimal(49.00));
		tarifaEstado.put("RO", new BigDecimal(51.00));
		tarifaEstado.put("BA", new BigDecimal(53.00));
		tarifaEstado.put("AC", new BigDecimal(55.00));
		tarifaEstado.put("PA", new BigDecimal(57.00));
		tarifaEstado.put("AM", new BigDecimal(59.00));
		tarifaEstado.put("AP", new BigDecimal(61.00));
		tarifaEstado.put("RR", new BigDecimal(63.00));
		tarifaEstado.put("MA", new BigDecimal(65.00));
		tarifaEstado.put("PI", new BigDecimal(67.00));
		tarifaEstado.put("CE", new BigDecimal(69.00));
		tarifaEstado.put("RN", new BigDecimal(71.00));
		tarifaEstado.put("PB", new BigDecimal(73.00));
		tarifaEstado.put("PE", new BigDecimal(75.00));
		tarifaEstado.put("AL", new BigDecimal(77.00));
		tarifaEstado.put("SE", new BigDecimal(79.00));
		
	}
	
	@Override
	public String processar(EntidadeDominio entidade) {

		Usuario usuario = (Usuario) entidade;
		
		for (Endereco endereco : usuario.getListadeEnderecos()) {
			
			if(endereco.getTipoEndereco().equals("ENTREGA")) {
				
			BigDecimal tarifaEstadual = tarifaEstado.get(endereco.getCidade().getEstado().getDescricao());
			BigDecimal valorTotal;
			
			valorTotal = tarifaEstadual.multiply(new BigDecimal(0.50));
			
			valorTotal = valorTotal.setScale(2);
			
			endereco.setValorFrete(valorTotal);
			
			}
			
		}

		return null;
	}



}

