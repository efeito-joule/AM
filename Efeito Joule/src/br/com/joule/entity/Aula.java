package br.com.joule.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.joule.entity.Curso;
import br.com.joule.entity.Historico;

@Entity
@Table (name="T_SEJ_AULA")
public class Aula implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "aulaSequence", sequenceName = "AULA_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aulaSequence")
	@Column(name="cd_aula")
	private long id;
	
	@Column(name="nm_aula", nullable=false)
	private String nome;

	@Column(name = "url_video")
	private String urlVideo;
	
	@OneToMany(mappedBy="aula", fetch=FetchType.LAZY)
	@Column(name="cd_questao")
	private List<Questao> questoes;
	
	@ManyToOne
	@JoinColumn(name="cd_curso")
	private Curso curso;
	
	@OneToMany(mappedBy="aula", fetch=FetchType.LAZY)
	@Column(name="cd_historico")
	private List<Historico> historico;

	public Aula() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUrlVideo() {
		return urlVideo;
	}

	public void setUrlVideo(String urlVideo) {
		this.urlVideo = urlVideo;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Historico> getHistorico() {
		return historico;
	}

	public void setHistorico(List<Historico> historico) {
		this.historico = historico;
	}
	
}