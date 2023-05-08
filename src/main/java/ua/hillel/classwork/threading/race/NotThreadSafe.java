package ua.hillel.classwork.threading.race;

public class NotThreadSafe {
    StringBuilder builder = new StringBuilder();

    public void add(String text){
        this.builder.append(text);
    }
}
