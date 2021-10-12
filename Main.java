class Main {

  /**************************************/
  /********* Testing Board.java *********/
  /**************************************/

  // testing theBoard() to ensure singleton created
  public static void board_test_01() {
    System.out.println("board_test_01() running.");
    Board b1 = Board.theBoard();
    Board b2 = Board.theBoard();
    if (b1 == b2) { System.out.println("True"); }
  }

  // testing addPiece() and getPiece()
  public static void board_test_02() {
    System.out.println("board_test_02() running.");
    Board b1 = Board.theBoard();
    Piece wn1 = Piece.createPiece("wn");
    b1.addPiece(wn1, "b3");
    if (b1.getPiece("b3").toString().equals("wn")) { 
      System.out.println("True"); 
    }
  }

  // testing getPiece() returns null
  public static void board_test_03() {
    System.out.println("board_test_03() running.");
    Board b1 = Board.theBoard();
    if (b1.getPiece("b15") == null) { 
      System.out.println("True"); 
    }
  }

  // testing addPiece() throws exception when adding an occupied spot
  public static void board_test_04() {
    System.out.println("board_test_04() running.");
    Board b1 = Board.theBoard();
    Piece wn1 = Piece.createPiece("wn");
    Piece br1 = Piece.createPiece("br");
    b1.addPiece(wn1, "a3");
    try { 
      b1.addPiece(br1, "a3");
    } catch ( ArrayIndexOutOfBoundsException exception ) {
       System.out.println("True"); 
    }
  }

  /**************************************/
  /****** Testing specific pieces *******/
  /**************************************/

  public static void bishop_test_01() {
    System.out.println("bishop_test_01() running.");
    Board b1 = Board.theBoard();
    Piece wb1 = Piece.createPiece("wb1");
    b1.addPiece(wb1, "d4");
    System.out.println(b1.returnPos(wb1.moves(b1, "d4"))); 
    // [c5, b6, a7, e5, f6, g7, h8, c3, b2, a1, e3, f2, g1]
  }

  public static void bishop_test_02() {
    System.out.println("bishop_test_02() running.");
    Board b1 = Board.theBoard();
    Piece wb2 = Piece.createPiece("wb2"); 
    b1.addPiece(wb2, "b4");
    Piece wn1 = Piece.createPiece("wn1");
    b1.addPiece(wn1, "f6"); 
    Piece bp1 = Piece.createPiece("bp1");
    b1.addPiece(bp1, "g7"); // [1][6] or 16
    System.out.println(b1.returnPos(wb2.moves(b1, "b4"))); 
    // [a5, c5, d6, e7, f8, a3, c3, d2, e1]
  }
  
  public static void king_test_01() {
    System.out.println("king_test_01() running.");
    Board b1 = Board.theBoard();
    Piece wk1 = Piece.createPiece("wk1");
    b1.addPiece(wk1, "e6"); 
    System.out.println(b1.returnPos(wk1.moves(b1, "e6")));  
    // [e5, f6, f5, d7, f7, d5, e7, d6]
  }

  public static void knight_test_01() {
    System.out.println("knight_test_01() running.");
    Board b1 = Board.theBoard();
    Piece wn1 = Piece.createPiece("wn1");
    b1.addPiece(wn1, "d5"); // [3][3] or 33
    System.out.println(b1.returnPos(wn1.moves(b1, "d5")));  
    // [e3, f4, c7, b6, f6, c3, e7, b4]
  }

  // white pawn test
  public static void pawn_test_01() {
    System.out.println("pawn_test_01() running.");
    Board b1 = Board.theBoard();
    Piece wp1 = Piece.createPiece("wp1");
    b1.addPiece(wp1, "c4"); 
    System.out.println(b1.returnPos(wp1.moves(b1, "c4")));
    // [c5, d5]
  }

  // black pawn test
  public static void pawn_test_02() {
    System.out.println("pawn_test_02() running.");
    Board b1 = Board.theBoard();
    Piece bp1 = Piece.createPiece("bp1");
    b1.addPiece(bp1, "f4"); // [4][5] or 45
    System.out.println(b1.returnPos(bp1.moves(b1, "f4")));  
    // [f3]
  }

  public static void rook_test_01() {
    System.out.println("rook_test_01() running.");
    Board b1 = Board.theBoard();
    Piece bp1 = Piece.createPiece("br1");
    b1.addPiece(bp1, "f2"); 
    System.out.println(b1.returnPos(bp1.moves(b1, "f2"))); 
    // [e2, d2, c2, b2, a2, g2, h2, f3, f4, f5, f6, f7, f8, f1]
  }

  // test movePiece() 1
  public static void board_test_05() {
    // basic functionality
    System.out.println("board_test_05() running.");
    Board b1 = Board.theBoard();
    Piece br1 = Piece.createPiece("br1");
    b1.addPiece(br1, "a1"); 
    b1.movePiece("a1", "a8");
    if (b1.getPiece("a1") == null) {
      System.out.println("True");
    }  
    if (b1.getPiece("a1") != null) {
      System.out.println("Failed");
    }
  }

  // test movePiece() 2
  public static void board_test_06() {
    System.out.println("board_test_06() running.");
    Board b1 = Board.theBoard();
    Piece wp2 = Piece.createPiece("wp2");
    b1.addPiece(wp2, "a2"); 
    try {
    b1.movePiece("a2", "a1"); // invalid 'to' throws exception
    }
    catch (NullPointerException e) {
      System.out.println("True");
    }
  }
  
  public static void queen_test_01() {
    Board b1 = Board.theBoard();
    Piece bq1 = Piece.createPiece("bq1");
    b1.addPiece(bq1, "d4"); 
    System.out.println(bq1.moves(b1, "d4"));
  }

  public static void knight_test_02() {
    Board b1 = Board.theBoard();
    Piece bn1 = Piece.createPiece("bn1");
    b1.addPiece(bn1, "d4"); 
    System.out.println(bn1.moves(b1, "d4"));
  }

  /**************************************/
  /******* Testing non empty board ******/
  /**************************************/

  public static void nonempty_board_test_01() {
    ////////////////////
    // CORRECT OUTPUT //
    ////////////////////
    // Black pawn from d7: [d6, d5, e6] 
    // White pawn from d2: [d3, d4, c3]
    // Black rook from a7: [b7, c7, a8, a6, a5, a4, a3, a2, a1]
    // White rook from h7: [g7, f7, e7, d7, h8, h6, h5, h4, h3, h2, h1]
    // Black bishop from c3: [b4, a5, d4, e5, f6, g7, h8, b2, a1, d2]
    // White bishop from e6: [d7, f7, g8, d5, c4, b3, a2, f5]
    // Black knight from c8: [d6, e7, b6]
    // White knight from f4: [g2, h3, d5, h5, e2, g6, d3]
    // Black queen from f5: [e6, g6, h7, e4, d3, c2, b1, g4, h3, e5, d5, c5, b5, a5, g5, h5, f6, f7, f8, f4]
    // White queen from d1: [c2, b3, a4, e2, f3, g4, h5, c1, b1, a1]
    // Black king from e8: [e7, f8, f7, d8]
    // White king from e1: [f1, f2, e2] 
    
    Board b = Board.theBoard();
    Piece bp1 = Piece.createPiece("bp1");
    Piece wp1 = Piece.createPiece("wp1");
    Piece br1 = Piece.createPiece("br1");
    Piece wr1 = Piece.createPiece("wr1");
    Piece bb1 = Piece.createPiece("bb1");
    Piece wb1 = Piece.createPiece("wb1");
    Piece bn1 = Piece.createPiece("bn1");
    Piece wn1 = Piece.createPiece("wn1");
    Piece bq1 = Piece.createPiece("bq1");
    Piece wq1 = Piece.createPiece("wq1");
    Piece bk1 = Piece.createPiece("bk1");
    Piece wk1 = Piece.createPiece("wk1");

    b.addPiece(bp1, "d7"); // can't take d7; goes no further on 7 rank
    b.addPiece(wp1, "d2"); 
    b.addPiece(br1, "a7"); 
    b.addPiece(wr1, "h7"); // can take d7 pawn; goes no further on 7 rank
    b.addPiece(bb1, "c3"); 
    b.addPiece(wb1, "e6"); // can take black pawn at d7
    b.addPiece(bn1, "c8"); // bishop can't take (it's behind a pawn); can't go to a7 because same color rook
    b.addPiece(wn1, "f4"); // can't move to e6 (because of white bishop)
    b.addPiece(bq1, "f5"); // can't go to c8, d7; can take e6 white bishop; can't go to f3, f2, or f1 (after capture of white knight on f4)
    b.addPiece(wq1, "d1"); // can't move on d column (blocked by pawn)
    b.addPiece(bk1, "e8"); // can't capture same color pawn on d7
    b.addPiece(wk1, "e1"); // can't go to d1 (same color queen) or d2 (same color pawn)

    System.out.println("Black pawn from d7: " + bp1.moves(b,"d7") + "\n");
    System.out.println("White pawn from d2: " + wp1.moves(b,"d2") + "\n");
    System.out.println("Black rook from a7: " + br1.moves(b,"a7") + "\n");
    System.out.println("White rook from h7: " + wr1.moves(b,"h7") + "\n");
    System.out.println("Black bishop from c3: " + bb1.moves(b,"c3") + "\n");
    System.out.println("White bishop from e6: " + wb1.moves(b,"e6") + "\n");
    System.out.println("Black knight from c8: " + bn1.moves(b,"c8") + "\n");
    System.out.println("White knight from f4: " + wn1.moves(b,"f4") + "\n");
    System.out.println("Black queen from f5: " + bq1.moves(b,"f5") + "\n");
    System.out.println("White queen from d1: " + wq1.moves(b,"d1") + "\n");
    System.out.println("Black king from e8: " + bk1.moves(b,"e8") + "\n");
    System.out.println("White king from e1: " + wk1.moves(b,"e1"));
  }

  public static void main(String[] args) {
    Piece.registerPiece(new KingFactory());
    Piece.registerPiece(new QueenFactory());
    Piece.registerPiece(new KnightFactory());
    Piece.registerPiece(new BishopFactory());
    Piece.registerPiece(new RookFactory());
    Piece.registerPiece(new PawnFactory());
    // board_test_01();
    // board_test_02();
    // board_test_03();
    // board_test_04();
    // bishop_test_01();
    // bishop_test_02();
    // king_test_01();
    // knight_test_01();
    // pawn_test_01();
    // pawn_test_02();
    // rook_test_01();
    // board_test_05();
    // board_test_06();
    // queen_test_01();
    // knight_test_02();
    nonempty_board_test_01();
  }
}