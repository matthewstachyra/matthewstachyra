import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chess {

  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("Usage: java Chess layout moves");
	  }
  	Piece.registerPiece(new KingFactory());
	  Piece.registerPiece(new QueenFactory());
	  Piece.registerPiece(new KnightFactory());
	  Piece.registerPiece(new BishopFactory());
	  Piece.registerPiece(new RookFactory());
	  Piece.registerPiece(new PawnFactory());
	  Board.theBoard().registerListener(new Logger());
    Board b = Board.theBoard();

  File layout = new File(args[0]);
  File moves = new File(args[1]);
  Map<String, String> layoutMap = new HashMap<>();
  Map<String, String> movesMap = new HashMap<>();

  // step 1: read layout
  try (BufferedReader br = new BufferedReader(new FileReader(layout)))
  {
    String line;
    while ((line = br.readLine()) != null) {
      // continue if its a comment
      if(line.charAt(0) == '#') {
        continue; 
      }

      // throw exception if line doesnt have 'xn=cp' format
      Pattern pattern = Pattern.compile("([a-h][1-8]+=+[w+b][kqbnrp])");
      Matcher m = pattern.matcher(line);
      if (m.find()) {
        // store moves (key: loc, value: piece)
        String loc = line.substring(0,2);
        String piece = line.substring(3,5);

        // only add if not added already
        if(b.getPiece(loc) == null) {
          layoutMap.put(loc, piece);
        }
      }
      else {
        throw new ArrayIndexOutOfBoundsException();
      }
    }
  } catch (IOException e) {
    e.printStackTrace();
  }

  // step 2: build board from layout
  for(Map.Entry<String, String> entry : layoutMap.entrySet()){
    Piece piece = Piece.createPiece(entry.getValue());
    b.addPiece(piece, entry.getKey());
  }

  // step 3: read moves
  try (BufferedReader br = new BufferedReader(new FileReader(moves)))
  {
    String line;
    while ((line = br.readLine()) != null) {
      if(line.charAt(0) == '#') {
        continue; 
      }

      // throw exception if line doesnt have 'xn=cp' format
      Pattern pattern = Pattern.compile("([a-h][1-8]+-+[a-h][1-8])");
      Matcher m = pattern.matcher(line);
      if (m.find()) {
        // key: loc, value: piece
        String from = line.substring(0,2);
        String to = line.substring(3,5);

        // store move if valid for the piece
        Piece toMove = b.getPiece(from);
        List<String> legalMoves = b.returnPos(toMove.moves(b, from));
        if (legalMoves.contains(to)) {
          movesMap.put(from,to);
        }
      }
      else {
        throw new ArrayIndexOutOfBoundsException();
      }
    }
  } catch (IOException e) {
    e.printStackTrace();
  }
  
  // step 4: execute moves
  for(Map.Entry<String, String> entry : movesMap.entrySet()) {
    b.movePiece(entry.getKey(), entry.getValue());
  }

	System.out.println("Final board:");
	Board.theBoard().iterate(new BoardPrinter());
  }
}