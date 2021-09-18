package com.mycompany.dummy;

import com.mycompany.entitybase.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DummyService extends BaseService<Dummy> {
    @Autowired
    public DummyService(DummyRepo dao) {
        super(dao);
    }
}
