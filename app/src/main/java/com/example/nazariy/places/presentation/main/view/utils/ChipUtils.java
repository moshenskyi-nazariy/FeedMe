package com.example.nazariy.places.presentation.main.view.utils;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class ChipUtils {
    public static String[] getChildren(ChipGroup chipGroup) {
        int childCount = chipGroup.getChildCount();
        String[] childNames = new String[childCount];
        for (int i = 0; i < childCount; i++) {
            childNames[i] = (String) ((Chip) chipGroup.getChildAt(i)).getText();
        }
        return childNames;
    }
}
