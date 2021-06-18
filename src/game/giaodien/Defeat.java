package game.giaodien;
//Victory va Defeat thuc ra cung chi la extends cua GameObject nhu cac doi tuong cay, da, nuoc,..
//khi nao thua hay thang thi chen anh vao thoi :))
import game.objects.GameObject;

public class Defeat extends GameObject{
	public Defeat(int x, int y) {
		super(x, y);
		initThua();
	}
	
	private void initThua() {
		loadImage("images/gameover.jpg");
		getImageDimensions();
	}
}

