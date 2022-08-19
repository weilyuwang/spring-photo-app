package com.weilyu.photoapp.photoappalbumsservice.service;

import com.weilyu.photoapp.photoappalbumsservice.data.AlbumEntity;

import java.util.List;

public interface AlbumsService {

    List<AlbumEntity> getAlbums(String userId);
}