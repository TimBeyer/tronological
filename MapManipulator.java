public class MapManipulator{

         /*
    *	Returns a board where a straight line of walls is inserted between (x,y) and the next wall 
    *	Directions:
    *	1 -- North
    *	2 -- East
    *	3 -- South
    *	4 -- West
    */
    public static int[][] straightLineFromPosition(int[][] map, int[] position, int direction){
	int x = position[0], y = position[1];
	//Let's clone it first
	int[][] newMap = Utils.cloneArray(map);

	boolean wallHit = false;

	int i = x, j = y;
	while(!wallHit){

	    if(direction == 1)
	      j--;
	    else if(direction == 2)
	      i++;
	    else if(direction == 3)
	      j++;
	    else if(direction == 4)
	      i--;
	    if(newMap[i][j] != 1){
	      newMap[i][j] = 1;
	    }
	    else{
	      wallHit = true;
	    }
	}
	return newMap;
    }

    public static int distanceStraightLine(int[][] map, int[] position, int direction){
	//Let's clone it first

	int x = position[0], y = position[1];

	int[][] newMap = Utils.cloneArray(map);

	boolean wallHit = false;
	int length = 0;
	int i = x, j = y;
	while(!wallHit){

	    if(direction == 1)
	      j--;
	    else if(direction == 2)
	      i++;
	    else if(direction == 3)
	      j++;
	    else if(direction == 4)
	      i--;
	    if(newMap[i][j] != 1){
	      length++;
	    }
	    else{
	      wallHit = true;
	    }
	}
	return length;
    }

    public static int floodFill(int[][] map, int[] coords){ return floodFill(map,coords[0],coords[1]);}

    public static int floodFill(int[][] map, int x, int y){
	if(x < 0 || x >= map.length || y < 0 || y>= map[0].length) return 0;
	if(map[x][y] == 0){
	   map[x][y] = 5;
	    return  1 + floodFill(map,x+1,y) + floodFill(map,x-1,y) + floodFill(map,x,y+1) + floodFill(map,x,y-1);
	}
	return 0;
    
    }

    public static int fieldFill(int[][] map, int[] position){
	//System.err.println("Position: " + position[0] + "," + position[1]);
	int[][] floodMap = map;
	return 1 + floodFill(floodMap,position[0] + 1,position[1]) + floodFill(floodMap,position[0] - 1,position[1]) + 
		    floodFill(floodMap,position[0],position[1] + 1) + floodFill(floodMap,position[0],position[1] - 1);
    }



}