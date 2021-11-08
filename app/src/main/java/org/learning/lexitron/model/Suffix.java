package org.learning.lexitron.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Suffix {

    @SerializedName("suffix")
    @Expose
    private List<String> suffix;

    public List<String> getSuffix() {
        return suffix;
    }

    public void setSuffix(List<String> suffix) {
        this.suffix = suffix;
    }
}
