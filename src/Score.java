public class Score {

    private int userWins = 0;
    private int computerWins = 0;
    private int numberOfGames = 0;

    public int getUserWins() {
        return this.userWins;
    }

    public void addUserWin() {
        userWins++;
    }

    public int getComputerWins() {
        return this.computerWins;
    }

    public void addComputerWin() {
        computerWins++;
    }

    public int getNumberOfGames() {
        return this.numberOfGames;
    }

    public void addNumberOfGames() {
        numberOfGames++;
    }
}
