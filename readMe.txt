Card.java
This class was the simplest of the four, including only a couple methods. There are 4 smaller methods, getSuit(), getRank(), setSuit(), and setRank(), althought the two setter methods
were not needed as they weren't used (if I recall correctly). The other two methods are the compareTo() and toString(). For the compareTo(), the method takes a card to compare to. If
the rank of the original card is not equal to the rank of the card entered in the parameter, it will return Integer.compare(original rank, parameter card rank). This will return 
either a positive number, negative number, or 0, depending on how the two cards compare. This allows for sorting hands by rank later on. An additional part of the method is to do the
same but using the suit of the two cards if the rank of both cards are the same. For the toString() method, two switch/case statements are used, along with two initially empty 
strings (cardSuit and cardRank). The first switch statement looks at the value of this.suit, which will range from 1 to 4. 4 cases are used, setting 1 to "hearts", 2 to "diamonds", 3 
to "spades", and 4 to "clubs". Whichever value matches this.suit, cardSuit is set equal to the corresponding suit. Similar steps are seen in the second switch statement, which using 
this.rank in the switch. The cases for this switch include 1, 11, 12, 13, and a default. If the rank is 1, then the rank is set to "Ace", if 11, then "Jack", if 12, then "Queen", and
if 13, then "King". If the value of this.rank does not equal any of these 4 values, then it just refers to the default case, which sets cardRank to String.valueOf(this.rank). Once
the rank and suit are determined the toString returns cardRank + " of " + cardSuit.

Deck.java
This class is about the same as the Card.java class in complexity. The constructor initializes an array called cards, which is equal to DECK_SIZE, an instance variable that is set
equal to 52. Using a nested for loop, looping through each suit (1 to 4) and then through each card (1 to 13), a deck of 52 is created. The shuffle method goes through every card 
value from cards.length - 1 (so 51, the index of the last card in the array) to 1. Variable j is a random variable from 0 to i + 1. So for example, if i is 51 (so on the first 
run of the loop), then j will be a random value from 0(inclusive) to 52(exclusive). A variable temp is set equal to the Card value at index i, and then the value at index i is set
to the value at index j. Finally, the value at index j is set equal to temp, which was the value originally at index i. This is repeated for every card in the deck for a fully 
shuffled deck that is different every time you run the shuffle method. The last method in the class is the deal() method, which deals a card from the deck. This method uses the 
instance variable top, which was earlier initialized to 0, which represents the index of the card at the top of the deck. So long as the top variable is less than the static variable
DECK_SIZE, then the card at the index of whatever top is equal to is returned and top is incremented by 1. This can be used until top is equal to the DECK_SIZE, in which case the 
deck is now empty and no more cards can be dealt. 

Player.java
The player class hold a decent bit of methods, but most of them are pretty small. There are also 3 instance variables initialized in the constructor. Bet is initialized to 0, and 
will be changed once the game runs. Bankroll is initialized to 50, the starting amount of money that a player can have. Finally, hand is initialized to a new ArrayList of Card
objects. The addCard and removeCard methods use .add() and .remove() from the imported java.util.ArrayList packaged. The methods take a parameter "Card c", so it will either add a 
card with the suit and rank of c, or remove a card that matches the suit and rank of c. The method bets accepts an amount (amt) as a parameter, setting the bet instance variable 
equal to that amount and subtracting that amount from the bankroll. The method winnings takes a parameter odds that will be used to multiply the bet by. This resulting value is added
to the bankroll, and the bet is reset to 0. The getBankroll() method is a method that simply returns the value of bankroll. The showHand() method uses Collections.sort() to sort the 
hand and then prints out each card on its own line using the toString method created back in the Card.java class. Finally, the getHand() method simply returns hand.

Game.java
The main parts of this class are the play(), checkHand(), and decode() methods. The decode() method is solely used for when you execute java PokerTest with a testhand. It'll take 
the char at index 0 as the suit and the rest as the rank. The char at index 0, which can be either 'h', 's', 'c', 'd', is ran through a switch case and converted to it's corresponding
integer representation, as that is how the suit is initially formatted in the original Card.java class. Once the suit value is converted, a new Card object is created. The checkHand()
method begins by sorting the hand, and then runs through a top down group of if-else if statements for each possible poker hand. For each possible hand, a method is created that will
check whether or not the player's current hand corresponds to the winning hand. If not, it will go down to the next winning hand. If none of the winning hands are seen in the player's
hand, then it defaults to the else statement, which returns "High Card". For each return statement for every possible winning hand, the type of winning hand is returned along with the
new bankroll (through the p.getBankroll() method). Before the return statement, the p.winnings() method is ran, with the odds parameter being the payout value each winning hand 
corresponds to (given in the README.md). Finally, the play method is the block of code that actually runs the game. Using a scanner, it first will prompt the player for a bet amount
between 1 and 5. No error/exception handling is done as it is said on EdDiscussion that a valid input will always be made. An if statement is there for if the bet is greater than
the bankroll, but this is not applicable for the game in the current state, as the player only plays once before a completely new game is started. p.bets() is ran, with the user input
bet being the amt. The hand is then displayed to the player. The player is then prompted if they would like to replace any cards. This is a while loop that runs until the variable
finished is true. This way, if the user inputs a value other than "y" or "n", the loop runs again and prompts for a valid input. If the user inputs "n", then the loops simply 
terminates and moves to the next step. If the user inputs "y", then they will be prompted for each card whether they want to replace it. 0 for no replacement, 1 for replace. 
Realistically, another while loop could be made here in case an invalid input is entered. Either way, if the user enters 1, then that card is removed, and a new card is drawn using
the respective removeCard(), getHand(), addCard(), and deal() methods from Player.java and Deck.java. Once all cards in the hand have been ran through, the finished variable is set 
to true and the loop is exited. On the last step, the final hand is displayed to the player, along with what winning hand they got (and their final bankroll). The game then finally
ends.
