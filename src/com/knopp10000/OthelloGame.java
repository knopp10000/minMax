package com.knopp10000;

public class OthelloGame {
        Player p1, p2;
        OthelloBoard currentBoard = new OthelloBoard();
        Player currentPlayer;

        public OthelloGame(Player p1, Player p2) {
            this.p1 = p1;
            this.p2 = p2;
            currentPlayer = p1;
        }

        private void togglePlayer() {
            if (currentPlayer == p1){
                currentPlayer = p2;
            }else{
                currentPlayer = p1;
            }
        }

        public void run() {
            currentBoard.init();

            boolean over = false;

//            for (int turn = 0; turn < 8 * 8; turn++){
//                byte[][] curBoard = state.getBoard();
//                byte curPlayer = state.getPlayer();
//                int blackScore = OthelloGame.computeScore(curBoard, OthelloGame.B);
//                int whiteScore = OthelloGame.computeScore(curBoard, OthelloGame.W);
//                HashSet<Position> disLegalMoves = new HashSet<>(legalMoves);
//                boolean disOver = over;
//                Future<Position> future = executor.submit(new TimedPlayer(state, legalMoves));
//                Position position = null;
//                if (!over) {
//                    StringBuilder sb = new StringBuilder();
//                    sb.append("-------------------------------------\n");
//                    sb.append("Move Number: ");
//                    sb.append(turn + 1);
//                    sb.append('\n');
//                    sb.append(state);
//                    sb.append("Player: ");
//                    if (curPlayer == OthelloGame.B) {
//                        sb.append(Black.player.name());
//                        sb.append(" (Black)\n");
//                    } else {
//                        sb.append(White.player.name());
//                        sb.append(" (White)\n");
//                    }
//                    sb.append("...thinking...");
//                    System.out.println(sb.toString());
//                }
//
//                    if (over) {
//                        break;
//                    }
//
//                    if (legalMoves.size() == 0) {
//                        StringBuilder sb = new StringBuilder();
//                        sb.append("-------------------------------------\n");
//                        if (state.getPlayer() == OthelloGame.B) {
//                            sb.append(Black.player.name());
//                            sb.append(" (Black)");
//                        } else {
//                            sb.append(White.player.name());
//                            sb.append(" (White)");
//                        }
//                        sb.append(" is out of move!\n");
//                        state.togglePlayer();
//                        legalMoves = OthelloGame.getAllLegalMoves(state.getBoard(), state.getPlayer());
//                        if (legalMoves.size() == 0) {
//                            if (state.getPlayer() == OthelloGame.B) {
//                                sb.append(Black.player.name());
//                                sb.append(" (Black)");
//                            } else {
//                                sb.append(White.player.name());
//                                sb.append(" (White)");
//                            }
//                            sb.append(" is out of move!\n");
//                            sb.append("Game Over");
//                            over = true;
//                        }
//                        System.out.println(sb.toString());
//                    }
//                }
            //executor.shutdown();
//            }
//        }
//        }
        }
}
