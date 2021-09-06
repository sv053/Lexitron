package org.learning.lexitron.service;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileReader {

    private String FILEPATH;
    private Context context;


    public FileReader(Context context, String filepath) {
        this.context = context;
        this.FILEPATH = filepath;
    }

    public List<String> ReadFile() throws IOException {

        String str = "";
        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new InputStreamReader(context.openFileInput(FILEPATH)));
            // читаем содержимое
            str = br.readLine();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            str = e.toString();
        } catch (IOException e) {
            e.printStackTrace();
            str = e.toString();
        }
//    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
// //   int n = Integer.parseInt(bufferedReader.readLine().trim());
//
//    List<String> ar = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
//            .map(Integer::parseInt)
//            .collect(toList());
//
//    int result = Result.sockMerchant(n, ar);
//
//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedReader.close();
//        bufferedWriter.close();

        List<String> contentFromFile = new ArrayList<>();
//        try (FileInputStream fs = new FileInputStream (new File(filepath))) {
//            String str;
//            while((str=fs.read())!=""){
//                contentFromFile.add()
//                System.out.print((char)c);
//            }
//        }
//        catch(IOException ex){
//
//            System.out.println(ex.getMessage());
//        }
//        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(FILEPATH))) {
//            contentFromFile.add(reader.readLine());
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
////        }

//        }
        contentFromFile.add(str);
        return contentFromFile;
    }
}
