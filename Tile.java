/**
* Name: John Otto
* Pennkey: jwotto
* Execution: N/A
*
* Description: a class that creates instances of tiles for the 2048 board. Each
* instance of the tile has a xPos, yPos, and value. In addition, the class
* maintains a static, final variable of each tiles half length.
**/
public class Tile {
    // the value of each tile in the game
    private int value;
    // declare a variables for the x and y position of each tile
    private double xPos, yPos;
    
    // construct a tile with just a value argument
    public Tile(int value) {
        this.value = value;
    }
    
    // create a tile with the full amount of data
    public Tile(double xPos, double yPos, int value) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.value = value;
    }
    
    /**
    * Inputs: N/A
    * Outputs: returns a double of the xPos
    * Description: a getter function that allows aother classes to access the
    * x position of a tile
    */
    public double getXPos() {
        return xPos;
    }
    
    /**
    * Inputs: N/A
    * Outputs: returns a double of the yPos
    * Description: a getter function that allows aother classes to access the
    * y position of a tile
    */
    public double getYPos() {
        return yPos;
    }
    
    /**
    * Inputs: N/A
    * Outputs: an int of the value of the target
    * Description: a getter function that allows other classes to access the
    * value of a tile
    */
    public int getValue() {
        return value;
    }
    
    /**
    * Inputs: N/A
    * Outputs: void
    * Description: a setter function which allows other clases to modify the value
    * of a tile
    */
    public void setValue(int afterCollision) {
        this.value = afterCollision;
    }
}
