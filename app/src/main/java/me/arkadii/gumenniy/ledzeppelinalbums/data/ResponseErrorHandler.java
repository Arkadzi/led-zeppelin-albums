package me.arkadii.gumenniy.ledzeppelinalbums.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.net.SocketTimeoutException;

import me.arkadii.gumenniy.ledzeppelinalbums.R;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.HttpException;

public class ResponseErrorHandler {
    private Gson gson = new Gson();
    private Context context;

    public ResponseErrorHandler(Context context) {
        this.context = context;
    }

    public String handleError(Throwable t) {
        String message = t.getMessage();
        if (t instanceof HttpException) {
            ResponseBody body = ((HttpException) t).response().errorBody();
            TypeAdapter<Error> adapter = gson.getAdapter(Error.class);
            try {
                Error error = adapter.fromJson(body.string());
                return error.getMessage();
            } catch (Exception e) {}
        }
        if (t instanceof SocketTimeoutException) {
            return context.getString(R.string.errorCheckNetwork);
        } else if (message != null) {
            if (message.startsWith("Unable to resolve host") || message.startsWith("Failed to connect")) {
                return context.getString(R.string.errorCheckNetwork);
            }
        }
        return message;
    }

    public String getMessageFromResource(int stringId) {
        return context.getString(stringId);
    }
}
