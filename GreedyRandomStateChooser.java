import java.util.ArrayList;

public class GreedyRandomStateChooser implements StateChooser{

    public Evaluator e;

    public GreedyRandomStateChooser(Evaluator e){this.e = e;}

    public GameState choose(GameState[] states){

	GameState best = states[0];
	int bestEvaluation = 0;

	for(int i = 0; i < states.length; i++){
	  int evaluation = e.evaluation(states[i],MyTronBot.player);
	  if(evaluation > bestEvaluation){
	    best = states[i];
	    bestEvaluation = evaluation;
	  }
	}

	//Go through them again and pick random if there is more than one best state
	ArrayList<GameState> randomPool = new ArrayList<GameState>();

	for(int i = 0; i < states.length; i++){	  
	  int evaluation = e.evaluation(states[i],MyTronBot.player);
	  if(evaluation == bestEvaluation){
	    randomPool.add(states[i]);

	  }
	}

	java.util.Random generator = new java.util.Random();
	
    
	return randomPool.get(generator.nextInt(randomPool.size()));

    }




}