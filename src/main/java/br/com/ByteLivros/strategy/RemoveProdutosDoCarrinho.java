package br.com.ByteLivros.strategy;

import java.util.Iterator;

import br.com.ByteLivros.dominio.CarrinhoDeCompras;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Item;


public class RemoveProdutosDoCarrinho implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
		CarrinhoDeCompras cdc = new CarrinhoDeCompras();
		
		Iterator<Item> it = cdc.getItens().iterator();
		
		while(it.hasNext()) {
			Item item = it.next();
			
              if(item.getId()== entidade.getId()) {
            
				it.remove();
				
			}
			
		}
		
		return null;
	}

}
