package com.weilyu.photoapp.photoappusersservice.data;


import com.weilyu.photoapp.photoappusersservice.ui.model.AlbumResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "albums-service")
public interface AlbumsServiceFeignClient {

    @GetMapping("/users/{id}/albums")
    List<AlbumResponseModel> getAlbums(@PathVariable String id);
}