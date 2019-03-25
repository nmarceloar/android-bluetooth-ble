package apps.my.p2017;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by standard on 10/1/2017.
 */

public class BalanceBottomSheet extends BottomSheetDialogFragment {

    private SheetListener listener;

    AppCompatButton confirmButton;

    public void setListener(SheetListener listener) {
        this.listener = listener;
    }

    static interface SheetListener {
        public void onGoalConfirmed();
    }

    SheetListener sheetListener;

    static BalanceBottomSheet newInstance(SheetListener listener) {

        BalanceBottomSheet d = new BalanceBottomSheet();
        d.setListener(listener);

        return d;

    }

    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.balance_goal, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        confirmButton = (AppCompatButton) view.findViewById(R.id.confirmButton);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onGoalConfirmed();

            }
        });


    }
}
