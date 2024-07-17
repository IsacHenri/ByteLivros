package br.com.ByteLivros.strategy;

import br.com.ByteLivros.dominio.CarrinhoDeCompras;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Item;

public class AdicionarAoCarrinho implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		Item item = (Item) entidade;
		
		CarrinhoDeCompras cdc = new CarrinhoDeCompras();
		
		cdc.adiciona(item);
		
		return null;
	}

}
