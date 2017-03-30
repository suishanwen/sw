package com.sw.domain.facade.autovote;


import com.google.inject.persist.Transactional;
import com.sw.domain.entity.autovote.*;
import com.sw.domain.entity.util.DateUtils;
import com.sw.domain.facade.BaseFacade;
import com.sw.domain.util.TaskVO;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class AutoVoteFacade extends BaseFacade {

    public List<TaskIndex> getValidTaskIndex() {
        return find(TaskIndex.class, "from TaskIndex where isValid=1");
    }

    public List<TaskVO> getTaskInfoToday() {
        Date today = DateUtils.parseDate(DateUtils.formatDate(new Date(), DateUtils.YYYY_MM_DD), DateUtils.YYYY_MM_DD);
        String sql = "select new com.sw.domain.util.TaskVO(" +
                " ti.taskName," +
                " ti.taskCate," +
                " ti.ipType," +
                " ti.downloadAddress," +
                " ti.isValid," +
                " tvb.id.backgroundNo," +
                " tvb.quantityRequire," +
                " tvb.quantityFinished," +
                " tvb.idActive," +
                " tvb.idTotal," +
                " tvb.idAllow," +
                " tvb.idCate," +
                " tvb.price," +
                " tvb.createDateTime," +
                " tvb.updateDateTime)" +
                " from TaskVsBackground tvb,TaskIndex ti" +
                " where ti.taskName=tvb.id.taskName" +
                " and (tvb.createDateTime>?1" +
                " or tvb.updateDateTime>?1)" +
                " order by tvb.updateDateTime desc";
        return find(TaskVO.class, sql, Collections.singletonList(today));
    }

    @Transactional
    public TaskIndex addTaskIndex(String taskName, int taskCate, int ipType, String downloadAddress) {
        return persist(new TaskIndex(taskName, taskCate, ipType, downloadAddress, 1));
    }

    @Transactional
    public TaskIndex updateTaskIndex(String taskName, int isValid) {
        TaskIndex taskIndex = get(TaskIndex.class, taskName);
        taskIndex.setIsValid(isValid);
        return persist(taskIndex);
    }

    @Transactional
    public TaskVsBackground addOrUpdateTaskBackground(String taskName, String backgroundNo, BigDecimal quantityRequire,
                                                      BigDecimal quantityFinished, int idActive, int idTotal, int idAllow,
                                                      String idCate, BigDecimal price) {
        TaskVsBackground taskVsBackground = get(TaskVsBackground.class, new TaskVsBackgroundPK(taskName, backgroundNo));
        Date now = new Date();
        if (taskVsBackground != null) {
            taskVsBackground.updateBackground(quantityRequire, quantityFinished, idActive, idTotal, idAllow, idCate, price, now);
            return merge(taskVsBackground);
        } else {
            return persist(new TaskVsBackground(
                    new TaskVsBackgroundPK(taskName, backgroundNo)
                    , quantityRequire
                    , quantityFinished
                    , idActive
                    , idTotal
                    , idAllow
                    , idCate
                    , price
                    , now
                    , now
            ));
        }
    }

    public BestTask getBestTask() {
        return get(BestTask.class, 1);
    }

    @Transactional
    public BestTask setBestTask(String taskName, String backgroundNo, String idCate, BigDecimal price) {
        BestTask bestTask = getBestTask();
        if (bestTask != null) {
            bestTask.setBestTask(taskName, backgroundNo, idCate, price, new Date());
            return persist(bestTask);
        } else {
            return persist(new BestTask(1, taskName, backgroundNo, idCate, price, new Date()));
        }
    }

    @Transactional
    public CurrentTaskIndex startTask(String userId, String taskName) {
        Date now = new Date();
        update("update TaskExecRecord set stopDateTime=?1 where id.userId=?2 and stopDateTime is null", now, userId);
        TaskExecRecord taskExecRecord = new TaskExecRecord(new TaskExecRecordPK(userId, taskName, now), null);
        persist(taskExecRecord);
        CurrentTaskIndex currentTaskIndex = get(CurrentTaskIndex.class, userId);
        if (currentTaskIndex != null) {
            currentTaskIndex.startTask(taskName, now);
            return merge(currentTaskIndex);
        } else {
            return persist(new CurrentTaskIndex(userId, taskName, new Date()));
        }
    }

    @Transactional
    public Boolean uploadTaskReward(String taskName, String backGroundNo, String userId, String workId, String subId, int quantity) {
        TaskRewardPK taskExecRecordPK = new TaskRewardPK(taskName, backGroundNo, userId, workId, subId);
        TaskReward taskReward = get(TaskReward.class, taskExecRecordPK);
        Date now = new Date();
        if (taskReward != null) {
            taskReward.setQuantity(quantity);
            taskReward.setUploadDateTime(now);
            persist(taskReward);
        } else {
            persist(new TaskReward(taskExecRecordPK, quantity, now));
        }
        return true;
    }
}
