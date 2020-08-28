import java.util.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

class PuzzleUtil{
	//constants
	public static final int MAX_WIDTH = 1000;
	public static final int MAX_HEIGHT = 1000;
	public static final String PATH = System.getProperty("user.dir");
	public static final String FILE_PATH_RES = "file:" + PATH + "/src/res/";
	public static final String LEVEL_PATH = PATH+"/src/levels/";
	public static final int ZACK_WIDTH = 66;
	public static final int ZACK_HEIGHT = 100;
	public static final int JUKEBOX_WIDTH = 70;
	public static final int JUKEBOX_HEIGHT = 100;
	public static final long NANO_IN_SEC = 1000000000;
	
	//images
	public static final Map<String, Image> TEXTURES = new HashMap<String, Image>(); 
	static {
		TEXTURES.put("checker", new Image(FILE_PATH_RES+"checker.jpg", false));
		TEXTURES.put("gray", new Image(FILE_PATH_RES+"gray.jpg", false));
		TEXTURES.put("brick", new Image(FILE_PATH_RES+"wall.jpg", false));
		TEXTURES.put("none", null);
	}
	
	public static void repeatImage(GraphicsContext gc, Image i, int x, int y, int w, int h) {
		if (i == null || gc == null)
			return;
		
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
	
	
	public static boolean isCollision(Element e1, Element e2) {
		return isCollision(e1.getX(), e1.getY(), e1.getW(), e1.getH(), e2.getX(), e2.getY(), e2.getW(), e2.getH());
	} 
	public static boolean isCollision(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {		
		return x1 < x2 + w2 && x1 + w1 > x2 && y1 < y2 + h2 && y1 + h1 > y2;
	} 
}