import java.util.ArrayList;

public class GreedyStateChooser implements StateChooser{

   public Evaluator e;

    public GreedyStateChooser(Evaluator e){this.e = e;}

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

	return best;

    }

}