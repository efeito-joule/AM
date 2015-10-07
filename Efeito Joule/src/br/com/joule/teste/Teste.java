package br.com.joule.teste;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import br.com.joule.dao.AlunoDAO;
import br.com.joule.dao.AulaDAO;
import br.com.joule.dao.CursoDAO;
import br.com.joule.dao.HistoricoDAO;
import br.com.joule.dao.MatriculaDAO;
import br.com.joule.dao.QuestaoDAO;
import br.com.joule.dao.RankingDAO;
import br.com.joule.daoimpl.AlunoDAOImpl;
import br.com.joule.daoimpl.AulaDAOImpl;
import br.com.joule.daoimpl.CursoDAOImpl;
import br.com.joule.daoimpl.HistoricoDAOImpl;
import br.com.joule.daoimpl.MatriculaDAOImpl;
import br.com.joule.daoimpl.QuestaoDAOImpl;
import br.com.joule.daoimpl.RankingDAOImpl;
import br.com.joule.entity.Alternativa;
import br.com.joule.entity.Aluno;
import br.com.joule.entity.Aula;
import br.com.joule.entity.Curso;
import br.com.joule.entity.Historico;
import br.com.joule.entity.Matricula;
import br.com.joule.entity.Questao;
import br.com.joule.entity.Ranking;
import br.com.joule.exceptions.DBCommitException;
import br.com.joule.singleton.EMFactorySingleton;

public class Teste {

	public static void main(String[] args) {
		
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();
		
		/*Populando e testando os alunos */
		
		AlunoDAO alunoDAO = new AlunoDAOImpl(em);
		
		Aluno aluno1 = new Aluno();
		aluno1.setNome("Vanks");
		aluno1.setEmail("vanks@gmail.com");
		aluno1.setSenha("xxx");
		
		Aluno aluno2 = new Aluno();
		aluno2.setNome("Francielly");
		aluno2.setEmail("francielly@gmail.com");
		aluno2.setSenha("xxx");
		
		Aluno aluno3 = new Aluno();
		aluno3.setNome("Natália");
		aluno3.setEmail("natália@gmail.com");
		aluno3.setSenha("xxx");
		
		Aluno aluno4 = new Aluno();
		aluno4.setNome("Vitorio");
		aluno4.setEmail("vitorio@gmail.com");
		aluno4.setSenha("xxx");
		
		Aluno aluno5 = new Aluno();
		aluno5.setNome("Thiago");
		aluno5.setEmail("thiago@gmail.com");
		aluno5.setSenha("xxx");
		
		Aluno aluno6 = new Aluno();
		aluno6.setNome("Rita");
		aluno6.setEmail("rita@gmail.com");
		aluno6.setSenha("xxx");
		
		Aluno aluno7 = new Aluno();
		aluno7.setNome("Agessandro");
		aluno7.setEmail("agessandro@gmail.com");
		aluno7.setSenha("xxx");
		
		Aluno aluno8 = new Aluno();
		aluno8.setNome("Lucia");
		aluno8.setEmail("lucia@gmail.com");
		aluno8.setSenha("xxx");
		
		Aluno aluno9 = new Aluno();
		aluno9.setNome("Ricardo");
		aluno9.setEmail("ricardo@gmail.com");
		aluno9.setSenha("xxx");
		
		Aluno aluno10 = new Aluno();
		aluno10.setNome("Surian");
		aluno10.setEmail("surian@gmail.com");
		aluno10.setSenha("xxx");
		
		Aluno aluno11 = new Aluno();
		aluno11.setNome("Antonio");
		aluno11.setEmail("antonio@gmail.com");
		aluno11.setSenha("xxx");
		
		Aluno aluno12 = new Aluno();
		aluno12.setNome("Evelyn");
		aluno12.setEmail("evelyn@gmail.com");
		aluno12.setSenha("xxx");
		
		
		try {
			alunoDAO.create(aluno1);
			alunoDAO.create(aluno2);
			alunoDAO.create(aluno3);
			alunoDAO.create(aluno4);
			alunoDAO.create(aluno5);
			alunoDAO.create(aluno6);
			alunoDAO.create(aluno7);
			alunoDAO.create(aluno8);
			alunoDAO.create(aluno9);
			alunoDAO.create(aluno10);
			alunoDAO.create(aluno11);
			alunoDAO.create(aluno12);
			System.out.println("************************************");
			System.out.println("");
			System.out.println("- 12 alunos cadastrados com sucesso -");
			System.out.println("");
		
		} catch (DBCommitException e1) {
			e1.printStackTrace();
			System.out.println("---   Erro ao criar os alunos!  ---");
		}
		
		/*Populando e testando os cursos */
		
		CursoDAO cursoDAO = new CursoDAOImpl(em);
		
		Curso curso1 = new Curso();
		curso1.setNome("Cinemática");
		curso1.setDescricao("Neste curso você vai aprender os conceitos de movimento: "
				+ "Espaço, Tempo, Velocidade, Movimento Uniforme, Aceleração, Movimento Uniformemente Variado"
				+ "Mocimento Circular");
		
		Curso curso2 = new Curso();
		curso2.setNome("Dinâmica");
		curso2.setDescricao("Neste curso você vai aprender os conceitos de dinâmica: "
				+ "Massa, Força, As leis de Newton, Torque, Atrito, Equilíbrio e Força Resultante");
		
			try {
				cursoDAO.create(curso1);
				cursoDAO.create(curso2);
				System.out.println("************************************");
				System.out.println("");
				System.out.println("- 2 cursos cadastrados com sucesso -");
				System.out.println("");
			} catch (DBCommitException e) {
				e.printStackTrace();
				System.out.println("---    Erro ao criar os cursos! ----");
			}
		
		/*Populando e testando as aulas */
		
		AulaDAO aulaDAO = new AulaDAOImpl(em);
		
		Aula aula1 = new Aula();
		aula1.setNome("Introdução");
		aula1.setUrlVideo("https://www.youtube.com/v/ILrzYSQcwVU");
		aula1.setCurso(curso1);
		
		Aula aula2 = new Aula();
		aula2.setNome("Espaço");
		aula2.setUrlVideo("https://www.youtube.com/v/ILrzYSQcwVU");
		aula2.setCurso(curso1);
		
		Aula aula3 = new Aula();
		aula3.setNome("Movimento Circular");
		aula3.setUrlVideo("https://www.youtube.com/v/ILrzYSQcwVU");
		aula3.setCurso(curso1);
		
		Aula aula4 = new Aula();
		aula4.setNome("As Leis de Newton");
		aula4.setUrlVideo("https://www.youtube.com/v/ILrzYSQcwVU");
		aula4.setCurso(curso2);
		
		Aula aula5 = new Aula();
		aula5.setNome("Atrito");
		aula5.setUrlVideo("https://www.youtube.com/v/ILrzYSQcwVU");
		aula5.setCurso(curso2);
		
		Aula aula6 = new Aula();
		aula6.setNome("Torque");
		aula6.setUrlVideo("https://www.youtube.com/v/ILrzYSQcwVU");
		aula6.setCurso(curso2);
		
		try {
			aulaDAO.create(aula1);
			aulaDAO.create(aula2);
			aulaDAO.create(aula3);
			aulaDAO.create(aula4);
			aulaDAO.create(aula5);
			aulaDAO.create(aula6);
			System.out.println("************************************");
			System.out.println("");
			System.out.println("-- 6 aulas cadastradas com sucesso -");
			System.out.println("");
			
		} catch (DBCommitException e) {
			e.printStackTrace();
			System.out.println("---    Erro ao criar as aulas! ----");
		}
		
		/*Populando e testando as questões */
		
		QuestaoDAO questaoDAO = new QuestaoDAOImpl(em);
		
		boolean resposta01;
		boolean resposta02;
		boolean resposta03;
		boolean resposta04;
		boolean resposta05;

		resposta01=false;
		resposta02=true;
		resposta03=false;
		resposta04=false;
		resposta05=false;
		
		Questao questao1 = new Questao();
		questao1.setAula(aula1);
		questao1.setDescricao("Conhecimentos de unidades de medidas utilizadas no"
				+ "estudo da cinemática. Velocidade e Aceleração");
		questao1.setPergunta("O fabricante informa que um carro, partindo do repouso, "
				+ "atinge 100 km/h em 10 segundos. A melhor estimativa para o valor "
				+ "da aceleração nesse intervalo de tempo, em m/s2, é:");
		
		Alternativa alternativa = new Alternativa();
		alternativa.setDescricao("3,0.10^-3");
		alternativa.setResposta(resposta01);
		alternativa.setQuestao(questao1);
		
		Alternativa alternativa2 = new Alternativa();
		alternativa2.setDescricao("2,8");
		alternativa2.setResposta(resposta02);
		alternativa2.setQuestao(questao1);
		
		Alternativa alternativa3 = new Alternativa();
		alternativa3.setDescricao("3,6");
		alternativa3.setResposta(resposta03);
		alternativa3.setQuestao(questao1);
		
		Alternativa alternativa4 = new Alternativa();
		alternativa4.setDescricao("9,8");
		alternativa4.setResposta(resposta04);
		alternativa4.setQuestao(questao1);
		
		Alternativa alternativa5 = new Alternativa();
		alternativa5.setDescricao("10");
		alternativa5.setResposta(resposta05);
		alternativa5.setQuestao(questao1);
		
		List<Alternativa> alternativas1 = new ArrayList<Alternativa>(); 
		alternativas1.add(alternativa);
		alternativas1.add(alternativa2);
		alternativas1.add(alternativa3);
		alternativas1.add(alternativa4);
		alternativas1.add(alternativa5);
		
		questao1.setListaAlternativas(alternativas1);
		
		try {
			questaoDAO.create(questao1);
			System.out.println("**************************************");
			System.out.println("");
			System.out.println("- 1 questão cadastrada com sucesso -");
			System.out.println("");
				
		} catch (DBCommitException e1) {
			e1.printStackTrace();
			System.out.println("---    Erro ao criar as aulas! ----");
		}
		
		/* Segunda questão */
		
		resposta01=false;
		resposta02=true;
		resposta03=false;
		resposta04=false;
		resposta05=false;
		
		Questao questao2 = new Questao();
		questao2.setAula(aula4);
		questao2.setDescricao("conhecimentos de cinemática, aceleração média e "
				+ "leis de Newton");
		questao2.setPergunta("Um corpo de massa igual a 3,0 kg está sob a ação de "
				+ "uma força horizontal constante. Ele se desloca num plano "
				+ "horizontal, sem atrito e sua velocidade aumenta de 2,0 m/s "
				+ "em 4,0s. A intensidade da força vale:");
		
		alternativa = new Alternativa();
		alternativa.setDescricao("3/8 N");
		alternativa.setResposta(resposta01);
		alternativa.setQuestao(questao2);
		
		alternativa2 = new Alternativa();
		alternativa2.setDescricao("1,5 N");
		alternativa2.setResposta(resposta02);
		alternativa2.setQuestao(questao2);
		
		alternativa3 = new Alternativa();
		alternativa3.setDescricao("3,0 N");
		alternativa3.setResposta(resposta03);
		alternativa3.setQuestao(questao2);
		
		alternativa4 = new Alternativa();
		alternativa4.setDescricao("6,0 N");
		alternativa4.setResposta(resposta04);
		alternativa4.setQuestao(questao2);
		
		alternativa5 = new Alternativa();
		alternativa5.setDescricao("24 N");
		alternativa5.setResposta(resposta05);
		alternativa5.setQuestao(questao2);
		
		List<Alternativa> alternativas2 = new ArrayList<Alternativa>(); 
		alternativas2.add(alternativa);
		alternativas2.add(alternativa2);
		alternativas2.add(alternativa3);
		alternativas2.add(alternativa4);
		alternativas2.add(alternativa5);
		
		questao2.setListaAlternativas(alternativas2);
		
		try {
			questaoDAO.create(questao2);
			System.out.println("**************************************");
			System.out.println("");
			System.out.println("- 1 questão cadastradas com sucesso -");
			System.out.println("");
				
		} catch (DBCommitException e1) {
			e1.printStackTrace();
			System.out.println("---    Erro ao criar as aulas! ----");
		}
		
		/* terceira questão */
		
		resposta01=false;
		resposta02=false;
		resposta03=true;
		resposta04=false;
		resposta05=false;
			
		Questao questao3 = new Questao();
		questao3.setAula(aula1);
		questao3.setDescricao("conhecimentos de cinemática, deslocamento escalar e espaço percorrido");
		questao3.setPergunta(" Um móvel parte do km 50, indo até o km 60, onde, mudando"
				+ " o sentido do movimento, vai até o km 32. O deslocamento escalar e "
				+ "a distância efetivamente percorrida são, respectivamente: ");
		
		alternativa = new Alternativa();
		alternativa.setDescricao("28 km e 28 km");
		alternativa.setResposta(resposta01);
		alternativa.setQuestao(questao3);
		
		alternativa2 = new Alternativa();
		alternativa2.setDescricao("18 km e 38 km");
		alternativa2.setResposta(resposta02);
		alternativa2.setQuestao(questao3);
		
		alternativa3 = new Alternativa();
		alternativa3.setDescricao("-18 km e 38 km");
		alternativa3.setResposta(resposta03);
		alternativa3.setQuestao(questao3);
		
		alternativa4 = new Alternativa();
		alternativa4.setDescricao("-18 km e 18 km");
		alternativa4.setResposta(resposta04);
		alternativa4.setQuestao(questao3);
		
		alternativa5 = new Alternativa();
		alternativa5.setDescricao("38 km e 18 km");
		alternativa5.setResposta(resposta05);
		alternativa5.setQuestao(questao3);
		
		List<Alternativa> alternativas3 = new ArrayList<Alternativa>(); 
		alternativas3.add(alternativa);
		alternativas3.add(alternativa2);
		alternativas3.add(alternativa3);
		alternativas3.add(alternativa4);
		alternativas3.add(alternativa5);
		
		questao3.setListaAlternativas(alternativas3);
		
		try {
			questaoDAO.create(questao3);
			System.out.println("**************************************");
			System.out.println("");
			System.out.println("- 1 questão cadastradas com sucesso -");
			System.out.println("");
				
		} catch (DBCommitException e1) {
			e1.printStackTrace();
			System.out.println("---    Erro ao criar as aulas! ----");
		}
			
		
		
		
		/*Populando e testando as matrículas */
			
		MatriculaDAO matriculaDAO = new MatriculaDAOImpl(em);
		
		Matricula matricula1 = new Matricula();
		Calendar dataInicio = Calendar.getInstance();
		matricula1.setDataInicio(dataInicio);
		matricula1.setUsuario(aluno1);
		matricula1.setCurso(curso1);
		
		Matricula matricula2 = new Matricula();
		matricula2.setDataInicio(dataInicio);
		matricula2.setUsuario(aluno1);
		matricula2.setCurso(curso2);
		
		Matricula matricula3 = new Matricula();
		matricula3.setDataInicio(dataInicio);
		matricula3.setUsuario(aluno2);
		matricula3.setCurso(curso1);
		
		Matricula matricula4 = new Matricula();
		matricula4.setDataInicio(dataInicio);
		matricula4.setUsuario(aluno2);
		matricula4.setCurso(curso2);
		
		Matricula matricula5 = new Matricula();
		matricula5.setDataInicio(dataInicio);
		matricula5.setUsuario(aluno3);
		matricula5.setCurso(curso1);
		
		Matricula matricula6 = new Matricula();
		matricula6.setDataInicio(dataInicio);
		matricula6.setUsuario(aluno3);
		matricula6.setCurso(curso2);
		
		Matricula matricula7 = new Matricula();
		matricula7.setDataInicio(dataInicio);
		matricula7.setUsuario(aluno4);
		matricula7.setCurso(curso1);
		
		Matricula matricula8 = new Matricula();
		matricula8.setDataInicio(dataInicio);
		matricula8.setUsuario(aluno4);
		matricula8.setCurso(curso2);
		
		Matricula matricula9 = new Matricula();
		matricula9.setDataInicio(dataInicio);
		matricula9.setUsuario(aluno5);
		matricula9.setCurso(curso1);
		
		Matricula matricula10 = new Matricula();
		matricula10.setDataInicio(dataInicio);
		matricula10.setUsuario(aluno5);
		matricula10.setCurso(curso2);
		
		Matricula matricula11 = new Matricula();
		matricula11.setDataInicio(dataInicio);
		matricula11.setUsuario(aluno6);
		matricula11.setCurso(curso1);
		
		Matricula matricula12 = new Matricula();
		matricula12.setDataInicio(dataInicio);
		matricula12.setUsuario(aluno6);
		matricula12.setCurso(curso2);
		
		Matricula matricula13 = new Matricula();
		matricula13.setDataInicio(dataInicio);
		matricula13.setUsuario(aluno7);
		matricula13.setCurso(curso1);
		
		Matricula matricula14 = new Matricula();
		matricula14.setDataInicio(dataInicio);
		matricula14.setUsuario(aluno7);
		matricula14.setCurso(curso2);
		
		Matricula matricula15 = new Matricula();
		matricula15.setDataInicio(dataInicio);
		matricula15.setUsuario(aluno8);
		matricula15.setCurso(curso1);
		
		Matricula matricula16 = new Matricula();
		matricula16.setDataInicio(dataInicio);
		matricula16.setUsuario(aluno8);
		matricula16.setCurso(curso2);
		
		Matricula matricula17 = new Matricula();
		matricula17.setDataInicio(dataInicio);
		matricula17.setUsuario(aluno9);
		matricula17.setCurso(curso1);
		
		Matricula matricula18 = new Matricula();
		matricula18.setDataInicio(dataInicio);
		matricula18.setUsuario(aluno9);
		matricula18.setCurso(curso2);
		
		Matricula matricula19 = new Matricula();
		matricula19.setDataInicio(dataInicio);
		matricula19.setUsuario(aluno10);
		matricula19.setCurso(curso1);
		
		Matricula matricula20 = new Matricula();
		matricula20.setDataInicio(dataInicio);
		matricula20.setUsuario(aluno10);
		matricula20.setCurso(curso2);
		
		Matricula matricula21 = new Matricula();
		matricula21.setDataInicio(dataInicio);
		matricula21.setUsuario(aluno11);
		matricula21.setCurso(curso1);
		
		Matricula matricula22 = new Matricula();
		matricula22.setDataInicio(dataInicio);
		matricula22.setUsuario(aluno11);
		matricula22.setCurso(curso2);
		
		Matricula matricula23 = new Matricula();
		matricula23.setDataInicio(dataInicio);
		matricula23.setUsuario(aluno12);
		matricula23.setCurso(curso1);
		
		Matricula matricula24 = new Matricula();
		matricula24.setDataInicio(dataInicio);
		matricula24.setUsuario(aluno12);
		matricula24.setCurso(curso2);
		
		try {
			matriculaDAO.create(matricula1);
			matriculaDAO.create(matricula2);
			matriculaDAO.create(matricula3);
			matriculaDAO.create(matricula4);
			matriculaDAO.create(matricula5);
			matriculaDAO.create(matricula6);
			matriculaDAO.create(matricula7);
			matriculaDAO.create(matricula8);
			matriculaDAO.create(matricula9);
			matriculaDAO.create(matricula10);
			matriculaDAO.create(matricula11);
			matriculaDAO.create(matricula12);
			matriculaDAO.create(matricula13);
			matriculaDAO.create(matricula14);
			matriculaDAO.create(matricula15);
			matriculaDAO.create(matricula16);
			matriculaDAO.create(matricula17);
			matriculaDAO.create(matricula18);
			matriculaDAO.create(matricula19);
			matriculaDAO.create(matricula20);
			matriculaDAO.create(matricula21);
			matriculaDAO.create(matricula22);
			matriculaDAO.create(matricula23);
			matriculaDAO.create(matricula24);
			System.out.println("*****************************************");
			System.out.println("");
			System.out.println("- 24 matrículas cadastradas com sucesso -");
			System.out.println("");
			
		} catch (DBCommitException e) {
			e.printStackTrace();
			System.out.println("----   Erro ao criar as matrículas   ----");
		}
		
		/*Populando e testando o histórico e o Ranking */
		
		HistoricoDAO historicoDAO = new HistoricoDAOImpl(em);
		RankingDAO rankingDAO = new RankingDAOImpl(em);
		
		
		Historico historico1 = new Historico();
		Ranking ranking1 = new Ranking();
		historico1.setAluno(aluno1);
		historico1.setAula(aula1);
		historico1.setNumAcerto(10);
		historico1.setNumErro(1);
		ranking1.setAluno(aluno1);
		ranking1.setNumAcerto(10);
		ranking1.setNumErro(1);
		
		Historico historico2 = new Historico();
		
		historico2.setAluno(aluno1);
		historico2.setAula(aula2);
		historico2.setNumAcerto(10);
		historico2.setNumErro(2);
		ranking1.setAluno(aluno1);
		ranking1.setNumAcerto(10);
		ranking1.setNumErro(2);
		
		Historico historico3 = new Historico();
		Ranking ranking2 = new Ranking();
		
		historico3.setAluno(aluno2);
		historico3.setAula(aula1);
		historico3.setNumAcerto(10);
		historico3.setNumErro(3);
		ranking2.setAluno(aluno2);
		ranking2.setNumAcerto(10);
		ranking2.setNumErro(3);
		
		Historico historico4 = new Historico();
		
		historico4.setAluno(aluno2);
		historico4.setAula(aula2);
		historico4.setNumAcerto(10);
		historico4.setNumErro(4);
		ranking2.setAluno(aluno2);
		ranking2.setNumAcerto(10);
		ranking2.setNumErro(4);
		
		Historico historico5 = new Historico();
		Ranking ranking3 = new Ranking();
		
		historico5.setAluno(aluno3);
		historico5.setAula(aula1);
		historico5.setNumAcerto(10);
		historico5.setNumErro(5);
		ranking3.setAluno(aluno3);
		ranking3.setNumAcerto(10);
		ranking3.setNumErro(5);
		
		Historico historico6 = new Historico();
		
		historico6.setAluno(aluno3);
		historico6.setAula(aula2);
		historico6.setNumAcerto(10);
		historico6.setNumErro(6);
		ranking3.setAluno(aluno3);
		ranking3.setNumAcerto(10);
		ranking3.setNumErro(6);
		
		Historico historico7 = new Historico();
		
		Ranking ranking4 = new Ranking();
		
		historico7.setAluno(aluno4);
		historico7.setAula(aula1);
		historico7.setNumAcerto(10);
		historico7.setNumErro(7);
		ranking4.setAluno(aluno4);
		ranking4.setNumAcerto(10);
		ranking4.setNumErro(7);
		
		Historico historico8 = new Historico();
		
		historico8.setAluno(aluno4);
		historico8.setAula(aula2);
		historico8.setNumAcerto(10);
		historico8.setNumErro(8);
		ranking4.setAluno(aluno4);
		ranking4.setNumAcerto(10);
		ranking4.setNumErro(8);
		
		Historico historico9 = new Historico();
		Ranking ranking5 = new Ranking();
		
		historico9.setAluno(aluno5);
		historico9.setAula(aula1);
		historico9.setNumAcerto(20);
		historico9.setNumErro(9);
		ranking5.setAluno(aluno5);
		ranking5.setNumAcerto(20);
		ranking5.setNumErro(9);
		
		Historico historico10 = new Historico();
		
		historico10.setAluno(aluno5);
		historico10.setAula(aula2);
		historico10.setNumAcerto(20);
		historico10.setNumErro(10);
		ranking5.setAluno(aluno5);
		ranking5.setNumAcerto(20);
		ranking5.setNumErro(10);
		
		Historico historico11 = new Historico();
		Ranking ranking6 = new Ranking();
		
		historico11.setAluno(aluno6);
		historico11.setAula(aula1);
		historico11.setNumAcerto(20);
		historico11.setNumErro(1);
		ranking6.setAluno(aluno6);
		ranking6.setNumAcerto(20);
		ranking6.setNumErro(1);
		
		Historico historico12 = new Historico();
		
		historico12.setAluno(aluno6);
		historico12.setAula(aula2);
		historico12.setNumAcerto(20);
		historico12.setNumErro(2);
		ranking6.setAluno(aluno6);
		ranking6.setNumAcerto(20);
		ranking6.setNumErro(2);
		
		Historico historico13 = new Historico();
		Ranking ranking7 = new Ranking();
		
		historico13.setAluno(aluno7);
		historico13.setAula(aula1);
		historico13.setNumAcerto(20);
		historico13.setNumErro(3);
		ranking7.setAluno(aluno7);
		ranking7.setNumAcerto(20);
		ranking7.setNumErro(3);
		
		Historico historico14 = new Historico();
		historico14.setAluno(aluno7);
		historico14.setAula(aula2);
		historico14.setNumAcerto(20);
		historico14.setNumErro(4);
		ranking7.setAluno(aluno7);
		ranking7.setNumAcerto(20);
		ranking7.setNumErro(4);
		
		Historico historico15 = new Historico();
		Ranking ranking8 = new Ranking();
		
		historico15.setAluno(aluno8);
		historico15.setAula(aula1);
		historico15.setNumAcerto(20);
		historico15.setNumErro(5);
		ranking8.setAluno(aluno8);
		ranking8.setNumAcerto(20);
		ranking8.setNumErro(5);
		
		Historico historico16 = new Historico();
		historico16.setAluno(aluno8);
		historico16.setAula(aula2);
		historico16.setNumAcerto(20);
		historico16.setNumErro(6);
		ranking8.setAluno(aluno8);
		ranking8.setNumAcerto(20);
		ranking8.setNumErro(6);
		
		Historico historico17 = new Historico();
		Ranking ranking9 = new Ranking();
		
		historico17.setAluno(aluno9);
		historico17.setAula(aula1);
		historico17.setNumAcerto(20);
		historico17.setNumErro(7);
		ranking9.setAluno(aluno9);
		ranking9.setNumAcerto(20);
		ranking9.setNumErro(7);
		
		Historico historico18 = new Historico();
		historico18.setAluno(aluno9);
		historico18.setAula(aula2);
		historico18.setNumAcerto(20);
		historico18.setNumErro(8);
		ranking9.setAluno(aluno9);
		ranking9.setNumAcerto(20);
		ranking9.setNumErro(8);
		
		Historico historico19 = new Historico();
		Ranking ranking10 = new Ranking();
		
		historico19.setAluno(aluno10);
		historico19.setAula(aula1);
		historico19.setNumAcerto(20);
		historico19.setNumErro(9);
		ranking10.setAluno(aluno10);
		ranking10.setNumAcerto(20);
		ranking10.setNumErro(9);
		
		Historico historico20 = new Historico();
		historico20.setAluno(aluno10);
		historico20.setAula(aula2);
		historico20.setNumAcerto(20);
		historico20.setNumErro(10);
		ranking10.setAluno(aluno10);
		ranking10.setNumAcerto(20);
		ranking10.setNumErro(10);
		
		Historico historico21 = new Historico();
		Ranking ranking11 = new Ranking();
		
		historico21.setAluno(aluno11);
		historico21.setAula(aula1);
		historico21.setNumAcerto(20);
		historico21.setNumErro(11);
		ranking11.setAluno(aluno11);
		ranking11.setNumAcerto(20);
		ranking11.setNumErro(11);
		
		Historico historico22 = new Historico();
		historico22.setAluno(aluno11);
		historico22.setAula(aula2);
		historico22.setNumAcerto(20);
		historico22.setNumErro(12);
		ranking11.setAluno(aluno11);
		ranking11.setNumAcerto(20);
		ranking11.setNumErro(11);
		
		Historico historico23 = new Historico();
		Ranking ranking12 = new Ranking();
		historico23.setAluno(aluno12);
		historico23.setAula(aula1);
		historico23.setNumAcerto(20);
		historico23.setNumErro(13);
		ranking12.setAluno(aluno12);
		ranking12.setNumAcerto(20);
		ranking12.setNumErro(13);
		
		Historico historico24 = new Historico();
		historico24.setAluno(aluno12);
		historico24.setAula(aula2);
		historico24.setNumAcerto(20);
		historico24.setNumErro(14);
		ranking12.setAluno(aluno12);
		ranking12.setNumAcerto(20);
		ranking12.setNumErro(14);
		
			try {
				rankingDAO.create(ranking1);
				historicoDAO.create(historico1);
				
				rankingDAO.create(ranking2);
				rankingDAO.create(ranking3);
				rankingDAO.create(ranking4);
				rankingDAO.create(ranking5);
				rankingDAO.create(ranking6);
				rankingDAO.create(ranking7);
				rankingDAO.create(ranking8);
				rankingDAO.create(ranking9);
				rankingDAO.create(ranking10);
				rankingDAO.create(ranking11);
				rankingDAO.create(ranking12);
				
				historicoDAO.create(historico2);
				historicoDAO.create(historico3);
				historicoDAO.create(historico4);
				historicoDAO.create(historico5);
				historicoDAO.create(historico6);
				historicoDAO.create(historico7);
				historicoDAO.create(historico8);
				historicoDAO.create(historico9);
				historicoDAO.create(historico10);
				historicoDAO.create(historico11);
				historicoDAO.create(historico12);
				historicoDAO.create(historico13);
				historicoDAO.create(historico14);
				historicoDAO.create(historico15);
				historicoDAO.create(historico16);
				historicoDAO.create(historico17);
				historicoDAO.create(historico18);
				historicoDAO.create(historico19);
				historicoDAO.create(historico20);
				historicoDAO.create(historico21);
				historicoDAO.create(historico22);
				historicoDAO.create(historico23);
				historicoDAO.create(historico24);
				System.out.println("*****************************************");
				System.out.println("");
				System.out.println(" 24 historicos e 12 rankings cadastrados ");
				System.out.println("");
				
				System.out.println("Histórico criado");
			} catch (DBCommitException e) {
				System.out.println("erro ao criar os rankings e historicos");
				e.printStackTrace();
			}

			/*Chamando a procedure para calcular os Rankings por aula e Geral */
			
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("proc");
		query.execute();

		
	}
}
