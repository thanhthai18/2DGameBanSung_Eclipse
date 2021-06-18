package game.objects;

public class Character extends GameObject {
	
	
	// orientation constant
	public static final int UP = 0;
	public static final int LEFT = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 3;
	
	protected int direction;
	protected int hp;
	protected int damage;
	protected int level;
	/*
	 toa do x,y tiep theo muon toi
	 */
	protected int flagX; 
	protected int flagY;   
	protected int dx, dy;
	
	public Character(int x, int y) {
		super(x, y);
		setDirection(UP);
		flagX = x;
		flagY = y;
	}
	
	public Character(int x, int y, int direction) {
		super(x, y);
		setDirection(direction);
		flagX = x;
		flagY = y;
	}
	
	public int getDirection() { 
		return direction; 
	}
	public void setDirection(int direction) { 
		this.direction = direction;
	}

	public int getHp() {
		return hp; 
	}
	
	public void setHp(int hp) { 
		this.hp = hp; 
	}
	
	public int getDamage() { 
		return damage; 
	}
	
	public void setDamage(int damage) { 
		this.damage = damage; 
	}
	
	public int getLevel() { 
		return level; 
	}
	
	public void setLevel(int level) { 
		this.level = level; 
	} 
	
	public int getFlagX() { 
		return flagX; 
	}
	
	public void setFlagX(int flagX) { 
		this.flagX = flagX; 
	}
	
	public int getFlagY() { 
		return flagY; 
	}
	
	public void setFlagY(int flagY) { 
		this.flagY = flagY; 
	}
}
