package com.qunar.im.base.protocol;

import java.io.InputStream;

public interface HttpContinueDownloadCallback {
    void onComplete(InputStream inputStream, boolean z);

    void onFailure(Exception exception);
}
