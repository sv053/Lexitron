package org.learning.lexitron.parser.interfaces;

import java.util.List;
import java.util.Map;

/** forms dict from input text */

public interface DictBuilder {

    public List<String> SplitText(String text);
    public List<String> CleanText(String text, List<String> removeWords);
    public Map<String,String> FormMap();
}
