
WITH TAREFA_DATAS AS (
    SELECT 
        T.ID_LOJA,
        T.TIPO,
        DATE(T.DATA_HORA) AS DIA,
        MIN(T.DATA_HORA) AS PRIMEIRA_TAREFA,
        MAX(T.DATA_HORA) AS ULTIMA_TAREFA
    FROM 
        TAREFA T
    GROUP BY 
        T.ID_LOJA, T.TIPO, DATE(T.DATA_HORA)
)

SELECT 
    TD.ID_LOJA,
    TD.TIPO,
    TD.DIA,
    F.ID_TAREFA AS PRIMEIRA_TAREFA_ID,
    F.ID_USUARIO AS PRIMEIRO_USUARIO,
    F.DATA_HORA AS PRIMEIRO_DATA_HORA,
    L.ID_TAREFA AS ULTIMA_TAREFA_ID,
    L.ID_USUARIO AS ULTIMO_USUARIO,
    L.DATA_HORA AS ULTIMO_DATA_HORA
FROM 
    TAREFA_DATAS TD
JOIN 
    TAREFA F ON F.ID_LOJA = TD.ID_LOJA 
             AND F.TIPO = TD.TIPO 
             AND DATE(F.DATA_HORA) = TD.DIA
             AND F.DATA_HORA = TD.PRIMEIRA_TAREFA
JOIN 
    TAREFA L ON L.ID_LOJA = TD.ID_LOJA
             AND L.TIPO = TD.TIPO
             AND DATE(L.DATA_HORA) = TD.DIA
             AND L.DATA_HORA = TD.ULTIMA_TAREFA;
