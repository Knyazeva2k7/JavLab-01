package storage;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import exceptions.CustomFormatExceptions;
import geometry2d.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;

public class TextFigureStorage implements FigureStorage{
    @Override 
    public List<Figure> read(Path path) throws IOException, ClassNotFoundException, CustomFormatExceptions{
        List<Figure> figures = new ArrayList<>();
        try(BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
            String line;
            while ((line = reader.readLine())!= null){
                String[] parts = line.split(" ");

                if (parts[0].equals("Circle")){
                    double r = Double.parseDouble(parts[1]);
                    Circle cir = new Circle(r);
                    figures.add(cir);
                }
                else if (parts[0].equals("Rectangle")){
                    double a = Double.parseDouble(parts[1]);
                    double b = Double.parseDouble(parts[2]);
                    Rectangle rec = new Rectangle (a, b);
                    figures.add(rec);                    
                }
                else {
                    throw new CustomFormatExceptions("Unknow figure type: " + line);
                }
            }

        }
        return figures;
    }
    

    @Override 
    public void write(Path path, List<Figure> figures) throws  IOException{
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)){
            for (Figure fig : figures) {
                if (fig instanceof Circle) {
                    Circle c = (Circle) fig;
                    writer.write("Circle " + c.getR()); 
                } 
                else if (fig instanceof Rectangle) {
                Rectangle r = (Rectangle) fig;
                writer.write("Rectangle " + r.getA() + " " + r.getB());
                }
                writer.newLine();
            }
    }
}
}
