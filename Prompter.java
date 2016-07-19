import java.io.Console;
public class Prompter {
// all io stuff here
    private Game mGame;
    public Prompter(Game game) {
        mGame = game;
    }
    
    public void play() {
        while (mGame.getRemainingTries() > 0 && !mGame.isSolved()) {
            displayProgress();
            promptForGuess();
        }
        if (mGame.isSolved()) {
            System.out.printf("Congratulations you won with %d tries remaining.\n",
                mGame.getRemainingTries());
        } else {
            System.out.printf("Bummer! The word was %s. :(\n",
                mGame.getAnswer());
        }
    }
    
    public boolean promptForGuess() {
        Console console = System.console();
        boolean isHit = false;
        boolean isValidGuess = false;
        
        while (! isValidGuess) {
			String guessAsString = console.readLine("Enter a letter:  ");
			// char guess = guessAsString.charAt(0);
			try {
                isHit = mGame.applyGuess(guessAsString);
                isValidGuess = true;
			} catch (IllegalArgumentException iae) {
                console.printf("%s.  Please try again.\n", iae.getMessage());
			}
        }
        return isHit;
    }
    public void displayProgress() {
        System.out.printf("You have %d tries left to solve:  %s\n",
            mGame.getRemainingTries(),
            mGame.getCurrentProgress());
        
    }
}