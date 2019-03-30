package vn.huynh.tikitest;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vn.huynh.tikitest.model.KeywordModel;
import vn.huynh.tikitest.presenter.MainPresenter;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by duong on 3/29/2019.
 *
 */
@RunWith(AndroidJUnit4.class)
public class MainPresenterTest {
    @Mock
    private MainContract.View view;
    @Mock
    private KeywordModel keywordModel;

    private MainPresenter presenter;

    @Captor
    private ArgumentCaptor<MainContract.Model.LoadKeywordCallback> argumentCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        presenter = new MainPresenter(view, keywordModel);

    }

    @Test
    public void loadKeywordSuccess() throws Exception {
        presenter.loadKeyword();
        verify(keywordModel, times(1)).loadKeywordFromUrl(argumentCaptor.capture());
        argumentCaptor.getValue().onLoaded(getListKeyword());
        verify(view, times(1)).setLoadingIndicator(false);
        ArgumentCaptor<List> entityArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(view).displayListKeyword(entityArgumentCaptor.capture());

        Assert.assertTrue(entityArgumentCaptor.getValue().size() == 20);
    }

    @Test
    public void loadKeywordFail() throws Exception {
        presenter.loadKeyword();
        verify(keywordModel, times(1)).loadKeywordFromUrl(argumentCaptor.capture());
        argumentCaptor.getValue().onFailed("Error");
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(view, times(1)).setLoadingIndicator(false);
        verify(view).showErrorMessage(argumentCaptor.capture());
    }

    private ArrayList<String> getListKeyword() {
        return new ArrayList<String>(
                Arrays.asList("xiaomi",
                        "bitis hunter",
                        "bts",
                        "balo",
                        "bitis hunter x",
                        "tai nghe",
                        "harry potter",
                        "anker",
                        "iphone",
                        "balo nữ",
                        "nguyễn nhật ánh",
                        "đắc nhân tâm",
                        "ipad",
                        "senka",
                        "tai nghe bluetooth",
                        "son",
                        "maybelline",
                        "laneige",
                        "kem chống nắng",
                        "anh chính là thanh xuân của em"));
    }
}
