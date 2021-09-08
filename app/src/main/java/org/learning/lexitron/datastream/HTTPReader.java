package org.learning.lexitron.datastream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HTTPReader implements Reader{

    private String path = "https://dle.rae.es/";

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HTTPReader(String path) {
        this.path = path;

    }

    @Override
    public String Read(String path, String query) throws IOException {
        BufferedReader reader=null;
        InputStream stream = null;
        HttpsURLConnection connection = null;
        StringBuilder buf = null;

        try {
            URL url=new URL(path+query+"?m=form");
//            connection =(HttpsURLConnection)url.openConnection();
//            connection.setRequestMethod("GET");
//            connection.setReadTimeout(10000);
            connection = new Connection(url).getConnection();
            connection.connect();
            stream = connection.getInputStream();
            reader= new BufferedReader(new InputStreamReader(stream));
           buf = new StringBuilder();
            String line;
            while ((line=reader.readLine()) != null) {
                buf.append(line).append("\n");
            }
            return(buf.toString());
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return buf.toString();
    }
}
