import org.example.controller.FrameDataRepository;
import org.example.controller.FrameDataService;
import org.example.model.FrameData;
import org.example.scraper.DustloopScraper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class FrameDataServiceTest {
    @Mock
    private FrameDataRepository frameDataRepository;
    @InjectMocks
    private FrameDataService frameDataService;
    @InjectMocks
    private DustloopScraper dustloopScraper;

    @Test
    public void testGetFrameDataByCharacterAndInput(){
        //dustloopScraper.scrape();
        FrameData mockFrameData = new FrameData("Izanami","5A","10");
        Mockito.when(frameDataRepository.findByCharacterNameAndInput("Izanami","5A")).thenReturn(mockFrameData);
        FrameData resultFrameData = frameDataService.getFrameDataByCharacterAndInput("Izanami","5A");
        assertEquals("Izanami",resultFrameData.getCharacterName());
    }

    @Test
    public void testGetFrameDataByCharacter(){
        List<FrameData> mockFrameDataList = dustloopScraper.extractTableTest();
        Mockito.when(frameDataRepository.findByCharacterName("Izanami")).thenReturn(mockFrameDataList);
        List<FrameData> result = frameDataService.getFrameDataByCharacter("Izanami");
        assertEquals(result,mockFrameDataList);
    }

}
