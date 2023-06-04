package ubiquitous.main.ui.favorite;

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
import ubiquitous.main.databinding.FragmentFavoriteBinding;

public class FavoriteFragment extends Fragment {

    private FragmentFavoriteBinding binding;
    private TextView favoriteTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FavoriteViewModel fragmentViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(FavoriteViewModel.class);

        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        favoriteTextView = binding.textFavorite;
        fragmentViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String text) {
                favoriteTextView.setText(text);
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