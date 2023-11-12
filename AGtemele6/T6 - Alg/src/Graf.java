import java.util.Vector;

import javax.swing.*;
import java.awt.Graphics;

public class Graf extends JPanel {

    private Vector<Node> listaNoduri = new Vector<Node>();
    private Vector<Muchie> listaMuchii = new Vector<Muchie>();
    int node_diam;

    Graf(int node_diam,Vector<Node> listaNoduri,
         Vector<Muchie> listaMuchii) {
        this.node_diam = node_diam;
        this.listaMuchii = new Vector<Muchie>();
        this.listaMuchii = listaMuchii;
        this.listaNoduri = new Vector<Node>();
        this.listaNoduri = listaNoduri;
    }

    Graf()
    {
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Muchie m : this.listaMuchii) {
            m.drawMuchie(g);
        }

        for (int i = 0; i < listaNoduri.size(); i++) {
            listaNoduri.elementAt(i).drawNode(g, node_diam,2);
        }
    }
}