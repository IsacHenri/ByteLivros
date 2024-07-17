package br.com.ByteLivros.strategy;

import java.math.BigDecimal;
import java.util.Iterator;

import br.com.ByteLivros.dominio.CarrinhoDeCompras;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Item;



public class AlterarItensDoCarrinho implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		Item item = (Item)entidade;
		
		Item itemConsultado = buscaPorId(item.getId());
		itemConsultado.setQtdeProdutos(item.getQtdeProdutos());
		
		BigDecimal preco = item.getLivro().getPreco().multiply(new BigDecimal(item.getQtdeProdutos()));
		
		itemConsultado.setPrecoItem(preco);
		
		return null;
	}
	
	public Item buscaPorId(Integer id) {
		
		CarrinhoDeCompras cdc = new CarrinhoDeCompras();
		
		for (Item item : cdc.getItens()) {
			if(item.getId()== id) {
				return item;
			}
			
		}
		
		return null;
	}

}
