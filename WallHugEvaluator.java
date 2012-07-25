public class WallHugEvaluator implements Evaluator{

   public int evaluation(GameState g, int player){
	MapAnalyzer m = new MapAnalyzer(g);
	return m.surroundingWalls(player == 0 ? g.getPlayerCoords() : g.getOpponentCoords());
   }

}