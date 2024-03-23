package com.zavirion.trafficmanage.iot.impl;

import com.zavirion.trafficmanage.iot.remote.Tax;
import jakarta.ejb.Stateless;

@Stateless
public class TaxBean implements Tax {
    @Override
    public String getDetails() {
        return "Hello";
    }
}
