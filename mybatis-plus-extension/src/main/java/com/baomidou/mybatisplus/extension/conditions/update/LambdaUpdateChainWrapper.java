/*
 * Copyright (c) 2011-2022, baomidou (jobob@qq.com).
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
package com.baomidou.mybatisplus.extension.conditions.update;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.Update;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.conditions.AbstractChainWrapper;

/**
 * Lambda Update Chain Wrapper
 *
 * @author miemie
 * @since 2018-12-19
 */
@SuppressWarnings({"serial"})
public class LambdaUpdateChainWrapper<T> extends AbstractChainWrapper<T, SFunction<T, ?>, LambdaUpdateChainWrapper<T>, LambdaUpdateWrapper<T>>
    implements ChainUpdate<T>, Update<LambdaUpdateChainWrapper<T>, SFunction<T, ?>> {

    private final BaseMapper<T> baseMapper;
    private final Class<T> entityClass;

    public LambdaUpdateChainWrapper(BaseMapper<T> baseMapper) {
        super();
        this.baseMapper = baseMapper;
        this.entityClass = null;
        super.wrapperChildren = new LambdaUpdateWrapper<>();
    }

    public LambdaUpdateChainWrapper(Class<T> entityClass) {
        super();
        this.baseMapper = null;
        this.entityClass = entityClass;
        super.wrapperChildren = new LambdaUpdateWrapper<>();
    }

    @Override
    public LambdaUpdateChainWrapper<T> set(boolean condition, SFunction<T, ?> column, Object val, String mapping) {
        wrapperChildren.set(condition, column, val, mapping);
        return typedThis;
    }

    @Override
    public LambdaUpdateChainWrapper<T> setSql(boolean condition, String sql) {
        wrapperChildren.setSql(condition, sql);
        return typedThis;
    }

    @Override
    public String getSqlSet() {
        throw ExceptionUtils.mpe("can not use this method for \"%s\"", "getSqlSet");
    }

    @Override
    public BaseMapper<T> getBaseMapper() {
        return baseMapper;
    }

    @Override
    public Class<T> getEntityClass() {
        return entityClass;
    }
}
