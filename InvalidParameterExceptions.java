package exceptions;

public class InvalidParameterExceptions extends RuntimeException{
    public InvalidParameterExceptions (String message){
        super(message);//вызываетсч конструктор родительского класса
    }    
}

