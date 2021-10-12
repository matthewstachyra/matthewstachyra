import java.util.*;

public class Pawn extends Piece {
  
  private String type = "p";
  private Color _c;

  public Pawn(Color c) { _c = c; }

  public Color color() {
    return this._c;
  }

  public String toString() {
    String color;
    if (_c == Color.BLACK) { color = "b"; }
    else { color = "w"; }
    return (color + type);
  }

  public List<String> moves(Board b, String loc) {
    List<String> legalMoves = new ArrayList<>();
    int i1 = b.returnIndices(loc).get(0);
    int i2 = b.returnIndices(loc).get(1);  
    String old_pos = b.returnCol(i2) +  b.returnRow(i1);

    //white -- decrementing first index / up in rows
    if (_c == Color.WHITE) {
      int new_i1 = i1-1;
      String pos = b.returnCol(i2) +  b.returnRow(new_i1); 
      //////////
      // up 1 //
      //////////

      // if null -> add
      // if not null -> don't add / continue
      if (b.getPiece(pos) == null) {
        legalMoves.add(pos);
      }

      /////////////////////////
      // up 2, if applicable //
      /////////////////////////
      
      // if unmoved and null -> add
      // if unmoved and not null -> don't add / continue
      if((unmoved(i1, _c)) && (b.getPiece(pos) == null)) {
        int new_i1_2 = i1-2;
        String pos2 = b.returnCol(i2) +  b.returnRow(new_i1_2); 
        legalMoves.add(pos2);
      }
      
      /////////////////////////////////
      // left capture, if applicable //
      /////////////////////////////////

      // if not null and opposite color -> add
      // if not null and same color -> don't add / continue    
      String pos3 = b.returnCol(i2-1) +  b.returnRow(i1-1); 
      if (b.getPiece(pos3) != null) {
        if (b.getPiece(pos3).toString().substring(0,1) != "w"){
          legalMoves.add(pos3);
        }
      }
      //////////////////////////////////
      // right capture, if applicable //
      //////////////////////////////////

      // if not null and opposite color -> add
      // if not null and same color -> don't add / continue 
      String pos4 = b.returnCol(i2+1) +  b.returnRow(i1-1); 
      if (b.getPiece(pos4) != null) {
        if ((b.getPiece(pos4).toString().substring(0,1) != "w")) {
          legalMoves.add(pos4);
        }
      }
    }
    // black -- incrementing first index / down in rows
    if (_c == Color.BLACK) {
      int new_i1 = i1+1;
      String pos = b.returnCol(i2) +  b.returnRow(new_i1); 
      //////////
      // up 1 //
      //////////

      // if null -> add
      // if not null -> don't add / continue
      if (b.getPiece(pos) == null) {
        legalMoves.add(pos);
      }

      /////////////////////////
      // up 2, if applicable //
      /////////////////////////
      
      // if unmoved and null -> add
      // if unmoved and not null -> don't add / continue
      if((unmoved(i1, _c)) && (b.getPiece(pos) == null)) {
        int new_i1_2 = i1+2;
        String pos2 = b.returnCol(i2) +  b.returnRow(new_i1_2); 
        legalMoves.add(pos2);
      }
      

      /////////////////////////////////
      // left capture, if applicable //
      /////////////////////////////////

      // if not null and opposite color -> add
      // if not null and same color -> don't add / continue  
      String pos3 = b.returnCol(i2-1) +  b.returnRow(i1+1); 
      if (b.getPiece(pos3) != null) {
        if(b.getPiece(pos3).toString().substring(0,1) != "b") {
          legalMoves.add(pos3);
        }
      }

      //////////////////////////////////
      // right capture, if applicable //
      //////////////////////////////////

      // if not null and opposite color -> add
      // if not null and same color -> don't add / continue  
      String pos4 = b.returnCol(i2+1) +  b.returnRow(i1+1); 
      if (b.getPiece(pos4) != null) {
        if (b.getPiece(pos4).toString().substring(0,1) != "b") {
          legalMoves.add(pos4);
        }
      }
    }
    return legalMoves;
  }

  // utility method to check if w or b pawn are on their first row
  public boolean unmoved(int row, Color color) {
    if(color == Color.BLACK && row == 1) { return true; }
    if (color == Color.WHITE && row == 6) { return true; }
    return false;
  }
}