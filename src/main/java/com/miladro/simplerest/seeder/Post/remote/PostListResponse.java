package com.miladro.simplerest.seeder.Post.remote;

import lombok.Data;

import java.util.List;

@Data
public class PostListResponse {
    List<PostResponseModel> postList;
}
