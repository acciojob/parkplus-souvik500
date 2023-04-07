package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserRepository userRepository3;
    @Autowired
    SpotRepository spotRepository3;
    @Autowired
    ReservationRepository reservationRepository3;
    @Autowired
    ParkingLotRepository parkingLotRepository3;
    @Override
    public Reservation reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception {

        User user;
        ParkingLot parkingLot;
        try {
            user = userRepository3.findById(userId).get();
            parkingLot = parkingLotRepository3.findById(parkingLotId).get();
        } catch (Exception e) {
            throw new Exception("Cannot make reservation");
        }

        List<Spot> spotList = new ArrayList<>();

//        Spot spot1 = new Spot();
//        for (Spot spot : parkingLot.getSpotList())
//        {
//            SpotType spotType = null;
//            if (numberOfWheels == 2) spotType = SpotType.TWO_WHEELER;
//            else if (numberOfWheels == 4) spotType = SpotType.FOUR_WHEELER;
//            else spotType = SpotType.OTHERS;
//
//            spot.setSpotType(spotType);
//            spot.setPricePerHour(100);
//            spot.setOccupied(true);
//            spot.setParkingLot(parkingLot);
//
//            spotList.add(spot);
//        }
        Payment payment = new Payment();
        payment.setPaymentCompleted(true);
        payment.setPaymentMode(PaymentMode.CASH);

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setNoOfHours(timeInHours);
        payment.setReservation(reservation);

        for (Spot spot : parkingLot.getSpotList())
        {
            SpotType spotType = null;
            if (numberOfWheels == 2) spotType = SpotType.TWO_WHEELER;
            else if (numberOfWheels == 4) spotType = SpotType.FOUR_WHEELER;
            else spotType = SpotType.OTHERS;

            spot.setSpotType(spotType);
            spot.setPricePerHour(100);
            spot.setOccupied(true);
            spot.setParkingLot(parkingLot);
            spot.getReservationList().add(reservation);

            spotList.add(spot);
        }

        user.getReservationList().add(reservation);

        parkingLot.setSpotList(spotList);

        parkingLotRepository3.save(parkingLot);

        userRepository3.save(user);

        return reservation;
    }
}
