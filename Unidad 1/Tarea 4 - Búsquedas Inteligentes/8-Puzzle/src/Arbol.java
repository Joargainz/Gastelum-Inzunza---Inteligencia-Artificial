import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Arbol {
    Nodo raiz;

    public Arbol(Nodo raiz) {
        this.raiz = raiz;
    }

    // --- BÚSQUEDA EN ANCHURA (BFS) ---
    public Nodo realizarBusquedaEnAnchura(String objetivo) {
        Queue<Nodo> cola = new LinkedList<>();
        HashSet<String> visitados = new HashSet<>();
        cola.add(raiz);
        visitados.add(raiz.estado);

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();
            System.out.println("Procesando (BFS): " + actual.estado);
            if (actual.estado.equals(objetivo)) return actual;

            for (String sucesor : actual.obtenerSucesores()) {
                if (!visitados.contains(sucesor)) {
                    visitados.add(sucesor);
                    cola.add(new Nodo(sucesor, actual));
                }
            }
        }
        return null;
    }

    // --- BÚSQUEDA EN PROFUNDIDAD (DFS) ---
    public Nodo realizarBusquedaEnProfundidad(String objetivo) {
        Stack<Nodo> pila = new Stack<>();
        HashSet<String> visitados = new HashSet<>();
        pila.push(raiz);
        visitados.add(raiz.estado);

        while (!pila.isEmpty()) {
            Nodo actual = pila.pop();
            System.out.println("Procesando (DFS): " + actual.estado);
            if (actual.estado.equals(objetivo)) return actual;

            for (String sucesor : actual.obtenerSucesores()) {
                if (!visitados.contains(sucesor)) {
                    visitados.add(sucesor);
                    pila.push(new Nodo(sucesor, actual));
                }
            }
        }
        return null;
    }

    // --- BÚSQUEDA DE COSTO UNIFORME (UCS) ---
    public Nodo realizarBusquedaCostoUniforme(String objetivo) {
        PriorityQueue<Nodo> colaPrioridad = new PriorityQueue<>();
        HashSet<String> visitados = new HashSet<>();
        colaPrioridad.add(raiz);
        visitados.add(raiz.estado);

        while (!colaPrioridad.isEmpty()) {
            Nodo actual = colaPrioridad.poll();
            System.out.println("Procesando (UCS): " + actual.estado + " (Costo: " + actual.profundidad + ")");
            if (actual.estado.equals(objetivo)) return actual;

            for (String sucesor : actual.obtenerSucesores()) {
                if (!visitados.contains(sucesor)) {
                    visitados.add(sucesor);
                    colaPrioridad.add(new Nodo(sucesor, actual));
                }
            }
        }
        return null;
    }

    // --- BÚSQUEDA POR PROFUNDIDAD LIMITADA (DLS) ---
    public Nodo realizarBusquedaLimitada(String objetivo, int limite) {
        Stack<Nodo> pila = new Stack<>();
        HashSet<String> visitados = new HashSet<>();
        pila.push(raiz);
        visitados.add(raiz.estado);

        while (!pila.isEmpty()) {
            Nodo actual = pila.pop();
            System.out.println("Procesando (DLS): " + actual.estado + " (Prof: " + actual.profundidad + ")");
            if (actual.estado.equals(objetivo)) return actual;

            if (actual.profundidad < limite) {
                for (String sucesor : actual.obtenerSucesores()) {
                    if (!visitados.contains(sucesor)) {
                        visitados.add(sucesor);
                        pila.push(new Nodo(sucesor, actual));
                    }
                }
            }
        }
        return null;
    }

    // --- BÚSQUEDA A* (A-STAR) ---
    public Nodo realizarBusquedaAEstrella(String objetivo) {
        Nodo raizAStar = new Nodo(this.raiz.estado, null, objetivo);
        PriorityQueue<Nodo> colaPrioridad = new PriorityQueue<>();
        HashSet<String> visitados = new HashSet<>();
        colaPrioridad.add(raizAStar);
        visitados.add(raiz.estado);
        
        while (!colaPrioridad.isEmpty()) {
            Nodo actual = colaPrioridad.poll();
            System.out.println("Procesando (A*): " + actual.estado + " (f(n)=" + actual.costo + ", g(n)=" + actual.profundidad + ", h(n)=" + actual.heuristica + ")");
            if (actual.estado.equals(objetivo)) return actual;

            for (String sucesor : actual.obtenerSucesores()) {
                if (!visitados.contains(sucesor)) {
                    visitados.add(sucesor);
                    colaPrioridad.add(new Nodo(sucesor, actual, objetivo));
                }
            }
        }
        return null;
    }
}