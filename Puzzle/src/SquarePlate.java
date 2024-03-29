import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SquarePlate extends Element{
	
	private final static Image SPLATE = new Image(PuzzleUtil.FILE_PATH_RES+"square_plates.png", false);
	private Col color;
	private int status;
	private Puzzle puzzle;
	
	public SquarePlate(int x, int y, int w, int h, Col c, int status, Puzzle p) {
		super(x,y,w,h);
		color = c;
		this.status = status;
		puzzle = p;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		int sx = status * 163;
		int sy = 251 * color.getInt();
		
		gc.drawImage(SPLATE, sx, sy, 100, 100, x, y, w, h);
	}
	@Override
	//every frame, do this
	public void update(long millis) {
		//advance when button pushed
	}
	
	@Override
	public void onCollision(boolean isOn) {
		if(status == 0) {
			puzzle.activateColor(color);
			status = 1;
		}
	};
	
	@Override
	public String toString() { //ex: sp,500,525,100,100,yellow
		ArrayList<Object> a = new ArrayList<Object>();
		a.add(new String("sp"));
		a.add(new Integer(x));
		a.add(new Integer(y));
		a.add(new Integer(w));
		a.add(new Integer(h));
		a.add(new String(color.toString()));		
		a.add(new Integer(status));
		return PuzzleUtil.getSaveData(a);
	}
}