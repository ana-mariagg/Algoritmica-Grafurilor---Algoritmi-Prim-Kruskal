import java.io.File;
import java.util.Scanner;

public class Graph {
    private int nr_noduri;
    public int[][] MatriceAdiacenta;
    public int nr_arce = 0;

    //constructor
    public Graph() {
    }

    public void Read() {
        try {
            Scanner in = null;
            File file = new File("D:\\facultate\\facultate-anul2\\AG\\Tema 6 - Algoritmi\\src\\MatriceAdiacenta");
            in = new Scanner(file);
            this.nr_noduri = in.nextInt();
            this.MatriceAdiacenta = new int[this.nr_noduri][this.nr_noduri];

            for(int i = 0; i < this.nr_noduri; ++i) {
                for(int j = 0; j < this.nr_noduri; ++j) {
                    this.MatriceAdiacenta[i][j] = in.nextInt();
                    if (this.MatriceAdiacenta[i][j] != 0) {
                        ++this.nr_arce;
                    }
                }
            }
        } catch (Exception var5) {
        }

    }

    public int getRows() {
        return this.nr_noduri;
    }

    private void CompConexa(int vechi, boolean[] vizitate, boolean[] recursiveStack) {
        vizitate[vechi] = true;
        recursiveStack[vechi] = true;
        //pentru fiecare nod "vechi" din nevizitate, daca se leaga de nodul "nou", acesta exista si e nevizitat,
        //atunci se aplica functia pana ajunge nodul nou care acum devine vechi la un nod deja vizitat
        for(int nou = 0; nou < this.nr_noduri; ++nou) {
            if (this.MatriceAdiacenta[vechi][nou] != 0 && !vizitate[nou]) {
                this.CompConexa(nou, vizitate, recursiveStack);
            }
        }
        recursiveStack[vechi] = false;
    }

    public boolean OSinguraCompConexa() {
        int nr_CompCnx = 0;
        boolean[] vizitate = new boolean[this.nr_noduri];
        boolean[] reccursiveStack = new boolean[this.nr_noduri];

        //pentru fiecare nod se verifica daca e vizitat deja
        for(int vechi = 0; vechi < this.nr_noduri; ++vechi) {
            //daca nu e atunci se vede care pot comp conexe pe care le poate forma(dintr-un nod sa pot ajunge in orice alt nod)
            if (!vizitate[vechi]) {
                this.CompConexa(vechi, vizitate, reccursiveStack);
                ++nr_CompCnx;
            }
            //altfel se trece la urmatorul nod
        }

        if (nr_CompCnx == 1) {
            return true;
        } else {
            return false;
        }
    }

    //fct care afla cheia cu costul minim si ii returneaza poz
    private int CostMinim_Key(int[] cost, boolean[] parcurs) {
        //fac minimum infinit si poz lui -1
        int min = Integer.MAX_VALUE;
        int min_poz = -1;

        //luam fiecare nod in parte si vedem daca nodul apare deja in parcurgere
        for(int index = 0; index < this.nr_noduri; ++index) {
            //daca nu apare si nodul are costul mai mic decat minimul se reactualizeaza
            if (!parcurs[index] && cost[index] < min) {
                min = cost[index];
                min_poz = index;
            }
        }

        return min_poz;
    }

    //afisam in parinti parcurgerea
    private void printMST(int[] parinti) {
        System.out.println("Arc \tWeight");
        int cost = 0;

        for(int i = 1; i < this.nr_noduri; ++i) {
            System.out.println(parinti[i] + " - " + i + "\t" + this.MatriceAdiacenta[i][parinti[i]]);
            cost += this.MatriceAdiacenta[i][parinti[i]];
        }

        System.out.printf("Costul minim= %d \n", cost);
    }

    //imi construieste parcurgerea si costul minim
    public void Prim() {
        //vectorul de parinti unde se stocheaza parcurgerea
        int[] parinti = new int[this.nr_noduri];
        //vectorul de chei folosit in a determina costul minim costurile dintre noduri
        int[] noduri = new int[this.nr_noduri];
        //retinem astfel incat sa nu intre un nod de doua ori in parcurgere(acelasi nod are parinti diferiti),
        //ci se ia doar cel cu costul minim
        boolean[] parcurs = new boolean[this.nr_noduri];

        int count;
        //fac vectorul de chei infinit si toate nodurile ca fiind neparcurse
        for(count = 0; count < this.nr_noduri; ++count) {
            noduri[count] = Integer.MAX_VALUE;
            parcurs[count] = false;
        }
        //imi setez startul(nodul 0) cu costul 0 si fara parinte
        noduri[0] = 0;
        parinti[0] = -1;

        //parcurgem nodurile (vor fi mereu nr_noduri parcurse)
        for(count = 0; count < this.nr_noduri - 1; ++count) {
            //pentru fiecare nod imi alege costul minim si se actualizeaza nodul al carui cost e minim in parcurgere ca fiind luat in calcul
            int minIndex = this.CostMinim_Key(noduri, parcurs);
            parcurs[minIndex] = true;

            //imi ia fiecare nod si le verifica cu restul ca sa se actualizeze parcurgerea si se ia in calcul doar cele care nu au fost analizate deja
            for(int i = 0; i < this.nr_noduri; ++i) {
                //de ex 3--->4 cu cost 8 si 5---->4 cu cost 6 ia 5---->4
                if (this.MatriceAdiacenta[minIndex][i] != 0 && !parcurs[i] && this.MatriceAdiacenta[minIndex][i] < noduri[i]) {
                    parinti[i] = minIndex;
                    noduri[i] = this.MatriceAdiacenta[minIndex][i];
                }
            }
        }
        //afisare
        this.printMST(parinti);
    }

    //imi gaseste parintele unui nod dat i
    private int gasire_parinte(int i, int[] parinti) {
        //imi gaseste radacina pe care il face parintele lui i
        while(parinti[i] != i) {
            i = parinti[i];
        }
        return i;
    }

    //uneste 2 mini comp conexe gen 1---->2 si 4--->6 => 1--->2---->4---->6
    private void union1(int i, int j, int[] parent) {
        int a = this.gasire_parinte(i, parent);
        int b = this.gasire_parinte(j, parent);
        parent[a] = b;
    }

    void Kruskal() {
        //parcurgerea finala
        int[] parent = new int[this.nr_noduri];
        int minCost = 0;
        //folosit pentru sortarea arcelor
        int nr_arce;
        for(nr_arce = 0; nr_arce < this.nr_noduri; parent[nr_arce] = nr_arce++) {
        }
        //sortam arcele descresc dupa cost
        int min;
        for(nr_arce = 0; nr_arce < this.nr_noduri - 1; minCost += min) {
            min = Integer.MAX_VALUE;
            int a = -1;
            int b = -1;
            //imi iau 2 noduri pe rand si daca nu au acelasi parinte si costul arcului dintre noduri e mai mic ca costul minim si exista arc
            for(int i = 0; i < this.nr_noduri; ++i) {
                for(int j = 0; j < this.nr_noduri; ++j) {
                    if (this.gasire_parinte(i, parent) != this.gasire_parinte(j, parent) && this.MatriceAdiacenta[i][j] < min && this.MatriceAdiacenta[i][j] != 0) {
                       //se actualizeaza costul minim si se retin nodurile
                        min = this.MatriceAdiacenta[i][j];
                        a = i;
                        b = j;
                    }
                }
            }
            //se face arc intre noduri si le afiseaza
            this.union1(a, b, parent);
            System.out.printf("Arc %d:(%d, %d) cost:%d \n", nr_arce++, a, b, min);
        }

        System.out.printf("Costul minim= %d \n", minCost);
    }
}
