import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

class Tile extends Element
{
	private final static Image TILE = new Image(PuzzleUtil.FILE_PATH_RES+"tile.jpg", false);
	
	public Tile(int x, int y, int w, int h) {
		super(x,y,w,h);
	}
	
	//the @Override annotation denotes that this function overrides the abstract function 
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.DARKGRAY);
		gc.fillRect(x, y, w, h);
		PuzzleUtil.repeatImage(gc, TILE, x, y, w, h);
	}
}