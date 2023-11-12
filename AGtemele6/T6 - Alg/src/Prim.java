import java.util.Vector;
//Acesta este un algoritm de tip Prim, care este utilizat pentru a genera o parcurgere în arbore a unui graf.
// În programul de mai sus, algoritmul primeste caintrare un numar de noduri, o lista de adiacenta si o lista de costuri pentru fiecare muchie.
// Acesta foloseste aceste date pentru a construi o parcurgere in arbore care are costul minim, folosind o abordare de tip greedy.
// Algoritmul ruleaza pana cand toate nodurile sunt incluse in parcurgerea in arbore, returnand apoi parcurgerea in arbore ca rezultat.
public class Prim {

    static Vector<Vector<Integer>> ProgramPrim(int numarNoduri, Vector<Vector<Integer>> listeAdiacenta, Vector<Vector<Integer>> listeCosturi) {

        int n=numarNoduri;
        int inf = Integer.MAX_VALUE;
        Vector<Integer> N = new Vector<Integer>();
        Vector<Vector<Integer>> E = new Vector<Vector<Integer>>();
        E = listeAdiacenta;
        Vector<Vector<Integer>> b = new Vector<Vector<Integer>>();
        b = listeCosturi;
        //v= lista noduri in functie de cost, ca sa il determine pe cel cu cost minim
        //e=lista muchii, tine evidenta pentru fiecare nod cu predecesorul lui
        //  utilizata pentru a determina muchiile din parcurgerea in arbore dupa ce toate nodurile au fost adaugate;
        Vector<Integer> e = new Vector<Integer>();
        Vector<Integer> v = new Vector<Integer>();
        //nevizitate
        Vector<Integer> N1 = new Vector<Integer>();
        Vector<Integer> N1_ = new Vector<Integer>();
        Vector<Vector<Integer>> A_prim = new Vector<Vector<Integer>>();
        N.add(1);
        N1_.add(1);
        v.add(0);
        A_prim = new Vector<Vector<Integer>>();
        Vector<Integer> vectorAuxiliar = new Vector<Integer>();
        A_prim.add(vectorAuxiliar);
        e.add(0);
        //adauga nodurile in arbore
        /*
        Se construieste parcurgerea in arbore. Algoritmul ruleaza in bucla, adaugand noduri unul cate unul pana cand toate nodurile sunt
        incluse in parcurgere. La fiecare iteratie a buclei, algoritmul alege un nod nevizitat cu costul minim de adaugare in parcurgere, si
        il adauga la parcurgerea in arbore. Costul de adaugare pentru fiecare nod este calculat ca fiind costul muchiei care leaga nodul curent
        la nodul adaugat anterior in parcurgere. Dupa ce nodul a fost adaugat, algoritmul actualizeaza costurile pentru toate nodurile vecine
        nevizitate, inlocuind costul actual cu costul de adaugare mai mic daca este cazul.
        */
        for (int indice = 1; indice < n; indice++) {
            //val "v" pentru fiecare nod se actualizeaza in functie de costul muchiei care leaga nodul curent la nodul adaugat anterior in parcurgere.
            // Dupa ce toate nodurile au fost adaugate in parcurgere, lista "v" poate fi utilizata pentru a determina costul total al parcurgerii
            // in arbore.
            v.add(inf);
            vectorAuxiliar = new Vector<Integer>();
            A_prim.add(vectorAuxiliar);
            e.add(0);
            N.add(indice + 1);
            N1_.add(indice + 1);
        }
        while (N1.size() != N.size()) {
            int y = N1_.get(0);
            for (int x : N1_) {
                if (v.get(y - 1) > v.get(x - 1)) {
                    y = x;
                }
            }
            N1.add(y);
            N1_.remove((Object)y);
            if (y != 1) {
                A_prim.get(y - 1).add(e.get(y - 1));
                A_prim.get(e.get(y - 1) - 1).add(y);
            }
            for (int y_ : E.get(y - 1)) {
                if (N1_.contains(y_) == true) {
                    if (v.get(y_ - 1) > b.get(y - 1).get(y_ - 1)) {
                        v.remove(y_ - 1);
                        v.insertElementAt(b.get(y - 1).get(y_ - 1), y_ - 1);
                        e.remove(y_ - 1);
                        e.insertElementAt(y, y_ - 1);
                    }
                }
            }
        }
        //Dupa ce toate nodurile au fost adaugate in parcurgere, algoritmul returneaza parcurgerea in arbore ca rezultat.
        return A_prim;
    }
}
