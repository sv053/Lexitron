package org.learning.lexitron.fileservice;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    private String FILEPATH;
    private Context context;
    private FileCred filename;


    public FileReader(Context context, String filepath) {
        this.context = context;
        this.FILEPATH = filepath;
        filename = new FileCred(filepath);
    }

    public List<String> ReadFile() throws IOException {

        String str = "";
        try(BufferedReader br = new BufferedReader(new InputStreamReader(context.openFileInput(FILEPATH)))) {
            str = br.readLine();
                Log.d(context.getPackageName(),"The file " + FILEPATH + " has been read!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            str = e.toString();
        } catch (IOException e) {
            e.printStackTrace();
            str = e.toString();
        }

//        String fileName = "stored_image.jpg";
//        String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
//        String pathDir = baseDir + "/Android/data/com.mypackage.myapplication/";
//
//        File f = new File(pathDir + File.separator + fileName);
//
//        if(f.exists()){
//            Log.d("Application", "The file " + f.getName() + " exists!";
//        }else{
//            Log.d("Application", "The file no longer exists!";
//        }

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
