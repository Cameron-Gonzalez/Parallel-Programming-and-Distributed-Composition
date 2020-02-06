//By: Cameron Gonzalez
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.*;


public class ColorSquares extends JFrame {

    //Components needed to display an image with squares at different locations
    int MAX_COL = 1200;
    int MAX_ROW = 700;
    ArrayList<Point> points = new ArrayList<Point>();
    int numPoints;
    BufferedImage image;

    //GUI components needed
    MyJPanel panel = new MyJPanel();
    JTextField txtNumThreads = new JTextField(10);
    JLabel result = new JLabel(" - ");

    public ColorSquares() {
        //Randomly generate 45 points
        Random rand = new Random();
        numPoints = 45;//Set numPoints to 45 for easy testing
        for (int i = 1; i <= numPoints; i++) {
            {
                int x = rand.nextInt(MAX_COL - 50) + 20;
                int y = rand.nextInt(MAX_ROW - 50) + 20;
                points.add(new Point(x, y));
            }
        }
        //Set Up GUI
        setSize(MAX_COL + 100, MAX_ROW + 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Color Squares");
        add(panel);
        JPanel bPanel = new JPanel();
        JButton button = new JButton("click");
        bPanel.add(button);
        bPanel.add(new JLabel("num threads"));
        bPanel.add((txtNumThreads));
        bPanel.add(result);
        add(bPanel, BorderLayout.SOUTH);
        setVisible(true);
        image = new BufferedImage(MAX_COL + 10, MAX_ROW + 10, BufferedImage.TYPE_4BYTE_ABGR);

        //Initialize Number of Threads
        txtNumThreads.setText("1");

        //When button is pressed, start the process
        button.addActionListener(
                event ->
                {


                    //Redraw dividing lines based on numThreads
                    Graphics2D g2 = image.createGraphics();
                    panel.repaint();
                    panel.paint(g2);
                    repaint();

                    //Record Start Time
                    long lStartTime = new Date().getTime();

                    //get the number of threads to create.
                    int numThreads = Integer.parseInt(txtNumThreads.getText());

                    //TODO Modify the code to start the SquareFinder object the requested number of Thread
                    //Divide the image into sections -- calculate  values for top and bottom rows
                    int threadNumber = 0;
                    int top = 0;
                    int bottom = 0;

                    SquareFinder sf = null;
                    //TODO Add Code that will until all threads have joined before calculating time
                    //Make an arrayList to hold the threads
                    ArrayList<Thread> list = new ArrayList<Thread>();
                    for (int i = 0; i < numThreads; i++)
                    {
                        top = (MAX_ROW * threadNumber) / numThreads;
                        bottom = (MAX_ROW * (threadNumber + 1) / numThreads) - 1;
                        sf = new SquareFinder(this.getContentPane(), image, top, bottom);
                        Thread t = new Thread(sf);
                        list.add(t);
                        t.start();
                        threadNumber++;
                    }
                    //test if any are alive; if they are, join them
                    for(int i = 0; i < list.size(); i++)
                    {
                        try
                        {
                            if(list.get(i).isAlive())
                            {
                                list.get(i).join();
                            }
                        }
                        catch(InterruptedException ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                    //Record End Time
                    long lEndTime = new Date().getTime();

                    //Report Elapsed Time
                    long difference = lEndTime - lStartTime;
                    String resultString = "#Threads: " + numThreads + "  ELAPSED TIME: " + difference;
                    System.out.println(resultString);
                    result.setText(resultString);
                }
        );
    }

    public static void main(String[] args) {
        new ColorSquares();
    }

    //---------------------------------------------------------------------
    // MyJPanel  is a helper class that draws squares and  dividing lines
    //---------------------------------------------------------------------
    class MyJPanel extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            //Draw black dividing lines based on numThreads
            g.setColor(Color.black);

            int numThreads = Integer.parseInt(txtNumThreads.getText());
            for (int i = 1; i <= numThreads; i++)
                g.drawLine(0, MAX_ROW * i / numThreads, MAX_COL, MAX_ROW * i / numThreads);

            //draw red squares
            g.setColor(Color.red);
            for (int i = 0; i < numPoints; i++)
                g.fillRect(points.get(i).x, points.get(i).y, 10, 10);
        }
    }


    //-------------------------------------------------------------
    // SquareFinder task
    //-------------------------------------------------------------
    //TODO Modify this class so that it can be a Task that is executed on a Thread
    private class SquareFinder implements Runnable
    {
        Container frame;
        BufferedImage image;
        int top, bottom, left, right;
        Color[] colors = {Color.BLACK, Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA};

        int color;
        public int numFound = 0;

        public SquareFinder(Container frame, BufferedImage image, int top, int bottom)
        {
            super();
            this.frame = frame;
            this.image = image;
            this.top = top;
            this.bottom = bottom;
            this.left = 0;
            this.right = MAX_COL;
            // Choose Color
            try {
                //Choose a color based on the currentThread number -- each process will have a different color
                String[] tokens = Thread.currentThread().getName().split("-");
                this.color = (Integer.parseInt(tokens[tokens.length - 1]) + colors.length) % colors.length;
            } catch (Exception e) {
                e.printStackTrace();
                this.color = 4;
            }
        }

        //TODO this is the function that should be executed when the Thread starts
        public void process() {
            try {
                //find the number of squares
                numFound = getNumSquares(top, bottom, left, right, 10);
                String r = String.format("numSquares = " + numFound);

                //Choose another color and find the squares again
                int oldColor = this.color;
                this.color = (this.color + 1) % colors.length;
                numFound = getNumSquares(top, bottom, left, right, 10);

                //Display the number of squares found
                String out = String.format("numSquares = " + numFound);
                System.out.println(out);
            } catch (Exception e) {
                System.out.println(Thread.currentThread().getName() + " wasn't done!");
                e.printStackTrace();
            } finally {
            }
        }

        //-----------------------------------------------------------------------
        // getNumSquares  - returns the number of squares found in grid
        //----------------------------------------------------------------------
        public int getNumSquares(int top, int bottom, int left, int right, int size) {
            Color[] colors = {Color.BLACK, Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA};
            int count = 0;

            Graphics g = frame.getGraphics();

            for (int row = top; row <= bottom; row++) {
                for (int col = left; col < right; col++) {
                    if (isSquare(row, col, size)) {
                        g.setColor(colors[color]);
                        g.fillRect(col, row, 10, 10);
                        //  System.out.println("painting square: (" + col + ", " + row + ")" + g.getColor());
                        count++;
                    }
                }
            }
            return count;
        }

        public boolean isSquare(int row, int col, int size) {
            int RED = -65536;

            int numColored = 0;
            int count = 0;
            for (int r = row; r < (row + size); r++)
                for (int c = col; c < (col + size); c++) {
                    try {
                        if (RED == image.getRGB(c, r))
                            numColored++;
                        else
                            count++;
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(" MAX Row: " + row + "Col" + col);
                        throw new ArrayIndexOutOfBoundsException(e.getMessage() + " Row: " + r + "  Col:" + c);
                    }
                }
            return (numColored == (size * size));
        }
        public void run()
        {
            process();
        }
    }
}