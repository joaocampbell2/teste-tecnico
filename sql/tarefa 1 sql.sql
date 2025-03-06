-- 1. Consulta contendo a quantidade de tarefas de ressuprimentos solicitadas por dia.


SELECT 
    DATE(h.DATA_HORA) AS data,
    COUNT(DISTINCT t.ID_TAREFA) AS quantidade_solicitacoes
FROM HISTORICO h
JOIN TAREFA t ON h.ID_TAREFA = t.ID_TAREFA
WHERE h.TIPO = 1
AND t.TIPO = 1 
GROUP BY DATE(h.DATA_HORA)
ORDER BY data;