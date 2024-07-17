package br.com.ByteLivros.acao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Grafico;
import br.com.ByteLivros.fachada.Fachada;

public class AcaoAnaliseVendas implements IAcao {
	private static String dtInicio;
	private static String dtFim;
	private Grafico grafico;
	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		dtInicio = request.getParameter("dtInicio");
		dtFim = request.getParameter("dtFim");
		
		if (dtInicio != null && dtFim != null && !dtInicio.isEmpty() && !dtFim.isEmpty()) {

			grafico = new Grafico(dtInicio, dtFim);

		}
		Fachada fac = new Fachada();
		
		
		EntidadeDominio entDom = fac.consultaGrafico(grafico);
		
		
		Grafico grafico = (Grafico) entDom;

		String txtLabels = "[ ";
		String dataSet = "[";
		
		if (grafico != null) {

			Map<String, Map<String, BigDecimal>> dados = grafico.getDados();


			for (String data : dados.keySet()) {
				txtLabels += "'" + data + "', ";
			}
			txtLabels += "] ";
			List<String> categorias = new ArrayList<>();

			categorias.add("Romance");
			categorias.add("Policial");
			categorias.add("Infantil");
			categorias.add("Terror");

			for (String categoria : categorias) {
				String auxDataset = "{ label: '" + categoria + "', data: [";
				for (Map<String, BigDecimal> mapa : dados.values()) {

					if (mapa.get(categoria) == null) {
						auxDataset += 0 + ",";
					} else {
						auxDataset += mapa.get(categoria) + ",";
					}

				}
				auxDataset += "], borderWidth: 1}, ";
				dataSet += auxDataset;
			}

			dataSet += "]";
			
			request.setAttribute("dataSet", dataSet);
			request.setAttribute("txtLabels", txtLabels);

		}
		request.setAttribute("dtInicioPesquisada", dtInicio);
		request.setAttribute("dtFimPesquisada", dtFim);
		
		return "forward:paginaGrafico.jsp";
	}

}
