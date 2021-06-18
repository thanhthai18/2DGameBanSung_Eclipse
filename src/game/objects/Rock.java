package game.objects;

public class Rock extends GameObject {
	
	public Rock(int x, int y) {
		super(x, y);
		initRock();
	}
	
	private void initRock() {
		loadImage("images/rock.png");
		getImageDimensions();
	}
}
