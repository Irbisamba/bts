package bts.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class LoaderImpl implements Loader{
    @Override
    public <T> List<T> load(String fileName) {
        List<T> list = null;
        try(FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            list = (List<T>)objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
        }
        return list==null ? new ArrayList<>() : list;
    }
}
