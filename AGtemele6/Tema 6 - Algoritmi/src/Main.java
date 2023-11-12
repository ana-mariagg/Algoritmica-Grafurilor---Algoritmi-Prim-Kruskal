public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.Read();
        if (graph.OSinguraCompConexa()) {
            System.out.println("Algoritmul lui Prim:");
            graph.Prim();
            System.out.println(" ");
            System.out.println("Algoritmul lui Kruskal:");
            graph.Kruskal();
        } else {
            System.out.println("EROARE! Graful are mai mult de o componenta conexa. Nu se pot folosi algoritmii.");
        }

    }
}
