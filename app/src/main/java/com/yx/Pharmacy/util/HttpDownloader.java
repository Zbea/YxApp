package com.yx.Pharmacy.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Zbea on 2017/7/11. 文件下载保存
 */

public class HttpDownloader
{

    public interface OnResponseListener
    {
        void onResponse(Object o);
    }

    /**
     * 下载并保存文件
     * @param urlStr
     * @param file
     * @param onResponseListener
     */
    public static void doGetDown(final String urlStr,final File file ,final OnResponseListener onResponseListener)
    {
        new Thread()
        {
            public void run()
            {
                try
                {
                    Object o=doGet(urlStr,file);
                    if (onResponseListener != null)
                    {
                        onResponseListener.onResponse(o);
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }

            }

            ;
        }.start();
    }


    private static Object  doGet(String urlStr, File file)
    {
        URL url = null;
        InputStream is = null;
        HttpURLConnection conn = null;
        ByteArrayOutputStream baos = null;
        try
        {
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            if (conn.getResponseCode() == 200)
            {
                is = conn.getInputStream();
                if (is==null||is.toString().length()==91)
                    return null;
                try
                {
                    if (!file.exists())
                        file.createNewFile();
                    //输出流
                    FileOutputStream os = new FileOutputStream(file);
                    byte buffer[] = new byte[1024];
                    int len = 0;
                    while ((len = is.read(buffer)) != -1)
                    {
                        os.write(buffer, 0, len);
                        os.flush();
                    }
                    return os.toString();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            } else
            {
                throw new RuntimeException(" responseCode is not 200 ... ");
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if (is != null)
                    is.close();
            } catch (IOException e)
            {
            }
            try
            {
                if (baos != null)
                    baos.close();
            } catch (IOException e)
            {
            }
            conn.disconnect();
        }
        return null;
    }

}
