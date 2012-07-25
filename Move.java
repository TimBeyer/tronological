public class Move{

    private int[] position;
    private int direction;
    // 0 = player, 1 = opponent
    private int player;
    
    public Move(int[] position, int direction, int player){
	this.position = position;
	this.direction = direction;
	this.player = player;
    }

    public int[] getPosition(){return position.clone();}
    public int getDirection(){return direction;}
    public int getPlayer(){return player;}
    public int[] getNewPosition(){

	int[] newPosition = position.clone();
	switch(direction){
	  case 1: newPosition[1] -= 1;break;
	  case 2: newPosition[0] += 1;break;
	  case 3: newPosition[1] += 1;break;
	  case 4: newPosition[0] -= 1;break;
	}
	return newPosition;
    }

    public String toString(){
      return "Position: \n" + "Player: " + player + "\nDirection: " + direction + "\nNewPosition: " + java.util.Arrays.toString(getNewPosition());
    }
}