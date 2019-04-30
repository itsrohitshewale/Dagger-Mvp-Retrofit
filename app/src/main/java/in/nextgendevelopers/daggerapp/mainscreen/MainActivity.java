package in.nextgendevelopers.daggerapp.mainscreen;

import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.inject.Inject;

import adapter.OnCellClickListener;
import adapter.PhotosRecyclerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import in.nextgendevelopers.daggerapp.R;
import model.Photo;
import utils.GlideHandler;
import utils.MessageHandler;

public class MainActivity extends DaggerAppCompatActivity implements MainActivityContract.View, OnCellClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_to_refresh)
    SwipeRefreshLayout swipeToRefresh;
    @BindView(R.id.message_container)
    RelativeLayout messageContainer;
    @BindView(R.id.error_message)
    TextView errorMessage;
    @Inject
    MainActivityContract.Presenter mPresenter;
    @Inject
    GlideHandler glideHandler;
    private PhotosRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mPresenter.setActivity(this);
        adapter = new PhotosRecyclerAdapter(new ArrayList<Photo>(0), this, glideHandler);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(llm);

        initViews();

    }

    void initViews() {
        //noinspection ConstantConditions
        swipeToRefresh.setColorSchemeColors(
                ContextCompat.getColor(this, R.color.colorPrimaryDark),
                ContextCompat.getColor(this, R.color.colorPrimary),
                ContextCompat.getColor(this, R.color.colorAccent)
        );

        swipeToRefresh.setOnRefreshListener(() -> {
            swipeToRefresh.setRefreshing(true);
            mPresenter.setBasicInit(true);
            mPresenter.loadPhotos();
        });

//        if (savedInstanceState == null) {
//            swipeToRefresh.setRefreshing(true);
//            mPresenter.setAdvancedInit(MoviesFilter.MOST_POPULAR, true, true);
//        }
    }
    private void showMessageError() {
        messageContainer.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showPhotos(ArrayList<Photo> photos, boolean setAdapter) {
        if (swipeToRefresh != null)
            swipeToRefresh.setRefreshing(false);
        recyclerView.setVisibility(View.VISIBLE);
        messageContainer.setVisibility(View.GONE);
        adapter.setPhotos(photos);
//        if (setAdapter)
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void showNoPhotos() {
        if (swipeToRefresh != null)
            swipeToRefresh.setRefreshing(false);
        errorMessage.setText("No photos available!");
        showMessageError();
    }

    @Override
    public void showServerError(String error) {
        if (swipeToRefresh != null)
            swipeToRefresh.setRefreshing(false);
        MessageHandler.alertDialog(this, error, null);

    }

    @Override
    public void showNoInternetConnection() {
        if (swipeToRefresh != null)
            swipeToRefresh.setRefreshing(false);
        Toast.makeText(this, "No internet connection!", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onCellClick(int position) {

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getView(this);
        mPresenter.setBasicInit(true);
        mPresenter.loadPhotos();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.dropView();  //prevent leaking activity in
        // case presenter is orchestrating a long running task
    }
}
