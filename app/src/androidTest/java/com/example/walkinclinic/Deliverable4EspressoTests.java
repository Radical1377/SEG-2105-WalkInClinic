package com.example.walkinclinic;

import android.widget.TextView;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import java.util.Random;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.typeText;

import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;

/*
 *@author Matthew Tran
 * Espresso UI tests relevant to deliverable 4 of SEG2105 walkin clinic project
 * 10 tests total
 * 1-Search clinic by name (passed)
 * 2-Search for clinic by Address (passed)
 * 3-Search by Working hours weekday(passed)
 * 4-Search by hours weekend (passed)
 * 5-Search for Service (passed)
 * 6-View clinic reviews (passed)
 * 7-Rate a clinic (passed)
 * 8-Comment a clinic (passed))
 * 9-Book an appointment (passed)
 *10-View waiting time (passed)
 */
public class Deliverable4EspressoTests {
    private Random rand = new Random();
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    @Test
    public void searchClinicByName() {
        onView(withId(R.id.logButton)).perform(click());
        onView(withId(R.id.username)).perform(typeText("jdoe099"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());
        //click to search for clinic, goes to PatientSearch.class
        onView(withId(R.id.searchBUTTON)).perform(click());
        //search for Rainbow clinic
        onView(withId(R.id.name)).perform(typeText("Rainbow") ,closeSoftKeyboard());
        onView(withId(R.id.submitBtn)).perform(click()); //searches for clinic

        //OPTION: assert equal to Rainbow clinic?
    }
    @Test
    public void searchClinicByAddress() {//search for a clinic by address
        onView(withId(R.id.logButton)).perform(click());
        onView(withId(R.id.username)).perform(typeText("jdoe099"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());
        //click to search for clinic, goes to PatientSearch.class
        onView(withId(R.id.searchBUTTON)).perform(click());
        //address for Rainbow clinic--> jireodk
        onView(withId(R.id.address)).perform(typeText("jireodk") ,closeSoftKeyboard());
        onView(withId(R.id.submitBtn)).perform(click()); //searches for clinic

        //OPTION: assert equal to Rainbow clinic?
    }
    @Test
    public void searchClinicByWorkHoursWeekday() {
        onView(withId(R.id.logButton)).perform(click());
        onView(withId(R.id.username)).perform(typeText("jdoe099"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());
        //click to search for clinic, goes to PatientSearch.class
        onView(withId(R.id.searchBUTTON)).perform(click());
        onView(withId(R.id.time)).perform(typeText("10") ,closeSoftKeyboard());
        onView(withId(R.id.weekday)).perform(click()); //check the weekday box
        onView(withId(R.id.submitBtn)).perform(click()); //searches for clinic open during this time

        //OPTION: assert equal to Rainbow clinic? from listView
    }
    @Test
    public void searchClinicByWorkHoursWeekend() {
        onView(withId(R.id.logButton)).perform(click());
        onView(withId(R.id.username)).perform(typeText("jdoe099"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());
        //click to search for clinic, goes to PatientSearch.class
        onView(withId(R.id.searchBUTTON)).perform(click());
        onView(withId(R.id.time)).perform(typeText("10") ,closeSoftKeyboard());
        onView(withId(R.id.weekend)).perform(click()); //check the weekend box
        onView(withId(R.id.submitBtn)).perform(click()); //searches for clinic open during this time

        //OPTION: assert equal to Rainbow clinic? from listView
    }
    @Test
    public void searchForService() {
        onView(withId(R.id.logButton)).perform(click());
        onView(withId(R.id.username)).perform(typeText("jdoe099"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());
        //click to search for services, goes to PatientSearchByService.class
        onView(withId(R.id.searchServices)).perform(click());
        onView(withId(R.id.selectService)).perform(click()); //click select a service
        //goes to PatientSelectService.class
        //choose first service from list and select
        onData(anything()).inAdapterView(withId(R.id.listServicesClinic)).atPosition(0).perform(click());

        //OPTION: assert equal to Rainbow clinic? from listView
    }
    @Test
    public void viewClinicReviews() { //search a clinic from the service search page
        onView(withId(R.id.logButton)).perform(click());
        onView(withId(R.id.username)).perform(typeText("jdoe099"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.allclinicsbtn)).perform(click()); //go to list of all clinics
        //click first element in clinic listView (Rainbow)
        onData(anything()).inAdapterView(withId(R.id.listClinics)).atPosition(0).perform(click());
        onView(withId(R.id.reviewsBtn)).perform(click());
        //goes to patientClinicReviews.java
        //OPTION: assert equal to Rainbow clinic? from listView
    }
    @Test
    public void rateClinic() {//user can give a rating at clinic
        onView(withId(R.id.logButton)).perform(click());
        onView(withId(R.id.username)).perform(typeText("jdoe099"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.allclinicsbtn)).perform(click()); //go to list of all clinics
        //click first element in clinic listView (Rainbow)
        onData(anything()).inAdapterView(withId(R.id.listClinics)).atPosition(0).perform(click());
        onView(withId(R.id.ratingBtn)).perform(click()); //go to rating page
        //choose a rating from 1-5, check rating 3 and submit
        onView(withId(R.id.three)).perform(click());
        onView(withId(R.id.submitBtn)).perform(click());
    }
    @Test
    public void commentClinic() {
        onView(withId(R.id.logButton)).perform(click());
        onView(withId(R.id.username)).perform(typeText("jdoe099"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.allclinicsbtn)).perform(click()); //go to list of all clinics
        //click first element in clinic listView (Rainbow)
        onData(anything()).inAdapterView(withId(R.id.listClinics)).atPosition(0).perform(click());
        onView(withId(R.id.ratingBtn)).perform(click()); //go to rating page
        //enter a comment in EditText box and submit for clinic
        onView(withId(R.id.comment)).perform(typeText("test comment"), closeSoftKeyboard());
        onView(withId(R.id.submitBtn)).perform(click());
    }
    @Test
    public void bookAppointment() {//user booking appointment at a clinic
        onView(withId(R.id.logButton)).perform(click());
        onView(withId(R.id.username)).perform(typeText("jdoe099"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.allclinicsbtn)).perform(click()); //go to list of all clinics
        //click first element in clinic listView (Rainbow)
        onData(anything()).inAdapterView(withId(R.id.listClinics)).atPosition(0).perform(click());
        //bookingBtn to go to PatientBooking.class
        onView(withId(R.id.bookingBtn)).perform(click());
        //enter in valid info and service requested
        //booking from 10:30-11:30 on December 06, 2019
        onView(withId(R.id.startTimeHours)).perform(typeText("10"),closeSoftKeyboard());
        onView(withId(R.id.startTimeMinutes)).perform(typeText("30"),closeSoftKeyboard());
        onView(withId(R.id.endTimeHours)).perform(typeText("11"),closeSoftKeyboard());
        onView(withId(R.id.endTimeHours)).perform(typeText("30"),closeSoftKeyboard());
        onView(withId(R.id.dateDay)).perform(typeText("06"),closeSoftKeyboard());
        onView(withId(R.id.dateMonth)).perform(typeText("12"),closeSoftKeyboard());
        onView(withId(R.id.dateYear)).perform(typeText("2019"),closeSoftKeyboard());
        onView(withId(R.id.serviceSelection)).perform(click()); //selecting a service from list
        //goes to activity_add_service.xml / serivcesEmployee.java
        //select the first service
        onData(anything()).inAdapterView(withId(R.id.listServicesClinic)).atPosition(0).perform(click());
        //should be back to PatientBooking.java
        onView(withId(R.id.submitBtn)).perform(click()); //submit booking, everything checked and success
    }
    @Test
    public void viewWaitTime() {//user should be able to select clinic and see wait time (PASSED)
        onView(withId(R.id.logButton)).perform(click());
        onView(withId(R.id.username)).perform(typeText("jdoe099"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());

        onView(withId(R.id.allclinicsbtn)).perform(click()); //go to list of all clinics
        //click first element in clinic listView (Rainbow)
        onData(anything()).inAdapterView(withId(R.id.listClinics)).atPosition(0).perform(click());

        onView(withId(R.id.waitTime)).check(matches((withText(containsString("Approximate Wait Time")))));
        //check if wait time exists
    }
}
