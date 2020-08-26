import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Jukebox extends Element {
	
	private final static Image JUKEBOX = new Image(PuzzleUtil.FILE_PATH_RES+"jukeboxes.png", false);
	private int playerWalkCycle = 0, playerDir = 0;
	//test upload
	
	public Jukebox() {
		this(635, 450, PuzzleUtil.JUKEBOX_WIDTH, PuzzleUtil.JUKEBOX_HEIGHT);
	}
	public Jukebox(int x, int y, int w, int h) {
		super(x,y,w,h);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.TRANSPARENT);
		gc.fillRect(x, y, w, h);

		//600 vertical, 400 horizontal
		int sx = playerWalkCycle*400;
		int sy = playerDir*600;
		
		gc.drawImage(JUKEBOX, sx, sy, 350, 600, x, y, w, h);
	}
}

