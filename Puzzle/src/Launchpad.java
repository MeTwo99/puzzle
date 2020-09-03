import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Launchpad extends Element{
	
	private final static Image LAUNCHPAD = new Image(PuzzleUtil.FILE_PATH_RES+"launchpads.png", false);
private int spikeCycle = 0, spikeDir = 0, bounceFrames = 0;
	
	public Launchpad() {
		this(0, 0, PuzzleUtil.SPLATE_WIDTH, PuzzleUtil.SPLATE_HEIGHT,"test", 'l',0);
	}
	public Launchpad(int x, int y, int w, int h, String c, char orientation, int status) {
		super(x,y,w,h,c,orientation,status);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		//600 vertical, 400 horizontal
		int sx = 0;//spikeCycle*400;
		int sy = 0;//spikeDir*600;
		
		switch(orientation) {
        //up
		case 'u':
        	sx = 0;
        	sy = 0;
      	  break; 
      	//left
        case 'l':
        	sx = 0;
        	sy = 213;
      	  break;
      	//right
        case 'r':
        	sx = 0;
        	sy = 426;
      	  break;
		}
		if(status == 1) {
			sx += 208;
		}

		
		gc.drawImage(LAUNCHPAD, sx, sy, 100, 100, x, y, w, h);
	}
	@Override
	//every frame, do this
	public void update(long millis) {
		//advanceCycle(millis); //advance when button pushed
	}
	
	private void advanceCycle(long millis) {
		bounceFrames += 1;
		if (bounceFrames > 20) {
			bounceFrames = 0;
			spikeCycle = (spikeCycle == 3 ? 0 : spikeCycle + 1);
		}
	}
}