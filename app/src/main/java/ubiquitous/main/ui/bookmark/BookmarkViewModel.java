package ubiquitous.main.ui.bookmark;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BookmarkViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BookmarkViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("BookMarked");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
