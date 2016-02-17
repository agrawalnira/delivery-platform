package com.social.services;

import com.social.model.Profile;

import java.util.List;

/**
 * Created by niranjan.agrawal on 2/8/16.
 */

public interface ProfileDAO {

    public List<Profile> getAllProfiles();

    public Profile getProfile(String username);

    public boolean addProfile(Profile profile);

    public Profile updateProfile(Profile profile);

    public Profile removeProfile(long id);
}
