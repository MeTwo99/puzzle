import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

class Wall extends Tile
{	
	public Wall(int x, int y, int w, int h, String imageName) {
		super(x,y,w,h,imageName);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		repeatImage(gc, image, x, y, w, h);
	}
	
	@Override
	public String toString() {
		ArrayList<Object> a = new ArrayList<Object>();
		a.add(new String("w"));
		a.add(new Integer(x));
		a.add(new Integer(y));
		a.add(new Integer(w));
		a.add(new Integer(h));
		a.add(new String(imageName));
		return PuzzleUtil.getSaveData(a);
	}
}