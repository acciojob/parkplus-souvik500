package com.driver.services.impl;

import com.driver.model.Payment;
import com.driver.model.PaymentMode;
import com.driver.model.Reservation;
import com.driver.repository.PaymentRepository;
import com.driver.repository.ReservationRepository;
import com.driver.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    ReservationRepository reservationRepository2;
    @Autowired
    PaymentRepository paymentRepository2;

    @Override
    public Payment pay(Integer reservationId, int amountSent, String mode) throws Exception {

        Reservation reservation = reservationRepository2.findById(reservationId).get();

        Payment payment = new Payment();

        if (mode.equalsIgnoreCase(PaymentMode.CASH.name()) ||
            mode.equalsIgnoreCase(PaymentMode.UPI.name()) ||
            mode.equalsIgnoreCase(PaymentMode.CARD.name()))
        {
            payment.setPaymentMode(PaymentMode.valueOf(mode));

            int perHrBill = reservation.getSpot().getPricePerHour();
            if (reservation.getNumberOfHours() * perHrBill > amountSent) throw new Exception("Insufficient Amount");

            else {
                payment.setIsPaymentCompleted(true);
            }
            payment.setReservation(reservation);
        }
        else throw new Exception("Payment mode not detected");

        return payment;

    }
}
