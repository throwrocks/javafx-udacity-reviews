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
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import javafx.scene.control.Alert;

/**
 *
 * @author josel
 */
public class Utilities {

    /**
     * getStringAsDate
     *
     * @param dateString a string in date format
     * @param format the resulting date format
     * @param timezone the time zone if null use UTC
     * @return a new date in the specified format
     */
    public static Date getStringAsDate(String dateString, String format, String timezone) {
        if (dateString.equals("")) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.getDefault());
        if (timezone == null) {
            formatter.setTimeZone(TimeZone.getDefault());
        } else {
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        }
        Date date = new Date();
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    
      public static void alert(String type, String title, String header, String content) {
        Alert.AlertType alertType;
        switch (type) {
            case "error":
                alertType = Alert.AlertType.ERROR;
                break;
            default:
                alertType = Alert.AlertType.NONE;
        }
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
}
