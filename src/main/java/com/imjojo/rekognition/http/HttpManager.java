package com.imjojo.rekognition.http;

import com.imjojo.rekognition.http.model.HttpParameter;
import com.imjojo.rekognition.http.model.RekognitionAPIException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.log4j.Logger;

/**
 *
 * @author Zhibo Wei (uglytroll@dongxuexidu.com)
 */
public class HttpManager {

    private static HttpManager instance = null;
    private static final Logger logger = Logger.getLogger(HttpManager.class.getName());
    private static final int MAX_CONNECTION = 150;
    private static final int CONNECTION_TIMEOUT_IN_MS = 5000;
    private static final int SOCKET_TIMEOUT_IN_MS = 5000;
    private static final int MAX_CONNECTION_PER_ROUTE = 50;
    private static final int HTTP_OK_RESPONSE_CODE = 200;
    private static final String DEFAULT_CHARSET = "UTF-8";
    private final RequestConfig requestConfig;
    private final CloseableHttpClient httpClient;

    public static synchronized HttpManager getInstance() {
        if (instance == null) {
            instance = new HttpManager();
        }
        return instance;
    }

    private HttpManager() {
        ConnectionKeepAliveStrategy keepAliveStrategy = getKeepAliveStrategy(5 * 1000);
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(MAX_CONNECTION);
        cm.setDefaultMaxPerRoute(MAX_CONNECTION_PER_ROUTE);
        httpClient = HttpClients.custom().
                setKeepAliveStrategy(keepAliveStrategy).
                setConnectionManager(cm).build();

        requestConfig = RequestConfig.custom().
                setConnectionRequestTimeout(CONNECTION_TIMEOUT_IN_MS).
                setConnectTimeout(CONNECTION_TIMEOUT_IN_MS).
                setSocketTimeout(SOCKET_TIMEOUT_IN_MS).build();
    }

    private ConnectionKeepAliveStrategy getKeepAliveStrategy(final long defaultVal) {
        return new ConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                while (it.hasNext()) {
                    HeaderElement he = it.nextElement();
                    String param = he.getName();
                    String value = he.getValue();
                    if (value != null && param.equalsIgnoreCase("timeout")) {
                        return Long.parseLong(value) * 1000;
                    }
                }
                return defaultVal;
            }
        };
    }

    public String get(String url, Collection<HttpParameter> params) throws RekognitionAPIException {
        if (params != null && !params.isEmpty()) {
            String encodedParams = encodeParameters(params);
            url = url + "?" + encodedParams;
        }
        HttpGet getMethod = new HttpGet(url);
        getMethod.setConfig(requestConfig);
        return httpRequest(getMethod);
    }

    public String get(String url) throws RekognitionAPIException {
        HttpGet getMethod = new HttpGet(url);
        getMethod.setConfig(requestConfig);
        return httpRequest(getMethod);
    }

    public String post(String url, Collection<HttpParameter> params) throws RekognitionAPIException {
        HttpPost postMethod = new HttpPost(url);
        postMethod.setConfig(requestConfig);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        for (HttpParameter postParam : params) {
            nvps.add(new BasicNameValuePair(postParam.getName(), postParam.getValue()));
        }
        postMethod.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
        return httpRequest(postMethod);
    }
    
    private String httpRequest(HttpUriRequest request) throws RekognitionAPIException {
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            try {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == HTTP_OK_RESPONSE_CODE) {
                    HttpEntity entity = response.getEntity();
                    byte[] result = IOUtils.toByteArray(entity.getContent());
                    if (result != null && result.length > 0) {
                        return IOUtils.toString(result, DEFAULT_CHARSET);
                    } else {
                        return null;
                    }
                } else {
                    throw new RekognitionAPIException("Un-acceptable response code from server", statusCode);
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException ex) {
            logger.error(null, ex);
            throw new RekognitionAPIException(ex);
        } catch (IOException ex) {
            logger.error("IOException happened in http request", ex);
            throw new RekognitionAPIException(ex);
        }
    }

    private String encodeParameters(Collection<HttpParameter> params) {
        StringBuilder buf = new StringBuilder();
        int j = 0;
        for (HttpParameter nvp : params) {
            if (j != 0) {
                buf.append("&");
            }
            j++;
            try {
                buf.append(URLEncoder.encode(nvp.getName(), DEFAULT_CHARSET)).append("=").append(URLEncoder.encode(nvp.getValue(), DEFAULT_CHARSET));
            } catch (UnsupportedEncodingException ex) {
                //System.out.println("Shouldn't come this far");
            }
        }
        return buf.toString();
    }
}
