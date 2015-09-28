package br.com.joule.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "aluno")
public class Aluno extends Pessoa {

	@Transient
	private static final long serialVersionUID = 1L;

	@Column(name = "nome_usuario")
	private String nomeUsuario;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "senha")
	private String senha;

	public Aluno() {
		super();
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}