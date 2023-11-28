package fr.unice.polytech.steats.restaurant.MenuComments;

import java.util.UUID;

public class MenuComment {
    final UUID CommentId;
    final UUID MenuId ;
    final UUID UserId;
    String Text;

    public MenuComment(UUID menuId, UUID userId,String text) {
        CommentId=UUID.randomUUID();
        MenuId = menuId;
        UserId = userId;
        Text=text;
    }

    public UUID getCommentId() {
        return CommentId;
    }
}
