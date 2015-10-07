package br.com.joule.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "T_SEJ_ADMINISTRADOR")
@DiscriminatorValue("ADM")
public class Administrador extends Pessoa {

	@Transient
	private static final long serialVersionUID = 1L;

	@Column(name = "nm_usuario")
	private String nomeUsuario;
	
	@Column(name="ds_email", nullable=false,length=300)
	private String email;
	
	@Column(name="ds_senha")
	private String senha;

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
