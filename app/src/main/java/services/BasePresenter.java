package services;


public interface BasePresenter<T> {
    void getView(T view);
    void dropView();
}
