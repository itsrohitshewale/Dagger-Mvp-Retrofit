package datasource.remote.respnse;

import java.util.ArrayList;

import model.Photo;

public class PhotosResponse extends GeneralResponse {
    private ArrayList<Photo> results;

    public PhotosResponse(String result, String message) {
        super(result, message);
    }

    public ArrayList<Photo> getResults() {
        return results;
    }

    public void setResults(ArrayList<Photo> results) {
        this.results = results;
    }

}
