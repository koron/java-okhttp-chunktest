package okhttp_test;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import com.squareup.okhttp.apache.OkApacheClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.Header;
import org.apache.commons.io.IOUtils;

public class Chunk {
    final String url = "http://httpbin.org/stream-bytes/1024?chunk_size=256";

    public static void main(String[] args) {
        Chunk c = new Chunk();
        try {
            System.out.println("=== with OkHttpClient");
            c.run1();
            System.out.println("=== with OkApacheClient");
            c.run2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run1() throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request req = new Request.Builder().url(url).build();
        Response res = client.newCall(req).execute();
        ResponseBody body = res.body();
        System.out.println(String.format(
                    "contentLength=%d", body.contentLength()));
        byte[] data = body.bytes();
        System.out.println(String.format(
                    "bytes().length=%d", data.length));

        Headers headers = res.headers();
        for (int i = 0, size = headers.size(); i < size; i++) {
            String name = headers.name(i);
            String value = headers.value(i);
            System.out.println(String.format(
                        "Header#%d %s=%s", i, name, value));
        }
    }

    public void run2() throws Exception {
        OkApacheClient client = new OkApacheClient();
        HttpGet get = new HttpGet(url);
        HttpResponse resp = client.execute(get);
        HttpEntity entity = resp.getEntity();
        System.out.println(String.format(
                    "contentLength=%d", entity.getContentLength()));
        System.out.println(String.format(
                    "chunked=%b", entity.isChunked()));
        System.out.println(String.format(
                    "contentEncoding=%s", entity.getContentEncoding()));
        System.out.println(String.format(
                    "repeatable=%b", entity.isRepeatable()));
        System.out.println(String.format(
                    "streaming=%b", entity.isStreaming()));
        byte[] data = IOUtils.toByteArray(entity.getContent());
        System.out.println(String.format(
                    "bytes().length=%d", data.length));
        Header[] headers = resp.getAllHeaders();
        for (int i = 0; i < headers.length; ++i) {
            String name = headers[i].getName();
            String value = headers[i].getValue();
            System.out.println(String.format(
                        "Header#%d %s=%s", i, name, value));
        }
    }
}
