package game.giaodien;
// Victory va Defeat thuc ra cung chi la extends cua GameObject nhu cac doi tuong cay, da, nuoc,..
// khi nao thua hay thang thi chen anh vao thoi :))
import game.objects.GameObject;

public class Victory extends GameObject{
	public Victory(int x, int y) {
		super(x, y);
		initThang();
	}
	
	private void initThang() {
		loadImage("images/victory.jpg");
		getImageDimensions();
	}
}
