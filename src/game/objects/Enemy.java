package game.objects;

public class Enemy extends Character {
		
	public Enemy(int x, int y, int direction) {
		super(x, y);
		initEnemy();
	}
	
	private void initEnemy() {
		loadImage("images/enemy.png");
		getImageDimensions();
	}
}
