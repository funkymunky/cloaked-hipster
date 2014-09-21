package net.lsf.service;

import net.lsf.model.Comments;

public interface CommentsService {
    void addComments(Comments comments);
    void updateComments(Comments modifiedComments, int id);
}
