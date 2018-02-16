package com.example.kuliza332.customcomponent;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Akash Verma on 11/02/18.
 */

public class CustomForm extends LinearLayout {

    private Context mContext;
    private LayoutInflater mInflater;
    private ImageView editMode;
    private LinearLayout customFormLinearLayout;
    private LinearLayout dummyLinearLayout;
    private List<CustomEditText> customEditTextList = new ArrayList<>();
    private List<LinearLayout> editTextContainerList = new ArrayList<>();
    private CustomFormListener mListener;

    public CustomForm(Context context) {
        super(context);
        init(context);
    }

    public CustomForm(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomForm(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context) {
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        CustomForm view = (CustomForm) mInflater.inflate(R.layout.custom_form, this);
        initializeView(view);
    }

    private void initializeView(CustomForm view) {
        customFormLinearLayout = view.findViewById(R.id.custom_form_ll);
        editMode = view.findViewById(R.id.edit_mode);
        dummyLinearLayout = view.findViewById(R.id.dummy_ll);
        addClickListener();
    }

    private void addClickListener() {
        editMode.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //enable disable form
                boolean enable = true;
                if (customEditTextList.size() > 0) {
                    if (customEditTextList.get(0).isEnabled()) {
                        enable = false;
                        dummyLinearLayout.setFocusableInTouchMode(true);
                    } else {
                        enable = true;
                        customEditTextList.get(0).requestFocus();
                        dummyLinearLayout.setFocusableInTouchMode(false);
                    }

                    for (CustomEditText customEditText :
                            customEditTextList) {
                        customEditText.enableDisableEditTextView(enable);
                    }
                }
            }
        });
    }

    public void addEditTextView(CustomFormListener mListener, int row, String tag, String hint) {
        this.mListener = mListener;
        int childCount = customFormLinearLayout.getChildCount();

        if (childCount == 0) {
            LinearLayout editTextContainer = new LinearLayout(mContext);

            createCustomEditText(editTextContainer, hint, tag);

            customFormLinearLayout.addView(editTextContainer);
            editTextContainerList.add(editTextContainer);
        } else {
            LinearLayout editTextContainer = null;
            try {
                editTextContainer = editTextContainerList.get(row);
            } catch (Exception e) {
                editTextContainer = new LinearLayout(mContext);
                customFormLinearLayout.addView(editTextContainer);
                editTextContainerList.add(editTextContainer);
            } finally {
                createCustomEditText(editTextContainer, hint, tag);
            }
        }
    }

    private void createCustomEditText(LinearLayout editTextContainer, String hint, String tagIdentifier) {
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT,
                1.0f
        );
        param.setMargins(20, 0, 20, 0);

        TextInputLayout textInputLayout = new TextInputLayout(mContext);
        textInputLayout.setLayoutParams(param);

        CustomEditText customEditText = new CustomEditText(mContext);
        customEditText.setLayoutParams(param);
        customEditText.setHint(hint);
        customEditText.setTagIdentifier(tagIdentifier);

        editTextContainer.addView(textInputLayout);
        textInputLayout.addView(customEditText);
        customEditTextList.add(customEditText);
    }

    public void generateTextMap() {
        HashMap<String, String> resultTextMap = new HashMap<>();
        for (CustomEditText customEditText :
                customEditTextList) {
            resultTextMap.put(customEditText.getTagIdentifier().toString(), customEditText.getText().toString());
        }

        this.mListener.returnText(resultTextMap);
    }

    public interface CustomFormListener {
        void returnText(HashMap<String, String> resultTextMap);
    }
}
