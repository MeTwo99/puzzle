import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Jukebox extends Wall {
	
	private final static Image JUKEBOX = new Image(PuzzleUtil.FILE_PATH_RES+"jukeboxes.png", false);
	private int jukeboxBounceCycle = 1, jukeboxDir = 0, bounceFrames = 0;
	
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
		int sx = jukeboxBounceCycle*400;
		int sy = jukeboxDir*600;
		
		gc.drawImage(JUKEBOX, sx, sy, 350, 600, x, y, w, h);
	}
	@Override
	//every frame, do this
	public void update(long millis) {
		advanceBounceCycle(millis);
	}
	
	private void advanceBounceCycle(long millis) {
		bounceFrames += 1;
		if (bounceFrames > 20) {
			bounceFrames = 0;
			jukeboxBounceCycle = (jukeboxBounceCycle == 3 ? 0 : jukeboxBounceCycle + 1);
		}
	}
}

