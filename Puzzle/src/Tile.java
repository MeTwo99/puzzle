import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

class Tile extends Element
{	
	protected String imageName;
	protected Image image;
	
	public Tile(int x, int y, int w, int h, String imageName) {
		super(x,y,w,h);
		this.imageName = imageName;
		image = PuzzleUtil.TEXTURES.get(imageName);
	}
	
	protected void repeatImage(GraphicsContext gc, Image i, int x, int y, int w, int h) {
		if (i == null || gc == null)
			return;
		
		double iw = i.getWidth();
		int rows = (int)Math.floor(w/iw);
		double remainderWidth = w - (rows*iw);
		double ih = i.getHeight();
		int cols = (int)Math.floor(h/ih);
		double remainderHeight = h - (cols*ih);
		
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++) {
				gc.drawImage(i, x+r*iw, y+c*ih);
			}
			gc.drawImage(i, x+r*iw, y+cols*ih, iw, remainderHeight);
		}
		for (int c = 0; c < cols; c++) {
			gc.drawImage(i, x+rows*iw, y+c*ih, remainderWidth, ih);
		}
		gc.drawImage(i, x+rows*iw, y+cols*ih, remainderWidth, remainderHeight);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		repeatImage(gc, image, x, y, w, h);
	}
	
	@Override
	public String toString() {
		ArrayList<Object> a = new ArrayList<Object>();
		a.add(new String("t"));
		a.add(new Integer(x));
		a.add(new Integer(y));
		a.add(new Integer(w));
		a.add(new Integer(h));
		a.add(new String(imageName));
		return PuzzleUtil.getSaveData(a);
	}
}