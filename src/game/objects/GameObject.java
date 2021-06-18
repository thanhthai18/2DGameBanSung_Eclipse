package game.objects;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
 
public class GameObject {
	protected int x, y; //toa do cua doi tuong
	protected int width, height; //chieu rong, chieu cao cua doi tuong
	protected Image image; //hinh anh doi tuong
	protected boolean visible; //trang thai hien thi
	
	public GameObject(int x, int y) { //phuong thuc khoi tao
		this.x = x;
		this.y = y;
		visible = true;
	}
	
	public void getImageDimensions() { //lay kich thuoc cua doi tuong
		width = image.getWidth(null); 
		height = image.getHeight(null);
	}
	
	
	public void loadImage(String path) { //load hinh anh cho doi tuong
		ImageIcon ii = new ImageIcon(getClass().getClassLoader().getResource(path));
		image = ii.getImage();
	}
	
	public Image getImage() { //lay hinh anh cua doi tuong
		return image;
	}
	
	public int getX() { 
		return x; 
	}
	
	public int getY() {
		return y; 
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
 	}
	
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public Rectangle getBounds() { //lay khung hinh anh cua doi tuong de  xu li va cham
		return new Rectangle(x, y, width, height);
	}
	
}