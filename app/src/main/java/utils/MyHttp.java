package utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 作者：李飞 on 2017/4/7 20:24
 * 类的用途：
 */

public class MyHttp {


          private String urlString;

          public MyHttp( String url){
              this.urlString=url;
          }

          public String getHttpContunt(){
              String s ="";

              try {
                  URL url = new URL(urlString);

                  URLConnection urlConnection = url.openConnection();

                  HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

                  httpURLConnection.setRequestMethod("GET");
                  httpURLConnection.setConnectTimeout(5000);
                  httpURLConnection.setUseCaches(true);
                  httpURLConnection.setReadTimeout(5000);

                  if (httpURLConnection.getResponseCode()==200){
                      InputStream inputStream = httpURLConnection.getInputStream();
                      ByteArrayOutputStream bos = new ByteArrayOutputStream();
                      byte[] bb = new byte[1024];
                      int len =0;
                      while ((len = inputStream.read(bb))!=-1){
                          bos.write(bb,0,len);
                      }
                      s = bos .toString();
                  }

              } catch (MalformedURLException e) {
                  e.printStackTrace();
              } catch (IOException e) {
                  e.printStackTrace();
              }


              return s;
          }
}
