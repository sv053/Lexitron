package org.learning.lexitron.parser.interfaces.dictFactory;

/** defines type of word on the basis of morpheme */

public interface WordQualifier {

    public boolean CompareMorpheme(String word, String morpheme);
    public boolean SearchMorpheme(String word, String morpheme);
    public String QualifyWord(String word);


}
