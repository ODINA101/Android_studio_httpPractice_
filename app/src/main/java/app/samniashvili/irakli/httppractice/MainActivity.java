package app.samniashvili.irakli.httppractice;

import android.annotation.SuppressLint;
import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

private  RecyclerView recyclerView;
private CustomAdapter adapter;
private  List<MyData> data_list;
private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
  recyclerView = findViewById( R.id.recycler_view );
  data_list = new ArrayList<>( );
  load_Data_from_server(0);
  adapter = new CustomAdapter(this,data_list);
  gridLayoutManager = new GridLayoutManager( this,1);
  recyclerView.setLayoutManager( gridLayoutManager );
  recyclerView.setAdapter( adapter );

    }
@SuppressLint("StaticFieldLeak")
private void load_Data_from_server(int id) {
    AsyncTask<Integer,Void,Void> task = new AsyncTask<Integer,Void,Void>() {
        @Override
        protected Void doInBackground(Integer... integers) {
          OkHttpClient client =new OkHttpClient();

            Request request = new Request.Builder()
                    .url("http://samniashvili.online/api.php?q=home")
                    .build();
            try {
                Response response = client.newCall(request).execute();
                JSONArray array = new JSONArray(response.body().string() );
                for(int i=0;i<array.length();i++) {
                    JSONObject object = array.getJSONObject( i );
                    MyData data = new MyData(object.getString( "name" ) );
                    data_list.add(data);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
adapter.notifyDataSetChanged();
        }
    };
    task.execute(id);
}

}
