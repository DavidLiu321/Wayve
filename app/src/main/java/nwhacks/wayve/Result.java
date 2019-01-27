package nwhacks.wayve;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class Result extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.result, container, false);

        Button dis = view.findViewById(R.id.dismiss);

        TextView loc = view.findViewById(R.id.Location);

        RatingBar rate = view.findViewById(R.id.ratingBar);

        loc.setText(Rating.entry.getname());

        rate.setRating(Rating.entry.getRating());

        dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        return view;
    }
}
