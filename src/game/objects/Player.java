package game.objects;

public class Player extends Character {

	
	public Player(int x, int y, int direction) {
		super(x, y, direction);
		initPlayer();
	}

	private void initPlayer() {
		loadImage("images/player.png");
		getImageDimensions();
	}
}
