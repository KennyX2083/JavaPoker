import java.util.ArrayList;
import java.util.Collections;

public class Player {
	
		
	private ArrayList<Card> hand; // the player's cards
	private double bankroll;
    private double bet;

	// you may choose to use more instance variables
		
	public Player(){		
	    // create a player here
        this.hand = new ArrayList<Card>();
        this.bankroll = 50;
        this.bet = 0;
	}

	public void addCard(Card c){
	    // add the card c to the player's hand
        hand.add(c);

	}

	public void removeCard(Card c){
	    // remove the card c from the player's hand
        hand.remove(c);
    }
		
    public void bets(double amt){
        // player makes a bet
        bet = amt;
        bankroll -= bet;
    }

    public void winnings(double odds){
        //	adjust bankroll if player wins
        bankroll += bet * odds;
        bet = 0;
    }

    public double getBankroll(){
        // return current balance of bankroll
        return bankroll;
    }

        // you may wish to use more methods here
    public void showHand() {
        Collections.sort(hand);
        for (Card c: hand) {
            System.out.println(c.toString());
        }
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
}


