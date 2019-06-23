package com.qunar.im.base.protocol;

import android.text.TextUtils;
import com.qunar.im.base.util.LogUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUrlConnectionHandler {
    private static final String COOKIE = "Cookie";
    public static final OkHttpClient DEFAULT_CLIENT = client;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
    public static final MediaType MULTIPART_DATA = MediaType.parse("multipart/form-data; charset=utf-8");
    private static final String Q_CKEY = "q_ckey=";
    private static final OkHttpClient client = new OkHttpClient();
    private static final Map<String, HttpNamedDownload> runningCall = new HashMap();

    public static boolean checkRunning(String url, final ProgressResponseListener listener) {
        if (!runningCall.containsKey(url)) {
            return false;
        }
        HttpNamedDownload download = (HttpNamedDownload) runningCall.get(url);
        if (!(download.client == null || listener == null)) {
            download.client.networkInterceptors().clear();
            download.client.networkInterceptors().add(new Interceptor() {
                public Response intercept(Chain chain) throws IOException {
                    Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder().body(new ProgressResponseBody(originalResponse.body(), listener)).build();
                }
            });
        }
        return true;
    }

    public static void excuteDownload(final String url, ProgressResponseListener progressResponseListener, final HttpContinueDownloadCallback callback) {
        LogUtil.d("HttpUrlConnectionHandler1", "url:" + url);
        Request request = new Builder().url(url).build();
        OkHttpClient cloneClient = client;
        HttpNamedDownload httpNamedDownload = new HttpNamedDownload();
        if (progressResponseListener != null) {
            cloneClient = addProgressResponseListener(client, progressResponseListener);
            httpNamedDownload.client = cloneClient;
        }
        Call call = cloneClient.newCall(request);
        httpNamedDownload.call = call;
        runningCall.put(url, httpNamedDownload);
        call.enqueue(new Callback() {
            public void onFailure(Call request, IOException e) {
                HttpUrlConnectionHandler.runningCall.remove(url);
                callback.onFailure(e);
            }

            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (response.isSuccessful()) {
                        callback.onComplete(response.body().byteStream(), false);
                        response.body().close();
                        HttpUrlConnectionHandler.runningCall.remove(url);
                        return;
                    }
                    callback.onFailure(new Exception("faild, code is :" + response.code()));
                } finally {
                    HttpUrlConnectionHandler.runningCall.remove(url);
                }
            }
        });
    }

    public static void cancelDownload(String url) {
        HttpNamedDownload call = (HttpNamedDownload) runningCall.get(url);
        if (call != null && call.call != null) {
            runningCall.remove(url);
            if (call.call.isCanceled()) {
                call.call.cancel();
            }
        }
    }

    public static void excuteContinueDownload(final String url, ProgressResponseListener progressResponseListener, String firstByte, final HttpContinueDownloadCallback callback) {
        LogUtil.d("HttpUrlConnectionHandler2", "url:" + url);
        Request request = new Builder().url(url).addHeader("Range", "bytes=" + firstByte + "-").build();
        OkHttpClient cloneClient = client;
        HttpNamedDownload httpNamedDownload = new HttpNamedDownload();
        if (progressResponseListener != null) {
            cloneClient = addProgressResponseListener(client, progressResponseListener);
            httpNamedDownload.client = cloneClient;
        }
        Call call = cloneClient.newCall(request);
        httpNamedDownload.call = call;
        runningCall.put(url, httpNamedDownload);
        call.enqueue(new Callback() {
            public void onFailure(Call request, IOException e) {
                HttpUrlConnectionHandler.runningCall.remove(url);
                callback.onFailure(e);
            }

            public void onResponse(Call request, Response response) throws IOException {
                HttpUrlConnectionHandler.runningCall.remove(url);
                if (response.isSuccessful()) {
                    if (response.code() != 206 || TextUtils.isEmpty(response.header("Content-Range"))) {
                        callback.onComplete(response.body().byteStream(), false);
                    } else {
                        callback.onComplete(response.body().byteStream(), true);
                    }
                    response.body().close();
                    return;
                }
                callback.onFailure(new Exception("faild, code is :" + response.code()));
            }
        });
    }

    public static OkHttpClient addProgressResponseListener(OkHttpClient client, final ProgressResponseListener progressListener) {
        return client.newBuilder().addNetworkInterceptor(new Interceptor() {
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                return originalResponse.newBuilder().body(new ProgressResponseBody(originalResponse.body(), progressListener)).build();
            }
        }).build();
    }

    public static void executeGetSync(String url, HttpRequestCallback callback) {
        LogUtil.d("HttpUrlConnectionHandler3", "url:" + url);
        Map<String, String> header = new HashMap();
        Headers headers = Headers.of(header);
        header.put("Cookie", "q_ckey=" + get_qckey());
        try {
            Response response = client.newCall(new Builder().url(url).headers(headers).build()).execute();
            if (response.isSuccessful()) {
                callback.onComplete(response.body().byteStream());
            } else {
                callback.onFailure(new Exception("status is not 200"));
            }
        } catch (IOException e) {
            e.printStackTrace();
            callback.onFailure(e);
        }
    }

    public static InputStream excuteGetSync(String url) {
        LogUtil.d("HttpUrlConnectionHandler3", "url:" + url);
        Map<String, String> header = new HashMap();
        Headers headers = Headers.of(header);
        header.put("Cookie", "q_ckey=" + get_qckey());
        try {
            Response response = client.newCall(new Builder().url(url).headers(headers).build()).execute();
            if (response.isSuccessful()) {
                return response.body().byteStream();
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void executeGet(String url, HttpRequestCallback callback) {
        LogUtil.d("HttpUrlConnectionHandler4", "url:" + url);
        executeGet(url, null, callback);
    }

    public static void executeGet(String url, Map<String, String> header, final HttpRequestCallback callback) {
        LogUtil.d("HttpUrlConnectionHandler5", "url:" + url);
        if (header != null && header.containsKey("Cookie")) {
            String cookie = (String) header.get("Cookie");
            if (TextUtils.isEmpty(cookie)) {
                header.put("Cookie", "q_ckey=" + get_qckey());
            } else if (!cookie.contains("q_ckey=")) {
                header.put("Cookie", cookie + ";" + "q_ckey=" + get_qckey());
            }
        } else if (header == null) {
            header = new HashMap();
            header.put("Cookie", "q_ckey=" + get_qckey());
        } else if (!header.containsKey("Cookie")) {
            header.put("Cookie", "q_ckey=" + get_qckey());
        }
        client.newCall(new Builder().url(url).headers(Headers.of(header)).build()).enqueue(new Callback() {
            public void onFailure(Call request, IOException e) {
                callback.onFailure(e);
            }

            public void onResponse(Call request, Response response) throws IOException {
                if (response.isSuccessful()) {
                    callback.onComplete(response.body().byteStream());
                    response.body().close();
                    return;
                }
                callback.onFailure(new Exception("faild, code is :" + response.code()));
            }
        });
    }

    public static void executePostForm(String url, Map<String, String> params, final HttpRequestCallback callback) {
        LogUtil.d("HttpUrlConnectionHandler6", "url:" + url);
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : params.keySet()) {
            builder.add(key, (String) params.get(key));
            LogUtil.d("HttpUrlConnectionHandler7", key + ":" + ((String) params.get(key)));
        }
        RequestBody body = builder.build();
        Map<String, String> header = new HashMap();
        Headers headers = Headers.of(header);
        header.put("Cookie", "q_ckey=" + get_qckey());
        client.newCall(new Builder().url(url).post(body).headers(headers).build()).enqueue(new Callback() {
            public void onFailure(Call request, IOException e) {
                callback.onFailure(e);
            }

            public void onResponse(Call request, Response response) throws IOException {
                if (response.isSuccessful()) {
                    callback.onComplete(response.body().byteStream());
                    response.body().close();
                    return;
                }
                callback.onFailure(new Exception("faild, code is :" + response.code()));
            }
        });
    }

    public static void executePostJson(String url, Map<String, String> header, String json, final HttpRequestCallback callback) {
        LogUtil.d("HttpUrlConnectionHandler8", "url:" + url);
        RequestBody body = RequestBody.create(JSON, json);
        if (header != null && header.containsKey("Cookie")) {
            String cookie = (String) header.get("Cookie");
            if (TextUtils.isEmpty(cookie)) {
                header.put("Cookie", "q_ckey=" + get_qckey());
            } else if (!cookie.contains("q_ckey=")) {
                header.put("Cookie", cookie + ";" + "q_ckey=" + get_qckey());
            }
        } else if (header == null) {
            header = new HashMap();
            header.put("Cookie", "q_ckey=" + get_qckey());
        } else if (!header.containsKey("Cookie")) {
            header.put("Cookie", "q_ckey=" + get_qckey());
        }
        client.newCall(new Builder().url(url).post(body).headers(Headers.of(header)).build()).enqueue(new Callback() {
            public void onFailure(Call request, IOException e) {
                callback.onFailure(e);
            }

            public void onResponse(Call request, Response response) throws IOException {
                if (response.isSuccessful()) {
                    callback.onComplete(response.body().byteStream());
                    response.body().close();
                    return;
                }
                callback.onFailure(new Exception("faild, code is :" + response.code()));
            }
        });
    }

    public static void executePostJson(String url, String json, HttpRequestCallback callback) {
        LogUtil.d("HttpUrlConnectionHandler9", "url:" + url);
        executePostJson(url, null, json, callback);
    }

    public static void executePostString(String url, String raw, final HttpRequestCallback callback) {
        LogUtil.d("HttpUrlConnectionHandler10", "url:" + url);
        RequestBody body = RequestBody.create(MEDIA_TYPE_MARKDOWN, raw);
        Map<String, String> header = new HashMap();
        Headers headers = Headers.of(header);
        header.put("Cookie", "q_ckey=" + get_qckey());
        client.newCall(new Builder().url(url).post(body).headers(headers).build()).enqueue(new Callback() {
            public void onFailure(Call request, IOException e) {
                callback.onFailure(e);
            }

            public void onResponse(Call request, Response response) throws IOException {
                if (response.isSuccessful()) {
                    callback.onComplete(response.body().byteStream());
                    response.body().close();
                    return;
                }
                callback.onFailure(new Exception("faild, code is :" + response.code()));
            }
        });
    }

    public static void executeUpload(String url, File file, String key, String fileName, ProgressRequestListener listener, Map<String, String> params, final HttpRequestCallback callback) {
        LogUtil.d("HttpUrlConnectionHandler11", "url:" + url);
        if (url == null) {
            callback.onFailure(new NullPointerException());
        }
        LogUtil.d("HttpUrlConnectionHandler12", "url:" + url);
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart(key, fileName, RequestBody.create(MULTIPART_DATA, file));
        if (params != null && params.size() > 0) {
            for (String pKey : params.keySet()) {
                builder.addFormDataPart(pKey, (String) params.get(pKey));
            }
        }
        RequestBody body = builder.build();
        if (listener != null) {
            body = new ProgressRequestBody(body, listener);
        }
        client.newCall(new Builder().url(url).post(body).build()).enqueue(new Callback() {
            public void onFailure(Call request, IOException e) {
                callback.onFailure(e);
            }

            public void onResponse(Call request, Response response) throws IOException {
                if (response.isSuccessful()) {
                    callback.onComplete(response.body().byteStream());
                    response.body().close();
                    return;
                }
                callback.onFailure(new Exception("faild, code is :" + response.code()));
            }
        });
    }

    public static void executePostJsonSync(String url, String json, HttpRequestCallback callback) {
        LogUtil.d("HttpUrlConnectionHandler13", "url:" + url);
        RequestBody body = RequestBody.create(JSON, json);
        Map<String, String> header = new HashMap();
        Headers headers = Headers.of(header);
        header.put("Cookie", "q_ckey=" + get_qckey());
        try {
            Response response = client.newCall(new Builder().url(url).post(body).headers(headers).build()).execute();
            if (response.isSuccessful()) {
                callback.onComplete(response.body().byteStream());
                response.body().close();
                return;
            }
            callback.onFailure(new Exception("faild, code is :" + response.code()));
        } catch (IOException e) {
            callback.onFailure(e);
        }
    }

    private static String get_qckey() {
        try {
            Class<?> clazz = Class.forName("com.qunar.im.base.protocol.Protocol");
            Object result = clazz.getMethod("getCKEY", new Class[0]).invoke(clazz.newInstance(), new Object[0]);
            if (result != null) {
                return result.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
