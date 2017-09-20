package io.natalietay.sparrow;

import android.support.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Rule;
import org.junit.Test;

import okhttp3.mockwebserver.MockResponse;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Natalie on 20/9/17.
 */
public class MainActivityTest {

    // https://developer.android.com/training/testing/espresso/intents.html
    @Rule
    public IntentsTestRule<MainActivity> rule = new IntentsTestRule<>(MainActivity.class);

    // we're introducing a mockwebserver rule
    // which essentially starts and stops the mock web server
    // before and after each test
    @Rule
    public MockWebServerRule mockWebServerRule = new MockWebServerRule();

    @Test
    public void hasGreetingWithResubscribeButton() {
        onView(withId(R.id.zen))
                .check(matches(withText("Hola amigo!")));
        onView(withId(R.id.resubscribe))
                .check(matches(withText("Re-subscribe")));
    }

    @Test
    public void clickOnResubscribeButton_getsZen_setsZen() {
        // take a look at https://github.com/square/okhttp/tree/master/mockwebserver
        // to see how the mock web server works
        mockWebServerRule
                .getMockWebServer()
                .enqueue(new MockResponse().setBody("Something else from the server"));

        onView(withId(R.id.resubscribe))
                .perform(click());
        onView(withId(R.id.zen))
                .check(matches(withText("Something else from the server")));
    }
}
