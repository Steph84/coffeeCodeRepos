package antPackage;
import java.util.Random;

//class FourmiSoldat inherits from abstract class Fourmi and implements interface Combattant
public class FourmiSoldat extends Fourmi implements Combattant {

	public int nbTileRow = 30;
	public int nbTileCol = 60;
	FourmiChef myChief;
	protected int qtyFood;
	int health = 100;
	
	
	public FourmiSoldat(){
		this.setQtyFood(0);
	}
	
	public FourmiSoldat(int idFourmi, int team, int posX, int posY, Terrain map, FourmiChef ant, int qtyFood) {
		super(idFourmi, team, posX, posY, map);
		myChief = ant;
		this.setQtyFood(0);
	}

	@Override
	public void run() {
		
		Random randHealth = new Random();
		
		this.getMap().GetCell(posX, posY).setSomeOne(this);
		
		while(health>0){
			int randomHealth = randHealth.nextInt(2); //0 or 1 health point
			
			//before the ant move, it release the tile
			this.getMap().GetCell(posX, posY).setSomeOne(null);
			//method to generate of the move
			moveAnt(posX, posY, health, qtyFood); //movement of the warriors
			
		
			
			//method to detect ant from an other team
//			lookingAround(posX, posY);
			this.getMap().GetCell(posX, posY).setSomeOne(this);
			
//			System.out.println("warrior # " + this.idFourmi + " is on the tile " + this.posX + " : " + this.posY);

			prospection(posX, posY); //prospection action
			
			upDateTileTrough(posX, posY);
			
			
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			//random decrease of health
			health = health - randomHealth;
			if(health<0)health = 0;
		}
		System.out.println("the warrior #" + this.getIdFourmi() + " of " + this.myChief.myQueen.queenName + " died");
		this.myChief.fourmiMeurt(); //death of the ant, notified to the chief
	}

//*************************************************FourmiSoldat methods************************************************************
	//hatch of the warriors
	@Override
	public void hatch() {
		new Thread(this).start();
	}

//**************************************movement of the warrior ants***********************************************************
	public void moveAnt(int posX, int posY, int health, int qtyFood){
		
		Random randMoveX = new Random();
		Random randMoveY = new Random();
		
		//random of -1, 0 or 1 to move in all direction
		int randomNumX = randMoveX.nextInt(3) - 1;
		int randomNumY = randMoveY.nextInt(3) - 1;
		
//*********************************************************back home*****************************************************
		if (this.qtyFood > 4){
			if(this.posX != myChief.posX || this.posY != myChief.posY){
//				while(this.posX != myChief.posX || this.posY != myChief.posY){
				if(this.posX > myChief.posX){
					this.posX = this.posX - 1;
				}
				else if(this.posX < myChief.posX){
					this.posX = this.posX + 1;
				}
				if(this.posY > myChief.posY){
					this.posY = this.posY - 1;
				}
				else if(this.posY < myChief.posY){
					this.posY = this.posY + 1;
				}
			}else if(this.posX == myChief.posX && this.posY == myChief.posY){
				this.myChief.giveFood(qtyFood);
				setQtyFood(0);
			}
		}
//***********************************************************random move***************************************************************
		else{
			boundary(posX, posY);
			//movement along X and Y
			if(this.posX<nbTileCol && this.posX>0){
				this.posX = this.posX + randomNumX;
			}
			if(this.posY<nbTileRow && this.posY>0){
				this.posY = this.posY + randomNumY;
			}
		}
	}

//prospection of the map to obtain food
		public void prospection(int posX, int posY){
			
			switch(map.GetCell(posX, posY).getWhatsDat()){
			case NOTHING_HERE:
				break;
			case FOOD:
				this.qtyFood = this.qtyFood + 1;
				map.GetCell(posX, posY).setWhatsDat(TileType.NOTHING_HERE);
				break;
			}
		}
	
//management of the boundary case
		public void boundary(int posX, int posY){
			//manage X boundary
			switch(posX){
			case 0:
				this.posX = this.posX + 1;
				break;
			case 59:
				this.posX = this.posX - 1;
				break;
			}

			//manage Y boundary
			switch(posY){
			case 0:
				this.posY = this.posY + 1;
				break;
			case 29:
				this.posY = this.posY - 1;
				break;
			}
		}
		
//detection of ant from an other team
		public void lookingAround(int posX, int posY){
			
//			System.out.println("mes prints !");
//			System.out.println(map.GetCell(posX, posY).getSomeOne());
//			System.out.println(this.getTeam());
//			System.out.println(((FourmiSoldat)map.GetCell(posX, posY).getSomeOne()).getTeam());
			
			//if there is nobody, the ant enter the tile
			if (map.GetCell(posX, posY).getSomeOne() == null){// || this.getTeam() == ((FourmiSoldat)map.GetCell(posX, posY).getSomeOne()).getTeam()){
				this.getMap().GetCell(posX, posY).setSomeOne(this);
				
//			if there is somebody AND it's someone from another team, fight
			}else{ //if (map.GetCell(posX, posY).getSomeOne() != null){ // && this.getTeam() != ((FourmiSoldat)map.GetCell(posX, posY).getSomeOne()).getTeam()){
				System.out.println("FIIIIIIIIIIIIIIIIIIIGHT!");
//				try {
//					((FourmiSoldat)map.GetCell(posX, posY).getSomeOne()).wait();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				Combattant visitorAnt = this;
				Combattant homeAnt = map.GetCell(posX, posY).getSomeOne();
				Combat fighting = new Combat(visitorAnt, homeAnt);
				//bloquer l'ennemie
				//creer des objets combattant
//				System.out.println("FIIIIIIIIIIIIIIIIIIIGHT! on the tile : " + map.GetCell(posX, posY).getCellX() + " : " + map.GetCell(posX, posY).getCellY());
//				System.out.println("we know that the fight between " + this.getIdFourmi() + " of " + this.getTeam() + " and " +  map.GetCell(posX, posY).getSomeOne().getIdFourmi() + " of " + map.GetCell(posX, posY).getSomeOne().getTeam());
//				Combat fighting = new Combat(this.getIdFourmi(), map.GetCell(posX, posY).getSomeOne().getIdFourmi());
//				fighting.letsFightBegin();
//				fighting.fight();
//				fighting.endOfFight();
//				this.getMap().GetCell(posX, posY).setSomeOne(this);
			}
		}
		
		public void upDateTileTrough(int posX, int posY){
			int tmp = 0;
			tmp = map.GetCell(posX, posY).getNbFighterThrough() + 1;
			map.GetCell(posX, posY).setNbFighterThrough(tmp);
		}
		
		
		
//*********************************************getter and setter*********************************************************
		public int getQtyFood() {
			return qtyFood;
		}

		public void setQtyFood(int qtyFood) {
			this.qtyFood = qtyFood;
		}
		
//*********************************************unimplement methods*********************************************************
		@Override
		public void letsFightBegin() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void fight() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void endOfFight() {
			// TODO Auto-generated method stub
			
		}
}
