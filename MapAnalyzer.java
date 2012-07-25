public class MapAnalyzer{

    public int[][] map;

    public MapAnalyzer(int[][] map){ this.map = Utils.cloneArray(map);}
    public MapAnalyzer(GameState g){ this.map = g.getMap();}


    public int surroundingWalls(int[] position){
	int x = position[0], y = position[1];
	//System.err.println("x: " + x + "Y: " + y);
	int walls = 0;
	if(map[x+1][y] == 1) walls++;
	if(map[x][y+1] == 1) walls++;
	if(map[x][y-1] == 1) walls++;
	if(map[x-1][y] == 1) walls++;

	return walls;
    }

    public int fieldSize(int[] position){
	//System.err.println("Position: " + position[0] + "," + position[1]);
	int[][] floodMap = Utils.cloneArray(map);
	return 1 + floodFill(floodMap,position[0] + 1,position[1]) + floodFill(floodMap,position[0] - 1,position[1]) + 
		    floodFill(floodMap,position[0],position[1] + 1) + floodFill(floodMap,position[0],position[1] - 1);
    }

    private int floodFill(int[][] floodMap,int[] position){ return MapManipulator.floodFill(floodMap,position);}
    private int floodFill(int[][] floodMap,int x, int y){ return MapManipulator.floodFill(floodMap,x,y);}

    public boolean bothInSameField(int[] position1, int[] position2){
	int[][] floodMap = Utils.cloneArray(map);
	int pos1Size = 1 + floodFill(floodMap,position1[0] + 1,position1[1]) + floodFill(floodMap,position1[0] - 1,position1[1]) + 
		    floodFill(floodMap,position1[0],position1[1] + 1) + floodFill(floodMap,position1[0],position1[1] - 1);
	int pos2Size =  1 + floodFill(floodMap,position2[0] + 1,position2[1]) + floodFill(floodMap,position2[0] - 1,position2[1]) + 
		    floodFill(floodMap,position2[0],position2[1] + 1) + floodFill(floodMap,position2[0],position2[1] - 1);
	
	// if both fields are filled in the same floodMap, if they are in fact the same field, then pos2Size will be 1
	return pos2Size <= 1;
    }


    // Outputs an ASCII representation of the map to the console.
    public void printMap() {
	int width = map.length, height = map[0].length;
	
	System.err.println("MapAnalyzer " + width + " " + height);
	for (int y = 0; y < height; ++y) {
	    for (int x = 0; x < width; ++x) {
		    System.err.write(map[x][y] == 1 ? '#' : ' ');
	    }
	    System.err.write('\n');
	}
	System.err.flush();
    }

}