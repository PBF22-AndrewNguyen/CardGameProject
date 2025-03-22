// Card Game test class
public class Gameplay{
    public static void main(String[] args) {
        Deck gameDeck = new Deck();
        Hand playerHand = new Hand();
        for (int i = 0; i < 5; i++) {
            playerHand.addCard(gameDeck.pullCard()); 
        }
        playerHand.printHand();
        gameDeck.printCards();
    }
}