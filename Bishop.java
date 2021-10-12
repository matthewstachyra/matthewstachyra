import java.util.*;

public class Bishop extends Piece {

  private String type = "b";
  private Color _c;

  public Bishop(Color c) { _c = c; }

  public Color color() {
    return this._c;
  }

  public String toString() {
    String color;
    if (_c == Color.BLACK) { color = "b"; }
    else { color = "w"; }
    return (color + type);
  }

  // reference: https://stackoverflow.com/questions/54641485/chess-piece-legal-moves
  public List<String> moves(Board b, String loc) {
    List<String> legalMoves = new ArrayList<>();
    int i1 = b.returnIndices(loc).get(0);
    int i2 = b.returnIndices(loc).get(1);

    /*****************************************/
    /** 4 loops, 1 for each direction ********/
    /*** left up diagnol    (i - 1, j - 1) ***/
    /*** right up diagnol   (i - 1, j + 1) ***/
    /*** left down diagnol  (i + 1, j - 1) ***/
    /*** right down diagnol (i + 1, j + 1) ***/
    /*****************************************/
    
    String old_pos = b.returnCol(i2) +  b.returnRow(i1); 
    int[] _math = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
    // left up diagnol 
    boolean collision = false;
    for (int i = 0; i < _math.length; i++) {
      int new_i1 = i1 - _math[i];
      int new_i2 = i2 - _math[i];
      if ((new_i1 < 0) || (new_i1 > 7) || (new_i2 < 0) || (new_i2 > 7)) {
        continue;
      }

      String pos = b.returnCol(new_i2) +  b.returnRow(new_i1); 
      if ((b.getPiece(pos) == null) && (collision == false)) {
        legalMoves.add(pos);
      }
      if ((b.getPiece(pos) != null) && (collision == false) && (b.getPiece(pos).color() != b.getPiece(old_pos).color())) {
        legalMoves.add(pos);
        collision = true;
      }
      // set flag to true if same color piece blocking
      if ((b.getPiece(pos) != null) && (collision == false) && (b.getPiece(pos).color() == b.getPiece(old_pos).color())) {
        collision = true;
      }
      if ((b.getPiece(pos) != null) && (collision == false) && (b.getPiece(pos).color() == b.getPiece(old_pos).color())) {
        continue;
      }
    }
    
    // right up diagnol
    collision = false;
    for (int i = 0; i < _math.length; i++) {
      int new_i1 = i1 - _math[i];
      int new_i2 = i2 + _math[i];
      if ((new_i1 < 0) || (new_i1 > 7) || (new_i2 < 0) || (new_i2 > 7)) {
        continue;
      }

      String pos = b.returnCol(new_i2) +  b.returnRow(new_i1);  
      if ((b.getPiece(pos) == null) && (collision == false)) {
        legalMoves.add(pos);
      }
      if ((b.getPiece(pos) != null) && (collision == false) && (b.getPiece(pos).color() != b.getPiece(old_pos).color())) {
        legalMoves.add(pos);
        collision = true;
      }
      // set flag to true if same color piece blocking
      if ((b.getPiece(pos) != null) && (collision == false) && (b.getPiece(pos).color() == b.getPiece(old_pos).color())) {
        collision = true;
      }
      if ((b.getPiece(pos) != null) && (collision == false) && (b.getPiece(pos).color() == b.getPiece(old_pos).color())) {
        continue;
      }
    }

    // left down diagnol
    collision = false;
    for (int i = 0; i < _math.length; i++) {
      int new_i1 = i1 + _math[i];
      int new_i2 = i2 - _math[i];
      if ((new_i1 < 0) || (new_i1 > 7) || (new_i2 < 0) || (new_i2 > 7)) {
        continue;
      }
      
      String pos = b.returnCol(new_i2) +  b.returnRow(new_i1); 
      if ((b.getPiece(pos) == null) && (collision == false)) {
        legalMoves.add(pos);
      }
      if ((b.getPiece(pos) != null) && (collision == false) && (b.getPiece(pos).color() != b.getPiece(old_pos).color())) {
        legalMoves.add(pos);
        collision = true;
      }
      // set flag to true if same color piece blocking
      if ((b.getPiece(pos) != null) && (collision == false) && (b.getPiece(pos).color() == b.getPiece(old_pos).color())) {
        collision = true;
      }
      if ((b.getPiece(pos) != null) && (collision == false) && (b.getPiece(pos).color() == b.getPiece(old_pos).color())) {
        continue;
      }
    }

    // right down diagnol
    collision = false;
    for (int i = 0; i < _math.length; i++) {
      int new_i1 = i1 + _math[i];
      int new_i2 = i2 + _math[i];
      if ((new_i1 < 0) || (new_i1 > 7) || (new_i2 < 0) || (new_i2 > 7)) {
        continue;
      }
      String pos = b.returnCol(new_i2) +  b.returnRow(new_i1); 
      if ((b.getPiece(pos) == null) && (collision == false)) {
        legalMoves.add(pos);
      }
      if ((b.getPiece(pos) != null) && (collision == false) && (b.getPiece(pos).color() != b.getPiece(old_pos).color())) {
        legalMoves.add(pos);
        collision = true;
      }
      // set flag to true if same color piece blocking
      if ((b.getPiece(pos) != null) && (collision == false) && (b.getPiece(pos).color() == b.getPiece(old_pos).color())) {
        collision = true;
      }
      if ((b.getPiece(pos) != null) && (collision == false) && (b.getPiece(pos).color() == b.getPiece(old_pos).color())) {
        continue;
      }
    }
    return legalMoves;
  }
}