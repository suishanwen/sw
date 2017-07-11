package com.sw.domain.facade.note;

import com.google.common.collect.Lists;
import com.sw.domain.entity.note.Note;
import com.sw.domain.facade.BaseFacade;
import com.sw.domain.util.PostVo;
import com.google.inject.persist.Transactional;

import java.util.Date;
import java.util.List;

public class NoteFacade extends BaseFacade {


    @Transactional
    public Note addNote(Note note) {
        note.setPostTime(new Date());
        try{
            entityManager.persist(note);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return note;
    }


    @Transactional
    public Note editNote(Note note) {
        note.setEditTime(new Date());
        entityManager.merge(note);
        return note;
    }


    public Note getNote(Integer id) {
        return entityManager.find(Note.class, id);
    }

    public List<PostVo> getAllPost() {
        String sql="SELECT\n" +
                "    id,\n" +
                "    title,\n" +
                "    post_time,\n" +
                "    edit_time\n" +
                "FROM\n" +
                "    note\n" +
                "ORDER BY\n" +
                "    CASE\n" +
                "        WHEN edit_time IS NULL THEN post_time\n" +
                "        ELSE edit_time\n" +
                "    END\n" +
                "DESC";
        return createNativeQuery(sql, Lists.newArrayList(),PostVo.class);
    }

    public List<PostVo> getRecommend() {
        String sql="SELECT\n" +
                "    id,\n" +
                "    title,\n" +
                "    summary,\n" +
                "    tag,\n" +
                "    post_time\n" +
                "FROM\n" +
                "    note\n" +
                "WHERE\n" +
                "    recommend = 1\n" +
                "ORDER BY post_time DESC\n" +
                "LIMIT 6";
        return createNativeQuery(sql, Lists.newArrayList(),PostVo.class);
    }
}
