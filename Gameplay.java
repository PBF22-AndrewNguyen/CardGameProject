import java.util.ArrayList;
import java.util.Scanner;

public class Gameplay {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deck gameDeck = new Deck();
        Hand playerHand = new Hand();
        Hand computerHand = new Hand();

        // Draw 7 cards for each player
        for (int i = 0; i < 7; i++) {
            playerHand.addCard(gameDeck.drawCard());
            computerHand.addCard(gameDeck.drawCard());
        }

        boolean playerTurn = true;
        String[] suits = {"C", "S", "D", "H"}; // Clubs, Spades, Diamonds, Hearts

        // Start the game loop
        while (!gameDeck.isDeckEmpty() && (playerHand.getTotalCards() > 0 || computerHand.getTotalCards() > 0)) {
            if (playerTurn) {
                System.out.println("\nYour turn! Your hand:");
                playerHand.printHand();
                System.out.print("Call a suit (C, S, D, H): ");
                String suit = scanner.next().toUpperCase();

                if (computerHand.hasSuit(suit)) {
                    ArrayList<String> receivedCards = computerHand.giveCards(suit);
                    System.out.println("You received: " + receivedCards);
                    for (String card : receivedCards) {
                        playerHand.addCard(card);
                        checkCardLimit(playerHand, scanner); // Check the card limit immediately after drawing
                    }
                    // Keep playing if successful
                } else {
                    System.out.println("Go Fish!");
                    if (!gameDeck.isDeckEmpty()) {
                        playerHand.addCard(gameDeck.drawCard());
                        checkCardLimit(playerHand, scanner); // Check the card limit immediately after drawing
                    }
                    playerTurn = false; // Pass turn
                }
            } else {
                System.out.println("\nComputer's turn...");
                String suit = suits[(int) (Math.random() * suits.length)];
                System.out.println("Computer calls: " + suit);

                if (playerHand.hasSuit(suit)) {
                    ArrayList<String> receivedCards = playerHand.giveCards(suit);
                    System.out.println("Computer received: " + receivedCards);
                    for (String card : receivedCards) {
                        computerHand.addCard(card);
                        checkCardLimit(computerHand, scanner); // Check the card limit immediately after drawing
                    }
                    // Keep playing if successful
                } else {
                    System.out.println("Go Fish!");
                    if (!gameDeck.isDeckEmpty()) {
                        computerHand.addCard(gameDeck.drawCard());
                        checkCardLimit(computerHand, scanner); // Check the card limit immediately after drawing
                    }
                    playerTurn = true; // Pass turn
                }
            }
        }

        // End of the game, determine the winner based on matching sets
        System.out.println("\nGame over!");
        System.out.println("Player Points: " + playerHand.getPoints());
        System.out.println("Computer Points: " + computerHand.getPoints());

        if (playerHand.getPoints() > computerHand.getPoints()) {
            System.out.println("You win!");
        } else if (playerHand.getPoints() < computerHand.getPoints()) {
            System.out.println("Computer wins!");
        } else {
            System.out.println("It's a tie!");
        }

        scanner.close();
    }

    // Method to check if a player exceeds the 10-card limit and prompt them to discard
    public static void checkCardLimit(Hand hand, Scanner scanner) {
        if (hand.getTotalCards() > 10) {
            System.out.println("You have more than 10 cards! Please discard one.");
            hand.printHand();
            System.out.print("Enter the card you want to discard: ");
            String cardToDiscard = scanner.next();
            hand.discardCard(cardToDiscard);
        }
    }
}