package com.gmail.pingkiuho.navigationdrawertemplates;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Brian Ho on 14/3/2018.
 */

public class ContentFragment extends Fragment {
    public static final String TAG = ContentFragment.class.getSimpleName();

    private TextView mTextView;
    private String mText;

    public static ContentFragment newInstance(String text) {
        Bundle args = new Bundle();
        args.putString("text", text);

        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mText = getArguments().getString("text");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        mTextView = view.findViewById(R.id.textView);
        mTextView.setText(mText);
        return view;
    }


}
