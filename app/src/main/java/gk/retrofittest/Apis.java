package gk.retrofittest;

/**
 * Created by Kucing Imut on 3/6/15.
 */

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.Callback;


public interface Apis {



    /** TES AMBIL DATA **/

    //http://www.codepolitan.com/api/posts/latest/post/1
    @GET("/api/posts/latest/post/{page}")
    Call<String> getPostBaruLine(@Path("page") String halaman);



    //http://www.codepolitan.com/api/posts/latest/post/1
    @GET("/api/posts/latest/post/{page}")
    Call<ArrayBerita> getPostBaruJakson(@Path("page") String halaman);



































}
