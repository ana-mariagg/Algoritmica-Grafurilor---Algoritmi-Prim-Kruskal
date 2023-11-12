import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;


public class Kruskal {


    public static void quicksort(int s, int d, Vector<Muchie> arce) {
        int i = s, j = d;
        Muchie x = arce.get((s + d) / 2);
        do {
            while (arce.get(i).getCost() < x.getCost()) i++;
            while (arce.get(j).getCost() > x.getCost()) j--;
            if (i <= j) {
                Muchie temp = arce.get(i);
                arce.set(i, arce.get(j));
                arce.set(j, temp);
                i++;
                j--;
            }
        } while (i <= j);
        if (s < j) quicksort(s, j, arce);
        if (d > i) quicksort(i, d, arce);
    }

    static Vector<Muchie> ProgramKruskal(Vector<Vector<Integer>> listeAdiacenta, Vector<Muchie>listaMuchii ) {

        int nrNoduri=listeAdiacenta.size();
        int nrMuchii=listaMuchii.size();
        Vector<Muchie> A = listaMuchii;
        Vector<Muchie> A_prim = new Vector<Muchie>();
        quicksort(0, A.size() - 1, A);
        Vector<Boolean> vizitat = new Vector();
        Vector<Integer> coada = new Vector();
        for (Muchie muchie : A) {

            vizitat.setSize(nrMuchii);
            for (int j = 0; j < nrMuchii; j++)
                vizitat.set(j, false);
            int ok = 1;
            coada.clear();
            Muchie a = muchie;
            coada.add(muchie.getNodeStart().getNumber());
            int st =muchie.getNodeEnd().getNumber();
            while (!coada.isEmpty()) {

                for (int index = 0; index < A_prim.size(); index++) {
                    if (!vizitat.get(index)) {
                        if (st == A_prim.get(index).getNodeStart().getNumber()) {
                            coada.add(A_prim.get(index).getNodeEnd().getNumber());
                            vizitat.set(index, true);
                        }
                        if (st == A_prim.get(index).getNodeEnd().getNumber()) {
                            coada.add(A_prim.get(index).getNodeStart().getNumber());
                            vizitat.set(index, true);
                        }
                    }
                }
                coada.remove(0);
                if (coada.size() > 0) {
                    st = coada.get(0);
                    if (st == a.getNodeStart().getNumber()) {
                        ok = 0;
                        break;
                    }
                }
            }
            if (ok == 1 && A_prim.size() < nrNoduri - 1) {
                A_prim.add(a);
            }
        }

        return A_prim;
    }

}
