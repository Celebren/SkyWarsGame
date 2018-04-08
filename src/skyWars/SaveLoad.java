package skyWars;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class SaveLoad {
	// serialization objects
	// to write data
	private FileOutputStream fos;
	private ObjectOutputStream oos;

	//to read data
	private FileInputStream fis;
	private ObjectInputStream ois;
	
	public SaveLoad() {};

	// serialize GameLogic
	public void serializeGameLogicState(GameLogic gameLogicState) throws IOException{
		
		fos = new FileOutputStream("SavedGame.txt");
		oos = new ObjectOutputStream(fos);

		oos.writeObject(gameLogicState);
		oos.close();		
	}
	
	// deserialize GameLogic
	public GameLogic deserializeGameLogicState() throws ClassNotFoundException, IOException {

		fis = new FileInputStream("SavedGame.txt");
		ois = new ObjectInputStream(fis);

		GameLogic deserializedGameLogicState = (GameLogic) ois.readObject();

		return deserializedGameLogicState;
	}
	
	public void saveHighScore(int highScore) throws IOException {
		BufferedWriter saveHighScoreBufferedWriter = new BufferedWriter(new FileWriter("highScore.txt"));
		saveHighScoreBufferedWriter.write(Integer.toString(highScore));
		saveHighScoreBufferedWriter.close();
	}
	
	public int loadHighScore() throws NumberFormatException, IOException {
		
		BufferedReader loadHighScoreBufferedReader = new BufferedReader(new FileReader("highScore.txt"));
		int highScore = Integer.parseInt(loadHighScoreBufferedReader.readLine());
		loadHighScoreBufferedReader.close();

		return highScore;
	}
	
}
