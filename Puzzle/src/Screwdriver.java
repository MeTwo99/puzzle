import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Screwdriver extends Element{
	
	private final static Image SCREWDRIVER = new Image(PuzzleUtil.FILE_PATH_RES+"screwdriver.png", false);
	private int status;
	private Puzzle puzzle;
	
	public Screwdriver(int x, int y, int w, int h, int status, Puzzle p) {
		super(x,y,w,h);
		this.status = status;
		puzzle = p;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		int sx = status * 163;
		int sy = 0;
		
		gc.drawImage(SCREWDRIVER, sx, sy, 100, 100, x, y, w, h);
	}
	@Override
	//every frame, do this
	public void update(long millis) {
		//advance when button pushed
	}
	
	@Override
	public void onCollision(boolean isOn) {
		if(isOn && status == 0) {
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
		//a.add(new String(color.toString()));		
		a.add(new Integer(status));
		return PuzzleUtil.getSaveData(a);
	}
}