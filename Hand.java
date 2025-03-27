import java.util.ArrayList;
import java.util.HashMap;

public class Hand {
    private ArrayList<String> hand;
    private int points;

    public Hand() {
        hand = new ArrayList<>();
        points = 0;
    }

    public void addCard(String card) {
        hand.add(card);
        checkForCompleteSet();
    }

    public boolean hasSuit(String suit) {
        for (String card : hand) {
            if (card.endsWith(suit)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> giveCards(String suit) {
        ArrayList<String> givenCards = new ArrayList<>();
        hand.removeIf(card -> {
            if (card.endsWith(suit)) {
                givenCards.add(card);
                return true;
            }
            return false;
        });
        return givenCards;
    }

    private void checkForCompleteSet() {
        HashMap<String, ArrayList<String>> rankGroups = new HashMap<>();

        for (String card : hand) {
            String rank = card.split("-")[0];
            rankGroups.putIfAbsent(rank, new ArrayList<>());
            rankGroups.get(rank).add(card);
        }

        for (String rank : rankGroups.keySet()) {
            if (rankGroups.get(rank).size() == 4) {
                System.out.println("Collected all suits of rank " + rank + "! +1 point.");
                hand.removeAll(rankGroups.get(rank));
                points++;
            }
        }
    }

    public void printHand() {
        System.out.println("Your hand: " + hand);
    }

    public int getPoints() {
        return points;
    }

    public int getTotalCards() {
        return hand.size();
    }

    // Method to discard a card
    public void discardCard(String card) {
        if (hand.contains(card)) {
            hand.remove(card);
            System.out.println("You discarded: " + card);
        } else {
            System.out.println("Card not found in your hand!");
        }
    }
}