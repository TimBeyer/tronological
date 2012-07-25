public class MiniMaxNode{

    //player info to know whether max or min is chosen
    private int player = 0;
    private GameState state;
    private Evaluator e;
    private MiniMaxNode[] children;
    private MiniMaxNode parent;

    public MiniMaxNode(GameState g, Evaluator e, int player){

	
	this.state = g;
	this.e = e;
	this.player = player;
	parent = this;
  
    }

    public MiniMaxNode(GameState g, Evaluator e, int player, MiniMaxNode parent){

	this.state = g;
	this.e = e;
	this.player = player;
	this.parent = parent;
  
    }

    public GameState getElem(){return state;}

    public int evaluate(){
      
      //player is always 0
      if(children == null || children.length == 0) return e.evaluation(state,0);
      //opponent playing, minimize outcome
      if(player == 1){
	int min = Integer.MAX_VALUE;
	for(MiniMaxNode n : children){
	  int evaluation = n.evaluate();
	  if(evaluation < min) min = evaluation;
	}
	//System.err.println("Min: " + min);
	return min;
      }
      //player playing, maximize outcome
      if(player == 0){
	int max = 0;
	for(MiniMaxNode n : children){
	  int evaluation = n.evaluate();
	  if(evaluation > max) max = evaluation;
	}
	//System.err.println("Max: " + max);
	return max;
      }
      //we should not get here
      return 0;
    }

    //creates the child nodes and expands the current node with them
    public void expand(){
	//next moves will always be the other players moves
	
	//opponent moves need successors generated from parent because of simulatneous moves
	GameState[] childStates = new GameState[1];
	if(player == 0){
	    childStates = parent.getElem().getSuccessorStates((player + 1)%2);

	}
	else{
	    childStates = state.getSuccessorStates((player + 1)%2);
	}
	children = new MiniMaxNode[childStates.length];
	for(int i = 0; i < children.length; i++){
	    children[i] = new MiniMaxNode(childStates[i],e,(player + 1)%2,this);
	}
    }

    private void expand(int depth){

	if(depth > 0){
	  expand();
	  for(MiniMaxNode n : children){
	    n.expand(depth - 1);
	  }

	}

    }

    public static MiniMaxNode getGameTree(GameState g,Evaluator e,int player,int depth){
	MiniMaxNode n = new MiniMaxNode(g,e,player);
	n.expand(depth);
	return n;
    }



}