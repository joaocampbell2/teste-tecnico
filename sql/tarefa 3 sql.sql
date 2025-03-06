-- 3. Consulta com a quantidade de tarefas por dia por usuário e a quantidade distinta de produtos ressuprimentos.


SELECT 
    DATE(h.DATA_HORA) AS data,
    h.ID_USUARIO,
    COUNT(DISTINCT h.ID_TAREFA) AS quantidade_tarefas,
    COUNT(DISTINCT r.ID_PRODUTO) AS quantidade_produtos_ressuprimento
FROM HISTORICO h
JOIN TAREFA t ON h.ID_TAREFA = t.ID_TAREFA
LEFT JOIN RESSUPRIMENTO r ON t.ID_TAREFA = r.ID_TAREFA
WHERE t.TIPO = 1 
GROUP BY DATE(h.DATA_HORA), h.ID_USUARIO
ORDER BY data, h.ID_USUARIO;