package ubiquitous.main.ui.addToMenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import ubiquitous.main.R;
import ubiquitous.main.databinding.FragmentAddToMenuBinding;
import ubiquitous.main.databinding.FragmentBookmarkBinding;
import ubiquitous.main.databinding.FragmentFavoriteBinding;

public class addToMenuFragment extends Fragment {

    private FragmentAddToMenuBinding binding;
    private TextView addToMenuTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addToMenuViewModel addToMenuViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(addToMenuViewModel.class);

        binding = FragmentAddToMenuBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        addToMenuTextView = binding.textAddToMenu;
        addToMenuViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String text) {
                addToMenuTextView.setText(text);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}