import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*; 
import java.io.*;
import edu.fcps.Turtle;
import javax.swing.Timer;

public class Testing
{
   public static int misses;
   private static Turtle smidge;

   public static class StartPanel extends JPanel 
   {
   //data fields
      private JLabel label;
      private JButton startButton; 
      private ImageIcon image;
      private static int choice, level;
      private TurtlePanel smudge; 
   
      public StartPanel()
      {
      
      
         setLayout(new BorderLayout());
         Font f = new Font("Serif", Font.BOLD, 30);
      
         label = new JLabel("Hangman! " );
         label.setFont(f);
         label.setForeground(Color.red);
         label.setHorizontalAlignment(SwingConstants.CENTER);
         add(label, BorderLayout.NORTH);
         
         smudge = new TurtlePanel();
         add(smudge, BorderLayout.CENTER);
      
            
         startButton = new JButton("Play");
         startButton.addActionListener(new Listener());
         add(startButton, BorderLayout.SOUTH);
      }
      private class Listener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            choice =  Integer.parseInt(JOptionPane.showInputDialog("1. easy\n2. hard"));
         
            if (choice == 1)
            {
               JFrame frame = new JFrame("Hangman");
               frame.setSize(400, 400);
               frame.setLocation(200, 100);
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setContentPane(new HangmanPanel());
               frame.setVisible(true);
            
               //chooseWord();
            }
            else   
            {        
               JFrame frame = new JFrame("Hangman");
               frame.setSize(400, 400);
               frame.setLocation(200, 100);
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setContentPane(new HangmanPanel());
               frame.setVisible(true);
            
               //chooseWord();
            }
         
         
         }
      }
   }
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("Hangman");
      frame.setSize(400, 400);
      frame.setLocation(200, 100);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new StartPanel());
      frame.setVisible(true);
      Turtle.clear(Color.white);
      smidge = 
         new Turtle(300.0, 500.0, 90.0)
         { 
            public void drawShape() 	
            {} 
         };
      smidge.setSpeed(5);
      smidge.setCrawl(true); 
      tree(smidge, 70.0, 30.0, 4);
   
      
   }


   
   public static class HangmanPanel extends JPanel 
   {
      private String myWord;
      private ButtonPanel buttons;
      
      private JLabel timer, image;
      
      private int mySeconds;
      private int highScore;
      
      public HangmanPanel()
      {
         setLayout(new BorderLayout());
         
         buttons = new ButtonPanel();
         add(buttons, BorderLayout.SOUTH);
         
      }
      public String getWord()
      {
         return myWord;
      }
      public int getSeconds()
      {
         return mySeconds;
      }
      public void setWord(String s)
      {
         myWord = s;
      }
      
      public String chooseWord() 
      {
         Scanner infile = null;
         try{
            infile = new Scanner(new File("words"));
            
         }
         catch(FileNotFoundException e)
         {
            JOptionPane.showMessageDialog(null,"The file could not be found.");
            System.exit(0);
         }
         int numitems = infile.nextInt();
         String[] array = new String[numitems];
         for(int k = 0; k < numitems; k++)
         {
            array[k] = new String(infile.next());
         }
         infile.close();
         return array[(int)(Math.random() * numitems)];
         
      }
   }
   public static class WordPanel extends JPanel
   {
      private String word;
      private JLabel wordDisplay;
      public WordPanel()
      {
         setLayout(new BorderLayout());
         
         
         wordDisplay = new JLabel("");
         wordDisplay.setFont(new Font("Serif", Font.BOLD, 20));
         wordDisplay.setForeground(Color.blue);
         add(wordDisplay);
         wordDisplay.setText("hi");
         
      }
   }
   public static class ButtonPanel extends JPanel
   {
      private Display display;
      private WordPanel word;
      public ButtonPanel()
      {
         setLayout(new BorderLayout());
         
         JPanel subpanel = new JPanel();
         subpanel.setLayout(new GridLayout(4, 7));
         add(subpanel, BorderLayout.SOUTH);
         
         addButton(subpanel, "a", 1);
         addButton(subpanel, "b", 2);
         addButton(subpanel, "c", 3);
         addButton(subpanel, "d", 4);
         addButton(subpanel, "e", 5);
         addButton(subpanel, "f", 6);
         addButton(subpanel, "g", 7);
         addButton(subpanel, "h", 8);
         addButton(subpanel, "i", 9);
         addButton(subpanel, "j", 10);
         addButton(subpanel, "k", 11);
         addButton(subpanel, "l", 12);
         
         display = new Display();
         add(display, BorderLayout.CENTER);
         
         word = new WordPanel();
         add(word, BorderLayout.EAST);
      }
      private void addButton(JPanel subpanel, String s, int x)
      {
         JButton button = new JButton(s);
         button.addActionListener(new Listener(x));
         subpanel.add(button);
         
      }
      private class Listener implements ActionListener
      {
         private int myX;
         public Listener(int x)
         {
            myX = x;
         }
         public void actionPerformed(ActionEvent e)
         {
            if(myX == 3)
               display.addLimb();
            if(myX == 2)
               System.out.println("b");
            
            if(myX == 1)
               System.out.println("a");
            
            
            
         }
      }
   }
   public static class Display extends JPanel
   {
      private JLabel label;
      
      public Display()
      {
         setLayout(new FlowLayout());
         label = new JLabel();
         label.setIcon(new ImageIcon("stick0.png"));
         add(label);
         
      }
      public void addLimb()
      {
         if(misses == 1)
         {
            ImageIcon one = new ImageIcon("stick1.png");
            label.setIcon(one);
         }
         if(misses == 2)
         {
            ImageIcon two = new ImageIcon("stick2.png");
            label.setIcon(two);
         }
         if(misses == 3)
         {
            ImageIcon three = new ImageIcon("stick3.png");
            label.setIcon(three);
         }
         if(misses == 4)
         {
            ImageIcon four = new ImageIcon("stick4.png");
            label.setIcon(four);
         }
         if(misses == 5)
         {
            ImageIcon five = new ImageIcon("stick5.png");
            label.setIcon(five);
         }
         if(misses == 6)
         {
            ImageIcon six = new ImageIcon("stick6.png");
            label.setIcon(six);
         }
         if(misses == 7)
         {
            ImageIcon seven = new ImageIcon("stick7.png");
            label.setIcon(seven);
         }
         if(misses == 8)
         {
         ImageIcon eight = new ImageIcon("stick.png");
         label.setIcon(eight);
         
         }

            
      }
   }

















   public static void tree( Turtle t, double size, double angle, int level)
   {
      if(level == 0)
         return;
         
      else 
      {
         t.forward(size);    
         t.turnLeft(angle);
         tree(t, size -10, angle, level-1); 
         t.turnRight(angle*2); 
         tree(t, size -10, angle, level-1);
         t.turnLeft(angle);
         t.back(size);
      }
   }
   public static class TurtlePanel extends JPanel
   {
      public TurtlePanel()
      {
         Timer t = new Timer(10, new Listener());
         t.start();
      }
      public void paintComponent(Graphics g)
      {
         g.drawImage(Turtle.getImage(), 0, 0, getWidth(), getHeight(), null);
      }
      private class Listener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            repaint();
         }
      }
   }
}
