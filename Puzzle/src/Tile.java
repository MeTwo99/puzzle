import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class Tile extends Element
{
	public Tile(int x, int y, int w, int h) {
		super(x,y,w,h);
	}
	
	//the @Override annotation denotes that this function overrides the abstract function 
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.DARKGRAY);
		gc.fillRect(x, y, w, h);
		
		//details!
		gc.setFill(Color.DIMGREY);
		gc.fillRect(x+10, y+10, 5, 5);
		gc.fillRect(x+w-15, y+10, 5, 5);
		gc.fillRect(x+10, y+h-15, 5, 5);
		gc.fillRect(x+w-15, y+h-15, 5, 5);
	}
}