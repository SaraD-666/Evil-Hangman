import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.lang.Math;
import java.util.Set;
import java.util.HashSet;
public class Main {
	
	/* 
    1) Select list from arrayList that matches the index found from wrdLength 

    2) Display # of underscores in the console that corresponds to that length
    
    3) Have user input a letter

    4) After letter has been inputted, create word families based off position of letter
    
    5) Select the word family that is the largest 

    6) Repeat process until every word family has been eliminated
      */
	
	static String underScore = "";
	
	public static void main(String[] args) throws IOException {
		//read the dictionary file
		BufferedReader wordsFamily = new BufferedReader(new FileReader("dictionary.txt"));
		//read whatever the player input
		Scanner in = new Scanner(System.in);
		
		//create an array list String and give let it have 24 space, 
		//because there are only 24 different lengths
		List<String>[] array = new ArrayList[24];
		
		//make each index equal to a string
		for (int i = 0; i < 24; i++) {
			array[i] = new ArrayList<String>();
		}
		
		Map<Integer, Integer> lengthMapping = new HashMap<>();
		for (int i = 2; i < 23; i++) {
			lengthMapping.put(i, i);
		}
		lengthMapping.put(23, 24);
		lengthMapping.put(24, 28);
		lengthMapping.put(25, 29);
		
		int wrdLength = (int)(Math.random() * 25) + 2;

		/*If I make a map from integers to integers of wordlegnth to valid word lengths, then I will always end up with a valid word length
		2: 2
		3: 3
		4: 4
		5: 5 ...
		23: 24
		24: 28
		25: 29
		
		if I use mapping, the wrdlist will never be empty
		*/
		List<String> wrdList = array[wrdLength-2]; //1
	
		   
		
		//create numbers of  "-"
		for (int i = 0; i < wrdLength; i++) {
			underScore += "-";
		}

		//int currentMax = 0;
		//String currentWord = "";
		
		//there are 127142 words, so run this much of time and plug in the words into the correct index of array 
		for (int i= 0; i < 127142; i++) {
			String wrd = wordsFamily.readLine();
			if (wrd.length() < 23) {
				array[wrd.length() - 2].add(wrd);
			}
			else if (wrd.length() == 24) {
				array[21].add(wrd);
			}
			else if (wrd.length() == 28) {
				array[22].add(wrd);
			}
			else {
				array[23].add(wrd);
			}
			
//			if (wrd.length() > currentMax) currentWord = wrd;
//			currentMax = Math.max(currentMax, ssss.length());
		}
		
//		for(int i = 0 ;i < array.length; i++) {
//			List list1 = array[i];
//			if(list1.size()!=0) {
//				System.out.println(i+2);
//			}
//		}

	/*
	 Things you need to print:
	 	# guesses left
	 	used letters
	 	underscores
	 	entered guess
	 	response
	 */
		String usedLetters = "";
		
		List<String> lstStr = wrdList;
		
		for (int i = 20; i > 0;) {//20 guesses 
			//if player guessed it right before the guesses run out
			if (playerWin()) {
				System.out.println("you have guessed: " + underScore);
				System.out.println("Congratulations! You have won this game");
				System.out.println("Play again?");
				break;
			}
			
			Map<String, List<String>> wurdFam = new HashMap<>();
			System.out.println("You have " + i + " guesses left");
			System.out.println("Used Letters: " + usedLetters);
			System.out.println("Word: " + underScore);
			System.out.println("Enter guess");
	
			char s = in.next().charAt(0);
			
//			Set<Integer> set1 = new HashSet<Integer>();

			for (int k = 0; k < lstStr.size(); k++) {
				String ss = lstStr.get(k);
				



				String newKey = underScore;
				for (int j = 0; j < wrdLength; j++) {
					if (ss.charAt(j)==s) {
						newKey = newKey.substring(0, j) + s + newKey.substring(j + 1);
					}
				}

				if (wurdFam.get(newKey) == null) {
					wurdFam.put(newKey, new ArrayList<>());
				}
				wurdFam.get(newKey).add(ss);
			}


			String maxKey = "";
			int maxLength = 0;
			for(String key: wurdFam.keySet()){
				if (wurdFam.get(key).size() > maxLength) {
					maxLength = wurdFam.get(key).size();
					maxKey = key;
				}
			}
			underScore = maxKey;
			
			if (usedLetters.contains(s + "")) {
				System.out.println("You have already guessed " + s + ", please guess another one");
				i++;
			}
			
			lstStr = wurdFam.get(maxKey);
			usedLetters += s + " ";
			
			i--;
			
			
			
			/* If word is completely guessed, print "User Won!", if guesses run out, print "USer lost" */
			if (i == 0) {
				System.out.println("You have " + i + " guesses left");
				if (playerWin()) {
					System.out.println("you have guessed: " + underScore);
					System.out.println("Congratulations! You have won this game");
					System.out.println("Play again?");
				}
				else {
					System.out.println("You lose, the word was: " + wurdFam.get(underScore).get(0));
					System.out.println("Play again?");
				}
			}
		}
		
	}
	
	static boolean playerWin() {
		if (underScore.contains("-")) {
			return false;
		}
		else {
			return true;
		}
	}
	
}
