package net.lsf.service.impl;

import net.lsf.dao.CommentsDao;
import net.lsf.model.Comments;
import net.lsf.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsDao commentsDao;

    @Override
    public void addComments(Comments comments) {
        commentsDao.addComments(comments);
    }

    @Override
    public void updateComments(Comments comments, int id) {
        commentsDao.updateComments(comments, id);
    }

}
