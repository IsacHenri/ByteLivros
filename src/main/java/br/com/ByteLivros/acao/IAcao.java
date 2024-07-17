package br.com.ByteLivros.acao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAcao {
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException;
}

