import java.util.Objects;
import java.util.Scanner;

public class SpelController {
    Scanner input = new Scanner(System.in);
    Score score = new Score();
    private int bestOff;
    private int userChoice;

    private boolean playGame;

    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }

    public static void welcomeMessage() {
        System.out.println("Welcome to the game: ROCK/PAPER/SCISSORS!");
    }

    private static void bestOffMessage() {
        System.out.println("Do you want to play best off 1, 3, 5 or 7?");
    }

    private static void wrongBestOffInput() {
        System.out.println("Wrong input, try again");
    }

    private static void bestOffConfirm(int value) {
        System.out.printf("\nWe will play best of %d hand%s\n", value, value == 1 ? "" : "s");
    }

    private static void inputPlayerChoiceMessage() {
        System.out.println("Choose your hand: 1 = Rock, 2 = Paper, 3 = Scissors");
    }

    private static void wrongPlayerChoiceMessage() {
        System.out.println("Wrong input, try again");
    }

    private static void randomComputerChoice() {
        int choice = (int) (Math.random() * 3 + 1);
    }

    private static String translate(int value) {
        return new String[]{"Rock", "Paper", "Scissors"}[value - 1];
    }

    private void winningHand(int userInput, int computerInput) {
        if (Objects.equals(translate(userInput), translate(computerInput))) {
            System.out.println("It's a tie!\n");
        } else if (Objects.equals(translate(userInput), "Rock") && Objects.equals(translate(computerInput), "Scissors")) {
            handleWin();
        } else if (Objects.equals(translate(userInput), "Paper") && Objects.equals(translate(computerInput), "Rock")) {
            handleWin();
        } else if (Objects.equals(translate(userInput), "Scissors") && Objects.equals(translate(computerInput), "Paper")) {
            handleWin();
        } else {
            score.addComputerWin();
            System.out.println("You lost this round!\n");
        }
    }

    private void showScore() {
        System.out.printf("The score is: %d (you) - %d (me)\n", score.getUserWins(), score.getComputerWins());
    }

    private int checkNextInt() {
        if (input.hasNextInt()) {
            return input.nextInt();
        } else {
            input.nextLine();
            return 0;
        }
    }

    private void resetVars() {
        bestOff = 0;
        userChoice = 0;
        playGame = false;
    }

    public void playGame() {
        bestOffMessage();
        int choice = checkNextInt();
        if (choice == 1 || choice == 3 || choice == 5 || choice == 7) {

            bestOffConfirm(choice);
            bestOff = choice;
            playGame = true;

            while (playGame) {
                // Ask player what they want to throw
                inputPlayerChoiceMessage();
                int choice2 = checkNextInt();
                if (choice2 == 1 || choice2 == 2 || choice2 == 3) {
                    userChoice = choice2;
                    int computerChoice = (int) (Math.random() * 3 + 1);
                    System.out.printf("\nYou chose %s, I chose %s\n", translate(userChoice), translate(computerChoice));
                    winningHand(userChoice, computerChoice);
                    showScore();

                    if (score.getUserWins() > (bestOff / 2) || score.getComputerWins() > (bestOff / 2)) {
                        score.addNumberOfGames();
                        if (score.getUserWins() > (bestOff / 2)) {
                            System.out.printf("\n\nYOU WON\n-------------\nTotal Games: %d\nScore: %d (you) - %d (me)\n", score.getNumberOfGames(), score.getUserWins(), score.getComputerWins());
                        } else if (score.getComputerWins() > (bestOff / 2)) {
                            System.out.printf("\n\nGAME OVER\n-------------\nTotal Games: %d\nScore: %d (you) - %d (me)\n", score.getNumberOfGames(), score.getUserWins(), score.getComputerWins());
                        }

                        System.out.println("\n\nDo you want to play again? (Y/N)");
                        String playAgain = input.next();
                        if (playAgain.equalsIgnoreCase("Y")) {
                            resetVars();
                            playGame();
                        } else {
                            System.out.println("\n\nGoodbye!");
                            System.exit(0);
                        }

                    }
                } else {
                    wrongPlayerChoiceMessage();
                }
            }

        } else {
            wrongBestOffInput();
            playGame();
        }
    }

    private void handleWin() {
        score.addUserWin();
        System.out.println("You won this round!\n");
    }

}