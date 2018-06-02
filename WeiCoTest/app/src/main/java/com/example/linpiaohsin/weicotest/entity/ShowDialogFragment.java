package com.example.linpiaohsin.weicotest.entity;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.example.linpiaohsin.weicotest.R;

/**
 * Created by linpiaohsin on 2017/10/30.
 */

public class ShowDialogFragment extends DialogFragment {
    Button btn_sure_delete;
    Button btn_cancel_delete;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view=inflater.inflate(R.layout.fragment_delete_dialog,container);
        btn_sure_delete= (Button) view.findViewById(R.id.btn_in_fragment_sure);
        btn_cancel_delete= (Button) view.findViewById(R.id.btn_in_fragment_cancel);
        btn_sure_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();

            }
        });
        btn_cancel_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();


            }
        });
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
