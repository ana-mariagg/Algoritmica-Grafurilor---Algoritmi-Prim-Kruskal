import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


public class Muchie
{
    private Node NodeStart;
    private Node NodeEnd;
    private int Cost;

    public Muchie()
    {
    }

    public Muchie(Node NodeStart, Node NodeEnd)
    {
        this.NodeStart = NodeStart;
        this.NodeEnd =  NodeEnd;
    }

    public Muchie(Node NodeStart, Node NodeEnd,int Cost)
    {
        this.NodeStart = NodeStart;
        this.NodeEnd =  NodeEnd;
        this.Cost=Cost;
    }


    public Node getNodeStart() {
        return NodeStart;
    }

    public Node getNodeEnd() {
        return NodeEnd;
    }

    public int getCost() {
        return Cost;
    }

    public void setStart(int coordX,int coordY) {
        this.NodeStart.setCoordX(coordX);
        this.NodeStart.setCoordY(coordY);
    }

    public void setEnd(int coordX,int coordY) {
        this.NodeEnd.setCoordX(coordX);
        this.NodeEnd.setCoordY(coordY);
    }

    public void setCost(int cost) {
        this.Cost=cost;
    }
    public void drawMuchie(Graphics g)
    {
        if (NodeStart != null)
        {
            g.setColor(Color.BLACK);
            g.drawLine(NodeStart.getCoordX()+Neorientat.NodDiam/2, NodeStart.getCoordY()+Neorientat.NodDiam/2, NodeEnd.getCoordX()+Neorientat.NodDiam/2, NodeEnd.getCoordY()+Neorientat.NodDiam/2);
        }
    }
}