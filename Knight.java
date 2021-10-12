import java.util.*;

public class Knight extends Piece {

  private String type = "n";
  private Color _c;

  public Knight(Color c) { _c = c; }

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
    int[] row_math = new int[]{2, 1, -2, -1, -1, 2, -2, 1};
    int[] col_math = new int[]{1, 2, -1, -2, 2, -1, 1, -2};
    String old_pos = b.returnCol(i2) +  b.returnRow(i1);
    for (int i = 0; i < row_math.length; i++) {
      int new_i1 = i1 + row_math[i];
      int new_i2 = i2 + col_math[i];
      if ((new_i1 < 0) || (new_i1 > 7) || (new_i2 < 0) || (new_i2 > 7)) {
        continue;
      }

      // if null -> add
      // if not null, and same color -> don't add
      // if not null, and same color -> add
      String pos = b.returnCol(new_i2) +  b.returnRow(new_i1); 
      if (b.getPiece(pos) == null) {
        legalMoves.add(pos);
      }
      if ((b.getPiece(pos) != null) && (b.getPiece(pos).color() == b.getPiece(old_pos).color())) {
        continue;
      }
      if ((b.getPiece(pos) != null) && (b.getPiece(pos).color() != b.getPiece(old_pos).color())) {
        legalMoves.add(pos);
      }
    }
    return legalMoves;
  }
}