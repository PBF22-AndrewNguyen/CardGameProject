public class Hand {
    private String[] Hand;
    public Hand() {
        this.Hand = new String[10];
    }
    public void addCard(String card) {
        int index = 0;
        // Checks if a card will replace an empty spot
        for(int i = 0; i < this.Hand.length; i++) {
            if(this.Hand[i]==null) {
                index = i;
                break;
            }
        }
        this.Hand[index] = card;
    }
    // Debug method to show what cards the player has
    public void printHand() {
        for (int i = 0; i < this.Hand.length; i++) {
            System.out.println(this.Hand[i]);
        }
    }
    // public static void main(String[] args) {
    //     Hand playerHand = new Hand();
    //     playerDeck.initializeDeck();
    //     playerDeck.printCards();
    // }
}