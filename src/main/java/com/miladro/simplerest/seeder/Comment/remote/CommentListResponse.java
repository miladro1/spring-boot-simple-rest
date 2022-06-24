package com.miladro.simplerest.seeder.Comment.remote;

import lombok.Data;

import java.util.List;

@Data
public class CommentListResponse {
    List<CommentResponseModel> commentList;
}
