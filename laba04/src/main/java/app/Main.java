package app;
import java.util.Scanner;

import javax.sound.SoundClip;

import org.w3c.dom.Text;

import geometry2d.Circle;
import geometry2d.Figure;
import geometry2d.Rectangle;
import geometry3d.Cylinder;
import storage.FigureStorage;
import storage.TextFigureStorage;
import exceptions.*;
import java.util.List;
import java.io.IOException;
import java.nio.file.Path;
import storage.Seriazable;



public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Input path to text file: ");
        Path inputTextPath = Path.of(scanner.nextLine());
        
        System.out.println("Input path to binaru file: ");
        Path outputBynaryPath = Path.of(scanner.nextLine());
        TextFigureStorage textStorage = new TextFigureStorage();
        List<Figure> figures;

        try{
           figures = textStorage.read(inputTextPath);

           for (Figure fig : figures){
                System.out.println(fig.toString());
           }
            
        } catch (IOException e){
            System.out.println("Input-output error: " + e.getMessage());
            return;
        }catch (ClassNotFoundException e){
            System.out.println("Incorrect class: " + e.getMessage());
            return;
        }

        if (figures.isEmpty()){
            System.out.println("List is empty");
        } else{
            Cylinder cyl = new Cylinder(figures.get((int) (Math.random() * figures.size())), 11);
            System.out.println(cyl);
        }

        FigureStorage binStorage = new Seriazable();
        try {
            binStorage.write(outputBynaryPath, figures);
        } catch(IOException e){
            System.out.println(e.getMessage());
            return;
        }


    scanner.close();
    }
}