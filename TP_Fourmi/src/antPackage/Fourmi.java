package antPackage;

//abstract class Fourmi using the interfaces Eggable and runnable for the threads
abstract class Fourmi implements Runnable, Eggable{
	
	protected int posX;
	protected int posY;
	protected int idFourmi;
	protected Terrain map;
	protected int team;
	
	//default constructor
	public Fourmi(){
	}
	
	//my constructor
	public Fourmi(int idFourmi, int team, int posX, int posY, Terrain map) {
		this.setIdFourmi(idFourmi);
		this.setMap(map);
		this.setPosX(posX);
		this.setPosY(posY);
		this.setTeam(team);
	}

//*****************getter and setter*****************************
	protected int getPosX() {
		return posX;
	}

	protected void setPosX(int posX) {
		this.posX = posX;
	}

	protected int getPosY() {
		return posY;
	}

	protected void setPosY(int posY) {
		this.posY = posY;
	}

	public int getIdFourmi() {
		return idFourmi;
	}

	public void setIdFourmi(int idFourmi) {
		this.idFourmi = idFourmi;
	}

	public Terrain getMap() {
		return map;
	}

	public void setMap(Terrain map) {
		this.map = map;
	}	
	
	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}
}

