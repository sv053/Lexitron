package org.learning.lexitron.fileservice;

import android.content.Context;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;

public class FileWriter {

   private Context context;
   private String FILENAME = "failik.txt";
    public FileWriter(Context context){
        this.context = context;

    }
    public List<String> WriteFile(String str) throws IOException {



//        try (FileWriter writer = new FileWriter("file.txt", false)) {
//
//            String text = "Hello Gold!";
//            writer.write(text);
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }

//        ArrayList<String> res = new ArrayList<>();
//        res.add("");
//        String text = "Hello world!"; // строка для записи
//        //  Toast.makeText(this, "before write", Toast.LENGTH_SHORT).show();
//
//        try (FileOutputStream fos = new FileOutputStream("findfile.txt", MODE_PRIVATE)) {
//            // перевод строки в байты
//            byte[] buffer = text.getBytes();
//
//            fos.write(buffer, 0, buffer.length);
//
//            //    Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
//
//            res.add(0, "файл записан");
//            return res;
//        } catch (IOException ex) {
//
//            System.out.println(ex.getMessage());
//        }
//        return null;
//        //  }
//----------------------------------------------------
    //    String text1 = "this string is from file ";
//        FileOutputStream fos1 = null;
//        try {
//            //  EditText textBox = (EditText) findViewById(R.id.editor);
//
//
//            fos1 = openFileOutput("newFile.txt", MODE_PRIVATE);
//            fos1.write(text1.getBytes());
//            //    Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
//        } catch (IOException ex) {
//
//        } finally {
//            try {
//                if (fos1 != null)
//                    fos1.close();
//            } catch (IOException ex) {
//
//            }
//        }

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
