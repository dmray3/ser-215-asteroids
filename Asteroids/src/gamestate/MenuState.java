package gamestate;

import java.awt.Graphics2D;
import java.awt.event.*;

import main.GamePanel;
import tilemap.DebrisField;
import tilemap.Images;

public class MenuState extends GameState{
		
	// background images
	private Images mainbg;
	private DebrisField debrisField;
	
	// menu icons and selection
	private Images title;
	private Images[] start;
	private Images[] highScores;
	private Images[] exitGame;
	private int currentChoice;
	
	public MenuState(GameStateManager gsm){
		this.gsm = gsm;
		
		try {
			mainbg = new Images("/resources/backgrounds/mainbg.png");
			debrisField = new DebrisField();
			
			title = new Images("/resources/backgrounds/asteroidsTitle.png");
			title.setPosition((GamePanel.WIDTH / 2) - (title.getWidth() / 2), 125);
			start = new Images[] {new Images("/resources/backgrounds/startButton.png"),
								  new Images("/resources/backgrounds/startButtonFade.png")};
			start[0].setPosition((GamePanel.WIDTH / 2) - (start[0].getWidth() / 2), 275);
			start[1].setPosition((GamePanel.WIDTH / 2) - (start[1].getWidth() / 2), 275);
			highScores = new Images[] {new Images("/resources/backgrounds/hiscoreButton.png"),
									   new Images("/resources/backgrounds/hiscoreButtonFade.png")};
			highScores[0].setPosition((GamePanel.WIDTH / 2) - (highScores[0].getWidth() / 2), 325);
			highScores[1].setPosition((GamePanel.WIDTH / 2) - (highScores[1].getWidth() / 2), 325);
			exitGame = new Images[] {new Images("/resources/backgrounds/exitButton.png"),
									 new Images("/resources/backgrounds/exitButtonFade.png")};
			exitGame[0].setPosition((GamePanel.WIDTH / 2) - (exitGame[0].getWidth() / 2), 385);
			exitGame[1].setPosition((GamePanel.WIDTH / 2) - (exitGame[1].getWidth() / 2), 385);
			init();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void init() {
		currentChoice = 0;
	}
	
	public void selection(){
		if (currentChoice==0){
			gsm.setState(GameStateManager.LEVEL1STATE);
		}
		if (currentChoice==1){
			gsm.setState(GameStateManager.HIGHSCORESTATE);
		}
		if (currentChoice==2){
			System.exit(0);
		}
	}
	
	@Override
	public void update() {
		
		debrisField.update();
		
	}
	
	@Override
	public void draw(Graphics2D g) {
		
		mainbg.draw(g);//draw the background
		debrisField.draw(g);//draw debris field
		
		title.draw(g); // draw main icon
		
		//draw start button
		if (currentChoice == 0) start[0].draw(g);
		else start[1].draw(g);
		
		// draw high score button
		if (currentChoice == 1) highScores[0].draw(g);
		else highScores[1].draw(g);
		
		// draw quit button
		if (currentChoice == 2) exitGame[0].draw(g);
		else exitGame[1].draw(g);
	}
	
	@Override
	public void keyPressed(int k) {
		switch (k) {
			case KeyEvent.VK_UP:
				if (--currentChoice < 0) currentChoice = 2;
				break;
			case KeyEvent.VK_DOWN:
				if (++currentChoice > 2) currentChoice = 0;
				break;
			case KeyEvent.VK_ENTER:
				selection();
				System.out.println("You shouldn't see this. See menustate keyhandler if you can.");
				return;
		}
	}
	
	@Override
	public void keyReleased(int k) {}
	
}
