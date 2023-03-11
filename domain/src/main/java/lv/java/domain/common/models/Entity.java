package lv.java.domain.common.models;

public class Entity<T> {
    private final T id;

    public Entity(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }
}
