package mx.com.pvictor3.emonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        Earthquake earthquake = (Earthquake) extras.get(MainActivity.SELECTED_EARTHQUAKE);

        TextView magnitudeTextView = findViewById(R.id.detail_magintude_textView);
        TextView longitudeTextView = findViewById(R.id.detail_longitude_textView);
        TextView latitudeTextView = findViewById(R.id.detail_latitude_textView);
        TextView placeTextView = findViewById(R.id.detail_place_textView);
        TextView dateTextView = findViewById(R.id.detail_date_textView);

        magnitudeTextView.setText(String.valueOf(earthquake.getMagnitude()));
        longitudeTextView.setText(earthquake.getLongitude());
        latitudeTextView.setText(earthquake.getLatitude());
        placeTextView.setText(earthquake.getPlace());
        dateTextView.setText(earthquake.getDate());

    }
}
