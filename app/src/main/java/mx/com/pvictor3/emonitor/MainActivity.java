package mx.com.pvictor3.emonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String SELECTED_EARTHQUAKE = "earthquake";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Earthquake> earthquakesList = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            earthquakesList.add(new Earthquake(i,"México", "Hoy", "128", "132"));
        }

        final EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(this,R.layout.list_item_layout,earthquakesList);

        ListView earthquakeListView = findViewById(R.id.earthquake_listView);

        earthquakeListView.setAdapter(earthquakeAdapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Earthquake selectedEarthquake = earthquakeAdapter.getItem(position);

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(SELECTED_EARTHQUAKE,selectedEarthquake);

                startActivity(intent);
            }
        });
    }
}
