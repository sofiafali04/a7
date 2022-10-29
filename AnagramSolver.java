/* CS 314 STUDENTS: FILL IN THIS HEADER.
 *
 * Student information for assignment:
 *
 *  On my honor, <NAME>, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID:
 *  email address:
 *  TA name:
 *  Number of slip days I am using:
 */


import java.util.*;

public class AnagramSolver {

    private Map<String, LetterInventory> wordInventories = new TreeMap<>();
    private Set<String> dictionary;

    /*
     * pre: list != null
     * @param list Contains the words to form anagrams from.
     */
    public AnagramSolver(Set<String> dictionary) {
        // CS314 Students, add your code here
        this.dictionary = dictionary;
        for (String word : dictionary) {
            wordInventories.put(word, new LetterInventory(word));
        }
    }

    /*
     * pre: maxWords >= 0, s != null, s contains at least one
     * English letter.
     * Return a list of anagrams that can be formed from s with
     * no more than maxWords, unless maxWords is 0 in which case
     * there is no limit on the number of words in the anagram
     */
    public List<List<String>> getAnagrams(String s, int maxWords) {
        if (maxWords < 0 || s == null) {
            throw new IllegalArgumentException("Violation of Preconditions");
        }
        // CS314 Students, add your code here.
        LetterInventory lettersLeft = new LetterInventory(s);
        ArrayList<List<String>> result = getAnagramsHelper(s, maxWords, lettersLeft);
        return result;
    }

    private ArrayList<List<String>> getAnagramsHelper(String s, int maxWords,
                                                      LetterInventory lettersLeft) {

        ArrayList<String> reducedDict = new ArrayList<>();
        for (String word : dictionary) {
            //if a word in the dictionary can be an anagram of s, add to reducedDict
            if (lettersLeft.subtract(wordInventories.get(word)) != null) {
                reducedDict.add(word);
            }
        }
        ArrayList<String> anagrams = new ArrayList<>();
        ArrayList<List<String>> currentAnagrams = new ArrayList<>();
        if(maxWords == 0){
            maxWords = lettersLeft.size();
        }
        return recursionHelper("", maxWords, lettersLeft, reducedDict, anagrams, currentAnagrams);

    }

    private ArrayList<List<String>> recursionHelper(String currentWord, int maxWords,
                                                    LetterInventory lettersLeft,
                                                    ArrayList<String> reducedDict,
                                                    ArrayList<String> anagrams,
                                                    ArrayList<List<String>> currentAnagrams) {
        //completed, add to currentAnagrams
        if (lettersLeft.isEmpty()) {
            if(!currentAnagrams.contains(anagrams)) {
                currentAnagrams.add(new ArrayList<>(anagrams));
            }
        }

        for (int i = 0; i < reducedDict.size(); i++) {
            currentWord = reducedDict.get(i);
            //okay to add word
            if (lettersLeft.subtract(wordInventories.get(currentWord)) != null) {
                ArrayList<String> newDict = reducedDict;

                //add word to current anagrams
                anagrams.add(reducedDict.get(i));
                LetterInventory LICopy = lettersLeft.subtract(wordInventories.get(reducedDict.get(i)));
                if(anagrams.size() > maxWords) {
                    newDict.remove(currentWord);
                }

                //call again with new letters left
                recursionHelper(currentWord, maxWords, LICopy, newDict,
                        anagrams, currentAnagrams);

                //undo for recursive backtracking
                anagrams.remove(currentWord);
            }
        }
        currentAnagrams.sort(new AnagramComparator());
        return currentAnagrams;
    }

    private static class AnagramComparator implements Comparator<List<String>> {
        public int compare(List<String> a1, List<String> a2) {
            if(a1.size() != a2.size()){
                return a1.size() - a2.size();
            }

            for(int i = 0; i < a1.size(); i++){
                if(a1.get(i).compareTo(a2.get(i)) != 0){
                    return a1.get(i).compareTo(a2.get(i));
                }
            }
            return 0;
        }
    }
}