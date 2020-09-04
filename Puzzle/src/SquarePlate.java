import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class SquarePlate extends Element{
	
	private final static Image SPLATE = new Image(PuzzleUtil.FILE_PATH_RES+"square_plates.png", false);
	private Col color;
	
	public SquarePlate(int x, int y, int w, int h, Col c) {
		super(x,y,w,h);
		color = c;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		int sx = 0;
		int sy = 251 * color.getInt();
		
		gc.drawImage(SPLATE, sx, sy, 100, 100, x, y, w, h);
	}
	@Override
	//every frame, do this
	public void update(long millis) {
		//advance when button pushed
	}
	
	@Override
	public String toString() { //ex: sp,500,525,100,100,yellow
		ArrayList<Object> a = new ArrayList<Object>();
		a.add(new String("sp"));
		a.add(new Integer(x));
		a.add(new Integer(y));
		a.add(new Integer(w));
		a.add(new Integer(h));
		a.add(new String(color.toString()));
		return PuzzleUtil.getSaveData(a);
	}
}