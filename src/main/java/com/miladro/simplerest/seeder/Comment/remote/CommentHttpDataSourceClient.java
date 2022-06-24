package com.miladro.simplerest.seeder.Comment.remote;

import com.miladro.simplerest.config.feign.FeignResponseConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Component
@FeignClient(name = "comments-data-source-client", url = "${remote.datasource.comments}", configuration = FeignResponseConfiguration.class)
public interface CommentHttpDataSourceClient {

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<CommentResponseModel> getCommentList();
//    CommentListResponse getCommentList();
}
