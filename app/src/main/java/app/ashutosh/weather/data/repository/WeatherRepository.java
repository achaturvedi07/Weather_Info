package app.ashutosh.weather.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import app.ashutosh.weather.data.model.WeatherResponse;
import app.ashutosh.weather.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {
    private static final String API_KEY = "83759bdf8324339cbcc2776fc0336476";
    private static final String UNITS = "metric";

    private final MutableLiveData<WeatherResponse> weatherData = new MutableLiveData<>();

    public LiveData<WeatherResponse> getWeather(String city) {
        RetrofitClient.getApiService().getWeather(city, UNITS, API_KEY).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    weatherData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                weatherData.setValue(null);
            }
        });
        return weatherData;
    }
}
