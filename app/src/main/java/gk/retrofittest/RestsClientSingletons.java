package gk.retrofittest;

import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
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
    private static Apis apisJaksonBerita;
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

//            RestAdapter.Builder builderestadapter = new RestAdapter.Builder();
//            builderestadapter.setEndpoint(MainActivity.ALAMATSERVERCODEPOLIT);
//            builderestadapter.setClient(new OkClient(okhttpklien));
//            builderestadapter.setConverter(new StringConverter());
//
//            RestAdapter restadapters = builderestadapter.build();

            Retrofit retrofits = retrobuilder.build();
            apisString = retrofits.create(Apis.class);

        }


        return apisString;
    }


    //DENGAN CONVERTER JACKSON
//    public static Apis getRestClient_Jackson() {
//
//        if (apisJakson == null) {
//
//            Log.w("API NULL","API NULL, INIT LAGI");
//
//            ObjectMapper objekmapper = new ObjectMapper();
//            objekmapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
//            objekmapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
//
//            RestAdapter.Builder builderestadapter = new RestAdapter.Builder();
//            builderestadapter.setEndpoint(MainActivity.ALAMATSERVERCODEPOLIT);
//            builderestadapter.setClient(new OkClient(okhttpklien));
//            builderestadapter.setConverter(new JacksonConverter(objekmapper));
//
//            RestAdapter restadapters = builderestadapter.build();
//
//            apisJakson = restadapters.create(Apis.class);
//        }
//
//
//
//        return apisJakson;
//
//    }


    //DENGAN CONVERTER JACKSON
    public static Apis getRestClient_JacksonBerita() {

        if (apisJaksonBerita == null) {

            Log.w("API NULL", "API NULL, INIT LAGI");

            Retrofit.Builder retrobuilder = new Retrofit.Builder();
            retrobuilder.baseUrl(MainActivity.ALAMATSERVERCODEPOLIT);
            retrobuilder.client(okhttpklien);
            retrobuilder.addConverter(ArrayBerita.class, new JacksonJrConverters(ArrayBerita.class));

            Retrofit retrofits = retrobuilder.build();
            apisJaksonBerita = retrofits.create(Apis.class);
        }


        return apisJaksonBerita;

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

//            //TANPA OKIO
//            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"), 8);
//            StringBuilder out = new StringBuilder();
//            String newLine = System.getProperty("line.separator");
//            String line;
//
//
//            while ((line = reader.readLine()) != null) {
//                out.append(line);
//                out.append(newLine);
//            }
//            return out.toString();


            //DENGAN OKIO
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

            String text = "";
            try {

                text = fromStream(body.byteStream());

            } catch (Exception ex) {/*NOP*/
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






    public final class StringFactori implements Converter.Factory {

        @Override
        public Converter<?> get(Type type) {
            return null;
        }
    }






}




















