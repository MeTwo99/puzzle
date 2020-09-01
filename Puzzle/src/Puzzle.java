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
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundImage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Puzzle extends Application {
	
	private LoadLevel levelCanvas;
	private List<Element> levelElements = new ArrayList<Element>();
	private Player zack = new Player();
	private Stage applicationStage;
	private String currentLevel;
	private AnimationTimer ta = new GameTimer();
	private int fadeValue, fadeDirection, destinationX, destinationY;
	private String changingLevel = null;
	
	
	public static void main(String args[]) {
		Application.launch(args);
	}
	
	//MAIN MENU	
	public void start(Stage stage) {
		applicationStage = stage;
		//make the image	
		Image themenu = new Image(PuzzleUtil.FILE_PATH_RES+"puzzle_title.png", false);	
			
		//make it into a bg image
		BackgroundImage myImage = new BackgroundImage(themenu, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		
		//buttons
		Button start = new Button("Start");
		start.setPrefSize(260, 90);
		
		Button load = new Button("Load Game");
		load.setPrefSize(260, 90);
		
		//box for buttons to put at the bottom of bp
		HBox myButtons = new HBox();
		myButtons.getChildren().add(start);
		myButtons.getChildren().add(load);
		myButtons.setAlignment(Pos.CENTER);
		
		//border pane holds everything
		BorderPane bp = new BorderPane();
		bp.setBackground(new Background(myImage));
		bp.setPrefSize(1000, 1000);
		bp.setBottom(myButtons);
		//lift the buttons off the ground
		bp.setPadding(new Insets(0, 00, 100, 0));
		
		//make buttons work
	    start.setOnAction(new startHandler());
	
		//turn it on         
		Scene scene = new Scene(bp);
		stage.setScene(scene);
		stage.show();
	}
	
	
	 public class startHandler implements EventHandler<ActionEvent>{
	      public void handle (ActionEvent e){
	    	  startGameButton(applicationStage);    	  
	      }
	 }
	
	public void startGameButton(Stage stage){
		//delete all the save files

		File saveDir = new File(PuzzleUtil.SAVE_PATH);
		if (saveDir.exists()) {
			File saves[] = saveDir.listFiles();
			for (File f : saves) {
				f.delete();
			}
		} else 
			saveDir.mkdir();


		/*File saves[] = new File(PuzzleUtil.SAVE_PATH).listFiles();
		for (File f : saves) {
			f.delete();
		}*/

		
		FlowPane fp = new FlowPane();
		fp.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

		ta.start();
		
		saveAndLoadNextLevel("level1");
		levelCanvas = new LoadLevel();
		fp.getChildren().add(levelCanvas);
		
		Scene scene = new Scene(fp, PuzzleUtil.MAX_WIDTH, PuzzleUtil.MAX_HEIGHT);
		scene.setOnKeyPressed(new KeyListenerDown(zack));
		scene.setOnKeyReleased(new KeyListenerUp(zack));
		stage.setScene(scene);
		stage.setTitle("Puzzle");
		stage.show();
	}

	public void saveAndLoadNextLevel(String levelName) {
		try {
			//save the current level to save file
			if(currentLevel != null) {
				PrintWriter pw = new PrintWriter(new File(PuzzleUtil.SAVE_PATH+currentLevel+".save"));
				for(Element e : levelElements) {
					pw.println(e.toString());			
				}
				pw.close();
			}
			
			//if saved, load the saved level
			File saveFile = new File(PuzzleUtil.SAVE_PATH+levelName+".save");
			if (saveFile.exists())
				createLevelElements(saveFile);
			else //if no save, load the level file
				createLevelElements(new File(PuzzleUtil.LEVEL_PATH+levelName));
			
			currentLevel = levelName;
				
		} catch (FileNotFoundException fnfe) {
	        System.out.println("An error occurred.");
	        fnfe.printStackTrace();
	    }
	}

	private void createLevelElements(File levelFile) throws FileNotFoundException {
		fadeValue = 100;
		fadeDirection = -1;
		
		levelElements.clear();
        Scanner scan = new Scanner(levelFile);
        while (scan.hasNextLine()) {
          String l = scan.nextLine();
          String[] data = l.split(",");
          
          //the x,y,w,h
          int[] v = new int[4];
          for(int i = 0; i < 4; i++)
        	  v[i] = Integer.parseInt(data[i+1]); 
          
          switch(data[0]) {
          case "t":
        	  levelElements.add(new Tile(v[0], v[1], v[2], v[3], PuzzleUtil.TEXTURES.get(data[5])));
        	  break;
          case "w":
        	  levelElements.add(new Wall(v[0], v[1], v[2], v[3], PuzzleUtil.TEXTURES.get(data[5])));
        	  break;
          case "j":
        	  levelElements.add(new Jukebox(v[0], v[1], v[2], v[3]));
        	  break;
          case "a":
        	  Dir dir = new Dir(Integer.parseInt(data[5]));
        	  int dx = Integer.parseInt(data[7]), dy = Integer.parseInt(data[8]);
        	  levelElements.add(new Arrow(v[0], v[1], v[2], v[3], dir, data[6], dx, dy, this));
        	  break;
          case "sp":
        	  levelElements.add(new squarePlate(v[0], v[1], v[2], v[3],Integer.parseInt(data[5])));//last int is color
        	  break;
          case "cp":
        	  levelElements.add(new circlePlate(v[0], v[1], v[2], v[3],Integer.parseInt(data[5])));//last int is color
        	  break;
          }
        }
        scan.close();
	}

	public class LoadLevel extends Canvas {
		private GraphicsContext gc = getGraphicsContext2D();

		public LoadLevel() {
			setWidth(PuzzleUtil.MAX_WIDTH);
			setHeight(PuzzleUtil.MAX_HEIGHT);
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
			
			//fade screen
			if((fadeValue > 0 && fadeDirection == -1) || (fadeValue < 100 && fadeDirection == 1)) {
				fadeValue += fadeDirection;
				gc.setFill(new Color(0,0,0,(double)fadeValue/100));
				gc.fillRect(0, 0, PuzzleUtil.MAX_WIDTH, PuzzleUtil.MAX_HEIGHT);
			}
		}
	}	 
	
	public class GameTimer extends AnimationTimer{
		long prevTime = System.nanoTime();
		long sec = 0;
		int frame = 0;
		
		@Override
		public void handle(long millis) {
			sec += (millis - prevTime);
			frame += 1;
			if (sec > PuzzleUtil.NANO_IN_SEC) {
				sec -= PuzzleUtil.NANO_IN_SEC;
				System.out.println("FPS:"+frame);
				frame = 0;
			}
			prevTime = millis;
			//every frame, do this (60 FPS)
			
			int tempX = zack.getX(), tempY = zack.getY();
			
			//update all data
			for (Element e : levelElements) {
				e.update(millis);
			}
			if( fadeValue == 100 ) {
				fadeDirection = -1;
				saveAndLoadNextLevel(changingLevel);
				changingLevel = null;
				zack.goTo(destinationX, destinationY);
			}
			else if (changingLevel == null)
				zack.update(millis);
			
			Element zackHitbox = new Tile(zack.getX()+10, zack.getY()+50, zack.getW()-20, zack.getH()-60, null);
			//check collisions
			for (Element e : levelElements) {
				if (PuzzleUtil.isCollision(zackHitbox, e)) {
					e.onCollision(PuzzleUtil.isOn(zackHitbox,e));
					if (e instanceof Wall)
						zack.goTo(tempX, tempY);
				}
			}
			
			//draw the level
			levelCanvas.draw();
		}
	}
	
	public void setLevelDestination(String levelName, int x, int y) {
		changingLevel = levelName;
		fadeDirection = 1;
		destinationX = x;
		destinationY = y;
	}
	
	public class KeyListenerDown implements EventHandler<KeyEvent> {
		private Player p;
		public KeyListenerDown(Player p) {
			this.p = p;
		}
		
		public void handle(KeyEvent event) {
			if(event.getCode() == KeyCode.UP) 
				p.setDirection(Dir.UP);
			if(event.getCode() == KeyCode.DOWN) 
				p.setDirection(Dir.DOWN);
			if(event.getCode() == KeyCode.LEFT) 
				p.setDirection(Dir.LEFT);
			if(event.getCode() == KeyCode.RIGHT) 
				p.setDirection(Dir.RIGHT);
		}
	}
	public class KeyListenerUp implements EventHandler<KeyEvent> {
		private Player p;
		public KeyListenerUp(Player p) {
			this.p = p;
		}
		
		public void handle(KeyEvent event) {
			Dir d = p.getDir(); 
			if(event.getCode() == KeyCode.UP && d == Dir.UP) 
				p.setWalking(false);
			if(event.getCode() == KeyCode.DOWN && d == Dir.DOWN)
				p.setWalking(false);
			if(event.getCode() == KeyCode.LEFT && d == Dir.LEFT) 
				p.setWalking(false);
			if(event.getCode() == KeyCode.RIGHT && d == Dir.RIGHT)
				p.setWalking(false);
		}
	}
}