package antPackage;

//class FourmiChef inherits from abstract class Fourmi
public class FourmiChef extends Fourmi {

	private int warriorCount;
	private int foodCount;
	private int leftOvers;
	FourmiReine myQueen;
	private int totalFoodCount;
	private int countTab[];

	public FourmiChef(int idFourmi, int team, int posX, int posY, Terrain map, int nbWarrior, FourmiReine ant) {
		super(idFourmi, team, posX, posY, map);
		warriorCount = nbWarrior;
		setFoodCount(0);
		setTotalFoodCount(0);
		myQueen = ant;
	}
	
	@Override
	public synchronized void run() { //waking up synchronized with the death of a warrior
//		System.out.println("warriorCount " + warriorCount);
		
		while(warriorCount > 0){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("warriorCount = " + warriorCount);
		}
		System.out.println("the chief of " + this.myQueen.queenName + " is dead");
		System.out.println("stock of " + this.myQueen.queenName + " is " + this.foodCount);
		System.out.println("pinaise !");
		
		countTab = new int[10];
		showCrowding(this.posX, this.posY, map, countTab);
		for (int i = 0; i < countTab.length; i++) {
			System.out.println(countTab[i]);
		}
		
	}

//*************************************FourmiChef methods***********************************************	
	//hatch of the chiefs
	@Override
	public void hatch() {
		// TODO Auto-generated method stub
		new Thread(this).start();
	}

	//notify synchronized for the death of a warrior
	public synchronized void fourmiMeurt(){
		warriorCount--;
		this.notify();
	}
	
	//synchronization to count the food delivery
	public synchronized void giveFood(int qtyFood){
		this.foodCount = this.foodCount + qtyFood;
	}
	
	public int[] showCrowding(int posX, int posY, Terrain map, int[] tab){
		
		for(int i = 0; i < map.getNbColumns(); i++){
			for(int j = 0; j < map.getNbLines(); j++){
				switch(map.GetCell(i, j).getWhereIsIt()){
					case FIGHT_ZONE :
						tab[5] = tab[5] + map.GetCell(i, j).getNbFighterThrough();
						break;
					case GREEN_ADV_FIGHT :
						tab[6] = tab[6] + map.GetCell(i, j).getNbFighterThrough();
						break;
					case GREEN_CONFORT :
						tab[8] = tab[8] + map.GetCell(i, j).getNbFighterThrough();
						break;
					case GREEN_EXPLORE :
						tab[7] = tab[7] + map.GetCell(i, j).getNbFighterThrough();
						break;
					case GREEN_HOME :
						tab[9] = tab[9] + map.GetCell(i, j).getNbFighterThrough();
						break;
					case NO_ANTS_LAND :
						tab[4] = tab[4] + map.GetCell(i, j).getNbFighterThrough();
						break;
					case RED_ADV_FIGHT :
						tab[3] = tab[3] + map.GetCell(i, j).getNbFighterThrough();
						break;
					case RED_CONFORT :
						tab[1] = tab[1] + map.GetCell(i, j).getNbFighterThrough();
						break;
					case RED_EXPLORE :
						tab[2] = tab[2] + map.GetCell(i, j).getNbFighterThrough();
						break;
					case RED_HOME :
						tab[0] = tab[0] + map.GetCell(i, j).getNbFighterThrough();
						break;
					default :
						break;
					
				}
				
			}
		}
		
		
		
		
		
		
		return tab;
	}
	
	
//************************************getter and setter**********************************************
	public int getFoodCount() {
		return foodCount;
	}

	public void setFoodCount(int foodCount) {
		this.foodCount = foodCount;
	}
	
	public int getLeftOvers() {
		return leftOvers;
	}

	public void setLeftOvers(int leftOvers) {
		this.leftOvers = leftOvers;
	}

	public int getTotalFoodCount() {
		return totalFoodCount;
	}

	public void setTotalFoodCount(int totalFoodCount) {
		this.totalFoodCount = totalFoodCount;
	}
}
