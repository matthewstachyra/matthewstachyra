import java.util.*;

public class Queen extends Piece {
  
  private String type = "q";
  private Color _c;

  public Queen(Color c) { _c = c; }

  public Color color() { return this._c; }

  public String toString() {
    String color;
    if (_c == Color.BLACK) { color = "b"; }
    else { color = "w"; }
    return (color + type);
  }

  // combination of bishop and rook movement
  public List<String> moves(Board b, String loc) {
    List<String> legalMoves = new ArrayList<>();
    int i1 = b.returnIndices(loc).get(0);
    int i2 = b.returnIndices(loc).get(1);
    String old_pos = b.returnCol(i2) +  b.returnRow(i1);
    int[] _math = new int[]{1, 2, 3, 4, 5, 6, 7, 8};

    /////////////////////
    // left up diagnol //
    /////////////////////

    // if null -> add
    // if not null and collision false and not same color -> add
    // else -> don't add / continue
    boolean collision = false;
    for (int i = 0; i < _math.length; i++) {
      int new_i1 = i1 - _math[i];
      int new_i2 = i2 - _math[i];
      if ((new_i1 < 0) || (new_i1 > 7) || (new_i2 < 0) || (new_i2 > 7)) {
        continue;
      }
      String pos = b.returnCol(new_i2) + b.returnRow(new_i1);
      if ((b.getPiece(pos) != null) && (collision == false) && (b.getPiece(pos).color() != b.getPiece(old_pos).color())) {
        legalMoves.add(pos);
        collision = true;
      }
      if ((b.getPiece(pos) == null) && (collision == false)) {
        legalMoves.add(pos);
      }
      // set flag to true if same color piece blocking
      if ((b.getPiece(pos) != null) && (collision == false) && (b.getPiece(pos).color() == b.getPiece(old_pos).color())) {
        collision = true;
      }
    }
    
    //////////////////////
    // right up diagnol //
    //////////////////////
    
    collision = false;
    for (int i = 0; i < _math.length; i++) {
      int new_i1 = i1 - _math[i];
      int new_i2 = i2 + _math[i];
      if ((new_i1 < 0) || (new_i1 > 7) || (new_i2 < 0) || (new_i2 > 7)) {
        continue;
      }

      String pos = b.returnCol(new_i2) + b.returnRow(new_i1);
      if ((b.getPiece(pos) != null) && (collision == false) && (b.getPiece(pos).color() != b.getPiece(old_pos).color())) {
        legalMoves.add(pos);
        collision = true;
      } 
      if ((b.getPiece(pos) == null) && (collision == false)) {
        legalMoves.add(pos);
      }
      // set flag to true if same color piece blocking
      if ((b.getPiece(pos) != null) && (collision == false) && (b.getPiece(pos).color() == b.getPiece(old_pos).color())) {
        collision = true;
      }
    }

    /////////////////////
    // left up diagnol //
    /////////////////////

    collision = false;
    for (int i = 0; i < _math.length; i++) {
      int new_i1 = i1 + _math[i];
      int new_i2 = i2 - _math[i];
      if ((new_i1 < 0) || (new_i1 > 7) || (new_i2 < 0) || (new_i2 > 7)) {
        continue;
      }
      
      String pos = b.returnCol(new_i2) + b.returnRow(new_i1); 
      if ((b.getPiece(pos) != null) && (collision == false) && (b.getPiece(pos).color() != b.getPiece(old_pos).color())) {
        legalMoves.add(pos);
        collision = true;
      }
      if ((b.getPiece(pos) == null) && (collision == false)) {
        legalMoves.add(pos);
      }
      // set flag to true if same color piece blocking
      if ((b.getPiece(pos) != null) && (collision == false) && (b.getPiece(pos).color() == b.getPiece(old_pos).color())) {
        collision = true;
      }
    }

    ////////////////////////
    // right down diagnol //
    ////////////////////////

    collision = false;
    for (int i = 0; i < _math.length; i++) {
      int new_i1 = i1 + _math[i];
      int new_i2 = i2 + _math[i];
      if ((new_i1 < 0) || (new_i1 > 7) || (new_i2 < 0) || (new_i2 > 7)) {
        continue;
      }
      String pos = b.returnCol(new_i2) + b.returnRow(new_i1); 
      if ((b.getPiece(pos) != null) && (collision == false) && (b.getPiece(pos).color() != b.getPiece(old_pos).color())) {
        legalMoves.add(pos);
        collision = true;
      }
      if ((b.getPiece(pos) == null) && (collision == false)) {
        legalMoves.add(pos);
      }
      // set flag to true if same color piece blocking
      if ((b.getPiece(pos) != null) && (collision == false) && (b.getPiece(pos).color() == b.getPiece(old_pos).color())) {
        collision = true;
      }
    }
    
    //////////////////
    // left on rank //
    //////////////////

    collision = false;
    for (int i = 0; i < _math.length; i++) {
      int new_i2 = i2 - _math[i];
      if ((new_i2 < 0)) {
        continue;
      }
      String pos = b.returnCol(new_i2) + b.returnRow(i1); 
      if ((b.getPiece(pos) != null) && (collision == false) && (b.getPiece(pos).color() != b.getPiece(old_pos).color())) {
        legalMoves.add(pos);
        collision = true;
      }
      if ((b.getPiece(pos) == null) && (collision == false)) {
        legalMoves.add(pos);
      }
      // set flag to true if same color piece blocking
      if ((b.getPiece(pos) != null) && (collision == false) && (b.getPiece(pos).color() == b.getPiece(old_pos).color())) {
        collision = true;
      }
    }

    ///////////////////
    // right on rank //
    ///////////////////

    collision = false;
    for (int i = 0; i < _math.length; i++) {
      int new_i2 = i2 + _math[i];
      if ((new_i2 > 7)) {
        continue;
      }
      String pos = b.returnCol(new_i2) + b.returnRow(i1); 
      if ((b.getPiece(pos) != null) && (collision == false) && (b.getPiece(pos).color() != b.getPiece(old_pos).color())) {
        legalMoves.add(pos);
        collision = true;
      }
      if ((b.getPiece(pos) == null) && (collision == false)) {
        legalMoves.add(pos);
      }
      // set flag to true if same color piece blocking
      if ((b.getPiece(pos) != null) && (collision == false) && (b.getPiece(pos).color() == b.getPiece(old_pos).color())) {
        collision = true;
      }
    }

    ////////////////
    // up on file //
    ////////////////

    collision = false;
    for (int i = 0; i < _math.length; i++) {
      int new_i1 = i1 - _math[i];
      if ((new_i1 < 0)) {
        continue;
      }
      String pos = b.returnCol(i2) + b.returnRow(new_i1); 
      if ((b.getPiece(pos) != null) && (collision == false) && (b.getPiece(pos).color() != b.getPiece(old_pos).color())) {
        legalMoves.add(pos);
        collision = true;
      }
      if ((b.getPiece(pos) == null) && (collision == false)) {
        legalMoves.add(pos);
      }
      // set flag to true if same color piece blocking
      if ((b.getPiece(pos) != null) && (collision == false) && (b.getPiece(pos).color() == b.getPiece(old_pos).color())) {
        collision = true;
      }
    }

    //////////////////
    // down on file //
    //////////////////
    
    collision = false;
    for (int i = 0; i < _math.length; i++) {
      int new_i1 = i1 + _math[i];
      if ((new_i1 > 7)) {
        continue;
      }
      String pos = b.returnCol(i2) + b.returnRow(new_i1); 
      if ((b.getPiece(pos) != null) && (collision == false) && (b.getPiece(pos).color() != b.getPiece(old_pos).color())) {
        legalMoves.add(pos);
        collision = true;
      }
      if ((b.getPiece(pos) == null) && (collision == false)) {
        legalMoves.add(pos);
      }
      // set flag to true if same color piece blocking
      if ((b.getPiece(pos) != null) && (collision == false) && (b.getPiece(pos).color() == b.getPiece(old_pos).color())) {
        collision = true;
      }
    }
    return legalMoves;
  }
}