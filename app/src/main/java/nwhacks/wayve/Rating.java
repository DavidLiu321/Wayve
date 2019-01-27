package nwhacks.wayve;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class Rating extends DialogFragment {

        static Place entry;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.rating, container, false);

            Button accept = view.findViewById(R.id.dismiss);

            final RatingBar star = view.findViewById(R.id.ratingBar);

            final EditText desc = view.findViewById(R.id.description);

            desc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    desc.setText("");
                }
            });

            final EditText name = view.findViewById(R.id.name);

            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name.setText("");
                }
            });

            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    entry = new Place(name.getText().toString());
                    entry.addRating(star.getNumStars());

                    getDialog().dismiss();
                }
            });

            return view;
        }
    }
