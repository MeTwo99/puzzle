import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Spike extends Element {
	
	private final static Image SPIKE = new Image(PuzzleUtil.FILE_PATH_RES+"spike.png", false);
	private int spikeCycle = 1, spikeDir = 0, bounceFrames = 0;
	
	public Spike() {
		this(0, 0, PuzzleUtil.SPIKE_WIDTH, PuzzleUtil.SPIKE_HEIGHT);
	}
	public Spike(int x, int y, int w, int h) {
		super(x,y,w,h);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		//600 vertical, 400 horizontal
		int sx = spikeCycle*400;
		int sy = spikeDir*600;
		
		gc.drawImage(SPIKE, sx, sy, 350, 600, x, y, w, h);
	}
	@Override
	//every frame, do this
	public void update(long millis) {
		advanceCycle(millis); //advance when button pushed
	}
	
	private void advanceCycle(long millis) {
		bounceFrames += 1;
		if (bounceFrames > 20) {
			bounceFrames = 0;
			spikeCycle = (spikeCycle == 3 ? 0 : spikeCycle + 1);
		}
	}
}
