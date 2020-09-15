package com.example.CarpathiansBlog.seeders;

import com.example.CarpathiansBlog.models.Post;
import com.example.CarpathiansBlog.models.Role;
import com.example.CarpathiansBlog.models.User;
import com.example.CarpathiansBlog.repo.PostRepository;
import com.example.CarpathiansBlog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PostsSeeder {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Transactional
    public void run() {
        User user = userRepository.findByUsername("smethan");
        if (user != null) {
            if(postRepository.findByTitle("Post 1") != null)
                return;
            //set 1 post
            Post post = new Post();
            post.setTitle("Post 1");
            post.setAnons("Description anons");
            post.setFullText("Description");
            post.setImage("img_1.jpg");
            post.setViews(0);
            post.setUser(user);
            postRepository.save(post);

            //set 2 post
            post = new Post();
            post.setTitle("Post 2");
            post.setAnons("Description anons");
            post.setFullText("Description");
            post.setImage("img_2.jpg");
            post.setViews(0);
            post.setUser(user);
            postRepository.save(post);

            //set 3 post
            post = new Post();
            post.setTitle("Post 3");
            post.setAnons("Description anons");
            post.setFullText("Description");
            post.setImage("img_3.jpg");
            post.setViews(0);
            post.setUser(user);
            postRepository.save(post);

            //set 4 post
            post = new Post();
            post.setTitle("Post 4");
            post.setAnons("Description anons");
            post.setFullText("Description");
            post.setImage("img_4.jpg");
            post.setViews(0);
            post.setUser(user);
            postRepository.save(post);

            //set 5 post
            post = new Post();
            post.setTitle("Post 5");
            post.setAnons("Description anons");
            post.setFullText("Description");
            post.setImage("img_5.jpg");
            post.setViews(0);
            post.setUser(user);
            postRepository.save(post);

            //set 6 post
            post = new Post();
            post.setTitle("Post 6");
            post.setAnons("Description anons");
            post.setFullText("Description");
            post.setImage("img_6.jpg");
            post.setViews(0);
            post.setUser(user);
            postRepository.save(post);

            //set 7 post
            post = new Post();
            post.setTitle("Post 7");
            post.setAnons("Description anons");
            post.setFullText("Description");
            post.setImage("img_7.jpg");
            post.setViews(0);
            post.setUser(user);
            postRepository.save(post);

            //set 8 post
            post = new Post();
            post.setTitle("Post 8");
            post.setAnons("Description anons");
            post.setFullText("Description");
            post.setImage("img_8.jpg");
            post.setViews(0);
            post.setUser(user);
            postRepository.save(post);

            //set 9 post
            post = new Post();
            post.setTitle("Post 9");
            post.setAnons("Description anons");
            post.setFullText("Description");
            post.setImage("img_9.jpg");
            post.setViews(0);
            post.setUser(user);
            postRepository.save(post);

            //set 10 post
            post = new Post();
            post.setTitle("Post 10");
            post.setAnons("Description anons");
            post.setFullText("Description");
            post.setImage("img_10.jpg");
            post.setViews(0);
            post.setUser(user);
            postRepository.save(post);
        }
    }
}
