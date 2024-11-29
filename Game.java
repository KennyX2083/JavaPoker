
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Game {
	
	private Player p;
	private Deck cards;
	// you'll probably need some more here
    private Scanner scanner;
	
	
	public Game(String[] testHand){
		// This constructor is to help test your code.
		// use the contents of testHand to
		// make a hand for the player
		// use the following encoding for cards
		// c = clubs (4)
		// d = diamonds (2)
		// h = hearts (1)
		// s = spades (3)
		// 1-13 correspond to ace-king
		// example: s1 = ace of spades
		// example: testhand = {s1, s13, s12, s11, s10} = royal flush
		p = new Player();
        cards = new Deck();

        for (String stringOfCard: testHand) {
            p.addCard(decode(stringOfCard));
        }
		
	}
	
	public Game(){
		// This no-argument constructor is to actually play a normal game
		cards = new Deck();
        cards.shuffle();
        p = new Player();

        for (int i = 0; i < 5; i++){ //deals 5 cards to the player
            p.addCard(cards.deal());
        }
	}
	
	public void play(){
		// this method should play the game	
        System.out.println("Welcome to gambling!");
        scanner = new Scanner(System.in);
        int bet = 0;
        
        System.out.print("How much would you like to bet? Enter a number between 1 and 5: "); //user will input a valid entry so no need to error handle
        bet = scanner.nextInt();
        scanner.nextLine();
        if (bet > p.getBankroll()) {
            System.out.println("Can't bet more than you have.");
            return;
        }

        p.bets(bet);
        System.out.println("Here is your hand:");
        p.showHand();

        //code block for replacing cards
        System.out.println("Would you like to replace any of the cards? (y/n): ");
        String replace = scanner.nextLine();
        boolean finished = false;
        while (!finished) {
            if (replace.equalsIgnoreCase("y")) {
                for (int i = 4; i >= 0; i--) {
                    System.out.println("For card " + (i + 1) + ", enter 0 to not replace or 1 to replace: ");
                    int yesOrNo = scanner.nextInt();
                    if (yesOrNo == 1) {
                        p.removeCard(p.getHand().get(i)); 
                        p.addCard(cards.deal());
                    }
                }
                finished = true;
            }
            else if (replace.equalsIgnoreCase("n")) {
                finished = true;
            }
            else {
                System.out.println("Invalid input.");
                System.out.println("Would you like to replace any of the cards? (y/n): ");
                replace = scanner.nextLine();
            }
        }
        scanner.close();
        System.out.println("Here is your final hand: ");
        p.showHand(); 
        System.out.println(checkHand(p.getHand()));
	}
	
	public String checkHand(ArrayList<Card> hand){
		// this method should take an ArrayList of cards
		// as input and then determine what evaluates to and
		// return that as a String

        Collections.sort(hand); 
        //if statements to check each possible hand
		if (isRoyalFlush(hand)) {
            p.winnings(250);
            return "Royal Flush. Current bankroll: " + p.getBankroll();
            
        }
        else if (isStraightFlush(hand)) {
            p.winnings(50);
            return "Straight Flush. Current bankroll: " + p.getBankroll();
        }
        else if (isFourOfAKind(hand)) {
            p.winnings(25);
            return "Four of a Kind. Current bankroll: " + p.getBankroll();
        }
        else if (isFullHouse(hand)) {
            p.winnings(6);
            return "Full House. Current bankroll: " + p.getBankroll();
        }
        else if (isFlush(hand)) {
            p.winnings(5);
            return "Flush. Current bankroll: " + p.getBankroll();
        }
        else if (isStraight(hand)) {
            p.winnings(4);
            return "Straight. Current bankroll: " + p.getBankroll();
        }
        else if (isThreeOfAKind(hand)) {
            p.winnings(3);
            return "Three of a Kind. Current bankroll: " + p.getBankroll();
        }
        else if (isTwoPair(hand)) {
            p.winnings(2);
            return "Two Pair. Current bankroll: " + p.getBankroll();
        }
        else if (isPair(hand)) {
            p.winnings(1);
            return "Pair. Current bankroll: " + p.getBankroll();
        }
        else {
            return "High Card. Current bankroll: " + p.getBankroll();
        }
	}
	
	
	// you will likely want many more methods here
	// per discussion in class
    private Card decode(String stringOfCard) { //ex string: s1
        char suitFromString = stringOfCard.charAt(0); //from ex string, charAt(0) returns s1
        int rankFromString = Integer.parseInt(stringOfCard.substring(1));

        int suit = 0;

        switch (suitFromString) {
            case 'h':
                suit = 1;
                break;
            case 'd':
                suit = 2;
                break;
            case 's':
                suit = 3;
                break;
            case 'c':
                suit = 4;
                break;
        }

        return new Card(suit, rankFromString);

    }
    /* hands:
    High card: payout (0; you lose your bet)
    Pair: payout (1) - done
    Two pair: payout (2) - done
    Three of a kind: payout (3) - done
    Straight: payout (4) - done
    Flush: payout (5) - done
    Full House: payout (6) - done
    Four of a Kind: payout (25) - done
    Straight Flush: payout (50) - done
    Royal Flush: payout (250)  - done
    */


    private boolean isPair(ArrayList<Card> hand) {
        boolean pairFound = false;
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).getRank() == hand.get(i + 1).getRank()) {
                if (pairFound == true){ //if already found a pair, then become false as that is more than 1 pair
                    return false;
                }
                pairFound = true;
                i++;
            }
        }
        return pairFound;
    }

    private boolean isTwoPair(ArrayList<Card> hand) {
        int pairCount = 0;
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).getRank() == hand.get(i + 1).getRank()) {
                pairCount++;
                i++;
            }
        }
        return (pairCount == 2);
    }

    private boolean isThreeOfAKind(ArrayList<Card> hand) {
        int counter = 1;
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).getRank() == hand.get(i + 1).getRank()) {
                counter++;
                if (counter == 3) {
                    return true;
                }
            }
            else {
                counter = 1;
            }
        }
        return false;
    }

    private boolean isFlush(ArrayList<Card> hand) {
        int suit = hand.get(0).getSuit();

        for (Card card : hand) {
            if (card.getSuit() != suit) {
                return false;
            }
        }
        return true;
    }

    private boolean isStraight(ArrayList<Card> hand) {
        //catch duplicate ranks
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).getRank() == hand.get(i + 1).getRank()) {
                return false;
            }
        }

        //check consecutive ranks
        for (int i = 1; i < hand.size(); i++) {
            if (hand.get(i).getRank() != hand.get(i - 1).getRank() + 1) {
                return false;
            }
        }

        return true;
    }  

    private boolean isFourOfAKind(ArrayList<Card> hand) {
        for (int i = 0; i < hand.size() - 3; i++) {
            if (hand.get(i).getRank() == hand.get(i + 1).getRank() && hand.get(i).getRank() == hand.get(i + 2).getRank() && hand.get(i).getRank() == hand.get(i + 3).getRank()) {
                return true; //four of a kind found
            }
        }
        return false; //four of a kind not found
    }

    private boolean isFullHouse(ArrayList<Card> hand) {
        ArrayList<Card> handCopy = new ArrayList<>(hand);
        Card[] threeOfAKind = new Card[3];
        boolean threeOfAKindFound = false;
        boolean pairFound = false;

        for (int i = 0; i < handCopy.size() - 2; i++) { //finding threeOfAKind
            if (handCopy.get(i).getRank() == handCopy.get(i + 1).getRank() && handCopy.get(i).getRank() == handCopy.get(i + 2).getRank()) {
                threeOfAKind[0] = handCopy.get(i);
                threeOfAKind[1] = handCopy.get(i+1);
                threeOfAKind[2] = handCopy.get(i+2);
                threeOfAKindFound = true;
            }
        }

        if (threeOfAKindFound) { //only run if there is a three of a kind
            handCopy.remove(threeOfAKind[0]);
            handCopy.remove(threeOfAKind[1]);
            handCopy.remove(threeOfAKind[2]);

            if (handCopy.get(0).getRank() == handCopy.get(1).getRank()) { 
                pairFound = true;
            }
        }

        if (threeOfAKindFound && pairFound) {
            return true;
        }
        return false;  
    }

    private boolean isStraightFlush(ArrayList<Card> hand) {
        if (isStraight(hand) && isFlush(hand)) {
            return true;
        }
        return false;
    }

    private boolean isRoyalFlush(ArrayList<Card> hand) {
        int suit = hand.get(0).getSuit();
        boolean sameSuit = true;

        for (Card card : hand) {
            if (card.getSuit() != suit) {
                sameSuit = false;
                break;
            }
        }
        
        int[] royalFlushRanks = {1, 10, 11, 12, 13};
        boolean followsRanks = true;

        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getRank() != royalFlushRanks[i]) {
                followsRanks = false;
                break;
            }
        }

        if (sameSuit && followsRanks) {
            return true;
        }

        return false;
    }
}
