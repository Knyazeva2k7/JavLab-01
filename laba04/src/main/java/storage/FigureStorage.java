package storage;
import java.util.List;
import java.io.IOException;
import exceptions.CustomFormatExceptions;
import geometry2d.Figure;
import java.nio.file.Path;


public interface FigureStorage {
    public  List<Figure> read(Path path) throws IOException, ClassNotFoundException, CustomFormatExceptions;

    public void write(Path path, List<Figure> figures) throws IOException;
}
