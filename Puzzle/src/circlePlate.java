import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class circlePlate extends Element{

	private final static Image pCPLATE = new Image(PuzzleUtil.FILE_PATH_RES+"purple_circle_plate.png", false);
	private final static Image bCPLATE = new Image(PuzzleUtil.FILE_PATH_RES+"blue_cirlce_plate.png", false);
	private final static Image oCPLATE = new Image(PuzzleUtil.FILE_PATH_RES+"orange_circle_plate.png", false);
	private final static Image yCPLATE = new Image(PuzzleUtil.FILE_PATH_RES+"yellow_circle_plate.png", false);
	
	private int color;
	
	public circlePlate(int x, int y, int w, int h, int c) {
		super(x,y,w,h);
		color = c;
		
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		
		switch(color) {
        //to draw purple plate
		case 0:
        	gc.drawImage(pCPLATE, x, y, 100, 100, x, y, w, h);
      	  break;
      	//to draw blue plate
        case 1:
        	gc.drawImage(bCPLATE, x, y, 100, 100, x, y, w, h);
      	  break;
      	//to draw orange plate
        case 2:
        	gc.drawImage(oCPLATE, x, y, 100, 100, x, y, w, h);
      	  break;
      	//to draw yellow plate
        case 3:
        	gc.drawImage(yCPLATE, x, y, 100, 100, x, y, w, h);
      	  break;
       
		}
	}
}