package storage;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Objects;
import geometry2d.*;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;

public class TextFigureStorage implements FigureStorage{

    private Figure parseFigure (String str) throws ClassNotFoundException, IOException {
        String[] words = str.split("\\s+");

        try{
            if (Objects.equals(words[0], "Circle")){
                return new Circle(Double.parseDouble(words[1]));
            }
            if (Objects.equals(words[0], "Rectangle")){
                return new Rectangle(Double.parseDouble(words[1]), Double.parseDouble(words[2]));
            }

        } catch (IndexOutOfBoundsException e){
            throw new IOException("Incorrect save format: " + str);
        }
        throw new ClassNotFoundException("Incorrect class: " + words[0]);
    }

    @Override 
    public List<Figure> read(Path path) throws IOException, ClassNotFoundException{
        List<Figure> figures = new ArrayList<>();

        try (Scanner scanner = new Scanner(path, StandardCharsets.UTF_8)) {
            while(scanner.hasNextLine()){
                String line = scanner.nextLine().strip();//убирает крайние пробелы и табуляции

                if (Objects.equals(line, "")){
                    continue;
                }

                figures.add(parseFigure(scanner.nextLine()));
            }

            IOException err = scanner.ioException();
            if(err!=null){
                throw err;
            }
           

        }
        return figures;
    }
    
    

    @Override 
    public void write(Path path, List<Figure> figures) throws  IOException{

        StringBuilder content = new StringBuilder();

        for (Figure fig : figures){
            content.append(fig.toStringNew()).append("\n");
        }

        Files.writeString(path, content, StandardCharsets.UTF_8);
        
    }
}

