package net.lsf.dao.impl;

import net.lsf.dao.CommentsDao;
import net.lsf.model.Comments;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CommentsDaoImpl implements CommentsDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addComments(Comments comments) {
        getCurrentSession().save(comments);
    }

    @Override
    public void updateComments(Comments comments, int id) {
        Comments commentsToUpdate = getComments(id);
        updateCommentDetails(comments, commentsToUpdate);
    }

    @Override
    public Comments getComments(int id) {
        Comments comments = (Comments) getCurrentSession().get(Comments.class, id);
        return comments;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private void updateCommentDetails(Comments comments, Comments commentsToUpdate) {
        commentsToUpdate.setComments(comments.getComments());

    }
}
