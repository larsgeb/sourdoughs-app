package com.example.swpsourdough;

import java.util.Date;

public final class BreadListItem {

    String mBaker = "You!";
    int mTaste = 10;
    int mCrunch = 10;
    int mFluff = 10;
    int mPretty = 10;
    int mHydration = 10;
    String mFlour = "100% all-purpose flour";
    int mID = -1;
    Date mBakeDate;
    String mStarter = "Yeast :(";

    BreadListItem() {

    }

    BreadListItem(int id) {
        this.mID = id;
    }

    public BreadListItem(int id, String baker, int taste, int crunch, int fluff,
                         int pretty, int hydration, String flour, Date bakeDate,
                         String starter) {
        this.mBaker = baker;
        this.mTaste = taste;
        this.mCrunch = crunch;
        this.mFluff = fluff;
        this.mPretty = pretty;
        this.mHydration = hydration;
        this.mFlour = flour;
        this.mID = id;
        this.mBakeDate = bakeDate;
        this.mStarter = starter;
    }
}
