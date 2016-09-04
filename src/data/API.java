/*
 * The MIT License
 *
 * Copyright 2016 Jose Lopez.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;

/**
 *
 * @author Jose E Lopez
 */
public class API {

    /**
     * getCertifications Get all the authenticated reviewer certifications
     *
     * @param APIKey the API key
     * @return a string of JSON formatted results
     */
    public static APIResponse getCertifications(String APIKey) {
        String APIUrl = "https://review-api.udacity.com/api/v1/me/certifications";
        return httpConnect(APIKey, APIUrl, "GET", "");
    }

    private static APIResponse httpConnect(String apiKey, String apiUrl, String requestMethod, String requestBody) {
        APIResponse apiResponse = new APIResponse();
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
            apiResponse.setResponseCode(urlConnection.getResponseCode());
            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            if (inputStream == null) {
                apiResponse.setResponseCode(urlConnection.getResponseCode());
                return apiResponse;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            if (buffer.length() == 0) {
                apiResponse.setResponseCode(urlConnection.getResponseCode());
                return apiResponse;
            }
            apiResponse.setResponseText(buffer.toString());
        } catch (IOException v) {
            apiResponse.setResponseText(v.toString());
            return apiResponse;

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    apiResponse.setResponseText(e.toString());
                }
            }
        }
        return apiResponse;
    }

}
