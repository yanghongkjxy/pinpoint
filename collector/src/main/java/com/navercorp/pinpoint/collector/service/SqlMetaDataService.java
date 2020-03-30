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

package com.navercorp.pinpoint.collector.service;

import com.navercorp.pinpoint.collector.dao.SqlMetaDataDao;
import com.navercorp.pinpoint.common.server.bo.SqlMetaDataBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SqlMetaDataService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final SqlMetaDataDao sqlMetaDataDao;

    public SqlMetaDataService(SqlMetaDataDao sqlMetaDataDao) {
        this.sqlMetaDataDao = Objects.requireNonNull(sqlMetaDataDao, "sqlMetaDataDao");
    }

    public void insert(final SqlMetaDataBo sqlMetaDataBo) {
        this.sqlMetaDataDao.insert(sqlMetaDataBo);
    }
}
