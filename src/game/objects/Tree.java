
package game.objects;

public class Tree extends GameObject {
	
	public Tree(int x, int y) {
		super(x, y);
		initTree();
	}
	
	private void initTree() {
		loadImage("images/tree.png");
		getImageDimensions();
	}
}
