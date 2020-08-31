import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class squarePlate extends Element{

	private final static Image pSPLATE = new Image(PuzzleUtil.FILE_PATH_RES+"purple_square_plate.png", false);
	private final static Image bSPLATE = new Image(PuzzleUtil.FILE_PATH_RES+"blue_square_plate.png", false);
	private final static Image oSPLATE = new Image(PuzzleUtil.FILE_PATH_RES+"orange_square_plate.png", false);
	private final static Image ySPLATE = new Image(PuzzleUtil.FILE_PATH_RES+"yellow_square_plate.png", false);
	
	private int color;
	
	public squarePlate(int x, int y, int w, int h, int c) {
		super(x,y,w,h);
		color = c;
		
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		
		switch(color) {
        //to draw purple plate
		case 0:
        	gc.drawImage(pSPLATE, x, y, 100, 100, x, y, w, h);
      	  break;
      	//to draw blue plate
        case 1:
        	gc.drawImage(bSPLATE, x, y, 100, 100, x, y, w, h);
      	  break;
      	//to draw orange plate
        case 2:
        	gc.drawImage(oSPLATE, x, y, 100, 100, x, y, w, h);
      	  break;
      	//to draw yellow plate
        case 3:
        	gc.drawImage(ySPLATE, x, y, 100, 100, x, y, w, h);
      	  break;
       
		}
	}
}