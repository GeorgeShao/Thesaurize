import java.io.*;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Thesaurize {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String search = br.readLine();
		
		String [] words = search.split(" ");
		
		boolean failed = false;
		
		boolean validentry = false;
		
		for(int i = 0; i < words.length; i++) {
			
			char [] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' '};
			
			String enteredURL = words[i];
			
			for(int j = 0; j < enteredURL.length(); j++) {
				char currentCharacter = enteredURL.charAt(j);
				validentry = false;
				for(int k = 0; k < 26; k++) {
					if(enteredURL.charAt(j) == alphabet[k]) {
						validentry = true;
					}
				}
				if(!validentry) {
					String currentCharacterStr = String.valueOf(currentCharacter);
					enteredURL = enteredURL.replaceAll(currentCharacterStr, "");
				}
			}
			
			enteredURL = "https://www.thesaurus.com/browse/" + enteredURL;
			
			
			
			// Establish connection to Thesaurus.com
			URL url = new URL(enteredURL);
			URLConnection urlConn = url.openConnection();
		
			try {
				br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
				failed = false;
			} catch (Exception e) {
				System.out.print(words[i] + " ");
				failed = true;
			}
		
			if(!failed) {
				// Get HTML data from Thesaurus.com
				String entirePage = "";
				String line = br.readLine();
				while (line != null) {
					entirePage = entirePage + line;
					line = br.readLine();
				}
				
				//Extract first word from Thesaurus.com & store it in an array
				String rest = entirePage.substring(entirePage.indexOf("<a href=\"/browse/") + 17);
				String word = rest.substring(0, rest.indexOf("\""));
				
				word = word.replaceAll("%20", " ");
				
				System.out.print(word + " ");
				
			}
		}
		
	}
}
