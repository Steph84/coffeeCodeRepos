package antPackage;
import java.util.Random;

//class Terrain creating the world map
public class Terrain {
	private String nameMap;
	private int nbLines;
	private int nbColumns;
	//creation of 2dimensional table of Cell type
	private Cell mapTiles[][];
	private int totalFood = 0;
	
	//default constructor
	public Terrain(){
	}
	
	public Terrain(String nameMap, int nbLines, int nbColumns) {

		setNbColumns(nbColumns);
		setNbLines(nbLines);
		
		mapTiles = new Cell[nbColumns][nbLines];
		int rand;
		
		for(int i = 0; i < nbColumns; i++){
			for(int j = 0; j < nbLines; j++){
				
				mapTiles[i][j] = new Cell();
				
//creation of food tiles
				Random rType = new Random();
				rand = rType.nextInt(10) + 1;
				if (rand == 1 || rand == 10) { //20% of food on the map
					totalFood++;
					mapTiles[i][j].setWhatsDat(TileType.FOOD);
				} else {
					mapTiles[i][j].setWhatsDat(TileType.NOTHING_HERE);
				}
			}
		}
		System.out.println("food tiles : " + totalFood);
		
//creation of zone tiles
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				mapTiles[i][j].setWhereIsIt(TileZone.RED_HOME);
			}
		}
		
		for(int i = 10; i < 15; i++){
			for(int j = 0; j < 15; j++){
				mapTiles[i][j].setWhereIsIt(TileZone.RED_CONFORT);
			}
		}
		for(int i = 0; i < 10; i++){
			for(int j = 10; j < 15; j++){
				mapTiles[i][j].setWhereIsIt(TileZone.RED_CONFORT);
			}
		}
		
		for(int i = 15; i < 20; i++){
			for(int j = 0; j < 20; j++){
				mapTiles[i][j].setWhereIsIt(TileZone.RED_EXPLORE);
			}
		}
		for(int i = 0; i < 15; i++){
			for(int j = 15; j < 20; j++){
				mapTiles[i][j].setWhereIsIt(TileZone.RED_EXPLORE);
			}
		}
		
		for(int i = 20; i < 25; i++){
			for(int j = 0; j < 25; j++){
				mapTiles[i][j].setWhereIsIt(TileZone.RED_ADV_FIGHT);
			}
		}
		for(int i = 0; i < 20; i++){
			for(int j = 20; j < 25; j++){
				mapTiles[i][j].setWhereIsIt(TileZone.RED_ADV_FIGHT);
			}
		}
		
		for(int i = 50; i < 60; i++){
			for(int j = 20; j < 30; j++){
				mapTiles[i][j].setWhereIsIt(TileZone.GREEN_HOME);
			}
		}
		
		for(int i = 45; i < 60; i++){
			for(int j = 15; j < 20; j++){
				mapTiles[i][j].setWhereIsIt(TileZone.GREEN_CONFORT);
			}
		}
		for(int i = 45; i < 50; i++){
			for(int j = 20; j < 30; j++){
				mapTiles[i][j].setWhereIsIt(TileZone.GREEN_CONFORT);
			}
		}
		
		for(int i = 40; i < 60; i++){
			for(int j = 10; j < 15; j++){
				mapTiles[i][j].setWhereIsIt(TileZone.GREEN_EXPLORE);
			}
		}
		for(int i = 40; i < 45; i++){
			for(int j = 15; j < 30; j++){
				mapTiles[i][j].setWhereIsIt(TileZone.GREEN_EXPLORE);
			}
		}
		
		for(int i = 35; i < 60; i++){
			for(int j = 5; j < 10; j++){
				mapTiles[i][j].setWhereIsIt(TileZone.GREEN_ADV_FIGHT);
			}
		}
		for(int i = 35; i < 40; i++){
			for(int j = 10; j < 30; j++){
				mapTiles[i][j].setWhereIsIt(TileZone.GREEN_ADV_FIGHT);
			}
		}
		
		for(int i = 25; i < 35; i++){
			for(int j = 0; j < 30; j++){
				mapTiles[i][j].setWhereIsIt(TileZone.FIGHT_ZONE);
			}
		}
		
		for(int i = 35; i < 60; i++){
			for(int j = 0; j < 5; j++){
				mapTiles[i][j].setWhereIsIt(TileZone.NO_ANTS_LAND);
			}
		}
		for(int i = 0; i < 25; i++){
			for(int j = 25; j < 30; j++){
				mapTiles[i][j].setWhereIsIt(TileZone.NO_ANTS_LAND);
			}
		}
		
		
		
		
		
	}

//*****************getter and setter*****************************	
	public int getNbLines() {
		return nbLines;
	}

	public void setNbLines(int nbLines) {
		this.nbLines = nbLines;
	}

	public int getNbColumns() {
		return nbColumns;
	}

	public void setNbColumns(int nbColumns) {
		this.nbColumns = nbColumns;
	}

	public String getNameMap() {
		return nameMap;
	}

	public void setNameMap(String nameMap) {
		this.nameMap = nameMap;
	}
	
	public Cell GetCell(int X, int Y){
		return this.mapTiles[X][Y];
	}

	public int getTotalFood() {
		return totalFood;
	}

	public void setTotalFood(int totalFood) {
		this.totalFood = totalFood;
	}
}
