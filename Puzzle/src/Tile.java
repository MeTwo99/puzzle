import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

class Tile extends Element
{	
	public Tile(int x, int y, int w, int h, Image i) {
		super(x,y,w,h,i);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.DARKGRAY);
		gc.fillRect(x, y, w, h);
		PuzzleUtil.repeatImage(gc, i, x, y, w, h);
	}
}