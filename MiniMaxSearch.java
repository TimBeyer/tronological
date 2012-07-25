public class MiniMaxSearch implements Search{

    public GameState doSearch(GameState g, Evaluator e){


	return null;
    }

    public GameState doSearch(GameState g, Evaluator e,int player, int depth){

	//first get the successors for this move and then make a minimax tree for each separately
	GameState[] states = g.getSuccessorStates(player);
	//let's see what successor states it actually gives us
// 	for(GameState gS : states){
// 	  System.err.println(gS.getPreviousPlayerMove().getDirection());
// 	}
	//now create tree for each state
	MiniMaxNode[] trees = new MiniMaxNode[states.length];
	for(int i = 0; i < trees.length; i++){
	    trees[i] = MiniMaxNode.getGameTree(states[i],e,player,depth);
// 	    System.err.println(trees[i].getElem().getPreviousPlayerMove().getDirection());
	}
	//look for best and choose
	int eval = 0;
	MiniMaxNode bestNode = new MiniMaxNode(states[0],e,player);
	for(int i = 0; i < trees.length; i++){
	  int evaluation = trees[i].evaluate();
	 // System.err.println("Minimax: Evalution - " + evaluation);
	  if(evaluation > eval){
	      eval = evaluation;
	      bestNode = trees[i];
	  }

	}
// 	System.err.println(bestNode.getElem().getPreviousPlayerMove().getDirection());
	return bestNode.getElem();

    }


 


}