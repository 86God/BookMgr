package com.company.service.impl;

import com.company.bean.Type;
import com.company.dao.TypeDao;
import com.company.dao.impl.TypeDaoImpl;
import com.company.service.TypeService;

import java.util.List;

public class TypeServiceImpl implements TypeService {

    TypeDao typeDao = new TypeDaoImpl();

    @Override
    public List<Type> query() {

        return typeDao.query();
    }
}
