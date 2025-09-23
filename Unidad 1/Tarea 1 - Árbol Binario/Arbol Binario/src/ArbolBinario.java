// Clase Nodo
class Nodo {
    int valor;
    Nodo izquierdo;
    Nodo derecho;

    public Nodo(int valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
    }
}

// Clase Árbol
class Arbol {
    private Nodo raiz;

    public Arbol() {
        this.raiz = null;
    }

    // Verificar si el árbol está vacío
    public boolean vacio() {
        return raiz == null;
    }

    // Insertar un valor en el árbol
    public void insertar(int valor) {
        raiz = insertarRecursivo(raiz, valor);
    }

    private Nodo insertarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            return new Nodo(valor);
        }
        if (valor < actual.valor) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, valor);
        } else if (valor > actual.valor) {
            actual.derecho = insertarRecursivo(actual.derecho, valor);
        }
        return actual; 
    }

    // Buscar un nodo por valor
    public Nodo buscarNodo(int valor) {
        return buscarRecursivo(raiz, valor);
    }

    private Nodo buscarRecursivo(Nodo actual, int valor) {
        if (actual == null || actual.valor == valor) {
            return actual;
        }
        if (valor < actual.valor) {
            return buscarRecursivo(actual.izquierdo, valor);
        } else {
            return buscarRecursivo(actual.derecho, valor);
        }
    }

    // Imprimir el árbol en inorden
    public void imprimir() {
        imprimirInorden(raiz);
        System.out.println();
    }

    private void imprimirInorden(Nodo actual) {
        if (actual != null) {
            imprimirInorden(actual.izquierdo);
            System.out.print(actual.valor + " ");
            imprimirInorden(actual.derecho);
        }
    }
}

// Clase Main para probar
public class ArbolBinario {
    public static void main(String[] args) {
        Arbol arbol = new Arbol();

        System.out.println("¿Árbol vacío?: " + arbol.vacio()); // true

        arbol.insertar(50);
        arbol.insertar(30);
        arbol.insertar(70);
        arbol.insertar(20);
        arbol.insertar(40);
        arbol.insertar(60);
        arbol.insertar(80);

        System.out.println("¿Árbol vacío?: " + arbol.vacio()); // false

        Nodo encontrado = arbol.buscarNodo(40);
        if (encontrado != null) {
            System.out.println("Nodo encontrado con valor: " + encontrado.valor);
        } else {
            System.out.println("Nodo no encontrado");
        }

        System.out.println("Árbol en inorden:");
        arbol.imprimir();
    }
}
