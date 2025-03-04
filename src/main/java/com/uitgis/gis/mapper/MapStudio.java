package com.uitgis.gis.mapper;

import com.daonsw.spatial.dto.TableSchemaDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MapStudio {
    boolean layerExists(String layerName);

    void layerRename(TableSchemaDto.Rename params);

    void deleteLayer(String layerName);
}
