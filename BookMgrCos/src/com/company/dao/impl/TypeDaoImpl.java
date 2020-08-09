package com.company.dao.impl;

import com.company.bean.Type;
import com.company.dao.TypeDao;

import java.util.List;

public class TypeDaoImpl extends BaseDao implements TypeDao {

    @Override
    public List<Type> query() {
        String sql = "select * from t_type";
        return queryForList(Type.class,sql);
    }
}
