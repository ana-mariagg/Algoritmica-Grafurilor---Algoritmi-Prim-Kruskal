import java.util.Vector;

public class Boruvka {

    static Vector<Muchie> ProgramBoruvka(Vector<Vector<Integer>> listeAdiacenta, Vector<Vector<Integer>> listeCosturi,
                                         Vector<Muchie>listaMuchii) {

        Vector<Vector<Integer>> N = new Vector<Vector<Integer>>();
        int nrNoduri=listeAdiacenta.size();
        int nrMuchii=listaMuchii.size();
        Vector<Muchie> A = listaMuchii;
        Vector<Muchie> A_prim = new Vector<Muchie>();
        Vector<Vector<Integer>> M=new Vector<Vector<Integer>>();
        int n=listeAdiacenta.size();

        for(int i=1;i<=n;i++)
        {
            Vector<Integer> v=new Vector<Integer>();
            v.add(i);
            N.add(v);
            Vector<Integer> v2=new Vector<Integer>();
            v2.add(i);
            M.add(v2);
        }
        while(A_prim.size()<n)
        {
            for(int i=0;i<N.size();i++)
            {
                Vector<Integer> a=new Vector<Integer>();
                Vector<Muchie> b=new Vector<Muchie>();

                Muchie minim=new Muchie();
                minim.setCost(Integer.MAX_VALUE);
                int poz=0;
                for(int j=0;j<A.size();j++)
                {
                    if(A.get(j).getNodeStart().getNumber()==N.get(i).get(0))
                    {
                        if(A.get(j).getCost()<minim.getCost())
                        {
                            minim=A.get(j);
                            poz=A.get(j).getNodeStart().getNumber()-1;
                        }
                    }
                    if(A.get(j).getNodeEnd().getNumber()==N.get(i).get(0))
                    {
                        if(A.get(j).getCost()<minim.getCost())
                        {
                            minim=A.get(j);
                            poz=A.get(j).getNodeEnd().getNumber()-1;
                        }
                    }
                }
                N.get(i).add(poz);
                N.get(poz).clear();
                for(int k=0;k<N.get(i).size();k++)
                {
                    N.get(poz).add(N.get(i).get(k));
                }
                boolean ok=true;
                for(int k=0;k<A_prim.size();k++)
                {
                    if(A_prim.get(k).getNodeStart()==minim.getNodeStart()&&A_prim.get(k).getNodeEnd()==minim.getNodeEnd()
                            ||A_prim.get(k).getNodeStart()==minim.getNodeEnd()&&A_prim.get(k).getNodeEnd()==minim.getNodeStart())
                    {
                        ok=false;
                    }
                }
                //if(ok==true)
                {
                    A_prim.add(minim);
                }
            }
        }
        for(int i=0;i<N.size();i++)
        {
            int k=0;
            boolean ok=true;
            int poz=0;
            for(int j=0;j<M.size();j++)
            {
                int m=0;
                while(k<N.get(i).size()&&m<M.get(j).size())
                {
                    if(N.get(i).get(k)==M.get(j).get(m))
                    {
                        ok=false;
                        poz=j;
                    }
                    k++;
                    m++;
                }

            }
        }


        return A_prim;
    }
}
