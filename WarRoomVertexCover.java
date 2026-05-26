import java.util.*;

public class WarRoomVertexCover {

    static class Aresta {
        String u;
        String v;

        public Aresta(String u, String v) {
            this.u = u;
            this.v = v;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Aresta)) return false;
            Aresta outra = (Aresta) obj;
            return (this.u.equals(outra.u) && this.v.equals(outra.v)) ||
                   (this.u.equals(outra.v) && this.v.equals(outra.u));
        }

        @Override
        public int hashCode() {
            return u.hashCode() + v.hashCode();
        }
    }

    static class GrafoRede {
        private final Map<String, Set<String>> adjacencia = new HashMap<>();

        public void adicionarConexao(String u, String v) {
            adjacencia.computeIfAbsent(u, k -> new HashSet<>()).add(v);
            adjacencia.computeIfAbsent(v, k -> new HashSet<>()).add(u);
        }

        public Set<String> obterTodosVertices() {
            return adjacencia.keySet();
        }

        public List<Aresta> obterTodasArestas() {
            List<Aresta> listaArestas = new ArrayList<>();
            Set<Aresta> visitadas = new HashSet<>();

            for (String u : adjacencia.keySet()) {
                for (String v : adjacencia.get(u)) {
                    Aresta aresta = new Aresta(u, v);
                    if (!visitadas.contains(aresta)) {
                        listaArestas.add(aresta);
                        visitadas.add(aresta);
                    }
                }
            }
            return listaArestas;
        }

        public Set<String> calcularVertexCoverAproximado() {
            List<Aresta> arestasRestantes = obterTodasArestas();
            Set<String> cobertura = new HashSet<>();

            while (!arestasRestantes.isEmpty()) {
                Aresta arestaAtual = arestasRestantes.get(0);
                String u = arestaAtual.u;
                String v = arestaAtual.v;

                cobertura.add(u);
                cobertura.add(v);

                arestasRestantes.removeIf(a -> a.u.equals(u) || a.v.equals(u) || 
                                               a.u.equals(v) || a.v.equals(v));
            }

            return cobertura;
        }
    }

    private static void exibirPainelWarRoom(GrafoRede grafo, Set<String> cobertura) {
        System.out.println("\n=== RELATÓRIO FINAL DO WAR ROOM ===");
        System.out.println("Dispositivos ativos na rede: " + grafo.obterTodosVertices());
        
        System.out.println("\nConexões analisadas:");
        for (Aresta a : grafo.obterTodasArestas()) {
            System.out.println("  " + a.u + " <---> " + a.v);
        }

        System.out.println("\nSolução Vertex Cover (Vértices Escolhidos): " + cobertura);
        System.out.println("Tamanho da Cobertura: " + cobertura.size());
        
        System.out.println("\nAnálise de Complexidade:");
        System.out.println("- Estrutura: Lista de Adjacência com HashMap e HashSet.");
        System.out.println("- Tempo de Execução: O(V + E) devido à abordagem de aproximação polinomial.");
        System.out.println("====================================");
    }

    public static void main(String[] args) {
        GrafoRede rede = new GrafoRede();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserir conexões da rede (Origem Destino). Digite 'SAIR' para processar:");

        int contador = 1;
        while (true) {
            System.out.print("Conexão " + contador + ": ");
            String entrada = scanner.nextLine().trim();

            if (entrada.equalsIgnoreCase("SAIR")) {
                break;
            }

            String[] partes = entrada.split("\\s+");
            if (partes.length != 2) {
                System.out.println("Entrada inválida. Use o formato: Nó1 Nó2");
                continue;
            }

            rede.adicionarConexao(partes[0], partes[1]);
            contador++;
        }

        if (rede.obterTodosVertices().isEmpty()) {
            System.out.println("Nenhum dado inserido.");
        } else {
            Set<String> resultado = rede.calcularVertexCoverAproximado();
            exibirPainelWarRoom(rede, resultado);
        }

        scanner.close();
    }
}
