package de.fsappz.ovid.ui.more;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import de.fsappz.ovid.R;
import de.fsappz.ovid.Util;

public class MoreFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_more, container, false);

        TextView tvGithub = root.findViewById(R.id.tv_source_code);
        tvGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/fsappz78/ovid"));
                startActivity(browserIntent);
            }
        });

        TextView tvIDE = root.findViewById(R.id.tv_ide);
        tvIDE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com/studio"));
                startActivity(browserIntent);
            }
        });

        TextView tvFontUsed = root.findViewById(R.id.tv_font_used);
        tvFontUsed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Cinzel Decorative");
                builder.setMessage(R.string.ofl);
                builder.setPositiveButton("Schließen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = builder.show();
                TextView textView = alertDialog.findViewById(android.R.id.message);
                Typeface face=Typeface.SANS_SERIF;
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(face);
                textView.setTypeface(face);
            }
        });

        TextView textView = new TextView(getContext());
        textView.setText(Util.s18573());
        textView.setGravity(Gravity.CENTER);

        ((LinearLayout) root.findViewById(R.id.about_container)).addView(textView,2);
        return root;
    }
}