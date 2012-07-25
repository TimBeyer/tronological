// MyTronBot.java
// Author: your name goes here

import java.util.*;

class MyTronBot {

    public static final int player = 0, opponent = 1;


    public static String MakeMove() {
	int x = Map.MyX();
	int y = Map.MyY();

	GameState g = new GameState(readMap(),x,y,Map.OpponentX(),Map.OpponentY());
	GameState[] succ = g.getSuccessorStates();
	GameState[] succPlayer = g.getSuccessorStates(player);

	//fix that later
	if(succ.length == 0) {
	   return randomMove();
	}

	//let's see if they are both separated already
	MapAnalyzer ma = new MapAnalyzer(g);
	boolean separated = !ma.bothInSameField(g.getPlayerCoords(),g.getOpponentCoords());
	int playerField = ma.fieldSize(g.getPlayerCoords());
	int opponentField = ma.fieldSize(g.getOpponentCoords());
	
	EvaluatorCollection ec = new EvaluatorCollection();
	  ec.add(new FloodFillEvaluator());	
	if(separated){
	  ec.add(new WallHugEvaluator());
	}
	if(!separated && opponentField >= playerField){

//	    ec.add(new CutOffEvaluator());
	    ec.add(new ChaseEvaluator());
	}

	Evaluator finalEval = new MultiplyEvaluators(new GameWinEvaluator(), ec);

	MiniMaxSearch minmax = new MiniMaxSearch();
// 
	GameState best = minmax.doSearch(g,finalEval,player,1);
	 
// 	StateChooser gsc = new GreedyStateChooser(finalEval);	
// 	GameState best = gsc.choose(succPlayer);
	//System.err.println("Result: " + best.getCurrentResult());
	return intDirectionToString(best.getPreviousPlayerMove().getDirection());


    }

    public static String randomMove(){
	   if(getValidMoves().length > 0)
	      return intDirectionToString(getValidMoves()[new java.util.Random().nextInt(getValidMoves().length)]);
	    else return "North";
    }




    public static int[] getValidMoves(){

	List<String> validMoves = new ArrayList<String>();
	
	int x = Map.MyX();
	int y = Map.MyY();
      
	if (!Map.IsWall(x,y-1)) {
	    validMoves.add("North");
	}
	if (!Map.IsWall(x+1,y)) {
	    validMoves.add("East");
	}
	if (!Map.IsWall(x,y+1)) {
	    validMoves.add("South");
	}
	if (!Map.IsWall(x-1,y)) {
	    validMoves.add("West");
	}
	
	int[] moves = new int[validMoves.size()];
	
	for(int i = 0; i < moves.length; i++){
	   moves[i] = StringDirectionToint(validMoves.get(i));
	   //System.err.println(validMoves.get(i));
	}
	return moves;

    }


    public static String intDirectionToString(int i){
	switch(i){
	  case 1: return "North";
	  case 2: return "East";
	  case 3: return "South";
	  case 4: return "West";
	}
	return null;
    }

    public static int StringDirectionToint(String s){
	
	if(s == "North") return 1;
	if(s == "East") return 2;
	if(s == "South") return 3;
	if(s == "West") return 4;
	
	return 0;
    }

    public static int[][] readMap(){

	int[][] map = new int[Map.Width()][Map.Height()];
	for(int x = 0; x < Map.Width(); x++){
	  for(int y = 0; y < Map.Height(); y++){
	      map[x][y] = (Map.IsWall(x,y) ? 1 : 0);
	  }
	}
	return map;
    }

    // Ignore this method. It's just doing boring stuff like communicating
    // with the contest tournament engine.
    public static void main(String[] args) {
	while (true) {
	    Map.Initialize();
	    Map.MakeMove(MakeMove());
}
    }
}
