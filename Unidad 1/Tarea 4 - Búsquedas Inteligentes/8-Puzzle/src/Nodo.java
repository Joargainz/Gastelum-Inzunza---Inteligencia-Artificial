import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger; 

public class Nodo implements Comparable<Nodo> {
    
    // Contador para rastrear el número total de nodos generados.
    public static final AtomicInteger contadorNodos = new AtomicInteger(0);

    String estado;
    Nodo padre;
    int profundidad;
    int costo;
    int heuristica;

    public Nodo(String estado, Nodo padre, String objetivo) {
        this.estado = estado;
        this.padre = padre;
        
        // Incrementamos el contador cada vez que se crea un nuevo nodo.
        contadorNodos.incrementAndGet();

        if (padre != null) {
            this.profundidad = padre.profundidad + 1;
        } else {
            this.profundidad = 0;
        }

        if (objetivo != null && !objetivo.isEmpty()) {
            this.heuristica = calcularHeuristica(estado, objetivo);
        } else {
            this.heuristica = 0;
        }
        
        this.costo = this.profundidad + this.heuristica;
    }

    public Nodo(String estado, Nodo padre) {
        this(estado, padre, "");
    }
    
    //Funcion heuristica: Conteo de fichas mal colocadas
    private int calcularHeuristica(String estadoActual, String estadoObjetivo) {
        int malColocadas = 0;
        for (int i = 0; i < estadoActual.length(); i++) {
            if (estadoActual.charAt(i) != ' ' && estadoActual.charAt(i) != estadoObjetivo.charAt(i)) {
                malColocadas++;
            }
        }
        return malColocadas;
    }

    @Override
    public int compareTo(Nodo otro) {
        return Integer.compare(this.costo, otro.costo);
    }
    
    public List<String> obtenerSucesores() {
        List<String> successors = new ArrayList<String>();

        switch (estado.indexOf(" ")) {
            case 0: {
                successors.add(estado.replace(estado.charAt(0), '*').replace(estado.charAt(1), estado.charAt(0)).replace('*', estado.charAt(1)));
                successors.add(estado.replace(estado.charAt(0), '*').replace(estado.charAt(3), estado.charAt(0)).replace('*', estado.charAt(3)));
                break;
            }
            case 1: {
                successors.add(estado.replace(estado.charAt(1), '*').replace(estado.charAt(0), estado.charAt(1)).replace('*', estado.charAt(0)));
                successors.add(estado.replace(estado.charAt(1), '*').replace(estado.charAt(2), estado.charAt(1)).replace('*', estado.charAt(2)));
                successors.add(estado.replace(estado.charAt(1), '*').replace(estado.charAt(4), estado.charAt(1)).replace('*', estado.charAt(4)));
                break;
            }
            case 2: {
                successors.add(estado.replace(estado.charAt(2), '*').replace(estado.charAt(1), estado.charAt(2)).replace('*', estado.charAt(1)));
                successors.add(estado.replace(estado.charAt(2), '*').replace(estado.charAt(5), estado.charAt(2)).replace('*', estado.charAt(5)));
                break;
            }
            case 3: {
                successors.add(estado.replace(estado.charAt(3), '*').replace(estado.charAt(0), estado.charAt(3)).replace('*', estado.charAt(0)));
                successors.add(estado.replace(estado.charAt(3), '*').replace(estado.charAt(4), estado.charAt(3)).replace('*', estado.charAt(4)));
                successors.add(estado.replace(estado.charAt(3), '*').replace(estado.charAt(6), estado.charAt(3)).replace('*', estado.charAt(6)));
                break;
            }
            case 4: {
                successors.add(estado.replace(estado.charAt(4), '*').replace(estado.charAt(1), estado.charAt(4)).replace('*', estado.charAt(1)));
                successors.add(estado.replace(estado.charAt(4), '*').replace(estado.charAt(3), estado.charAt(4)).replace('*', estado.charAt(3)));
                successors.add(estado.replace(estado.charAt(4), '*').replace(estado.charAt(5), estado.charAt(4)).replace('*', estado.charAt(5)));
                successors.add(estado.replace(estado.charAt(4), '*').replace(estado.charAt(7), estado.charAt(4)).replace('*', estado.charAt(7)));
                break;
            }
            case 5: {
                successors.add(estado.replace(estado.charAt(5), '*').replace(estado.charAt(2), estado.charAt(5)).replace('*', estado.charAt(2)));
                successors.add(estado.replace(estado.charAt(5), '*').replace(estado.charAt(4), estado.charAt(5)).replace('*', estado.charAt(4)));
                successors.add(estado.replace(estado.charAt(5), '*').replace(estado.charAt(8), estado.charAt(5)).replace('*', estado.charAt(8)));
                break;
            }
            case 6: {
                successors.add(estado.replace(estado.charAt(6), '*').replace(estado.charAt(3), estado.charAt(6)).replace('*', estado.charAt(3)));
                successors.add(estado.replace(estado.charAt(6), '*').replace(estado.charAt(7), estado.charAt(6)).replace('*', estado.charAt(7)));
                break;
            }
            case 7: {
                successors.add(estado.replace(estado.charAt(7), '*').replace(estado.charAt(4), estado.charAt(7)).replace('*', estado.charAt(4)));
                successors.add(estado.replace(estado.charAt(7), '*').replace(estado.charAt(6), estado.charAt(7)).replace('*', estado.charAt(6)));
                successors.add(estado.replace(estado.charAt(7), '*').replace(estado.charAt(8), estado.charAt(7)).replace('*', estado.charAt(8)));
                break;
            }
            case 8: {
                successors.add(estado.replace(estado.charAt(8), '*').replace(estado.charAt(5), estado.charAt(8)).replace('*', estado.charAt(5)));
                successors.add(estado.replace(estado.charAt(8), '*').replace(estado.charAt(7), estado.charAt(8)).replace('*', estado.charAt(7)));
                break;
            }
        }
        return successors;
    }
}