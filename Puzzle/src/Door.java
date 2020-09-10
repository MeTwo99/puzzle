import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Door extends Element{
	
	private final static Image DOOR = new Image(PuzzleUtil.FILE_PATH_RES+"door.png", false);
	private final static int DOOR_WIDTH = 83;
	private final static int DOOR_HEIGHT = 184;
	private final static int DOOR_ADVANCE_WAIT = 5;
	private final static int DOOR_MAX_WAIT_VALUE = 140;
	private int wait;
	private int advanceWait;
	private int minWait;
	private int maxWait;
	private int doorFrame;
	private Puzzle puzzle;
	
	public Door(int x, int y, int w, int h, int wait, int minWait, int maxWait, Puzzle p) {
		super(x,y,w,h);
		this.wait = wait;
		this.minWait = minWait;
		this.maxWait = maxWait;
		puzzle = p;
		advanceWait = 0;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		int sx = doorFrame * DOOR_WIDTH;
		gc.drawImage(DOOR, sx, 0, DOOR_WIDTH, DOOR_HEIGHT, x, y, w, h);
	}
	@Override
	//every frame, do this
	public void update(long millis) {
		advanceWait += 1;
		if (advanceWait > DOOR_ADVANCE_WAIT) {
			advanceWait = 0;
			wait = wait > DOOR_MAX_WAIT_VALUE ? 0 : wait + 1;
			
			if(wait > minWait && wait < maxWait) {
				//open door
				doorFrame = doorFrame < 3 ? doorFrame + 1 : 3;
			}
			else {
				//shut the door
				doorFrame = doorFrame > 0 ? doorFrame - 1 : 0;
			}
		}
	}
	
	@Override
	public void onCollision(boolean isOn) {
		if(doorFrame == 0)
			puzzle.stopPlayer();
	}
	
	@Override
	public String toString() { 
		ArrayList<Object> a = new ArrayList<Object>();
		a.add(new String("door"));
		a.add(new Integer(x));
		a.add(new Integer(y));
		a.add(new Integer(w));
		a.add(new Integer(h));
		a.add(new Integer(wait));
		a.add(new Integer(minWait));
		a.add(new Integer(maxWait));
		return PuzzleUtil.getSaveData(a);
	}
}