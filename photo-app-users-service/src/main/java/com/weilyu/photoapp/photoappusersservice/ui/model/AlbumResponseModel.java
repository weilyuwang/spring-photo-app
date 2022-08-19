package com.weilyu.photoapp.photoappusersservice.ui.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumResponseModel {
    private String albumId;
    private String userId;
    private String name;
    private String description;
}
