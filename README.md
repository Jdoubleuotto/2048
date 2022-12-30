# 2048
/**********************************************************************
 * Readme
 * CIS 1100 Final Project: 2048
 **********************************************************************/

 Name: John Otto


/**********************************************************************
 * Breifly explain Tile.java, Board.java, Game.java, Play2048.java, 
 * and TileData.txt
 **********************************************************************/
 * Tile.java is a class representing the object of an individual tile in 
 * the 2048 game and has a xPos, yPos, and value
 *
 * Board.java is a class that represents the board in a game is comprised of a 4x4
 * 2D array of tiles and a tracker that remembers the number of moves in round.
 * It has methods that control movement and spawn tiles
 * 
 * Game.java is a class that combines both the board and tile classes in order
 * to update the board, draw new tiles with new colors/values, and determine
 * if the player has won or lost
 * 
 * Play2048.java is a class that is the main file that takes in a text file of 
 * the initial data points for the tiles and runs the game until the player 
 * has won or lost.
 *
 * TileData.txt is a file containing the initial values of the tiles for 2048
 *
 * More in depth explanations are in every class

 /**********************************************************************
 * Instructions for how to run 2048
 **********************************************************************/
 * First, Add the all files I submitted to an IDE. 
 * Then, compile the code. 
 * Finally, type 'java Play2048 TileData.txt' and the game should run.
 * Move with the WASD keys.


