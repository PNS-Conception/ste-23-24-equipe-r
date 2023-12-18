package fr.unice.polytech.steats.steatspico.entities.restaurant.MenuComments;

import fr.unice.polytech.steats.steatspico.entities.restaurant.Menu;
import fr.unice.polytech.steats.steatspico.repositories.CommentsRepository;
import fr.unice.polytech.steats.steatspico.entities.users.User;

import java.util.Optional;

public class CommentsRegistry {
    final CommentsRepository commentsRepository;

    public CommentsRegistry(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    public MenuComment register(User user, Menu menu, String text){
        MenuComment menuComment = new MenuComment(user.getId(),menu.getId(),text);
        commentsRepository.save(menuComment,menuComment.getCommentId());
        return menuComment;
    }

    public Optional<MenuComment> findById(MenuComment menuComment){
        return commentsRepository.findById(menuComment.CommentId);
    }
}
