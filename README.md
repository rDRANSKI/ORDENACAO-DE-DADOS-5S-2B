# Trabalho de Estruturas de Dados II - Vertex Cover
Acadêmico: Ronaldo Dranski
Período/Curso: ADSIS_5S
Repositório destinado à entrega do exercício prático de simulação de War Room. O objetivo é resolver o problema de Cobertura de Vértices (Vertex Cover) aplicado a um cenário de ataque cibernético em uma infraestrutura de rede.

## Implementação e Complexidade

O problema do Vertex Cover pertence à classe dos problemas NP-Completos. Para obter uma solução eficiente em tempo de execução, foi implementado um algoritmo de aproximação fator 2.

- **Estrutura utilizada:** Lista de adjacência implementada com `HashMap` e `HashSet` para otimização de busca de vizinhos.
- **Complexidade de Tempo:** $O(V + E)$, processando os vértices e arestas de forma linear na etapa de redução.
- **Complexidade de Espaço:** $O(V + E)$ para armazenamento da topologia informada dinamicamente.

## Como rodar o projeto

1. Compilar o arquivo:
   ```bash
   javac WarRoomVertexCover.java
