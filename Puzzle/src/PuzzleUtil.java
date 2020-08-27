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
	
	public enum Dir {DOWN, UP, LEFT, RIGHT, NONE;
	    public static int toInt(Dir d) {
	        switch (d) {
	            case DOWN:
	            	return 0;
	            case UP:
	            	return 1;
	            case LEFT:
	                return 2;           
	            case RIGHT:
	            	return 3;
	        }
	        return -1;
	    }
	    public static Dir toDir(int i) {
	        switch (i) {
	            case 0:
	            	return DOWN;
	            case 1:
	            	return UP;
	            case 2:
	                return LEFT;           
	            case 3:
	            	return RIGHT;
	        }
	        return NONE;
	    }
    }
	
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