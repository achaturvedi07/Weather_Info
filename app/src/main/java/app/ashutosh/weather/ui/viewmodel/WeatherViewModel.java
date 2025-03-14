package app.ashutosh.weather.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import app.ashutosh.weather.data.model.WeatherResponse;
import app.ashutosh.weather.data.repository.WeatherRepository;

public class WeatherViewModel extends ViewModel {
    private final WeatherRepository repository = new WeatherRepository();

    public LiveData<WeatherResponse> getWeather(String city) {
        return repository.getWeather(city);
    }
}
