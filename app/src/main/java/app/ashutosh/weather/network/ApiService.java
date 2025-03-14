package app.ashutosh.weather.network;

import app.ashutosh.weather.data.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("weather")
    Call<WeatherResponse> getWeather(
            @Query("q") String city,
            @Query("units") String units,
            @Query("appid") String apiKey
    );
}
