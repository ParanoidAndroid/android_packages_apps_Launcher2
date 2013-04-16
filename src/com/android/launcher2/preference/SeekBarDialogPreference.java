package com.android.launcher2.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.launcher.R;

/**
 * @author nebkat
 */
public class SeekBarDialogPreference extends DialogPreference implements SeekBar.OnSeekBarChangeListener {
    private int mMax, mMin, mDefault;

    private String mPrefix, mSuffix;

    private TextView mValueText;
    private SeekBar mSeekBar;

    public SeekBarDialogPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray seekBarType = context.obtainStyledAttributes(attrs,
                R.styleable.SeekBarDialogPreference, 0, 0);

        mMax = seekBarType.getInt(R.styleable.SeekBarDialogPreference_max, 100);
        mMin = seekBarType.getInt(R.styleable.SeekBarDialogPreference_min, 0);

        mDefault = seekBarType.getInt(R.styleable.NumberPickerPreference_defaultValue, mMin);

        mPrefix = seekBarType.getString(R.styleable.SeekBarDialogPreference_prefix);
        mSuffix = seekBarType.getString(R.styleable.SeekBarDialogPreference_suffix);
        if (mPrefix == null) {
            mPrefix = "";
        }
        if (mSuffix == null) {
            mSuffix = "%";
        }

        seekBarType.recycle();
    }

    @Override
    protected View onCreateDialogView() {
        LayoutInflater inflater =
                (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.seekbar_dialog, null);

        mValueText = (TextView) view.findViewById(R.id.value);

        mSeekBar = (SeekBar) view.findViewById(R.id.seekbar);
        mSeekBar.setOnSeekBarChangeListener(this);
        mSeekBar.setMax(mMax - mMin);
        mSeekBar.setProgress(getPersistedInt(mDefault) - mMin);

        mValueText.setText(mPrefix + getPersistedInt(mDefault) + mSuffix);

        return view;
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mValueText.setText(mPrefix + (progress + mMin) + mSuffix);
    }

    public void onStartTrackingTouch(SeekBar seekBar) {}
    public void onStopTrackingTouch(SeekBar seekBar) {}

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            PreferencesProvider.setInt(getKey(), mSeekBar.getProgress() + mMin);
        }
    }

    @Override
    public void setDefaultValue(Object defaultValue) {
        mDefault = (Integer)defaultValue;
    }

}