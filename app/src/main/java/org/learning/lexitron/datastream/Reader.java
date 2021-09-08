package org.learning.lexitron.datastream;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public interface Reader {

    public String Read(String path, String query) throws IOException;
}
