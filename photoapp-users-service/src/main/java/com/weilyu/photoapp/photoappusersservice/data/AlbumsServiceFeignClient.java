package com.weilyu.photoapp.photoappusersservice.data;


import com.weilyu.photoapp.photoappusersservice.ui.model.AlbumResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "albums-service", fallback = AlbumsFallback.class)
public interface AlbumsServiceFeignClient {

    @GetMapping("/users/{id}/albums")
    List<AlbumResponseModel> getAlbums(@PathVariable String id);
}


@Component
class AlbumsFallback implements AlbumsServiceFeignClient {

    // callback method signature must match with the original method
    @Override
    public List<AlbumResponseModel> getAlbums(String id) {
        return new ArrayList<>();
    }
}