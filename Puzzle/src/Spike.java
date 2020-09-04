import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Spike extends Element {
	
	private final static Image SPIKE = new Image(PuzzleUtil.FILE_PATH_RES+"spike.png", false);
	private int status;
	private char orientation;
	private Col color;
	
	public Spike(int x, int y, int w, int h, Col c, char orientation, int status) {
		super(x,y,w,h);
		color = c;
		this.orientation = orientation;
		this.status = status;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		int sx = 0;
		int sy = 0;
		int width = 0;
		int height = 0;
		
		if(orientation == 'h') {
			sy = color.getInt() * 80;
			if(status == 1) {
				sx += 117; 
			}
			width = 110;
			height = 50;
		}
		if(orientation == 'v') {
			sy = 349 + color.getInt() * 163;
			if(status == 1) {
				sx += 50;
			}
			width = 40;
			height = 110;
		}
		
		gc.drawImage(SPIKE, sx, sy, width, height, x, y, w, h);
	}
	@Override
	//every frame, do this
	public void update(long millis) {
		//advance when button pushed
	}
	
	
	@Override
	public String toString() { //ex: spike,600,425,110,50,blue,h,1
		ArrayList<Object> a = new ArrayList<Object>();
		a.add(new String("spike"));
		a.add(new Integer(x));
		a.add(new Integer(y));
		a.add(new Integer(w));
		a.add(new Integer(h));
		a.add(new String(color.toString()));
		a.add(new Character(orientation));
		a.add(new Integer(status));
		return PuzzleUtil.getSaveData(a);
	}
}
