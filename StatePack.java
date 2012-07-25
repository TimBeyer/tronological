import java.util.ArrayList;

/*
      Contains some GameStates that are packed together.
      Used to sort the GameStates so that the same directions are in one pack and can be averaged


    */
    public class StatePack{

	public GameState[] gameStates;

	public StatePack(GameState[] gameStates){
	    this.gameStates = gameStates;
	}

	

	public int averageStateEvaluation(Evaluator e){
	  int score = 0;
	  for(GameState g : gameStates){
	      score += e.evaluation(g,MyTronBot.player);
	  }
	  return score/gameStates.length;
	}

	@SuppressWarnings({"unchecked"})
	public static StatePack[] packByDirection(GameState[] g){

	    //first use an array to count how big every move section will be

	    int[] moves = new int[4]; //notice the -1 shift for the directions because of index 0

	    for(GameState gs : g){
	      moves[gs.getPreviousPlayerMove().getDirection() - 1] += 1;
	    }
      
	    // now check how many we need
	    int cells = 0;

	    for(int i : moves){
		if(i>0) cells += 1;
	    }

	    int currentIndex = 0;

	    //An array of array lists because I can't find another way atm :(
	    ArrayList[] directionsLists = new ArrayList[cells];
	    for(int i = 0; i < directionsLists.length; i++) directionsLists[i] = new ArrayList<GameState>();

	    for(ArrayList al : directionsLists){
		if(currentIndex >= moves.length) break;
		//fast forward to next filled
		while(moves[currentIndex] <= 0) currentIndex++;

		for(GameState gs : g){
		  if((gs.getPreviousPlayerMove().getDirection() - 1) == currentIndex) al.add(gs);
		}
		currentIndex++;
	    }

	    //now just unpack the array lists in a state pack

	    StatePack[] statePacks = new StatePack[cells];
	    for(int i = 0; i < statePacks.length; i++){
		
	      GameState[] s = new GameState[directionsLists[i].size()];
	      for(int j = 0; j < s.length; j++){
		  try{
		  s[j] =(GameState) directionsLists[i].get(j);
		  }
		  catch(Exception e){}
	      }
	      statePacks[i] = new StatePack(s);

	    }

	    return statePacks;

	}

    }