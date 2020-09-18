import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Door extends Element{
	
	private final static Image DOOR = new Image(PuzzleUtil.FILE_PATH_RES+"door.png", false);
	private final static int DOOR_WIDTH = 83;
	private final static int DOOR_HEIGHT = 184;
	private final static int DOOR_ADVANCE_WAIT = 5;
	private final static int DOOR_OPEN_DURATION = 30;
	private int wait;
	private int advanceWait;
	private int doorFrame;
	private Puzzle puzzle;
	private int active;
	private String name, openNextName;
	private boolean isPlayerTouching, isClosed = true; // variable that will stop the player
	
	public Door(int x, int y, int w, int h, int active, String name, String openNextName, Puzzle p) {
		super(x,y,w,h);
		this.active = active;
		this.name = name;
		this.openNextName = openNextName;
		puzzle = p;
		advanceWait = 0;
		isPlayerTouching = false;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		int sx = doorFrame * DOOR_WIDTH;
		gc.drawImage(DOOR, sx, 0, DOOR_WIDTH, DOOR_HEIGHT, x, y, w, h);
	}
	@Override
	//every frame, do this
	public void update(long millis) {
		if(active == 1) {
			advanceWait += 1;
			if (advanceWait > DOOR_ADVANCE_WAIT) {
				advanceWait = 0;
				wait += 1;
				
				if(wait < DOOR_OPEN_DURATION) {
					//open door
					isClosed = false;
					doorFrame = doorFrame < 3 ? doorFrame + 1 : 3;
				}
				else {
					//check can close door?
					if  (!isPlayerTouching) {
						//start closing when the player is out of the way
						isClosed = true; 
					}
					if(isClosed) {
						//stop the player from moving into the wall
						doorFrame = doorFrame > 0 ? doorFrame - 1 : 0;
					}
				}
				
				//once cycle is complete, send message to the next door
				if (doorFrame == 0 && isClosed) {
					active = 0;
					puzzle.setDoorActive(openNextName);
				}
			}
		}
		//reset check player collision
		isPlayerTouching = false;
	}
	
	//get the door with the name to open
	public String getName() {
		return name;
	}
	//activate that door
	public void setActive() {
		wait = 0;
		advanceWait = 0;
		active = 1;
	}
	
	@Override
	public void onCollision(boolean isOn) {
		isPlayerTouching = true;
		if(isClosed)
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
		a.add(new Integer(active));
		a.add(new String(name));
		a.add(new String(openNextName));
		return PuzzleUtil.getSaveData(a);
	}
}