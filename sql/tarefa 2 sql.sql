-- 2. Consulta contendo a quantidade de tarefas de ressuprimento por situação por dia.

SELECT 
    DATE(t.DATA_HORA) AS data,
    t.SITUACAO,
    COUNT(*) AS quantidade_tarefas
FROM TAREFA t
WHERE t.TIPO = 1
GROUP BY DATE(t.DATA_HORA), t.SITUACAO
ORDER BY data, t.SITUACAO;