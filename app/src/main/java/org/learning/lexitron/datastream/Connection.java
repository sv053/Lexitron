package org.learning.lexitron.datastream;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Connection {

    HttpsURLConnection connection = null;

    public HttpsURLConnection getConnection() {
        return connection;
    }

    public void setConnection(HttpsURLConnection connection) {
        this.connection = connection;
    }

    public Connection(URL url) {

        try {
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}