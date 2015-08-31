package gk.retrofittest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;


public class MainActivity extends AppCompatActivity {



    private Button tombolambildatastring;
    private Button tombolambildatajakson;

    public static final String ALAMATSERVERCODEPOLIT = "http://www.codepolitan.com";

    private ProgressDialog progressbar;


    private String respons = "gagal";

    private TextView tekshasil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintest);

        tombolambildatastring = (Button) findViewById(R.id.tombolambildata);
        tombolambildatastring.setOnClickListener(listenertombol);

        tombolambildatajakson = (Button) findViewById(R.id.tombolambildatajakson);
        tombolambildatajakson.setOnClickListener(listenertombol);

        tekshasil = (TextView) findViewById(R.id.tekshasil);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





    View.OnClickListener listenertombol = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {

                case R.id.tombolambildata :

                    munculProgress();

                    ambilDataRetro();

                    break;

                case R.id.tombolambildatajakson :

                    munculProgress();
                    ambilDataRetroJaksonConverter();

                    break;
            }

        }
    };




    private void ambilDataRetro() {

        Callback<String> callbackstr =  new Callback<String>() {
            @Override
            public void onResponse(Response<String> response) {

                progressbar.dismiss();

                respons = response.body();
                tekshasil.setText(respons);
            }

            @Override
            public void onFailure(Throwable t) {

                t.printStackTrace();

                progressbar.dismiss();

                respons = t.getMessage();
                tekshasil.setText(respons);
            }
        };


        Call<String> calls = RestsClientSingletons.getRestClients().getPostBaruLine("1");
        calls.enqueue(callbackstr);

    }



    private void ambilDataRetroJaksonConverter() {


        Callback<ArrayBerita> callbackberita = new Callback<ArrayBerita>() {
            @Override
            public void onResponse(Response<ArrayBerita> response) {

                progressbar.dismiss();

                ArrayBerita arrberita = response.body();

                List<Berita> arrberitalis = arrberita.getResult();

                tekshasil.setText("Ukuran array " + arrberitalis.size());

                Log.w("SUKSES", " SUKSES JACKSON JR");
            }

            @Override
            public void onFailure(Throwable t) {

                t.printStackTrace();

                progressbar.dismiss();

                tekshasil.setText(t.getMessage());
            }
        };


        Call<ArrayBerita> callberita = RestsClientSingletons.getRestClient_JacksonBerita1().getPostBaruJakson("1");
        callberita.enqueue(callbackberita);
    }




    private void munculProgress() {

        progressbar = new ProgressDialog(MainActivity.this);
        progressbar.setMessage("Ambil data...");
        progressbar.setCancelable(false);
        progressbar.show();

    }




















}
