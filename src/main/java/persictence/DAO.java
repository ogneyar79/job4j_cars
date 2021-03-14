package persictence;


import java.util.Collection;

public interface DAO <T> extends AutoCloseable {

    public T add(T model) ;
    public T getById(int id);
    public void update(T model, int id);
    public void delete(int id);
    public Collection<T> allCandidates();

}
