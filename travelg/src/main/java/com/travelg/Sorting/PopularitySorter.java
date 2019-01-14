package com.travelg.Sorting;

import com.travelg.Model.Sight;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
public class PopularitySorter {

    @GetMapping("/popularitySort/{cityName}")
    public List<PopularityDto> sortByPopularity(@PathVariable String cityName) throws IOException {
        List<PopularityDto> popularityDtoList = new ArrayList<>();
        List<Sight> sights;

        sights = SightDataRetriever.returnData(cityName);

        for(int i = 0; i< SightDataRetriever.getCount(); i++) {
            PopularityDto popularityDto = new PopularityDto();
                popularityDto.setName(sights.get(i).getName());
                popularityDto.setPhotoCount(sights.get(i).getPhotoCount());
                popularityDtoList.add(popularityDto);
            }

        popularityDtoList.sort(Comparator.comparing(PopularityDto::getPhotoCount));
        return popularityDtoList;
    }
}
