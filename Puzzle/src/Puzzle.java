import javafx.animation.AnimationTimer;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Puzzle extends Application {
	
	private LoadLevel levelCanvas;
	private ArrayList<Element> levelElements = new ArrayList<Element>();
	private Player zack = new Player();
	private Jukebox jb = new Jukebox();
	
	public static void main(String args[]) {
		Application.launch(args);
	}

	public void start(Stage stage) {
		FlowPane fp = new FlowPane();
		fp.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

		createLevelElements("level1");
		levelCanvas = new LoadLevel();
		fp.getChildren().add(levelCanvas);

		
		AnimationTimer ta = new GameTimer();
		ta.start();
		

		
		Scene scene = new Scene(fp, PuzzleUtil.MAX_WIDTH, PuzzleUtil.MAX_HEIGHT);
		scene.setOnKeyPressed(new KeyListenerDown(zack));
		stage.setScene(scene);
		stage.setTitle("Puzzle");
		stage.show();
	}

	private void createLevelElements(String levelName) {
		try {
			levelElements.clear();
	        Scanner scan = new Scanner(new File(PuzzleUtil.LEVEL_PATH+levelName));
	        while (scan.hasNextLine()) {
	          String l = scan.nextLine();
	          String[] data = l.split(",");
	          
	          int[] v = new int[data.length-1];
	          for(int i = 1; i < data.length; i++)
	        	  v[i-1] = Integer.parseInt(data[i]); 
	          
	          switch(data[0]) {
	          case "t":
	        	  levelElements.add(new Tile(v[0], v[1], v[2], v[3]));
	        	  break;
	          case "w":
	        	  levelElements.add(new Wall(v[0], v[1], v[2], v[3]));
	        	  break;
	          case "j":
	        	  levelElements.add(new Jukebox(v[0], v[1], v[2], v[3]));
	        	  break;
	          case "a":
	        	  levelElements.add(new Arrows(v[0], v[1], v[2], v[3]));
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
			
			//this.setOnKeyPressed(new KeyListenerDown(zack));
			//this.setOnKeyReleased(new KeyListenerUp());
			
			draw(gc);
		}
		
		public void draw() {
			this.draw(gc);
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
	
	public class GameTimer extends AnimationTimer{
		long prevTime = System.nanoTime();
		long sec = 0;
		int frame = 0;
		
		@Override
		//every frame, do this (60 FPS)
		public void handle(long millis) {
			sec += (millis - prevTime);
			frame += 1;
			if (sec > PuzzleUtil.NANO_IN_SEC) {
				sec -= PuzzleUtil.NANO_IN_SEC;
				System.out.println("FPS:"+frame);
				frame = 0;
			}
			prevTime = millis;
			
			for (Element e : levelElements) {
				e.update(millis);
			}
			
			zack.update(millis);
			
			levelCanvas.draw();
		}
	}
	
	public class KeyListenerDown implements EventHandler<KeyEvent> {
		private Player p;
		public KeyListenerDown(Player p) {
			this.p = p;
		}
		
		public void handle(KeyEvent event) {
			if(event.getCode() == KeyCode.UP) 
				p.setDirection(PuzzleUtil.Dir.UP);
			if(event.getCode() == KeyCode.DOWN) 
				p.setDirection(PuzzleUtil.Dir.DOWN);
			if(event.getCode() == KeyCode.LEFT) 
				p.setDirection(PuzzleUtil.Dir.LEFT);
			if(event.getCode() == KeyCode.RIGHT) 
				p.setDirection(PuzzleUtil.Dir.RIGHT);
		}
	}
}