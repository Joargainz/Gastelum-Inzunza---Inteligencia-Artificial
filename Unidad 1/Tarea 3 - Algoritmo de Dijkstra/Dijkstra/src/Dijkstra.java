import java.util.Arrays;

public class Dijkstra {
    private static final int V = 9;

    private int distanciaMinima(int[] distancia, boolean[] visitado) {
        int min = Integer.MAX_VALUE;
        int minIndice = -1;

        for (int v = 0; v < V; v++) {
            if (!visitado[v] && distancia[v] <= min) {
                min = distancia[v];
                minIndice = v;
            }
        }
        return minIndice;
    }

    private void imprimirSolucion(int[] distancia) {
        System.out.println("Distancia desde el origen a cada vértice:");
        for (int i = 0; i < V; i++) {
            System.out.println("Vértice " + i + ": " + distancia[i]);
        }
    }

    public void dijkstra(int[][] grafo, int origen) {
        int[] distancia = new int[V]; 

        boolean[] visitado = new boolean[V];

        Arrays.fill(distancia, Integer.MAX_VALUE);
        Arrays.fill(visitado, false);

        distancia[origen] = 0;

        for (int count = 0; count < V - 1; count++) {

            int u = distanciaMinima(distancia, visitado);

            visitado[u] = true;

            for (int v = 0; v < V; v++) {

                if (!visitado[v] && grafo[u][v] != 0 &&
                    distancia[u] != Integer.MAX_VALUE &&
                    distancia[u] + grafo[u][v] < distancia[v]) {
                    distancia[v] = distancia[u] + grafo[u][v];
                }
            }
        }
        imprimirSolucion(distancia);
    }

    public static void main(String[] args) {

        int[][] grafo = new int[][] {
            {0, 4, 0, 0, 0, 0, 0, 8, 0},
            {4, 0, 8, 0, 0, 0, 0, 11, 0},
            {0, 8, 0, 7, 0, 4, 0, 0, 2},
            {0, 0, 7, 0, 9, 14, 0, 0, 0},
            {0, 0, 0, 9, 0, 10, 0, 0, 0},
            {0, 0, 4, 14, 10, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, 1, 6},
            {8, 11, 0, 0, 0, 0, 1, 0, 7},
            {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        Dijkstra t = new Dijkstra();
        t.dijkstra(grafo, 0); 
    }
}