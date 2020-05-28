package pacman;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class EvilPacman extends Pacman implements Runnable{

    private Scene scene;
    Random random = new Random();
    private Pacman pacman;
    public int direction = 1; // Assuming, my evilpacman is facing right. Right is 1 and left would be -1
    Board board;

    public EvilPacman(int xAxis, int yAxis, String imagePath, boolean isAlive, Scene scene, Pacman pacman, Board board) {
        super(xAxis, yAxis, imagePath, isAlive, board);
        setSize(32, 32);
        this.board = board;
        this.scene = scene;
        this.pacman = pacman;
    }

    /*
      I will make the game end???
      Let's say restart the scene.
     */
    public void kill()
    {
        Rectangle pacmanRect = new Rectangle(pacman.getX(),pacman.getY(),25,25);
        Rectangle evilRect = new Rectangle(this.getX(),this.getY(),20,20);
        //System.out.println("Evil's X : "+getX());
        //System.out.println("Evil's Y: "+getY());
       // System.out.println("GOOD's X : "+pacman.getX());
       // System.out.println("GOOD's Y: "+pacman.getY());
        if(pacmanRect.contains(evilRect))
        {
            JOptionPane.showMessageDialog(null,"You died");
            System.out.println("I died");
            System.exit(0);
        }

    }
    
    public void changeDirection(double chance) {
    	if (Math.random() < chance) {
    		if (Math.random() < 0.5) {
    			if (pacman.getY() > getY()) {
    				setNextDir(Direction.DOWN);
    			}
    			else if (pacman.getY() < getY()) {
    				setNextDir(Direction.UP);
    			}
    			else if (pacman.getX() > getX()) {
    				setNextDir(Direction.RIGHT);
    			}
    			else if (pacman.getX() < getX()) {
    				setNextDir(Direction.LEFT);
    			}
    		}
    		else {
    			if (pacman.getX() > getX()) {
    				setNextDir(Direction.RIGHT);
    			}
    			else if (pacman.getX() < getX()) {
    				setNextDir(Direction.LEFT);
    			}
    			else if (pacman.getY() > getY()) {
    				setNextDir(Direction.DOWN);
    			}
    			else if (pacman.getY() < getY()) {
    				setNextDir(Direction.UP);
    			}
    			
    		}
    	}
    }

    public void update() {
    	changeDirection(0.01);
    	if (board.isTile(getX(), getY(), getdx(), getdy())) {
    		setdx(0);
    		setdy(0);
    		changeDirection(1);
    	}
    	if (getNextDir() == Direction.DOWN && !board.isTile(getX(), getY(), 0, 1)) {
			setdx(0);
			setdy(1);
			setLocation(getX(), getY() + getdy());
		}
		else if (getNextDir() == Direction.UP && !board.isTile(getX(), getY(), 0, -1)) {
			setdx(0);
			setdy(-1);
			setLocation(getX(), getY() + getdy());
		}
		else if (getNextDir() == Direction.RIGHT && !board.isTile(getX(), getY(), 1, 0)) {
			setdx(1);
			setdy(0);
			setLocation(getX() + getdx(), getY());
		}
		else if (getNextDir() == Direction.LEFT && !board.isTile(getX() - 1, getY(), -1, 0)) {
			setdx(-1);
			setdy(0);
			setLocation(getX() + getdx(), getY());
		}
		else if (getdy() > 0 && !board.isTile(getX(), getY() + 1)) {
			setLocation(getX(), getY() + getdy());
		}
		else if (getdy() < 0 && !board.isTile(getX(), getY() - 1)) {
			setLocation(getX(), getY() + getdy());
		}
		else if (getdx() > 0 && !board.isTile(getX() + 1, getY())) {
			setLocation(getX() + getdx(), getY());
		}
		else if (getdx() < 0 && !board.isTile(getX() - 1, getY())) {
			setLocation(getX() + getdx(), getY());
		}
    	
    	if(getImagePath().equals("images//ghostblue1.png"))
    		setImagePath("images//ghostblue2.png");
    	if(getImagePath().equals("images//ghostpink1.png"))
    		setImagePath("images//ghostpink2.png");
    	if(getImagePath().equals("images//ghostred1.png"))
    		setImagePath("images//ghostred2.png");
    	if(getImagePath().equals("images//ghostyellow1.png"))
    		setImagePath("images//ghostyellow2.png");
    	if(getImagePath().equals("images//ghostblue2.png"))
    		setImagePath("images//ghostblue1.png");
    	if(getImagePath().equals("images//ghostpink2.png"))
    		setImagePath("images//ghostpink1.png");
    	if(getImagePath().equals("images//ghostred1.png"))
    		setImagePath("images//ghostred1.png");
    	if(getImagePath().equals("images//ghostyellow2.png"))
    		setImagePath("images//ghostyellow1.png");
    		
    }

    @Override
    public void run() {
        while (true)
        {
            try{
                Thread.sleep(random.nextInt(100));
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Thread interrupted");
                System.exit(1);
            }

            if(getX() <= new Random().nextInt(50))
            {
                direction = 1;
                setImagePath("images//evil_pacman_right.png");
            }
            else if(getX() >= 450)
            {
                direction = - 1;
                setImagePath("images//evil_pacman_left.png");
            }

            kill();
            setX(getX() + 3 * direction);
            scene.repaint();


        }
    }
}