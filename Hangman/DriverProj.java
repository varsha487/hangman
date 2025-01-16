import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*; 
import java.io.*;

public class DriverProj
{
   public static int misses;
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("Hangman");
      frame.setSize(400, 400);
      frame.setLocation(200, 100);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new StartPanel());
      frame.setVisible(true);
   }
   public static class HangmanPanel extends JPanel 
   {
      
      private ButtonPanel buttons;
      private int myMisses;
      private JLabel timer, image;
      private WordPanel word;
      private int mySeconds;
      private int highScore;
   
      public HangmanPanel()
      {
         setLayout(new BorderLayout());
      
         buttons = new ButtonPanel();
         add(buttons, BorderLayout.SOUTH);
      }

      public int getSeconds()
      {
         return mySeconds;
      }

   }
   public static class WordPanel extends JPanel
   {
      private String myWord;
      private JLabel[] wordDisplay;
      private JLabel numLetters;
      private int count;
      public WordPanel()
      {
         count = 0;
         myWord = chooseWord();
         setLayout(new GridLayout(1, getWord().length()));
         
         wordDisplay = new JLabel[getWord().length()];
      
         for(int x = 0; x < getWord().length(); x++)
         {
            wordDisplay[x] = new JLabel("", SwingConstants.CENTER);
            wordDisplay[x].setFont(new Font("Serif", Font.BOLD, 50));
            
            add(wordDisplay[x]);
            
         }
         setBlank();
 
      }
      public void setBlank()
      {
         for(int x = 0; x <= getWord().length() - 1; x++)
         {
            wordDisplay[x].setText("-");
         }
      }
      public String getWord()
      {
         return myWord;
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
         myWord = array[(int)(Math.random() * numitems)];
         return myWord ;
      
      }
      public void showLetters(String letter)
      {
         
         for(int x = 0; x < getWord().length(); x++)
         {
            if(letter.compareTo(getWord().substring(x, x+1)) == 0)
            {
               wordDisplay[x].setText(letter);
               count++;
            }
         }
         if(count == getWord().length())
         {
            JOptionPane.showMessageDialog(null, "You Win!");
            JFrame frame = new JFrame("Hangman");
            frame.setSize(400, 400);
            frame.setLocation(200, 100);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new StartPanel());
            frame.setVisible(true);
            output("output.txt");
            misses = 0;
         }
         
      }
      public void output(String filename)
      {
         Scanner infile = null;
         try{
            infile = new Scanner(new File("output.txt"));
         }
         catch(Exception e)
         {
            JOptionPane.showMessageDialog(null,"The file could not be found.");
         }
         int numitems = infile.nextInt();
         Comparable[] array = new String[numitems];
         for(int k = 0; k < numitems; k++)
         {
         
            array[k] = infile.next();
         
         }
         infile.close();
      
      
         PrintWriter outfile = null;
         try{
            outfile = new PrintWriter(new FileWriter(filename));
         }
         catch(Exception e)
         {
            JOptionPane.showMessageDialog(null,"The file could not be created.");
         } 
         outfile.println((array.length + 1) + "");
         for(int n = 0; n < array.length; n++)
            outfile.println(array[n]);
         outfile.println(myWord);
         outfile.close();
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
         for(int x = 1; x<= 26; x++){
            addButton(subpanel, "" + (char)(x + 96), x);}
                     
         display = new Display();
         add(display, BorderLayout.CENTER);
         word = new WordPanel();
         add(word, BorderLayout.EAST);
         
      }
      private void addButton(JPanel subpanel, String s, int x)
      {
         JButton button = new JButton(s);
         button.addActionListener(new Listener(x, button));
         button.setEnabled(true);
         subpanel.add(button);
      }  
      
   
               
      private class Listener implements ActionListener
      {
         private int myX;
         private JButton myButton;
         public Listener(int x, JButton button)
         {
            myX = x;
            myButton = button;
         }
         public void actionPerformed(ActionEvent e)
         {
            
            if(myX == 1)
            {
               if(word.getWord().contains("a") || word.getWord().contains("A"))
               {
                  word.showLetters("a");
                  
               }
               else
               {
                  misses++;
                  display.addLimb();
               }   
            }
            if(myX == 2)
            {
               if(word.getWord().contains("b") || word.getWord().contains("B"))
               {
                  word.showLetters("b");
               }
               else
               {
                  misses++;
                  display.addLimb();
               }   
            }
            if(myX == 3)
            {
               if(word.getWord().contains("c") || word.getWord().contains("C"))
                  word.showLetters("c");
               else
               {
                  misses++;
                  display.addLimb();
               }   
            }
            if(myX == 4)
            {
               if(word.getWord().contains("d") || word.getWord().contains("D"))
                  word.showLetters("d");
               else
               {
                  misses++;
                  display.addLimb();
               }   
            }
            if(myX == 5)
            {
               if(word.getWord().contains("e") || word.getWord().contains("E"))
                  word.showLetters("e");
               else
               {
                  misses++;
                  display.addLimb();
               }   
            }
            if(myX == 6)
            {
               if(word.getWord().contains("f") || word.getWord().contains("F"))
                  word.showLetters("f");
               else
               {
                  misses++;
                  display.addLimb();
               }   
            }
            if(myX == 7)
            {
               if(word.getWord().contains("g") || word.getWord().contains("G"))
                  word.showLetters("g");
               else
               {
                  misses++;
                  display.addLimb();
               }   
            }
         
            if(myX == 8)
            {
               if(word.getWord().contains("h") || word.getWord().contains("H"))
                  word.showLetters("h");
               else
               {
                  misses++;
                  display.addLimb();
               }   
            } 
            if(myX == 9)
            {
               if(word.getWord().contains("i") || word.getWord().contains("I"))
                  word.showLetters("i");
               else
               {
                  misses++;
                  display.addLimb();
               }   
            }
            if(myX == 10)
            {
               if(word.getWord().contains("j") || word.getWord().contains("J"))
                  word.showLetters("j");
               else
               {
                  misses++;
                  display.addLimb();
               }   
            }
            if(myX == 11)
            {
               if(word.getWord().contains("k") || word.getWord().contains("K"))
                  word.showLetters("k");
               else
               {
                  misses++;
                  display.addLimb();
               }   
            }
            if(myX == 12)
            {
               if(word.getWord().contains("l") || word.getWord().contains("L"))
                  word.showLetters("l");
               else
               {
                  misses++;
                  display.addLimb();
               }   
            }
            if(myX == 13)
            {
               if(word.getWord().contains("m") || word.getWord().contains("M"))
                  word.showLetters("m");
               else
               {
                  misses++;
                  display.addLimb();
               }   
            }
            if(myX == 14)
            {
               if(word.getWord().contains("n") || word.getWord().contains("N"))
                  word.showLetters("n");
               else
               {
                  misses++;
                  display.addLimb();
               }   
            }
            if(myX == 15)
            {
               if(word.getWord().contains("o") || word.getWord().contains("O"))
                  word.showLetters("o");
               else
               {
                  misses++;
                  display.addLimb();
               }   
            }
            if(myX == 16)
            {
               if(word.getWord().contains("p") || word.getWord().contains("P"))
                  word.showLetters("p");
               else
               {
                  misses++;
                  display.addLimb();
               }   
            }
            if(myX == 26)
            {
               if(word.getWord().contains("z") || word.getWord().contains("Z"))
                  word.showLetters("z");
                  
                  
               else
               {
                  misses++;
                  display.addLimb();
               }   
            }
            if(myX == 25)
            {
               if(word.getWord().contains("y") || word.getWord().contains("Y"))
                  word.showLetters("y");
                  
                  
               else
               {
                  misses++;
                  display.addLimb();
               }   
            }
            if(myX == 24)
            {
               if(word.getWord().contains("x") || word.getWord().contains("X"))
                  word.showLetters("x");
                  
                  
               else
               {
                  misses++;
                  display.addLimb();
               }   
            }
            if(myX == 23)
            {
               if(word.getWord().contains("w") || word.getWord().contains("W"))
                  word.showLetters("w");
                  
                  
               else
               {
                  misses++;
                  display.addLimb();
               }   
            }
            if(myX == 22)
            {
               if(word.getWord().contains("v") || word.getWord().contains("V"))
                  word.showLetters("v");
                  
                  
               else
               {
                  misses++;
                  display.addLimb();
               }   
            }
            if(myX == 21)
            {
               if(word.getWord().contains("u") || word.getWord().contains("U"))
                  word.showLetters("u");
               else
               {
                  misses++;
                  display.addLimb();
               }   
            }
            if(myX == 20)
            {
               if(word.getWord().contains("t") || word.getWord().contains("T"))
                  word.showLetters("t");
                  
                  
               else
               {
                  misses++;
                  display.addLimb();
               }   
            }
            if(myX == 19)
            {
               if(word.getWord().contains("s") || word.getWord().contains("S"))
                  word.showLetters("s");
                  
                  
               else
               {
                  misses++;
                  display.addLimb();
               }   
            }
            if(myX == 17)
            {
               if(word.getWord().contains("q") || word.getWord().contains("Q"))
                  word.showLetters("q");
                  
                  
               else
               {
                  misses++;
                  display.addLimb();
               }   
            }
            if(myX == 18)
            {
               if(word.getWord().contains("r") || word.getWord().contains("R"))
                  word.showLetters("r");
                  
                  
               else
               {
                  misses++;
                  display.addLimb();
               }   
            }
            
            myButton.setEnabled(false);            
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
            JOptionPane.showMessageDialog(null, "You Lose");
            JFrame frame = new JFrame("Hangman");
            frame.setSize(400, 400);
            frame.setLocation(200, 100);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new StartPanel());
            frame.setVisible(true);
            
            misses = 0;
         
         }
            
      }
   }
   public static class StartPanel extends JPanel 
   {
   //data fields
      private JLabel label;
      private JButton startButton; 
      private ImageIcon image;
      private static int choice, level;

   
      public StartPanel()
      {
         setLayout(new BorderLayout());
         Font f = new Font("Serif", Font.BOLD, 30);
      
         label = new JLabel("Hangman! " );
         label.setFont(f);
         label.setForeground(Color.red);
         label.setHorizontalAlignment(SwingConstants.CENTER);
         add(label, BorderLayout.NORTH);
         
           
            
         startButton = new JButton("Play");
         startButton.addActionListener(new Listener());
         add(startButton, BorderLayout.SOUTH);
      }
      private class Listener implements ActionListener //ALSO demonstrates implementing and interface in addition to compareTo from the String class
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
            
               
            }
            else   
            {        
               JFrame frame = new JFrame("Hangman");
               frame.setSize(400, 400);
               frame.setLocation(200, 100);
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setContentPane(new HangmanPanel());
               frame.setVisible(true);
            
               
            }
            
         }
      }
   }
   
      
}

