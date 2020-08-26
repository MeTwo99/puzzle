import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Arrows extends Element{
	private final static Image ARROWS = new Image(PuzzleUtil.FILE_PATH_RES+"arrows.png", false);
	private int arrowsCycle = 1, arrowsDir = 0;
	
	public Arrows(int x, int y, int w, int h) {
		super(x,y,w,h);
	}
	
	//the @Override annotation denotes that this function overrides the abstract function 
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.TRANSPARENT);
		gc.fillRect(x, y, w, h);

		int sx = arrowsCycle;
		int sy = arrowsDir;
		
		gc.drawImage(ARROWS, sx, sy, 350, 600, x, y, w, h);
	}
}
