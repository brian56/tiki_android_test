package vn.huynh.tikitest.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.huynh.tikitest.MainContract;
import vn.huynh.tikitest.R;
import vn.huynh.tikitest.model.KeywordModel;
import vn.huynh.tikitest.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.rv_list_keyword)
    RecyclerView rvListKeyword;
    @BindView(R.id.pb_loading)
    ProgressBar progressBar;

    private MainContract.Presenter mPresenter;
    private ArrayList<String> listKeyword;
    private RecyclerView.LayoutManager keywordLayoutManager;
    private KeywordAdapter adapter;
    private boolean onSavedState = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initRecyclerView();
        mPresenter = new MainPresenter(this, KeywordModel.getInstance());
        if (savedInstanceState != null && savedInstanceState.containsKey("keyword")) {
            onSavedState = true;
            setLoadingIndicator(false);
            displayListKeyword(savedInstanceState.getStringArrayList("keyword"));
        } else {
            onSavedState = false;
            mPresenter.loadKeyword();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                onSavedState = false;
                mPresenter.loadKeyword();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putStringArrayList("keyword", listKeyword);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.dropView();
    }

    private void initRecyclerView() {
        listKeyword = new ArrayList<>();
        rvListKeyword.setNestedScrollingEnabled(false);
        rvListKeyword.setHasFixedSize(false);
        keywordLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayout.HORIZONTAL, false);
        rvListKeyword.setLayoutManager(keywordLayoutManager);
        adapter = new KeywordAdapter(getApplicationContext(), listKeyword);
        rvListKeyword.setAdapter(adapter);
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (active) {
            rvListKeyword.setVisibility(android.view.View.GONE);
            progressBar.setVisibility(android.view.View.VISIBLE);
        } else {
            rvListKeyword.setVisibility(android.view.View.VISIBLE);
            progressBar.setVisibility(android.view.View.GONE);
        }
    }

    @Override
    public void displayListKeyword(List<String> list) {
        if (list != null) {
            if (!onSavedState)
                Toast.makeText(MainActivity.this, getResources().getString(R.string.load_successful), Toast.LENGTH_LONG).show();
            listKeyword.clear();
            listKeyword.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showErrorMessage(String message) {
        progressBar.setVisibility(android.view.View.GONE);
        rvListKeyword.setVisibility(android.view.View.GONE);
        Toast.makeText(MainActivity.this, getResources().getString(R.string.load_failed_please_try_again), Toast.LENGTH_LONG).show();
    }
}
