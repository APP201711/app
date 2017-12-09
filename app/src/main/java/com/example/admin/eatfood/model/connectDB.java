package com.example.admin.eatfood.model;

import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class connectDB {
/*

User a = new User("testadmin","abc");  < -- Login
Log.e("result", String.valueOf(a.LoginStatus));

if(a.LoginStatus){                      < -- check Login
    a.address ="87878787";              < -- update
    a.update();
 }

 */

    public static String db(String data) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());

        String sURL = "http://eatfoot.tk/db.php";
        String cookie =null;
        String referer =null;
        String charset = "utf-8";
        String result = "";
        java.io.BufferedWriter wr = null;
        try {

            URL url = new URL(sURL);
            HttpURLConnection URLConn = (HttpURLConnection) url
                    .openConnection();

            URLConn.setDoOutput(true);
            URLConn.setDoInput(true);
            ((HttpURLConnection) URLConn).setRequestMethod("POST");
            URLConn.setUseCaches(false);
            URLConn.setAllowUserInteraction(true);
            HttpURLConnection.setFollowRedirects(true);
            URLConn.setInstanceFollowRedirects(true);

            URLConn
                    .setRequestProperty(
                            "User-agent",
                            "Mozilla/5.0 (Windows; U; Windows NT 6.0; zh-TW; rv:1.9.1.2) "
                                    + "Gecko/20090729 Firefox/3.5.2 GTB5 (.NET CLR 3.5.30729)");
            URLConn
                    .setRequestProperty("Accept",
                            "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            URLConn.setRequestProperty("Accept-Language",
                    "zh-tw,en-us;q=0.7,en;q=0.3");
            URLConn.setRequestProperty("Accept-Charse",
                    "Big5,utf-8;q=0.7,*;q=0.7");
            if (cookie != null)
                URLConn.setRequestProperty("Cookie", cookie);
            if (referer != null)
                URLConn.setRequestProperty("Referer", referer);

            URLConn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            URLConn.setRequestProperty("Content-Length", String.valueOf(data
                    .getBytes().length));

            java.io.DataOutputStream dos = new java.io.DataOutputStream(URLConn
                    .getOutputStream());
            dos.writeBytes(data);

            java.io.BufferedReader rd = new java.io.BufferedReader(
                    new java.io.InputStreamReader(URLConn.getInputStream(),
                            charset));
            String line;
            StringBuffer stringBuffer = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            result = stringBuffer.toString();
            rd.close();
        } catch (java.io.IOException e) {
//            doSuccess = false;
//            logger.info(e);

        } finally {
            if (wr != null) {
                try {
                    wr.close();
                } catch (java.io.IOException ex) {
//                    logger.info(ex);
                }
                wr = null;
            }
        }

        return result;
    }

}