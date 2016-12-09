package antPackage;
import java.util.ArrayList;

//class FourmiReine inherits from abstract class Fourmi
public class FourmiReine extends Fourmi{
	public int nbTileRow = 30;
	public int nbTileCol = 60;
	private ArrayList<Eggable> tabEggs = new ArrayList<>(); //array list to store the eggs laid
	String queenName; //to use the name of the instance for friendly reading
	
	//default constructor
	public FourmiReine(){
	}
	
	//my constructor
	public FourmiReine(int idFourmi, int team, int posX, int posY, Terrain map, String antName) {
		super(idFourmi, team, posX, posY, map);
		queenName = antName;
	}
	
	
	//run of the queen like the main
	@Override
	public void run() {
		System.out.println("This queen's name is " + this.queenName);
		
		//laying of each queen
		//set the number of chief and warrior ants, their team and their position
//		layEggs(1, 10, this.team, this.posX, this.posY);
		
		//hatch of the eggs
//		hatchEggs();
		
		//infinite loop to maintain the thread
		//sleep of 50 to leave the CPU alone
		while(true){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i = 0; i < nbTileCol; i++){
				for(int j = 0; j < nbTileRow; j++){
					if (this.getMap().GetCell(i, j).getSomeOne() != null){
//						System.out.println("the tile " + i + " : " + j + " : " + this.getMap().GetCell(i, j).getSomeOne().getTeam());
//						if (this.getMap().GetCell(i, j).getSomeOne())
					}
				}
			}
					}		
	}

	//laying eggs method
	//create an array list of all the eggs of the world
	public void layEggs(int nbChief, int nbWarrior, int team, int posX, int posY) {
		for(int c = 1; c <= nbChief; c++)
		{
			FourmiChef chef = new FourmiChef(c*100, this.team, this.posX, this.posY, map, nbWarrior, this);
			tabEggs.add(chef);
			for(int w = 0; w < nbWarrior; w++)
			{
				FourmiSoldat soldat = new FourmiSoldat(w, this.team, this.posX, this.posY, map, chef, 0);
				tabEggs.add(soldat);
			}
		}
	}

	//hatch eggs method
	//parse the array list of eggs to hatch
	//return to the hatch method of each ant class
	public void hatchEggs() {
		for(Eggable myegg:tabEggs){
			myegg.hatch();
		}
	}

	//hatch of the queen
	//creation of their thread
	//return to their run()
	@Override
	public void hatch() {
		new Thread(this).start();
	}
}