package com.sw.domain.facade.note;

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
        String sql="select new com.sw.domain.util.PostVo(n.id,n.title,n.postTime,n.editTime) from Note n order by "+
                "case when n.editTime is null then n.postTime else n.editTime end desc";
        return entityManager.createQuery(sql).getResultList();
    }

}
