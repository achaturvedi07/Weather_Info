package app.ashutosh.weatherapp;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testSearchButton_ClickAndShowResult() {
        onView(withId(R.id.cityName)).perform(typeText("Mumbai"), closeSoftKeyboard());
        onView(withId(R.id.search)).perform(click());
        onView(withId(R.id.weather)).check(matches(isDisplayed()));
    }

    @Test
    public void testEmptyCity_ShowsToastMessage() {
        onView(withId(R.id.search)).perform(click());
        onView(withText("Please enter a city name"))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }
}
