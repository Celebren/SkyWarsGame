package skyWars;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This class displays all graphical interface elements and 
	 * communicates with the game logic via the GameLogic class
	 *  
	 */
	private static JPanel contentPane;

	private final static int NUMBER_OF_TILES = 16;

	private final static String SKY_BACKGROUND = "/images/space.jpg";

	private final static String MASTER_SHIP_DEFENSIVE = "/images/cobra_defensive.png";
	private final static String MASTER_SHIP_OFFENSIVE = "/images/cobra_offensive.png";
	private final static String MASTER_SHIP_ICON = "/images/cobraIcon.png";

	private final static String BATTLE_STAR = "/images/fighterSmall.png";
	private final static String BATTLE_CRUISER = "/images/corvetteSmall.png";
	private final static String BATTLE_SHOOTER = "/images/gunshipSmall.png";

	private final static String MASTER_SHIP_EXPLOSION = "/images/cobraExplosion.png";
	private final static String BATTLE_STAR_EXPLOSION = "/images/fighterExplosion.png";
	private final static String BATTLE_CRUISER_EXPLOSION = "/images/corvetteExplosion.png";
	private final static String BATTLE_SHOOTER_EXPLOSION = "/images/gunshipExplosion.png";

	private final static ImageIcon ICON = new ImageIcon(GUI.class.getResource(MASTER_SHIP_ICON));
	
	private final static String EXPLOSION = "/images/explosion.png";

	private static String masterShipIcon = MASTER_SHIP_DEFENSIVE;
	private static int whoExploded = 0;

	private static ArrayList<JLabel> listOfTiles = new ArrayList<JLabel>();
	private static ArrayList<JLabel> listOfStarTiles = new ArrayList<JLabel>();
	private static ArrayList<JLabel> listOfCruiserTiles = new ArrayList<JLabel>();
	private static ArrayList<JLabel> listOfShooterTiles = new ArrayList<JLabel>();

	private static ArrayList<JLabel> scoreAndHighScore = new ArrayList<JLabel>();

	private static GameLogic gameLogic = new GameLogic();
	private static SaveLoad saveLoadLogic = new SaveLoad();
	private static Scores scores = new Scores();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
					frame.setResizable(false);

					introDialog(frame);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource(MASTER_SHIP_ICON)));

		setTitle("Sky Wars Game");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	try {
					exitGame(0);
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		});
		setBounds(100, 100, 800, 600);

		// register Observers to list of observers
		gameLogic.registerObserver(scores);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);
		
		// RESET GAME ---------------------------------------------------------------
		JMenuItem mntmRestart = new JMenuItem("Restart");
		mntmRestart.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				resetButton(0);				
			}
		});
		mnGame.add(mntmRestart);	
		
		// SAVE GAME ---------------------------------------------------------------
		JMenuItem mntmSaveGame = new JMenuItem("Save Game");
		mntmSaveGame.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					saveLoadLogic.serializeGameLogicState(gameLogic);
				} catch (IOException e1) {					
					e1.printStackTrace();
				}			
			}
		});
		mnGame.add(mntmSaveGame);
		
		// LOAD GAME ---------------------------------------------------------------
		JMenuItem mntmLoadGame = new JMenuItem("Load Game");
		mntmLoadGame.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("load clicked");
				File tmpDir = new File("SavedGame.txt");
				if (!tmpDir.exists()) {
					JOptionPane.showMessageDialog(contentPane, "SaveGame.txt not found.\nSelect \"New Game\" instead.", "Save Not Found", JOptionPane.ERROR_MESSAGE);
				} else {
					resetButton(1);
					try {
						gameLogic = new GameLogic();
						gameLogic = saveLoadLogic.deserializeGameLogicState();
					} catch (ClassNotFoundException e1) {						
						e1.printStackTrace();
					} catch (IOException e1) {						
						e1.printStackTrace();
					}
					drawMasterShip();
					drawMovedEnemies();
					try {
						gameLogic.setHighScore(saveLoadLogic.loadHighScore());
					} catch (NumberFormatException | IOException e1) {
						e1.printStackTrace();
					}
					updateScores();
				}			
			}
		});
		mnGame.add(mntmLoadGame);
		
		// EXIT GAME ---------------------------------------------------------------
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					exitGame(0);
				} catch (IOException e1) {
					e1.printStackTrace();
				}	
			}
		});
		mnGame.add(mntmExit);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// create and display all initial GUI elements
		createJLabelTiles(contentPane);
		
		
		// MOVE SHIPS BUTTON ---------------------------------------------------------------
		JButton btnMove = new JButton("MOVE SHIPS");
		btnMove.setBackground(Color.BLACK);
		btnMove.setForeground(Color.ORANGE);
		btnMove.setFont(new Font("Agency FB", Font.BOLD, 15));
		btnMove.setBounds(465, 433, 120, 80);
		contentPane.add(btnMove);
		btnMove.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				clearTiles();

				gameLogic.moveMasterShip();
				drawMasterShip();

				gameLogic.moveEnemyShips();
				drawMovedEnemies();

				int spawnEnemy = gameLogic.enemyShipSpawn();
				if (spawnEnemy > 0) {
					int enemyType = gameLogic.getEnemyType();
					drawSpawnedEnemies(spawnEnemy, enemyType);
					enemyType = 0;
				}

				// check for conflict resolution
				whoExploded = gameLogic.conflictResolution();

				// display exposion
				if (whoExploded > 0) {							
					displayExplosion(whoExploded);
				}
				
				// notify observers that the scores have been updated
				gameLogic.notifyObservers();
				
				// update scores display
				updateScores();
				

				// check for game over state
				if (whoExploded == 1) {
					gameOverDialog();
				}
				whoExploded = 0;
			}
		});
		
		// CHANGE MODE BUTTON ---------------------------------------------------------------
		JButton btnChangeMode = new JButton("CHANGE MODE");
		btnChangeMode.setBackground(Color.BLACK);
		btnChangeMode.setForeground(Color.ORANGE);
		btnChangeMode.setFont(new Font("Agency FB", Font.BOLD, 15));
		btnChangeMode.setEnabled(true);
		btnChangeMode.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {				
				changeModes();
			}
		});

		btnChangeMode.setBounds(598, 433, 120, 80);
		contentPane.add(btnChangeMode);

		JLabel lblBackGround = new JLabel("");
		lblBackGround.setFont(new Font("Agency FB", Font.BOLD, 12));
		lblBackGround.setIcon(new ImageIcon(GUI.class.getResource(SKY_BACKGROUND)));
		lblBackGround.setBounds(0, 0, 800, 600);
		contentPane.add(lblBackGround);

	} // end GUI()
	
	/****************************************************************************
	 ****************************************************************************
	 * GUI helper methods														*
	 **************************************************************************** 
	 ****************************************************************************/
	
	// intro dialog
	private static void introDialog(GUI frame) throws ClassNotFoundException, IOException {
		
		Object[] options1 = {"New Game", "Load Game", "Quit Game"};

		int selection1 = JOptionPane.showOptionDialog(frame, "Welcome to Sky Wars Game", "Sky Wars Game", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, ICON, options1, options1[0]);

		if (selection1 == 0) { // New Game
			GUI.resetButton(0);
		} else if (selection1 == 1){
			File tmpDir = new File("SavedGame.txt");
			if (!tmpDir.exists()) {
				saveFileIsMissingDialog(frame);
			} else { // Load Game
				GUI.resetButton(1);
				gameLogic = saveLoadLogic.deserializeGameLogicState();
				
				if (gameLogic.getMasterMode() == 1) {
					masterShipIcon = MASTER_SHIP_OFFENSIVE;
				}
				
				GUI.drawMasterShip();
				frame.drawMovedEnemies();
				gameLogic.setHighScore(saveLoadLogic.loadHighScore());
				GUI.updateScores();
			} 
		} else { // Quit Game
			exitGame(frame);
		}
	}
	
	// missing save error dialog
	private static void saveFileIsMissingDialog(GUI frame) throws ClassNotFoundException, IOException {
		Object[] options2 = {"Ok"};
		int selection2 = JOptionPane.showOptionDialog(frame, "SaveGame.txt not found.\nSelect \"New Game\" instead.", "Save Not Found", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, options2, options2[0]);
		if (selection2 == 0 || selection2 == -1) {
			introDialog(frame);
		}
	}
	
	// game over dialog
	private static void gameOverDialog() {
		final ImageIcon icon = new ImageIcon(GUI.class.getResource(EXPLOSION));
		Object[] options = {"New Game","Quit Game"};

		String output = "Master Space Ship destroyed!!!\n\nGAME OVER\n\nYour final score: " + gameLogic.getScore() + "\n\n";

		int selection = JOptionPane.showOptionDialog(contentPane, output, "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, icon, options, options[0]);
		if (selection == 0) {
			resetButton(0);
		} else {
			try {
				exitGame(1);
			} catch (IOException e1) {							
				e1.printStackTrace();
			}
		}	
	}
	

	// reset tiles method
	private static void resetTiles() {
		gameLogic.setMasterShipTile(null);
		gameLogic.setBattleStarTile(null);
		gameLogic.setBattleCruiserTile(null);
		gameLogic.setBattleShooterTile(null);

		clearTiles();
	} // end reset tiles 

	// clear tiles method without clearing the ships
	private static void clearTiles() {			
		for (JLabel label : listOfTiles) {
			label.setIcon(null);
		}
		for (JLabel label : listOfStarTiles) {
			label.setIcon(null);
		}
		for (JLabel label : listOfCruiserTiles) {
			label.setIcon(null);
		}
		for (JLabel label : listOfShooterTiles) {
			label.setIcon(null);
		}
	} // end clear tiles

	// method for drawing master ship
	private static void drawMasterShip() {

		if (gameLogic.getMasterShipTile() != null) {
			for (int i = 0; i < NUMBER_OF_TILES; i++) {
				if (gameLogic.getMasterShipTile().getTileId()-1 == i) {
					listOfTiles.get(i).setIcon(new ImageIcon(GUI.class.getResource(masterShipIcon)));
				}					
			}
		}
	} // end drawMasterShop()

	// method for drawing enemy ships
	private static void drawSpawnedEnemies(int tileNumber, int enemyType) {
		for (int i = 0; i < NUMBER_OF_TILES; i++) {
			if (tileNumber-1 == i) {
				switch (enemyType) {
				case 1: listOfStarTiles.get(i).setIcon(new ImageIcon(GUI.class.getResource(BATTLE_STAR))); break;
				case 2: listOfCruiserTiles.get(i).setIcon(new ImageIcon(GUI.class.getResource(BATTLE_CRUISER))); break;
				case 3: listOfShooterTiles.get(i).setIcon(new ImageIcon(GUI.class.getResource(BATTLE_SHOOTER))); break;
				}

			}
		}
	} // end drawSpawnedEnemies()

	private void drawMovedEnemies() {
		if (gameLogic.getBattleStarTile() != null) {
			for (int i = 0; i < NUMBER_OF_TILES; i++) {
				if (gameLogic.getBattleStarTile().getTileId()-1 == i) {
					listOfStarTiles.get(i).setIcon(new ImageIcon(GUI.class.getResource(BATTLE_STAR)));
				}					
			}
		}
		if (gameLogic.getBattleCruiserTile() != null) {
			for (int i = 0; i < NUMBER_OF_TILES; i++) {
				if (gameLogic.getBattleCruiserTile().getTileId()-1 == i) {
					listOfCruiserTiles.get(i).setIcon(new ImageIcon(GUI.class.getResource(BATTLE_CRUISER)));
				}					
			}
		}
		if (gameLogic.getBattleShooterTile() != null) {
			for (int i = 0; i < NUMBER_OF_TILES; i++) {
				if (gameLogic.getBattleShooterTile().getTileId()-1 == i) {
					listOfShooterTiles.get(i).setIcon(new ImageIcon(GUI.class.getResource(BATTLE_SHOOTER)));
				}					
			}
		}
	} // end drawMovedEnemies()

	//displayExplosion()
	private void displayExplosion(int whoExploded) {
		switch (whoExploded) {
		case 1: listOfTiles.get(gameLogic.getMasterShipTile().getTileId()-1).setIcon(new ImageIcon(GUI.class.getResource(MASTER_SHIP_EXPLOSION))); break;
		case 2: listOfStarTiles.get(gameLogic.getMasterShipTile().getTileId()-1).setIcon(new ImageIcon(GUI.class.getResource(BATTLE_STAR_EXPLOSION))); break;
		case 3: listOfCruiserTiles.get(gameLogic.getMasterShipTile().getTileId()-1).setIcon(new ImageIcon(GUI.class.getResource(BATTLE_CRUISER_EXPLOSION))); break;
		case 4: listOfShooterTiles.get(gameLogic.getMasterShipTile().getTileId()-1).setIcon(new ImageIcon(GUI.class.getResource(BATTLE_SHOOTER_EXPLOSION))); break;
		default: break;

		}
	} // displayExplosion

	//swap betweenModes
	private void changeModes() {
		if (gameLogic.getMasterMode() == 0) {
			gameLogic.setMasterMode(1);
			masterShipIcon = MASTER_SHIP_OFFENSIVE;
		} else if (gameLogic.getMasterMode() == 1) {
			gameLogic.setMasterMode(0);
			masterShipIcon = MASTER_SHIP_DEFENSIVE;
		}
		drawMasterShip();
	}

	private static void resetButton(int gameHasBeenLoaded) {
		System.out.println("---------------------------------------------------\nreset clicked");

		// reset score
		gameLogic.setScore(0);
		updateScores();
		resetTiles();

		for (GameTile t : gameLogic.getListOfTiles()) {
			t.getListOfShipsOnTile().clear();
		}
		
		if (gameHasBeenLoaded == 0) {
			gameLogic.setMasterMode(0);
			masterShipIcon = MASTER_SHIP_DEFENSIVE;
	
			gameLogic.masterShipSpawn();
			
			drawMasterShip();
			
			int spawnEnemy = gameLogic.enemyShipSpawn();
			int enemyType = gameLogic.getEnemyType();
			drawSpawnedEnemies(spawnEnemy, enemyType);
			
			enemyType = 0;
		}
	} // end resetbutton

	// display scores
	private static void updateScores() {
		scoreAndHighScore.get(0).setText(Integer.toString(gameLogic.getScore()));
		
		scoreAndHighScore.get(1).setText(Integer.toString(gameLogic.getHighScore()));
	}
	
	// exit game
	private static void exitGame(int gameOver) throws IOException {
		if (JOptionPane.showConfirmDialog(contentPane, 
				"Are you sure to quit?", "Sky Wars Game", 
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
			saveLoadLogic.saveHighScore(gameLogic.getHighScore());
			System.exit(0);
		}
		if  (gameOver == 1) {
			gameOverDialog();
		}
		
	}
	private static void exitGame(GUI frame) throws ClassNotFoundException, IOException {
		if (JOptionPane.showConfirmDialog(contentPane, 
				"Are you sure to quit?", "", 
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
			
			saveLoadLogic.saveHighScore(gameLogic.getHighScore());

			System.exit(0);
			
		} else {
			introDialog(frame);
		}
		
	}
		
	private void createJLabelTiles(JPanel contentPane) {
		Border border = LineBorder.createGrayLineBorder();
		
		JLabel lblTile1 = new JLabel("");
		lblTile1.setVerticalAlignment(SwingConstants.TOP);
		lblTile1.setForeground(Color.CYAN);
		lblTile1.setBackground(Color.DARK_GRAY);
		lblTile1.setBounds(35, 100, 100, 100);
		lblTile1.setBorder(border);
		contentPane.add(lblTile1);

		JLabel lblTile2 = new JLabel("");
		lblTile2.setVerticalAlignment(SwingConstants.TOP);
		lblTile2.setForeground(Color.CYAN);
		lblTile2.setBackground(Color.DARK_GRAY);
		lblTile2.setBounds(134, 100, 100, 100);
		lblTile2.setBorder(border);
		contentPane.add(lblTile2);

		JLabel lblTile3 = new JLabel("");
		lblTile3.setVerticalAlignment(SwingConstants.TOP);
		lblTile3.setForeground(Color.CYAN);
		lblTile3.setBackground(Color.DARK_GRAY);
		lblTile3.setBounds(234, 100, 100, 100);
		lblTile3.setBorder(border);
		contentPane.add(lblTile3);

		JLabel lblTile4 = new JLabel("");
		lblTile4.setVerticalAlignment(SwingConstants.TOP);
		lblTile4.setForeground(Color.CYAN);
		lblTile4.setBackground(Color.DARK_GRAY);
		lblTile4.setBounds(333, 100, 100, 100);
		lblTile4.setBorder(border);
		contentPane.add(lblTile4);

		JLabel lblTile5 = new JLabel("");
		lblTile5.setVerticalAlignment(SwingConstants.TOP);
		lblTile5.setForeground(Color.CYAN);
		lblTile5.setBackground(Color.DARK_GRAY);
		lblTile5.setBounds(35, 200, 100, 100);
		lblTile5.setBorder(border);
		contentPane.add(lblTile5);

		JLabel lblTile6 = new JLabel("");
		lblTile6.setVerticalAlignment(SwingConstants.TOP);
		lblTile6.setForeground(Color.CYAN);
		lblTile6.setBackground(Color.DARK_GRAY);
		lblTile6.setBounds(134, 200, 100, 100);
		lblTile6.setBorder(border);
		contentPane.add(lblTile6);

		JLabel lblTile7 = new JLabel("");
		lblTile7.setVerticalAlignment(SwingConstants.TOP);
		lblTile7.setForeground(Color.CYAN);
		lblTile7.setBackground(Color.DARK_GRAY);
		lblTile7.setBounds(234, 200, 100, 100);
		lblTile7.setBorder(border);
		contentPane.add(lblTile7);

		JLabel lblTile8 = new JLabel("");
		lblTile8.setVerticalAlignment(SwingConstants.TOP);
		lblTile8.setForeground(Color.CYAN);
		lblTile8.setBackground(Color.DARK_GRAY);
		lblTile8.setBounds(333, 200, 100, 100);
		lblTile8.setBorder(border);
		contentPane.add(lblTile8);

		JLabel lblTile9 = new JLabel("");
		lblTile9.setVerticalAlignment(SwingConstants.TOP);
		lblTile9.setForeground(Color.CYAN);
		lblTile9.setBackground(Color.DARK_GRAY);
		lblTile9.setBounds(35, 300, 100, 100);
		lblTile9.setBorder(border);
		contentPane.add(lblTile9);

		JLabel lblTile10 = new JLabel("");
		lblTile10.setVerticalAlignment(SwingConstants.TOP);
		lblTile10.setForeground(Color.CYAN);
		lblTile10.setBackground(Color.DARK_GRAY);
		lblTile10.setBounds(134, 300, 100, 100);
		lblTile10.setBorder(border);
		contentPane.add(lblTile10);

		JLabel lblTile11 = new JLabel("");
		lblTile11.setVerticalAlignment(SwingConstants.TOP);
		lblTile11.setForeground(Color.CYAN);
		lblTile11.setBackground(Color.DARK_GRAY);
		lblTile11.setBounds(234, 300, 100, 100);
		lblTile11.setBorder(border);
		contentPane.add(lblTile11);

		JLabel lblTile12 = new JLabel("");
		lblTile12.setVerticalAlignment(SwingConstants.TOP);
		lblTile12.setForeground(Color.CYAN);
		lblTile12.setBackground(Color.DARK_GRAY);
		lblTile12.setBounds(333, 300, 100, 100);
		lblTile12.setBorder(border);
		contentPane.add(lblTile12);

		JLabel lblTile13 = new JLabel("");
		lblTile13.setVerticalAlignment(SwingConstants.TOP);
		lblTile13.setForeground(Color.CYAN);
		lblTile13.setBackground(Color.DARK_GRAY);
		lblTile13.setBounds(35, 399, 100, 100);
		lblTile13.setBorder(border);
		contentPane.add(lblTile13);

		JLabel lblTile14 = new JLabel("");
		lblTile14.setVerticalAlignment(SwingConstants.TOP);
		lblTile14.setForeground(Color.CYAN);
		lblTile14.setBackground(Color.DARK_GRAY);
		lblTile14.setBounds(134, 399, 100, 100);
		lblTile14.setBorder(border);
		contentPane.add(lblTile14);

		JLabel lblTile15 = new JLabel("");
		lblTile15.setVerticalAlignment(SwingConstants.TOP);
		lblTile15.setForeground(Color.CYAN);
		lblTile15.setBackground(Color.DARK_GRAY);
		lblTile15.setBounds(234, 399, 100, 100);
		lblTile15.setBorder(border);
		contentPane.add(lblTile15);

		JLabel lblTile16 = new JLabel("");
		lblTile16.setVerticalAlignment(SwingConstants.TOP);
		lblTile16.setForeground(Color.CYAN);
		lblTile16.setBackground(Color.DARK_GRAY);
		lblTile16.setBounds(333, 399, 100, 100);
		lblTile16.setBorder(border);
		contentPane.add(lblTile16);

		JLabel starlblTile1 = new JLabel("");
		starlblTile1.setVerticalAlignment(SwingConstants.TOP);
		starlblTile1.setForeground(Color.CYAN);
		starlblTile1.setBackground(Color.DARK_GRAY);
		starlblTile1.setBounds(35, 100, 100, 100);
		starlblTile1.setBorder(border);
		contentPane.add(starlblTile1);

		JLabel starlblTile2 = new JLabel("");
		starlblTile2.setVerticalAlignment(SwingConstants.TOP);
		starlblTile2.setForeground(Color.CYAN);
		starlblTile2.setBackground(Color.DARK_GRAY);
		starlblTile2.setBounds(134, 100, 100, 100);
		starlblTile2.setBorder(border);
		contentPane.add(starlblTile2);

		JLabel starlblTile3 = new JLabel("");
		starlblTile3.setVerticalAlignment(SwingConstants.TOP);
		starlblTile3.setForeground(Color.CYAN);
		starlblTile3.setBackground(Color.DARK_GRAY);
		starlblTile3.setBounds(234, 100, 100, 100);
		starlblTile3.setBorder(border);
		contentPane.add(starlblTile3);

		JLabel starlblTile4 = new JLabel("");
		starlblTile4.setVerticalAlignment(SwingConstants.TOP);
		starlblTile4.setForeground(Color.CYAN);
		starlblTile4.setBackground(Color.DARK_GRAY);
		starlblTile4.setBounds(333, 100, 100, 100);
		starlblTile4.setBorder(border);
		contentPane.add(starlblTile4);

		JLabel starlblTile5 = new JLabel("");
		starlblTile5.setVerticalAlignment(SwingConstants.TOP);
		starlblTile5.setForeground(Color.CYAN);
		starlblTile5.setBackground(Color.DARK_GRAY);
		starlblTile5.setBounds(35, 200, 100, 100);
		starlblTile5.setBorder(border);
		contentPane.add(starlblTile5);

		JLabel starlblTile6 = new JLabel("");
		starlblTile6.setVerticalAlignment(SwingConstants.TOP);
		starlblTile6.setForeground(Color.CYAN);
		starlblTile6.setBackground(Color.DARK_GRAY);
		starlblTile6.setBounds(134, 200, 100, 100);
		starlblTile6.setBorder(border);
		contentPane.add(starlblTile6);

		JLabel starlblTile7 = new JLabel("");
		starlblTile7.setVerticalAlignment(SwingConstants.TOP);
		starlblTile7.setForeground(Color.CYAN);
		starlblTile7.setBackground(Color.DARK_GRAY);
		starlblTile7.setBounds(234, 200, 100, 100);
		starlblTile7.setBorder(border);
		contentPane.add(starlblTile7);

		JLabel starlblTile8 = new JLabel("");
		starlblTile8.setVerticalAlignment(SwingConstants.TOP);
		starlblTile8.setForeground(Color.CYAN);
		starlblTile8.setBackground(Color.DARK_GRAY);
		starlblTile8.setBounds(333, 200, 100, 100);
		starlblTile8.setBorder(border);
		contentPane.add(starlblTile8);

		JLabel starlblTile9 = new JLabel("");
		starlblTile9.setVerticalAlignment(SwingConstants.TOP);
		starlblTile9.setForeground(Color.CYAN);
		starlblTile9.setBackground(Color.DARK_GRAY);
		starlblTile9.setBounds(35, 300, 100, 100);
		starlblTile9.setBorder(border);
		contentPane.add(starlblTile9);

		JLabel starlblTile10 = new JLabel("");
		starlblTile10.setVerticalAlignment(SwingConstants.TOP);
		starlblTile10.setForeground(Color.CYAN);
		starlblTile10.setBackground(Color.DARK_GRAY);
		starlblTile10.setBounds(134, 300, 100, 100);
		starlblTile10.setBorder(border);
		contentPane.add(starlblTile10);

		JLabel starlblTile11 = new JLabel("");
		starlblTile11.setVerticalAlignment(SwingConstants.TOP);
		starlblTile11.setForeground(Color.CYAN);
		starlblTile11.setBackground(Color.DARK_GRAY);
		starlblTile11.setBounds(234, 300, 100, 100);
		starlblTile11.setBorder(border);
		contentPane.add(starlblTile11);

		JLabel starlblTile12 = new JLabel("");
		starlblTile12.setVerticalAlignment(SwingConstants.TOP);
		starlblTile12.setForeground(Color.CYAN);
		starlblTile12.setBackground(Color.DARK_GRAY);
		starlblTile12.setBounds(333, 300, 100, 100);
		starlblTile12.setBorder(border);
		contentPane.add(starlblTile12);

		JLabel starlblTile13 = new JLabel("");
		starlblTile13.setVerticalAlignment(SwingConstants.TOP);
		starlblTile13.setForeground(Color.CYAN);
		starlblTile13.setBackground(Color.DARK_GRAY);
		starlblTile13.setBounds(35, 399, 100, 100);
		starlblTile13.setBorder(border);
		contentPane.add(starlblTile13);

		JLabel starlblTile14 = new JLabel("");
		starlblTile14.setVerticalAlignment(SwingConstants.TOP);
		starlblTile14.setForeground(Color.CYAN);
		starlblTile14.setBackground(Color.DARK_GRAY);
		starlblTile14.setBounds(134, 399, 100, 100);
		starlblTile14.setBorder(border);
		contentPane.add(starlblTile14);

		JLabel starlblTile15 = new JLabel("");
		starlblTile15.setVerticalAlignment(SwingConstants.TOP);
		starlblTile15.setForeground(Color.CYAN);
		starlblTile15.setBackground(Color.DARK_GRAY);
		starlblTile15.setBounds(234, 399, 100, 100);
		starlblTile15.setBorder(border);
		contentPane.add(starlblTile15);

		JLabel starlblTile16 = new JLabel("");
		starlblTile16.setVerticalAlignment(SwingConstants.TOP);
		starlblTile16.setForeground(Color.CYAN);
		starlblTile16.setBackground(Color.DARK_GRAY);
		starlblTile16.setBounds(333, 399, 100, 100);
		starlblTile16.setBorder(border);
		contentPane.add(starlblTile16);

		JLabel cruiserlblTile1 = new JLabel("");
		cruiserlblTile1.setVerticalAlignment(SwingConstants.TOP);
		cruiserlblTile1.setForeground(Color.CYAN);
		cruiserlblTile1.setBackground(Color.DARK_GRAY);
		cruiserlblTile1.setBounds(35, 100, 100, 100);
		cruiserlblTile1.setBorder(border);
		contentPane.add(cruiserlblTile1);

		JLabel cruiserlblTile2 = new JLabel("");
		cruiserlblTile2.setVerticalAlignment(SwingConstants.TOP);
		cruiserlblTile2.setForeground(Color.CYAN);
		cruiserlblTile2.setBackground(Color.DARK_GRAY);
		cruiserlblTile2.setBounds(134, 100, 100, 100);
		cruiserlblTile2.setBorder(border);
		contentPane.add(cruiserlblTile2);

		JLabel cruiserlblTile3 = new JLabel("");
		cruiserlblTile3.setVerticalAlignment(SwingConstants.TOP);
		cruiserlblTile3.setForeground(Color.CYAN);
		cruiserlblTile3.setBackground(Color.DARK_GRAY);
		cruiserlblTile3.setBounds(234, 100, 100, 100);
		cruiserlblTile3.setBorder(border);
		contentPane.add(cruiserlblTile3);

		JLabel cruiserlblTile4 = new JLabel("");
		cruiserlblTile4.setVerticalAlignment(SwingConstants.TOP);
		cruiserlblTile4.setForeground(Color.CYAN);
		cruiserlblTile4.setBackground(Color.DARK_GRAY);
		cruiserlblTile4.setBounds(333, 100, 100, 100);
		cruiserlblTile4.setBorder(border);
		contentPane.add(cruiserlblTile4);

		JLabel cruiserlblTile5 = new JLabel("");
		cruiserlblTile5.setVerticalAlignment(SwingConstants.TOP);
		cruiserlblTile5.setForeground(Color.CYAN);
		cruiserlblTile5.setBackground(Color.DARK_GRAY);
		cruiserlblTile5.setBounds(35, 200, 100, 100);
		cruiserlblTile5.setBorder(border);
		contentPane.add(cruiserlblTile5);

		JLabel cruiserlblTile6 = new JLabel("");
		cruiserlblTile6.setVerticalAlignment(SwingConstants.TOP);
		cruiserlblTile6.setForeground(Color.CYAN);
		cruiserlblTile6.setBackground(Color.DARK_GRAY);
		cruiserlblTile6.setBounds(134, 200, 100, 100);
		cruiserlblTile6.setBorder(border);
		contentPane.add(cruiserlblTile6);

		JLabel cruiserlblTile7 = new JLabel("");
		cruiserlblTile7.setVerticalAlignment(SwingConstants.TOP);
		cruiserlblTile7.setForeground(Color.CYAN);
		cruiserlblTile7.setBackground(Color.DARK_GRAY);
		cruiserlblTile7.setBounds(234, 200, 100, 100);
		cruiserlblTile7.setBorder(border);
		contentPane.add(cruiserlblTile7);

		JLabel cruiserlblTile8 = new JLabel("");
		cruiserlblTile8.setVerticalAlignment(SwingConstants.TOP);
		cruiserlblTile8.setForeground(Color.CYAN);
		cruiserlblTile8.setBackground(Color.DARK_GRAY);
		cruiserlblTile8.setBounds(333, 200, 100, 100);
		cruiserlblTile8.setBorder(border);
		contentPane.add(cruiserlblTile8);

		JLabel cruiserlblTile9 = new JLabel("");
		cruiserlblTile9.setVerticalAlignment(SwingConstants.TOP);
		cruiserlblTile9.setForeground(Color.CYAN);
		cruiserlblTile9.setBackground(Color.DARK_GRAY);
		cruiserlblTile9.setBounds(35, 300, 100, 100);
		cruiserlblTile9.setBorder(border);
		contentPane.add(cruiserlblTile9);

		JLabel cruiserlblTile10 = new JLabel("");
		cruiserlblTile10.setVerticalAlignment(SwingConstants.TOP);
		cruiserlblTile10.setForeground(Color.CYAN);
		cruiserlblTile10.setBackground(Color.DARK_GRAY);
		cruiserlblTile10.setBounds(134, 300, 100, 100);
		cruiserlblTile10.setBorder(border);
		contentPane.add(cruiserlblTile10);

		JLabel cruiserlblTile11 = new JLabel("");
		cruiserlblTile11.setVerticalAlignment(SwingConstants.TOP);
		cruiserlblTile11.setForeground(Color.CYAN);
		cruiserlblTile11.setBackground(Color.DARK_GRAY);
		cruiserlblTile11.setBounds(234, 300, 100, 100);
		cruiserlblTile11.setBorder(border);
		contentPane.add(cruiserlblTile11);

		JLabel cruiserlblTile12 = new JLabel("");
		cruiserlblTile12.setVerticalAlignment(SwingConstants.TOP);
		cruiserlblTile12.setForeground(Color.CYAN);
		cruiserlblTile12.setBackground(Color.DARK_GRAY);
		cruiserlblTile12.setBounds(333, 300, 100, 100);
		cruiserlblTile12.setBorder(border);
		contentPane.add(cruiserlblTile12);

		JLabel cruiserlblTile13 = new JLabel("");
		cruiserlblTile13.setVerticalAlignment(SwingConstants.TOP);
		cruiserlblTile13.setForeground(Color.CYAN);
		cruiserlblTile13.setBackground(Color.DARK_GRAY);
		cruiserlblTile13.setBounds(35, 399, 100, 100);
		cruiserlblTile13.setBorder(border);
		contentPane.add(cruiserlblTile13);

		JLabel cruiserlblTile14 = new JLabel("");
		cruiserlblTile14.setVerticalAlignment(SwingConstants.TOP);
		cruiserlblTile14.setForeground(Color.CYAN);
		cruiserlblTile14.setBackground(Color.DARK_GRAY);
		cruiserlblTile14.setBounds(134, 399, 100, 100);
		cruiserlblTile14.setBorder(border);
		contentPane.add(cruiserlblTile14);

		JLabel cruiserlblTile15 = new JLabel("");
		cruiserlblTile15.setVerticalAlignment(SwingConstants.TOP);
		cruiserlblTile15.setForeground(Color.CYAN);
		cruiserlblTile15.setBackground(Color.DARK_GRAY);
		cruiserlblTile15.setBounds(234, 399, 100, 100);
		cruiserlblTile15.setBorder(border);
		contentPane.add(cruiserlblTile15);

		JLabel cruiserlblTile16 = new JLabel("");
		cruiserlblTile16.setVerticalAlignment(SwingConstants.TOP);
		cruiserlblTile16.setForeground(Color.CYAN);
		cruiserlblTile16.setBackground(Color.DARK_GRAY);
		cruiserlblTile16.setBounds(333, 399, 100, 100);
		cruiserlblTile16.setBorder(border);
		contentPane.add(cruiserlblTile16);

		JLabel shooterlblTile1 = new JLabel("");
		shooterlblTile1.setVerticalAlignment(SwingConstants.TOP);
		shooterlblTile1.setForeground(Color.CYAN);
		shooterlblTile1.setBackground(Color.DARK_GRAY);
		shooterlblTile1.setBounds(35, 100, 100, 100);
		shooterlblTile1.setBorder(border);
		contentPane.add(shooterlblTile1);

		JLabel shooterlblTile2 = new JLabel("");
		shooterlblTile2.setVerticalAlignment(SwingConstants.TOP);
		shooterlblTile2.setForeground(Color.CYAN);
		shooterlblTile2.setBackground(Color.DARK_GRAY);
		shooterlblTile2.setBounds(134, 100, 100, 100);
		shooterlblTile2.setBorder(border);
		contentPane.add(shooterlblTile2);

		JLabel shooterlblTile3 = new JLabel("");
		shooterlblTile3.setVerticalAlignment(SwingConstants.TOP);
		shooterlblTile3.setForeground(Color.CYAN);
		shooterlblTile3.setBackground(Color.DARK_GRAY);
		shooterlblTile3.setBounds(234, 100, 100, 100);
		shooterlblTile3.setBorder(border);
		contentPane.add(shooterlblTile3);

		JLabel shooterlblTile4 = new JLabel("");
		shooterlblTile4.setVerticalAlignment(SwingConstants.TOP);
		shooterlblTile4.setForeground(Color.CYAN);
		shooterlblTile4.setBackground(Color.DARK_GRAY);
		shooterlblTile4.setBounds(333, 100, 100, 100);
		shooterlblTile4.setBorder(border);
		contentPane.add(shooterlblTile4);

		JLabel shooterlblTile5 = new JLabel("");
		shooterlblTile5.setVerticalAlignment(SwingConstants.TOP);
		shooterlblTile5.setForeground(Color.CYAN);
		shooterlblTile5.setBackground(Color.DARK_GRAY);
		shooterlblTile5.setBounds(35, 200, 100, 100);
		shooterlblTile5.setBorder(border);
		contentPane.add(shooterlblTile5);

		JLabel shooterlblTile6 = new JLabel("");
		shooterlblTile6.setVerticalAlignment(SwingConstants.TOP);
		shooterlblTile6.setForeground(Color.CYAN);
		shooterlblTile6.setBackground(Color.DARK_GRAY);
		shooterlblTile6.setBounds(134, 200, 100, 100);
		shooterlblTile6.setBorder(border);
		contentPane.add(shooterlblTile6);

		JLabel shooterlblTile7 = new JLabel("");
		shooterlblTile7.setVerticalAlignment(SwingConstants.TOP);
		shooterlblTile7.setForeground(Color.CYAN);
		shooterlblTile7.setBackground(Color.DARK_GRAY);
		shooterlblTile7.setBounds(234, 200, 100, 100);
		shooterlblTile7.setBorder(border);
		contentPane.add(shooterlblTile7);

		JLabel shooterlblTile8 = new JLabel("");
		shooterlblTile8.setVerticalAlignment(SwingConstants.TOP);
		shooterlblTile8.setForeground(Color.CYAN);
		shooterlblTile8.setBackground(Color.DARK_GRAY);
		shooterlblTile8.setBounds(333, 200, 100, 100);
		shooterlblTile8.setBorder(border);
		contentPane.add(shooterlblTile8);

		JLabel shooterlblTile9 = new JLabel("");
		shooterlblTile9.setVerticalAlignment(SwingConstants.TOP);
		shooterlblTile9.setForeground(Color.CYAN);
		shooterlblTile9.setBackground(Color.DARK_GRAY);
		shooterlblTile9.setBounds(35, 300, 100, 100);
		shooterlblTile9.setBorder(border);
		contentPane.add(shooterlblTile9);

		JLabel shooterlblTile10 = new JLabel("");
		shooterlblTile10.setVerticalAlignment(SwingConstants.TOP);
		shooterlblTile10.setForeground(Color.CYAN);
		shooterlblTile10.setBackground(Color.DARK_GRAY);
		shooterlblTile10.setBounds(134, 300, 100, 100);
		shooterlblTile10.setBorder(border);
		contentPane.add(shooterlblTile10);

		JLabel shooterlblTile11 = new JLabel("");
		shooterlblTile11.setVerticalAlignment(SwingConstants.TOP);
		shooterlblTile11.setForeground(Color.CYAN);
		shooterlblTile11.setBackground(Color.DARK_GRAY);
		shooterlblTile11.setBounds(234, 300, 100, 100);
		shooterlblTile11.setBorder(border);
		contentPane.add(shooterlblTile11);

		JLabel shooterlblTile12 = new JLabel("");
		shooterlblTile12.setVerticalAlignment(SwingConstants.TOP);
		shooterlblTile12.setForeground(Color.CYAN);
		shooterlblTile12.setBackground(Color.DARK_GRAY);
		shooterlblTile12.setBounds(333, 300, 100, 100);
		shooterlblTile12.setBorder(border);
		contentPane.add(shooterlblTile12);

		JLabel shooterlblTile13 = new JLabel("");
		shooterlblTile13.setVerticalAlignment(SwingConstants.TOP);
		shooterlblTile13.setForeground(Color.CYAN);
		shooterlblTile13.setBackground(Color.DARK_GRAY);
		shooterlblTile13.setBounds(35, 399, 100, 100);
		shooterlblTile13.setBorder(border);
		contentPane.add(shooterlblTile13);

		JLabel shooterlblTile14 = new JLabel("");
		shooterlblTile14.setVerticalAlignment(SwingConstants.TOP);
		shooterlblTile14.setForeground(Color.CYAN);
		shooterlblTile14.setBackground(Color.DARK_GRAY);
		shooterlblTile14.setBounds(134, 399, 100, 100);
		shooterlblTile14.setBorder(border);
		contentPane.add(shooterlblTile14);

		JLabel shooterlblTile15 = new JLabel("");
		shooterlblTile15.setVerticalAlignment(SwingConstants.TOP);
		shooterlblTile15.setForeground(Color.CYAN);
		shooterlblTile15.setBackground(Color.DARK_GRAY);
		shooterlblTile15.setBounds(234, 399, 100, 100);
		shooterlblTile15.setBorder(border);
		contentPane.add(shooterlblTile15);

		JLabel shooterlblTile16 = new JLabel("");
		shooterlblTile16.setVerticalAlignment(SwingConstants.TOP);
		shooterlblTile16.setForeground(Color.CYAN);
		shooterlblTile16.setBackground(Color.DARK_GRAY);
		shooterlblTile16.setBounds(333, 399, 100, 100);
		shooterlblTile16.setBorder(border);
		contentPane.add(shooterlblTile16);

		listOfTiles.addAll(Arrays.asList(lblTile1, lblTile2, lblTile3, lblTile4, lblTile5, lblTile6, lblTile7, lblTile8, lblTile9, lblTile10, lblTile11, lblTile12, lblTile13, lblTile14, lblTile15, lblTile16));
		listOfStarTiles.addAll(Arrays.asList(starlblTile1, starlblTile2, starlblTile3, starlblTile4, starlblTile5, starlblTile6, starlblTile7, starlblTile8, starlblTile9, starlblTile10, starlblTile11, starlblTile12, starlblTile13, starlblTile14, starlblTile15, starlblTile16));
		listOfCruiserTiles.addAll(Arrays.asList(cruiserlblTile1, cruiserlblTile2, cruiserlblTile3, cruiserlblTile4, cruiserlblTile5, cruiserlblTile6, cruiserlblTile7, cruiserlblTile8, cruiserlblTile9, cruiserlblTile10, cruiserlblTile11, cruiserlblTile12, cruiserlblTile13, cruiserlblTile14, cruiserlblTile15, cruiserlblTile16));
		listOfShooterTiles.addAll(Arrays.asList(shooterlblTile1, shooterlblTile2, shooterlblTile3, shooterlblTile4, shooterlblTile5, shooterlblTile6, shooterlblTile7, shooterlblTile8, shooterlblTile9, shooterlblTile10, shooterlblTile11, shooterlblTile12, shooterlblTile13, shooterlblTile14, shooterlblTile15, shooterlblTile16));
		
		JLabel lblSkyWars = new JLabel("SKY WARS");
		lblSkyWars.setFont(new Font("Agency FB", Font.BOLD | Font.ITALIC, 29));
		lblSkyWars.setForeground(Color.ORANGE);
		lblSkyWars.setBounds(181, 11, 207, 39);
		contentPane.add(lblSkyWars);

		JLabel lblScore = new JLabel("SCORE");
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setForeground(Color.WHITE);
		lblScore.setFont(new Font("MS Gothic", Font.BOLD, 15));
		lblScore.setBounds(82, 36, 108, 39);
		contentPane.add(lblScore);

		JLabel lblHiscore = new JLabel("HIGH SCORE");
		lblHiscore.setHorizontalAlignment(SwingConstants.CENTER);
		lblHiscore.setFont(new Font("MS Gothic", Font.BOLD, 15));
		lblHiscore.setForeground(Color.WHITE);
		lblHiscore.setBounds(280, 36, 108, 39);
		contentPane.add(lblHiscore);

		JLabel lbldefIcon = new JLabel("");
		lbldefIcon.setIcon(new ImageIcon(GUI.class.getResource(MASTER_SHIP_DEFENSIVE)));
		lbldefIcon.setBounds(465, 119, 43, 100);
		contentPane.add(lbldefIcon);

		JLabel lbloffIcon = new JLabel("");
		lbloffIcon.setIcon(new ImageIcon(GUI.class.getResource(MASTER_SHIP_OFFENSIVE)));
		lbloffIcon.setBounds(465, 173, 43, 100);
		contentPane.add(lbloffIcon);

		JLabel lblTheMasterSpace = new JLabel("<html>\r\nTHE MASTER SPACE SHIP<br/><br/>\r\nDEFENSIVE MODE - TAKES 2 ENEMIES TO DEFEAT\r\n</html>");
		lblTheMasterSpace.setVerticalAlignment(SwingConstants.TOP);
		lblTheMasterSpace.setFont(new Font("MS Gothic", Font.BOLD, 12));
		lblTheMasterSpace.setForeground(Color.WHITE);
		lblTheMasterSpace.setBounds(518, 101, 234, 62);
		contentPane.add(lblTheMasterSpace);

		JLabel lblOffensiveMode = new JLabel("<html>\r\nOFFENSIVE MODE - TAKES 3 ENEMIES TO DEFEAT\r\n</html>");
		lblOffensiveMode.setForeground(Color.WHITE);
		lblOffensiveMode.setFont(new Font("MS Gothic", Font.BOLD, 12));
		lblOffensiveMode.setBounds(518, 157, 234, 62);
		contentPane.add(lblOffensiveMode);

		JLabel lblbtlStar = new JLabel("");
		lblbtlStar.setIcon(new ImageIcon(GUI.class.getResource(BATTLE_STAR)));
		lblbtlStar.setBounds(465, 190, 43, 100);
		contentPane.add(lblbtlStar);

		JLabel lblTheEnemiesBattle = new JLabel("<html>\r\n-----------------------------<br/><br/>\r\nTHE ENEMIES<br/><br/><br/>\r\nBATTLE SHOOTER - 25 PTS<br/><br/><br/><br/>\r\nBATTLE CRUISER - 50 PTS<br/><br/><br/><br/>\r\nBATTLE STAR - 75 PTS<br/><br/><br/>\r\n</html>");
		lblTheEnemiesBattle.setVerticalAlignment(SwingConstants.TOP);
		lblTheEnemiesBattle.setForeground(Color.WHITE);
		lblTheEnemiesBattle.setFont(new Font("MS Gothic", Font.BOLD, 12));
		lblTheEnemiesBattle.setBounds(518, 211, 234, 189);
		contentPane.add(lblTheEnemiesBattle);

		JLabel lblbtlShtr = new JLabel("");
		lblbtlShtr.setIcon(new ImageIcon(GUI.class.getResource(BATTLE_SHOOTER)));
		lblbtlShtr.setBounds(408, 310, 100, 100);
		contentPane.add(lblbtlShtr);

		JLabel lblbtlCruisr = new JLabel("");
		lblbtlCruisr.setIcon(new ImageIcon(GUI.class.getResource(BATTLE_CRUISER)));
		lblbtlCruisr.setBounds(408, 310, 100, 100);
		contentPane.add(lblbtlCruisr);

		JLabel lblScoreValue = new JLabel("0");
		lblScoreValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblScoreValue.setForeground(Color.WHITE);
		lblScoreValue.setFont(new Font("MS Gothic", Font.BOLD, 15));
		lblScoreValue.setBounds(82, 61, 108, 39);
		contentPane.add(lblScoreValue);
		
		scoreAndHighScore.add(lblScoreValue);

		JLabel lblHighScoreValue = new JLabel("0");
		lblHighScoreValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblHighScoreValue.setForeground(Color.WHITE);
		lblHighScoreValue.setFont(new Font("MS Gothic", Font.BOLD, 15));
		lblHighScoreValue.setBounds(280, 61, 108, 39);
		contentPane.add(lblHighScoreValue);
		
		// load saved high score
		File tmpDir = new File("highScore.txt");
		if (tmpDir.exists()) {			
			
			try {
				gameLogic.setHighScore(saveLoadLogic.loadHighScore());
				lblHighScoreValue.setText(Integer.toString(gameLogic.getHighScore()));
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		scoreAndHighScore.add(lblHighScoreValue);

	}
} //  end GUI
