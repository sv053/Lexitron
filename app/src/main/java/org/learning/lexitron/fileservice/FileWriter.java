package org.learning.lexitron.fileservice;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;

public class FileWriter {

   private Context context;
   private String FILENAME = "failik.txt";
   private FileCred filename;

    public FileWriter(Context context){
        this.context = context;
        filename = new FileCred("lexit");
    }
    public List<String> WriteFile(String str) throws IOException {

        filename.setFileName(str);

        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(context.openFileOutput(FILENAME, Context.MODE_PRIVATE)));
            bw.write(str);
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Arrays.asList(new String("here am i"));
    }
}
