package com.example.walkinclinic;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import java.util.Random;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


public class MainActivityTest {

    private Random rand = new Random();
    private int num = rand.nextInt(999);

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void testA(){ //registers a user jdoe099
        onView(withId(R.id.regButton)).perform(click());
        onView(withId(R.id.first_name)).perform(typeText("John"), closeSoftKeyboard());
        onView(withId(R.id.last_name)).perform(typeText("Doe"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("jdoe@protonmail.com"), closeSoftKeyboard());
        onView(withId(R.id.username)).perform(typeText("jdoe099"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.password2)).perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.patient)).perform(click());
        onView(withId(R.id.register)).perform(click());
    }


    @Test
    public void testB(){ //logs in with registered user jdoe099
        onView(withId(R.id.logButton)).perform(click());
        onView(withId(R.id.username)).perform(typeText("jdoe099"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());
    }

    @Test
    public void testC(){ //adds service Test Service
        onView(withId(R.id.logButton)).perform(click());
        onView(withId(R.id.username)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("5T5ptQ"), closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.servicesAdmin)).perform(click());
        onView(withId(R.id.addClinic)).perform(click());
        onView(withId(R.id.addName)).perform(typeText("Test Service" + num), closeSoftKeyboard());
        onView(withId(R.id.addStaff)).perform(typeText("1"), closeSoftKeyboard());
        onView(withId(R.id.buttonAddService)).perform(click());
    }

    @Test
    public void testD(){ //adds clinic Test Clinic
        onView(withId(R.id.logButton)).perform(click());
        onView(withId(R.id.username)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("5T5ptQ"), closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.clinicsAdmin)).perform(click());
        onView(withId(R.id.addClinic)).perform(click());
        onView(withId(R.id.addName)).perform(typeText("Test Clinic" + num), closeSoftKeyboard());
        onView(withId(R.id.addOpeningHour)).perform(typeText("10"), closeSoftKeyboard());
        onView(withId(R.id.addClosingHour)).perform(typeText("12"), closeSoftKeyboard());
        onView(withId(R.id.addAddress)).perform(typeText("21 Jump Street"), closeSoftKeyboard());
        onView(withId(R.id.buttonAddClinic)).perform(click());

    }

    @Test
    public void testE(){ //logs in through different users and checks everything out
        onView(withId(R.id.logButton)).perform(click());
        onView(withId(R.id.username)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("5T5ptQ"), closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.servicesAdmin)).perform(click());
        onView(withId(R.id.backButton)).perform(click());
        onView(withId(R.id.usersAdmin)).perform(click());
        onView(withId(R.id.backButton)).perform(click());
        onView(withId(R.id.clinicsAdmin)).perform(click());
        onView(withId(R.id.backButton)).perform(click());
        onView(withId(R.id.logoff)).perform(click());

        onView(withId(R.id.logButton)).perform(click());
        onView(withId(R.id.username)).perform(typeText("akomp033"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("Kmpzkmpzkmpz1"), closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.logoff)).perform(click());

        onView(withId(R.id.logButton)).perform(click());
        onView(withId(R.id.username)).perform(typeText("jdoe099"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.logoff)).perform(click());
    }





}
