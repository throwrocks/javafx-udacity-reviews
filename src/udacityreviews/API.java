/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udacityreviews;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;

/**
 *
 * @author josel
 */
public class API {

    /**
     * getCertifications Get all the authenticated reviewer certifications
     *
     * @param APIKey the API key
     * @return a string of JSON formatted results
     */
    public static String getCertifications(String APIKey) {
        String APIUrl = "https://review-api.udacity.com/api/v1/me/certifications";
        return httpConnect(APIKey, APIUrl, "GET", "");
    }

    private static String httpConnect(String apiKey, String apiUrl, String requestMethod, String requestBody) {
        String results = null;
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            // Build the URL
            URI builtUri = URI.create(apiUrl);
            URL url = new URL(builtUri.toString());
            // Establish the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(requestMethod);
            urlConnection.addRequestProperty("Authorization", apiKey);
            urlConnection.addRequestProperty("Content-Length", "0");
            urlConnection.addRequestProperty("Body", requestBody);
            urlConnection.addRequestProperty("Accept", "application/json");
            urlConnection.connect();
            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            if (inputStream == null) {
                return "error: inputStream == null";
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            if (buffer.length() == 0) {
                results = "error: buffer.length() == 0";
            }
            results = buffer.toString();
        } catch (IOException v) {
            results = "error: IOException " + v;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return results;
    }

}
