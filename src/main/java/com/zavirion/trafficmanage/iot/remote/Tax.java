package com.zavirion.trafficmanage.iot.remote;

import jakarta.ejb.Remote;

@Remote
public interface Tax {
    public String getDetails();
}
