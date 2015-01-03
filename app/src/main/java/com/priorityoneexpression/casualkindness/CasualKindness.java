package com.priorityoneexpression.casualkindness;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Jason Suttles on 12/23/2014.
 */
public class CasualKindness extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "3HwsLkZpIG4FrvRDg3GKwOR3jLnj5IG0XQ07h7Yb", "JPTR7O8McreukkFvMkJPVXT61dUIUTEr8CJdH5yG");

        // TODO: Seed local Datastore
    }
}
