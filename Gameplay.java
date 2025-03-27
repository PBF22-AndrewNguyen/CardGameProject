import java.util.ArrayList;
import java.util.Scanner;

public class Gameplay {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deck gameDeck = new Deck();
        Hand playerHand = new Hand();
        Hand computerHand = new Hand();

        // Draw 7 cards for each player at the start
        for (int i = 0; i < 7; i++) {
            playerHand.addCard(gameDeck.drawCard());
            computerHand.addCard(gameDeck.drawCard());
        }

        boolean playerTurn = true;

        // Start the game loop
        while (!gameDeck.isDeckEmpty() && (playerHand.getTotalCards() > 0 || computerHand.getTotalCards() > 0)) {
            if (playerTurn) {
                System.out.println("\nYour turn! Your hand:");
                playerHand.printHand();
                String rank = askForRank(playerHand);

                if (computerHand.hasRank(rank)) {
                    ArrayList<String> receivedCards = computerHand.giveCards(rank);  // Computer gives cards and they are removed
                    System.out.println("You received: " + receivedCards);
                    for (String card : receivedCards) {
                        playerHand.addCard(card);
                        checkCardLimit(playerHand, scanner); // Ensure the player doesn't exceed 10 cards
                    }
                    checkForCompleteSets(playerHand); // Check for any completed sets of 4
                } else {
                    System.out.println("Go Fish!");
                    if (!gameDeck.isDeckEmpty()) {
                        String card = gameDeck.drawCard();
                        playerHand.addCard(card);
                        checkCardLimit(playerHand, scanner); // Ensure the player doesn't exceed 10 cards
                    }
                    playerTurn = false; // Switch turn to computer
                }
            } else {
                System.out.println("\nComputer's turn...");
                String rank = getRandomRank(computerHand); // Computer randomly picks a rank from its hand
                System.out.println("Computer asks for: " + rank);

                if (playerHand.hasRank(rank)) {
                    ArrayList<String> receivedCards = playerHand.giveCards(rank);  // Player gives cards and they are removed
                    System.out.println("Computer received: " + receivedCards);
                    for (String card : receivedCards) {
                        computerHand.addCard(card);
                        checkCardLimit(computerHand, scanner); // Ensure computer doesn't exceed 10 cards
                    }
                    checkForCompleteSets(computerHand); // Check for any completed sets of 4
                } else {
                    System.out.println("Go Fish!");
                    if (!gameDeck.isDeckEmpty()) {
                        String card = gameDeck.drawCard();
                        computerHand.addCard(card);
                        checkCardLimit(computerHand, scanner); // Ensure computer doesn't exceed 10 cards
                    }
                    playerTurn = true; // Switch turn back to player
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

    // Ask the user which rank they want to request
    public static String askForRank(Hand hand) {
        Scanner scanner = new Scanner(System.in);
        String rank = "";
        boolean valid = false;

        while (!valid) {
            System.out.print("Enter the rank you want to ask for: ");
            rank = scanner.next().toUpperCase();
            if (hand.hasRank(rank)) {
                valid = true;
            } else {
                System.out.println("You don't have any " + rank + "s! Try another rank.");
            }
        }
        return rank;
    }

    // Method to generate a random rank from the computer's hand
    public static String getRandomRank(Hand hand) {
        ArrayList<String> ranks = hand.getRanks();
        return ranks.get((int) (Math.random() * ranks.size()));
    }

    // Check if a player has completed any sets of 4 cards (Rule 4)
    public static void checkForCompleteSets(Hand hand) {
        ArrayList<String> ranks = hand.getRanks();
        for (String rank : ranks) {
            if (hand.getCardsOfRank(rank).size() == 4) {
                System.out.println("You completed a set of four " + rank + "s!");
                hand.removeCardsOfRank(rank);
                hand.addPoint(); // Add 1 point for completing a set
            }
        }
    }

    // Method to check if a player's hand exceeds 10 cards and prompt to discard
    public static void checkCardLimit(Hand hand, Scanner scanner) {
        if (hand.getTotalCards() > 10) {
            System.out.println("You have more than 10 cards! Please discard one.");
            hand.printHand();
            System.out.print("Enter the card you want to discard: ");
            String cardToDiscard = scanner.next();
            hand.discardCard(cardToDiscard);
            System.out.println("You discarded: " + cardToDiscard);
        }
    }
}
