package com.qunar.im.base.protocol;

import java.io.IOException;
import java.io.InputStream;

public interface HttpRequestCallback {
    void onComplete(InputStream inputStream) throws IOException;

    void onFailure(Exception exception);
}
