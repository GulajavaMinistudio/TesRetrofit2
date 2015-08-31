package gk.retrofittest;

import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okio.BufferedSource;
import okio.Okio;
import retrofit.Converter;
import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;




/**
 * Created by Kucing Imut on 3/6/15.
 */
public class RestsClientSingletons {


    private static OkHttpClient okhttpklien;

    private static Apis apisString;
    private static Apis apisJaksonBerita1;


    static {

        if (okhttpklien == null) {

            okhttpklien = new OkHttpClient();
            okhttpklien.setConnectTimeout(60, TimeUnit.SECONDS);
            okhttpklien.setWriteTimeout(60, TimeUnit.SECONDS);
            okhttpklien.setReadTimeout(60, TimeUnit.SECONDS);

        }

    }


    public static Apis getRestClients() {


        if (apisString == null) {

            Log.w("API NULL", "API NULL, INIT LAGI");

            Retrofit.Builder retrobuilder = new Retrofit.Builder();
            retrobuilder.baseUrl(MainActivity.ALAMATSERVERCODEPOLIT);
            retrobuilder.client(okhttpklien);
            retrobuilder.addConverter(String.class, new StringConverter());


            Retrofit retrofits = retrobuilder.build();
            apisString = retrofits.create(Apis.class);

        }


        return apisString;
    }



    //DENGAN CONVERTER JACKSON
    public static Apis getRestClient_JacksonBerita1() {

        if (apisJaksonBerita1 == null) {

            Log.w("API NULL", "API NULL, INIT LAGI");

            Retrofit.Builder retrobuilder = new Retrofit.Builder();
            retrobuilder.baseUrl(MainActivity.ALAMATSERVERCODEPOLIT);
            retrobuilder.client(okhttpklien);

            JacksonConverterFactory jaksonfactory = JacksonConverterFactory.create();
            retrobuilder.addConverterFactory(jaksonfactory);

            Retrofit retrofits = retrobuilder.build();
            apisJaksonBerita1 = retrofits.create(Apis.class);
        }


        return apisJaksonBerita1;

    }












    public static class StringConverter implements Converter<String> {

        public static String fromStream(InputStream in) throws IOException {

            BufferedSource sourceStr = Okio.buffer(Okio.source(in));

            String line1 = "";
            StringBuilder out1 = new StringBuilder();
            String newLine1 = System.getProperty("line.separator");

            while ((line1 = sourceStr.readUtf8Line()) != null) {
                out1.append(line1);
                out1.append(newLine1);
            }

            sourceStr.close();

            return out1.toString();
        }

        @Override
        public String fromBody(ResponseBody body) throws IOException {

            String text;
            try {

                text = fromStream(body.byteStream());

            } catch (Exception ex) {
                ex.printStackTrace();
                text = "";
            }


            return text;
        }

        @Override
        public RequestBody toBody(String value) {
            return null;
        }
    }



}




















