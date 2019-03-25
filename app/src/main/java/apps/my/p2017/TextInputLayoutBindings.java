package apps.my.p2017;

import android.databinding.BindingAdapter;
import android.support.design.widget.TextInputLayout;

public class TextInputLayoutBindings {

    @BindingAdapter("app:error")
    public static void setError(TextInputLayout textInputLayout, String errorText) {

        if (errorText.isEmpty())
            textInputLayout.setError(null);
        else
            textInputLayout.setError(errorText);

    }
}
