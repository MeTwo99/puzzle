import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Puzzle extends Application {
	public static final int MAX_WIDTH = 1000;
	public static final int MAX_HEIGHT = 1000;
	public static final String PATH = System.getProperty("user.dir");

	private LoadLevel levelCanvas;
	private ArrayList<Element> levelElements = new ArrayList<Element>();

	public static void main(String args[]) {
		Application.launch(args);
	}

	public void start(Stage stage) {
		FlowPane fp = new FlowPane();
		fp.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

		createLevelElements("level1");
		levelCanvas = new LoadLevel();
		fp.getChildren().add(levelCanvas);

		Scene scene = new Scene(fp, MAX_WIDTH, MAX_HEIGHT);
		stage.setScene(scene);
		stage.setTitle("Puzzle");
		stage.show();
	}

	private void createLevelElements(String levelName) {
		try {
			levelElements.clear();
	        Scanner scan = new Scanner(new File(PATH+"/src/levels/"+levelName));
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
			setWidth(MAX_WIDTH);
			setHeight(MAX_HEIGHT);
			draw(gc);
		}

		public void draw(GraphicsContext gc) {
			gc.setFill(Color.BLACK);
			gc.fillRect(0, 0, MAX_WIDTH, MAX_HEIGHT);

			// render each element in the level
			for (Element e : levelElements) {
				e.draw(gc);
			}
		}
	}
}