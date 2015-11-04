package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.LinkedList;

public class IO {
	private boolean restart = false;
	private String player1 = "";
	private String player2 = "";
	private int[] map = { -1, -1 };
	private data game;
	private boolean ai = false;
	private boolean aih = false;
	LinkedList<String> edges = new LinkedList<String>();
	
	
	public IO() {
		start();
	}

	void start() {
		intro();
		if (restart)
			return;
		System.out.println("Starting Game...");
		game = new data(map[0], map[1]);
		System.out.println("Finished initialization:\n\n");
		aiList();
		play();
		return;
	}
	
	void aiList(){
		for(int i = 0; i < game.maxVal; i++){
			edges.add(Integer.toString((i + 1)));
		}
	}

	void play() {
		if (restart)
			return;
		if(ai)
			startC();
		if(aih)
			startHC();
		else
			startM();
		System.out.println();
		game.printField();
		System.out.println();
		System.out.println("\nFinished Game:\n" + "calculating Score...");

		game.printResult(player1, player2);
	}

	private void startHC() {
		boolean p1 = true;
		boolean valid;
		String turn = "It's your turn ";
		String in = "";
		while (game.marked < game.maxVal) {
			valid = false;
			game.printField();
			System.out.println(turn
					+ ((p1) ? (player1 + "(p1)") : (player2 + "(p2)")));
			in = getInput();
			if (restart)
				return;
			while (!valid) {
				if (in.matches("[0-9]+")) {
					int x = game.mark(Integer.parseInt(in), (p1) ? '1' : '2');
					if (x == 1) {
						valid = true;
						break;
					}
					if (x == 2) {
						valid = true;
						System.out
								.println("Congrats you scored and deserve another try:");
						p1 = !p1;
						break;
					}
					if (x == 0) {
						System.out
								.println("Index already marked: Try again ...");
					} else {
						if (x == -1) {
							System.out
									.println("Index not in reach: Try again ...");
						}
					}
				} else {
					System.out
							.println("Invalid Input: Try it with a number ...");
				}
				in = getInput();
				if (restart)
					return;
			}
			p1 = !p1;
		}
	}

	private void startC() {
		boolean p1 = true;
		boolean valid;
		String turn = "It's your turn ";
		String in = "";
		Random random = new Random();
		while (game.marked < game.maxVal) {
			valid = false;
			game.printField();
			System.out.println(turn
					+ ((p1) ? (player1 + "(p1)") : (player2 + "(p2)")));
			if(p1){
				in = getInput();
				edges.remove(in);
			}
			else {
				if(edges.size() == 1){
					in = edges.getFirst();
				} else{
					in = edges.get(random.nextInt((edges.size() - 1)) + 1);
				}
				edges.remove(in);
			}
			if (restart)
				return;
			while (!valid) {
				if (in.matches("[0-9]+")) {
					int x = game.mark(Integer.parseInt(in), (p1) ? '1' : '2');
					if (x == 1) {
						valid = true;
						break;
					}
					if (x == 2) {
						valid = true;
						System.out
								.println("Congrats you scored and deserve another try:");
						p1 = !p1;
						break;
					}
					if (x == 0) {
						System.out
								.println("Index already marked: Try again ...");
					} else {
						if (x == -1) {
							System.out
									.println("Index not in reach: Try again ...");
						}
					}
				} else {
					System.out
							.println("Invalid Input: Try it with a number ...");
				}
				in = getInput();
				if (restart)
					return;
			}
			p1 = !p1;
		}
	}

	private void startM() {
		boolean p1 = true;
		boolean valid;
		String turn = "It's your turn ";
		String in = "";
		while (game.marked < game.maxVal) {
			valid = false;
			game.printField();
			System.out.println(turn
					+ ((p1) ? (player1 + "(p1)") : (player2 + "(p2)")));
			in = getInput();
			if (restart)
				return;
			while (!valid) {
				if (in.matches("[0-9]+")) {
					int x = game.mark(Integer.parseInt(in), (p1) ? '1' : '2');
					if (x == 1) {
						valid = true;
						break;
					}
					if (x == 2) {
						valid = true;
						System.out
								.println("Congrats you scored and deserve another try:");
						p1 = !p1;
						break;
					}
					if (x == 0) {
						System.out
								.println("Index already marked: Try again ...");
					} else {
						if (x == -1) {
							System.out
									.println("Index not in reach: Try again ...");
						}
					}
				} else {
					System.out
							.println("Invalid Input: Try it with a number ...");
				}
				in = getInput();
				if (restart)
					return;
			}
			p1 = !p1;
		}
	}

	void intro() {
		System.out
				.println("Hello and welcome to CheeseBoxing\n"
						+ "This Game is for two Players\n"
						+ "First you will choose map size\n"
						+ "Then you can choose game mode\n"
						+ "GoodLuck and Have Fun\n"
						+ "You can always restart by typing restart!\n");

		chooseMap();
		if (restart)
			return;
		System.out.println("Map accepted;");
		
		chooseplaymode();
		if (restart)
			return;
		System.out.println("Gamemode accepted;");
	}

	private void chooseplaymode() {

		System.out.println("This Game is playable as multiplayer\n"
				+ "or as 1 vs 1 against a computer,\n"
				+ "for multiplayer enter: m\n"
				+ "to play against the easy computer enter: c\n"
				+ "to play against the hard computer enter hc\n");
		while (!restart) {
			String gameMode = getInput();
			if (gameMode.equals("restart"))
				return;
			if (gameMode.equals("m")) {
				chooseNames();
				break;
			}
			if (gameMode.equals("c")) {
				ai = true;
				chooseNames();
				break;
			}
			if (gameMode.equals("hc")) {
				aih = true;
				chooseNames();
				break;
			}
			System.out.println("Invalid Input: ... Try again");
		}
	}

	private String calcBestMove(){
		
		
		
		
		
		
		return "s";
	}

	private void chooseMap() {
		System.out.println("It's time to chose your Map;\n"
				+ "Here are some Rules:\n"
				+ "Split your X and Y Coordinates by a lower x\n"
				+ "Allowed Input Ranges are: 2 - 20 (2x2 - 20x20)\n "
				+ "here is an example: 7x5\n");
		String map = getInput();
		boolean correct = false;
		while (!correct && !restart) {
			String[] cood = map.split("x");
			if (cood.length == 2 && cood[0].matches("[0-9]+")
					&& cood[1].matches("[0-9]+")) {
				int x = Integer.parseInt(cood[0]);
				int y = Integer.parseInt(cood[1]);
				if (1 < x && x < 21 && 1 < y && y <= 20) {
					correct = true;
					this.map[0] = x;
					this.map[1] = y;
					break;
				}
			}
			System.out.println("Invalid Input: please try again\n"
					+ "Input valid: 2x2 - 20x20\n " + "for example: 7x5\n");
			System.out.println("Your Input was: " + map);
			map = getInput();
			if (restart)
				return;
		}

	}

	void chooseNames() {
		String instr = "It's time to choose your name\n"
				+ "User name with lengths of 3 to 6\n" + "\nPlayer 1:";
		System.out.println(instr);
		getPlayersName(true);
		if (restart)
			return;
		System.out.println("\nPlayer2 :");
		getPlayersName(false);
		if (restart)
			return;
		System.out.println("\nP1:\t" + player1 + ";\tP2:\t" + player2 + ";");
	}

	/**
	 * true for player1 false for player2
	 * 
	 * @param a
	 */
	void getPlayersName(boolean a) {
		System.out.println("Please enter your Name:");
		String in = getInput();
		while (!isNameValid(in)) {
			if (restart)
				return;
			System.out.println("Invalid Name...");
			in = getInput();
		}
		if (a)
			player1 = in;
		if (!a)
			player2 = in;
	}

	private boolean isNameValid(String in) {
		return (2 < in.length() && in.length() < 7);
	}

	// returnt den input des players
	String getInput() {
		String s = "";
		while (true) {
			try {
				BufferedReader bufferRead = new BufferedReader(
						new InputStreamReader(System.in));
				s = bufferRead.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (s.equals("restart")) {
				restart = true;
			}
			if (s.length() < 10 && s.length() > 0)
				break;
			System.out
					.println("Your Input length isn't acceptable, please try again.");
		}
		return s;
	}

}