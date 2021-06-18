package game.play;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import game.giaodien.Defeat;
import game.giaodien.StartGame;
import game.giaodien.Victory;
import game.objects.Bullet;
import game.objects.Character;
import game.objects.Enemy;
import game.objects.OpenDoor;
import game.objects.Path;
import game.objects.Player;
import game.objects.River;
import game.objects.Rock;
import game.objects.Thung;
import game.objects.Tree;

public class GAME extends JFrame implements  Runnable, KeyListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5713217835493723131L;
	private Thread loop;
	private JPanel jpanel;
	private Player player;
	private ArrayList<Enemy> enemies;
	private BufferedImage backbuffer;
	private Graphics2D g2d;
	private ArrayList<Rock> rocks;
	private ArrayList<Tree> trees;
	private ArrayList<Path> paths;
	private ArrayList<River> river;
	private ArrayList<Thung> thung;
	private Bullet[] bullets;
	private int currentBullet = 0;
	private OpenDoor door;
	private Defeat  loss;
	private Victory win;
	private AffineTransform identity = new AffineTransform();
	
	public GAME(int x) {
       
        setSize(810,790);
        addKeyListener(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Nhóm 13");
        setLocationRelativeTo(null);
		setVisible(true);

        jpanel = new JPanel() {
		/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g2d.setTransform(identity);
			g2d.setPaint(Color.BLACK);
			g2d.fillRect(0, 0, getSize().width, getSize().height );
			g2d.setPaint(Color.RED);
			g2d.drawString("HP: " + player.getHp(), 5, 15);
			g2d.drawString("Damage: " + player.getDamage(), 5, 30);
			if(player.getLevel()==1||player.getLevel()==2||player.getLevel()==3) {
			g2d.drawString("Level: " + player.getLevel(), 100, 15);}
			else g2d.drawString("Level " + player.getLevel()/10, 100, 15);

			drawMap();
			drawPlayer();
			drawEnemy();
			drawBullet();
			drawVictory();
			drawDefeat();
			g.drawImage(backbuffer, 0, 0, this);
			
		}
		};
		setContentPane(jpanel);
		initGamePlay(x);
	}
	private void initGamePlay(int x) {
		setPreferredSize(new Dimension(800, 760));
		setUpLevel1(x);
	}
	
	private void setUpLevel1(int a) {
		backbuffer = new BufferedImage(800, 760, BufferedImage.TYPE_INT_RGB);
		g2d = backbuffer.createGraphics();
		
		win = new Victory(0,0);
		win.setVisible(false);
		loss =new Defeat(0,0);
		loss.setVisible(false);
		
		player = new Player(40, 40, Character.DOWN);
		player.setLevel(a);
		player.setHp(15);
		player.setDamage(5);
		
		enemies = new ArrayList<Enemy>();
		rocks = new ArrayList<Rock>();
		trees = new ArrayList<Tree>();
		paths = new ArrayList<Path>();
		river = new ArrayList<River>();
		thung =new ArrayList<Thung>();
		door = new OpenDoor(600,680);
		bullets = new Bullet[50];
		for (int i = 0; i < 50; i++) {
			bullets[i] = new Bullet();
		}
		
		if(a==1) {	
			// level 1 cua muc de
			enemies.add(new Enemy(200, 160, Character.RIGHT));
			enemies.add(new Enemy(40, 640, Character.UP));
			enemies.add(new Enemy(680, 200, Character.RIGHT));
			enemies.add(new Enemy(560, 600, Character.UP));
			for(Enemy e : enemies) {
				e.setLevel(1);
				e.setHp(10);
				e.setDamage(5);
			   }	
			File map1= new File("src/map/map1.txt");
			initMap(map1.getAbsolutePath());
			}
		else if(a==10) {	
			// level 1 cua muc kho
				enemies.add(new Enemy(680, 40, Character.RIGHT));
				enemies.add(new Enemy(640, 560, Character.UP));
				enemies.add(new Enemy(720, 640, Character.RIGHT));
				enemies.add(new Enemy(680, 440, Character.UP));
				enemies.add(new Enemy(80, 680, Character.RIGHT));
				enemies.add(new Enemy(320, 600, Character.UP));
				enemies.add(new Enemy(120, 160, Character.RIGHT));
				enemies.add(new Enemy(400, 360, Character.UP));
				enemies.add(new Enemy(160, 520, Character.UP));
			for(Enemy e : enemies) {
				e.setLevel(1);
				e.setHp(15);
				e.setDamage(15);
			   }
			File map10= new File("src/map/map10.txt");
			initMap(map10.getAbsolutePath());
		}
		for (Enemy e : enemies) {
			EnemyBehaviour ai = new EnemyBehaviour(e, this);
			ai.start();
		}
		
	}

	private void setUpLevel2(int a) {
		
		player = new Player(600, 680, Character.UP);
		player.setLevel(a);
		player.setHp(20);
		player.setDamage(5);
		
		enemies = new ArrayList<Enemy>();
		rocks = new ArrayList<Rock>();
		trees = new ArrayList<Tree>();
		paths = new ArrayList<Path>();
		river = new ArrayList<River>();
		thung = new ArrayList<Thung>();
		bullets = new Bullet[50];
		for (int i = 0; i < 50; i++) {
			bullets[i] = new Bullet();
		}
		
		if(a==2) {

		// level 2 cua muc de
		enemies.add(new Enemy(720, 640, Character.DOWN));
		enemies.add(new Enemy(80, 640, Character.DOWN));
		enemies.add(new Enemy(360, 640, Character.DOWN));
		enemies.add(new Enemy(600, 160, Character.RIGHT));
		enemies.add(new Enemy(80, 40, Character.LEFT));
		enemies.add(new Enemy(240, 320, Character.LEFT));
		enemies.add(new Enemy(720, 80, Character.LEFT));
		
		for(Enemy e : enemies) {
			e.setLevel(2);
			e.setHp(20);
			e.setDamage(10);
		}
		door = new OpenDoor(120,120);
		File map2= new File("src/map/map2.txt");
		initMap(map2.getAbsolutePath());
		}
		else if(a==20) {
			// lelvel 2 cua muc kho
			enemies.add(new Enemy(720, 600, Character.DOWN));
			enemies.add(new Enemy(600, 40, Character.RIGHT));
			enemies.add(new Enemy(120, 680, Character.DOWN));
			enemies.add(new Enemy(520, 360, Character.RIGHT));
			enemies.add(new Enemy(400, 240, Character.LEFT));
			enemies.add(new Enemy(400, 640, Character.UP));
			enemies.add(new Enemy(40, 680, Character.UP));
			enemies.add(new Enemy(120, 480, Character.DOWN));
			enemies.add(new Enemy(200, 80, Character.LEFT));
			enemies.add(new Enemy(40, 40, Character.RIGHT));
			enemies.add(new Enemy(240, 280, Character.RIGHT));
			enemies.add(new Enemy(40, 40, Character.RIGHT));
			for(Enemy e : enemies) {
				e.setLevel(2);
				e.setHp(25);
				e.setDamage(20);
			}
			door = new OpenDoor(680,680);
			File map20= new File("src/map/map20.txt");
			initMap(map20.getAbsolutePath());
		}
		for (Enemy e : enemies) {
			EnemyBehaviour ai = new EnemyBehaviour(e, this);
			ai.start();
		}
	}
	
	private void setUpLevel3(int a) {
		player = new Player(0, 360, Character.UP);
		player.setLevel(a);
		player.setHp(30);
		player.setDamage(20);
		door.setVisible(false);
		
		enemies = new ArrayList<Enemy>();		
		rocks = new ArrayList<Rock>();
		trees = new ArrayList<Tree>();
		paths = new ArrayList<Path>();
		river = new ArrayList<River>();
		thung = new ArrayList<Thung>();
		bullets = new Bullet[50];
		for (int i = 0; i < 50; i++) {
			bullets[i] = new Bullet();
		}
		
		if(a==3) {
			// level 3 cua muc de
			File map3= new File("src/map/map3.txt");
			initMap(map3.getAbsolutePath());
			enemies.add(new Enemy(160, 200, Character.DOWN));
			enemies.add(new Enemy(600, 200, Character.DOWN));
			enemies.add(new Enemy(160, 520, Character.UP));
			enemies.add(new Enemy(600, 520, Character.UP));
			enemies.add(new Enemy(160, 200, Character.UP));
			enemies.add(new Enemy(600, 200, Character.RIGHT));
			enemies.add(new Enemy(160, 520, Character.DOWN));
			enemies.add(new Enemy(600, 520, Character.LEFT));
			enemies.add(new Enemy(600, 360, Character.LEFT));
			for(Enemy e : enemies) {
				e.setLevel(3);
				e.setHp(200);
				e.setDamage(30);
				e.loadImage("images/dragon1.png");
				e.getImageDimensions();
			   }
			}
			else if(a==30) {
				// level 3 cua muc kho
				File map30= new File("src/map/map30.txt");
				initMap(map30.getAbsolutePath());
				enemies.add(new Enemy(760, 360, Character.DOWN));
				enemies.add(new Enemy(760, 520, Character.DOWN));
				enemies.add(new Enemy(720, 520, Character.DOWN));
				enemies.add(new Enemy(0, 560, Character.LEFT));
				enemies.add(new Enemy(40, 160, Character.DOWN));
				enemies.add(new Enemy(760, 200, Character.DOWN));
				enemies.add(new Enemy(40, 560, Character.LEFT));
				enemies.add(new Enemy(0, 160, Character.DOWN));
				enemies.add(new Enemy(720, 200, Character.DOWN));
				enemies.add(new Enemy(200, 40, Character.LEFT));
				enemies.add(new Enemy(560, 40, Character.RIGHT));
				enemies.add(new Enemy(200, 680, Character.LEFT));
				enemies.add(new Enemy(560, 680, Character.RIGHT));
				enemies.add(new Enemy(520, 240, Character.DOWN));
				for(Enemy e : enemies) {
					e.setLevel(3);
					e.setHp(200);
					e.setDamage(30);
					e.loadImage("images/dragon1.png");
					e.getImageDimensions();
				   }
			}
			
		for (Enemy e : enemies) 
		{
			EnemyBehaviour ai = new EnemyBehaviour(e, this);
			ai.start();
		}
		
	}

	public void initMap(String url){
				
		int j = 0;
	    try {
	        File myObj = new File(url);
	        // doc du lieu map tu file text
	        Scanner myReader = new Scanner(myObj);
	        while (myReader.hasNextLine()) {
	          String data = myReader.nextLine();
	          String[] test = data.split(" ");
            // gan vi tri cac doi tuong tren ban do
	          for (int i =0; i< test.length; i++) {
	        	  if (test[i].contentEquals("0")) {
					paths.add(new Path(i*40, j*40));
	        	  }
	        	  if(test[i].contentEquals("2")) {
	        		  river.add(new River(i*40,j*40));
	        	  }
	        	  if(test[i].contentEquals("3")) {
	        		  trees.add(new Tree(i*40,j*40));
	        		  paths.add(new Path(i*40,j*40));
	        	  }
	        	  if(test[i].contentEquals("4")) {
	        		  rocks.add(new Rock(i*40,j*40));
	        		  paths.add(new Path(i*40,j*40));
	        	  }
	        	  if(test[i].contentEquals("1")) {
	        		  thung.add(new Thung(i*40,j*40,2));
	        		  paths.add(new Path(i*40,j*40));
	        	  }
	        	
	          }
	          j++;
	        }
	        myReader.close();
	      } catch (FileNotFoundException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }

	}
// ve hinh anh cac nhan vat
	public void drawEnemy() {
		for (Enemy e : enemies) {
			if (e.isVisible()) {
				AffineTransform trans = new AffineTransform();
				trans.setTransform(identity);
				switch(e.getDirection()) {
				case Character.DOWN:
					trans.translate(e.getX()+40, e.getY()+40);
					trans.rotate(Math.toRadians(180));
					break;
				case Character.UP:
					trans.translate(e.getX(), e.getY());
					break;
				case Character.LEFT:
					trans.translate(e.getX(), e.getY() + 40);
					trans.rotate(Math.toRadians(270));
					break;
				case Character.RIGHT:
					trans.translate(e.getX() + 40, e.getY());
					trans.rotate(Math.toRadians(90));
					break;
				}
				g2d.drawImage(e.getImage(), trans, this);
			}
		}
	}
	
	public void drawPlayer() {
		if (player.isVisible()) {
			AffineTransform trans = new AffineTransform();
			trans.setTransform(identity);
			switch(player.getDirection()) {
				case Character.DOWN:
					trans.translate(player.getX()+40, player.getY()+40);
					trans.rotate(Math.toRadians(180));
					break;
				case Character.UP:
					trans.translate(player.getX(), player.getY());
					break;
				case Character.LEFT:
					trans.translate(player.getX(), player.getY() + 40);
					trans.rotate(Math.toRadians(270));
					break;
				case Character.RIGHT:
					trans.translate(player.getX() + 40, player.getY());
					trans.rotate(Math.toRadians(90));
					break;
			}
			g2d.drawImage(player.getImage(), trans, this);
		}
	}
	public void drawDefeat() {
		if(loss.isVisible()) {
			g2d.drawImage(loss.getImage(), 0, 0, this);
		}
	}
	
	public void drawVictory() {
		if(win.isVisible()) {
			g2d.drawImage(win.getImage(), 0, 0, this);
		}
	}
	
	public void drawBullet() {
		for (Bullet b : bullets) {
			if (b.isVisible()) {
				g2d.drawImage(b.getImage(), b.getX(), b.getY(), this);
			}
		}
	}

	public void drawMap() {
		
		for (Path b : paths) {
			if(b.isVisible()) {
				g2d.drawImage(b.getImage(), b.getX(), b.getY(), this);
			}
		}	
		
		for (Tree t : trees) {
			if(t.isVisible()) {
				g2d.drawImage(t.getImage(), t.getX(), t.getY(), this);
			}
		}
		
		for (Rock r : rocks) {
			if(r.isVisible()) {
				g2d.drawImage(r.getImage(), r.getX(), r.getY(), this);
			}
		}
		
		for (Thung th : thung) {
			if(th.isVisible()) {
				g2d.drawImage(th.getImage(), th.getX(), th.getY(), this);
			}
		}	
	
		if (door.isVisible()) 
			g2d.drawImage(door.getImage(), door.getX(), door.getY(), this);
		
		for (River w : river) {
			if (w.isVisible()) {
				g2d.drawImage(w.getImage(), w.getX(), w.getY(), this);
			}
		}

	}


	// thread start
	public void start() {
		loop = new Thread(this);
		loop.start();
	}

	// thread run
	public void run() {		
		// acquire the current thread
		Thread t = Thread.currentThread();
		// keep going as long as the thread is alive
		while (t == loop) {
			try {
				// update the game loop
				gameUpdate();
				// for frame rate
				Thread.sleep(10);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
		
	}
	
	// thread stop
	public void stop() {
		// kill the game loop thread
		loop = null;
		
	}
	
	public void gameUpdate() {
		checkGameOver();
		updatePlayer();
		updateEnemy();
		updateBullet();
		checkCollisions();
	}
	
	private void checkGameOver() {
		if ( !player.isVisible()) {
             loss.setVisible(true);
             stop();
              }
		 
		else if (door.isVisible()) {
			if(checkDeadEnemy()) {
			if (door.getBounds().contains(player.getX(), player.getY()) ||
					door.getBounds().contains(player.getX() + player.getWidth(), player.getY() + player.getHeight())) {
				if (player.getLevel() == 1) {
					setUpLevel2(2);
				}
				else if (player.getLevel() == 2) {
					setUpLevel3(3);				
				}
				else if (player.getLevel() == 10) {
					setUpLevel2(20);
				}
				else if (player.getLevel() == 20) {
					setUpLevel3(30);
				}
				}
			}	
		}
		if(checkDeadEnemy()) {
			if (player.getLevel() == 3|| player.getLevel() == 30) {
				win.setVisible(true);
                stop();
		        }
			
		}
	}
	
	public boolean checkDeadEnemy() {
		if (enemies.isEmpty()) return true;
		return false;
	}
	
	// update vi tri player 
	public void updatePlayer() {
		if (player.getX() < player.getFlagX()) player.setX(player.getX() + 1);
		if (player.getX() > player.getFlagX()) player.setX(player.getX() - 1);
		if (player.getY() < player.getFlagY()) player.setY(player.getY() + 1);
		if (player.getY() > player.getFlagY()) player.setY(player.getY() - 1);
	}

	// update vi tri enemy 
	public void updateEnemy() {
		for (Enemy e : enemies) {
			if (e.getX() < e.getFlagX()) e.setX(e.getX() + 1);
			if (e.getX() > e.getFlagX()) e.setX(e.getX() - 1);
			if (e.getY() < e.getFlagY()) e.setY(e.getY() + 1);
			if (e.getY() > e.getFlagY()) e.setY(e.getY() - 1);
		}
	}
	
	// update vi tri dan
	public void updateBullet() {
		for (Bullet b : bullets) {
			if (b.isVisible()) {
				b.setX(b.getX() + b.getVelX());
				b.setY(b.getY() + b.getVelY());
				// dan bien mat khi di het ban do
				if (b.getY() < 40 || b.getY() > 720) b.setVisible(false);
			}
		}
	}

	// kiem tra va cham 
	public void checkCollisions() {
		
		// va cham giua player va enemy 
		if(player.isVisible()) {
			for (Enemy e : enemies) {
				if (e.isVisible()) {
					if (e.getBounds().contains(player.getX(), player.getY()) ) {
						 player.setHp(0);
			             player.setVisible(false);
					}
				}
			}
		}
		// va cham cua dan
		for (Bullet b : bullets) {
			if (b.isVisible()) {
				
				// va cham voi cac character 
				for (Enemy e : enemies) {
					if (e.isVisible()) {
						if (e.getBounds().contains(b.getX(), b.getY())) {
							if (b.beLongTo() == b.ENEMY) break;
							else {
								b.setVisible(false);
								if(e.getHp() == 0 + player.getDamage()) e.setVisible(false);
								else e.setHp(e.getHp() - player.getDamage());
							}

						}
					}
				}
				
				if (player.isVisible()) {
					if (player.getBounds().contains(b.getX(), b.getY())) {
						if (b.beLongTo() == b.PLAYER) break;
						else {
							b.setVisible(false);
							if (player.getHp() == 0+ enemies.get(0).getDamage()) {
								player.setHp(0);
								player.setVisible(false);
							}
							else player.setHp(player.getHp() - enemies.get(0).getDamage());
						}
					}
				}	
				
				// va cham voi thung
				for (Thung th : thung) {
					if (th.isVisible()) {
						if (th.getBounds().contains(b.getX(), b.getY()) ) 
						{
							b.setVisible(false);
							if(th.getHp() == 0) {
							th.setVisible(false);}
							else th.setHp(th.getHp()-2);
							continue;
						}
					}
				}
					
				// va cham voi da
				for (Rock w : rocks) {
					if (w.isVisible()) {
						if (w.getBounds().contains(b.getX(), b.getY())) {
							b.setVisible(false);
						}
					}
				}
				
				// va cham voi cay
				for (Tree t : trees) {
					if (t.isVisible()) {
						if (t.getBounds().contains(b.getX(), b.getY())) {
							b.setVisible(false);
						}
					}
				}
				
				// va cham giua 2 dan
				for (Bullet ob : bullets) {
					if (ob.isVisible() && ob.beLongTo() != b.beLongTo()) {
						if (b.getBounds().contains(ob.getX(), ob.getY())) {
							if (b.beLongTo() != ob.beLongTo()) {
								ob.setVisible(false);
								b.setVisible(false);
							}
						}
					}
				}
			}
		}
		
		//loai bo enemy  bi ban chet
		for (int i = 0; i < enemies.size(); i++) {
			if (!enemies.get(i).isVisible()) {
				enemies.remove(i); 
			}
		}
	}

	// Ban dan
	synchronized public void Fire(Character c) {
		if (c.isVisible()) {
			currentBullet++;
			if (currentBullet > 49) currentBullet = 0;
			Bullet b = bullets[currentBullet];
			b.setVisible(true);
			if (c == player) b.setBeLongTo(b.PLAYER);
			else b.setBeLongTo(b.ENEMY);
			
			// dan ban ra cung huong mat cua nhan vat
			switch (c.getDirection()) {
			case Character.UP:
				b.setX(c.getX() + 14);
				b.setY(c.getY() - 6);
				b.setVelX(0);
				b.setVelY(-5);
				break;
			case Character.DOWN:
				b.setX(c.getX() + 14);
				b.setY(c.getY() + 6);
				b.setVelX(0);
				b.setVelY(5);
				break;
			case Character.LEFT:
				b.setX(c.getX() - 6);
				b.setY(c.getY() + 14);
				b.setVelX(-5);
				b.setVelY(0);
				break;
			case Character.RIGHT:
				b.setX(c.getX() + c.getWidth());
				b.setY(c.getY() + 14);
				b.setVelX(5);
				b.setVelY(0);
				break;
			}
		}
	}
	
	// kiem tra xem co the di duoc khong
	synchronized public boolean isAllowed(Character c, int x) {
		
		if (c.isVisible()) {
			switch(x) {
			case Character.DOWN:
				// cham day ban do
				if (c.getFlagY()  + 40  > 700) return false;
				
				// kiem tra cac vat can
				for (Thung th : thung) {
					if (th.isVisible()) {
						if (c.getFlagY() + 40 == th.getY() && c.getFlagX() == th.getX() ||
							c.getFlagY() + 40 == th.getY() && c.getFlagX() + 20 == th.getX() ||
							c.getFlagY() + 40 == th.getY() && c.getFlagX() - 20 == th.getX()) 
							return false;
							}
				}
				for (River w : river) {
					if (w.isVisible()) {
						if (c.getFlagY() + 40 == w.getY() && c.getFlagX() == w.getX() ||
							c.getFlagY() + 40 == w.getY() && c.getFlagX() + 20 == w.getX() ||
							c.getFlagY() + 40 == w.getY() && c.getFlagX() - 20 == w.getX()) 
							return false;
					}	
				}
				for (Rock r : rocks) {
					if (r.isVisible()) {
						if (c.getFlagY() + 40 == r.getY() && c.getFlagX() == r.getX() ||
							c.getFlagY() + 40 == r.getY() && c.getFlagX() + 20 == r.getX() ||
							c.getFlagY() + 40 == r.getY() && c.getFlagX() - 20 == r.getX()) 
							return false;
					}	
				}
				for (Tree t : trees) {
					if (t.isVisible()) {
						if (c.getFlagY() + 40 == t.getY() && c.getFlagX() == t.getX() ||
							c.getFlagY() + 40 == t.getY() && c.getFlagX() + 20 == t.getX() ||
							c.getFlagY() + 40 == t.getY() && c.getFlagX() - 20 == t.getX()) 
							return false;
					}	
				}
				break;
			case Character.UP:
				// cham dinh ban do
				if (c.getFlagY() - 20 < 40) return false;
				// kiem tra vat can
				for (Thung th : thung) {
					if (th.isVisible())
						if (c.getFlagY() - 40 == th.getY() && c.getFlagX() == th.getX() ||
							c.getFlagY() - 40 == th.getY() && c.getFlagX() + 20 == th.getX() ||
							c.getFlagY() - 40 == th.getY() && c.getFlagX() - 20 == th.getX()) 
							return false;
				}
				for (River w : river) {
					if (w.isVisible()) {
						if (c.getFlagY() - 40 == w.getY() && c.getFlagX() == w.getX() ||
							c.getFlagY() - 40 == w.getY() && c.getFlagX() + 20 == w.getX() ||
							c.getFlagY() - 40 == w.getY() && c.getFlagX() - 20 == w.getX()) 
							return false;
					}
				}
				for (Tree t : trees) {
					if (t.isVisible()) {
						if (c.getFlagY() - 40 == t.getY() && c.getFlagX() == t.getX() ||
							c.getFlagY() - 40 == t.getY() && c.getFlagX() + 20 == t.getX() ||
							c.getFlagY() - 40 == t.getY() && c.getFlagX() - 20 == t.getX()) 
							return false;
					}	
				}
				for (Rock r : rocks) {
					if (r.isVisible()) {
						if (c.getFlagY() - 40 == r.getY() && c.getFlagX() == r.getX() ||
							c.getFlagY() - 40 == r.getY() && c.getFlagX() + 20 == r.getX() ||
							c.getFlagY() - 40 == r.getY() && c.getFlagX() - 20 == r.getX()) 
							return false;
					}	
				}
				break;
			case Character.LEFT:
				// cham mep trai ban do
				if (c.getFlagX() - 20 < 0) return false;
				// kiem tra vat can
				for (Thung th : thung) {
					if (th.isVisible())
						if (c.getFlagY() == th.getY() && c.getFlagX() - 40 == th.getX() ||
							c.getFlagY() + 20 == th.getY() && c.getFlagX() - 40 == th.getX() ||
							c.getFlagY() - 20 == th.getY() && c.getFlagX() - 40 == th.getX()) 
							return false;
				}				
				for (River w : river) {
					if (w.isVisible()) {
						if (c.getFlagY() == w.getY() && c.getFlagX() - 40 == w.getX() ||
							c.getFlagY() + 20 == w.getY() && c.getFlagX() - 40 == w.getX() ||
							c.getFlagY() - 20 == w.getY() && c.getFlagX() - 40 == w.getX()) 
							return false;
					}
				}				
				for (Rock r : rocks) {
					if (r.isVisible()) {
						if (c.getFlagY()  == r.getY() && c.getFlagX() - 40 == r.getX() ||
							c.getFlagY() + 20 == r.getY() && c.getFlagX() - 40 == r.getX() ||
							c.getFlagY() - 20 == r.getY() && c.getFlagX() - 40 == r.getX()) 
							return false;
					}	
				}
				for (Tree t : trees) {
					if (t.isVisible()) {
						if (c.getFlagY()  == t.getY() && c.getFlagX() - 40 == t.getX() ||
							c.getFlagY() + 20 == t.getY() && c.getFlagX() - 40 == t.getX() ||
							c.getFlagY() - 20 == t.getY() && c.getFlagX() - 40 == t.getX()) 
							return false;
					}	
				}
				break;
			case Character.RIGHT:
				// cham mep phai ban do
				if (c.getFlagX() + 20 > 760) return false;
				// kiem tra vat can
				for (Thung th : thung) {
					if (th.isVisible())
						if (c.getFlagY() == th.getY() && c.getFlagX() + 40 == th.getX() ||
							c.getFlagY() + 20 == th.getY() && c.getFlagX() + 40 == th.getX() ||
							c.getFlagY() - 20 == th.getY() && c.getFlagX() + 40 == th.getX()) 
							return false;
				}
				for (River w : river) {
					if (w.isVisible()) {
						if (c.getFlagY() == w.getY() && c.getFlagX() + 40 == w.getX() ||
							c.getFlagY() + 20 == w.getY() && c.getFlagX() + 40 == w.getX() ||
							c.getFlagY() - 20 == w.getY() && c.getFlagX() + 40 == w.getX()) 
							return false;
					}
				}
				for (Rock r : rocks) {
					if (r.isVisible()) {
						if (c.getFlagY()  == r.getY() && c.getFlagX() + 40 == r.getX() ||
							c.getFlagY() + 20 == r.getY() && c.getFlagX() + 40 == r.getX() ||
							c.getFlagY() - 20 == r.getY() && c.getFlagX() + 40 == r.getX()) 
							return false;
					}	
				}
				for (Tree t : trees) {
					if (t.isVisible()) {
						if (c.getFlagY()  == t.getY() && c.getFlagX() + 40 == t.getX() ||
							c.getFlagY() + 20 == t.getY() && c.getFlagX() + 40 == t.getX() ||
							c.getFlagY() - 20 == t.getY() && c.getFlagX() + 40 == t.getX()) 
							return false;
					}	
				}
				break;
			}
			return true;
		}
		return false;
	}
	// Key listener event
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {	
		int keyCode = e.getKeyCode();
		switch(keyCode) {
		case KeyEvent.VK_CONTROL:
		case KeyEvent.VK_Q:
		case KeyEvent.VK_SPACE:
			Fire(player);break;}
	}
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch(keyCode) {
		case KeyEvent.VK_LEFT:
			if (player.getDirection() != Character.LEFT) {
				if (player.getX() != player.getFlagX() || player.getY() != player.getFlagY())
					break;
				player.setDirection(Character.LEFT);
				break;
			}
			if (isAllowed(player, Character.LEFT)) {
				if (player.getX() == player.getFlagX())
					player.setFlagX(player.getFlagX() - 20);
				else break;
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (player.getDirection() != Character.RIGHT) {
				if (player.getX() != player.getFlagX() || player.getY() != player.getFlagY())
					break;
				player.setDirection(Character.RIGHT);
				break;
			}
			if (isAllowed(player, Character.RIGHT)) {
				if (player.getX() == player.getFlagX())
					player.setFlagX(player.getFlagX() + 20);
				else break;
			}
			break;
		case KeyEvent.VK_UP:
			if (player.getDirection() != Character.UP) {
				if (player.getX() != player.getFlagX() || player.getY() != player.getFlagY())
					break;
				player.setDirection(Character.UP);
				break;
			}
			if (isAllowed(player, Character.UP)) {
				if (player.getY() == player.getFlagY())
					player.setFlagY(player.getFlagY() - 20);
				else break;
			}
			break;
		case KeyEvent.VK_DOWN:
			if (player.getDirection() != Character.DOWN) {
				if (player.getX() != player.getFlagX() || player.getY() != player.getFlagY())
					break;
				player.setDirection(Character.DOWN);
				break;
			}
			if (isAllowed(player, Character.DOWN)) {
				if (player.getY() == player.getFlagY())
					player.setFlagY(player.getFlagY() + 20);
				else break;
			}
			break;
		case KeyEvent.VK_Z:
			if(loss.isVisible()||win.isVisible()) {
				this.dispose();
				@SuppressWarnings("unused")
				StartGame replay = new StartGame();
			}
			break;			
		case KeyEvent.VK_X:
			if(loss.isVisible()||win.isVisible())
			System.exit(0);
		}
	}
}
