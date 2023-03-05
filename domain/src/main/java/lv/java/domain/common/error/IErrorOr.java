package lv.java.domain.common.error;

import java.util.List;

public interface IErrorOr {
    List<Error> errors();

    boolean isError();
}
