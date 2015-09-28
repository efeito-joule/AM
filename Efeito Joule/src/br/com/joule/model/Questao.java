package br.com.joule.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "questao")
public class Questao implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "questaoSequence", sequenceName = "QUESTAO_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "questaoSequence")
	private long id;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "pergunta")
	private String pergunta;
	
	@OneToMany
	@JoinColumn(name = "alternativa_id")
	private List<Alternativa> listaAlternativas;

	public Questao() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public List<Alternativa> getListaAlternativas() {
		return listaAlternativas;
	}

	public void setListaAlternativas(List<Alternativa> listaAlternativas) {
		this.listaAlternativas = listaAlternativas;
	}
}