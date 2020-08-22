import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

class Player extends Element
{
	private final static Image ZACK = new Image(PuzzleUtil.FILE_PATH_RES+"zack.png", false);
	
	private int playerWalkCycle = 0, playerDir = 0;
	
	public Player() {
		this(400, 600, PuzzleUtil.ZACK_WIDTH, PuzzleUtil.ZACK_HEIGHT);
	}
	public Player(int x, int y, int w, int h) {
		super(x,y,w,h);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.BLUE);
		gc.fillRect(x, y, w, h);

		//600 vertical, 400 horizontal
		int sx = playerWalkCycle*400;
		int sy = playerDir*600;
		
		gc.drawImage(ZACK, sx, sy, 400, 600, x, y, w, h);
	}
}