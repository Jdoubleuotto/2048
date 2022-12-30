/**
* Name: John Otto
* Pennkey: jwotto
* Execution: N/A
*
* Description: create a class that represents the board of 2048. The board is
* comprised of a 2D array of tiles and has methods that determine if the board can
* move in a certain direction, shift the board a certain direction, and 
* spawn tiles randomly. 
**/
public class Board {
    // create a 2d array for the 2048 board. This 2D array will be a 4x4 grid.
    private Tile[][] board;
    // a variables that keeps track of the numebr of moves in a round
    private int numOfMoves;
        
    // create a constructor with the 2D grid and total number of moves
    public Board(Tile[][] board, int numOfMoves) {
        this.board = board;
        this.numOfMoves = numOfMoves;
    }
   
    /**
    * Inputs: N/A
    * Outputs: boolean of whether the player can move left or not
    * Description: a method that checks two cases to determine if a player can
    * move to the left. This method will be used to determine if the player as 
    * lost and if a tile should spawn when the user presses 'a'.
    */
    public boolean canMoveLeft() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 1; j < board[i].length; j++) {
                // intialize tile variables for readability
                int currTile = board[i][j].getValue();
                int leftTile = board[i][j - 1].getValue();
                
                // check if there is a empty tile left of a 'live' tile
                // or if the value of adjancent tiles are equal and > 0
                if (leftTile == 0 && currTile != 0 ||
                    currTile == leftTile && currTile > 0) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
    * Inputs: N/A
    * Outputs: a boolean of whether a player can move right or not
    * Description: a method which checks two cases to determine if a player can
    * move to right.This method will be used to determine if the player as lost 
    * and if a tile should spawn when the user presses 'd'.
    */
    public boolean canMoveRight() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length - 1; j++) {
                // intialize tile variables for readability
                int currTile = board[i][j].getValue();
                int rightTile = board[i][j + 1].getValue();

                // checks if there is a empty tile right of a 'live' tile
                // or if the value of adjancent tiles are equal and > 0
                if (currTile != 0 && rightTile == 0 ||
                    currTile == rightTile && currTile > 0) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
    * Inputs: N/A
    * Outputs: a boolean of whether a player can move up or not
    * Description: a method which checks two cases to determine if a player can
    * move up. This method will be used to determine if the player as lost and if a 
    * tile should spawn when the user presses 'w'.
    */
    public boolean canMoveUp() {
        for (int i = 1; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // intialize tile variables for readability
                int currTile = board[i][j].getValue(); 
                int upTile  = board[i - 1][j].getValue();

                // check if there is a empty tile up of a 'live' tile
                // or if the value of vertically adjancent tiles are > 0 and equal
                if (currTile != 0 && upTile == 0 ||
                    currTile == upTile && currTile > 0) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
    * Inputs: N/A
    * Outputs: a boolean of whether the board can move down or not
    * Description: a method which checks two cases to determine if a player can
    * move down. This method will be used to determine if the player as lost and 
    * if a tile should spawn when the user presses 's'.
    */
    public boolean canMoveDown() {
        for (int i = 0; i < board.length - 1; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // intialize tile variables for readability
                int currTile = board[i][j].getValue(); 
                int downTile  = board[i + 1][j].getValue();
                // check if there is a empty tile down of a 'live' tile
                // or if the value of vertically adjancent tiles are > 0 and equal
                if (currTile != 0 && downTile == 0 ||
                    currTile == downTile && currTile > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    
    /**
    * Inputs: N/A
    * Outputs: void
    * Description: a method which shifts the tiles over to the left and
    * combines the values of the tiles that have "collided".
    */
    public void moveLeft() {
        // With a nested for loop, shift eveything over the left without colliding
        for (int i = 0; i < board.length; i++) {
            int index = 0;
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getValue() != 0) {
                    // set the value of the tile on the left to the original one
                    board[i][index].setValue(board[i][j].getValue());
                    // set the original tile to 0 once the tile is moved
                    if (index != j) {
                        board[i][j].setValue(0);
                    }
                    index++;
                }
            }
        }
        
        // use a nested for loop to merge adjacnent and equal values greater than 0
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (j + 1 <= 3) {
                    if (board[i][j].getValue() == board[i][j + 1].getValue()) {
                        board[i][j].setValue(2 * board[i][j + 1].getValue());
                        board[i][j + 1].setValue(0);
                    }
                }
            }
        }
        
        // after merging adjacent and equal tiles, repeat the first for loop
        // in this method to move all the tiles to the left side
        for (int i = 0; i < board.length; i++) {
            int index = 0;
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getValue() != 0) {   
                    board[i][index].setValue(board[i][j].getValue());
                    if (index != j) {
                       board[i][j].setValue(0);
                    }
                    index++;
                }
            }
        }
    }

    /**
    * Inputs: N/A
    * Outputs: void
    * Description: a method that flips a 2D array along a vertical axis in the 
    * 'middle' of the 2D array in order to mirror it horizontally.
    */
    public void mirrorHorizontally() {
        for (int i = 0; i < board.length; i++) {  
            // Because we are swapping values instead of making a new array, we can
            // just iterate through half the board.
            for (int j = 0; j < board[i].length / 2; j++) {
                // swap values on the opposite side of each other using a temp int 
                int temp = board[i][j].getValue();
                board[i][j].setValue(board[i][board[i].length - 1 - j].getValue());
                board[i][board[i].length - 1 - j].setValue(temp);
            }
        }
    }
    
    /**
    * Inputs: N/A
    * Outputs: void
    * Description: a method that moves the tiles of the board to the 
    * right while checking and appropriately updating the values for the tiles
    * when collisions occur.
    */
    public void moveRight() {
        /*
        * Instead of implementing a seperate move right function, we can 
        * mirrored the original array move left and mirror the mirrored to 
        * get back to the original, whichh is effectively moving right
        */
        mirrorHorizontally();
        moveLeft();    
        mirrorHorizontally();
    }

    
    /**
    * Inputs: N/A
    * Outputs: void
    * Description: create a method that moves the tiles in the board up
    * combines the values of the tiles that have "collided".
    */
    public void moveUp() {
        // With a nested for loop, shift eveything over the left without colliding
        for (int j = 0; j < board.length; j++) {
            int index = 0;
            for (int i = 0; i < board[j].length; i++) {
                if (board[i][j].getValue() != 0) {
                    // set the value of the tile of the up tile to the original one
                    board[index][j].setValue(board[i][j].getValue());
                    // set the original tile to 0 once the tile is moved
                    if (index != i) {
                        board[i][j].setValue(0);
                    }
                    index++;
                }
            }
        }
        
        // use a nested for loop to merge adjacent and equal values greater than 0
        for (int j = 0; j < board.length; j++) {
            for (int i = 0; i < board[j].length; i++) {
                if (i + 1 <= 3) {
                    if (board[i][j].getValue() == board[i + 1][j].getValue()) {
                        board[i][j].setValue(2 * (board[i + 1][j].getValue()));
                        board[i + 1][j].setValue(0);
                    }
                }
            }
        }
        
      
        // after merging two tiles, repeat the first for loop in this method to
        // move all the tiles to the left side
        for (int j = 0; j < board.length; j++) {
            int index = 0;
            for (int i = 0; i < board[j].length; i++) {
                if (board[i][j].getValue() != 0) {
                    board[index][j].setValue(board[i][j].getValue());
                    if (index != i) {
                        board[i][j].setValue(0);
                    }
                    index++;
                }
            }
        }
    }

    /**
    * Inputs: N/A
    * Outputs: void
    * Description:  a method that flips a 2D array along a horizontal axis in the 
    * 'middle' of the 2D array to mirror it vertically
    */
    public void verticallyMirror() {
         // Because we are swapping values instead of making a new array, we can 
         // just iterate through half the board.
        for (int i = 0; i < board.length / 2; i++) {  
            for (int j = 0; j < board[i].length; j++) {
                int temp = board[i][j].getValue();
                board[i][j].setValue(board[board[i].length - 1 - i][j].getValue());
                board[board[i].length - 1 - i][j].setValue(temp);
            }
        }
    }

    /**
    * Inputs: N/A
    * Outputs: void
    * Description: a method that moves the tiles of the board down
    * while checking and appropriately updating the values for the tiles
    * when collisions occur.
    */
    public void moveDown() {
        // same logic as move right & mirror just vertically instead of horizontally.
        verticallyMirror();
        moveUp();
        verticallyMirror();
    }

    /**
    * Inputs: N/A
    * Outputs: a boolean of whether the board has moved
    * Description: a method that checks if the player has pressed 'w', 'a',
    * 's', and 'd' and moves accordingly.
    */
    public boolean moveWithWASD() {
        if (PennDraw.hasNextKeyTyped()) {
            // Need to intialize a variable that stores the most recent key pressed
            char currentKeyPressed = PennDraw.nextKeyTyped();
            
            /* 
            * Check if the WASD keys have been pressed and if the board is able 
            * to move in the direction the user pressed and if the board can, 
            * then move accordingly. The second 'if' prevents uneccesary spawns.
            * Also, increment number of moves in the second if statement.
            */
            if (currentKeyPressed == 'W' || currentKeyPressed == 'w') {
                if (canMoveUp()) {
                    moveUp();
                    numOfMoves++;
                    return true;
                }   
            }
            if (currentKeyPressed == 'A' || currentKeyPressed == 'a') {
                if (canMoveLeft()) {
                    moveLeft();
                    numOfMoves++;
                    return true;
                }
            }
            if (currentKeyPressed == 'S' || currentKeyPressed == 's') {
                if (canMoveDown()) {
                    moveDown();
                    numOfMoves++;
                    return true;
                }
            }
            if (currentKeyPressed == 'D' || currentKeyPressed == 'd') {
                if (canMoveRight()) {
                    moveRight();
                    numOfMoves++;
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
    * Inputs: N/A
    * Outputs: void
    * Description: a method that spawns a tile of value 2 or 4 after a player
    * has either moved left, right, up, or down
    */
    public void spawnTileRandomly() {
        // the while loop ensures that a tile is spawned in an empty location
        boolean empty = true;
        while (empty) {
            // randomly generate a number between 0 and 4
            int x = (int) (Math.random() * 4);
            int y = (int) (Math.random() * 4);
            
            /*
            * We also want '2' to spawn four times as likely as '4' becuase that 
            * is what it seems like when you actually play the game.
            * We can do this by generating a random integer that is between 0 and 4.
            */
            int randomTileValue = (int) (Math.random() * 5);
            
            if (board[x][y].getValue() == 0) {
                if (randomTileValue <= 3) {
                    board[x][y].setValue(2);
                } else if (randomTileValue == 4) {
                    board[x][y].setValue(4);
                }
                empty = false;
            } 
        }
    }
    /**
    * Inputs: N/A
    * Outputs: an int of the number of moves
    * Description: a getter for the number of total moves in a given round
    */
    public int getNumOfMoves() {
        return numOfMoves;
    }

    /**
    * Inputs: N/A
    * Outputs: returns an Tile[][] of the board
    * Description: a getter for the current board after every move
    */
    public Tile[][] getBoard() {
        return board;
    }
}
