package antPackage;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main extends Applet implements Runnable {
	public int nbTileRow = 30;
	public int nbTileCol = 60;
	private Thread thread; // thread de controle
	public Terrain map = new Terrain("map01", nbTileRow, nbTileCol);
	FourmiReine redQueenAnt = new FourmiReine(99, 42, 0, 0, map, "RedQueen");
	FourmiReine greenQueenAnt = new FourmiReine(98, 24, nbTileCol-1 , nbTileRow-1, map,
			"GreenQueen");
	public Image imageTerrain = null;
	public Image imageFood = null;
	public Image imageRedAnt = null;
	public Image imageGreenAnt = null;

	public void start() {
		// ncessaire pour arrer l'animation
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	/* ************************************************ */
	/*
	 * Tout ce qui est nécessaire au double buffering pris sur :
	 * http://www.developer.com/repository/softwaredev/content/article/2000/06/
	 * 20/SDtravisdblbuf/DoubleBufferApplet.java
	 */
	/* ************************************************ */
	private int width = -1;
	private int height = -1;
	// The offscreen image
	private Image offscreen;

	// switch: are we double buffering or not?
	private boolean dbon = true;

	// Use this to turn double buffering on and off
	protected void setDoubleBuffering(boolean dbon) {
		this.dbon = dbon;
		if (!dbon) {
			offscreen = null;
		}
	}

	// Depending on the value of our switch, we either call our
	// special code, or just call the default code
	public void update(Graphics g) {
		if (dbon) {
			updateDoubleBufffered(g);
		} else {
			super.update(g);
		}
	}

	// Do the drawing to an offscreen buffer -- maybe
	private void updateDoubleBufffered(Graphics g) {

		// Let's make sure we have an offscreen buffer, and that
		// it's the right size. If the applet has been resized,
		// our buffer will be the wrong size and we need to make
		// a new one
		Dimension d = getSize();
		if (offscreen == null || width != d.width || height != d.height
				|| offscreen == null) {
			width = d.width;
			height = d.height;
			if (width > 0 || height > 0) {
				offscreen = createImage(width, height);
			} else
				offscreen = null;
		}

		// If we still don't have one, give up
		if (offscreen == null)
			return;

		// Get the off-screen graphics object
		Graphics gg = offscreen.getGraphics();

		// Clear the off-screen graphics object
		gg.setColor(getBackground());
		gg.fillRect(0, 0, width, height);
		gg.setColor(getForeground());

		// Draw to the off-screen graphics object
		paint(gg);

		// We don't need this Graphics object anymore
		gg.dispose();

		// Finally, we transfer the newly-drawn stuff right to the
		// screen
		g.drawImage(offscreen, 0, 0, null);
	}
	public void run() {
		// TODO Auto-generated method stub

		// plein de chose..
		resize(1200,600);
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			repaint();
		}
	}

    public void init() {
   	  	
   	  //hatch of the queens
   	  redQueenAnt.hatch();
   	  greenQueenAnt.hatch();
   	  
   	  redQueenAnt.layEggs(1, 20, 42, 0, 0);
   	  greenQueenAnt.layEggs(1, 20, 24, nbTileCol-1, nbTileRow-1);
	
   	  redQueenAnt.hatchEggs();
   	  greenQueenAnt.hatchEggs();
   	  
     }
              
    public void paint(Graphics g) {
    	
    	imageTerrain = getImage(getCodeBase(), "cobblestone1.png");
    	imageFood = getImage(getCodeBase(), "orange.png");
    	imageRedAnt = getImage(getCodeBase(), "red_ant1.png");
    	imageGreenAnt = getImage(getCodeBase(), "green_ant1.png");
    	
    	int sizeTile = 20;
//    	int sizeDot = 10;
    	
    	
//    	g.setColor(Color.darkGray);
//		g.fillRect(0, 0, 1200, 600);
    	
    	for(int i = 0; i < nbTileCol; i++){
			for(int j = 0; j < nbTileRow; j++){
				if(imageTerrain != null)
					g.drawImage(imageTerrain, i* sizeTile, j*sizeTile, this);
					
					
				//show the position of the food
				if(map.GetCell(i, j).getWhatsDat() == TileType.FOOD){
					g.drawImage(imageFood, i* sizeTile, j*sizeTile, this);
//					g.setColor(Color.yellow);
//					myDots(g, i, j, sizeTile, sizeDot);
				}
				
				//show the ants and their team
				if(map.GetCell(i, j).getSomeOne() != null){
				switch(((FourmiSoldat)map.GetCell(i, j).getSomeOne()).getTeam()){
					case 24:
						g.drawImage(imageGreenAnt, i* sizeTile, j*sizeTile, this);
//						g.setColor(Color.green);
//						myDots(g, i, j, sizeTile, sizeDot);
						break;
					case 42:
						g.drawImage(imageRedAnt, i* sizeTile, j*sizeTile, this);
//						g.setColor(Color.red);
//						myDots(g, i, j, sizeTile, sizeDot);
						break;
				}
					
				}
				
				//show the different zones				
//				if(map.GetCell(i, j).getWhereIsIt() == TileZone.RED_HOME){
//					g.setColor(Color.red);
//					mySlash(g, i, j, sizeTile, sizeDot);
//				}
//				if(map.GetCell(i, j).getWhereIsIt() == TileZone.RED_CONFORT){
//					g.setColor(Color.orange);
//					myBackSlash(g, i, j, sizeTile, sizeDot);
//				}
//				if(map.GetCell(i, j).getWhereIsIt() == TileZone.RED_EXPLORE){
//					g.setColor(Color.yellow);
//					mySlash(g, i, j, sizeTile, sizeDot);
//				}
//				if(map.GetCell(i, j).getWhereIsIt() == TileZone.RED_ADV_FIGHT){
//					g.setColor(Color.white);
//					myBackSlash(g, i, j, sizeTile, sizeDot);
//				}
//				if(map.GetCell(i, j).getWhereIsIt() == TileZone.GREEN_HOME){
//					g.setColor(Color.green);
//					mySlash(g, i, j, sizeTile, sizeDot);
//				}
//				if(map.GetCell(i, j).getWhereIsIt() == TileZone.GREEN_CONFORT){
//					g.setColor(Color.cyan);
//					myBackSlash(g, i, j, sizeTile, sizeDot);
//				}
//				if(map.GetCell(i, j).getWhereIsIt() == TileZone.GREEN_EXPLORE){
//					g.setColor(Color.magenta);
//					mySlash(g, i, j, sizeTile, sizeDot);
//				}
//				if(map.GetCell(i, j).getWhereIsIt() == TileZone.GREEN_ADV_FIGHT){
//					g.setColor(Color.white);
//					myBackSlash(g, i, j, sizeTile, sizeDot);
//				}
//				if(map.GetCell(i, j).getWhereIsIt() == TileZone.FIGHT_ZONE){
//					g.setColor(Color.black);
//					mySlash(g, i, j, sizeTile, sizeDot);
//				}
//				if(map.GetCell(i, j).getWhereIsIt() == TileZone.NO_ANTS_LAND){
//					g.setColor(Color.pink);
//					mySlash(g, i, j, sizeTile, sizeDot);
//				}
				
			}
    	}
			
    	
              }
    
    //my graphic methods
    public void myDots(Graphics g, int i, int j, int sizeTile, int sizeDot){
    	g.fillOval(i*sizeTile + sizeTile/2 - sizeDot/2, j*sizeTile + sizeTile/2 - sizeDot/2, sizeDot, sizeDot);
    }

    public void mySlash(Graphics g, int i, int j, int sizeTile, int sizeDot){
    	g.drawLine(i*sizeTile, j*sizeTile + sizeTile, i*sizeTile + sizeTile, j*sizeTile);
    }
    public void myBackSlash(Graphics g, int i, int j, int sizeTile, int sizeDot){
    	g.drawLine(i*sizeTile, j*sizeTile, i*sizeTile + sizeTile, j*sizeTile + sizeTile);
    }

}


//class Main{
//
//  public static void main(String[] args){ 
//	 
//	  //creation of the terrain 20 by 20
//	  Terrain map = new Terrain("map01", 20, 20); //I choose 20 because in the switch (FourmiSoldat) we have to have a constant case
//	  
//	  //creation of several queens
//	  Fourmi redQueenAnt = new FourmiReine(99, 42, 0, 0, map, "RedQueen");
//	  Fourmi greenQueenAnt = new FourmiReine(98, 24, 19, 19, map, "GreenQueen");
//	  
//	  //hatch of the queens
//	  redQueenAnt.hatch();
//	  greenQueenAnt.hatch();
//  }
//}