package types;

import java.util.HashMap;

public class TypeRepository<T> {
    private final HashMap<String, T> repository;

    public TypeRepository() {
        repository = new HashMap<>();
    }

    public void register(String id, T obj) {
        repository.put(id, obj);
    }
    public T obtain(String id) {
        return repository.get(id);
    }
}
