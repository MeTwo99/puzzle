import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

abstract class Element
{
	protected int x;
	protected int y;
	protected int w;
	protected int h;
	protected String c;
	protected char orientation;
	protected int status;
	protected Image i;
	
	public Element (int x, int y, int w, int h, String c, char orientation, int status) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.c = c;
		this.orientation = orientation;
		this.status = status;
	}
	
	public Element (int x, int y, int w, int h, String c) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.c = c;
	}
	
	public Element(int x, int y, int w, int h, Image i) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.i = i;
	}
	
	public Element(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		i = null;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getW() {
		return w;
	}
	public int getH() {
		return h;
	}
	
	public abstract void draw(GraphicsContext gc);
	public void update(long millis) {}
	public void onCollision(boolean isOn) {};
}