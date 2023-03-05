package lv.java.domain.common.models;

public class AggregateRoot<T> {
    private final T id;

    public AggregateRoot(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }
}
