package com.miladro.simplerest.seeder.Post.remote;

import com.miladro.simplerest.config.feign.FeignResponseConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(name = "posts-data-source-client", url = "${remote.datasource.posts}", configuration = FeignResponseConfiguration.class)
public interface PostHttpDataSourceClient {

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<PostResponseModel> getPostList();
//    PostListResponse getPostList();
}
