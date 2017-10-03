import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
	
	private ImageIcon titleImage;
	private int[] snakexlength = new int[750];
	private int[] snakeylength = new int[750];
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	private ImageIcon rightmouth;
	private ImageIcon leftmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	private int lengthofsnake = 3;
	private Timer timer;
	private int delay = 100;
	private int moves = 0;
	private ImageIcon snakeimage;
	private int [] enemyxpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850}; 
	private int [] enemyypos = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
	private ImageIcon enemyimage;
	private Random random = new Random();
	private int xpos = random.nextInt(34);
	private int ypos = random.nextInt(23);
	private int score = 0;
	private int difficulty;
	private int [] scorestore = new int [10];
	private int [] scorestoreeeeeee = new int [10];
	private int scoreitr = 0;
	private String Username;
	private String highscore;
	
	public Gameplay(String usr) throws IOException
	{   
		String inputString = JOptionPane.showInputDialog(null, "Difficulty:\n 1 - Easy \n 2 - Medium \n 3 - Hard \n");
		difficulty = Integer.parseInt(inputString);
		Username = usr + ".txt";
		BufferedReader bufferedReader = new BufferedReader(new FileReader(Username));
		int j = 0;
		while((highscore = bufferedReader.readLine()) != null && j <10) {
			scorestoreeeeeee[j] = Integer.parseInt(highscore);
			System.out.print(scorestoreeeeeee[j]);
			j++;
		}
		bufferedReader.close();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
        
	}
	
	public void paint( Graphics g) 
	{	
		if (moves == 0) {
			
			snakexlength[0] = 100;
			snakexlength[1] = 75;
			snakexlength[2] = 50; 
			snakeylength[0] = 100;
			snakeylength[1] = 100;
			snakeylength[2] = 100;
		}
		
		// title border
		g.setColor(Color.WHITE);
		g.drawRect(24, 10, 851, 55);
		
		// leaderboard
		g.setColor(Color.GRAY);
		g.fillRect(875,10, 300, 642);
		g.setColor(Color.black);
		g.setFont(new Font("arial", Font.PLAIN, 30));
		g.drawString("LeaderBoard ", 950, 40);
		g.setColor(Color.WHITE);
		g.drawRect(876, 11, 299, 55);
		g.drawRect(876, 75, 299, 575);
		// title image
		titleImage = new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		
		// gameplay border
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 851, 577);
		
		// gameplay background
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		//score
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Score: "+score, 780, 30);
		
		// leaderboard data
		Arrays.sort(scorestore);
		for (int i = 0; i <scoreitr; i++) {
			if ( i < 10) {
			g.setFont(new Font("arial", Font.PLAIN, 40));
			g.drawString((i+1)+"	:	" + scorestore[9 - i] , 980, (150 + (i* 60)));
			}
		}
				
		// length of snake disp
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Length: "+lengthofsnake, 780, 50);
		
		//snake
		rightmouth = new ImageIcon("rightmouth.png");
		rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		
		for(int a = 0; a < lengthofsnake ; a++) {
			
			if(a == 0 && right) {
				rightmouth = new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			
			if(a == 0 && left) {
				leftmouth = new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			
			if(a == 0 && up) {
				upmouth = new ImageIcon("upmouth.png");
				upmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			
			if(a == 0 && down) {
				downmouth = new ImageIcon("downmouth.png");
				downmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			
			if (a != 0) {
				snakeimage = new ImageIcon("snakeimage.png");
				snakeimage.paintIcon(this, g, snakexlength[a], snakeylength[a]);
	
			}
		}
		
		enemyimage = new ImageIcon("enemy.png");
		if ( difficulty == 1 ) {
			if((enemyxpos[xpos] == snakexlength[0]) && (enemyypos[ypos] == snakeylength[0])) {
			
				score++;
				lengthofsnake++;
				xpos = random.nextInt(34);
				ypos = random.nextInt(23);
				}
		}
		else if ( difficulty == 2) {
			if((enemyxpos[xpos] == snakexlength[0]) && (enemyypos[ypos] == snakeylength[0])) {
				
				score++;
				lengthofsnake++;
				xpos = random.nextInt(34);
				ypos = random.nextInt(23);
				}
			else if((enemyxpos[xpos] == snakexlength[0])  && (enemyypos[ypos] - snakeylength[0]) == 25) {
				
				score++;
				lengthofsnake++;
				xpos = random.nextInt(34);
				ypos = random.nextInt(23);
				}
			else if((enemyxpos[xpos] - snakexlength[0]) == 25 && (enemyypos[ypos] == snakeylength[0])) {
				
				score++;
				lengthofsnake++;
				xpos = random.nextInt(34);
				ypos = random.nextInt(23);
				}
		}
		
		else if ( difficulty == 3) {
			if((enemyxpos[xpos] == snakexlength[0]) && (enemyypos[ypos] == snakeylength[0])) {
					
				score++;
				lengthofsnake++;
				xpos = random.nextInt(34);
				ypos = random.nextInt(23);
					}
			else if((enemyxpos[xpos] == snakexlength[0])  && (enemyypos[ypos] - snakeylength[0]) == 50) {
					
				score++;
				lengthofsnake++;
				xpos = random.nextInt(34);
				ypos = random.nextInt(23);
				}
			else if((enemyxpos[xpos] - snakexlength[0]) == 50 && (enemyypos[ypos] == snakeylength[0])) {
					
				score++;
				lengthofsnake++;
				xpos = random.nextInt(34);
				ypos = random.nextInt(23);
				}
		}
		
		enemyimage.paintIcon(this, g, enemyxpos[xpos], enemyypos[ypos]);
		
		for (int b = 1 ; b < lengthofsnake; b++) {
			if ((snakexlength[b] == snakexlength[0]) && (snakeylength[b] == snakeylength[0])) {
				right = false;
				left = false;
				up = false;
				down = false;
				scorestore[scoreitr] = score;
				g.setColor(Color.white);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("Game OVer!!!", 300, 300);
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("Press Space to Restart or any key to continue", 250, 340);
				scoreitr++;
				
			}
		}
		
		g.dispose();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if (right == true) {
			
			for (int r = lengthofsnake - 1; r>= 0; r--) {
				snakeylength[r+1] = snakeylength[r];
			}
			
			for (int r = lengthofsnake; r>= 0; r--) {
				
				if (r == 0 ) {
					snakexlength[r] = snakexlength[r] + (25 * difficulty);
				}
				
				else if (r != 0) {
					snakexlength[r] = snakexlength[r-1];
				}
				
				if (snakexlength[r] > 850) {
					snakexlength[r] = 25;
				}
				
			}
			
			repaint();
		}
		
		if (left == true) {
			
			for (int r = lengthofsnake - 1; r>= 0; r--) {
				snakeylength[r+1] = snakeylength[r];
			}
			
			for (int r = lengthofsnake; r>= 0; r--) {
				
				if (r == 0 ) {
					snakexlength[r] = snakexlength[r] - (25 * difficulty);
				}
				
				else if (r != 0) {
					snakexlength[r] = snakexlength[r-1];
				}
				
				if (snakexlength[r] < 25) {
					snakexlength[r] = 850;
				}
				
			}
			
			repaint();
			
		}

		if (up == true) {
			
			for (int r = lengthofsnake - 1; r>= 0; r--) {
				snakexlength[r+1] = snakexlength[r];
			}
			
			for (int r = lengthofsnake; r>= 0; r--) {
				
				if (r == 0 ) {
					snakeylength[r] = snakeylength[r] - (25 * difficulty);
				}
				
				else if (r != 0) {
					snakeylength[r] = snakeylength[r-1];
				}
				
				if (snakeylength[r] < 75) {
					snakeylength[r] = 625;
				}
				
			}
			
			repaint();
	
		}
		
		if (down == true) {
			
			for (int r = lengthofsnake - 1; r>= 0; r--) {
				snakexlength[r+1] = snakexlength[r];
			}
			
			for (int r = lengthofsnake; r>= 0; r--) {
				
				if (r == 0 ) {
					snakeylength[r] = snakeylength[r] + (25 * difficulty);
				}
				
				else if (r != 0) {
					snakeylength[r] = snakeylength[r-1];
				}
				
				if (snakeylength[r] > 625) {
					snakeylength[r] = 75;
				}
				
			}
			
			repaint();
			
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			moves = 0;
			score = 0;
			lengthofsnake = 3;
			repaint();
			
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
		    moves++;
		    right = true;
		    if (left != true) {
		    	right = true;
		    }
		    else {
		    	right = false;
		    	left = true;
		    }
		    up = false;
			down = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
		    moves++;
		    left = true;
		    if (right != true) {
		    	left = true;
		    }
		    else {
		    	left = false;
		    	right = true;
		    }
		    up = false;
			down = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
		    moves++;
		    up = true;
		    if (down != true) {
		    	up = true;
		    }
		    else {
		    	up = false;
		    	down = true;
		    }
		    left = false;
			right = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
		    moves++;
		    down = true;
		    if (up != true) {
		    	down = true;
		    }
		    else {
		    	down = false;
		    	up = true;
		    }
		    left = false;
			right = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	
	}
}
