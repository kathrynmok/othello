
package main.java.com.mrjaffesclass.othello;

import java.util.ArrayList;
import java.util.Random;

/**
 * Test player
 */
public class Mokhov extends Player {

  /**
   * Constructor
   * @param name Player's name
   * @param color Player color: one of Constants.BLACK or Constants.WHITE
   */
  public Mokhov(int color) {
    super(color);
  }

      /**
       *
       * @param board
       * @return The player's next move
       */
      @Override
      public Position getNextMove(Board board) {
        ArrayList<Position> allpossiblemoves = this.getLegalMoves(board);
        if (allpossiblemoves.size() > 0) {
          int index = (int) (Math.random() * allpossiblemoves.size());
          return allpossiblemoves.get(index);
        } else {
          return null;
        }
    }
      private boolean isLegalMove(Board board, Position positionToCheck) {
    for (String direction : Directions.getDirections()) {
      Position directionVector = Directions.getVector(direction);
      if (step(board, positionToCheck, directionVector, 0)) {
        return true;
      }
    }
    return false;
  }
  private boolean step(Board board, Position position, Position direction, int count) {
    Position newPosition = position.translate(direction);
    int color = this.getColor();
    if (newPosition.isOffBoard()) {
      return false;
    } else if (board.getSquare(newPosition).getStatus() == -color) {
      return this.step(board, newPosition, direction, count+1);
    } else if (board.getSquare(newPosition).getStatus() == color) {
      return count > 0;
    } else {
      return false;
    }
  }
  public ArrayList<Position> getLegalMoves(Board board) {
    int color = this.getColor();
    ArrayList list = new ArrayList<>();
    for (int row = 0; row < Constants.SIZE; row++) {
      for (int col = 0; col < Constants.SIZE; col++) {
        if (board.getSquare(this, row, col).getStatus() == Constants.EMPTY) {
          Position testPosition = new Position(row, col);
          if (this.isLegalMove(board, testPosition)) {
            list.add(testPosition);
          }
        }        
      }
    }
    return list;
  }
   private Board copyBoard(Board b){
    Board board = new Board();
    for(int i = 0; i < Constants.SIZE; i++){
      for(int c = 0; c < Constants.SIZE; c++){
        Position pos = new Position(c, i);
        Player pl = new Player(b.getSquare(pos).getStatus());
        board.setSquare(pl, pos);
      }
    }
    return board;
  }
  
    

}




