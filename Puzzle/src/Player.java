import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

class Player extends Element
{
	private final static Image ZACK = new Image(PuzzleUtil.FILE_PATH_RES+"zack.png", false);
	public static final int ZACK_WIDTH = 66;
	public static final int ZACK_HEIGHT = 100;	
	public static final int START_X = 570;
	public static final int START_Y = 540;
	private final static int PLAYER_SPEED = 2;
	private int playerWalkCycle = 0, walkFrames = 0;
	private Dir playerDir = Dir.DOWN;
	private boolean walking = false;
	
	public Player() {
		this(570, 540, ZACK_WIDTH, ZACK_HEIGHT);
	}
	public Player(int x, int y, int w, int h) {
		super(x,y,w,h);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		//600 vertical, 400 horizontal
		int sx = playerWalkCycle*400;
		int sy = playerDir.getInt()*600;
		
		gc.drawImage(ZACK, sx, sy, 400, 600, x, y, w, h);
	}
	@Override
	//every frame, do this
	public void update(long millis) {
		if (walking) {
			advanceWalkCycle(millis);
			if (playerDir == Dir.UP)
				y -= PLAYER_SPEED;
			if (playerDir == Dir.DOWN)
				y += PLAYER_SPEED;
			if (playerDir == Dir.LEFT)
				x -= PLAYER_SPEED;
			if (playerDir == Dir.RIGHT)
				x += PLAYER_SPEED;
		}
		else
			playerWalkCycle = 0;
	}
	
	private void advanceWalkCycle(long millis) {
		walkFrames += 1;
		if (walkFrames > 10) {
			walkFrames = 0;
			playerWalkCycle = (playerWalkCycle == 3 ? 0 : playerWalkCycle + 1);
		}
	}
	
	//mutators
	public Player setDirection(Dir d, boolean walk) {
		playerDir = d;
		if (walk) 
			setWalking(true);
		return this;
	}
	public Player setWalking(boolean w) {
		walking = w;
		return this;
	}
	//accessors
	public Dir getDir(){
		return playerDir;
	}
	public boolean getWalking() {
		return walking;
	}
	public void goTo(int x, int y) {
		this.x = x;
		this.y = y;
	}
}