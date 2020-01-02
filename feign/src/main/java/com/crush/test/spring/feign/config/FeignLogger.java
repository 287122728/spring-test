/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.crush.test.spring.feign.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.util.Iterator;

import feign.Logger;
import feign.Request;
import feign.Response;
import feign.Util;

/**
 * Description TODO
 *
 * @author Apple
 * @version $Id: FeignLogger.java, v 0.1
 * @create 2019年11月27日 14:52 crush_lee Exp $
 */
public class FeignLogger extends Logger {
    private Log                        logger;
    private ThreadLocal<StringBuilder> logInfo;

    public FeignLogger() {
        this(Logger.class);
    }

    public FeignLogger(Class<?> clazz) {
        this(LogFactory.getLog(clazz));
    }

    public FeignLogger(String name) {
        this(LogFactory.getLog(name));
    }

    public FeignLogger(Log logger) {
        this.logInfo = new ThreadLocal();
        this.logger = logger;
    }

    @Override
    public void log(String configKey, String format, Object... args) {
        this.logger.info(String.format(methodTag(configKey) + format, args));
    }

    @Override
    public void logRequest(String configKey, Level logLevel, Request request) {
        this.logInfo.remove();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%s---> %s %s HTTP/1.1", methodTag(configKey), request.method(), request.url()));
        stringBuilder.append(",---> request header[");
        Iterator var5 = request.headers().keySet().iterator();

        String bodyText;
        while (var5.hasNext()) {
            bodyText = (String) var5.next();
            Iterator var7 = Util.valuesOrEmpty(request.headers(), bodyText).iterator();

            while (var7.hasNext()) {
                String value = (String) var7.next();
                stringBuilder.append(String.format("%s: %s", bodyText, value)).append(",");
            }
        }

        stringBuilder.append("]");
        stringBuilder.append(",---> request body[");
        int bodyLength = 0;
        if (request.body() != null) {
            bodyLength = request.body().length;
            bodyText = request.charset() != null ? new String(request.body(), request.charset()) : null;
            stringBuilder.append(bodyText != null ? bodyText : "Binary data");
        }

        stringBuilder.append(String.format("],length[%s]", bodyLength));
        this.logInfo.set(stringBuilder);
    }

    @Override
    public Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime) throws IOException {
        StringBuilder stringBuilder = (StringBuilder) this.logInfo.get();
        stringBuilder.append(",---> response body[");
        int bodyLength = 0;
        if (response.body() != null) {
            byte[] bodyData = Util.toByteArray(response.body().asInputStream());
            bodyLength = bodyData.length;
            if (bodyLength > 0) {
                stringBuilder.append(String.format("%s]", this.decodeOrDefault(bodyData, Util.UTF_8, "Binary data")));
            }

            stringBuilder.append("]");
            this.log(stringBuilder, elapsedTime, response, bodyLength);
            return response.toBuilder().body(bodyData).build();
        } else {
            this.log(stringBuilder, elapsedTime, response, bodyLength);
            return response;
        }
    }

    public IOException logIOException(String configKey, Level logLevel, IOException ioe, long elapsedTime) {
        StringBuilder stringBuilder = (StringBuilder) this.logInfo.get();
        stringBuilder.append("<--- ERROR ").append(ioe.getClass().getSimpleName()).append(": ").append(ioe.getMessage()).append(" (")
                .append(elapsedTime).append("ms)");
        this.logger.error(stringBuilder.toString());
        this.logInfo.remove();
        return ioe;
    }

    private void log(StringBuilder str, long elapsedTime, Response response, int length) {
        String reason = response.reason() != null ? " " + response.reason() : "";
        str.append(String.format("<--- HTTP/1.1 %s%s (%sms)(%s-byte body)", response.status(), reason, elapsedTime, length));
        this.logger.info(str.toString());
        this.logInfo.remove();
    }

    private String decodeOrDefault(byte[] data, Charset charset, String defaultValue) {
        if (data == null) {
            return defaultValue;
        } else {
            try {
                return charset.newDecoder().decode(ByteBuffer.wrap(data)).toString();
            } catch ( CharacterCodingException var5 ) {
                return defaultValue;
            }
        }
    }
}