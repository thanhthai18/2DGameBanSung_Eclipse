package game.objects;

public class OpenDoor extends GameObject {
	
	public OpenDoor(int x, int y) {
		super(x, y);
		initdoor();
	}
	
	private void initdoor() {
		loadImage("images/nextground.png");
		getImageDimensions();
	}

}
