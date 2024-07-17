package br.com.ByteLivros.dominio;

import java.math.BigDecimal;
import java.util.Objects;

public class Endereco extends EntidadeDominio{

	private String tipoResidencia;
	private String logradouro;
	private String numero;
	private String bairro;
	private String cep;
	private String tipoLogradouro;
	private Cidade cidade;
	private String tipoEndereco;
	private String observacao;
	private Integer enderecoUsuarioId;
	private BigDecimal valorFrete;
	
	
	public Endereco(String tipoResidencia, 
					String logradouro, 
					String numero, 
			        String cep, 
			        String bairro, 
			        String tipoLogradouro, 
			        String tipoEndereco, 
			        String observacao, 
			        Cidade cidade) {
		
		setTipoResidencia(tipoResidencia);
		setLogradouro(logradouro);
		setTipoLogradouro(tipoLogradouro);
		setNumero(numero);
		setCep(cep);
		setBairro(bairro); 
		setTipoLogradouro(tipoLogradouro);
		setTipoEndereco(tipoEndereco);
		setObservacao(observacao);
		setCidade(cidade);
	}
	
	public Endereco( String logradouro, 
					 String numero,
					 String bairro,
					 BigDecimal valorFrete,
					 Cidade cidade) {
		setLogradouro(logradouro);
		setNumero(numero);
		setBairro(bairro);
		this.valorFrete = valorFrete;
		setCidade(cidade);
		}
	
	public Endereco( String logradouro, 
			 String numero,
			 String bairro,
			 Cidade cidade) {
		setLogradouro(logradouro);
		setNumero(numero);
		setBairro(bairro);
		setCidade(cidade);
	}
	
	public Endereco() {
	}

	public Endereco(Integer id) {
		setId(id);
	}
	
	public Endereco(Integer id, BigDecimal valorFrete) {
		setId(id);
		this.valorFrete = valorFrete;
	}

	public String getTipoResidencia() {
		return tipoResidencia;
	}
	
	public void setTipoResidencia(String tipoResidencia) {
		Objects.requireNonNull(tipoResidencia);
		tipoResidencia = tipoResidencia.trim().toUpperCase();
		this.tipoResidencia = tipoResidencia;
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	
	public void setLogradouro(String logradouro) {
		Objects.requireNonNull(logradouro);
		logradouro = logradouro.trim().toUpperCase();
		this.logradouro = logradouro;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		Objects.requireNonNull(cep);
		this.cep = cep;
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		Objects.requireNonNull(tipoLogradouro);
		tipoLogradouro = tipoLogradouro.trim().toUpperCase();
		this.tipoLogradouro = tipoLogradouro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		Objects.requireNonNull(cidade);
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		Objects.requireNonNull(bairro);
		bairro = bairro.trim().toUpperCase();
		this.bairro = bairro;
	}

	public String getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(String tipoEndereco) {
		Objects.requireNonNull(tipoEndereco);
		tipoEndereco = tipoEndereco.trim().toUpperCase();
		this.tipoEndereco = tipoEndereco;
	}

	public Integer getEnderecoUsuarioId() {
		return enderecoUsuarioId;
	}

	public void setEnderecoUsuarioId(Integer enderecoUsuarioId) {
		this.enderecoUsuarioId = enderecoUsuarioId;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}
	
}
