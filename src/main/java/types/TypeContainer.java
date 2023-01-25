package types;

public interface TypeContainer<T> {
    TypeRepository<T> getRepository();
    TypeNodeFactory<T> getFactory(String name);
}
