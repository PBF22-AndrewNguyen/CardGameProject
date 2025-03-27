import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<String> cards;

    public Deck() {
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
        String[] suits = {"C", "S", "D", "H"}; // Clubs, Spades, Diamonds, Hearts
        cards = new ArrayList<>();

        for (String rank : ranks) {
            for (String suit : suits) {
                cards.add(rank + "-" + suit);
            }
        }
        Collections.shuffle(cards);
    }

    public String drawCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        }
        return null; // Deck is empty
    }

    public boolean isDeckEmpty() {
        return cards.isEmpty();
    }
}