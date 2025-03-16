package app.ashutosh.weather;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private TextView cityName, show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityName = findViewById(R.id.cityName);
        Button search = findViewById(R.id.search);
        show = findViewById(R.id.weather);

        search.setOnClickListener(v -> {
            String city = cityName.getText().toString().trim();
            if (city.isEmpty()) {
                Toast.makeText(this, "Enter City", Toast.LENGTH_SHORT).show();
                return;
            }
            String url = "https://api.openweathermap.org/data/2.5/weather?q=patna&units=metric&appid=83759bdf8324339cbcc2776fc0336476";
            new GetWeatherTask().execute(url);
        });
    }

    private class GetWeatherTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            StringBuilder result = new StringBuilder();
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) new URL(urls[0]).openConnection();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) result.append(line).append("\n");
                }
                return result.toString();
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result == null) {
                show.setText("Unable to fetch weather data");
                return;
            }
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONObject main = jsonObject.getJSONObject("main");
                String weatherInfo = "Temperature : " + main.getDouble("temp") + "°C\n" +
                        "Feels Like : " + main.getDouble("feels_like") + "°C\n" +
                        "Temperature Max : " + main.getDouble("temp_max") + "°C\n" +
                        "Temperature Min : " + main.getDouble("temp_min") + "°C\n" +
                        "Pressure : " + main.getInt("pressure") + " hPa\n" +
                        "Humidity : " + main.getInt("humidity") + "%";
                show.setText(weatherInfo);
            } catch (Exception e) {
                show.setText("Error parsing weather data");
            }
        }
    }
}
