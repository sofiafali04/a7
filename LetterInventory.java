/* CS 314 STUDENTS: FILL IN THIS HEADER AND THEN COPY AND PASTE IT TO YOUR
 * LetterInventory.java AND AnagramSolver.java CLASSES.
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

public class LetterInventory {

    private int[] letterFrequencies;
    private int size;
    private final int ALPHABET_LENGTH = 26;
    private final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    /**
     * Pre: word != null
     * @param word
     */
    public LetterInventory(String word){
        if(word == null){
            throw new IllegalArgumentException("Violation of Preconditions");
        }

        size = 0;
        String wordLowerCase = word.toLowerCase();
        letterFrequencies = new int[ALPHABET_LENGTH];

        for(int i = 0; i < word.length(); i++){
            if('a' <= wordLowerCase.charAt(i) && wordLowerCase.charAt(i) <= 'z'){
                int index = ALPHABET.indexOf(wordLowerCase.charAt(i));
                letterFrequencies[index]++;
                size++;
            }
        }
    }

    /**
     * Pre: a <= letter <= z
     * @param letter
     * @return
     */
    public int get(char letter){
        if(!('a' <= letter && letter <= 'z') && !('A' <= letter && letter <= 'Z')){
            throw new IllegalArgumentException("Violation of Preconditions");
        }
        if(ALPHABET.indexOf(letter) == -1 || ALPHABET.toUpperCase().indexOf(letter) == -1){
            return 0;
        }
        return letterFrequencies[ALPHABET.indexOf(letter)];
    }

    /**
     * @return the total number of letters in this LetterInventory
     */
    public int size(){
        return size;
    }

    /**
     *
     * @return true is the size of this LetterInventory is 0, false otherwise
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     *
     * @return return a String representation of this LetterInventory
     */
    public String toString(){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < letterFrequencies.length; i++){
            for(int j = 0; j < letterFrequencies[i]; j++){
                result.append(ALPHABET.charAt(i));
            }
        }
        return result.toString();
    }

    /**
     * Pre: other != null
     * @param other
     * @return a new LetterInventory created by adding the frequencies of this
     * LetterInventory and the other LetterInventory
     */
    public LetterInventory add(LetterInventory other) {
        if (other == null) {
            throw new IllegalArgumentException("Violation of Preconditions");
        }

        LetterInventory result = new LetterInventory("");

        for(int i = 0; i < ALPHABET_LENGTH; i++){
            result.letterFrequencies[i] = (letterFrequencies[i] + other.letterFrequencies[i]);
        }
        result.size = (size + other.size);
        return result;
    }

    /**
     * Pre: other != null
     * @param other
     * @return a new LetterInventory created by subtracting the frequencies of this
     * LetterInventory and the other LetterInventory
     */
    public LetterInventory subtract(LetterInventory other){
        if (other == null) {
            throw new IllegalArgumentException("Violation of Preconditions");
        }

        LetterInventory result = new LetterInventory("");

        for(int i = 0; i < ALPHABET_LENGTH; i++){
            if(letterFrequencies[i] - other.letterFrequencies[i] < 0){
                return null;
            }
            result.letterFrequencies[i] = (letterFrequencies[i] - other.letterFrequencies[i]);

        }
        result.size = (size - other.size);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }

        LetterInventory other = (LetterInventory) obj;

        for(int i = 0; i < ALPHABET_LENGTH; i++){
            if(letterFrequencies[i] != other.letterFrequencies[i]){
                return false;
            }
        }
        return true;
    }
}