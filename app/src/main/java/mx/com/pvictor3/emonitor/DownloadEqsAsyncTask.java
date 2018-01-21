package mx.com.pvictor3.emonitor;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class DownloadEqsAsyncTask extends AsyncTask<URL, Void, ArrayList<Earthquake>>{

    DownloadEqsInterface delegate;

    public interface DownloadEqsInterface{
        void onEqsDownloaded(ArrayList<Earthquake> eqList);
    }

    @Override
    protected ArrayList<Earthquake> doInBackground(URL... urls) {
        String eqData = "";
        ArrayList<Earthquake> eqList = null;
        try{
            eqData = downloadData(urls[0]);
            eqList = parseDataFromJson(eqData);
        }catch (IOException e){
            e.printStackTrace();
        }
        return eqList;
    }

    @Override
    protected void onPostExecute(ArrayList<Earthquake> eqList) {
        super.onPostExecute(eqList);

        delegate.onEqsDownloaded(eqList);
    }

    private ArrayList<Earthquake> parseDataFromJson(String eqData){
        ArrayList<Earthquake> earthquakesList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(eqData);
            JSONArray featuresJsonArray = jsonObject.getJSONArray("features");

            for(int i = 0; i<featuresJsonArray.length(); i++){
                JSONObject featureJsonItem = featuresJsonArray.getJSONObject(i);
                JSONObject propertiesJsonObject = featureJsonItem.getJSONObject("properties");

                double magnitude = propertiesJsonObject.getDouble("mag");
                String place = propertiesJsonObject.getString("place");
                Long date = propertiesJsonObject.getLong("time");
                JSONObject geometryJsonObject = featureJsonItem.getJSONObject("geometry");
                JSONArray coordinatesJsonArray = geometryJsonObject.getJSONArray("coordinates");
                String longitude = coordinatesJsonArray.getString(0);
                String latitude = coordinatesJsonArray.getString(1);
                earthquakesList.add(new Earthquake(magnitude,place,date,longitude,latitude));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return earthquakesList;
    }

    private String downloadData(URL url) throws IOException{
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /*milliseconds*/);
            urlConnection.setConnectTimeout(15000 /*milliseconds*/);

            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);
        }catch (IOException e){

        }finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }

            if (inputStream != null){
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();
        if(inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while(line != null){
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}
