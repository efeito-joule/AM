CREATE OR REPLACE PROCEDURE RANKING
IS
BEGIN
  UPDATE T_SEJ_HISTORICO
  SET T_SEJ_HISTORICO.nr_posAula=
    (SELECT T1.rank FROM(
      SELECT CD_ALUNO,
       CD_AULA,
       NR_ACERTO,
       NR_ERRO,
       DENSE_RANK() OVER (PARTITION BY CD_AULA ORDER BY NR_ACERTO - NR_ERRO DESC) AS rank
      FROM T_SEJ_HISTORICO
    ) T1
    WHERE T1.CD_ALUNO = T_SEJ_HISTORICO.cd_aluno 
    AND T1.CD_AULA = T_SEJ_HISTORICO.cd_aula
  );
  
END RANKING;

INSERT INTO T_SEJ_UF (id, descricao, estado)
VALUES ( 1, 'AC', 'Acre');

INSERT INTO T_SEJ_UF (ID, DESCRICAO, ESTADO)
VALUES (2, 'AL', 'Alagoas');

INSERT INTO T_SEJ_UF (ID, DESCRICAO, ESTADO)
VALUES (3, 'AP', 'Amap�');

INSERT INTO T_SEJ_UF (ID, DESCRICAO, ESTADO)
VALUES (4, 'AM', 'Amazonas');

INSERT INTO T_SEJ_UF (id, descricao, estado)
VALUES (5, 'BA', 'Bahia');

INSERT INTO T_SEJ_UF (ID, DESCRICAO, ESTADO)
VALUES (6, 'CE', 'Cear�');

INSERT INTO T_SEJ_UF (id, descricao, estado)
VALUES (7, 'DF', 'Distrito Federal');

INSERT INTO T_SEJ_UF (ID, DESCRICAO, ESTADO)
VALUES (8, 'ES', 'Esp�rito Santo');

INSERT INTO T_SEJ_UF (ID, DESCRICAO, ESTADO)
VALUES (9, 'GO', 'Goi�s');

INSERT INTO T_SEJ_UF (ID, DESCRICAO, ESTADO)
VALUES (10, 'MA', 'Maranh�o');

INSERT INTO T_SEJ_UF (ID, DESCRICAO, ESTADO)
VALUES (11, 'MT', 'Mato Grosso');

INSERT INTO T_SEJ_UF (id, descricao, estado)
VALUES (12, 'MS', 'Mato Grosso do Sul');

INSERT INTO T_SEJ_UF (id, descricao, estado)
VALUES (13, 'MG', 'Minas Gerais');

INSERT INTO T_SEJ_UF (ID, DESCRICAO, ESTADO)
VALUES (14, 'PA', 'Par�');

INSERT INTO T_SEJ_UF (ID, DESCRICAO, ESTADO)
VALUES (15, 'PB', 'Para�ba');

INSERT INTO T_SEJ_UF (ID, DESCRICAO, ESTADO)
VALUES (16, 'PR', 'Paran�');

INSERT INTO T_SEJ_UF (ID, DESCRICAO, ESTADO)
VALUES (17, 'PE', 'Pernambuco');

INSERT INTO T_SEJ_UF (ID, DESCRICAO, ESTADO)
VALUES (18, 'PI', 'Piau�');

INSERT INTO T_SEJ_UF (id, descricao, estado)
VALUES (19, 'RJ', 'Rio de Janeiro');

INSERT INTO T_SEJ_UF (id, descricao, estado)
VALUES (20, 'RN', 'Rio Grande do Norte');

INSERT INTO T_SEJ_UF (id, descricao, estado)
VALUES (21, 'RS', 'Rio Grande do Sul');

INSERT INTO T_SEJ_UF (ID, DESCRICAO, ESTADO)
VALUES (22, 'RO', 'Rond�nia');

INSERT INTO T_SEJ_UF (id, descricao, estado)
VALUES (23, 'RR', 'Roraima');

INSERT INTO T_SEJ_UF (id, descricao, estado)
VALUES (24, 'SC', 'Santa Catarina');