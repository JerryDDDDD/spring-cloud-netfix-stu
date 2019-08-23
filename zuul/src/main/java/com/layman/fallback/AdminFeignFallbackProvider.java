package com.layman.fallback;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AdminFeignFallbackProvider
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/8/23 21:30
 * @Version 3.0
 **/
@Component
public class AdminFeignFallbackProvider implements FallbackProvider {

    @Override
    public String getRoute() {
        // return 的是Service id,如果所有调用都支持回退,则return "*"或者 return null
        return "customer-feign";
    }

    /**    
     * @Author 叶泽文
     * @Description 服务请求失败时, 则返回给指定的信息给调用者
     * @Date 21:33 2019/8/23
     * @Param [route, cause]
     * @return org.springframework.http.client.ClientHttpResponse
     **/
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {

        return new ClientHttpResponse() {
            /**    
             * @Author 叶泽文
             * @Description 网关向api请求失败了,但是消费者客户端想网关发起的请求是成功的,
             *               不应该吧api的404,500等问题抛给客户端  网关和api服务集群对也客户端来说是黑盒
             * @Date 21:34 2019/8/23
             * @Param []
             * @return org.springframework.http.HttpStatus
             **/
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.OK.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.OK.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> map = new HashMap<>();
                map.put("status", 200);
                map.put("message", "无法链接,请检查您的网络");
                return new ByteArrayInputStream(objectMapper.writeValueAsString(map).getBytes("UTF-8"));
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                // 设置编码格式与getBody中的内容编码一致
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return headers;
            }
        };
    }
}
