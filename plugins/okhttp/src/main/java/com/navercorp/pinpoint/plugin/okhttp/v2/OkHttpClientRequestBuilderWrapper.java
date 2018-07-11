/*
 * Copyright 2018 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.navercorp.pinpoint.plugin.okhttp.v2;

import com.navercorp.pinpoint.bootstrap.logging.PLogger;
import com.navercorp.pinpoint.bootstrap.logging.PLoggerFactory;
import com.navercorp.pinpoint.bootstrap.plugin.request.ClientRequestWrapper;
import com.navercorp.pinpoint.common.util.Assert;
import com.squareup.okhttp.Request;

/**
 * @author jaehong.kim
 */
public class OkHttpClientRequestBuilderWrapper implements ClientRequestWrapper {
    private final PLogger logger = PLoggerFactory.getLogger(this.getClass());
    private final boolean isDebug = logger.isDebugEnabled();

    private final Request.Builder builder;
    private final String host;

    // Only RequestBuilder class
    public OkHttpClientRequestBuilderWrapper(final Request.Builder builder, final String host) {
        this.builder = Assert.requireNonNull(builder, "builder must not be null");
        this.host = host;
    }

    @Override
    public void setHeader(final String name, final String value) {
        builder.header(name, value);
        if (isDebug) {
            logger.debug("Set header {}={}", name, value);
        }
    }

    @Override
    public String getHost() {
        return this.host;
    }

    @Override
    public String getDestinationId() {
        throw new UnsupportedOperationException("Must be used only in the AbstractRequestBuilderBuildMethodInterceptor class");
    }

    @Override
    public String getUrl() {
        throw new UnsupportedOperationException("Must be used only in the AbstractRequestBuilderBuildMethodInterceptor class");
    }

    @Override
    public String getEntityValue() {
        throw new UnsupportedOperationException("Must be used only in the AbstractRequestBuilderBuildMethodInterceptor class");
    }

    @Override
    public String getCookieValue() {
        throw new UnsupportedOperationException("Must be used only in the AbstractRequestBuilderBuildMethodInterceptor class");
    }
}