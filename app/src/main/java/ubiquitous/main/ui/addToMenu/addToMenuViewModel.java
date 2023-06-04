package ubiquitous.main.ui.addToMenu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class addToMenuViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public addToMenuViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Add To Menu");
    }

    public LiveData<String> getText() {
        return mText;
    }


}
