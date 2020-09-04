import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Launchpad extends Element{
	
	private final static Image LAUNCHPAD = new Image(PuzzleUtil.FILE_PATH_RES+"launchers.png", false);
	private Dir direction;
	private int status;
	
	public Launchpad(int x, int y, int w, int h, Dir d, int status) {
		super(x,y,w,h);
		direction = d;
		this.status = status;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		//600 vertical, 400 horizontal
		int sx = status * 100;
		int sy = direction.getInt() * 100;
		
		gc.drawImage(LAUNCHPAD, sx, sy, 100, 100, x, y, w, h);
	}
	@Override
	//every frame, do this
	public void update(long millis) {
		//advance when button pushed
	}
}