import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Node
{
    private int coordX;
    private int coordY;
    private int number;

    public Node predecesor = null;

    public Node()
    {
    }

    public Node(int coordX, int coordY, int number)
    {
        this.coordX = coordX;
        this.coordY = coordY;
        this.number = number;
    }

    public Node(int coordX, int coordY)
    {
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public int getCoordX() {
        return coordX;
    }
    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }
    public int getCoordY() {
        return coordY;
    }
    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public void drawNode(Graphics g, int node_diam,int optiune) {
        if (optiune == 1) {
            g.setColor(Color.BLACK);
            g.fillOval(this.coordX, this.coordY, node_diam, node_diam);
        } else {
            g.setColor(Color.CYAN);
            g.fillOval(this.coordX, this.coordY, node_diam, node_diam);
        }
        g.setFont(new Font("TimesRoman", 1, 15));
        g.setColor(Color.WHITE);
        g.drawOval(coordX, coordY, node_diam, node_diam);
        if (this.number < 10) {
            g.drawString(Integer.valueOf(this.number).toString(), this.coordX + 13, this.coordY + 20);
        } else {
            g.drawString(Integer.valueOf(this.number).toString(), this.coordX + 8, this.coordY + 20);
        }
    }
    void setnewtarget(int x, int y)
    {
        coordX = x;
        coordY = y;
    }
}