import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

class Player extends Element
{
	private final static Image ZACK = new Image(PuzzleUtil.FILE_PATH_RES+"zack.png", false);
	private int playerWalkCycle = 0, playerDir = 0, walkFrames = 0;
	
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
		int sy = playerDir*600;
		
		gc.drawImage(ZACK, sx, sy, 400, 600, x, y, w, h);
	}
	@Override
	//every frame, do this
	public void update(long millis) {
		advanceWalkCycle(millis);
	}
	
	private void advanceWalkCycle(long millis) {
		walkFrames += 1;
		if (walkFrames > 20) {
			walkFrames = 0;
			playerWalkCycle = (playerWalkCycle == 3 ? 0 : playerWalkCycle + 1);
		}
	}
	
	public Player setDirection(PuzzleUtil.Dir d) {
		playerDir = PuzzleUtil.Dir.toInt(d);
		return this;
	}
}