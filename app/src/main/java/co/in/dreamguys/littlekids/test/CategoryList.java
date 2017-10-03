package co.in.dreamguys.littlekids.test;

import java.util.List;

/**
 * Created by user5 on 26-09-2017.
 */

public class CategoryList {

    private String AlphabetLetter;
    private List<AlphabetList> alphabetsLists = null;

    public String getAlphabetLetter() {
        return AlphabetLetter;
    }

    public void setAlphabetLetter(String alphabetLetter) {
        AlphabetLetter = alphabetLetter;
    }

    public List<AlphabetList> getAlphabetsLists() {
        return alphabetsLists;
    }

    public void setAlphabetsLists(List<AlphabetList> alphabetsLists) {
        this.alphabetsLists = alphabetsLists;
    }
}
