/* 
Michael Olvera
SP21-CPSC-24500-001
Sprint 4
Mosaic
Draws a 12 x 12 grid with random letters and colors in each box. Each box also has either a square or circle inside. 
*/

//Importing Packages
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import java.util.ArrayList;
import java.util.Random;


class AlphabetTile extends JPanel {
    private int red, green, blue;
    private String letter;

    //Constructor
    AlphabetTile() {
        super();
        SetRandomValue();
    }

    //Gets random colors and letters
    final public void SetRandomValue() {
        red = GetNumberBetween(0,255);
        green = GetNumberBetween(0,255);
        blue = GetNumberBetween(0,255);
        
        letter = "A";
        Random r = new Random();
        char c = (char) (r.nextInt(26) + 'A');
        //Converts Char to String
        letter = String.valueOf(c);
    }

        
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        g.setColor(new Color(red,green,blue));

        
        //Random number is generated to choose circle or square
        Random r = new Random();
        int upperbound = 2;
        int shapeChooser = r.nextInt(upperbound);
        
        
        //Random shape is chosen
        if (shapeChooser == 0) {
            g.fillRect(0, 0, panelWidth, panelHeight);
        } else {
            g.fillOval(0, 0, panelWidth, panelHeight);
        }
        
        //Contrasting color is set to draw text
        g.setColor(new Color(GetContrastingColor(red),GetContrastingColor(green),GetContrastingColor(blue)));

        final int fontSize=25;
        g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        int stringX = (panelWidth/2)-10;
        int stringY = (panelHeight/2)+10;
        g.drawString(letter,stringX,stringY);
    }

    private static int GetContrastingColor(int colorIn) {
        return ((colorIn+128)%256);
    }
    
    private static int GetNumberBetween(int min, int max) {
        Random myRandom = new Random();
        return min + myRandom.nextInt(max-min+1);
    }
}

/* class ShapeChooser {
    static Random r = new Random();
    static int upperbound = 2;
    public static int shapeChoice = r.nextInt(upperbound);{
        //return shapeChoice;
    }
} */

class MosaicFrame extends JFrame implements ActionListener{
private ArrayList<AlphabetTile> tileList;

    public MosaicFrame() {
        //Setting bounds of window
        setBounds(200,200,800,820);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //Creating JPanel for button
        JPanel buttonPanel = new JPanel();
        //Adding panel to randomize at bottom of screen
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        //Buttom is created
        JButton randomize = new JButton("Randomize");
        buttonPanel.add(randomize);
        randomize.addActionListener(this);

        //Main Grid is created
        JPanel alphabetPanel = new JPanel();
        contentPane.add(alphabetPanel, BorderLayout.CENTER);
        alphabetPanel.setLayout(new GridLayout(12,12));

        //Each tile is generated and added to a list
        tileList = new ArrayList<AlphabetTile>();
        for(int i=1; i<145; i++) {
            AlphabetTile tile = new AlphabetTile();
            alphabetPanel.add(tile);
            tileList.add(tile);
        }
    }

    public void actionPerformed(ActionEvent e) {
        for (AlphabetTile tile:tileList) {
            tile.SetRandomValue();
        }
        repaint();
    }
}


public class Mosaic {
    public static void main(String[] args) {
        System.out.println("Mosaic Starting...");

        MosaicFrame myMosaicFrame = new MosaicFrame();
        myMosaicFrame.setVisible(true);
    }
}
