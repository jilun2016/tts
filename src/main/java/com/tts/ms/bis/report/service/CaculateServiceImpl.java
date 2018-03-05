package com.tts.ms.bis.report.service;

import com.tts.ms.bis.report.vo.AqlCaculateResultVo;
import com.xcrm.cloud.database.db.BaseDaoSupport;
import com.xcrm.cloud.database.db.query.Ssqb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author gaoyan
 */
@Service
@Transactional
public class CaculateServiceImpl implements ICaculateService {

    protected Logger log = LoggerFactory.getLogger(CaculateServiceImpl.class);

    @Autowired
    private BaseDaoSupport dao;

    @Override
    public List<AqlCaculateResultVo> caculate(String caculateType, Integer shippingSize, String majorValue, String minorValue) {
        Ssqb query = Ssqb.create("com.jlt.tts.caculate.caculateAqlSample")
                .setParam("caculateType", caculateType)
                .setParam("shippingSize", shippingSize)
                .setParam("majorValue", majorValue)
                .setParam("minorValue", minorValue);
        return dao.findForList(query, AqlCaculateResultVo.class);
    }
}
