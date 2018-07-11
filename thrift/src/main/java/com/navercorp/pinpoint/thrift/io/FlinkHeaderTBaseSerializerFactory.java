/*
 * Copyright 2018 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.navercorp.pinpoint.thrift.io;

import com.navercorp.pinpoint.io.util.TypeLocator;
import org.apache.thrift.TBase;

/**
 * @author minwoo.jung
 */
public class FlinkHeaderTBaseSerializerFactory implements SerializerFactory<HeaderTBaseSerializer> {

    private final TypeLocator<TBase<?, ?>> tBaseLocator;
    private final HeaderTBaseSerializerFactory headerTBaseSerializerFactory;

    public FlinkHeaderTBaseSerializerFactory(TypeLocator<TBase<?, ?>> flinkTBaseLocator) {
        if (flinkTBaseLocator == null) {
            throw new NullPointerException("flinkTBaseLocator must not be null.");
        }
        tBaseLocator = flinkTBaseLocator;

        headerTBaseSerializerFactory = new HeaderTBaseSerializerFactory(tBaseLocator);
    }

    @Override
    public HeaderTBaseSerializer createSerializer() {
        return headerTBaseSerializerFactory.createSerializer();
    }

    @Override
    public boolean isSupport(Object target) {
        if (target instanceof TBase<?, ?>) {
            return tBaseLocator.isSupport((Class<? extends TBase<?, ?>>) target.getClass());
        }

        return false;
    }

}
