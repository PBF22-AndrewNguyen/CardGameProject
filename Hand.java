import java.util.ArrayList;

public class Hand {
    private ArrayList<String> hand;
    private int points;

    public Hand() {
        this.hand = new ArrayList<>();
        this.points = 0;
    }

    // Add a card to the hand
    public void addCard(String card) {
        hand.add(card);
    }

    // Get all the ranks in the hand (without duplicates)
    public ArrayList<String> getRanks() {
        ArrayList<String> ranks = new ArrayList<>();
        for (String card : hand) {
            String rank = card.split("-")[0]; // Split to get rank (e.g., "A" from "A-C")
            if (!ranks.contains(rank)) {
                ranks.add(rank);
            }
        }
        return ranks;
    }

    // Check if the hand has at least one card of the specified rank
    public boolean hasRank(String rank) {
        for (String card : hand) {
            if (card.split("-")[0].equals(rank)) {
                return true;
            }
        }
        return false;
    }

    // Get all the cards of a specific rank
    public ArrayList<String> getCardsOfRank(String rank) {
        ArrayList<String> cardsOfRank = new ArrayList<>();
        for (String card : hand) {
            if (card.split("-")[0].equals(rank)) {
                cardsOfRank.add(card);
            }
        }
        return cardsOfRank;
    }

    // Remove all cards of a specific rank from the hand
    public void removeCardsOfRank(String rank) {
        hand.removeIf(card -> card.split("-")[0].equals(rank));
    }

    // Discard a card by name
    public void discardCard(String card) {
        hand.remove(card);
    }

    // Get the total number of cards in the hand
    public int getTotalCards() {
        return hand.size();
    }

    // Get the current score (number of completed sets of 4)
    public int getPoints() {
        return points;
    }

    // Add 1 point when a player completes a set of 4
    public void addPoint() {
        points++;
    }

    // Print the hand
    public void printHand() {
        if (hand.isEmpty()) {
            System.out.println("Your hand is empty.");
        } else {
            System.out.println("Your hand:");
            for (String card : hand) {
                System.out.println(card);
            }
        }
    }

    // New method to give all cards of a specific rank to another player
    public ArrayList<String> giveCards(String rank) {
        ArrayList<String> cardsToGive = getCardsOfRank(rank);
        removeCardsOfRank(rank); // This will remove the cards from the player's hand
        return cardsToGive; // Return the cards of the requested rank
    }
}
