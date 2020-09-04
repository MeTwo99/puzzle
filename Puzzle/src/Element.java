import javafx.scene.canvas.GraphicsContext;

abstract class Element
{
	protected int x;
	protected int y;
	protected int w;
	protected int h;
	
	public Element(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
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