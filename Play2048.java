/**
* Name: John Otto
* Pennkey: jwotto
* Execution: java Play2048 [filename]
*
* Description: represents the actual 2048 game. It takes in one command line 
* argument of the data of each and then runs the game until a player has won
* by reaching 2048 or has last by running out of moves.
**/
public class Play2048 {
    public static void main(String [] args) {
        String filename = args[0];
        // set width and height of the game
        PennDraw.setCanvasSize(400, 400);
        // the game runs at 45 frames per second
        PennDraw.enableAnimation(45);
        // instantiate a Game object with a command line argument for tile data
        Game playGame = new Game(args[0]);
        
        // while the player has neither won nor lost, keep running the game
        while (!playGame.gameOver()) {
            playGame.update();
            playGame.draw();
        }
        // draw a victory or loss screen 
        playGame.drawGameCompleteScreen();
    }
}
