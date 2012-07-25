import java.util.ArrayList;

public class EvaluatorCollection implements Evaluator{

    private ArrayList<Evaluator> evaluators = new ArrayList<Evaluator>();
    
    public void add(Evaluator e){ evaluators.add(e);}

    public int evaluation(GameState g, int player){
	
	int evaluation = 0;
	for(Evaluator e : evaluators) evaluation += e.evaluation(g,player);
	return evaluation;

    }

}