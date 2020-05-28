package pacman;
import javax.swing.*;
import java.awt.*;

public class Pacman extends JComponent {

    private String imagePath;
    private boolean isAlive;
    private Direction nextDirection;
	private int dx;
	private int dy;
	private Board board;

    public Pacman(int x, int y, String imagePath, boolean isAlive, Board board)
    {
        setSize(32, 32);
        setLocation(x, y);
        setImagePath(imagePath);
        this.isAlive = isAlive;
        this.board = board;
    }

    public void setX(int x)
    {
        // My player to remain on the screen while he is alive
        if(isAlive)
        {
            // I will be able to move in a certain bound
            // I want a window of 500 pixels
            if(this.getX() >= 470)
            {
            	this.setLocation(470, getY());
            }
            else if(this.getX() <= 0)
            {
            	this.setLocation(0, getY());
            }
            else
            	this.setLocation(getX(), getY()); // This only happens when my player is in the window

        }
        else
        {
        	this.setLocation(getX(), getY());
        }
    }

    public void setY(int y)
    {
        // My player to remain on the screen while he is alive
        if(isAlive)
        {
            // I will be able to move in a certain bound
            // I want a window of 500 pixels
            if(this.getY() >= 300)
            {
                this.setLocation(getX(), 300);
            }
            else if(this.getY() <= 0)
            {
            	this.setLocation(getX(), 0);
            }
            else
            	this.setLocation(getX(), getY()); // This only happens when my player is in the window

        }
        else
        {
        	this.setLocation(getX(), getY());
        }

    }

    public void setImagePath(String imagePath)
    {
        if(imagePath == null)
        {
            JOptionPane.showMessageDialog(null, "Image is null");
            System.exit(1);
        }
        else
            this.imagePath = imagePath;
    }
    
    public void update() {
    	board.checkForDots(this);
    	if (board.isTile(getX(), getY(), dx, dy)) {
    		dx = 0;
    		dy = 0;
    	}
		if (nextDirection == Direction.DOWN && !board.isTile(getX(), getY(), 0, 1)) {
			dx = 0;
			dy = 2;
			setLocation(getX(), getY() + dy);
		}
		else if (nextDirection == Direction.UP && !board.isTile(getX(), getY(), 0, -1)) {
			dx = 0;
			dy = -2;
			setLocation(getX(), getY() + dy);
		}
		else if (nextDirection == Direction.RIGHT && !board.isTile(getX(), getY(), 1, 0)) {
			dx = 2;
			dy = 0;
			setLocation(getX() + dx, getY());
		}
		else if (nextDirection == Direction.LEFT && !board.isTile(getX() - 1, getY(), -1, 0)) {
			dx = -2;
			dy = 0;
			setLocation(getX() + dx, getY());
		}
		else if (dy > 0 && !board.isTile(getX(), getY() + 1)) {
			setLocation(getX(), getY() + dy);
		}
		else if (dy < 0 && !board.isTile(getX(), getY() - 1)) {
			setLocation(getX(), getY() + dy);
		}
		else if (dx > 0 && !board.isTile(getX() + 1, getY())) {
			setLocation(getX() + dx, getY());
		}
		else if (dx < 0 && !board.isTile(getX() - 1, getY())) {
			setLocation(getX() + dx, getY());
		}
	}

    public void setIsAlive(boolean isAlive)
    {
        this.isAlive = isAlive;
    }

   

    public String getImagePath() {
        return imagePath;
    }

    public boolean isAlive() {
        return isAlive;
    }

	public int getdx() {
		return dx;
	}
	
	public int getdy() {
		return dy;
	}
	
	public void setdx(int newdx) {
		dx = newdx;
	}
	
	public void setdy(int newdy) {
		dy = newdy;
	}
	
	public void setNextDir(Direction dir) {
		nextDirection = dir;
	}
	
	public Direction getNextDir() {
		return nextDirection;
	}
	

    // This function will allow me to draw the pacman image!
    public void drawPacman(Graphics graphics)
    {
        ImageIcon img = new ImageIcon(imagePath);
        graphics.drawImage(img.getImage(),getX(),getY(),null);
    }
    
}