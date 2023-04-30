package lv.java.domain.common.models;

public class AggregateRoot<T> extends Entity<T> {

    public AggregateRoot(T id) {
        super(id);
    }

}
