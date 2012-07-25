public class FloodFillEvaluator implements Evaluator{

  public int evaluation(GameState g, int player){
	MapAnalyzer m = new MapAnalyzer(g);
	return m.fieldSize(player == 0 ? g.getPlayerCoords() : g.getOpponentCoords());
  }


}