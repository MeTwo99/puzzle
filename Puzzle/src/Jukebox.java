import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Jukebox extends Wall {
	
	private int jukeboxBounceCycle = 1, jukeboxDir = 0, bounceFrames = 0;
	
	public Jukebox(int x, int y, int w, int h) {
		super(x,y,w,h,"jukebox");
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		//600 vertical, 400 horizontal
		int sx = jukeboxBounceCycle*400;
		int sy = jukeboxDir*600;
		
		gc.drawImage(image, sx, sy, 350, 600, x, y, w, h);
	}
	@Override
	//every frame, do this
	public void update(long millis) {
		advanceBounceCycle(millis);
	}
	
	private void advanceBounceCycle(long millis) {
		bounceFrames += 1;
		if (bounceFrames > 20) {
			bounceFrames = 0;
			jukeboxBounceCycle = (jukeboxBounceCycle == 3 ? 0 : jukeboxBounceCycle + 1);
		}
	}
	
	@Override
	public String toString() {
		ArrayList<Object> a = new ArrayList<Object>();
		a.add(new String("j"));
		a.add(new Integer(x));
		a.add(new Integer(y));
		a.add(new Integer(w));
		a.add(new Integer(h));
		return PuzzleUtil.getSaveData(a);
	}
}

