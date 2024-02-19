package com.train.schedule.service.impl;


import com.train.schedule.model.Train;
import com.train.schedule.model.request.TrainRequest;
import com.train.schedule.model.request.TrainSearchRequest;
import com.train.schedule.repository.ITrainRepository;
import com.train.schedule.service.ITrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TrainService implements ITrainService {
    @Autowired
    private ITrainRepository iTrainRepository;
    @Override
    public Train saveOrUpdateTrainDetails(TrainRequest trainRequest) {
        Train train = new Train();
        if(iTrainRepository.existsByTrainNumber(trainRequest.getTrainNumber())){
            train=iTrainRepository.findByTrainNumber(trainRequest.getTrainNumber());
        }
        train.setTrainNumber(trainRequest.getTrainNumber());
        train.setTrainName(trainRequest.getTrainName());
        train.setStations(new LinkedHashSet<>( trainRequest.getStations().stream().map(String :: toLowerCase).collect(Collectors.toList())));
        iTrainRepository.save(train);

        return train;
    }

    @Override
    public Train getTrainByTrainNumnber(String trainNumber) {
        return  iTrainRepository.findByTrainNumber(trainNumber);
    }

    @Override
    public List<Train>  searchTrainsBySourceAndDestenation(TrainSearchRequest request) {
        List<Train> finalRouteTrain = new ArrayList<>();
        List<Train> trains = iTrainRepository.findStationBySource(request.getSource().toLowerCase());
        for (Train train :trains){
            LinkedHashSet<String> stations = train.getStations();
            int sourceIndex = 0;
            int destinationIndex=0;
            int i =0;
            for(String station : stations){
                if(station.equals(request.getSource().toLowerCase())){
                    sourceIndex=i;
                }
                if(station.equals(request.getDestination().toLowerCase())){
                    destinationIndex=i;
                }
                i++;
            }
            if(sourceIndex<destinationIndex){
                finalRouteTrain.add(train);
            }
        }
        return finalRouteTrain;
    }

    @Override
    public String deleteByTrainNumber(String trainNumber) {
        if (iTrainRepository.existsByTrainNumber(trainNumber)) {
            iTrainRepository.deleteByTrainNumber(trainNumber);
            return "deleted";
        }else {
            return  "Train Number is not Exists";
        }
    }
}
