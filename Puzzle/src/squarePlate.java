import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class squarePlate extends Element{
	
	private final static Image SPLATE = new Image(PuzzleUtil.FILE_PATH_RES+"square_plates.png", false);
private int spikeCycle = 0, spikeDir = 0, bounceFrames = 0;
	
	public squarePlate() {
		this(0, 0, PuzzleUtil.SPLATE_WIDTH, PuzzleUtil.SPLATE_HEIGHT,"test");
	}
	public squarePlate(int x, int y, int w, int h, String c) {
		super(x,y,w,h,c);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		//600 vertical, 400 horizontal
		int sx = 0;//spikeCycle*400;
		int sy = 0;//spikeDir*600;
		
		switch(c) {
        //blue
		case "blue":
        	sx = 0;
        	sy = 0;
      	  break; 
      	//orange
        case "orange":
        	sx = 0;
        	sy = 251;
      	  break;
      	//purple
        case "purple":
        	sx = 0;
        	sy = 502;
      	  break;
      	//yellow
        case "yellow":
        	sx = 0;
        	sy = 753;
      	  break;
		}

		
		gc.drawImage(SPLATE, sx, sy, 100, 100, x, y, w, h);
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