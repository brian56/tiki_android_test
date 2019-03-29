package vn.huynh.tikitest.model;

import android.util.Log;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import vn.huynh.tikitest.MainContract;
import vn.huynh.tikitest.utils.FormatDataUtils;
import vn.huynh.tikitest.utils.network.APIUtils;
import vn.huynh.tikitest.view.MainActivity;

/**
 * Created by duong on 3/28/2019.
 *
 */

public class KeywordModel implements MainContract.Model {
    private static KeywordModel INSTANCE = null;

    public KeywordModel() {
    }

    public static KeywordModel getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new KeywordModel();
        }
        return INSTANCE;
    }

    @Override
    public void loadKeywordFromUrl(final LoadKeywordCallback callback) {
        Call<List<String>> getKeyWordRequest = APIUtils.getKeyWordService().getKeyword();
        getKeyWordRequest.enqueue(new retrofit2.Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.body() != null) {
                    if (!response.isSuccessful()) {
                        //request failed
                        callback.onFailed("Load failed");
                    } else {
                        //request successful
                        final List<String> list = new ArrayList<>();
                        for (int i = 0; i < response.body().size(); i++) {
                            list.add(FormatDataUtils.formatKeyword(response.body().get(i)));
                        }
                        Log.d(MainActivity.class.getSimpleName(), list.size() + "");
                        callback.onLoaded(list);
                    }
                } else {
                    callback.onFailed("Empty data");
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                t.printStackTrace();
                if (t instanceof SocketTimeoutException) {
                    callback.onFailed("Time out. Please try again.");
                } else {
                    callback.onFailed("An error occurred. Please try again.");
                }
            }
        });
    }
}
