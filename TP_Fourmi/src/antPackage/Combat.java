package antPackage;

public class Combat implements Combattant{

	Combattant idFourmiVisitor;
	Combattant idFourmiHome;
	
	

	public Combat(){		
	}
	
	public Combat(Combattant idFourmiVisitor, Combattant idFourmiHome){
		this.idFourmiVisitor = idFourmiVisitor;
		this.idFourmiHome = idFourmiHome;
	}
	
	public void letsFightBegin(){
		System.out.println("wait thread for fight between " + this.idFourmiVisitor + " and " + this.idFourmiHome);
		
	}
	
	
	public void fight(){
		System.out.println("fight between " + this.idFourmiVisitor + " and " + this.idFourmiHome);
	}
	
	public void endOfFight(){
		System.out.println("notify thread after fight between " + this.idFourmiVisitor + " and " + this.idFourmiHome);
	}
	
	
	
//*********************************************getter and setter*********************************************************
	public Combattant getIdFourmiVisitor() {
		return idFourmiVisitor;
	}

	public void setIdFourmiVisitor(Combattant idFourmiVisitor) {
		this.idFourmiVisitor = idFourmiVisitor;
	}

	public Combattant getIdFourmiHome() {
		return idFourmiHome;
	}

	public void setIdFourmiHome(Combattant idFourmiHome) {
		this.idFourmiHome = idFourmiHome;
	}
}
