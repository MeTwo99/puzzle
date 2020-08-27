import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

class Player extends Element
{
	private final static Image ZACK = new Image(PuzzleUtil.FILE_PATH_RES+"zack.png", false);
	private final static int PLAYER_SPEED = 2;
	private int playerWalkCycle = 0, walkFrames = 0;
	private PuzzleUtil.Dir playerDir = PuzzleUtil.Dir.DOWN;
	private boolean walking = false;
	
	public Player() {
		this(400, 600, PuzzleUtil.ZACK_WIDTH, PuzzleUtil.ZACK_HEIGHT);
	}
	public Player(int x, int y, int w, int h) {
		super(x,y,w,h);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		//600 vertical, 400 horizontal
		int sx = playerWalkCycle*400;
		int sy = PuzzleUtil.Dir.toInt(playerDir)*600;
		
		gc.drawImage(ZACK, sx, sy, 400, 600, x, y, w, h);
	}
	@Override
	//every frame, do this
	public void update(long millis) {
		if (walking) {
			advanceWalkCycle(millis);
			if (playerDir == PuzzleUtil.Dir.UP)
				y -= PLAYER_SPEED;
			if (playerDir == PuzzleUtil.Dir.DOWN)
				y += PLAYER_SPEED;
			if (playerDir == PuzzleUtil.Dir.LEFT)
				x -= PLAYER_SPEED;
			if (playerDir == PuzzleUtil.Dir.RIGHT)
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
	public Player setDirection(PuzzleUtil.Dir d) {
		playerDir = d;
		setWalking(true);
		return this;
	}
	public Player setWalking(boolean w) {
		walking = w;
		return this;
	}
	//accessors
	public PuzzleUtil.Dir getDir(){
		return playerDir;
	}
	public boolean getWalking() {
		return walking;
	}
}