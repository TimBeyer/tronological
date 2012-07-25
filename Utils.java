public class Utils{

        public static int[][] cloneArray(int[][] array){
	int[][] newArray = new int[array.length][array[0].length];
	for(int x = 0; x < array.length; x++){
	  for(int y = 0; y < array[0].length ; y++){
	      newArray[x][y] = array[x][y];
	  }
	}
	return newArray;
    }


}