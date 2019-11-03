package bts.service;

import java.util.List;

public interface Writer {
    <T> void write(List<T> entities, String fileName);
}
