package com.sagar.flatmates.com.sagar.flatmates.com.sagar.flatmates.domain;

import com.sagar.flatmates.com.sagar.flatmates.constants.AppConstants;

/**
 * Created by santosh sagar on 10-12-2017.
 */

public class FCM {

    private Long id;
    private String userName;
    private String deviceType = AppConstants.DEVICE_ANDROID;
    private String deviceManufacturer;
    private String deviceModel;
    private String deviceVersion;
    private String deviceVersionRelease;
    private String fcmToken;
    private boolean active = true;

    public Long getId() {
        return id;
    }

    public FCM setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public FCM setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public FCM setDeviceType(String deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    public String getDeviceManufacturer() {
        return deviceManufacturer;
    }

    public FCM setDeviceManufacturer(String deviceManufacturer) {
        this.deviceManufacturer = deviceManufacturer;
        return this;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public FCM setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
        return this;
    }

    public String getDeviceVersion() {
        return deviceVersion;
    }

    public FCM setDeviceVersion(String deviceVersion) {
        this.deviceVersion = deviceVersion;
        return this;
    }

    public String getDeviceVersionRelease() {
        return deviceVersionRelease;
    }

    public FCM setDeviceVersionRelease(String deviceVersionRelease) {
        this.deviceVersionRelease = deviceVersionRelease;
        return this;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public FCM setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public FCM setActive(boolean active) {
        this.active = active;
        return this;
    }

    @Override
    public String toString() {
        return "FCM{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", deviceManufacturer='" + deviceManufacturer + '\'' +
                ", deviceModel='" + deviceModel + '\'' +
                ", deviceVersion='" + deviceVersion + '\'' +
                ", deviceVersionRelease='" + deviceVersionRelease + '\'' +
                ", fcmToken='" + fcmToken + '\'' +
                ", active=" + active +
                '}';
    }
}