import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Spike extends Element {
	
	private final static Image SPIKE = new Image(PuzzleUtil.FILE_PATH_RES+"spike.png", false);
	private int spikeCycle = 0, spikeDir = 0, bounceFrames = 0;
	//private Dir orientation;
	
	public Spike() {
		this(0, 0, PuzzleUtil.SPIKE_WIDTH, PuzzleUtil.SPIKE_HEIGHT,"test",'h',0);
	}
	public Spike(int x, int y, int w, int h, String c, char orientation, int status) {
		super(x,y,w,h,c,orientation,status);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		int sx = 0;//spikeCycle*400;
		int sy = 0;//spikeDir*600;
		int width = 0;
		int height = 0;
		
		if(orientation == 'h') {
			//horizontal spikes
			switch(c) {
	        //blue
			case "blue":
	        	sx = 0;
	        	sy = 0;
	      	  break; 
	      	//orange
	        case "orange":
	        	sx = 0;
	        	sy = 80;
	      	  break;
	      	//purple
	        case "purple":
	        	sx = 0;
	        	sy = 160;
	      	  break;
	      	//yellow
	        case "yellow":
	        	sx = 0;
	        	sy = 240;
	      	  break;
			}
			if(status == 1) {
				sx += 117; 
			}
			width = 110;
			height = 50;
		}
		
		if(orientation == 'v') {
			//vertical
			switch(c) {
			//blue
			case "blue":
	        	sx = 0;
	        	sy = 349;
	      	  break; 
	      	//orange
	        case "orange":
	        	sx = 0;
	        	sy = 512;
	      	  break;
	      	//purple
	        case "purple":
	        	sx = 0;
	        	sy = 675;
	      	  break;
	      	//yellow
	        case "yellow":
	        	sx = 0;
	        	sy = 838;
	      	  break;
			}
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
		//advanceCycle(millis); //advance when button pushed
	}
	
	private void advanceCycle(long millis) {
		bounceFrames += 1;
		if (bounceFrames > 20) {
			bounceFrames = 0;
			spikeCycle = (spikeCycle == 3 ? 0 : spikeCycle + 1);
		}
	}
	
	@Override
	public String toString() {
		ArrayList<Object> a = new ArrayList<Object>();
		a.add(new String("spike"));
		a.add(new Integer(x));
		a.add(new Integer(y));
		a.add(new Integer(w));
		a.add(new Integer(h));
		return PuzzleUtil.getSaveData(a);
	}
}
