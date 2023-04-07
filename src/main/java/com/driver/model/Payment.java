package com.driver.model;

import javax.persistence.*;

@Entity
public class Payment
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Boolean isPaymentCompleted;

    private PaymentMode paymentMode;


    @OneToOne (mappedBy = "payment", cascade = CascadeType.ALL)
    Reservation reservation;


    public Payment() {
    }

    public Payment(int id, Boolean isPaymentCompleted, PaymentMode paymentMode, Reservation reservation) {
        this.id = id;
        this.isPaymentCompleted = isPaymentCompleted;
        this.paymentMode = paymentMode;
        this.reservation = reservation;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getIsPaymentCompleted() {
        return isPaymentCompleted;
    }

    public void setIsPaymentCompleted(Boolean isPaymentCompleted) {
        this.isPaymentCompleted = isPaymentCompleted;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
