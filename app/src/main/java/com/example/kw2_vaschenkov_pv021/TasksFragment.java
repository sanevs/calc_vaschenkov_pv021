package com.example.kw2_vaschenkov_pv021;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.kw2_vaschenkov_pv021.databinding.FragmentTasksBinding;

public class TasksFragment extends Fragment {

    private FragmentTasksBinding binding;
    private TasksViewModel mViewModel;

    public static TasksFragment newInstance() {
        return new TasksFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentTasksBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TasksFragment.this)
                        .navigate(R.id.action_tasksFragment_to_FirstFragment);
            }
        });
        binding.datePickerActions.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if(binding.taskText.getText() == "")
                    binding.taskText.setText(dayOfMonth + "." + monthOfYear + "." + year);
                else
                    binding.taskText.setText(
                        binding.taskText.getText() + ", " + dayOfMonth + "." + monthOfYear + "." + year);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TasksViewModel.class);
        // TODO: Use the ViewModel
    }

}