package com.example.nazariy.places.presentation.main.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nazariy.places.R;
import com.example.nazariy.places.presentation.main.utils.SharedPrefUtils;
import com.example.nazariy.places.presentation.main.view.recyclerview.sorting.SortingListAdapter;
import com.example.nazariy.places.presentation.main.view.recyclerview.sorting.SortingTypeDiffCallback;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FilterDialog extends DialogFragment {
    private OnCompleteListener settingsCompleteListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        settingsCompleteListener = (OnCompleteListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_filter, container);
        initUi(root);
        return root;
    }

    private void initUi(View root) {
        SortingListAdapter sortingListAdapter = new SortingListAdapter(new
                SortingTypeDiffCallback());
        initRecycler(root, sortingListAdapter);

        root.findViewById(R.id.btn_dismiss).setOnClickListener(view -> dismiss());
        root.findViewById(R.id.btn_submit).setOnClickListener(view -> {
            String checkedSortingType = sortingListAdapter.getCheckedSortingType();
            updateSortingTypePreference(checkedSortingType);
            dismiss();
        });
    }

    private void updateSortingTypePreference(String checkedSortingType) {
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(
                    SharedPrefUtils.generateSharedPrefName(getActivity()), Context.MODE_PRIVATE);
            String previousSortingType = sharedPreferences
                    .getString(getString(R.string.sorting_type), checkedSortingType);
            if (previousSortingType != null && !previousSortingType.equals(checkedSortingType)) {
                sharedPreferences
                        .edit()
                        .putString(getString(R.string.sorting_type), checkedSortingType)
                        .apply();
                settingsCompleteListener.onFilterPicked(checkedSortingType);
            }
        }
    }

    private void initRecycler(View root, SortingListAdapter sortingListAdapter) {
        RecyclerView sortingTypeList = root.findViewById(R.id.sorting_type_list);
        sortingTypeList.setAdapter(sortingListAdapter);
        sortingTypeList.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView
                .VERTICAL, false));
        sortingListAdapter.submitList(Arrays.asList(getResources().getStringArray(R.array
                .available_sorting_types)));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        settingsCompleteListener = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    interface OnCompleteListener {
        void onFilterPicked(String sortingType);
    }

}
