import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;


/**
 * 
 * O(n*m) - time, where n is length of start word, m is number of "same-sized" words in dictionary
 * O(m)   - space m is number of words in dictionary
 * 
 * BB: 9
 * 
 */
public class ShortestTransform {

    private ShortestTransform () { };

    public static List<String> transform(String startWord, String stopWord, Set<String> dictionary) {
        
        if (startWord.length() != stopWord.length()) {
            throw new IllegalArgumentException("src and dest name should be same length.");
        }
        
        startWord = startWord.toUpperCase();
        stopWord = stopWord.toUpperCase();
        
        Queue<String> actionQueue = new LinkedList<String>();
        Set<String> visitedSet = new HashSet<String>();
        Map<String, String> backtrackMap = new HashMap<String, String>();
        
        actionQueue.add(startWord); // CAT.
        visitedSet.add(startWord);
        
        while (!actionQueue.isEmpty()) {
            String w = actionQueue.poll(); // CAT -> 
            
            // For each possible word v from w with one edit operation 
            for (String v : getOneEditWords(w, dictionary, visitedSet)) {
                if (v.equals(stopWord)) {
                    // Found our word! Now, back track. 
                    List<String> list = new ArrayList<String>(); // Append v to list
                    list.add(v);
                    while (w != null) {
                        list.add(w);
                        w = backtrackMap.get(w); 
                    }
                    Collections.reverse(list);
                    return list;
                }
              
                actionQueue.add(v); 
                visitedSet.add(v); // mark visited 
                backtrackMap.put(v, w);  // (new, old)
            }
        }
        return Collections.emptyList(); 
    }

    public static Set<String> getOneEditWords(String word, Set<String> dictionary, Set<String> visitedSet) { 
        Set<String> words = new HashSet<String>(); 
        char[] wordArray = word.toCharArray(); // change that letter to something else
        
        for (int i = 0; i < word.length(); i++) {
            char ch = wordArray[i];
            for (char c = 'A'; c <= 'Z'; c++) {
                if (c != ch) { 
                    wordArray[i] = c;
                    
                    String newWord = new String(wordArray);
                    if (dictionary.contains(newWord) && !visitedSet.contains(newWord)) {
                        words.add(newWord);
                    }
                }
            }
            wordArray[i] = ch;
        }
        return words;
    }

    
    public static void main(String[] args) {
        
        Set<String> dictionary = new HashSet<String>();
        dictionary.add("CAMERA");
        dictionary.add("CAT");
        dictionary.add("COT");
        dictionary.add("DOME");
        dictionary.add("DOT");
        dictionary.add("DOG");
        
        // CAT -> COT -> DOT -> DOG
        for (String string :  transform("CAT", "DOG", dictionary)) {
            System.out.println(string);
        }
    }
}
    