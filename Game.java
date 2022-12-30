/**
* Name: John Otto
* Pennkey: jwotto
* Execution: N/A
*
* Description: a class representing the place where 2048 is actally played.
* It keeps tracks of the each tile and the board as a whole while implementing
* methods which update and draw the tiles in the board.
**/
public class Game {
    // instantiates a tile array
    private Tile[] tileArray;
    // instantiates a board object into the game class
    private Board game;
    // a double of the half length of each tile that is immutable
    public static final double LEN = 0.115;
 
    public Game(String filename) {
        // read with In
        In in = new In(filename);
            
        // create new empty tile and board arrays for a 4x4 2048 game
        Tile [][] boardInput = new Tile [4][4];
        tileArray = new Tile [16];
     
        // create new arrays for each variable in the file
        double [] xPos = new double[16];
        double [] yPos = new double[16];
        int [] value = new int[16];
        
        // store the values from the file in each array then the in the tile array
        for (int i = 0; i < tileArray.length; i++) {
           xPos[i] = in.readDouble();
           yPos[i] = in.readDouble();
           value[i] = in.readInt();
           tileArray[i] = new Tile(xPos[i], yPos[i], value[i]);
       }
     
        // with that array of tiles, assign each one to a position within the board
        int index = 0;
        for (int i = 0; i < boardInput.length; i++) {
            for (int j = 0; j < boardInput[i].length; j++) {
                boardInput[i][j] = tileArray[index];
                index++;
            }
        }
        game = new Board(boardInput, 0);
        
        in.close();
    }
 
    /**
    * Inputs: N/A
    * Outputs: void
    * Description: a draw method which colors each tile relative to each ones
    * value. The gradient goes from a beign white to brighr pink then to light
    * purple and finally dark purple.
    */
    public void draw() {
        // creates a draw grey background
        PennDraw.clear(70, 70, 70);
        
        
        for (int i = 0; i < game.getBoard().length; i++) {
            for (int j = 0; j < game.getBoard()[i].length; j++) {
                // intialize a tile's value, xpos, ypos for readability
                int tileValue = game.getBoard()[i][j].getValue();
                double xPos = game.getBoard()[i][j].getXPos();
                double yPos = game.getBoard()[i][j].getYPos();
                
                if (tileValue == 0) {
                    // draws a gray square with no text value
                    PennDraw.setPenColor(125, 125, 125);
                    PennDraw.filledSquare(xPos, yPos, LEN);
                } else {
                    // initially, draw a cream colored tile with a black value
                    int red = 252;
                    int green = 242;
                    int blue = 216;
                    // intialize an index to determine the # of times to decrement
                    int index = 0;

                    if (game.getBoard()[i][j].getValue() <= 64) {
                        // for each increase in a tile's value decrement green by .8
                        for (int k = 2; k < tileValue; k *= 2) {
                            index++;
                        }
                        PennDraw.setPenColor(red, 
                                            (int) (green * Math.pow(.8, index)),
                                            blue);
                        PennDraw.filledSquare(xPos, yPos, LEN);
                    } else {
                        /* 
                        * if the tile is 64 or greater we start at the same color
                        * then want to decrement the amount of red to turn to 
                        * more purple then more blue. 
                        */
                        for (int l = 64; l < tileValue; l *= 2) {
                            index++;
                        }
                        PennDraw.setPenColor((int) (red * Math.pow(.8, index)),
                                                (int) (green * .262), blue);
                        PennDraw.filledSquare(xPos, yPos, LEN);
                    }
                    
                    // draw text that represents the value of the tile
                    PennDraw.setPenColor(10, 10, 10);
                    String value = Integer.toString(tileValue);
                    PennDraw.setFontBold();
                    PennDraw.setFontSize(28);
                    PennDraw.text(xPos, yPos, value);
                }
            }
        }
        PennDraw.advance();
    }
 
    /**
    * Inputs: N/A
    * Outputs: void
    * Description: this method will update the position of the tiles
    * according to the keyboard press of the player and account for collisons
    * after a player has move. Also, if the player has moved, a tile of either
    * value 2 or 4 will be spawned.
    */
    public void update() {
        if (game.moveWithWASD()) {
            game.spawnTileRandomly();
        }
    }
 
    /**
    * Inputs: N/A
    * Outputs: returns true if the player has won and false otherwise
    * Description: a method that checks if any tile equals 2048 and outputs
    * true if so.
    */
    public boolean didPlayerWin() {
        // check if the most valuable tile equals 2048
        int mostValuable = 0;
        for (int i = 0; i < game.getBoard().length; i++) {
            for (int j = 0; j < game.getBoard()[i].length; j++) {
                if (game.getBoard()[i][j].getValue() > mostValuable) {
                    mostValuable = game.getBoard()[i][j].getValue();
                }
            }
        }
        // return true if the tile is 2048 and false if not
        return mostValuable == 2048;
    }

    /**
    * Inputs: N/A
    * Outputs: returns true if the player has lost and false otherwise
    * Description: a method that checks if the player can move in any cardinal
    * direction and if they can not then the player has lost.
   */
    public boolean didPlayerLose() {
        return !game.canMoveLeft() && !game.canMoveRight() && 
               !game.canMoveUp() && !game.canMoveDown();
    }

    /**
    * Inputs: N/A
    * Outputs: returns true if the game is over and false otherwise
    * Description: a method to determine if the game has ended
    */
    public boolean gameOver() {
       return didPlayerWin() || didPlayerLose();
    }
 
    /**
    * Inputs: N/A
    * Outputs: void
    * Description: Draws either a victory of loss screen. If the player has won,
    * the screen will say, "You Won 2048 with x moves!". If you hvae lost, the 
    * screen will say, "You made x moves and have lost..."
    */
    public void drawGameCompleteScreen() {
       // set pen color to be black for the text and the text size to be 14
       PennDraw.setPenColor(0, 0, 0);
       PennDraw.setFontSize(14);
       // check if the game is over and clear
       if (gameOver()) {
           PennDraw.clear();
           // draw some text depending on whether the player has won or lost
           if (didPlayerWin()) {
               PennDraw.text(.5, .5, "You won 2048 with " + game.getNumOfMoves() + 
                                     " moves!");
           } else if (didPlayerLose()) {
               PennDraw.text(.5, .5, "You made " + game.getNumOfMoves() + 
                                     " moves and have lost...");
           }
           PennDraw.advance();
        }
    }
}
