package game.play;

import java.util.Random;

import game.objects.Enemy;
import game.objects.Character;

public class EnemyBehaviour implements Runnable {
	
	private Enemy c;
	private GAME g;
	Thread ai;
	
	public EnemyBehaviour(Enemy c, GAME g) {
		this.c = c;
		this.g = g;
	}
	
	public void start() {
		//Chay luong vua moi khoi tao
		ai = new Thread(this);
		ai.start();
	}
	
	public void run() {
		Random rand = new Random();
		int currentBack = 4;
		switch(c.getDirection()) {
		// Khong quay lai vi tri vua dung 
		case Character.DOWN: currentBack = Character.UP; break;
		case Character.UP: currentBack = Character.DOWN; break;
		case Character.LEFT: currentBack = Character.RIGHT; break;
		case Character.RIGHT: currentBack = Character.LEFT; break;
		}
		int count = 0;
		while (true) {
			switch(c.getDirection()) {
			case Character.DOWN: currentBack = Character.UP; break;
			case Character.UP: currentBack = Character.DOWN; break;
			case Character.LEFT: currentBack = Character.RIGHT; break;
			case Character.RIGHT: currentBack = Character.LEFT; break;
			}
			if (c.isVisible()) {
				int direction = rand.nextInt(4);
				if (direction == currentBack) continue;
				else {
					if(g.isAllowed(c, direction)) {
						count = 0; // reset count
						try {
							g.Fire(c);
							Thread.sleep(600);
							go(direction);
							if(g.isAllowed(c, direction)) {
								Thread.sleep(700);
								go(direction);
							}
							if(g.isAllowed(c, direction)) {
								Thread.sleep(700);
								go(direction);
							}
						} catch (Exception e) {}
					}
					else {
						count++;
					}	
				}
				// Neu bat buoc phai quay lai vi tri vua dung thi cho quay lai
				if (count == 100) {
					go(currentBack);
					count = 0;
				}
			} else
				try {
					Thread.sleep(999999999);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
	}
	
	private void go(int direction) {
		switch(direction) {
		case Character.LEFT:
			if (c.getDirection()!= Character.LEFT) {
				c.setDirection(Character.LEFT);
				break;
			}								
			c.setFlagX(c.getFlagX() - 40);
			break;
		case Character.RIGHT:
			if (c.getDirection() != Character.RIGHT) {
				c.setDirection(Character.RIGHT);
				break;
			}
			c.setFlagX(c.getFlagX() + 40);
			break;
		case Character.UP:
			if (c.getDirection() != Character.UP) {
				c.setDirection(Character.UP);
				break;
			}
			c.setFlagY(c.getFlagY() - 40);
			break;
		case Character.DOWN:
			if (c.getDirection() != Character.DOWN) {
				c.setDirection(Character.DOWN);
				break;
			}
			c.setFlagY(c.getFlagY() + 40);
			break;
		}
	}
}
