import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Launchpad extends Element{
	
	private final static Image LAUNCHPAD = new Image(PuzzleUtil.FILE_PATH_RES+"launchers.png", false);
	private Dir direction;
	private int status;
	private Puzzle puzzle;
	
	public Launchpad(int x, int y, int w, int h, Dir d, int status, Puzzle p) {
		super(x,y,w,h);
		direction = d;
		this.status = status;
		puzzle = p;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		//600 vertical, 400 horizontal
		int sx = status * 100;
		int sy = direction.getInt() * 100;
		
		gc.drawImage(LAUNCHPAD, sx, sy, 100, 100, x, y, w, h);
	}
	@Override
	//every frame, do this
	public void update(long millis) {
		//advance when button pushed
	}
	
	@Override
	public void onCollision(boolean isOn) {
		if(status == 1)
			puzzle.stopPlayer();
		if(isOn && status == 0) {
			puzzle.launchPlayer(direction);
			status = 1;
		}
	}
	
	@Override
	public String toString() { //ex: launchpad,500,700,100,100,left,0
		ArrayList<Object> a = new ArrayList<Object>();
		a.add(new String("launchpad"));
		a.add(new Integer(x));
		a.add(new Integer(y));
		a.add(new Integer(w));
		a.add(new Integer(h));
		a.add(new String(direction.toString()));
		a.add(new Integer(status));
		return PuzzleUtil.getSaveData(a);
	}
}