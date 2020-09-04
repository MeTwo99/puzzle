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
	public static final String SAVE_PATH = PATH+"/src/saves/";
	public static final int ZACK_WIDTH = 66;
	public static final int ZACK_HEIGHT = 100;
	public static final int JUKEBOX_WIDTH = 70;
	public static final int JUKEBOX_HEIGHT = 100;
	public static final int SPIKE_WIDTH = 50;
	public static final int SPIKE_HEIGHT = 50;
	public static final int SPLATE_WIDTH = 100;
	public static final int SPLATE_HEIGHT = 100;
	public static final int LAUNCHPAD_WIDTH = 100;
	public static final int LAUNCHPAD_HEIGHT = 100;
	public static final long NANO_IN_SEC = 1000000000;

	
	//images
	public static final Map<String, Image> TEXTURES = new HashMap<String, Image>(); 
	static {
		TEXTURES.put("checker", new Image(FILE_PATH_RES+"tile.png", false));
		TEXTURES.put("gray", new Image(FILE_PATH_RES+"gray.jpg", false));
		TEXTURES.put("brick", new Image(FILE_PATH_RES+"wall.jpg", false));
		TEXTURES.put("jukebox", new Image(FILE_PATH_RES+"jukeboxes.png", false));
		TEXTURES.put("none", null);
	}
	
	//checks if elements are overlapping
	public static boolean isCollision(Element e1, Element e2) {
		return isCollision(e1.getX(), e1.getY(), e1.getW(), e1.getH(), e2.getX(), e2.getY(), e2.getW(), e2.getH());
	} 
	public static boolean isCollision(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {		
		return x1 < x2 + w2 && x1 + w1 > x2 && y1 < y2 + h2 && y1 + h1 > y2;
	}

	//check if small element is fully on the large element
	public static boolean isOn(Element sm, Element lg) {
		return isOn(sm.getX(), sm.getY(), sm.getW(), sm.getH(), lg.getX(), lg.getY(), lg.getW(), lg.getH());
	} 
	public static boolean isOn(int xs, int ys, int ws, int hs, int xL, int yL, int wL, int hL) {	
		return xs >= xL && xs <= xL + ws && ys >= yL && ys <= yL + hs;
	}
	
	public static String getSaveData(ArrayList<Object> a) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < a.size(); i++) {
			sb.append(a.get(i));
			if (i != a.size()-1)
				sb.append(",");
		}
		return sb.toString();
	}
}