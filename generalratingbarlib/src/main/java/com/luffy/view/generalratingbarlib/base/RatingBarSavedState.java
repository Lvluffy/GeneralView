package com.luffy.view.generalratingbarlib.base;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/**
 * Created by lvlufei on 2019/1/4
 *
 * @desc 保存状态
 */
class RatingBarSavedState extends View.BaseSavedState {

    private float rating;

    /**
     * Constructor called from {@link BaseRatingBar#onSaveInstanceState()}
     */
    RatingBarSavedState(Parcelable superState) {
        super(superState);
    }

    /**
     * Constructor called from {@link #CREATOR}
     */
    private RatingBarSavedState(Parcel in) {
        super(in);
        rating = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeFloat(rating);
    }

    public static final Creator<RatingBarSavedState> CREATOR = new Creator<RatingBarSavedState>() {
        public RatingBarSavedState createFromParcel(Parcel in) {
            return new RatingBarSavedState(in);
        }

        public RatingBarSavedState[] newArray(int size) {
            return new RatingBarSavedState[size];
        }
    };

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
