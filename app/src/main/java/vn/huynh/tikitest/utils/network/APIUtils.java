package vn.huynh.tikitest.utils.network;

/**
 * Created by duong on 3/28/2019.
 */

public class APIUtils {
    public static final String BASE_URL = "https://raw.githubusercontent.com";

    public static KeyWordService getKeyWordService() {
        return RetrofitClient.getClient(BASE_URL).create(KeyWordService.class);
    }
}
