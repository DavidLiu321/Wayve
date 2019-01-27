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

public class Rating extends DialogFragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.rating, container, false);

            Button accept = view.findViewById(R.id.dismiss);

            final EditText desc = view.findViewById(R.id.description);

            desc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    desc.setText("");
                }
            });

            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getDialog().dismiss();
                }
            });

            return view;
        }
    }
