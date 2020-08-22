import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Puzzle extends Application {

	private static final Image ZACK = new Image("file:"+PuzzleUtil.PATH+"/res/zack2.jpg",false);
	
	private LoadLevel levelCanvas;
	private ArrayList<Element> levelElements = new ArrayList<Element>();
	private Player zack = new Player();
	
	public static void main(String args[]) {
		Application.launch(args);
	}

	public void start(Stage stage) {
		FlowPane fp = new FlowPane();
		fp.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

		createLevelElements("level1");
		levelCanvas = new LoadLevel();
		fp.getChildren().add(levelCanvas);

		Scene scene = new Scene(fp, PuzzleUtil.MAX_WIDTH, PuzzleUtil.MAX_HEIGHT);
		stage.setScene(scene);
		stage.setTitle("Puzzle");
		stage.show();
	}

	private void createLevelElements(String levelName) {
		try {
			levelElements.clear();
	        Scanner scan = new Scanner(new File(PuzzleUtil.PATH+"/src/levels/"+levelName));
	        while (scan.hasNextLine()) {
	          String l = scan.nextLine();
	          String[] data = l.split(",");
	          
	          int[] v = new int[data.length-1];
	          for(int i = 1; i < data.length; i++)
	        	  v[i-1] = Integer.parseInt(data[i]); 
	          
	          switch(data[0]) {
	          case "t":
	        	  levelElements.add(new Tile(v[0],v[1],v[2],v[3]));
	        	  break;
	          case "w":
	        	  levelElements.add(new Wall(v[0],v[1],v[2],v[3]));
	        	  break;
	          }
	        }
	        scan.close();
	      } catch (FileNotFoundException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	}

	public class LoadLevel extends Canvas {
		private GraphicsContext gc = getGraphicsContext2D();

		public LoadLevel() {
			setWidth(PuzzleUtil.MAX_WIDTH);
			setHeight(PuzzleUtil.MAX_HEIGHT);
			draw(gc);
		}

		public void draw(GraphicsContext gc) {
			gc.setFill(Color.BLACK);
			gc.fillRect(0, 0, PuzzleUtil.MAX_WIDTH, PuzzleUtil.MAX_HEIGHT);

			// render each element in the level
			for (Element e : levelElements) {
				e.draw(gc);
			}
			
			//render the player
			zack.draw(gc);
		}
	}	 
}