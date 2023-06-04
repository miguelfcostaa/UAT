package ubiquitous.main.ui.bookmark;

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
import ubiquitous.main.databinding.FragmentBookmarkBinding;
import ubiquitous.main.databinding.FragmentFavoriteBinding;

public class BookmarkFragment extends Fragment {

    private FragmentBookmarkBinding binding;
    private TextView bookmarkTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BookmarkViewModel bookmarkViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(BookmarkViewModel.class);

        binding = FragmentBookmarkBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        bookmarkTextView = binding.textBookmark;
        bookmarkViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String text) {
                bookmarkTextView.setText(text);
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