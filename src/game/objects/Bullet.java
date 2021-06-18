package game.objects;

public class Bullet extends GameObject {
	
	protected int velX, velY;// toc do dan
	public final int PLAYER = 1;
	public final int ENEMY = 2;
	protected int belongto;//dan ban tu nhan vat nao

	public Bullet(int x, int y) {
		super(x, y);

		initBullet();
	}
	
	public Bullet() {
		super(0, 0);
		
		initBullet();
	}
	
	private void initBullet() {
		loadImage("images/bullet01.png");
		getImageDimensions();//lay kich thuoc doi tuong
	}
	
	public int beLongTo() {
		return belongto;
	}
	
	public int getVelX() {
		return velX;
	}
	
	public int getVelY() {
		return velY;
	}
	
	public void setVelX(int velX) {
		this.velX = velX;
	}
	
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public void setBeLongTo(int who) {
		belongto = who;
	}
}