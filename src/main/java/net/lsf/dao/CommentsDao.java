package net.lsf.dao;


import net.lsf.model.Comments;

public interface CommentsDao {
    void addComments(Comments comments);
    void updateComments(Comments comments, int id);
    public Comments getComments(int id);
}
