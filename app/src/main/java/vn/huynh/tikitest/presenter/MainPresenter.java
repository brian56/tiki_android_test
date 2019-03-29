package vn.huynh.tikitest.presenter;

import java.util.List;

import vn.huynh.tikitest.MainContract;

/**
 * Created by duong on 3/28/2019.
 *
 */

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private MainContract.Model model;

    public MainPresenter(MainContract.View view, MainContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void dropView() {
        this.view = null;
    }

    @Override
    public void loadKeyword() {
        view.setLoadingIndicator(true);
        model.loadKeywordFromUrl(new MainContract.Model.LoadKeywordCallback() {
            @Override
            public void onLoaded(List<String> keywords) {
                view.setLoadingIndicator(false);
                view.displayListKeyword(keywords);
            }

            @Override
            public void onFailed(String message) {
                view.setLoadingIndicator(false);
                view.showErrorMessage(message);
            }
        });
    }
}
