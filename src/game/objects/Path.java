package game.objects;

public class Path extends GameObject {
	
	public Path(int x, int y) {
		super(x, y);
		initPath();
	}
	
	private void initPath() {
		loadImage("images/background.png");
		getImageDimensions();
	}
}
