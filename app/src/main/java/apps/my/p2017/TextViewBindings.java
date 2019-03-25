package apps.my.p2017;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextViewBindings {

    @BindingAdapter("android:dateValue")
    public static void setDateFromLong(TextView view, long timeStampMillis) {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        view.setText(dateFormat.format(new Date(timeStampMillis)));

    }

    @BindingAdapter("android:timeValue")
    public static void setTimeFromLong(TextView view, long timeStampMillis) {

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");

        view.setText(dateFormat.format(new Date(timeStampMillis)));

    }

    @BindingAdapter(value = {"app:hourValue", "app:minuteValue"}, requireAll = true)
    public static void setDuration(TextView view, int hour, int value) {

        view.setText(String.format("%d hs %d min", hour, value));

    }


    @BindingAdapter("android:text")
    public static void setTextFloat(TextView view, float value) {

        boolean fieldIsEmpty = (view.getText().length() == 0);

        if (!fieldIsEmpty) {

            float content = Float.parseFloat(view.getText().toString());

            if (value != content) {

                view.setText(String.valueOf(value));

            }

        } else {

            view.setText(String.valueOf(value));

        }


    }

    @InverseBindingAdapter(attribute = "android:text")
    public static float getTextFloat(TextView view) {
        try {
            return Float.parseFloat(view.getText().toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @BindingAdapter("android:text")
    public static void setTextInt(TextView view, int value) {

        boolean fieldIsEmpty = view.getText().length() == 0;

        if (!fieldIsEmpty) {

            int content = Integer.parseInt(view.getText().toString());

            if (value != content) {

                view.setText(String.valueOf(value));

            }

        } else {

            view.setText(String.valueOf(value));

        }


    }

    @InverseBindingAdapter(attribute = "android:text")
    public static int getTextInt(TextView view) {

        try {

            return Integer.parseInt(view.getText().toString());

        } catch (NumberFormatException e) {

            return 0;

        }
    }

}