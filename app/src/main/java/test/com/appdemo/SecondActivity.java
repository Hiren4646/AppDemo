package test.com.appdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import test.com.utils.Bean;
import test.com.utils.JSONParser;

public class SecondActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    TextView txtDisplay;
    ArrayList<Bean> bnList;
    double latitude=0;
    double longitude=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        bnList=new ArrayList<>();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spinner=(Spinner)findViewById(R.id.planets_spinner);
        txtDisplay=(TextView)findViewById(R.id.txtDisplay);
        spinner.setOnItemSelectedListener(this);
        new async().execute("http://express-it.optusnet.com.au/sample.json");
        Button btnNext=(Button)findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecondActivity.this,MapActivity.class);
                i.putExtra("latitude",latitude);
                i.putExtra("longitude",longitude);
                startActivity(i);
            }
        });

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        txtDisplay.setText(item);
        latitude=bnList.get(position).getLatitude();
        longitude=bnList.get(position).getLongitude();
        // Showing selected spinner item
       // Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    public class async extends AsyncTask<String, Void, Void> {
        ProgressDialog progressdialog  = new ProgressDialog(SecondActivity.this);

        JSONParser jsonParser=new JSONParser();

        @Override
        protected void onPreExecute() {

            progressdialog.setIndeterminate(true);
            progressdialog.setMessage("Please Wait...");
            progressdialog.show();
        }


        @Override
        protected Void doInBackground(String...str) {


            bnList=jsonParser.getJSON (str[0]);

            //
            return null;


        }

        @Override
        protected void onPostExecute(Void unused) {

            String array[] = new String[bnList.size()];
            for(int j =0;j<bnList.size();j++){
                array[j] = bnList.get(j).getName();
            }


            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(SecondActivity.this, android.R.layout.simple_spinner_item, array);

            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setAdapter(dataAdapter);

           progressdialog.dismiss();

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();



        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
