/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
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
 * </p>
 */

package io.shardingsphere.transaction.listener.xa;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import io.shardingsphere.core.constant.TransactionType;
import io.shardingsphere.transaction.event.xa.XATransactionEvent;
import io.shardingsphere.transaction.listener.ShardingTransactionListenerAdapter;
import io.shardingsphere.transaction.manager.ShardingTransactionManager;
import io.shardingsphere.transaction.manager.ShardingTransactionManagerRegistry;

import java.sql.SQLException;

/**
 * XA transaction listener.
 *
 * @author zhangliang
 */
public final class XATransactionListener extends ShardingTransactionListenerAdapter<XATransactionEvent> {
    
    private final ShardingTransactionManager shardingTransactionManager = ShardingTransactionManagerRegistry.getInstance().getShardingTransactionManager(TransactionType.XA);
    
    @Subscribe
    @AllowConcurrentEvents
    @Override
    public void listen(final XATransactionEvent transactionEvent) throws SQLException {
        doTransaction(shardingTransactionManager, transactionEvent);
    }
}