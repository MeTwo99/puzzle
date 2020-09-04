import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Arrow extends Element{
	private final static Image ARROW = new Image(PuzzleUtil.FILE_PATH_RES+"arrow.png", false);
	private Dir arrowDir;
	private String levelName;
	private int destinationX, destinationY;
	private Player player;
	private Puzzle puzzle;
	
	public Arrow(int x, int y, int w, int h, Dir dir, String ln, int dx, int dy, Puzzle p) {
		super(x,y,w,h);
		arrowDir = dir;
		levelName = ln;
		destinationX = dx;
		destinationY = dy;
		puzzle = p;
	}

	@Override
	public void draw(GraphicsContext gc) {
		int sx = arrowDir.getInt()*100;		
		gc.drawImage(ARROW, sx, 0, 100, 100, x, y, w, h);
	}
	@Override
	public void onCollision(boolean isOn) {
		if (isOn) {
			puzzle.setLevelDestination(levelName, destinationX, destinationY);
		}
	};
	@Override
	public String toString() {
		ArrayList<Object> a = new ArrayList<Object>();
		a.add(new String("a"));
		a.add(new Integer(x));
		a.add(new Integer(y));
		a.add(new Integer(w));
		a.add(new Integer(h));
		a.add(new String(arrowDir.toString()));
		a.add(new String(levelName));
		a.add(new Integer(destinationX));
		a.add(new Integer(destinationY));
		return PuzzleUtil.getSaveData(a);
	}
}
