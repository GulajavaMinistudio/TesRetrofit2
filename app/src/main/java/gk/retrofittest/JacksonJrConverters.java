package gk.retrofittest;

import com.fasterxml.jackson.jr.ob.JSON;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import retrofit.Converter;

/**
 * Created by Kucing Imut on 8/28/15.
 */
public class JacksonJrConverters implements Converter<Object> {


    private Class clazz;

    public JacksonJrConverters(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public Object fromBody(ResponseBody body) throws IOException {
        return JSON.std.beanFrom(clazz, body.byteStream());
    }

    @Override
    public RequestBody toBody(Object value) {
        return null;
    }







//
//
//    @Override
//    public Object fromBody(ResponseBody body) throws IOException {
//
//        try {
//
//            return JSON.std.beanFrom(clazz, body.byteStream());
//
//        } catch (Exception ex) {
//
//            ex.printStackTrace();
//            throw new IOException(ex.getMessage());
//        }
//    }
//
//    @Override
//    public RequestBody toBody(Object value) {
//        return null;
//    }

//    @Override
//    public Object fromBody(TypedInput body, Type type) throws ConversionException {
//
//        String text = "";
//        try {
//
////            text = fromStream(body.in());
//
//            return JSON.std.beanFrom(clazz, body.in());
//
//        } catch (Exception ex) {/*NOP*/
//            ex.printStackTrace();
//            text = "";
//            throw new ConversionException(ex.getMessage());
//        }
//    }
//
//    @Override
//    public TypedOutput toBody(Object object) {
//        return null;
//    }


//    public static String fromStream(InputStream in) throws IOException {
//
//        BufferedSource sourceStr = Okio.buffer(Okio.source(in));
//
//        String line1 = "";
//        StringBuilder out1 = new StringBuilder();
//        String newLine1 = System.getProperty("line.separator");
//
//
//        while ((line1 = sourceStr.readUtf8Line()) != null) {
//            out1.append(line1);
//            out1.append(newLine1);
//        }

//
//
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(in,"utf-8"),8);
//        StringBuilder out = new StringBuilder();
//        String newLine = System.getProperty("line.separator");
//        String line;
//
//
//        while ((line = reader.readLine()) != null) {
//            out.append(line);
//            out.append(newLine);
//        }


//        return out1.toString();
//    }


}
