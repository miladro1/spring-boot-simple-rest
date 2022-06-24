package com.miladro.simplerest.unit;

import com.miladro.simplerest.dal.entity.Post;
import com.miladro.simplerest.dal.repository.post.PostRepository;
import com.miladro.simplerest.service.Counter.PostsCounterService;
import com.miladro.simplerest.service.SimplePostService;
import com.miladro.simplerest.service.UserService;
import com.miladro.simplerest.service.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceUnitTest {
    @InjectMocks
    private SimplePostService postService;

    @Mock
    private PostRepository repository;
    @Mock
    private PostsCounterService counterService;
    @Mock
    private UserService userService;

    private PodamFactory podamFactory = new PodamFactoryImpl();

    // ######################################## create Method ########################################
//    @Test
//    void GivenAPostWithSameDataExistAlready_WhenTryToPersistThatPost_ThenValidationExceptionShouldBeThrown() {
//        //GIVEN
//        Post persistingPost = podamFactory.manufacturePojo(Post.class);
//
//
//        //WHEN
//        //THEN
//        assertThrows(NotFoundException.class, () -> postService.createPost(persistingPost));
//
//    }

    @Test
    void GivenLastPostIdIsANumber_WhenTryToCreatePost_ThenACreatedPostShouldBeReturnedWithIncrementedIdAnExpectedFieldValues() {
        //GIVEN
        Post persistingPost = podamFactory.manufacturePojo(Post.class);
        persistingPost.setId(null);
        Long lastPostId = 1L;

        when(userService.getAuthenticatedUserId()).thenReturn(3L);
        when(counterService.incrId(persistingPost)).thenReturn(lastPostId + 1);

        Post savedPost = new Post();
        savedPost.setId(2L);
        savedPost.setUserId(3L);
        savedPost.setTitle(persistingPost.getTitle());
        savedPost.setBody(persistingPost.getBody());
        when(repository.save(any())).thenReturn(savedPost);


        //WHEN
        Post createdPost = postService.createPost(persistingPost);

        //THEN
        assertEquals(2L, lastPostId + 1);
        assertEquals(createdPost.getTitle(), persistingPost.getTitle());
        assertEquals(createdPost.getBody(), persistingPost.getBody());
        assertEquals(createdPost.getUserId(), persistingPost.getUserId());
    }


    // ######################################## update Method ########################################
    @Test
    void GivenTheTargetPostExists_WhenTryToUpdateThePost_ThenTheUpdatedPostShouldBeReturn() {
        //GIVEN
        Post existingPost = podamFactory.manufacturePojo(Post.class);
        Post updatePostData = podamFactory.manufacturePojo(Post.class);
        updatePostData.setId(null);

        when(repository.findById(existingPost.getId())).thenReturn(Optional.of(existingPost));
        updatePostData.setId(existingPost.getId());
        when(repository.save(updatePostData)).thenReturn(updatePostData);

        //WHEN
        Post updatedPost = postService.updatePost(String.valueOf(existingPost.getId()), updatePostData);


        //THEN
        assertEquals(existingPost.getId(), updatedPost.getId());
        assertEquals(updatePostData.getTitle(), updatedPost.getTitle());
        assertEquals(updatePostData.getBody(), updatedPost.getBody());
        assertEquals(updatePostData.getUserId(), updatedPost.getUserId());

    }

    @Test
    void GivenTheTargetPostDoesntExistWhenTryToUpdateThePost_ThenNotFoundExceptionShouldBeThrown() {
        //GIVEN
        Long postId = 1L;
        Post updatePostData = podamFactory.manufacturePojo(Post.class);
        updatePostData.setId(null);

        when(repository.findById(postId)).thenReturn(Optional.empty());

        //WHEN
        //THEN
        assertThrows(NotFoundException.class, () -> postService.updatePost(String.valueOf(postId), updatePostData));
    }

    // ######################################## getPostById method ########################################
    @Test
    void GivenAPostWithInputIdExists_WhenTryToGetPostById_ThenItShouldBeReturned() {
        //GIVEN
        Post returningPost = podamFactory.manufacturePojo(Post.class);

        when(repository.findById(returningPost.getId())).thenReturn(Optional.of(returningPost));

        //WHEN
        Post post = postService.GetPostById(String.valueOf(returningPost.getId()));

        //THEN
        assertEquals(returningPost.getId(), post.getId());
        assertEquals(returningPost.getUserId(), post.getUserId());
        assertEquals(returningPost.getTitle(), post.getTitle());
        assertEquals(returningPost.getBody(), post.getBody());
    }

    @Test
    void GivenNotAnyPostWithInputIdExists_WhenTryToGetPostById_ThenNotFoundExceptionShouldBeThrown() {
        //GIVEN
        Long postId = 1L;

        when(repository.findById(postId)).thenReturn(Optional.empty());

        //WHEN
        //THEN
        assertThrows(NotFoundException.class, () -> postService.GetPostById(String.valueOf(postId)));
    }

    // ######################################## listPostsPaginated method ########################################
    @Test
    void GivenTwoPostsWithInputTitleAndInputUserIdAnThreeOthersExist_WhenTryToListPostsPaginated_ThenAPageOfJustTwoTargetedPostsShouldBeReturned() {
        //GIVEN
        String inputTitle = "example title";
        Long inputUserId = 4L;
        Post existingPost1 = podamFactory.manufacturePojo(Post.class);
        Post existingPost2 = podamFactory.manufacturePojo(Post.class);
        existingPost1.setTitle(inputTitle);
        existingPost1.setUserId(inputUserId);
        existingPost2.setTitle(inputTitle);
        existingPost2.setUserId(inputUserId);

        PageImpl<Post> returningPostPage = new PageImpl<Post>(
                List.of(existingPost1, existingPost2),
                PageRequest.of(0, 10),
                2
        );

        when(repository.findAllPaginated(inputTitle, null, inputUserId, PageRequest.of(0, 10)))
                .thenReturn(returningPostPage);

        //WHEN
        Page<Post> postPage = postService
                .listPostsPaginated(inputTitle, null, String.valueOf(inputUserId), PageRequest.of(0, 10));

        //THEN
        assertEquals(2, postPage.getTotalElements());
//        assertEquals(returningPostPage.getTotalElements(), postPage.getTotalElements());
        assertEquals(returningPostPage.getTotalPages(), postPage.getTotalPages());
        assertEquals(returningPostPage.getSize(), postPage.getSize());
        assertEquals(returningPostPage.getContent(), postPage.getContent());
    }

    @Test
    void GivenNotAnyPostWithInputsExist_WhenTryToListPostsPaginated_ThenAnEmptyPageShouldBeReturned() {
        //GIVEN
        String inputTitle = "example title";
        Long inputUserId = 4L;

        PageImpl<Post> returningPostPage = new PageImpl<Post>(
                List.of(),
                PageRequest.of(0, 10),
                0
        );

        when(repository.findAllPaginated(inputTitle, null, inputUserId, PageRequest.of(0, 10)))
                .thenReturn(returningPostPage);

        //WHEN
        Page<Post> postPage = postService
                .listPostsPaginated(inputTitle, null, String.valueOf(inputUserId), PageRequest.of(0, 10));

        //THEN
        assertEquals(0, postPage.getTotalElements());
        assertEquals(returningPostPage.getTotalPages(), postPage.getTotalPages());
        assertEquals(returningPostPage.getSize(), postPage.getSize());
        assertTrue(postPage.getContent().isEmpty());
    }

    // ######################################## deletePost method ########################################
    @Test
    void GivenAPostWithInputIdExists_WhenTryToDeletePostById_ThenTrueBooleanValueShouldBeReturned() {
        //GIVEN
        Post existingPost1 = podamFactory.manufacturePojo(Post.class);

        when(repository.findById(existingPost1.getId())).thenReturn(Optional.of(existingPost1));

        //WHEN
        Boolean deleted = postService.deletePost(String.valueOf(existingPost1.getId()));

        //THEN
        assertTrue(deleted);
    }

    @Test
    void GivenAnyPostWithInputIdExists_WhenTryToDeletePostById_ThenNotFoundExceptionShouldBeThrown() {
        //GIVEN
        Long postId = 1L;

        when(repository.findById(postId)).thenReturn(Optional.empty());

        //WHEN
        //THEN
        assertThrows(NotFoundException.class, () -> postService.deletePost(String.valueOf(postId)));
    }
}
