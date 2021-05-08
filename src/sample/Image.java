package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Image {
    private static int lengthImg = 50;
    private static List<Double> axisX = new ArrayList<Double>();
    private static List<Double> axisY = new ArrayList<Double>();
    private static double PI = Math.PI;
    private static double angleImg = PI/2;
    private static double angle = PI/2;
    private static double transAxisX = 0;
    private static double transAxisY = 0;

    public static void start(){
        String currentImg = "";
        String translationF = "";
        int systemInt = 0;

        try{
            File file = new File("input.txt");
            Scanner scan = new Scanner(file);

            int i = 1;

            StringBuilder strg = new StringBuilder();

            while (scan.hasNextLine()){
                String line = scan.nextLine().trim();
                strg.append(line);
                strg.append(System.lineSeparator());

                if(i == 1){
                    try {
                        systemInt = Integer.parseInt(line.split(",")[0].substring(line.split(",")[0].indexOf("=")+1));
                        int number = Integer.parseInt(line.split(",")[1].substring(line.split(",")[1].indexOf("=")+1));
                        angle = (number*PI)/180;
                    }
                    catch (Exception error){
                        error.printStackTrace();
                    }
                }
                else if( i == 2){
                    currentImg = line.substring(line.indexOf("=")+1);
                }
                else if(i == 3){
                    translationF = line.substring(line.indexOf("=")+1);
                }
                i++;
            }
            //String CompleteText = strg.toString();
        }
        catch (Exception error){
            error.printStackTrace();
        }

        Generator.Start(currentImg, translationF);
        next(systemInt);
    }

    public static void draw(String sentence)
    {
        StringBuffer svg = new StringBuffer();
        svg.append("<?xml version=\"1.0\" standalone=\"no\"?> \n");
        svg.append("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\"> \n");
        transAxisX = 500;
        transAxisY = 500;
        for (int i = 0; i < sentence.length(); i++)
        {
            char c = sentence.charAt(i);
            if (c == 'F')
            {
                double originX = transAxisX;
                double originY = transAxisY;
                double destinationX = (-(Math.cos(angleImg) * lengthImg) + originX);
                double destinationY = (-(Math.sin(angleImg) * lengthImg) + originY);
                svg.append("\t  <line x1 = \""+ originX +"\" y1 = \""+ originY +"\" x2 = \""+ destinationX +"\" y2 = \""+ destinationY +"\" stroke = \"black\" stroke-width = \"1\"/> \n");
                transAxisX = destinationX;
                transAxisY = destinationY;
            }
            else if (c == '+')
            {
                angleImg -= angle;
            }
            else if (c == '-')
            {
                angleImg += angle;
            }
            else if (c == '[')
            {
                push();
            }
            else if (c == ']')
            {
                pop();
            }
        }

        svg.append("</svg> \n");

        FileOutputStream out;
        try
        {
            out = new FileOutputStream("Image.svg");
            out.write(svg.toString().getBytes());
            out.close();
        }
        catch (FileNotFoundException error)
        {
            error.printStackTrace();
        }
        catch (IOException error)
        {
            error.printStackTrace();
        }
    }

    public static void next(int iterations)
    {
        String sentence = Generator.Read(iterations);
        draw(sentence);
    }

    public static void push()
    {
        axisX.add(transAxisX);
        axisY.add(transAxisY);
    }

    public static void pop()
    {
        transAxisX = axisX.remove(axisX.size()-1);
        transAxisY = axisY.remove(axisY.size()-1);
    }
}
