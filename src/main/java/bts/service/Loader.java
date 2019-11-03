package bts.service;

import java.util.List;

public interface Loader {
    <T> List<T> load(String fileName);
}
