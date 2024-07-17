package br.com.ByteLivros.dominio;

import java.math.BigDecimal;
import java.util.Objects;

public class Livro extends EntidadeDominio{

	    private String autor;
	    private String categoria;
	    private BigDecimal preco;
	    private Integer ano;
	    private String titulo;
	    private String editora;
	    private String edicao;
	    private String isbn;
	    private Integer numeroPaginas;
	    private Integer qtde;
	    private String sinopse;
	    private BigDecimal altura;
	    private BigDecimal largura;
	    private BigDecimal profundidade;
	    private String codBarras;
	    private String codImagem;
	    private GrupoDePrecificacao grupoDePrecificacao;
	    
	    public Livro(Integer id, String titulo, String codImagem) {
	    	setId(id);
	    	this.titulo = titulo;
	    	this.codImagem = codImagem;
	    }
	    		
	    
		public Livro(String autor, 
					 String categoria, 
					 BigDecimal preco, 
					 Integer ano, 
					 String titulo, 
					 String editora,
					 String edicao, 
					 String isbn, 
					 Integer numeroPaginas, 
					 Integer qtde, 
					 String sinopse, 
					 BigDecimal altura,
					 BigDecimal largura, 
					 BigDecimal profundidade, 
					 String codBarras,
					 GrupoDePrecificacao grupoDePrecificacao) {

			this.autor = autor;
			this.categoria = categoria;
			this.preco = preco;
			this.ano = ano;
			this.titulo = titulo;
			this.editora = editora;
			this.edicao = edicao;
			this.isbn = isbn;
			this.numeroPaginas = numeroPaginas;
			this.qtde = qtde;
			this.sinopse = sinopse;
			this.altura = altura;
			this.largura = largura;
			this.profundidade = profundidade;
			this.codBarras = codBarras;
			this.grupoDePrecificacao = grupoDePrecificacao;
		}

		public Livro() {
			// TODO Auto-generated constructor stub
		}
		
		public Livro(BigDecimal preco) {
			this.preco = preco;
		}
		
		public Livro(Integer id) {
			setId(id);
		}

		public Livro(Integer id, String titulo) {
			setId(id);
			this.titulo = titulo;
		}

		public Livro(Integer id, 
					 String titulo, 
					 BigDecimal preco, 
					 Integer qtde, 
					 String codImagem, 
					 BigDecimal altura, 
					 BigDecimal largura, 
					 BigDecimal profundidade) {
			// TODO Auto-generated constructor stub
			setId(id);
			this.titulo = titulo;
			this.preco = preco;
			this.qtde = qtde;
			this.codImagem = codImagem;
			this.altura = altura;
			this.largura = largura;
			this.profundidade = profundidade;
		}
		
		public String getAutor() {
			return autor;
		}

		public void setAutor(String autor) {
			this.autor = autor;
		}

		public String getCategoria() {
			return categoria;
		}

		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}

		public BigDecimal getPreco() {
			return preco;
		}

		public void setPreco(BigDecimal preco) {
			this.preco = preco;
		}

		public Integer getAno() {
			return ano;
		}

		public void setAno(Integer ano) {
			this.ano = ano;
		}

		public String getTitulo() {
			return titulo;
		}

		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}

		public String getEditora() {
			return editora;
		}

		public void setEditora(String editora) {
			this.editora = editora;
		}

		public String getEdicao() {
			return edicao;
		}

		public void setEdicao(String edicao) {
			this.edicao = edicao;
		}

		public String getIsbn() {
			return isbn;
		}

		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}

		public Integer getNumeroPaginas() {
			return numeroPaginas;
		}

		public void setNumeroPaginas(Integer numeroPaginas) {
			this.numeroPaginas = numeroPaginas;
		}

		public Integer getQtde() {
			return qtde;
		}

		public void setQtde(Integer qtde) {
			this.qtde = qtde;
		}

		public String getSinopse() {
			return sinopse;
		}

		public void setSinopse(String sinopse) {
			this.sinopse = sinopse;
		}

		public BigDecimal getAltura() {
			return altura;
		}

		public void setAltura(BigDecimal altura) {
			this.altura = altura;
		}

		public BigDecimal getLargura() {
			return largura;
		}

		public void setLargura(BigDecimal largura) {
			this.largura = largura;
		}

		public BigDecimal getProfundidade() {
			return profundidade;
		}

		public void setProfundidade(BigDecimal profundidade) {
			this.profundidade = profundidade;
		}

		public String getCodBarras() {
			return codBarras;
		}

		public void setCodBarras(String codBarras) {
			this.codBarras = codBarras;
		}

		public String getCodImagem() {
			return codImagem;
		}

		public void setCodImagem(String codImagem) {
			this.codImagem = codImagem;
		}

		public GrupoDePrecificacao getGrupoDePrecificacao() {
			return grupoDePrecificacao;
		}

		public void setGrupoDePrecificacao(GrupoDePrecificacao grupoDePrecificacao) {
			this.grupoDePrecificacao = grupoDePrecificacao;
		}
	    
		
	    
	    
	    
	    
}