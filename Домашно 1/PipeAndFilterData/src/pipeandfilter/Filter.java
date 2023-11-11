package pipeandfilter;

public interface Filter <T>{

    T execute (T input);
}
