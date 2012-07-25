public class ChaseEvaluator implements Evaluator{

   public int evaluation(GameState g, int player){
	int direction = g.getPreviousPlayerMove().getDirection();
	int[][] map = g.getMap();

	int[][] newMap = MapManipulator.straightLineFromPosition(map,player == 1 ? g.getOpponentCoords() : g.getPlayerCoords(),direction);
	int distance = MapManipulator.distanceStraightLine(map,player == 1 ? g.getOpponentCoords() : g.getPlayerCoords(),direction);
	MapAnalyzer m = new MapAnalyzer(newMap);

	//m.printMap();
	
	int playerFieldSize = m.fieldSize(player == 1 ? g.getOpponentCoords() : g.getPlayerCoords());
	int opponentFieldSize = m.fieldSize(player == 0 ? g.getOpponentCoords() : g.getPlayerCoords());

	//System.err.println("Utility cutoff: " + (playerFieldSize-opponentFieldSize));

	int evaluation = playerFieldSize - opponentFieldSize;
	if(evaluation> 0) return evaluation; //magic number here, attention
	else return 0;
	
   }

  

}