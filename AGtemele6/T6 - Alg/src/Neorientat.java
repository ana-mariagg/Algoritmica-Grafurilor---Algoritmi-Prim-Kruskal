import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;


public class Neorientat extends JPanel {
    Node n=new Node();
    private int nodeNr = 1;
    static public int NodDiam = 30;
    private Vector<Node> listaNoduri;
    private Vector<Muchie> listaMuchii;
    Point pointStart = null;
    Point pointEnd = null;
    boolean isDragging = false;
    boolean move=false;
    JToggleButton b1 = new JToggleButton("AlgoritmulLuiPrim");
    JToggleButton b2 = new JToggleButton("AlgoritmulLuiKruskal");
    JToggleButton b3 = new JToggleButton("AlgoritmulLuiBoruvka");
    JFrame frame1 = new JFrame("Arbore de cost minim-Prim");
    JFrame frame2 = new JFrame("Arbore de cost minim-Kruskal");
    JFrame frame3 = new JFrame("Arbore de cost minim-Boruvka");

    public Neorientat(Vector<Vector<Integer>>  listeAdiacenta,Vector<Vector<Integer>>  listeCosturi)
    {
        listaNoduri = new Vector<Node>();
        listaMuchii = new Vector<Muchie>();

        addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    pointStart = e.getPoint();
                } else if (e.getButton() == MouseEvent.BUTTON1)
                {
                    for(int i=0;i<listaNoduri.size();i++)
                    {
                        double dist=Math.sqrt(Math.pow(listaNoduri.elementAt(i).getCoordX()-e.getX(), 2)+
                                Math.pow(listaNoduri.elementAt(i).getCoordY()-e.getY(), 2));
                        if(dist<=NodDiam)
                        {
                            n=listaNoduri.elementAt(i);
                            move=true;
                        }
                    }
                }
            }

            public void mouseReleased(MouseEvent e) {

                PrintWriter pw =null;
                try {
                    pw = new PrintWriter(new FileWriter("ListeAdiacenta.txt"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                if (e.getButton() == MouseEvent.BUTTON3)
                {

                    boolean position=true;
                    for(int i=0;i<listaNoduri.size()&&position==true;i++)
                    {
                        double dist=Math.sqrt(Math.pow(listaNoduri.elementAt(i).getCoordX()-e.getX(), 2)+
                                Math.pow(listaNoduri.elementAt(i).getCoordY()-e.getY(), 2));
                        if(dist<2*NodDiam)
                        {
                            position=false;
                        }
                    }
                    if (!isDragging)
                    {
                        if(position==true)
                        {
                            for(int i=0;i<listeCosturi.size();i++)
                            {
                                listeCosturi.get(i).add(0);
                            }
                            addNode(e.getX()-15,e.getY()-15);
                            Vector <Integer> v = new Vector<Integer>();
                            for(int i=0;i<listaNoduri.size();i++)
                            {
                                v.add(0);
                            }
                            listeCosturi.add(v);
                            Vector <Integer> v1 = new Vector<Integer>();
                            listeAdiacenta.add(v1);
                            //Vector <Integer> v2 = new Vector<Integer>();
                            //listeCosturi.add(v2);
                        }
                    }
                    else
                    {
                        boolean validS=false;
                        boolean validE=false;
                        Node nodeS=null;
                        Node nodeE=null;
                        for(int i=0;i<listaNoduri.size();i++)
                        {
                            if(Math.abs(pointStart.getX()-listaNoduri.elementAt(i).getCoordX())<NodDiam
                                    &&Math.abs(pointStart.getY()-listaNoduri.elementAt(i).getCoordY())<NodDiam)
                            {
                                validS=true;
                                nodeS=listaNoduri.elementAt(i);
                            }

                            if(Math.abs(pointEnd.getX()-listaNoduri.elementAt(i).getCoordX())<NodDiam
                                    &&Math.abs(pointEnd.getY()-listaNoduri.elementAt(i).getCoordY())<NodDiam)
                            {
                                validE=true;
                                nodeE=listaNoduri.elementAt(i);
                            }
                        }
                        boolean valid=false;
                        if(Math.abs(pointEnd.getX()-pointStart.getX())>=NodDiam
                                &&Math.abs(pointEnd.getY()-pointStart.getY())>=NodDiam)
                        {
                            valid=true;
                        }
                        if(validS==true&&validE==true&&valid==true)
                        {
                            final JFrame frame = new JFrame();
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            String s = JOptionPane.showInputDialog("Introduceti costul muchiei ("+nodeS.getNumber()+","+nodeE.getNumber()+"): ");

                            int cost=Integer.parseInt(s);
                            listeCosturi.get(nodeS.getNumber()-1).set(nodeE.getNumber()-1,cost);
                            listeCosturi.get(nodeE.getNumber()-1).set(nodeS.getNumber()-1,cost);

                            listeAdiacenta.get(nodeS.getNumber()-1).add(nodeE.getNumber());
                            listeAdiacenta.get(nodeE.getNumber()-1).add(nodeS.getNumber());

                            Muchie muchie = new Muchie(nodeS, nodeE,cost);
                            listaMuchii.add(muchie);
                        }
                    }

                    pointStart = null;
                    isDragging = false;

                }
                else
                {
                    move = false;
                }

                for(int i=0;i<listeCosturi.size();i++)
                {
                    pw.print(listeCosturi.get(i));
                    pw.print('\n');
                }

                pw.close();

            }
        });

        addMouseMotionListener(new MouseMotionAdapter()
        {
            public void mouseDragged(MouseEvent e)
            {
                if (!move)
                {
                    pointEnd = e.getPoint();
                }
                else
                {
                    boolean position=true;
                    for(int i=0;i<listaNoduri.size()&&position==true;i++)
                    {
                        double dist=Math.sqrt(Math.pow(listaNoduri.elementAt(i).getCoordX()-e.getX(), 2)+
                                Math.pow(listaNoduri.elementAt(i).getCoordY()-e.getY(), 2));
                        if(dist<2*NodDiam)
                        {
                            position=false;
                        }
                    }
                    if(position==true)
                    {
                        for(int i=0;i<listaNoduri.size();i++)
                        {
                            if(listaNoduri.elementAt(i).getCoordX()==n.getCoordX()
                                    &&listaNoduri.elementAt(i).getCoordY()==n.getCoordY())
                            {
                                listaNoduri.elementAt(i).setCoordX(e.getX());
                                listaNoduri.elementAt(i).setCoordY(e.getY());
                            }
                        }
                    }
                }
                pointEnd = e.getPoint();
                isDragging = true;
                repaint();
            }

        });

        this.add(b1);
        this.setLayout(null);
        b1.setBounds(510, 545, 180, 40);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                repaint();
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        if (listeAdiacenta.size() == 0) {
                            JOptionPane.showMessageDialog(null, "In graful dat nu exista noduri!", "Message",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            Vector<Vector<Integer>> listeAdiacentaNoi = Prim.ProgramPrim(listeAdiacenta.size(), listeAdiacenta, listeCosturi);
                            Vector<Muchie> listaMuchiiN=new Vector<Muchie>();
                            for(int i=0;i<listeAdiacentaNoi.size();i++)
                            {
                                for(int j=0;j<listeAdiacentaNoi.get(i).size();j++)
                                {
                                    Node nodeS=listaNoduri.get(i);
                                    Node nodeE=listaNoduri.get(listeAdiacentaNoi.get(i).get(j)-1);

                                    boolean ok=true;
                                    for(int k=0;k<listaMuchii.size();k++)
                                    {
                                        if(listaMuchii.get(k).getNodeStart()==nodeE&&listaMuchii.get(k).getNodeEnd()==nodeS)
                                        {
                                            ok=false;
                                        }

                                    }
                                    if(ok==true)
                                    {
                                        Muchie muchie = new Muchie(nodeS, nodeE);
                                        listaMuchiiN.add(muchie);
                                    }
                                }
                            }
                            Graf g = new Graf(NodDiam,listaNoduri, listaMuchiiN);
                            frame1.add(g);
                            frame1.setSize(700, 700);
                            frame1.setLocation(800, 100);
                            frame1.setVisible(true);
                        }
                    }
                });
            }
        });

        this.add(b2);
        this.setLayout(null);
        b2.setBounds(510, 585, 180, 40);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                repaint();
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        if (listeAdiacenta.size() == 0) {
                            JOptionPane.showMessageDialog(null, "In graful dat nu exista noduri!", "Message",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            Vector<Muchie> listaMuchiiN = Kruskal.ProgramKruskal(listeAdiacenta, listaMuchii);

                            Graf g = new Graf(NodDiam, listaNoduri, listaMuchiiN);
                            frame2.add(g);
                            frame2.setSize(700, 700);
                            frame2.setLocation(800, 100);
                            frame2.setVisible(true);
                        }
                    }
                });
            }
        });

        this.add(b3);
        this.setLayout(null);
        b3.setBounds(510, 625, 180, 40);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                repaint();
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        if (listeAdiacenta.size() == 0) {
                            JOptionPane.showMessageDialog(null, "In graful dat nu exista noduri!", "Message",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            Vector<Muchie> listaMuchiiN=Boruvka.ProgramBoruvka( listeAdiacenta, listeCosturi,listaMuchii);

                            Graf g = new Graf(NodDiam, listaNoduri, listaMuchiiN);
                            frame3.add(g);
                            frame3.setSize(700, 700);
                            frame3.setLocation(800, 100);
                            frame3.setVisible(true);
                        }
                    }
                });
            }
        });
    }

    private void addNode(int x, int y)
    {
        Node node = new Node(x, y, nodeNr);
        listaNoduri.add(node);
        nodeNr++;
        repaint();
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawString("Draw your Graph!", 10, 20);
        for (Muchie m : listaMuchii)
        {
            m.drawMuchie(g);
        }

        if (pointStart != null)
        {
            g.setColor(Color.RED);
            g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);

            repaint();
        }

        for(int i=0; i<listaNoduri.size(); i++)
        {
            listaNoduri.elementAt(i).drawNode(g, NodDiam,1);
        }
    }

}

