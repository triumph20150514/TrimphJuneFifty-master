package com.trimh.nuannuan.view;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DialogTitle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.trimh.nuannuan.R;
import com.trimh.nuannuan.utils.loading.Style;

/**
 * @description: Project TrimphJuneFifty
 * Package_name com.trimh.nuannuan.view
 * Created by Trimph on 2016/7/3.
 */
public class LoadDialog extends AlertDialog {

    public Context mContext;
    public LayoutInflater layoutInflater;
    AlertDialog alertDialog;

    public LoadDialog(Context context) {
        super(context);
        this.mContext = context;
        layoutInflater = LayoutInflater.from(context);

    }

    public void showDialog() {

        alertDialog = new AlertDialog.Builder(mContext, 0).create();
        alertDialog.show();

        Window window = alertDialog.getWindow();

        View view = layoutInflater.inflate(R.layout.load_dialog_layout, null);
     /*   SpinKitView spinKitView = (SpinKitView) view.findViewById(R.id.spinKitView);
        Circle circle = new Circle();
        circle.setColor(Color.RED);
        spinKitView.setIndeterminateDrawable(circle);*/
        window.setContentView(view);

    }

    public void closeDialog() {
        alertDialog.dismiss();
    }

}
