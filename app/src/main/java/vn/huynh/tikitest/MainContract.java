package vn.huynh.tikitest;

import java.util.List;

/**
 * Created by duong on 3/28/2019.
 *
 */

public interface MainContract {
    interface View {
        void setLoadingIndicator(boolean active);

        void displayListKeyword(List<String> list);

        void showErrorMessage(String message);
    }

    interface Presenter {
        void loadKeyword();

        void dropView();
    }

    interface Model {
        interface LoadKeywordCallback {

            void onLoaded(List<String> keywords);

            void onFailed(String message);
        }

        void loadKeywordFromUrl(LoadKeywordCallback callback);
    }
}
