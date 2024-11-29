

import java.util.ArrayList;
import java.util.Collections;

public class Card implements Comparable<Card>{
	
	private int suit; // use integers 1-4 to encode the suit
	private int rank; // use integers 1-13 to encode the rank
	
	public Card(int s, int r){
		this.suit = s;
        this.rank = r;
	}
	
	public int compareTo(Card c){
		// use this method to compare cards so they 
		// may be easily sorted
        if (this.rank != c.rank) {
            return Integer.compare(this.rank, c.rank);
        }
        return Integer.compare(this.suit, c.suit);
	}
	
    //list of suits: hearts, diamonds, spades, and clubs
	public String toString(){
		// use this method to easily print a Card object
        String cardSuit = "";
        String cardRank = "";

        switch (this.suit) {
            case 1: //if suit == 1, suit is hearts
                cardSuit = "hearts";
                break;
            case 2: //if suit == 2, suit is diamonds
                cardSuit = "diamonds";
                break;
            case 3: //if suit == 3, suit is spades
                cardSuit = "spades";
                break;
            case 4: //if suit == 4, suit is clubs
                cardSuit = "clubs";
                break;
        }

        switch (this.rank) {
            case 1: //if rank == 1, rank is ace
                cardRank = "Ace";
                break;
            case 11: //if rank == 11, rank is jack
                cardRank = "Jack";
                break;
            case 12: //if rank == 12, rank is queen
                cardRank = "Queen";
                break;
            case 13: //if rank == 13, rank is king
                cardRank = "King";
                break;
            default: //if rank is not any of the above, rank is just the numeric value of rank
                cardRank = String.valueOf(this.rank);
                break;
        }

        return cardRank + " of " + cardSuit; //if suit is 2 and rank is 11, returns: 'jack of diamonds'
	}
	// add some more methods here if needed

    public int getSuit() {
        return this.suit;
    }

    public int getRank() {
        return this.rank;
    }

    public void setSuit(int s) {
        this.suit = s;
    }

    public void setRank(int r) {
        this.rank = r;
    }
}
