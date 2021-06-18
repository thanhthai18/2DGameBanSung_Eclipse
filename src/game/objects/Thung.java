package game.objects;

public class Thung extends GameObject {
	private int hp;  //Mau cua thung
	public Thung(int x, int y, int hp) {
		super(x, y);
		this.hp=2;
		initThung();
	}
	
	private void initThung() {
		loadImage("images/thung.png");
		getImageDimensions();
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
	
}
