package com.train.schedule.service;

import com.train.schedule.model.Train;
import com.train.schedule.model.request.TrainRequest;
import com.train.schedule.model.request.TrainSearchRequest;
import com.train.schedule.repository.ITrainRepository;
import com.train.schedule.service.impl.TrainService;
import org.apache.tomcat.util.http.ResponseUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TrainServiceTest {
    @InjectMocks
    private TrainService trainService;

    @Mock
    private TrainRequest trainRequest;

    @Mock
    private ITrainRepository iTrainRepository;

    @Mock
    private Train train;


    @BeforeEach
    void setup() {
        LinkedHashSet<String> stations = new LinkedHashSet();
        LinkedHashSet<String> stations2 = new LinkedHashSet();

        List<Train> trains = new ArrayList<>();
        stations.add("Dharwar Railway Station".toLowerCase());
        stations.add("Hubli Junction".toLowerCase());
        stations.add("Davangere Railway Station".toLowerCase());
        stations.add("Bangalore Yesvantpur Junction".toLowerCase());
        stations.add("Bangalore City Junction".toLowerCase());


        stations2.add("jalna");
        stations2.add("aurangabad");
        stations2.add("pune");

        Train train1 = new Train();
        train1.setTrainName("SBC VANDE BHARAT Train");
        train1.setTrainId(2L);
        train1.setTrainNumber("20662");
        train1.setStations(stations);

        Train train2 = new Train();
        train2.setTrainName("Pune express");
        train2.setTrainId(1L);
        train2.setTrainNumber("00021");
        train2.setStations(stations2);

        trains.add(train1);
        trains.add(train2);

        TrainRequest trainRequest_1 = new TrainRequest(1L, "20662", stations, "SBC VANDE BHARAT Train");
        Mockito.when(iTrainRepository.save(Mockito.any())).thenReturn(train1);
        Mockito.when(iTrainRepository.findByTrainNumber("00021")).thenReturn(train2);
        Mockito.when(iTrainRepository.existsByTrainNumber("00021")).thenReturn(true);
        Mockito.when(iTrainRepository.findStationBySource(Mockito.any())).thenReturn(trains);

    }

    @Test
    void savetrain() throws Exception {
        LinkedHashSet<String> stations = new LinkedHashSet();
        stations.add("Dharwar Railway Station".toLowerCase());
        stations.add("Hubli Junction".toLowerCase());
        stations.add("Davangere Railway Station".toLowerCase());
        stations.add("Bangalore Yesvantpur Junction".toLowerCase());
        stations.add("Bangalore City Junction".toLowerCase());

        TrainRequest trainRequest_1 = new TrainRequest(1L, "0001", stations, "SBC VANDE BHARAT Train");

        Train train1 = trainService.saveOrUpdateTrainDetails(trainRequest_1);
        Assertions.assertEquals("SBC VANDE BHARAT Train",train1.getTrainName());
    }
    @Test
    void getTrainDetailsByTrainNumber(){
        Train train1 = trainService.getTrainByTrainNumnber("00021");

        Assertions.assertEquals("Pune express",train1.getTrainName());
    }
    @Test
    void deleteByTrainNumber(){
        String train3 = trainService.deleteByTrainNumber("00021");
        Assertions.assertEquals("deleted",train3);
    }
    @Test
    void searchTrainsBySourceAndDestenation(){
        TrainSearchRequest trainSearchRequest = new TrainSearchRequest();
        trainSearchRequest.setSource("Davangere Railway Station");
        trainSearchRequest.setDestination("Bangalore City Junction");
        List<Train> train4 = trainService.searchTrainsBySourceAndDestenation(trainSearchRequest);
        Assertions.assertEquals("20662",train4.get(0).getTrainNumber());
    }
}

