package vn.huynh.tikitest.view;

import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import vn.huynh.tikitest.BuildConfig;
import vn.huynh.tikitest.R;

/**
 * Created by duong on 3/29/2019.
 *
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk=21)
public class MainActivityTest {

    private MainActivity mainActivity;

    @Before
    public void setUp() throws Exception
    {
        mainActivity = Robolectric.buildActivity( MainActivity.class )
                .create()
                .visible()
                .get();
    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        Assert.assertNotNull(mainActivity);
        ProgressBar progressBar = mainActivity.findViewById(R.id.pb_loading);
        Assert.assertNotNull(progressBar);
        RecyclerView rvListKeyword = mainActivity.findViewById(R.id.rv_list_keyword);
        Assert.assertNotNull(rvListKeyword);
        ActionMenuItemView menuItem = mainActivity.findViewById(R.id.action_refresh);
        Assert.assertNotNull(menuItem);
    }
}
