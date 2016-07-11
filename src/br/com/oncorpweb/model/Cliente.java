package br.com.oncorpweb.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Cliente implements Serializable {

	private Long id;
	private TipoIdentificador tipoIdentificador;
	private String nome, identificador, email, telefone;
	private Usuario usuario;
	private ClienteEndereco clienteEndereco;
	private Boolean flagAtivo;

	public Cliente() {

	}

	public Cliente(Boolean flagAtivo, TipoIdentificador tipoIdentificador, Usuario usuario, ClienteEndereco clienteEndereco) {

		this.flagAtivo = flagAtivo;
		this.tipoIdentificador = tipoIdentificador;
		this.usuario = usuario;
		this.clienteEndereco = clienteEndereco;
	}

	public Cliente(Long id) {
		
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoIdentificador getTipoIdentificador() {
		return tipoIdentificador;
	}

	public void setTipoIdentificador(TipoIdentificador tipoIdentificador) {
		this.tipoIdentificador = tipoIdentificador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ClienteEndereco getClienteEndereco() {
		return clienteEndereco;
	}

	public void setClienteEndereco(ClienteEndereco clienteEndereco) {
		this.clienteEndereco = clienteEndereco;
	}

	public Boolean getFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(Boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

}
