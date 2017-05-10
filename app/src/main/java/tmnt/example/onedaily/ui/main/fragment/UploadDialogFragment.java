package tmnt.example.onedaily.ui.main.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import tmnt.example.onedaily.R;

/**
 * Created by tmnt on 2016/8/19.
 */
public class UploadDialogFragment extends DialogFragment {

    private Button takePhoto;
    private Button photo;
    private Button cancel;

    public OnDoOptionOnDialog sOnDoOptionOnDialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_bottom_lay);
        dialog.setCanceledOnTouchOutside(true);

        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        //window.getAttributes().windowAnimations = R.style.AnimBottom;

        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;

        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = getActivity().getWindowManager().getDefaultDisplay().getHeight() / 3;

        window.setAttributes(layoutParams);

        takePhoto = (Button) dialog.findViewById(R.id.takePhoto);
        photo = (Button) dialog.findViewById(R.id.photo);
        cancel = (Button) dialog.findViewById(R.id.cancel);

        if (sOnDoOptionOnDialog != null) {
            takePhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sOnDoOptionOnDialog.onTakePhoto(v);
                }
            });

            photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sOnDoOptionOnDialog.onChoosePhoto(v);
                }
            });

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sOnDoOptionOnDialog.onCancel(v);
                }
            });
        }

        return dialog;

    }

    public void setOnDoOptionOnDialog(OnDoOptionOnDialog sOnDoOptionOnDialog) {
        this.sOnDoOptionOnDialog = sOnDoOptionOnDialog;
    }

    public interface OnDoOptionOnDialog {

        void onTakePhoto(View view);

        void onChoosePhoto(View view);

        void onCancel(View view);

    }
}
