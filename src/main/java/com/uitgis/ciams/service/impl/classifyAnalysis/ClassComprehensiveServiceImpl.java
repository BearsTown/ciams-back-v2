package com.uitgis.ciams.service.impl.classifyAnalysis;

import com.uitgis.ciams.dto.classifyAnalysis.comprehensive.ClassComprehensiveDto;
import com.uitgis.ciams.mapper.classifyAnalysis.ClassComprehensiveMapper;
import com.uitgis.ciams.model.CiamsImage;
import com.uitgis.ciams.service.classifyAnalysis.ClassComprehensiveService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClassComprehensiveServiceImpl implements ClassComprehensiveService {
    private final ClassComprehensiveMapper classComprehensiveMapper;


    /**
     * 유형화종합분석 상세정보
     *
     * @param zoneNo
     * @return
     */
    @Override
    public ClassComprehensiveDto.Info getComprehensiveInfo(String zoneNo) {
        return classComprehensiveMapper.findComprehensiveInfoById(zoneNo);
    }


    @Override
    public CiamsImage getImage(int id) {
        return classComprehensiveMapper.selectImage(id);
    }

}
