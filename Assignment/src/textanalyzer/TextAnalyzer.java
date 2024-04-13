/* Current package name */
package textanalyzer;
/* Text input service */
import java.util.Scanner;
/*String splitting service */
import java.util.StringTokenizer;
/* collections for calculating max in a set*/
import java.util.Collections;
/* Import Hash map to work as dictionary */
import java.util.HashMap;

public class TextAnalyzer {
	/* Main method */
	public static void main(String[] args)
	{
		String Text = UserInput();
		/* Character count */
		System.out.println("Character count:   " + CharacterCounter(Text));
		
		/* Word Count*/
		System.out.println("Word Count:   " + WordCounter(Text));
		
		/* Character frequency*/
		System.out.println("Character Frequency:   " + Frequency(Text, 1));
		
		/* Word frequency */
		System.out.println("Word Frequency:    " + Frequency(Text, 0));
		
		/* Most appearing Character*/
		ModeFunction(Frequency(Text, 1));
		
		/* Unique Wordsv*/
		UniqueWords(Frequency(Text, 0));
	}
	
	/* Fetch User input */
	@SuppressWarnings("resource")
	/* Suppress all warnings concerned with same variable and method names */
	static String UserInput()
	{
		Scanner TextInput = new Scanner(System.in);
		System.out.println("Enter Paragraph to be analysed...\n");
		String Text = TextInput.nextLine();
		return Text;
	}
	
	/* Word count */
	static int WordCounter(String Text)
	{
		int WordCount = new StringTokenizer(Text).countTokens();
		return WordCount;
	}
	
	/* Count characters */
	static int CharacterCounter(String Text)
	{
		return Text.length();
	}
	
	/* Character frequency */
	static HashMap<String, Integer> Frequency(String Text, int Flag)
	/* This is a multi-dimension function*/
	/* It will compute frequency of everything passes to it*/
	/*
	 * Flags:
	 * 1 represents a character
	 * anything else represents a word
	 */
	{
		HashMap<String, Integer> CharacterDict = new HashMap<String, Integer>();
		if (Flag == 1)
		{
			for (int i = 0; i < CharacterCounter(Text); i++)
			{
				char c = Text.charAt(i);
				/*
				 * First compute if found
				 * Convert to lowercase to make it case insenstive
				 * */
				CharacterDict.computeIfPresent(String.valueOf(c).toLowerCase(), (key, value)->(value + 1));
				/*Put is the key is not found */
				CharacterDict.putIfAbsent(String.valueOf(c).toLowerCase(), 1);
			}
		} else
		{
			StringTokenizer Token = new StringTokenizer(Text);
			while (Token.hasMoreTokens())
			{
				/*
				 * First compute if found
				 * Convert to lowercase to make it case insenstive
				 * */
				String token = Token.nextToken();
				//CharacterDict.computeIfPresent(String.valueOf(c).toLowerCase(), (key, value)->(value + 1));
				/*Put is the key is not found */
				//CharacterDict.putIfAbsent(String.valueOf(c).toLowerCase(), 1);
				CharacterDict.computeIfPresent(token.toLowerCase(), (key, value)->(value + 1));
				/*Put is the key is not found */
				CharacterDict.putIfAbsent(token.toLowerCase(), 1);
			}
		}
		
		return CharacterDict;
	}
	
	static void ModeFunction(HashMap <String, Integer> Dict)
	{
		int max = Collections.max(Dict.values());
		Dict.forEach((key, value)-> {
			if (value.equals(max))
			{
				System.out.println("Modal character    " + key);
			}
		});
	}
	
	static void UniqueWords(HashMap <String, Integer> Dict)
	{
		System.out.println("Unique Words:   " + Dict.size());
	}
}