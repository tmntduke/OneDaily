package tmnt.example.onedaily.Rx;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by tmnt on 2017/4/23.
 */

public interface Operation<T> {
    T operation() throws IOException;
}
