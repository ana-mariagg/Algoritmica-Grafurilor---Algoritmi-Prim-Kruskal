import java.util.Vector;
import javax.swing.JFrame;

public class Main
{
    static Vector<Vector<Integer>>  listeAdiacenta=new Vector<Vector<Integer>>();
    static Vector<Vector<Integer>>  listeCosturi=new Vector<Vector<Integer>>();
    private static void initUIN()
    {
        JFrame f = new JFrame("Algoritmica Grafurilor");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new Neorientat(listeAdiacenta, listeCosturi));
        f.setSize(700, 700);
        f.setVisible(true);
    }

    public static void main(String[] args)
    {
        initUIN();
    }
}