public class GameState{

    //stores x and y coordinate of opponent and player
    private int[] player, opponent;
    private int[][] map;
    //need a dummy move  
    private Move previousPlayerMove = new Move(player,1,0);

    public GameState(int[][] map, int playerX, int playerY, int opponentX, int opponentY){
	this.map = Utils.cloneArray(map);
	player = new int[2];
	opponent = new int[2];
	player[0] = playerX;
	player[1] = playerY;
	opponent[0] = opponentX;
	opponent[1] = opponentY;
    }

    public GameState(int[][] map, int[] player, int[] opponent){
	this.map = Utils.cloneArray(map);
	this.player = new int[2];
	this.opponent = new int[2];
	this.player = player;
	this.opponent = opponent;
    }

    public GameState(int[][] map, int[] player, int[] opponent, Move previousPlayerMove){
	this.map = Utils.cloneArray(map);
	this.player = new int[2];
	this.opponent = new int[2];
	this.player = player;
	this.opponent = opponent;
	this.previousPlayerMove = previousPlayerMove;

    }

    public GameState(GameState g){
	this.map = g.getMap();
	this.player = g.getPlayerCoords();
	this.opponent = g.getOpponentCoords();
	this.previousPlayerMove = g.getPreviousPlayerMove();
    }

    public Move getPreviousPlayerMove(){
	return previousPlayerMove;
    }
  
    public int[][] getMap(){
      return Utils.cloneArray(map);
    }

    public int[] getPlayerCoords(){
      return player.clone();
    }
    
    public int[] getOpponentCoords(){
      return opponent.clone();
    }
    
    //returns -1 if player lost, 0 if still unclear or draw, 1 if player won
    public int getCurrentResult(){
      MapAnalyzer ma = new MapAnalyzer(map);
      int playerFields = ma.fieldSize(player);
      int opponentFields = ma.fieldSize(opponent);
      if(player[0] == opponent[0] && player[1] == opponent[1]) return 0;
      if(playerFields <= 1) return -1;
      if(opponentFields <= 1) return 1;
      return 0;

    }

    public GameState[] getSuccessorStates(){
      		
	/*
	  This 2d array holds all combinations of moves for both players 
    
	*/
	Move[][] successorMoves = getValidSuccessorMoves();
	GameState[] successors = new GameState[successorMoves.length];
	for(int i = 0; i < successors.length; i++){
	  successors[i] = getStateAfterMoves(successorMoves[i]);
	}
	return successors;
    }
    
    public GameState[] getSuccessorStates(int player){

	  Move[] moves = getValidSuccessorMoves(player);
	  GameState[] successors = new GameState[moves.length];
	  for(int i = 0; i < successors.length; i++){
	    successors[i] = getStateAfterMove(moves[i]);
	  }
	  return successors;
    }

    private boolean isWall(int[] position){
	if(map[position[0]][position[1]] == 1) return true;
	return false;
    }

    //returns if there is a player at this position
    private boolean isPlayer(int[] position){
	return((player[0] == position[0] && player[1] == position[1]) || (opponent[0] == position[0] && opponent[1] == position[1]));
    }

    private int[][] executeMove(int[][] board, Move m){
	  int[] newPosition = m.getNewPosition();
	  board[newPosition[0]][newPosition[1]] = 1;
	  return board;
    }
  

    public GameState getStateAfterMove(Move m){
	return new GameState(executeMove(Utils.cloneArray(map),m),m.getPlayer() == 0 ? m.getNewPosition() : getPlayerCoords(), m.getPlayer() == 1 ? m.getNewPosition() : getOpponentCoords(), m.getPlayer() == 0 ? m : getPreviousPlayerMove()); 
    }

    public GameState getStateAfterMoves(Move[] moves){
	if(moves.length == 1) return getStateAfterMove(moves[0]);
	else if (moves.length > 1){
	  GameState g = getStateAfterMove(moves[0]);
	  for(int i = 1; i < moves.length; i++){
	    g = g.getStateAfterMove(moves[i]);
	  }
	  return g;
	}
	return null;
    }

    private Move[][] getValidSuccessorMoves(){
	  java.util.ArrayList<Move[]> moves = new java.util.ArrayList<Move[]>();

	  int[] playerDirections = possibleDirections(player);
	  int[] opponentDirections = possibleDirections(opponent);

	  int successorStatesSize = playerDirections.length * opponentDirections.length;
	  
	  /*
	    This 2d array holds all combinations of moves for both players 
      
	  */

	  for(int i = 0; i < playerDirections.length; i++){
	    for(int j = 0; j < opponentDirections.length; j++){
	
	      Move[] successorMoves = new Move[2];
	      successorMoves[0] = new Move(player,playerDirections[i],0);
	      successorMoves[1] = new Move(opponent,opponentDirections[j],1);
	      
	      //check for collision
	      //if(!isPlayer(successorMoves[0].getNewPosition()) && !isPlayer(successorMoves[1].getNewPosition())){
		moves.add(successorMoves);
	      //}
	    }
	  }
    
	  Move[][] successors = new Move[moves.size()][2];
	  for(int i = 0; i < moves.size(); i++){

	    successors[i] = moves.get(i);
	  }
	  return successors;
	  

    }

    private Move[] getValidSuccessorMoves(int player){
      
	int[] playerArray = (player == 0 ? this.player : this.opponent);
	int[] directions = possibleDirections(playerArray);
	Move[] successors = new Move[directions.length];
	for(int i = 0; i < directions.length; i++){
	    successors[i] = new Move(playerArray,directions[i],player);
	}
	return successors;

    }



    /*
    *	returns an array that contains the numbers that correspond to the directions that can be chosen
    *	Directions:
    *	1 -- North
    *	2 -- East
    *	3 -- South
    *	4 -- West
    *
    */
    private int[] possibleDirections(int[] position){
      java.util.ArrayList<Integer> directions = new java.util.ArrayList<Integer>();
      if(map[position[0]][position[1] - 1] != 1) directions.add(1);
      if(map[position[0] + 1][position[1]] != 1) directions.add(2);
      if(map[position[0]][position[1] + 1] != 1) directions.add(3);
      if(map[position[0] - 1][position[1]] != 1) directions.add(4);
      int[] directionsArray = new int[directions.size()];
      for(int i = 0; i < directions.size(); i++){
	directionsArray[i] = directions.get(i);    
      }
      return directionsArray;
    }


    public GameState clone(){
	return new GameState(map,player,opponent);     
    }

}