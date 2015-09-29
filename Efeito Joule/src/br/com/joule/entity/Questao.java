package br.com.joule.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.joule.entity.Aula;

@Entity
@Table(name="T_SEJ_QUESTAO")
public class Questao implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "questaoSequence", sequenceName = "QUESTAO_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "questaoSequence")
	@Column(name="cd_questao")
	private long id;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "pergunta")
	private String pergunta;
	
	@OneToMany(mappedBy="questao", cascade=CascadeType.ALL)
	@Column(name="cd_alternativa")
	private List<Alternativa> listaAlternativas;

	@ManyToOne()
	@JoinColumn(name="cd_aula")
	private Aula aula;
	
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
	

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}
}