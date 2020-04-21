package com.example.swpsourdough;

public final class BreadListItem {

  String mBaker = "You!";
  int mTaste = 10;
  int mCrunch = 10;
  int mFluff = 10;
  int mPretty = 10;
  int mHydration = 10;
  String mFlour = "100% all-purpose flour";
  int mID = -1;
  String mBakeDate;
  String mStarter = "Yeast :(";
  String mImageURL;

  BreadListItem() {

  }

  BreadListItem(int id) {
    this.mID = id;
  }

  public BreadListItem(int id, String baker, int taste, int crunch, int fluff,
      int pretty, int hydration, String flour, String bakeDate,
      String starter, String imageURL) {

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
    this.mImageURL = imageURL;
  }
}
