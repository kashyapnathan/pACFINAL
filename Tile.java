package pacman;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class Tile extends JComponent {

	public Tile(int x, int y) {
		setSize(32, 32);
		setLocation(x, y);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLUE);
		g2.fill(new Rectangle2D.Double(getX(), getY(), 31, 31));
		g2.setColor(Color.WHITE);
		g2.draw(new Rectangle2D.Double(getX(), getY(), 31, 31));
	}
	
	public boolean checkCollision(JComponent other) {
		return ((other.getX() < getX() + getWidth()) && (other.getY() < getY() + getHeight()) && (other.getY() + other.getHeight() > getY()) && (other.getX() + other.getWidth() > getX()));
	}
	
	public boolean checkCollision(int x, int y, int changex, int changey) {
		Tile tile = new Tile(x + changex, y + changey);
		return (checkCollision(tile));
	}
	
}