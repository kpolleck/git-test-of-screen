import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;
import javax.swing.*;
 
/**
 * This class demonstrates how to load an Image from an external file
 */

@SuppressWarnings("serial")

public class Screen extends JPanel implements MouseListener {


	public static final int MAX_PLANTS = 10;
	
    BufferedImage img1;
    BufferedImage img2;
    Image icon1;
    Image icon2;
    PlantPoint points[] = new PlantPoint[MAX_PLANTS];
    int plantCount = 0;
    
	public class PlantPoint {
		int plantX;
		int plantY;
		
		public PlantPoint(int x, int y) {
			plantX = x;
			plantY = y;
		}
	}
    
    public Screen() {
        try {
        	//img1 = ImageIO.read(getClass().getResource("burn1.png"));
        	//img2 = ImageIO.read(getClass().getResource("burn2.png"));
        	icon1 = new ImageIcon(getClass().getResource("burn1.png")).getImage();
        	icon2 = new ImageIcon(getClass().getResource("movingflower.gif")).getImage();
        } catch (Exception e) {
        	System.out.println(e);
        }
        
        points[0] = new PlantPoint(1,1);
        points[1] = new PlantPoint(3,4);
        points[2] = new PlantPoint(5,4);
        points[3] = new PlantPoint(7,3);
        points[4] = new PlantPoint(9,2);
        points[5] = new PlantPoint(9,2);
        points[6] = new PlantPoint(9,2);
        points[7] = new PlantPoint(9,2);
        points[8] = new PlantPoint(9,2);
        points[9] = new PlantPoint(9,2);

        /***
        for (int i=1; i<=points.length; i++) {
        	try {
        		System.out.println(i + "-->" + points[i].plantX + "," + points[i].plantY);
        	} catch (Exception e) {
        		System.out.println(i + "--> point not defined");
        	}
        }
        */
    }
 
    @Override
    protected void paintComponent(Graphics g) {
    	System.out.println("Running paintComponent");
    	super.paintComponent(g);
    	int counterX=0;
    	int counterY=0;
    	int px=0;
    	int py=0;
    	while(counterX<40){
    		counterX++;
    		counterY=0;
    		while(counterY<10){
        		//g.drawImage(selectImage(counterX,counterY), px, py, null);
    			g.drawImage(selectImage(counterX,counterY), px, py, this);
        		counterY++;
        		py=py+16;
        		//System.out.println(counter1);
        	}
    		px=px+16;
    		py=0;
    		//System.out.println(pxw);
    	}
    }
 
    public Dimension getPreferredSize() {
        if (img1 == null) {
             return new Dimension(100,100);
        } else {
           return new Dimension(img1.getWidth(null), img1.getHeight(null));
       }
    }
    
    public Image selectImage(int x, int y) {
    	
        for (int i=0; i<points.length; i++) {
        	System.out.print(i);
        	printPoint(points[i]);
        	try {
        		if (points[i].plantX==x & points[i].plantY==y) {
        			return icon2;
        		}
        	} catch (Exception e) {}
        }
        return icon1;
    }
    
    public void addPlantPoint(int x, int y) {
    	plantCount++;
    	if (plantCount >= MAX_PLANTS) { plantCount = 1; }
        points[plantCount] = new PlantPoint(x,y);
    }
    
    public static void printPoint(PlantPoint pp) {
    	System.out.println("(" + pp.plantX + "," + pp.plantY + ")");
    }
    
    public static void main(String[] args) {
        JFrame f = new JFrame("Burn Plants");
       
        f.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e) {
                    System.out.println("Closing window.  Goodbye.");
                    System.exit(0);
                }
            });

        JButton button = new JButton("Burn");
        button.setPreferredSize(new Dimension(200,40));
        button.setSize(new Dimension(200,40));
        button.setLocation(100,100);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("You clicked the button");
                System.out.println(e);
            }
        });
        
    	Screen s = new Screen();
    	printPoint(s.points[1]);
    	s.addMouseListener(s);
        f.add(s);
        
        GridLayout myLayout = new GridLayout(2,1);
        f.setLayout(myLayout);
        //f.setLayout(null);
        f.getContentPane().add(button);

        f.pack();
        f.setSize(640, 640);
        f.setResizable(false);
        f.setVisible(true);
        
        s.repaint();
    }

	@Override
	public void mouseEntered(MouseEvent e) {		
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
        //System.out.println(e);
        int screenX = e.getX();
        int screenY = e.getY();
        
        System.out.println(screenX/16 + "," + screenY/16);
        addPlantPoint(screenX/16+1,screenY/16);
        
        e.getComponent().repaint();
        
        printPoint(points[1]);
	}
}
