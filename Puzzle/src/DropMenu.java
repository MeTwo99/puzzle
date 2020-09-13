import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DropMenu{
	private static final int MENU_ITEM_WIDTH = 200;
	private static final int MENU_ITEM_HEIGHT = 50;
	private ArrayList<MenuItem> items = new ArrayList<MenuItem>();
	private final String [] itemNames = {"Restart Area", "Restart Level", "Save Game", "Load Game", "Quit"};
	private Puzzle puzzle;
	private int selected = 0;
	private boolean shown = false;
	
	//init with menu items
	public DropMenu(Puzzle p) {
		puzzle = p;
		for(int i = 0; i < itemNames.length; i++) {
			items.add(new MenuItem(itemNames[i], 0, i*MENU_ITEM_HEIGHT, this, puzzle));
		}
		highlightItem(selected);		
	}
	
	//set shown/hidden
	public void setShown(boolean s) {
		shown = s;
	}
	public boolean isShown() {
		return shown;
	}
	
	//change which menu item is highlighted
	public void highlightNext(int direction) {
		selected += direction;
		//bounds checking
		selected = selected > items.size()-1 ? 0 : selected;
		selected = selected < 0 ? items.size()-1 : selected;
		//highlight the new item
		highlightItem(selected);
	}

	//highlight a specific 
	public void setSelected(int index) {
		selected = index;
		highlightItem(selected);
	}	
	
	//do the action of the highlighted menu item
	public void selectCurrent() {
		items.get(selected).doAction();
	}
	
	//sets the cursor over the index item
	private void highlightItem(int index) {
		for(int i = 0; i < itemNames.length; i++) {
			items.get(i).setSelected(false);
		}
		items.get(index).setSelected(true);
	}

	//draw the whole menu
	public void draw(GraphicsContext gc) {
		for (int i = 0; i < items.size(); i++)
			items.get(i).draw(gc);
	}
	
	//define class MenuItem -- each item is drawn and can be selected and 
	private class MenuItem{
		private String name;
		private boolean isSelected;
		private int x,y;
		private Puzzle puzzle;
		private DropMenu menu;
		
		public MenuItem(String n, int x, int y, DropMenu dm, Puzzle p) {
			name = n;
			this.x = x;
			this.y = y;
			menu = dm;
			puzzle = p;
		}
		
		public void setSelected(boolean s) {
			isSelected = s;
		}
		
		public void draw(GraphicsContext gc) {
			if (isSelected)
				gc.setFill(Color.YELLOW);
			else
				gc.setFill(Color.DARKGRAY);
			gc.fillRect(x,y,MENU_ITEM_WIDTH,MENU_ITEM_HEIGHT);
			gc.setFill(Color.GRAY);
			gc.fillRect(x+10,y+10,MENU_ITEM_WIDTH-20,MENU_ITEM_HEIGHT-20);
			
			//draw the option name
			gc.setFont(Font.font("Helvetica", FontWeight.SEMI_BOLD, 25));
			gc.setFill(Color.WHITE);
			gc.fillText(name, x+15, y+MENU_ITEM_HEIGHT-15);
			
		}
		
		//Based on the name, we should call a function in the puzzle
		public void doAction() {
			switch(name) {
			case "Restart Area":
				puzzle.restartArea();
				break;
			case "Restart Level":
				puzzle.restartLevel();
				break;
			case "Save Game":
				puzzle.createLoadDir();
				break;
			case "Load Game":
				puzzle.loadGame();
				break;
			case "Quit":
				System.exit(0);
				break;
			}
			//close the options after a user makes a selection
			menu.setShown(false);
		}
	}


}