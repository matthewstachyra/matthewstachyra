import java.util.*;

public class Board {

  private static Board single_instance = null;

  private Piece[][] pieces = new Piece[8][8];

  private List<BoardListener> boardListeners = new ArrayList<>();

  private Board() { }
  
  // returns singleton
  public static Board theBoard() {
    if (single_instance == null) { single_instance = new Board(); }
    return single_instance;
  }

  // returns piece at given loc or null if no such piece exists
  public Piece getPiece(String loc) {
    try {
      Piece toReturn = pieces[returnIndices(loc).get(0)][returnIndices(loc).get(1)];
      if (toReturn != null) { return toReturn; }
      return null;
    } catch(ArrayIndexOutOfBoundsException e) {
      throw new ArrayIndexOutOfBoundsException();
    }
  }

  // adds piece p to the board at the given location
  public void addPiece(Piece p, String loc) {
    if (pieces[returnIndices(loc).get(0)][returnIndices(loc).get(1)] != null) {
      throw new ArrayIndexOutOfBoundsException(); 
    }
    pieces[returnIndices(loc).get(0)][returnIndices(loc).get(1)] = p;
  }

  // moves the piece at location from to location to
  public void movePiece(String from, String to) {
    int i1 = returnFirstIndex(from.substring(1, 2));
    int i2 = returnSecondIndex(from.substring(0, 1));
    int i3 = returnFirstIndex(to.substring(1, 2));
    int i4 = returnSecondIndex(to.substring(0, 1));

    // throw exception if there's no piece at 'from' or if move 'to' is invalid
    List<String> moves = returnPos(pieces[i1][i2].moves(this, from)); 
    if (pieces[i1][i2] == null) { 
      throw new ArrayIndexOutOfBoundsException(); 
    }

    if (!(moves.contains(to))) { 
      throw new ArrayIndexOutOfBoundsException(); 
    }

    // on capture, call onMove && onCapture
    if(pieces[i3][i4] != null) {
      for(BoardListener bl: boardListeners) {
        bl.onMove(from, to, pieces[i1][i2]);
        bl.onCapture(pieces[i1][i2], pieces[i3][i4]);
      }
      Piece copy = pieces[i1][i2];
      pieces[i1][i2] = null;
      pieces[i3][i4] = copy;
    }

    // on move, call onMove
    for(BoardListener bl: boardListeners) {
      bl.onMove(from, to, pieces[i1][i2]);
    }
    Piece copy = pieces[i1][i2];
    pieces[i1][i2] = null;
    pieces[i3][i4] = copy;
  }

  public void clear() { 
    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 8; j++)
        pieces[i][j] = null;
    } 
  }

  public void registerListener(BoardListener bl) {
	  if(!boardListeners.contains(bl)) {
      boardListeners.add(bl);
    }
  }

  public void removeListener(BoardListener bl) {
	  if(boardListeners.contains(bl)) {
      boardListeners.remove(bl);
    }
  }

  public void removeAllListeners() {
	  for(BoardListener bl : boardListeners) {
      boardListeners.remove(bl);
    }
  }

  public void iterate(BoardExternalIterator bi) {
    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 8; j++) {
        Piece curr = pieces[i][j];
        String row = this.returnRow(i);
        String col = this.returnCol(j);
        String nextLoc = (col + row);
        bi.visit(nextLoc, curr);
      }
    }
  }

  /****************************************/
  /****** utility fields and methods ******/
  /****************************************/

    public Map<String, Integer> cols = new HashMap<String, Integer> () {{
    put("a", 0);
    put("b", 1);
    put("c", 2);
    put("d", 3);
    put("e", 4);
    put("f", 5);
    put("g", 6);
    put("h", 7);
  }};

  public Map<String, Integer> rows = new HashMap<String, Integer> () {{
    put("8", 0);
    put("7", 1);
    put("6", 2);
    put("5", 3);
    put("4", 4);
    put("3", 5);
    put("2", 6);
    put("1", 7);
  }};

  // return first index, given row string
  public int returnFirstIndex(String row) { return rows.get(row); }

  // return second index, given col string
  public int returnSecondIndex(String col) { return cols.get(col); }

  // return row string, given first index
  public String returnRow(int firstIndex) {
    Map<Integer, String> _rows = new HashMap<>();
    for(Map.Entry<String, Integer> entry : rows.entrySet()){
      _rows.put(entry.getValue(), entry.getKey());
    }
    return _rows.get(firstIndex);
  }

  // return col string, given second index
  public String returnCol(int secondIndex) {
    Map<Integer, String> _cols = new HashMap<>();
    for(Map.Entry<String, Integer> entry : cols.entrySet()){
      _cols.put(entry.getValue(), entry.getKey());
    }
    return _cols.get(secondIndex);
  }

  // return board positions as strings, given indices
  public List<String> returnPos(List<String> indices) {
    List<String> positions = new ArrayList<>();
    for (String idx : indices) {
      String col = returnCol(Integer.valueOf(idx.substring(1, 2)));
      String row = returnRow(Integer.valueOf(idx.substring(0, 1)));
      positions.add(col+row); 
    }
    return positions;
  }

  // return indices, given position as string
  public List<Integer> returnIndices(String loc) {
    int i1 = returnFirstIndex(Character.toString(loc.charAt(1)));
    int i2 = returnSecondIndex(Character.toString(loc.charAt(0)));
    List<Integer> indices = new ArrayList<Integer>();
    indices.add(i1);
    indices.add(i2);
    return indices;
  }

  public void printBoard() {
    for (int i = 0; i < pieces.length; i++) { 
      for (int j = 0; j < pieces.length; j++) {
        String loc = returnCol(j) + returnRow(i);
        System.out.println(loc + ": " + getPiece(loc)); 
      }
    }
  }
}