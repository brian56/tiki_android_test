package vn.huynh.tikitest.utils.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by duong on 3/28/2019.
 */

public interface KeyWordService {
    @GET("/tikivn/android-home-test/v2/keywords.json")
    Call<List<String>> getKeyword();
}
