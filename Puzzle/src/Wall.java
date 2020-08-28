import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

class Wall extends Element
{
	public Wall(int x, int y, int w, int h, Image i) {
		super(x,y,w,h,i);
	}
	public Wall(int x, int y, int w, int h) {
		super(x,y,w,h);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		PuzzleUtil.repeatImage(gc, i, x, y, w, h);
	}
}