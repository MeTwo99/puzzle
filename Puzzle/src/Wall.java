import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

class Wall extends Element
{
	private final static Image WALL = new Image(PuzzleUtil.FILE_PATH_RES+"wall.jpg", false);
	
	public Wall(int x, int y, int w, int h) {
		super(x,y,w,h);
	}
	
	//the @Override annotation denotes that this function overrides the abstract function 
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.GRAY);
		gc.fillRect(x, y, w, h);
		PuzzleUtil.repeatImage(gc, WALL, x, y, w, h);
	}
}