import java.util.*;

abstract public class Piece {

  private Color color;

  public static HashMap<Character, PieceFactory> pieceFactoryMap = new HashMap<>();

  public static void registerPiece(PieceFactory pf) {
    pieceFactoryMap.put(pf.symbol(), pf);
  }

  public static Piece createPiece(String name) {
    char _color = name.charAt(0); 
    char _type = name.charAt(1);

    if(_color == 'b') {
      return pieceFactoryMap.get(_type).create(Color.BLACK);
    }
    else if(_color == 'w') {
      return pieceFactoryMap.get(_type).create(Color.WHITE);
    }
    else {
      throw new InputMismatchException();
    }
  }

  public Color color() {
    return color;
  }
  
  abstract public String toString();
  abstract public List<String> moves(Board b, String loc);
}