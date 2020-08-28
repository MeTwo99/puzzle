import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Arrow extends Element{
	private final static Image ARROW = new Image(PuzzleUtil.FILE_PATH_RES+"arrow.png", false);
	private Dir arrowDir;
	
	public Arrow(int x, int y, int w, int h, Dir dir) {
		super(x,y,w,h);
		arrowDir = dir;
	}
	
	//the @Override annotation denotes that this function overrides the abstract function 
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.TRANSPARENT);
		gc.fillRect(x, y, w, h);

		int sx = arrowDir.getInt()*100;		
		gc.drawImage(ARROW, sx, 0, 100, 100, x, y, w, h);
	}
}
