package bts.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class WriterImpl implements Writer {
    @Override
    public <T> void write(List<T> entities, String fileName) {
        try(FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(entities);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
