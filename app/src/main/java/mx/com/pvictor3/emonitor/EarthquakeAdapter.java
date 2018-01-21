package mx.com.pvictor3.emonitor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake>{
    private Context context;
    private int layoutId;
    private ArrayList<Earthquake> earthquakes;

    public EarthquakeAdapter(@NonNull Context context, int resource, @NonNull List<Earthquake> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutId = resource;
        this.earthquakes = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layoutId,null);

            holder = new ViewHolder();

            holder.magnitudeTextView = convertView.findViewById(R.id.magnitude_textView);
            holder.placeTextView = convertView.findViewById(R.id.place_textView);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Earthquake earthquake = earthquakes.get(position);

        holder.magnitudeTextView.setText(String.valueOf(earthquake.getMagnitude()));
        holder.placeTextView.setText(earthquake.getPlace());

        return convertView;
    }

    private class ViewHolder{
        TextView magnitudeTextView;
        TextView placeTextView;
    }
}
