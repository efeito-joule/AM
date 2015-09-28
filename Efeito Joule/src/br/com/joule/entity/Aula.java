package br.com.joule.entity;

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
@Table (name="T_SEJ_AULA")
public class Aula implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "aulaSequence", sequenceName = "AULA_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aulaSequence")
	@Column(name="cd_aula")
	private long id;
	
	@Column(name = "nome")
	private String nome;

	@Column(name = "url_video")
	private String urlVideo;
	
	@OneToMany
	@JoinColumn(name = "questao_id")
	private List<Questao> questoes;

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
}