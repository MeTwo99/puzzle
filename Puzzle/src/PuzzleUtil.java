import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

class PuzzleUtil{
	//constants
	public static final int MAX_WIDTH = 1000;
	public static final int MAX_HEIGHT = 1000;
	public static final String PATH = System.getProperty("user.dir");
	public static final String FILE_PATH_RES = "file:" + PATH + "/src/res/";
	public static final int ZACK_WIDTH = 66;
	public static final int ZACK_HEIGHT = 100;
	
	
	public static void repeatImage(GraphicsContext gc, Image i, int x, int y, int w, int h) {
		double iw = i.getWidth();
		int rows = (int)Math.floor(w/iw);
		double remainderWidth = w - (rows*iw);
		double ih = i.getHeight();
		int cols = (int)Math.floor(h/ih);
		double remainderHeight = h - (cols*ih);
		
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++) {
				gc.drawImage(i, x+r*iw, y+c*ih);
			}
			gc.drawImage(i, x+r*iw, y+cols*ih, iw, remainderHeight);
		}
		for (int c = 0; c < cols; c++) {
			gc.drawImage(i, x+rows*iw, y+c*ih, remainderWidth, ih);
		}
		gc.drawImage(i, x+rows*iw, y+cols*ih, remainderWidth, remainderHeight);
	}
}