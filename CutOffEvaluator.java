public class CutOffEvaluator implements Evaluator{

   public int evaluation(GameState g, int player){
	int direction = g.getPreviousPlayerMove().getDirection();
	int[][] map = g.getMap();

	int[][] newMap = MapManipulator.straightLineFromPosition(map,player == 1 ? g.getOpponentCoords() : g.getPlayerCoords(),direction);
	int distance = MapManipulator.distanceStraightLine(map,player == 1 ? g.getOpponentCoords() : g.getPlayerCoords(),direction);

	//m.printMap();

	//since MapAnalyzer actually clones the arrays and so player reports the wrong size if being part of the wall, manipulate the array directly
	//and fill opponent first, then check the player field size. this will report correct size
	int opponentFieldSize = MapManipulator.fieldFill(newMap,player == 0 ? g.getOpponentCoords() : g.getPlayerCoords());
	int playerFieldSize = MapManipulator.fieldFill(newMap,player == 1 ? g.getOpponentCoords() : g.getPlayerCoords());


	//System.err.println("OpponentFieldSize: " + opponentFieldSize + " PlayerFieldSize: " + playerFieldSize);
	//System.err.println("Utility cutoff: " + (playerFieldSize-opponentFieldSize));
	int evaluation = playerFieldSize - opponentFieldSize;

	//now what to do with distance? we want to minimize it but we need higher evaluations for better positions
	//and there is no (obvious) maximum distance. 
	//try array size - length

	int sideLength = (direction == 1 || direction == 3) ? newMap[0].length : newMap.length;

	int distanceEvaluation = sideLength - distance;

	int finalEval = 0;


	//System.err.println("Distance Eval: " + distanceEvaluation + "\nEval: " + evaluation);
	if(opponentFieldSize < playerFieldSize) finalEval = playerFieldSize + distanceEvaluation;
	
	//System.err.println("FinalEval: " + finalEval);
	return finalEval;

// 	if(finalEval > 0) return finalEval;
// 	else return 0;
	
   }

  

}