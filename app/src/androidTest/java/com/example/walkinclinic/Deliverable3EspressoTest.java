package com.example.walkinclinic;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import java.util.Random;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class Deliverable3EspressoTest { //Espresso UI Test regarding deliverable 3 functionalities

    private Random rand = new Random();

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void testA(){ //registers a random user as an employee
        onView(withId(R.id.regButton)).perform(click());
        onView(withId(R.id.first_name)).perform(typeText("Anonymous"), closeSoftKeyboard());
        onView(withId(R.id.last_name)).perform(typeText("Unknownson"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("anon@protonmail.com"), closeSoftKeyboard());
        onView(withId(R.id.username)).perform(typeText("randomEmp"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(String.valueOf("random")), closeSoftKeyboard());
        onView(withId(R.id.password2)).perform(typeText(String.valueOf("random")), closeSoftKeyboard());
        onView(withId(R.id.employee)).perform(click());
        onView(withId(R.id.register)).perform(click());
    }

    @Test
    public void testB(){ //logs in as registered employee and chooses clinic
        onView(withId(R.id.logButton)).perform(click());
        onView(withId(R.id.username)).perform(typeText("randomEmp"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("random"), closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());

        onView(withId(R.id.profileEmployee)).perform(click());
        onView(withId(R.id.clinicSelect)).perform(click());
        onView(withText("Rainbow")).perform(click());
    }

    @Test
    public void testC(){ //logs in as registered employee and adds a service for its designated clinic
        onView(withId(R.id.logButton)).perform(click());
        onView(withId(R.id.username)).perform(typeText("randomEmp"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("random"), closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());

        onView(withId(R.id.clinicEmployee)).perform(click());
        onView(withId(R.id.servicesEmployee)).perform(click());
        onView(withId(R.id.addService)).perform(click());
        onView(withText("Blah")).perform(click());
    }

    @Test
    public void testD(){ //logs in as employee and edits the rating on the added service
        onView(withId(R.id.logButton)).perform(click());
        onView(withId(R.id.username)).perform(typeText("randomEmp"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("random"), closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());

        onView(withId(R.id.clinicEmployee)).perform(click());
        onView(withId(R.id.servicesEmployee)).perform(click());
        onView(withText("Blah")).perform(longClick());
        onView(withId(R.id.addRate)).perform(typeText(String.valueOf(rand.nextInt( (5-1) + 1 ) + 1)), closeSoftKeyboard());
        onView(withId(R.id.submit)).perform(click());
    }



    @Test
    public void testE(){ //logs in as employee and deletes the service
        onView(withId(R.id.logButton)).perform(click());
        onView(withId(R.id.username)).perform(typeText("randomEmp"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("random"), closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());

        onView(withId(R.id.clinicEmployee)).perform(click());
        onView(withId(R.id.servicesEmployee)).perform(click());
        onView(withText("Blah")).perform(longClick());
        onView(withId(R.id.deleteService)).perform(click());
    }

}
