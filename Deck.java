// Card Game Project
public class Deck {
    private final String[][] Cards;
        public Deck() {
            // Initalizes an empty deck of cards
            this.Cards = new String[4][13];
            // Initalizes all of the cards
            String[] Ranks = {"A","2","3","4","5","6","7","8","9","T","J","Q","K"};
            String[] Suites = {"C","S","D","H"};
    
            for (int i = 0; i < 4; i++) {
                for(int x = 0; x < 13; x++) {
                    this.Cards[i][x] = Ranks[x]+"-"+Suites[i];
                }
            }
        }
        // Debug method to see if there are cards in the deck
        public void printCards() {
            for (int i = 0; i < 4; i++) {
                for(int x = 0; x < this.Cards[i].length; x++) {
                    System.out.println(this.Cards[i][x]);
                }
            }
        }
        // Debug method to see if a card can be picked from object
        public String pullCard() {
            boolean cardChosen = false;
            int x = 0;
            int i = 0;
            // Continously chooses a random card that is not null within the array to set
            while(!cardChosen){
            x = (int)(Math.random()*13);
            i = (int)(Math.random()*4);
            if(Cards[i][x]!=null) cardChosen = true;
            }
            String card = Cards[i][x];
            removeCard(card);
            return card;
        }
        public void removeCard(String card) {
            // Finds where the pulled card is within the given deck
            int removalRow = 0;
            int removalColumn = 0;
            for (int i = 0; i < 4; i++) {
                for (int x = 0; x < this.Cards[i].length; x++) {
                    if(card.equals(this.Cards[i][x])) {
                        removalColumn=x;
                        removalRow=i;
                        break;
                    }
                }
            }
            // Creates a temporary array used to store the new set of cards for that particular suite
            String[] tempCardArray = new String[this.Cards[removalRow].length-1];
            int abstractIndex = 0;
            for(int i = 0; i < this.Cards[removalRow].length;i++) {
                if(i==removalColumn);
                else {
                    tempCardArray[abstractIndex] = this.Cards[removalRow][i];
                    abstractIndex++;
                }
            }
            this.Cards[removalRow] = tempCardArray;
        }
        // Used to test if methods work
    public static void main(String[] args) {
        Deck gameDeck = new Deck();
        Hand playerHand = new Hand();
        Hand computerHand = new Hand();
        // playerDeck.initializeDeck();
        // playerDeck.printCards();
        // System.out.println(playerDeck.pullCard());
        for (int i = 0; i < 5; i++) {
            playerHand.addCard(gameDeck.pullCard()); 
        }
        for (int i = 0; i < 5; i++) {
            computerHand.addCard(gameDeck.pullCard()); 
        }
        playerHand.printHand();
        computerHand.printHand();
        gameDeck.printCards();

    }
}