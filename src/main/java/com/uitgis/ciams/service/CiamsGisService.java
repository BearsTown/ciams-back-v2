package com.uitgis.ciams.service;

import com.uitgis.spatial.adpater.mapstudio.dto.MapStudioResult;
import com.uitgis.gis.dto.UploadDataDto;

public interface CiamsGisService {

    MapStudioResult.LayerBody deleteLayer(String layerName) throws Exception;
    MapStudioResult.LayerBody uploadKrasLayer(UploadDataDto.Upload param) throws Exception;
}
