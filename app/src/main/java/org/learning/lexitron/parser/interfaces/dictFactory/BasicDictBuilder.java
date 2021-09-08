package org.learning.lexitron.parser.interfaces.dictFactory;

import org.learning.lexitron.parser.interfaces.DictBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BasicDictBuilder implements DictBuilder {

    private String txt;

    @Override
    public List<String> SplitText(String text) {
        return Arrays.asList(text.split(".").clone());
    }

    @Override
    public List<String> CleanText(String text, List<String> removeWords) {

        return null;
    }

    @Override
    public Map<String, String> FormMap() {
        return null;
    }
}
