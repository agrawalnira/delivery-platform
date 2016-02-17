package com.social.services;

import com.social.database.SocialServiceDAO;
import com.social.model.Profile;

import java.util.List;

/**
 * Created by niranjan.agrawal on 2/8/16.
 */
public class ProfileDAOImpl implements ProfileDAO {

    @Override
    public List<Profile> getAllProfiles() {
        return null;
    }

    @Override
    public Profile getProfile(String username) {
        return SocialServiceDAO.getProfile(username);
    }

    @Override
    public boolean addProfile(Profile profile) {
        return SocialServiceDAO.registerNewProfile(profile);
    }

    @Override
    public Profile updateProfile(Profile profile) {
        return null;
    }

    @Override
    public Profile removeProfile(long id) {
        return null;
    }

}
