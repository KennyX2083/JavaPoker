
import java.util.Random;
import java.util.ArrayList;

public class Deck {
	
	private Card[] cards;
	private int top; // the index of the top of the deck

	// add more instance variables if needed
    private static final int DECK_SIZE = 52;
	
	public Deck(){
		// make a 52 card deck here
        cards = new Card[DECK_SIZE];
        int top = 0;
        int index = 0;
        for (int suit = 1; suit <= 4; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                cards[index] = new Card(suit,rank);
                index++;
            } 
        }
	}
	
	public void shuffle(){ //using Fisher-Yates shuffle algorithm
		Random random = new Random();

        for (int i = cards.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
	}

    // algorithm that would randomly swap the card at an index from 0 to 51 (so 52 times) with a random card from 0 to 51
    // public void shuffle() {
    //     Random random = new Random();

    //     for (int i = 0; i < DECK_SIZE; i++) {
    //         int j = random.nextInt(DECK_SIZE);

    //         Card temp = cards[i];
    //         cards[i] = cards[j];
    //         cards[j] = temp;
    //     }
    // }
	
	public Card deal(){
		// deal the top card in the deck
        if (top < DECK_SIZE) { //if top reaches 52, then all cards have been dealt
            Card dealtCard = cards[top];
            top++;
            return dealtCard;
        }
        else { //no cards left
            return null; 
        }
	}
	
	// add more methods here if needed

}
