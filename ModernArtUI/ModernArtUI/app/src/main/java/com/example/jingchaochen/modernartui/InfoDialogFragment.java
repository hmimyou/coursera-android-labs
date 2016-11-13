package com.example.jingchaochen.modernartui;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class InfoDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppTheme_Dialog);
        builder.setMessage(R.string.info_content)
                .setNegativeButton(R.string.info_action_visit, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                        Intent baseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org"));

                        Intent chooserIntent = Intent.createChooser(baseIntent, "Load http://www.moma.org with:");

                        if (baseIntent.resolveActivity(getActivity().getPackageManager()) != null ) {
                            startActivity(chooserIntent);
                        }
                    }
                })
                .setPositiveButton(R.string.info_action_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it

        Dialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        return dialog;
    }
}