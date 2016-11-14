package com.sw.domain.facade.util;

import com.google.inject.persist.Transactional;
import com.sw.domain.entity.util.SwSequence;
import com.sw.domain.facade.BaseFacade;

import java.util.Calendar;
import java.util.List;


public class SequenceFacade extends BaseFacade {

    @Transactional
    public String nextSequence(String table,String column){
        List<SwSequence> swSequenceList =entityManager.createQuery("from SwSequence s where s.tableName=:table and s.columnName=:column")
                .setParameter("table", table).setParameter("column", column).getResultList();
        if (swSequenceList.size() > 0) {
            SwSequence swSequence = swSequenceList.get(0);
            String seq = (Integer.parseInt(swSequence.getSequence()) + 1) + "";
            swSequence.setSequence(seq);
            entityManager.persist(swSequence);
            return seq;
        } else {
            Calendar calendar=Calendar.getInstance();
            int year=calendar.get(Calendar.YEAR);
            SwSequence swSequence =new SwSequence();
            swSequence.setTableName(table);
            swSequence.setColumnName(column);
            swSequence.setSequence(year+"001");
            entityManager.persist(swSequence);
            return swSequence.getSequence();
        }
    }
}
