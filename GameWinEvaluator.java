public class GameWinEvaluator implements Evaluator{

      
   public int evaluation(GameState g, int player){
      int winStatus = g.getCurrentResult();
      if(player == MyTronBot.player && winStatus == 1) return Integer.MAX_VALUE;
      if(player == MyTronBot.player && winStatus == 0) return 1;
      if(player == MyTronBot.opponent && winStatus == -1) return Integer.MAX_VALUE;
      if(player == MyTronBot.opponent && winStatus == 0) return 1;
      return 0;
   }

}