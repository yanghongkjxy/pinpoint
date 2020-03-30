/*
 * Copyright 2019 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.navercorp.pinpoint.profiler.context.grpc;

import com.google.protobuf.InvalidProtocolBufferException;
import com.navercorp.pinpoint.grpc.trace.PResult;
import com.navercorp.pinpoint.profiler.context.thrift.MessageConverter;
import com.navercorp.pinpoint.profiler.sender.ResultResponse;
import com.navercorp.pinpoint.rpc.ResponseMessage;

/**
 * @author jaehong.kim
 */
public class GrpcMessageToResultConverter implements MessageConverter<ResultResponse> {
    @Override
    public ResultResponse toMessage(Object object) {
        if (object instanceof ResponseMessage) {
            final ResponseMessage responseMessage = (ResponseMessage) object;
            final byte[] byteMessage = responseMessage.getMessage();
            try {
                final PResult pResult = PResult.parseFrom(byteMessage);
                return new ResultResponse() {
                    @Override
                    public boolean isSuccess() {
                        return pResult.getSuccess();
                    }

                    @Override
                    public String getMessage() {
                        return pResult.getMessage();
                    }
                };
            } catch (InvalidProtocolBufferException e) {
                throw new IllegalArgumentException("invalid message data. response message=" + responseMessage, e);
            }
        }
        return null;
    }
}
