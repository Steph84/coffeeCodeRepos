package antPackage;

//class Cell containing the attributes of each cell
public class Cell {

	private int cellX;
	private int cellY;
	private TileType whatsDat;
	private Combattant someOne;
	private TileZone whereIsIt;
	private int nbFighterThrough = 0;

	
	public Cell(){
	}
	
	//attributes of position, tile type and presence of someone
	public Cell(int cellX, int cellY) {
		this.setCellX(cellX);
		this.setCellY(cellY);
		this.setWhatsDat(whatsDat);
		this.setSomeOne(someOne);
		this.setWhereIsIt(whereIsIt);
		this.setNbFighterThrough(nbFighterThrough);
	}
	
//*****************getter and setter*****************************
	public TileType getWhatsDat() {
		return whatsDat;
	}

	public void setWhatsDat(TileType whatsDat) {
		this.whatsDat = whatsDat;
	}

	public int getCellX() {
		return cellX;
	}

	public void setCellX(int cellX) {
		this.cellX = cellX;
	}

	public int getCellY() {
		return cellY;
	}

	public void setCellY(int cellY) {
		this.cellY = cellY;
	}

	public Combattant getSomeOne() {
		return someOne;
	}

	public void setSomeOne(Combattant someOne) {
		this.someOne = someOne;
	}

	public TileZone getWhereIsIt() {
		return whereIsIt;
	}

	public void setWhereIsIt(TileZone whereIsIt) {
		this.whereIsIt = whereIsIt;
	}

	public int getNbFighterThrough() {
		return nbFighterThrough;
	}

	public void setNbFighterThrough(int nbAntThrough) {
		this.nbFighterThrough = nbAntThrough;
	}
}
