import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {

    public static void main(String[] args) {
        String estadoInicial = "7245 6831";
        String estadoFinal = " 12345678";

        // Reiniciamos el contador de nodos antes de empezar
        Nodo.contadorNodos.set(0); 
        Arbol arbol = new Arbol(new Nodo(estadoInicial, null));
        Nodo resultado = null;
        
        // Medición de tiempo
        long tiempoInicio = System.nanoTime();

        // Uso de los diferentes metodos
        // resultado = arbol.realizarBusquedaEnAnchura(estadoFinal);
        // resultado = arbol.realizarBusquedaEnProfundidad(estadoFinal);
        // resultado = arbol.realizarBusquedaCostoUniforme(estadoFinal);
        // resultado = arbol.realizarBusquedaLimitada(estadoFinal,100);
        resultado = arbol.realizarBusquedaAEstrella(estadoFinal);
        
        
        long tiempoFin = System.nanoTime();
        long duracionMs = (tiempoFin - tiempoInicio) / 1000000; // Convertir a milisegundos

        if (resultado != null) {
            System.out.println("\n¡Solución encontrada!");
            imprimirSolucion(resultado);
            
            // Imprimir estadísticas de rendimiento
            System.out.println("\n--- Estadísticas de Rendimiento ---");
            System.out.println("Tiempo de ejecución: " + duracionMs + " ms");
            System.out.println("Nodos generados: " + Nodo.contadorNodos.get());

        } else {
            System.out.println("\nNo se encontró una solución.");
        }
    }

    public static void imprimirSolucion(Nodo solucion) {
        List<Nodo> pasos = new ArrayList<>();
        Nodo actual = solucion;
        while (actual != null) {
            pasos.add(actual);
            actual = actual.padre;
        }
        Collections.reverse(pasos);

        System.out.println("Pasos para la solución (" + (pasos.size()) + " movimientos):");
        for (int i = 0; i < pasos.size(); i++) {
            Nodo nodoActual = pasos.get(i);
            System.out.println("\n--- Paso " + (i + 1) + " ---");
            if (i > 0) {
                Nodo nodoAnterior = pasos.get(i - 1);
                System.out.println(obtenerMovimiento(nodoAnterior.estado, nodoActual.estado));
            } else {
                System.out.println("Estado Inicial");
            }
            imprimirTablero(nodoActual.estado);
        }
    }
    
    public static void imprimirTablero(String estado) {
        System.out.println("-------------");
        for (int i = 0; i < estado.length(); i++) {
            System.out.print("| " + estado.charAt(i) + " ");
            if ((i + 1) % 3 == 0) {
                System.out.println("|");
                System.out.println("-------------");
            }
        }
    }
    
    public static String obtenerMovimiento(String estadoAnterior, String estadoActual) {
        int posEspacioAnterior = estadoAnterior.indexOf(' ');
        int posEspacioActual = estadoActual.indexOf(' ');
        char piezaMovida = estadoAnterior.charAt(posEspacioActual);
        String direccion = "";

        switch (posEspacioActual - posEspacioAnterior) {
            case -3: direccion = "Abajo"; break;
            case 3:  direccion = "Arriba"; break;
            case -1: direccion = "Derecha"; break;
            case 1:  direccion = "Izquierda"; break;
        }
        return "Movimiento: Pieza '" + piezaMovida + "' se movió hacia " + direccion;
    }
}