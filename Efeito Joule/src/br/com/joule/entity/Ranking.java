package br.com.joule.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="T_SEJ_RANKING")
@SequenceGenerator(name="seqRanking", sequenceName="SEQ_RANKING",allocationSize=1)
public class Ranking implements Serializable{
		@Transient
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(generator="seqRanking", strategy=GenerationType.SEQUENCE)
		@Column(name="cd_ranking") 
		private long id;
		
		@OneToOne
		@JoinColumn(name="cd_aluno")
		private Aluno aluno;
		
		@Column(name="nr_erro")
		private int numErro;
		
		@Column(name="nr_acerto")
		private int numAcerto;
		
		@Column(name="nr_posTotal")
		private int posicaoTotal;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public Aluno getAluno() {
			return aluno;
		}

		public void setAluno(Aluno aluno) {
			this.aluno = aluno;
		}

		public int getNumErro() {
			return numErro;
		}

		public void setNumErro(int numErro) {
			this.numErro = this.numErro + numErro;
		}

		public int getNumAcerto() {
			return numAcerto;
		}

		public void setNumAcerto(int numAcerto) {
			this.numAcerto = this.numAcerto + numAcerto;
		}

		public int getPosicaoTotal() {
			return posicaoTotal;
		}

		public void setPosicaoTotal(int posicaoTotal) {
			this.posicaoTotal = posicaoTotal;
		}
		
		
}
