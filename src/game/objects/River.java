package game.objects;

public class River extends GameObject  {
	
	public River(int x, int y) {
		//xac dinh vi tri cua song
		super(x, y);
		initRiver();
	}
	
	private void initRiver() {
		loadImage("images/river.png");
		getImageDimensions();
	}
	
}
