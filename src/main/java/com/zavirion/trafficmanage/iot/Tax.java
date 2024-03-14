package com.zavirion.trafficmanage.iot;

import jakarta.ejb.Remote;

@Remote
public interface Tax {
    public String getDetails();
}
